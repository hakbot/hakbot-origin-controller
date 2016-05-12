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
import io.hakbot.providers.appspider.ws.SYSTEMINFO;
import javax.xml.ws.Holder;
import java.util.Map;

public class AppSpiderProvider extends BaseProvider {

    // Setup logging
    private static final Logger logger = Logger.getLogger(AppSpiderProvider.class);

    private static Map<String, RemoteInstance> scanEngineMap = new RemoteInstanceAutoConfig().createMap(Type.PROVIDER, "appspider");

    private AppSpiderInstance engine;

    @Override
    public boolean initialize(Job job) {
        //todo: change this - testing only - need to define the payload for this provider
        String alias = job.getProviderPayload();
        this.engine = (AppSpiderInstance)scanEngineMap.get(alias);
        if (engine == null) {
            logger.error("The specified scan engine is not defined.");
            return false;
        }
        return true;
    }

    public boolean process(Job job) {
        Holder<Result> resultHolder = new Holder<>();
        Holder<SYSTEMINFO> dataHolder = new Holder<>();

        NTOService service = new NTOService(engine.getURL(), engine.getServiceName());
        NTOServiceSoap soap = service.getNTOServiceSoap();

        soap.getSysInfo(engine.getUsername(), engine.getPassword(), null, resultHolder, dataHolder);

        Result result = resultHolder.value;
        if (result.isSuccess()) {
            String resultData = result.getData();
            System.out.println(resultData);
        } else {
            System.out.println(result.getErrorDescription());
        }

        SYSTEMINFO systeminfo = dataHolder.value;
        System.out.println(systeminfo.getTotalRAM());

        setResult(systeminfo.getTotalRAM());

        return result.isSuccess();
    }

    public boolean cancel() {
        return true; //todo
    }

    @Override
    public boolean isAvailable(Job job) {
        NTOService service = new NTOService(engine.getURL(), engine.getServiceName());
        NTOServiceSoap soap = service.getNTOServiceSoap();
        return !soap.isBusy(engine.getUsername(), engine.getPassword());
    }

    public String getName() {
        return "AppSpider Pro";
    }

    public String getDescription() {
        return "Performs dynamic analysis using AppSpider Pro. Interacts with AppSpider instances using it's SOAP-based web services interface.";
    }

    public String getResultMimeType() {
        return "application/xml";
    }

    public String getResultExtension() {
        return "xml";
    }

}
