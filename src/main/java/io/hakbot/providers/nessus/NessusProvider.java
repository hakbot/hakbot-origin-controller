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

import alpine.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.JobArtifact;
import io.hakbot.controller.plugin.Console;
import io.hakbot.controller.plugin.ConsoleIdentifier;
import io.hakbot.controller.plugin.RemoteInstance;
import io.hakbot.controller.plugin.RemoteInstanceAutoConfig;
import io.hakbot.controller.workers.State;
import io.hakbot.providers.AsynchronousProvider;
import io.hakbot.providers.BaseProvider;
import io.hakbot.util.JsonUtil;
import net.continuumsecurity.ClientFactory;
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

    @Override
    public boolean initialize(Job job) {
        JsonObject payload = JsonUtil.toJsonObject(getProviderPayload(job).getContents());
        if (!JsonUtil.requiredParams(payload, "scanName", "scanPolicy", "targets")) {
            addProcessingMessage(job, "Invalid request. Expected parameters: [scanName], [scanPolicy], [targets]");
            return false;
        }
        RemoteInstance remoteInstance = instanceMap.get(JsonUtil.getString(payload, "instance"));
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
        }

        // Save the remote instance used for this job
        setRemoteInstance(job, remoteInstance);

        // Save job properties
        HashMap<String, Object> properties = new HashMap<>();
        properties.put(NessusConstants.SCAN_NAME, JsonUtil.getString(payload, NessusConstants.SCAN_NAME));
        properties.put(NessusConstants.SCAN_POLICY, JsonUtil.getString(payload, NessusConstants.SCAN_POLICY));
        properties.put(NessusConstants.TARGETS, JsonUtil.getString(payload, NessusConstants.TARGETS));
        setJobProperties(job, properties);

        return true;
    }

    public void process(Job job) {
        // Retrieve remote instance and job properties defined during initialization
        RemoteInstance remoteInstance = getRemoteInstance(job);
        String scanName = getJobProperty(job, NessusConstants.SCAN_NAME);
        String scanPolicy = getJobProperty(job, NessusConstants.SCAN_POLICY);
        String targets = getJobProperty(job, NessusConstants.TARGETS);
        try {
            ScanClientV6 scan = (ScanClientV6)ClientFactory.createScanClient(remoteInstance.getUrl(), 6, !remoteInstance.isValidateCertificates());
            scan.login(remoteInstance.getUsername(), remoteInstance.getPassword());
            String scanID = scan.newScan(scanName, scanPolicy, targets);
            // Save the scan ID Nessus assigned to the job
            setJobProperty(job, NessusConstants.PROP_SCAN_ID, scanID);
            scan.logout();
        } catch (LoginException e) {
            updateState(job, State.FAILED, "Unable to login to Nessus");
        } catch (RuntimeException e) {
            updateState(job, State.FAILED, "Unable to process Nessus job. Likely cause is an invalid scan policy.");
        }
    }

    public boolean isRunning(Job job) {
        // Retrieve the remote instance defined during initialization
        RemoteInstance remoteInstance = getRemoteInstance(job);
        try {
            ScanClientV6 scan = (ScanClientV6) ClientFactory.createScanClient(remoteInstance.getUrl(), 6, !remoteInstance.isValidateCertificates());
            scan.login(remoteInstance.getUsername(), remoteInstance.getPassword());
            String scanId = getJobProperty(job, NessusConstants.PROP_SCAN_ID);
            boolean isRunning = scan.isScanRunning(scanId);
            scan.logout();
            return isRunning;
        } catch (LoginException e) {
            updateState(job, State.FAILED, "Unable to login to Nessus");
        }
        return false;
    }

    @Override
    public void getResult(Job job) {
        // Retrieve the remote instance defined during initialization
        RemoteInstance remoteInstance = getRemoteInstance(job);
        try {
            ScanClientV6 scan = (ScanClientV6) ClientFactory.createScanClient(remoteInstance.getUrl(), 6, !remoteInstance.isValidateCertificates());
            scan.login(remoteInstance.getUsername(), remoteInstance.getPassword());
            String scanId = getJobProperty(job, NessusConstants.PROP_SCAN_ID);
            File report = scan.download(Integer.parseInt(scanId), ExportFormat.NESSUS, Paths.get(System.getProperty("java.io.tmpdir")));

            // Convert result to byte array and save it
            byte[] result = FileUtils.readFileToByteArray(report);
            addArtifact(job, JobArtifact.Type.PROVIDER_RESULT, JobArtifact.MimeType.XML.value(), result, job.getUuid() + ".nessus");

            // Cleanup and logout
            report.delete();
            scan.logout();
        } catch (LoginException e) {
            updateState(job, State.FAILED, "Unable to login to Nessus");
        } catch (IOException e) {
            updateState(job, State.FAILED, "IOException - Possibly due to downloading report");
        }
    }

    public boolean cancel(Job job) {
        // Retrieve the remote instance defined during initialization
        RemoteInstance remoteInstance = getRemoteInstance(job);
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

    public Class<? extends Console> getConsoleClass() {
        return NessusConsole.class;
    }

}
