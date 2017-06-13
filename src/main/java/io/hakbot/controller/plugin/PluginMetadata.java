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

import alpine.logging.Logger;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PluginMetadata {

    // Setup LOGGER
    private static final Logger LOGGER = Logger.getLogger(PluginMetadata.class);

    private Plugin plugin;

    public PluginMetadata(Class clazz) {
        init(clazz);
    }

    private void init(Class clazz) {
        try {
            @SuppressWarnings("unchecked")
            final Constructor<?> constructor = clazz.getConstructor();
            this.plugin = (Plugin) constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            LOGGER.error(e.getMessage());
        }
    }

    public String getName() {
        return plugin.getName();
    }

    public String getDescription() {
        return plugin.getDescription();
    }

    @JsonProperty(value = "class")
    public String getClassname() {
        return plugin.getClass().getCanonicalName();
    }

    @JsonProperty(value = "console")
    public boolean hasConsole() {
        return ConsoleIdentifier.class.isAssignableFrom(plugin.getClass());
    }

}
