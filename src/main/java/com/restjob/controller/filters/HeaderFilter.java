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
package com.restjob.controller.filters;

import com.restjob.controller.Config;
import com.restjob.controller.ConfigItem;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;

@Priority(Priorities.HEADER_DECORATOR)
public class HeaderFilter implements ContainerResponseFilter {

    private String appName;
    private String appVersion;

    private void init() {
        if (appName == null) {
            appName = Config.getInstance().getProperty(ConfigItem.APPLICATION_NAME);
        }
        if (appVersion == null) {
            appVersion = Config.getInstance().getProperty(ConfigItem.APPLICATION_VERSION);
        }
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        init();
        responseContext.getHeaders().add("X-Powered-By", appName + " v" + appVersion);
        responseContext.getHeaders().add(HttpHeaders.CACHE_CONTROL, "private, max-age=0, must-revalidate, no-cache");
    }

}