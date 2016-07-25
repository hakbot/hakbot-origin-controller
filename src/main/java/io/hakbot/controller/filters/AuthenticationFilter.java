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
package io.hakbot.controller.filters;

import io.hakbot.controller.auth.ApiKeyAuthService;
import io.hakbot.controller.auth.JwtAuthService;
import io.hakbot.controller.logging.Logger;
import org.glassfish.jersey.server.ContainerRequest;
import javax.annotation.Priority;
import javax.naming.AuthenticationException;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import java.security.Principal;

@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    // Setup logging
    private static final Logger logger = Logger.getLogger(AuthenticationFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) {
        if (requestContext instanceof ContainerRequest) {
            ContainerRequest request = (ContainerRequest) requestContext;

            // Bypass authentication for swagger
            if (request.getRequestUri().getPath().contains("/api/swagger")) {
                return;
            }

            Principal principal = null;

            ApiKeyAuthService apiKeyAuthService = new ApiKeyAuthService(request);
            if (apiKeyAuthService.isSpecified()) {
                try {
                    principal = apiKeyAuthService.authenticate();
                } catch (AuthenticationException e) {
                    logger.info("Invalid login attempt");
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                    return;
                }
            }

            JwtAuthService jwtAuthService = new JwtAuthService(request);
            if (jwtAuthService.isSpecified()) {
                try {
                    principal = jwtAuthService.authenticate();
                } catch (AuthenticationException e) {
                    logger.info("Invalid login attempt");
                    requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
                    return;
                }
            }

            if (principal == null) {
                requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
            } else {
                requestContext.setProperty("Principal", principal);
            }
        }
    }

}