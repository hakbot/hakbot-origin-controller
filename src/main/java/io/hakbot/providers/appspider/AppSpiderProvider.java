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

import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.providers.BaseProvider;
import io.hakbot.controller.plugin.RemoteInstance;
import io.hakbot.controller.plugin.RemoteInstanceAutoConfig;
import io.hakbot.providers.appspider.ws.NTOService;
import io.hakbot.providers.appspider.ws.NTOServiceSoap;
import io.hakbot.providers.appspider.ws.Result;
import io.hakbot.providers.appspider.ws.SCANSTATUS2;
import io.hakbot.util.PayloadUtil;
import io.hakbot.util.UuidUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.fluent.Response;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import java.io.IOException;
import java.io.StringReader;
import java.util.Base64;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.util.Map;

public class AppSpiderProvider extends BaseProvider {

    // Setup logging
    private static final Logger logger = Logger.getLogger(AppSpiderProvider.class);

    private static Map<String, RemoteInstance> instanceMap = new RemoteInstanceAutoConfig().createMap(Type.PROVIDER, "appspider");
    protected static final QName serviceName = new QName("http://ntobjectives.com/webservices/", "NTOService");

    private RemoteInstance remoteInstance;
    private String scanConfig;

    @Override
    public boolean initialize(Job job) {
        Map<String, String> params = PayloadUtil.toParameters(job.getProviderPayload());
        if (!PayloadUtil.requiredParams(params, "instance", "scanConfig")) {
            job.addMessage("Invalid request. Expected parameters: [instance], [config]");
            return false;
        }
        remoteInstance = instanceMap.get(MapUtils.getString(params, "instance"));
        if (remoteInstance == null) {
            return false;
        }
        scanConfig = MapUtils.getString(params, "scanConfig");
        return true;
    }

    public boolean process(Job job) {
        NTOService service = new NTOService(remoteInstance.getURL(), serviceName);
        NTOServiceSoap soap = service.getNTOServiceSoap();

        // Retrieve UUID from job and use it as the AppSpider scan token
        String token = UuidUtil.stripHyphens(job.getUuid());

        // Decodes the scan config (should be XML)
        String decodedScanConfig = new String(Base64.getDecoder().decode(scanConfig));

        // Submit the scan request
        Result submitResult = soap.runScanXml(remoteInstance.getUsername(), remoteInstance.getPassword(), token, decodedScanConfig, null, null);
        if (!submitResult.isSuccess()) {
            job.addMessage("Failed to execute AppSpider job");
            job.addMessage(submitResult.getErrorDescription());
            return false;
        }
        boolean running = true;
        try {
            while (running) {
                // Check to see if the scan is running
                Result runningResult = soap.isScanRunning(remoteInstance.getUsername(), remoteInstance.getPassword(), token);
                if (runningResult.getData().equalsIgnoreCase("false")) {

                    // Get the scan date and create the 'format' of the date that will be used in the URL
                    SCANSTATUS2 scanStatus2 = soap.getStatus2(remoteInstance.getUsername(), remoteInstance.getPassword(), token);
                    XMLGregorianCalendar startTime = scanStatus2.getStartTime();
                    String dirDate =
                                    startTime.getYear() + "_" +
                                    String.format("%02d", startTime.getMonth()) + "_" +
                                    String.format("%02d", startTime.getDay()) + "_" +
                                    String.format("%02d", startTime.getHour()) + "_" +
                                    String.format("%02d", startTime.getMinute());

                    // Parse the scanConfig and retrieve the scan name
                    String scanName = getScanName(decodedScanConfig);

                    // Using the scan date, and scan config, create the URL where the report will be accessible from
                    String reportUrl = remoteInstance.getUrl().substring(0, remoteInstance.getUrl().lastIndexOf("/")) + "/Reports/" + scanName + "/" + dirDate + "/VulnerabilitiesSummary.xml";

                    // Download report
                    Response response = Request.Get(reportUrl).execute();
                    HttpResponse httpResponse = response.returnResponse();
                    StatusLine statusLine = httpResponse.getStatusLine();
                    HttpEntity entity = httpResponse.getEntity();
                    if (httpResponse.getStatusLine().getStatusCode() >= 300) {
                        job.addMessage("Unable to download report file. Status Code: " + statusLine.getStatusCode());
                    }
                    if (entity == null) {
                        job.addMessage("Report contains no data");
                    }

                    //Save result
                    setResult(IOUtils.toByteArray(entity.getContent()));
                    return true;
                }
                Thread.sleep(20 * 1000);
            }
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean cancel(Job job) {
        NTOService service = new NTOService(remoteInstance.getURL(), serviceName);
        NTOServiceSoap soap = service.getNTOServiceSoap();
        String token = UuidUtil.stripHyphens(job.getUuid());
        Result running = soap.isScanRunning(remoteInstance.getUsername(), remoteInstance.getPassword(), token);
        if (running.getData().equalsIgnoreCase("true")) {
            Result cancel = soap.stopScan(remoteInstance.getUsername(), remoteInstance.getPassword(), token, false);
            return cancel.isSuccess();
        }
        return false;
    }

    @Override
    public boolean isAvailable(Job job) {
        NTOService service = new NTOService(remoteInstance.getURL(), serviceName);
        NTOServiceSoap soap = service.getNTOServiceSoap();
        return !soap.isBusy(remoteInstance.getUsername(), remoteInstance.getPassword());
    }

    public String getName() {
        return "AppSpider Pro";
    }

    public String getDescription() {
        return "Performs dynamic analysis using AppSpider Pro. Interacts with AppSpider Pro SOAP-based WebServices.";
    }

    public String getResultMimeType() {
        return "application/xml";
    }

    public String getResultExtension() {
        return "xml";
    }

    private String getScanName(String decodedScanConfig) {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        InputSource source = new InputSource(new StringReader(decodedScanConfig));
        String scanName = null;
        try {
            scanName = xpath.evaluate("/ScanConfig/Name", source);
        } catch (XPathExpressionException e) {
        }
        return scanName;
    }

}
