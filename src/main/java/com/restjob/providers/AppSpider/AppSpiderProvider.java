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
package com.restjob.providers.appspider;

import com.restjob.controller.Config;
import com.restjob.controller.logging.Logger;
import com.restjob.controller.model.Job;
import com.restjob.providers.BaseProvider;
import com.restjob.providers.appspider.ws.NTOService;
import com.restjob.providers.appspider.ws.NTOServiceSoap;
import com.restjob.providers.appspider.ws.Result;
import com.restjob.providers.appspider.ws.SYSTEMINFO;

import javax.xml.ws.Holder;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AppSpiderProvider extends BaseProvider {

    // Setup logging
    private static final Logger logger = Logger.getLogger(AppSpiderProvider.class);

    private static Map<String, ScanEngine> scanEngineMap = new HashMap<>();
    static {
        String[] scanners = Config.getInstance().getProperty("provider.appspider.scanners").split(",");
        for (String s: scanners) {
            s = s.trim();
            ScanEngine engine = new ScanEngine();
            engine.setAlias(Config.getInstance().getProperty("provider.appspider." + s + ".alias").trim());
            engine.setUsername(Config.getInstance().getProperty("provider.appspider." + s + ".username").trim());
            engine.setPassword(Config.getInstance().getProperty("provider.appspider." + s + ".password").trim());
            try {
                engine.setWsdlLocation(new URL(Config.getInstance().getProperty("provider.appspider." + s + ".url").trim()));
            } catch (MalformedURLException e) {
                logger.error("The URL specified for the scanner engine is not valid. " + e.getMessage());
            }
            scanEngineMap.putIfAbsent(engine.getAlias(), engine);
        }
    }

    private ScanEngine engine;

    @Override
    public boolean initialize(Job job) {
        //todo: change this - testing only - need to define the payload for this provider
        String alias = job.getPayload();
        this.engine = scanEngineMap.get(alias);
        if (engine == null) {
            logger.error("The specified scan engine is not defined.");
            return false;
        }
        return true;
    }

    public boolean process(Job job) {
        Holder<Result> resultHolder = new Holder<>();
        Holder<SYSTEMINFO> dataHolder = new Holder<>();

        NTOService service = new NTOService(engine.getWsdlLocation(), engine.getServiceName());
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
        NTOService service = new NTOService(engine.getWsdlLocation(), engine.getServiceName());
        NTOServiceSoap soap = service.getNTOServiceSoap();
        return !soap.isBusy(engine.getUsername(), engine.getPassword());
    }

}
