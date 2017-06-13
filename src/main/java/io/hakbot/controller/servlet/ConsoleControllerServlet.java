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
package io.hakbot.controller.servlet;

import alpine.logging.Logger;
import alpine.util.UuidUtil;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.SystemAccount;
import io.hakbot.controller.persistence.QueryManager;
import io.hakbot.controller.workers.ExpectedClassResolver;
import io.hakbot.controller.workers.ExpectedClassResolverException;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ConsoleControllerServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(ConsoleControllerServlet.class);


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doRequest(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doRequest(request, response);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doRequest(request, response);
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        doRequest(request, response);
    }

    private void doRequest(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        final String pathInfo = request.getPathInfo();
        final String uuid;

        // Check to make sure path info was specified
        if (StringUtils.isEmpty(pathInfo)) {
            response.sendError(400);
            return;
        } else {
            // Path info was specified so strip off the leading / character
            uuid = StringUtils.stripStart(pathInfo, "/");
        }

        // Check to make sure the uuid is a valid format
        if (!UuidUtil.isValidUUID(uuid)) {
            response.sendError(400);
            return;
        }

        final QueryManager qm = new QueryManager();
        final Job job = qm.getJob(uuid, new SystemAccount());
        qm.close();
        if (job == null) {
            response.sendError(404);
            return;
        }

        final ExpectedClassResolver resolver = new ExpectedClassResolver();
        try {
            final Class pluginClass = resolver.resolveProvider(job);
            request.setAttribute("job", job);
            final String pluginPage = "/WEB-INF/plugins/" + pluginClass.getName() + "/index.jsp?uuid=" + uuid;
            response.setContentType("text/html;charset=UTF-8");
            request.getRequestDispatcher(pluginPage).include(request, response);
            return;
        } catch (ClassNotFoundException | ExpectedClassResolverException e) {
            LOGGER.error(e.getMessage());
        }

        response.sendError(404);
    }

}
