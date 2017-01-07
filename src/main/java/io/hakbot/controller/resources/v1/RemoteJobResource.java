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
package io.hakbot.controller.resources.v1;

import io.hakbot.controller.model.Job;
import io.hakbot.controller.persistence.QueryManager;
import io.hakbot.controller.workers.ExpectedClassResolver;
import io.hakbot.controller.workers.State;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.Authorization;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/v1/orbit")
@Api(value = "orbit", authorizations = {
        @Authorization(value="X-Api-Key")
})
public class RemoteJobResource extends BaseResource {

    @GET
    @Path("/pickup/{class}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Returns jobs by class name",
            notes = "Returns jobs by class name",
            response = Job.class
    )
    public Response getJobByClass(
            @ApiParam(value = "The name of the class", required = true)
            @PathParam("class") String classname) {

        ExpectedClassResolver resolver = new ExpectedClassResolver();
        if (!resolver.isClassAllowed(classname)) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        QueryManager qm = new QueryManager();
        List<Job> jobs = qm.getJobs(classname, State.CREATED, QueryManager.OrderDirection.DESC, getPrincipal());
        qm.close();
        if (jobs.size() > 0) {
            return Response.ok(jobs.get(0)).build();
        } else {
            return Response.ok().build();
        }

    }

}
