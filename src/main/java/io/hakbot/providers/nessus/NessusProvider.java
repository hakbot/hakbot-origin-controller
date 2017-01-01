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
package io.hakbot.providers.nessus;

import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.plugin.Console;
import io.hakbot.controller.plugin.ConsoleIdentifier;
import io.hakbot.controller.workers.State;
import io.hakbot.providers.AsynchronousProvider;
import io.hakbot.providers.BaseProvider;
import io.hakbot.controller.plugin.RemoteInstance;
import io.hakbot.controller.plugin.RemoteInstanceAutoConfig;
import io.hakbot.util.JsonUtil;
import net.continuumsecurity.ClientFactory;
import net.continuumsecurity.ReportClient;
import net.continuumsecurity.v6.ScanClientV6;
import net.continuumsecurity.v6.model.ExportFormat;
import org.apache.commons.io.FileUtils;
import javax.json.JsonObject;
import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class NessusProvider extends BaseProvider implements AsynchronousProvider, ConsoleIdentifier {

    // Setup logging
    private static final Logger logger = Logger.getLogger(NessusProvider.class);

    private static Map<String, RemoteInstance> instanceMap = new RemoteInstanceAutoConfig().createMap(Type.PROVIDER, NessusConstants.PLUGIN_ID);

    private RemoteInstance remoteInstance;
    private String scanName;
    private String scanPolicy;
    private String targets;

    @Override
    public boolean initialize(Job job) {
        JsonObject payload = JsonUtil.toJsonObject(job.getProviderPayload());
        if (!JsonUtil.requiredParams(payload, "scanName", "scanPolicy", "targets")) {
            addProcessingMessage(job, "Invalid request. Expected parameters: [scanName], [scanPolicy], [targets]");
            return false;
        }
        remoteInstance = instanceMap.get(JsonUtil.getString(payload, "instance"));
        if (remoteInstance == null) {
            remoteInstance = new RemoteInstance();
            if (!JsonUtil.requiredParams(payload, "url", "username", "password")) {
                addProcessingMessage(job, "Invalid request. Expected parameters: [url], [username], [password]");
                return false;
            }
            remoteInstance.setUrl(JsonUtil.getString(payload, "url"));
            remoteInstance.setUsername(JsonUtil.getString(payload, "username"));
            remoteInstance.setPassword(JsonUtil.getString(payload, "password"));
            remoteInstance.setValidateCertificates(JsonUtil.getBoolean(payload, "validateCertificates"));
            // Save the properties of the instance we're conducting the scan with
            setJobProperties(job, new HashMap<String, Object>() {{
                put(NessusConstants.PROP_SERVER_URL, remoteInstance.getUrl());
                put(NessusConstants.PROP_SCAN_USERNAME, remoteInstance.getUsername());
                put(NessusConstants.PROP_SCAN_PASSWORD, remoteInstance.getPassword());
                put(NessusConstants.PROP_SERVER_VALIDATE_CERTS, remoteInstance.isValidateCertificates());
            }});
        } else {
            // Save the alias of the remote instance we're conducting the scan with
            setJobProperty(job, NessusConstants.PROP_INSTANCE_ALIAS, remoteInstance.getAlias());
        }
        scanName = JsonUtil.getString(payload, "scanName");
        scanPolicy = JsonUtil.getString(payload, "scanPolicy");
        targets = JsonUtil.getString(payload, "targets");
        return true;
    }

    public void process(Job job) {
        try {
            ScanClientV6 scan = (ScanClientV6)ClientFactory.createScanClient(remoteInstance.getUrl(), 6, !remoteInstance.isValidateCertificates());
            scan.login(remoteInstance.getUsername(), remoteInstance.getPassword());
            String scanID = scan.newScan(scanName, scanPolicy, targets);

            // Save the scan ID Nessus assigned to the job
            setJobProperty(job, NessusConstants.PROP_SCAN_ID, scanID);

            boolean isRunning = scan.isScanRunning(scanID);
            while (isRunning) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }
                // Need to reinitialize the scan object as login tokens on log-running scans will expire.
                scan = (ScanClientV6)ClientFactory.createScanClient(remoteInstance.getUrl(), 6, !remoteInstance.isValidateCertificates());
                scan.login(remoteInstance.getUsername(), remoteInstance.getPassword());
                isRunning = scan.isScanRunning(scanID);
            }

            ReportClient reportClient = ClientFactory.createReportClient(remoteInstance.getUrl(), 6, !remoteInstance.isValidateCertificates());
            reportClient.login(remoteInstance.getUsername(), remoteInstance.getPassword());
            File report = scan.download(Integer.parseInt(scanID), ExportFormat.NESSUS, Paths.get(System.getProperty("java.io.tmpdir")));
            super.setResult(FileUtils.readFileToByteArray(report));
            report.delete();
        } catch (LoginException e) {
            addProcessingMessage(job, "Unable to login to Nessus");
            updateState(job, State.FAILED);
        } catch (IOException e) {
            addProcessingMessage(job, "IOException - Possibly due to downloading report");
            updateState(job, State.FAILED);
        }
        updateState(job, State.COMPLETED);
    }

    public boolean isRunning(Job job) {
        return true; //todo
    }

    public boolean cancel(Job job) {
        updateState(job, State.CANCELED);
        try {
            ScanClientV6 scan = (ScanClientV6)ClientFactory.createScanClient(remoteInstance.getUrl(), 6, !remoteInstance.isValidateCertificates());
            scan.login(remoteInstance.getUsername(), remoteInstance.getPassword());

            String scanId = getJobProperty(job, NessusConstants.PROP_SCAN_ID);

            // todo cancel the job with the specified scanId
        } catch (LoginException e) {
            addProcessingMessage(job,"Unable to login to Nessus");
            return false;
        }
        return true;
    }

    public String getName() {
        return "Nessus";
    }

    public String getDescription() {
        return "Performs a Nessus scan against one or more targets. Interacts using the Nessus v6 API.";
    }

    public String getResultMimeType() {
        return "application/xml";
    }

    public String getResultExtension() {
        return "nessus";
    }

    public Class<? extends Console> getConsoleClass() {
        return NessusConsole.class;
    }

}
