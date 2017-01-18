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

import io.hakbot.controller.model.LdapUser;
import io.hakbot.controller.persistence.QueryManager;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import java.security.Principal;

abstract class BaseResource {

    @Context
    ContainerRequestContext requestContext;

    /**
     * Returns the principal for who initiated the request.
     * @see {@link io.hakbot.controller.model.ApiKey}
     * @see {@link io.hakbot.controller.model.LdapUser}
     */
    protected Principal getPrincipal() {
        Object principal = requestContext.getProperty("Principal");
        if (principal != null) {
            return (Principal)principal;
        } else {
            return null;
        }
    }

    protected boolean isLdapUser() {
        return (getPrincipal() instanceof LdapUser);
    }

    protected boolean isHakmaster() {
        boolean isHakMaster;
        Principal principal = getPrincipal();
        if (principal == null) {
            // authentication was already required (if enabled)
            isHakMaster = true;
        } else {
            try (QueryManager qm = new QueryManager()) {
                isHakMaster = qm.isHakMaster((LdapUser) principal);
            }
        }
        return isHakMaster;
    }

}
