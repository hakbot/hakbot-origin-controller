/*
 * This file is part of RESTjob Controller.
 *
 * RESTjob Controller is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * RESTjob Controller is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * RESTjob Controller. If not, see http://www.gnu.org/licenses/.
 */
package com.restjob.providers.nessus;

import com.restjob.controller.logging.Logger;
import com.restjob.controller.model.Job;
import com.restjob.providers.BaseProvider;
import com.restjob.controller.plugin.RemoteInstance;
import com.restjob.controller.plugin.RemoteInstanceAutoConfig;
import com.restjob.util.PayloadUtil;
import net.continuumsecurity.ClientFactory;
import net.continuumsecurity.ReportClient;
import net.continuumsecurity.v6.ScanClientV6;
import net.continuumsecurity.v6.model.ExportFormat;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;
import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class NessusProvider extends BaseProvider {

    // Setup logging
    private static final Logger logger = Logger.getLogger(NessusProvider.class);

    private static Map<String, RemoteInstance> instanceMap = new RemoteInstanceAutoConfig().createMap(Type.PROVIDER, "nessus");

    private RemoteInstance remoteInstance;
    private String scanName;
    private String scanPolicy;
    private String targets;

    @Override
    public boolean initialize(Job job) {
        Map<String, String> params = PayloadUtil.toParameters(job.getProviderPayload());
        if (!PayloadUtil.requiredParams(params, "scanName", "scanPolicy", "targets")) {
            job.addMessage("Invalid request. Expected parameters: [scanName], [scanPolicy], [targets]");
            return false;
        }
        remoteInstance = instanceMap.get(MapUtils.getString(params, "instance"));
        if (remoteInstance == null) {
            remoteInstance = new RemoteInstance();
            if (!PayloadUtil.requiredParams(params, "nessusUrl", "username", "password")) {
                job.addMessage("Invalid request. Expected parameters: [nessusUrl], [username], [password]");
                return false;
            }
            remoteInstance.setUrl(MapUtils.getString(params, "nessusUrl"));
            remoteInstance.setUsername(MapUtils.getString(params, "username"));
            remoteInstance.setPassword(MapUtils.getString(params, "password"));
            remoteInstance.setValidateCertificates(MapUtils.getBooleanValue(params, "validateCertificates"));
        }
        scanName = MapUtils.getString(params, "scanName");
        scanPolicy = MapUtils.getString(params, "scanPolicy");
        targets = MapUtils.getString(params, "targets");
        return true;
    }

    public boolean process(Job job) {
        try {
            ScanClientV6 scan = (ScanClientV6)ClientFactory.createScanClient(remoteInstance.getUrl(), 6, !remoteInstance.isValidateCertificates());
            scan.login(remoteInstance.getUsername(), remoteInstance.getPassword());
            String scanID = scan.newScan(scanName, scanPolicy, targets);
            while (scan.isScanRunning(scanID)) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }
            }
            ReportClient reportClient = ClientFactory.createReportClient(remoteInstance.getUrl(), 6, !remoteInstance.isValidateCertificates());
            reportClient.login(remoteInstance.getUsername(), remoteInstance.getPassword());
            File report = scan.download(Integer.parseInt(scanID), ExportFormat.NESSUS, Paths.get("~/").toAbsolutePath());
            super.setResult(FileUtils.readFileToByteArray(report));
        } catch (LoginException e) {
            job.addMessage("Unable to login to Nessus");
            return false;
        } catch (IOException e) {
            job.addMessage("IOExceptio - Possibly due to downloading report");
            return false;
        }
        return true;
    }

    public boolean cancel() {
        return true; //todo
    }

    public String getName() {
        return "Nessus";
    }

    public String getDescription() {
        return "Performs a Nessus scan against one or more targets. Interacts with a Nessus instance using v6 of the XML-RPC interface.";
    }

    public String getResultMimeType() {
        return "application/xml";
    }

    public String getResultExtension() {
        return "nessus";
    }

}
