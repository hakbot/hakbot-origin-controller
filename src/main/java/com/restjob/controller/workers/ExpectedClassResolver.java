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
package com.restjob.controller.workers;

import com.restjob.controller.Config;
import com.restjob.controller.ConfigItem;
import com.restjob.controller.logging.Logger;
import com.restjob.controller.model.Job;

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
     * Resolves the Class for the specified Job's provider. The provider needs to be whitelisted
     * in order to be resolved. If provider is not whitelisted, an ExpectedClassResolverException
     * is thrown.
     */
    public Class resolveClass(Job job) throws ClassNotFoundException, ExpectedClassResolverException {
        if (providersConfigured.contains(job.getProvider()) || publishersConfigured.contains(job.getPublisher())) {
            return Class.forName(job.getProvider(), false, this.getClass().getClassLoader());
        } else {
            throw new ExpectedClassResolverException();
        }
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
