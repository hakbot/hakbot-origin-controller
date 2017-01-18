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

import io.hakbot.controller.auth.AuthenticationNotRequired;
import io.hakbot.controller.auth.JsonWebToken;
import io.hakbot.controller.auth.KeyManager;
import io.hakbot.controller.auth.LdapAuthenticator;
import io.hakbot.controller.model.LdapUser;
import io.hakbot.controller.persistence.QueryManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/v1/user")
@Api(value = "user")
public class UserResource extends BaseResource {

    @POST
    @Path("login")
    @Produces(MediaType.TEXT_PLAIN)
    @ApiOperation(
            value = "Assert login credentials",
            notes = "Upon a successful login, a JSON Web Token will be returned in the response body. This functionality requires authentication to be enabled on Origin Controller.",
            response = String.class
    )
    @AuthenticationNotRequired
    public Response validateCredentials(@FormParam("username") String username, @FormParam("password") String password) {

        LdapAuthenticator ldapAuth = new LdapAuthenticator();
        boolean isValid = ldapAuth.validateCredentials(username, password);
        if (!isValid) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }

        try (QueryManager qm = new QueryManager()) {
            LdapUser ldapUser = qm.getLdapUser(username);
            KeyManager km = KeyManager.getInstance();
            JsonWebToken jwt = new JsonWebToken(km.getSecretKey());
            String token = jwt.createToken(ldapUser);
            return Response.ok(token).build();
        }
    }

    @GET
    @Path("/hakmaster")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Queries if the user is a hakmaster",
            notes = "Determines if the user making the request is a hakmaster (aka: superuser or administrator of Origin Controller)",
            response = Boolean.class,
            authorizations = {
                    @Authorization(value="X-Api-Key")
            }
    )
    public Response getIsHakmaster() {
        return Response.ok(isHakmaster()).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Returns a list of all users",
            notes = "Requires hakmaster permission.",
            response = LdapUser.class,
            responseContainer = "List"
    )
    public Response getUsers() {
        if (!isHakmaster()) {
            Response.status(Response.Status.UNAUTHORIZED);
        }
        try (QueryManager qm = new QueryManager()) {
            List<LdapUser> users = qm.getLdapUsers();
            return Response.ok(users).build();
        }
    }

    @GET
    @Path("/self")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(
            value = "Returns information about the current logged in user.",
            response = LdapUser.class
    )
    public Response getSelf() {
        try (QueryManager qm = new QueryManager()) {
            LdapUser user = qm.getLdapUser(getPrincipal().getName());
            return Response.ok(user).build();
        }
    }

}
