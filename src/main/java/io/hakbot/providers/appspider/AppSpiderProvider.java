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
import io.hakbot.util.PayloadUtil;
import io.hakbot.util.UuidUtil;
import org.apache.commons.collections.MapUtils;
import java.util.Base64;
import javax.xml.namespace.QName;
import java.util.Map;

public class AppSpiderProvider extends BaseProvider {

    // Setup logging
    private static final Logger logger = Logger.getLogger(AppSpiderProvider.class);

    private static Map<String, RemoteInstance> instanceMap = new RemoteInstanceAutoConfig().createMap(Type.PROVIDER, "appspider");
    private static final QName serviceName = new QName("http://ntobjectives.com/webservices/", "NTOService");

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
        String token = UuidUtil.stripHyphens(job.getUuid());
        Result submitResult = soap.runScanXml(remoteInstance.getUsername(), remoteInstance.getPassword(), token, new String(Base64.getDecoder().decode(scanConfig)), null, null);
        if (!submitResult.isSuccess()) {
            job.addMessage("Failed to execute AppSpider job");
            job.addMessage(submitResult.getErrorDescription());
            return false;
        }
        boolean running = true;
        try {
            while (running) {
                Result statusResult = soap.isScanRunning(remoteInstance.getUsername(), remoteInstance.getPassword(), token);
                if (statusResult.getData().equalsIgnoreCase("false")) {
                    //todo: investigate how to get scan result

                    return true;
                }
                Thread.sleep(20 * 1000);
            }
        } catch (InterruptedException e) {
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

}
