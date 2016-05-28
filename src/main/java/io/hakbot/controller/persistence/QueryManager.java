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

import io.hakbot.controller.listener.LocalPersistenceManagerFactory;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.workers.State;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class QueryManager {

    public enum OrderDirection {
        ASC, DESC
    }

    private PersistenceManager getPersistenceManager() {
        return LocalPersistenceManagerFactory.createPersistenceManager();
    }

    @SuppressWarnings("unchecked")
    public List<Job> getJobs(OrderDirection order) {
        PersistenceManager pm = getPersistenceManager();
        Query query = pm.newQuery(Job.class);
        query.setOrdering("created " + order.name());
        List<Job> result = (List<Job>) query.execute();
        pm.close();
        return result;
    }

    @SuppressWarnings("unchecked")
    public List<Job> getJobs(State state, OrderDirection order) {
        PersistenceManager pm = getPersistenceManager();
        Query query = pm.newQuery(Job.class, "state == :state");
        query.setOrdering("created " + order.name());
        List<Job> result = (List<Job>)query.execute (state.getValue());
        pm.close();
        return result;
    }

    @SuppressWarnings("unchecked")
    public Job getJob(String uuid) {
        PersistenceManager pm = getPersistenceManager();
        Query query = pm.newQuery(Job.class, "uuid == :uuid");
        List<Job> result = (List<Job>)query.execute (uuid);
        pm.close();
        return result.size() == 0 ? null : result.get(0);
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
        job.setUuid(UUID.randomUUID().toString());
        pm.makePersistent(job);
        pm.currentTransaction().commit();
        job = pm.getObjectById(Job.class, job.getId());
        pm.close();
        return job;
    }

    public void deleteAllJobs() {
        PersistenceManager pm = LocalPersistenceManagerFactory.createPersistenceManager();
        pm.currentTransaction().begin();
        Query query = pm.newQuery(Job.class);
        query.deletePersistentAll();
        pm.currentTransaction().commit();
        pm.evictAll();
        pm.close();
    }

    public void deleteJob(String uuid) {
        PersistenceManager pm = LocalPersistenceManagerFactory.createPersistenceManager();
        pm.currentTransaction().begin();
        Query query = pm.newQuery(Job.class, "uuid == :uuid");
        query.deletePersistentAll(uuid);
        pm.currentTransaction().commit();
        pm.evictAll();
        pm.close();
    }

    public void deleteJobs(State state) {
        PersistenceManager pm = LocalPersistenceManagerFactory.createPersistenceManager();
        pm.currentTransaction().begin();
        Query query = pm.newQuery(Job.class, "state == :state");
        query.deletePersistentAll(state.getValue());
        pm.currentTransaction().commit();
        pm.evictAll();
        pm.close();
    }

}

