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

import io.hakbot.controller.listener.LocalEntityManagerFactory;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.workers.State;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class QueryManager {

    private EntityManager getEntityManager() {
        return LocalEntityManagerFactory.createEntityManager();
    }

    public List<Job> getJobs() {
        EntityManager em = getEntityManager();
        TypedQuery<Job> query = em.createNamedQuery("Job.getJobs", Job.class);
        List<Job> result = query.getResultList();
        em.close();
        return result;
    }

    public List<Job> getJobs(State state) {
        EntityManager em = getEntityManager();
        TypedQuery<Job> query = em.createNamedQuery("Job.getJobsByState", Job.class);
        query.setParameter("state", state.getValue());
        List<Job> result = query.getResultList();
        em.close();
        return result;
    }

    public Job getJob(String uuid) {
        EntityManager em = getEntityManager();
        TypedQuery<Job> query = em.createNamedQuery("Job.getJobByUuid", Job.class).setParameter("uuid", uuid);
        List<Job> result = query.getResultList();
        em.close();
        return result.size() == 0 ? null : result.get(0);
    }

    public Job createJob(Job transientJob) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Job job = new Job();
        job.setProvider(transientJob.getProvider());
        job.setPublisher(transientJob.getPublisher());
        job.setProviderPayload(transientJob.getProviderPayload());
        job.setPublisherPayload(transientJob.getPublisherPayload());
        em.persist(job);
        em.getTransaction().commit();
        em.close();
        return job;
    }

    public void deleteAllJobs() {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Job");
        query.executeUpdate();
        em.getTransaction().commit();
        em.getEntityManagerFactory().getCache().evict(Job.class);
        em.close();
    }

    public void deleteJob(String uuid) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Job j where j.uuid=:uuid");
        query.setParameter("uuid", uuid);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
    }

    public void deleteJobs(State state) {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Job j where j.state=:state");
        query.setParameter("state", state.getValue());
        query.executeUpdate();
        em.getTransaction().commit();
        em.getEntityManagerFactory().getCache().evict(Job.class);
        em.close();
    }

}
