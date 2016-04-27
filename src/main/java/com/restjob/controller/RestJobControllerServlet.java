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

import com.restjob.controller.logging.Logger;
import com.restjob.controller.workers.JobManager;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.Info;
import io.swagger.models.License;
import io.swagger.models.Swagger;
import io.swagger.models.auth.ApiKeyAuthDefinition;
import io.swagger.models.auth.In;
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * The RestJobControllerServlet is the main servlet which extends
 * the Jersey ServletContainer. It is responsible for setting up
 * the runtime environment by initializing the
 * {@link com.restjob.controller.workers.JobManager JobManager}
 * and setting the path to properties files used for
 * {@link com.restjob.controller.Config Config}(uration).
 */
public final class RestJobControllerServlet extends ServletContainer {

    private static final long serialVersionUID = -133386507668410112L;
    private static final Logger logger = Logger.getLogger(RestJobControllerServlet.class);

    /**
     * Overrides the servlet init method and loads sets the InputStream necessary
     * to load application.properties and creates the first instance of
     * {@link com.restjob.controller.workers.JobManager JobManager} thus starting
     * its scheduled tasks.
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.info("Starting " + Config.getInstance().getProperty(ConfigItem.APPLICATION_NAME));
        super.init(config);

        Info info = new Info()
                .title(Config.getInstance().getProperty(ConfigItem.APPLICATION_NAME) + " API")
                .version(Config.getInstance().getProperty(ConfigItem.APPLICATION_VERSION))
                .license(new License()
                        .name("GPL v3.0")
                        .url("http://www.gnu.org/licenses/gpl-3.0.txt"));

        Swagger swagger = new Swagger().info(info);
        swagger.securityDefinition("api_key", new ApiKeyAuthDefinition("api_key", In.HEADER));
        new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);

        JobManager.getInstance(); // starts the JobManager
    }

    /**
     * Overrides the servlet destroy method and shuts down the servlet
     */
    @Override
    public void destroy() {
        logger.info("Stopping " + Config.getInstance().getProperty(ConfigItem.APPLICATION_NAME));
        super.destroy();
    }

}
