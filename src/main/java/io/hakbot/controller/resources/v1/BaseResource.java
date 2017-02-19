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

import alpine.model.LdapUser;
import alpine.resources.AlpineResource;
import io.hakbot.controller.persistence.QueryManager;
import java.security.Principal;

abstract class BaseResource extends AlpineResource {

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
