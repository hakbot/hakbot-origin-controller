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
import com.restjob.providers.RemoteScanEngine;
import com.restjob.providers.RemoteScanEngineAutoConfig;
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

    private static Map<String, RemoteScanEngine> scanEngineMap = new RemoteScanEngineAutoConfig().createMap("nessus");

    private RemoteScanEngine engine;
    private String scanName;
    private String scanPolicy;
    private String targets;

    @Override
    public boolean initialize(Job job) {
        Map<String, String> params = PayloadUtil.toParameters(job.getPayload());
        if (!PayloadUtil.requiredParams(params, "scanName", "scanPolicy", "targets")) {
            job.addMessage("Invalid request. Expected parameters: [scanName], [scanPolicy], [targets]");
            return false;
        }
        engine = scanEngineMap.get(MapUtils.getString(params, "scanner"));
        if (engine == null) {
            engine = new RemoteScanEngine();
            if (!PayloadUtil.requiredParams(params, "nessusUrl", "username", "password")) {
                job.addMessage("Invalid request. Expected parameters: [nessusUrl], [username], [password]");
                return false;
            }
            engine.setUrl(MapUtils.getString(params, "nessusUrl"));
            engine.setUsername(MapUtils.getString(params, "username"));
            engine.setPassword(MapUtils.getString(params, "password"));
            engine.setValidateCertificates(MapUtils.getBooleanValue(params, "validateCertificates"));
        }
        scanName = MapUtils.getString(params, "scanName");
        scanPolicy = MapUtils.getString(params, "scanPolicy");
        targets = MapUtils.getString(params, "targets");
        return true;
    }

    public boolean process(Job job) {
        try {
            ScanClientV6 scan = (ScanClientV6)ClientFactory.createScanClient(engine.getUrl(), 6, !engine.isValidateCertificates());
            scan.login(engine.getUsername(), engine.getPassword());
            String scanID = scan.newScan(scanName, scanPolicy, targets);
            while (scan.isScanRunning(scanID)) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }
            }
            ReportClient reportClient = ClientFactory.createReportClient(engine.getUrl(), 6, !engine.isValidateCertificates());
            reportClient.login(engine.getUsername(), engine.getPassword());
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

}
