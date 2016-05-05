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
package com.restjob.providers;

import com.restjob.controller.Config;
import com.restjob.controller.logging.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Providers can be auto-configured if the properties follow the following conventions:
 * <pre>
 *     # Enable the following scanners
 *     provider.${provider}.scanners=scanner1, scanner2
 *
 *     # Properties for scanner1
 *     provider.${provider}.scanner1.alias=My-Scanner
 *     provider.${provider}.scanner1.url=http://scanner1.example.com
 *     provider.${provider}.scanner1.username=${username}
 *     provider.${provider}.scanner1.password=${password}
 *
 *     # Properties for scanner2
 *     ...
 * </pre>
 */
public class RemoteScanEngineAutoConfig {

    // Setup logging
    private static final Logger logger = Logger.getLogger(RemoteScanEngineAutoConfig.class);

    public Map<String, RemoteScanEngine> createMap(String provider) {
        Map<String, RemoteScanEngine> scanEngineMap = new HashMap<>();

        String[] scanners = Config.getInstance().getProperty("provider." + provider + ".scanners").split(",");
        for (String s: scanners) {
            s = s.trim();
            RemoteScanEngine engine = new RemoteScanEngine();
            engine.setAlias(Config.getInstance().getProperty("provider." + provider + "." + s + ".alias").trim());
            engine.setUsername(Config.getInstance().getProperty("provider." + provider + "." + s + ".username").trim());
            engine.setPassword(Config.getInstance().getProperty("provider." + provider + "." + s + ".password").trim());
            try {
                engine.setURL(new URL(Config.getInstance().getProperty("provider." + provider + "." + s + ".url").trim()));
            } catch (MalformedURLException e) {
                logger.error("The URL specified for the scanner engine is not valid. " + e.getMessage());
            }
            scanEngineMap.putIfAbsent(engine.getAlias(), engine);
        }
        return scanEngineMap;
    }

}
