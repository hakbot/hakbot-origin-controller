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
import org.glassfish.jersey.servlet.ServletContainer;

import javax.servlet.ServletException;
import java.io.InputStream;

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
    static InputStream inputStream;

    /**
     * Overrides the servlet init method and loads sets the InputStream necessary
     * to load application.properties and creates the first instance of
     * {@link com.restjob.controller.workers.JobManager JobManager} thus starting
     * its scheduled tasks.
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        logger.info("Starting RESTjob Controller");
        super.init();
        inputStream = getServletContext().getResourceAsStream("/WEB-INF/classes/" + Config.propFile);
        JobManager.getInstance(); // starts the JobManager
    }

    /**
     * Overrides the servlet destroy method and shuts down the servlet
     */
    @Override
    public void destroy() {
        logger.info("Stopping RESTjob Controller");
        super.destroy();
    }

}
