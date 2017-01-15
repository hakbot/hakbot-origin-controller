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
import io.hakbot.controller.model.ApiKey;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.JobArtifact;
import io.hakbot.controller.model.JobProperty;
import io.hakbot.controller.model.LdapUser;
import io.hakbot.controller.model.SystemAccount;
import io.hakbot.controller.model.Team;
import io.hakbot.controller.workers.State;
import io.hakbot.util.UuidUtil;
import org.apache.commons.lang3.StringUtils;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class QueryManager {

    private static final boolean ENFORCE_AUTHORIZATION = Config.getInstance().getPropertyAsBoolean(Config.Key.ENFORCE_AUTHORIZATION);

    public enum OrderDirection {
        ASC, DESC
    }

    private PersistenceManager pm = LocalPersistenceManagerFactory.createPersistenceManager();

    @SuppressWarnings("unchecked")
    public List<Job> getJobs(OrderDirection order, Principal principal) {
        Query query = pm.newQuery(Job.class);
        query.setOrdering("created " + order.name());
        List<Job> result = (List<Job>) query.execute();
        return getPermissible(result, principal);
    }

    @SuppressWarnings("unchecked")
    public List<Job> getJobs(State state, OrderDirection order, Principal principal) {
        Query query = pm.newQuery(Job.class, "state == :state");
        query.setOrdering("created " + order.name());
        List<Job> result = (List<Job>)query.execute(state.getValue());
        return getPermissible(result, principal);
    }

    @SuppressWarnings("unchecked")
    public List<Job> getJobs(String pluginClass, State state, OrderDirection order, Principal principal) {
        Query query = pm.newQuery(Job.class, "providerClass == :pluginClass && state == :state");
        query.setOrdering("created " + order.name());
        List<Job> result = (List<Job>)query.execute(pluginClass, state.getValue());
        return getPermissible(result, principal);
    }

    @SuppressWarnings("unchecked")
    public Job getJob(String uuid, Principal principal) {
        Query query = pm.newQuery(Job.class, "uuid == :uuid");
        List<Job> result = (List<Job>)query.execute(uuid);
        List<Job> permissible = getPermissible(result, principal);
        return permissible.size() == 0 ? null : permissible.get(0);
    }

    public Job createJob(String name, String provider, String providerPayload, String publisher, String publisherPayload, ApiKey apiKey) {
        pm.currentTransaction().begin();
        Job job = new Job();
        job.setName(name);
        job.setProvider(provider);
        job.setPublisher(publisher);
        job.setCreated(new Date());
        job.setState(State.CREATED);
        if (apiKey != null) {
            job.setStartedByApiKeyId(apiKey.getId());
        }
        job.setUuid(UUID.randomUUID().toString());
        pm.makePersistent(job);
        pm.currentTransaction().commit();
        if (StringUtils.isNotBlank(providerPayload)) {
            setJobArtifact(job, JobArtifact.Type.PROVIDER_PAYLOAD, JobArtifact.MimeType.JSON.value(), providerPayload.getBytes(), null, null);
        }
        if (StringUtils.isNotBlank(publisherPayload)) {
            setJobArtifact(job, JobArtifact.Type.PUBLISHER_PAYLOAD, JobArtifact.MimeType.JSON.value(), publisherPayload.getBytes(), null, null);
        }
        return pm.getObjectById(Job.class, job.getId());
    }

    public Job updateJob(Job transientJob) {
        Job job = getJob(transientJob.getUuid(), new SystemAccount());
        pm.currentTransaction().begin();
        job.setCompleted(transientJob.getCompleted());
        job.setCreated(transientJob.getCreated());
        job.setMessage(transientJob.getMessage());
        job.setName(transientJob.getName());
        job.setProvider(transientJob.getProvider());
        job.setPublisher(transientJob.getPublisher());
        job.setStarted(transientJob.getStarted());
        job.setStartedByApiKeyId(transientJob.getStartedByApiKeyId());
        job.setState(transientJob.getState());
        pm.currentTransaction().commit();
        return pm.getObjectById(Job.class, job.getId());
    }

    public long getUnprocessedJobCount() {
        Query query = pm.newQuery(Job.class, "state == :created || state == :unavailable || state == :inQueue || state == :inProgress");
        query.setResult("count(id)");
        return (Long)query.executeWithArray(State.CREATED.getValue(), State.UNAVAILABLE.getValue(), State.IN_QUEUE.getValue(), State.IN_PROGRESS.getValue());
    }

    @SuppressWarnings("unchecked")
    public List<JobProperty> getJobProperties(Job job) {
        Query query = pm.newQuery(JobProperty.class, "jobid == :jobid");
        return (List<JobProperty>)query.execute(job.getId());
    }

    public JobProperty getJobProperty(Job job, String key) {
        List<JobProperty> properties = getJobProperties(job);
        for (JobProperty property: properties) {
            if (property.getKey().equals(key)) {
                return property;
            }
        }
        return null;
    }

    public JobProperty setJobProperty(Job job, String key, Object value) {
        pm.currentTransaction().begin();
        JobProperty property = getJobProperty(job, key);
        if (property == null) {
            property = new JobProperty(job, key, value.toString());
            pm.makePersistent(property);
        } else {
            property.setJobId(job.getId());
            property.setKey(key);
            property.setValue(value.toString());
        }
        pm.currentTransaction().commit();
        property = pm.getObjectById(JobProperty.class, property.getId());
        return property;
    }

    @SuppressWarnings("unchecked")
    public List<JobArtifact> getJobArtifacts(Job job) {
        Query query = pm.newQuery(JobArtifact.class, "jobid == :jobid");
        return (List<JobArtifact>)query.execute(job.getId());
    }

    public JobArtifact getJobArtifact(Job job, JobArtifact.Type type) {
        List<JobArtifact> artifacts = getJobArtifacts(job);
        for (JobArtifact artifact: artifacts) {
            if (artifact.getType().equals(type.name())) {
                return artifact;
            }
        }
        return null;
    }

    public JobArtifact setJobArtifact(@Nonnull Job job, @Nonnull JobArtifact.Type type, @Nonnull String mimeType, @Nonnull byte[] contents, @Nullable String uuid, @Nullable String filename) {
        pm.currentTransaction().begin();
        JobArtifact artifact = null;
        boolean isNewObject = false;
        if (uuid != null) {
            isNewObject = false;
            artifact = getObjectByUuid(JobArtifact.class, uuid);
        }
        if (artifact == null) {
            isNewObject = true;
            artifact = new JobArtifact();
            artifact.setUuid(UUID.randomUUID().toString());
        }
        artifact.setJobid(job.getId());
        artifact.setType(type);
        artifact.setMimetype(mimeType);
        artifact.setContents(contents);
        artifact.setFilename(filename);
        if (isNewObject) {
            pm.makePersistent(artifact);
        }
        pm.currentTransaction().commit();
        return pm.getObjectById(JobArtifact.class, artifact.getId());
    }

    @SuppressWarnings("unchecked")
    public void deleteAllJobs(Principal principal) {
        Query query = pm.newQuery(Job.class);
        List<Job> result = (List<Job>) query.execute();
        List<Job> permissible = getPermissible(result, principal);
        pm.currentTransaction().begin();
        for (Job job: permissible) {
            List<JobProperty> properties = getJobProperties(job);
            pm.deletePersistentAll(properties);
        }
        pm.deletePersistentAll(permissible);
        pm.currentTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public void deleteJob(String uuid, Principal principal) {
        Query query = pm.newQuery(Job.class, "uuid == :uuid");
        List<Job> result = (List<Job>) query.execute(uuid);
        List<Job> permissible = getPermissible(result, principal);
        pm.currentTransaction().begin();
        for (Job job: permissible) {
            List<JobProperty> properties = getJobProperties(job);
            pm.deletePersistentAll(properties);
        }
        pm.deletePersistentAll(permissible);
        pm.currentTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public void deleteJobs(State state, Principal principal) {
        Query query = pm.newQuery(Job.class, "state == :state");
        List<Job> result = (List<Job>) query.execute(state.getValue());
        List<Job> permissible = getPermissible(result, principal);
        pm.currentTransaction().begin();
        for (Job job: permissible) {
            List<JobProperty> properties = getJobProperties(job);
            pm.deletePersistentAll(properties);
        }
        pm.deletePersistentAll(permissible);
        pm.currentTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public ApiKey getApiKey(String key) {
        Query query = pm.newQuery(ApiKey.class, "key == :key");
        List<ApiKey> result = (List<ApiKey>)query.execute (key);
        return result.size() == 0 ? null : result.get(0);
    }

    public ApiKey regenerateApiKey(ApiKey apiKey) {
        pm.currentTransaction().begin();
        apiKey.setKey(UuidUtil.stripHyphens(UUID.randomUUID().toString()));
        pm.currentTransaction().commit();
        apiKey = pm.getObjectById(ApiKey.class, apiKey.getId());
        return apiKey;
    }

    public ApiKey createApiKey(Team team) {
        Set<Team> teams = new HashSet<>();
        teams.add(team);
        pm.currentTransaction().begin();
        ApiKey apiKey = new ApiKey();
        apiKey.setKey(UuidUtil.stripHyphens(UUID.randomUUID().toString()));
        apiKey.setTeams(teams);
        pm.makePersistent(apiKey);
        pm.currentTransaction().commit();
        return pm.getObjectById(ApiKey.class, apiKey.getId());
    }

    @SuppressWarnings("unchecked")
    public LdapUser getLdapUser(String username) {
        Query query = pm.newQuery(LdapUser.class, "username == :username");
        List<LdapUser> result = (List<LdapUser>)query.execute(username);
        return result.size() == 0 ? null : result.get(0);
    }

    @SuppressWarnings("unchecked")
    public List<LdapUser> getLdapUsers() {
        Query query = pm.newQuery(LdapUser.class);
        query.setOrdering("username " + OrderDirection.ASC.name());
        return (List<LdapUser>)query.execute();
    }

    @SuppressWarnings("unchecked")
    public List<Team> getTeams() {
        pm.getFetchPlan().addGroup(Team.FetchGroup.ALL.getName());
        Query query = pm.newQuery(Team.class);
        query.setOrdering("name " + OrderDirection.ASC.name());
        return (List<Team>)query.execute();
    }

    public Team updateTeam(Team transientTeam) {
        Team team = getObjectByUuid(Team.class, transientTeam.getUuid());
        pm.currentTransaction().begin();
        team.setName(transientTeam.getName());
        team.setHakmaster(transientTeam.isHakmaster());
        pm.currentTransaction().commit();
        return pm.getObjectById(Team.class, team.getId());
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
        ApiKey apiKey = pm.getObjectById(ApiKey.class, job.getStartedByApiKeyId());
        ArrayList<Long> list = new ArrayList<>();
        for (Team team: apiKey.getTeams()) {
            list.add(team.getId());
        }
        for (Team team: ldapUser.getTeams()) {
            if (team.isHakmaster()) {
                return true;
            }
            if (list.contains(team.getId())) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public boolean isHakMaster(LdapUser ldapUser) {
        for (Team team: ldapUser.getTeams()) {
            if (team.isHakmaster()) {
                return true;
            }
        }
        return false;
    }

    public void delete(Object... objects) {
        pm.currentTransaction().begin();
        pm.deletePersistentAll(objects);
        pm.currentTransaction().commit();
    }

    public <T>T getObjectById (Class<T> clazz, Object key) {
        return pm.getObjectById(clazz, key);
    }

    @SuppressWarnings("unchecked")
    public <T>T getObjectByUuid(Class<T> clazz, String uuid) {
        Query query = pm.newQuery(clazz, "uuid == :uuid");
        List<T> result = (List<T>)query.execute(uuid);
        return result.size() == 0 ? null : result.get(0);
    }

    public void close() {
        pm.close();
    }

    protected void finalize() throws Throwable {
        close();
        super.finalize();
    }
}
