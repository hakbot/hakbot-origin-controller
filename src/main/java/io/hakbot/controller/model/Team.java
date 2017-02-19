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
package io.hakbot.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.FetchGroups;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
@FetchGroups({
        @FetchGroup(name="all", members={
                @Persistent(name="uuid"),
                @Persistent(name="name"),
                @Persistent(name="hakmaster"),
                @Persistent(name="apiKeys"),
                @Persistent(name="ldapUsers"),
                @Persistent(name="managedUsers")
        })
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Team extends alpine.model.Team {

    private static final long serialVersionUID = 6173877472831531299L;

    public enum FetchGroup {
        ALL("all");

        private String fetchGroupName;
        FetchGroup(String fetchGroupName) {
            this.fetchGroupName = fetchGroupName;
        }

        public String getName() {
            return fetchGroupName;
        }
    }

    @Persistent
    @Column(name="HAKMASTER")
    private boolean hakmaster;

    public boolean isHakmaster() {
        return hakmaster;
    }

    public void setHakmaster(boolean hakmaster) {
        this.hakmaster = hakmaster;
    }

}
