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

import io.hakbot.controller.plugin.PluginMetadata;
import io.hakbot.controller.workers.ExpectedClassResolver;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/providers")
@Api(value = "providers", authorizations = {
        @Authorization(value="X-Api-Key")
})
public class ProvidersResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Returns all providers",
            notes = "Returns an array of all enabled providers. Providers not enabled are omitted.",
            response = PluginMetadata.class,
            responseContainer = "List"
    )
    public Response getAll() {
        List<PluginMetadata> list = new ArrayList<>();
        ExpectedClassResolver resolver = new ExpectedClassResolver();
        List<Class> classes = resolver.getResolvedProviders();
        for (Class clazz: classes) {
            PluginMetadata meta = new PluginMetadata(clazz);
            list.add(meta);
        }
        return Response.ok(list).build();
    }

}
