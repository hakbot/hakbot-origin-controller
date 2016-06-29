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
package io.hakbot.controller.resources;

import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.persistence.QueryManager;
import io.hakbot.controller.plugin.Console;
import io.hakbot.controller.plugin.ConsoleIdentifier;
import io.hakbot.controller.workers.ExpectedClassResolver;
import io.hakbot.controller.workers.ExpectedClassResolverException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@Path("/console")
public class ConsoleResource extends BaseResource {

    private static final Logger logger = Logger.getLogger(ConsoleResource.class);

    @GET
    @Path("job/{job}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getConsole(@PathParam("job") String jobUuid) {

        // Query on the specified job and determine if principal has permissions
        QueryManager qm = new QueryManager();
        Job job = qm.getJob(jobUuid, Job.FetchGroup.ALL, getPrincipal());
        if (job != null) {
            // Principal has access to job
            try {
                ExpectedClassResolver resolver = new ExpectedClassResolver();
                Class pluginClass = resolver.resolveProvider(job);
                if (ConsoleIdentifier.class.isAssignableFrom(pluginClass)) {
                    Map queryParams = requestContext.getUriInfo().getQueryParameters();

                    // Lookup the corresponding console class from the plugin instance
                    ConsoleIdentifier ci = (ConsoleIdentifier) pluginClass.newInstance();
                    Class consoleClass = ci.getConsoleClass();

                    // Execute the console sending the job and query parameters (if any) to it
                    Console console = (Console) consoleClass.newInstance();
                    Object response = console.console(job, queryParams);
                    return Response.ok(response).build();
                }
            } catch (ClassNotFoundException | ExpectedClassResolverException | InstantiationException | IllegalAccessException e) {
                logger.error(e.getMessage());
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
