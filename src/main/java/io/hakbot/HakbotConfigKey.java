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
package io.hakbot;

import alpine.Config;

public enum HakbotConfigKey implements Config.Key {

    MAX_QUEUE_SIZE            ("hakbot.max.queue.size",             100),
    QUEUE_CHECK_INTERVAL      ("hakbot.queue.check.interval",       30),
    JOB_PRUNE_CHECK_INTERVAL  ("hakbot.job.prune.check.interval",   1),
    JOB_PRUNE_INTERVAL        ("hakbot.job.prune.interval",         14),
    PROVIDERS_ENABLED         ("hakbot.providers.enabled",          null),
    PUBLISHERS_ENABLED        ("hakbot.publishers.enabled",         null);


    private String propertyName;
    private Object defaultValue;
    HakbotConfigKey(String item, Object defaultValue) {
        this.propertyName = item;
        this.defaultValue = defaultValue;
    }

    public String getPropertyName() {
        return propertyName;
    }

    public Object getDefaultValue() {
        return defaultValue;
    }

}
