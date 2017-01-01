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
package io.hakbot.controller.auth;

import io.hakbot.controller.model.ApiKey;
import io.hakbot.controller.persistence.QueryManager;
import org.glassfish.jersey.server.ContainerRequest;
import javax.naming.AuthenticationException;
import java.security.Principal;

public class ApiKeyAuthService implements AuthService {

    private String assertedApiKey = null;

    public ApiKeyAuthService(ContainerRequest request) {
        this.assertedApiKey = request.getHeaderString("X-Api-Key");
    }

    public boolean isSpecified() {
        return (assertedApiKey != null);
    }

    public Principal authenticate() throws AuthenticationException {
        QueryManager qm = new QueryManager();
        ApiKey apiKey =  qm.getApiKey(assertedApiKey);
        qm.close();
        if (apiKey == null) {
            throw new AuthenticationException();
        } else {
            return apiKey;
        }
    }

}
