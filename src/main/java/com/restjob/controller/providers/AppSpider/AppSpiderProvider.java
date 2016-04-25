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
package com.restjob.controller.providers.AppSpider;

import com.restjob.controller.logging.Logger;
import com.restjob.controller.model.Job;
import com.restjob.controller.providers.BaseProvider;
import com.restjob.controller.providers.AppSpider.ws.NTOService;
import com.restjob.controller.providers.AppSpider.ws.NTOServiceSoap;
import com.restjob.controller.providers.AppSpider.ws.Result;
import com.restjob.controller.providers.AppSpider.ws.SYSTEMINFO;
import com.restjob.controller.providers.shell.ShellProvider;

import javax.xml.namespace.QName;
import javax.xml.ws.Holder;
import java.net.MalformedURLException;
import java.net.URL;

public class AppSpiderProvider extends BaseProvider {

    // Setup logging
    private static final Logger logger = Logger.getLogger(ShellProvider.class);

    private static final String username = "AppSpider";
    private static final String password = "Se!e2956ebb6a0c";

    public boolean process(Job job) {
        Holder<Result> resultHolder = new Holder<>();
        Holder<SYSTEMINFO> dataHolder = new Holder<>();

        URL url;
        try {
            url = new URL("http://appspider-east.example.com/AppSpiderEntScanEngine/default.asmx?WSDL");
        } catch (MalformedURLException e){
            logger.error(e.getMessage());
            return false;
        }

        NTOService service = new NTOService(url, new QName("NTOService"));
        NTOServiceSoap soap = service.getNTOServiceSoap();

        soap.getSysInfo(username, password, null, resultHolder, dataHolder);

        Result result = resultHolder.value;
        if (result.isSuccess()) {
            String resultData = result.getData();
            System.out.println(resultData);
        } else {
            System.out.println(result.getErrorDescription());
        }

        SYSTEMINFO systeminfo = dataHolder.value;
        System.out.println(systeminfo.getTotalRAM());

        return result.isSuccess();
    }

    public boolean cancel() {
        return true; //todo
    }

}
