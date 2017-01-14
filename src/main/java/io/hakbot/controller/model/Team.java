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

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.FetchGroup;
import javax.jdo.annotations.FetchGroups;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.Order;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;
import java.io.Serializable;
import java.util.List;

@PersistenceCapable
@FetchGroups({
        @FetchGroup(name="all", members={
                @Persistent(name="uuid"),
                @Persistent(name="name"),
                @Persistent(name="hakmaster"),
                @Persistent(name="apiKeys"),
                @Persistent(name="ldapUsers")})
})
public class Team implements Serializable {

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

    @PrimaryKey
    @Persistent(valueStrategy=IdGeneratorStrategy.INCREMENT)
    @JsonIgnore
    private long id;

    @Persistent
    @Unique(name="TEAM_UUID_IDX")
    @Column(name="UUID", jdbcType="VARCHAR", length=36, allowsNull="false")
    private String uuid;

    @Persistent
    @Column(name="NAME", jdbcType="VARCHAR", length=50, allowsNull="false")
    private String name;

    @Persistent
    @Column(name="HAKMASTER")
    private boolean hakmaster;

    @Persistent(mappedBy="teams")
    @Order(extensions=@Extension(vendorName="datanucleus", key="list-ordering", value="id ASC"))
    private List<ApiKey> apiKeys;

    @Persistent(mappedBy="teams")
    @Order(extensions=@Extension(vendorName="datanucleus", key="list-ordering", value="username ASC"))
    private List<LdapUser> ldapUsers;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHakmaster() {
        return hakmaster;
    }

    public void setHakmaster(boolean hakmaster) {
        this.hakmaster = hakmaster;
    }

    public List<ApiKey> getApiKeys() {
        return apiKeys;
    }

    public void setApiKeys(List<ApiKey> apiKeys) {
        this.apiKeys = apiKeys;
    }

    public List<LdapUser> getLdapUsers() {
        return ldapUsers;
    }

    public void setLdapUsers(List<LdapUser> ldapUsers) {
        this.ldapUsers = ldapUsers;
    }

}
