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

import io.hakbot.controller.Config;
import io.hakbot.controller.ConfigItem;
import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class ExpectedClassResolver {

    // Setup logging
    private static final Logger logger = Logger.getLogger(ExpectedClassResolver.class);

    private static final List<String> providersConfigured = new ArrayList<>();
    private static final List<String> publishersConfigured = new ArrayList<>();
    private static final List<Class> resolvedProviders = new ArrayList<>();
    private static final List<Class> resolvedPublishers = new ArrayList<>();
    static {
        String[] classes = Config.getInstance().getProperty(ConfigItem.PROVIDERS_ENALBED).split(",");
        for (String clazz : classes) {
            providersConfigured.add(clazz.trim());
        }
        classes = Config.getInstance().getProperty(ConfigItem.PUBLISHERS_ENABLED).split(",");
        for (String clazz : classes) {
            publishersConfigured.add(clazz.trim());
        }
    }

    /**
     * Resolves the Class for the specified Job and plugin type. The plugin needs to be whitelisted
     * in order to be resolved. If plugin is not whitelisted, an ExpectedClassResolverException
     * is thrown.
     */
    private Class resolveClass(Plugin.Type type, Job job) throws ClassNotFoundException, ExpectedClassResolverException {
        if (type.equals(Plugin.Type.PROVIDER)) {
            if (providersConfigured.contains(job.getProvider())) {
                return Class.forName(job.getProvider(), false, this.getClass().getClassLoader());
            }
        } else {
            if (publishersConfigured.contains(job.getPublisher())) {
                return Class.forName(job.getPublisher(), false, this.getClass().getClassLoader());
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
        return resolveClass(Plugin.Type.PROVIDER, job);
    }

    /**
     * Resolves the Class for the specified Job's publisher. The publisher needs to be whitelisted
     * in order to be resolved. If publisher is not whitelisted, an ExpectedClassResolverException
     * is thrown.
     */
    public Class resolvePublisher(Job job) throws ClassNotFoundException, ExpectedClassResolverException {
        return resolveClass(Plugin.Type.PUBLISHER, job);
    }

    private List<Class> autoResolve(List<Class> resolveList, List<String> classNames) {
        if (resolveList.size() == 0) {
            for (String className: classNames) {
                try {
                    Class clazz = Class.forName(className, false, this.getClass().getClassLoader());
                    resolveList.add(clazz);
                } catch (ClassNotFoundException e) {
                    logger.error("Cannot resolve " + className);
                }
            }
        }
        return resolveList;
    }

    public List<Class> getResolvedProviders() {
        return autoResolve(resolvedProviders, providersConfigured);
    }

    public List<Class> getResolvedPubishers() {
        return autoResolve(resolvedPublishers, publishersConfigured);
    }

}
