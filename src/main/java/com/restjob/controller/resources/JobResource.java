/*
 * This file is part of RESTjob Controller.
 *
 * RESTjob Controller is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * RESTjob Controller is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * RESTjob Controller. If not, see http://www.gnu.org/licenses/.
 */
package com.restjob.controller.resources;

import com.restjob.controller.listener.LocalEntityManagerFactory;
import com.restjob.controller.model.Job;
import com.restjob.controller.workers.State;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/job")
@Api(value = "job", authorizations = {
        @Authorization(value="api_key")
})
public class JobResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns all job",
            notes = "Returns a list of all jobs ordered by the time the job was created.",
            response = Job.class,
            responseContainer = "List")
    public Response getAllJobs() {
        EntityManager em = LocalEntityManagerFactory.createEntityManager();
        List result = em.createNamedQuery("Job.getJobs").getResultList();
        em.close();
        return Response.ok(result).build();
    }

    @GET
    @Path("{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Returns a specific job",
            notes = "Returns a specific job by it's UUID.",
            response = Job.class)
    public Response getJobByUuid(@PathParam("uuid") String uuid) {
        EntityManager em = LocalEntityManagerFactory.createEntityManager();
        TypedQuery<Job> query = em.createNamedQuery("Job.getJobByUuid", Job.class).setParameter("uuid", uuid);
        List<Job> jobs = query.getResultList();
        em.close();
        if (jobs.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            Job job = jobs.get(0);
            return Response.ok(job).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Creates a new job",
            notes = "Returns the job after creating it. The UUID can be used to later query on the job.",
            response = Job.class)
    public Response addJob(Job jsonJob) {
        if (jsonJob.getProvider() == null || jsonJob.getPayload() == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        EntityManager em = LocalEntityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Job job = new Job();
        job.setProvider(jsonJob.getProvider());
        job.setPayload(jsonJob.getPayload());
        em.persist(job);
        em.getTransaction().commit();
        em.close();
        return Response.ok(job).build();
    }

    //todo: delete this when major testing is complete
    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Purges all jobs from database")
    public Response purgeAll() {
        EntityManager em = LocalEntityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Job");
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        return Response.ok().build();
    }

    @DELETE
    @Path("/uuid/{uuid}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Deletes a specific job")
    public Response purgeByUuid(@PathParam("uuid") String uuid) {
        EntityManager em = LocalEntityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Job j where j.uuid=:uuid");
        query.setParameter("uuid", uuid);
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        return Response.ok().build();
    }

    @DELETE
    @Path("/state/{state}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Purges all jobs with a specific state from the database",
                notes = "A supported state is required.")
    public Response purge(@PathParam("state") State state) {
        EntityManager em = LocalEntityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("DELETE FROM Job j where j.state=:state");
        query.setParameter("state", state.getValue());
        query.executeUpdate();
        em.getTransaction().commit();
        em.close();
        return Response.ok().build();
    }

}
