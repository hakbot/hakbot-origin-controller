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
package io.hakbot.controller.plugin;

import alpine.Config;
import alpine.logging.Logger;
import org.apache.commons.lang3.StringUtils;
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
 *     provider.${pluginId}.instance1.token=${token}
 *
 *     # Properties for instance2
 *     ...
 * </pre>
 */
public class RemoteInstanceAutoConfig {

    // Setup logging
    private static final Logger LOGGER = Logger.getLogger(RemoteInstanceAutoConfig.class);

    public Map<String, RemoteInstance> createMap(Plugin.Type pluginType, String pluginId) {
        LOGGER.info("Initializing instance properties");
        final  Map<String, RemoteInstance> instanceMap = new HashMap<>();
        final String type = pluginType.name().toLowerCase();
        final String[] instances = StringUtils.split(Config.getInstance().getProperty(type + "." + pluginId + ".instances"), ",");
        if (instances == null) {
            LOGGER.info("Instances were not specified. Unable to autoconfigure.");
            return instanceMap;
        }
        for (String instanceIdentifier: instances) {
            instanceIdentifier = instanceIdentifier.trim();
            final RemoteInstance instance = generateInstance(pluginType, pluginId, instanceIdentifier);
            instanceMap.putIfAbsent(instance.getAlias(), instance);
        }
        return instanceMap;
    }

    public RemoteInstance resolveInstance(Plugin.Type pluginType, String pluginId, String alias) {
        return createMap(pluginType, pluginId).get(alias);
    }

    private RemoteInstance generateInstance(Plugin.Type pluginType, String pluginId, String instanceIdentifier) {
        final String type = pluginType.name().toLowerCase();
        final RemoteInstance instance = new RemoteInstance();
        instance.setAlias(StringUtils.trimToNull(Config.getInstance().getProperty(type + "." + pluginId + "." + instanceIdentifier + ".alias")));
        instance.setUsername(StringUtils.trimToNull(Config.getInstance().getProperty(type + "." + pluginId + "." + instanceIdentifier + ".username")));
        instance.setPassword(StringUtils.trimToNull(Config.getInstance().getProperty(type + "." + pluginId + "." + instanceIdentifier + ".password")));
        instance.setApiKey(StringUtils.trimToNull(Config.getInstance().getProperty(type + "." + pluginId + "." + instanceIdentifier + ".apikey")));
        instance.setToken(StringUtils.trimToNull(Config.getInstance().getProperty(type + "." + pluginId + "." + instanceIdentifier + ".token")));
        try {
            instance.setURL(new URL(StringUtils.trimToNull(Config.getInstance().getProperty(type + "." + pluginId + "." + instanceIdentifier + ".url"))));
        } catch (MalformedURLException e) {
            LOGGER.error("The URL specified for the server instance is not valid. " + e.getMessage());
        }
        return instance;
    }

}
