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
package io.hakbot.controller.persistence;

import io.hakbot.controller.Config;
import io.hakbot.controller.ConfigItem;
import io.hakbot.controller.model.ApiKey;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.LdapUser;
import io.hakbot.controller.model.SystemAccount;
import io.hakbot.controller.model.Team;
import io.hakbot.controller.workers.State;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class QueryManager {

    private static final boolean ENFORCE_AUTHORIZATION = Config.getInstance().getPropertyAsBoolean(ConfigItem.ENFORCE_AUTHORIZATION);

    public enum OrderDirection {
        ASC, DESC
    }

    private PersistenceManager getPersistenceManager() {
        return LocalPersistenceManagerFactory.createPersistenceManager();
    }

    @SuppressWarnings("unchecked")
    public List<Job> getJobs(OrderDirection order, Job.FetchGroup fetchGroup, Principal principal) {
        PersistenceManager pm = getPersistenceManager();
        pm.getFetchPlan().addGroup(fetchGroup.getName());
        Query query = pm.newQuery(Job.class);
        query.setOrdering("created " + order.name());
        List<Job> result = (List<Job>) query.execute();
        List<Job> permissible = getPermissible(result, principal);
        pm.close();
        return permissible;
    }

    @SuppressWarnings("unchecked")
    public List<Job> getJobs(State state, OrderDirection order, Job.FetchGroup fetchGroup, Principal principal) {
        PersistenceManager pm = getPersistenceManager();
        pm.getFetchPlan().addGroup(fetchGroup.getName());
        Query query = pm.newQuery(Job.class, "state == :state");
        query.setOrdering("created " + order.name());
        List<Job> result = (List<Job>)query.execute(state.getValue());
        List<Job> permissible = getPermissible(result, principal);
        pm.close();
        return permissible;
    }

    @SuppressWarnings("unchecked")
    public Job getJob(String uuid, Job.FetchGroup fetchGroup, Principal principal) {
        PersistenceManager pm = getPersistenceManager();
        pm.getFetchPlan().addGroup(fetchGroup.getName());
        Query query = pm.newQuery(Job.class, "uuid == :uuid");
        List<Job> result = (List<Job>)query.execute(uuid);
        List<Job> permissible = getPermissible(result, principal);
        pm.close();
        return permissible.size() == 0 ? null : permissible.get(0);
    }

    public Job createJob(Job transientJob) {
        PersistenceManager pm = getPersistenceManager();
        pm.currentTransaction().begin();
        Job job = new Job();
        job.setName(transientJob.getName());
        job.setProvider(transientJob.getProvider());
        job.setPublisher(transientJob.getPublisher());
        job.setProviderPayload(transientJob.getProviderPayload());
        job.setPublisherPayload(transientJob.getPublisherPayload());
        job.setCreated(new Date());
        job.setState(State.CREATED);
        job.setStartedByApiKeyId(transientJob.getStartedByApiKeyId());
        job.setUuid(UUID.randomUUID().toString());
        pm.makePersistent(job);
        pm.currentTransaction().commit();
        job = pm.getObjectById(Job.class, job.getId());
        pm.close();
        return job;
    }

    public void deleteAllJobs(Principal principal) {
        PersistenceManager pm = LocalPersistenceManagerFactory.createPersistenceManager();
        Query query = pm.newQuery(Job.class);
        List<Job> result = (List<Job>) query.execute();
        List<Job> permissible = getPermissible(result, principal);
        pm.currentTransaction().begin();
        query.deletePersistentAll(permissible);
        pm.currentTransaction().commit();
        pm.evictAll();
        pm.close();
    }

    public void deleteJob(String uuid, Principal principal) {
        PersistenceManager pm = LocalPersistenceManagerFactory.createPersistenceManager();
        Query query = pm.newQuery(Job.class, "uuid == :uuid");
        List<Job> result = (List<Job>) query.execute(uuid);
        List<Job> permissible = getPermissible(result, principal);
        pm.currentTransaction().begin();
        query.deletePersistentAll(permissible);
        pm.currentTransaction().commit();
        pm.evictAll();
        pm.close();
    }

    public void deleteJobs(State state, Principal principal) {
        PersistenceManager pm = LocalPersistenceManagerFactory.createPersistenceManager();
        Query query = pm.newQuery(Job.class, "state == :state");
        List<Job> result = (List<Job>) query.execute(state);
        List<Job> permissible = getPermissible(result, principal);
        pm.currentTransaction().begin();
        query.deletePersistentAll(permissible);
        pm.currentTransaction().commit();
        pm.evictAll();
        pm.close();
    }

    @SuppressWarnings("unchecked")
    public ApiKey getApiKey(String key) {
        PersistenceManager pm = getPersistenceManager();
        Query query = pm.newQuery(ApiKey.class, "key == :key");
        List<ApiKey> result = (List<ApiKey>)query.execute (key);
        pm.close();
        return result.size() == 0 ? null : result.get(0);
    }

    @SuppressWarnings("unchecked")
    public LdapUser getLdapUser(String username) {
        PersistenceManager pm = getPersistenceManager();
        Query query = pm.newQuery(LdapUser.class, "username == :username");
        List<LdapUser> result = (List<LdapUser>)query.execute(username);
        pm.close();
        return result.size() == 0 ? null : result.get(0);
    }

    private List<Job> getPermissible(List<Job> result, Principal principal) {
        List<Job> permissible = new ArrayList<>();
        for (Job job: result) {
            if (hasPermission(job, principal)) {
                permissible.add(job);
            }
        }
        return permissible;
    }

    public boolean hasPermission(Job job, Principal principal) {
        if (!ENFORCE_AUTHORIZATION) {
            return true;
        }
        if (principal instanceof ApiKey) {
            return hasPermission(job, (ApiKey)principal);
        } else if (principal instanceof LdapUser) {
            return hasPermission(job, (LdapUser) principal);
        } else if (principal instanceof SystemAccount) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hasPermission(Job job, ApiKey apiKey) {
        //todo: check apikey team permission - future enhancement
        return job.getStartedByApiKeyId() == apiKey.getId();
    }

    @SuppressWarnings("unchecked")
    private boolean hasPermission(Job job, LdapUser ldapUser) {
        PersistenceManager pm = getPersistenceManager();
        ApiKey apiKey = pm.getObjectById(ApiKey.class, job.getStartedByApiKeyId());
        Query query = pm.newQuery(Team.class);
        query.setFilter("(ldapUsers.contains(ldapUser) && apiKeys.contains(apiKey)) || (ldapUsers.contains(ldapUser) && hakmaster == true)");
        List<Team> teams = (List<Team>)query.execute(ldapUser);
        pm.close();
        return teams.size() > 0;
    }

}
