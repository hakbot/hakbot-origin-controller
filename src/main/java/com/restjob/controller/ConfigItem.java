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
package com.restjob.controller;

/**
 * This class provides a statically typed way to specify the named pairs
 * in a property file. All names which have a corresponding value in the
 * property file should be added to the enum.
 * Refer to {@link com.restjob.controller.Config#getProperty(ConfigItem) Config.getProperty}
 */
public enum	ConfigItem {

    APPLICATION_NAME("application.name"),
    APPLICATION_VERSION("application.version"),

    QUEUE_CHECK_INTERVAL("queue.check.interval"),
    JOB_CLEANUP_INTERVAL("job.cleanup.interval"),
    MAX_JOB_SIZE("max.job.size"),
    MAX_QUEUE_SIZE("max.queue.size"),

    PROVIDERS_ENALBED("providers.enabled"),

    TEMP_DIR("temp.dir");

    String propertyName;
    private ConfigItem(String item) {
        this.propertyName = item;
    }

}
