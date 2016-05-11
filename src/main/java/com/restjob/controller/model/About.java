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
package com.restjob.controller.model;

import com.restjob.controller.Config;
import com.restjob.controller.ConfigItem;
import java.io.Serializable;

public class About implements Serializable {

    private static final long serialVersionUID = -4017608525127778644L;

    private static final String application = Config.getInstance().getProperty(ConfigItem.APPLICATION_NAME);
    private static final String version = Config.getInstance().getProperty(ConfigItem.APPLICATION_VERSION);


    public String getApplication() {
        return application;
    }

    public String getVersion() {
        return version;
    }

}
