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
package com.restjob.controller.plugin;

import com.restjob.controller.Config;
import com.restjob.controller.logging.Logger;
import org.apache.commons.lang.StringUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Providers and publishers can be auto-configured if the properties follow the following conventions:
 * <pre>
 *     # Enable the following scanners
 *     provider.${pluginId}.instances=instance1, instance2
 *
 *     # Properties for instance1
 *     provider.${pluginId}.instance1.alias=My-Scanner
 *     provider.${pluginId}.instance1.url=http://scanner1.example.com
 *     provider.${pluginId}.instance1.username=${username}
 *     provider.${pluginId}.instance1.password=${password}
 *     provider.${pluginId}.instance1.apikey=${apikey}
 *
 *     # Properties for instance2
 *     ...
 * </pre>
 */
public class RemoteInstanceAutoConfig {

    // Setup logging
    private static final Logger logger = Logger.getLogger(RemoteInstanceAutoConfig.class);

    public Map<String, RemoteInstance> createMap(Plugin.Type pluginType, String pluginId) {
        logger.info("Initializing instance properties");
        Map<String, RemoteInstance> instanceMap = new HashMap<>();
        String type = pluginType.name().toLowerCase();
        String[] instances = StringUtils.split(Config.getInstance().getProperty(type + "." + pluginId + ".instances"), ",");
        if (instances == null) {
            logger.info("Instances were not specified. Unable to autoconfigure.");
            return instanceMap;
        }
        for (String s: instances) {
            s = s.trim();
            RemoteInstance instance = new RemoteInstance();
            instance.setAlias(StringUtils.trimToNull(Config.getInstance().getProperty(type + "." + pluginId + "." + s + ".alias")));
            instance.setUsername(StringUtils.trimToNull(Config.getInstance().getProperty(type + "." + pluginId + "." + s + ".username")));
            instance.setPassword(StringUtils.trimToNull(Config.getInstance().getProperty(type + "." + pluginId + "." + s + ".password")));
            instance.setApiKey(StringUtils.trimToNull(Config.getInstance().getProperty(type + "." + pluginId + "." + s + ".apikey")));
            try {
                instance.setURL(new URL(StringUtils.trimToNull(Config.getInstance().getProperty(type + "." + pluginId + "." + s + ".url"))));
            } catch (MalformedURLException e) {
                logger.error("The URL specified for the server instance is not valid. " + e.getMessage());
            }
            instanceMap.putIfAbsent(instance.getAlias(), instance);
        }
        return instanceMap;
    }

}
