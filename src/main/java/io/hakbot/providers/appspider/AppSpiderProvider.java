/*
 * This file is part of Hakbot Origin Controller.
 *
 * Hakbot Origin Controller is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * Hakbot Origin Controller is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * Hakbot Origin Controller. If not, see http://www.gnu.org/licenses/.
 */
package io.hakbot.providers.appspider;

import alpine.logging.Logger;
import alpine.util.UuidUtil;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.JobArtifact;
import io.hakbot.controller.plugin.Console;
import io.hakbot.controller.plugin.ConsoleIdentifier;
import io.hakbot.controller.plugin.RemoteInstance;
import io.hakbot.controller.plugin.RemoteInstanceAutoConfig;
import io.hakbot.controller.workers.State;
import io.hakbot.providers.AsynchronousProvider;
import io.hakbot.providers.BaseProvider;
import io.hakbot.providers.appspider.ws.NTOService;
import io.hakbot.providers.appspider.ws.NTOServiceSoap;
import io.hakbot.providers.appspider.ws.Result;
import io.hakbot.providers.appspider.ws.SCANSTATUS2;
import io.hakbot.util.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.xml.sax.InputSource;
import javax.json.JsonObject;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.util.Base64;
import java.util.Map;

public class AppSpiderProvider extends BaseProvider implements AsynchronousProvider, ConsoleIdentifier {

    // Setup logging
    private static final Logger LOGGER = Logger.getLogger(AppSpiderProvider.class);

    private static Map<String, RemoteInstance> instanceMap = new RemoteInstanceAutoConfig().
            createMap(Type.PROVIDER, AppSpiderConstants.PLUGIN_ID);


    @Override
    public boolean initialize(Job job) {
        final JsonObject payload = JsonUtil.toJsonObject(getProviderPayload(job).getContents());
        if (!JsonUtil.requiredParams(payload, "instance", "scanConfig")) {
            addProcessingMessage(job, "Invalid request. Expected parameters: [instance], [config]");
            return false;
        }
        final RemoteInstance remoteInstance = instanceMap.get(JsonUtil.getString(payload, "instance"));
        if (remoteInstance == null) {
            return false;
        }
        // Save the remote instance used for this job
        setRemoteInstance(job, remoteInstance);
        return true;
    }

    public void process(Job job) {
        // Retrieve payload and extract scan config (which is currently Base64 encoded)
        final JsonObject payload = JsonUtil.toJsonObject(getProviderPayload(job).getContents());
        final String scanConfig = JsonUtil.getString(payload, "scanConfig");

        // Retrieve the remote instance defined during initialization
        final RemoteInstance remoteInstance = getRemoteInstance(job);

        final NTOService service = new NTOService(remoteInstance.getURL(), AppSpiderConstants.SERVICE_NAME);
        final NTOServiceSoap soap = service.getNTOServiceSoap();

        // Retrieve UUID from job and use it as the AppSpider scan token
        final String token = UuidUtil.stripHyphens(job.getUuid());
        setJobProperty(job, "token", token);

        // Decodes the scan config (should be XML)
        final String decodedScanConfig = new String(Base64.getDecoder().decode(scanConfig));

        // Parse the scanConfig and retrieve the scan name
        final String scanName = getScanName(decodedScanConfig);
        setJobProperty(job, "scanName", scanName);

        // Submit the scan request
        final Result submitResult = soap.runScanXml(remoteInstance.getUsername(), remoteInstance.getPassword(), token, decodedScanConfig, null, null);
        if (!submitResult.isSuccess()) {
            updateState(job, State.FAILED, "Failed to execute AppSpider job", submitResult.getErrorDescription());
        }
    }

    public boolean isRunning(Job job) {
        // Retrieve the remote instance defined during initialization
        final RemoteInstance remoteInstance = getRemoteInstance(job);
        final String token = getJobProperty(job, "token");
        final NTOService service = new NTOService(remoteInstance.getURL(), AppSpiderConstants.SERVICE_NAME);
        final NTOServiceSoap soap = service.getNTOServiceSoap();
        final Result runningResult = soap.isScanRunning(remoteInstance.getUsername(), remoteInstance.getPassword(), token);
        return !runningResult.getData().equalsIgnoreCase("false");
    }

    @Override
    public void getResult(Job job) {
        // Retrieve the remote instance defined during initialization
        final RemoteInstance remoteInstance = getRemoteInstance(job);
        final String token = getJobProperty(job, "token");

        // Create the URL where the report will be accessible from
        final String reportUrl = remoteInstance.getUrl().substring(0, remoteInstance.getUrl().lastIndexOf("/")) + "/Reports/" + token + "/VulnerabilitiesSummary.xml";
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Job UUID: " + job.getUuid() + " - Downloading report from: " + reportUrl);
        }

        try {
            // Download report
            final Response response = Request.Get(reportUrl).execute();
            final HttpResponse httpResponse = response.returnResponse();
            final StatusLine statusLine = httpResponse.getStatusLine();
            final HttpEntity entity = httpResponse.getEntity();
            if (httpResponse.getStatusLine().getStatusCode() >= 300) {
                updateState(job, State.FAILED, "Unable to download report file. Status Code: " + statusLine.getStatusCode());
            }

            // Convert result to byte array and save it
            final byte[] results = IOUtils.toByteArray(entity.getContent());
            addArtifact(job, JobArtifact.Type.PROVIDER_RESULT, JobArtifact.MimeType.XML.value(), results, "VulnerabilitySummary_" + job.getUuid() + ".xml");
        } catch (IOException e) {
            updateState(job, State.FAILED, "Unable to get scan result", e.getMessage());
        }
    }

    public boolean cancel(Job job) {
        // Retrieve the remote instance defined during initialization
        final RemoteInstance remoteInstance = getRemoteInstance(job);

        updateState(job, State.CANCELED);
        final NTOService service = new NTOService(remoteInstance.getURL(), AppSpiderConstants.SERVICE_NAME);
        final NTOServiceSoap soap = service.getNTOServiceSoap();
        final String token = UuidUtil.stripHyphens(job.getUuid());
        final Result running = soap.isScanRunning(remoteInstance.getUsername(), remoteInstance.getPassword(), token);
        if (running.getData().equalsIgnoreCase("true")) {
            final Result cancel = soap.stopScan(remoteInstance.getUsername(), remoteInstance.getPassword(), token, false);
            return cancel.isSuccess();
        }
        return false;
    }

    @Override
    public boolean isAvailable(Job job) {
        // Retrieve the remote instance defined during initialization
        final RemoteInstance remoteInstance = getRemoteInstance(job);
        final
        NTOService service = new NTOService(remoteInstance.getURL(), AppSpiderConstants.SERVICE_NAME);
        final NTOServiceSoap soap = service.getNTOServiceSoap();
        return !soap.isBusy(remoteInstance.getUsername(), remoteInstance.getPassword());
    }

    public String getName() {
        return "AppSpider Pro";
    }

    public String getDescription() {
        return "Performs dynamic analysis using AppSpider Pro. Interacts with AppSpider Pro SOAP-based WebServices.";
    }

    private String getScanName(String decodedScanConfig) {
        final XPathFactory xpathFactory = XPathFactory.newInstance();
        final XPath xpath = xpathFactory.newXPath();
        final InputSource source = new InputSource(new StringReader(decodedScanConfig));
        String scanName = null;
        try {
            scanName = xpath.evaluate("/ScanConfig/Name", source);
        } catch (XPathExpressionException e) {
            LOGGER.error("Error processing scan file: " + e.getMessage());
        }
        return scanName;
    }

    public Class<? extends Console> getConsoleClass() {
        return AppSpiderConsole.class;
    }

}
