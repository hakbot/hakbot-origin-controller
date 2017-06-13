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
package io.hakbot.controller.workers;

import alpine.Config;
import alpine.logging.Logger;
import io.hakbot.HakbotConfigKey;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.plugin.Plugin;
import org.apache.commons.lang3.StringUtils;
import java.util.ArrayList;
import java.util.List;

public class ExpectedClassResolver {

    // Setup logging
    private static final Logger LOGGER = Logger.getLogger(ExpectedClassResolver.class);

    private static final List<String> PROVIDERS_CONFIGURED = new ArrayList<>();
    private static final List<String> PUBLISHERS_CONFIGURED = new ArrayList<>();
    private static final List<Class> RESOLVED_PROVIDERS = new ArrayList<>();
    private static final List<Class> RESOLVED_PUBLISHERS = new ArrayList<>();
    static {
        String[] classes = Config.getInstance().getProperty(HakbotConfigKey.PROVIDERS_ENABLED).split(",");
        for (String clazz : classes) {
            PROVIDERS_CONFIGURED.add(clazz.trim());
        }
        classes = Config.getInstance().getProperty(HakbotConfigKey.PUBLISHERS_ENABLED).split(",");
        for (String clazz : classes) {
            PUBLISHERS_CONFIGURED.add(clazz.trim());
        }
    }

    /**
     * Resolves the Class for the specified Job and plugin type. The plugin needs to be whitelisted
     * in order to be resolved. If plugin is not whitelisted, an ExpectedClassResolverException
     * is thrown.
     */
    private Class resolveClass(Plugin.Type type, String pluginClass) throws ClassNotFoundException, ExpectedClassResolverException {
        if (type.equals(Plugin.Type.PROVIDER)) {
            if (PROVIDERS_CONFIGURED.contains(pluginClass)) {
                return Class.forName(pluginClass, false, this.getClass().getClassLoader());
            }
        } else {
            if (PUBLISHERS_CONFIGURED.contains(pluginClass)) {
                return Class.forName(pluginClass, false, this.getClass().getClassLoader());
            }
        }
        throw new ExpectedClassResolverException();
    }

    /**
     * Resolves the Class for the specified Job's provider. The provider needs to be whitelisted
     * in order to be resolved. If provider is not whitelisted, an ExpectedClassResolverException
     * is thrown.
     */
    public Class resolveProvider(Job job) throws ClassNotFoundException, ExpectedClassResolverException {
        return resolveClass(Plugin.Type.PROVIDER, job.getProvider());
    }

    /**
     * Resolves the Class for the specified Job's publisher. The publisher needs to be whitelisted
     * in order to be resolved. If publisher is not whitelisted, an ExpectedClassResolverException
     * is thrown.
     */
    public Class resolvePublisher(Job job) throws ClassNotFoundException, ExpectedClassResolverException {
        return resolveClass(Plugin.Type.PUBLISHER, job.getPublisher());
    }

    private List<Class> autoResolve(List<Class> resolveList, List<String> classNames) {
        if (resolveList.size() == 0) {
            for (String className: classNames) {
                try {
                    final Class clazz = Class.forName(className, false, this.getClass().getClassLoader());
                    resolveList.add(clazz);
                } catch (ClassNotFoundException e) {
                    LOGGER.error("Cannot resolve " + className);
                }
            }
        }
        return resolveList;
    }

    public List<Class> getResolvedProviders() {
        return autoResolve(RESOLVED_PROVIDERS, PROVIDERS_CONFIGURED);
    }

    public List<Class> getResolvedPubishers() {
        return autoResolve(RESOLVED_PUBLISHERS, PUBLISHERS_CONFIGURED);
    }

    public boolean isClassAllowed(String pluginClass) {
        if (StringUtils.isEmpty(pluginClass)) {
            return true;
        }
        return (PROVIDERS_CONFIGURED.contains(pluginClass) || PUBLISHERS_CONFIGURED.contains(pluginClass));
    }
}
