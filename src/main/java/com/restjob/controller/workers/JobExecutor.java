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
package com.restjob.controller.workers;

import com.restjob.controller.logging.Logger;
import com.restjob.controller.model.Job;
import com.restjob.controller.providers.BaseProvider;

import javax.persistence.EntityManager;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * This class is responsible for executing jobs.
 */
public class JobExecutor implements Runnable {

    // Setup logging
    private static final Logger logger = Logger.getLogger(JobExecutor.class);

    private Job job;
    private boolean isExecuting = true;
    private EntityManager em;
    private BaseProvider provider;

    /**
     * Constructs a new JobExecutor instance for the specified Job
     * @param job A Job object
     */
    public JobExecutor(EntityManager em, Job job) {
        this.em = em;
        this.job = job;
    }

    /**
     * Executes the job.
     */
    public void run() {
        if (job.getUuid() == null || job.getPayload() == null) {
            return;
        }
        if (logger.isInfoEnabled()) {
            logger.info("Job: " + job.getUuid() + " is being executed.");
        }

        boolean initialized = false;
        boolean isAvailable = false;
        boolean success = false;
        String result = null;
        try {
            Class clazz = Class.forName(job.getProvider(), false, this.getClass().getClassLoader());
            // /todo - whitelist allowable classes
            Constructor<?> constructor = clazz.getConstructor();
            this.provider = (BaseProvider) constructor.newInstance();
            initialized = provider.initialize(job);
            if (initialized) {
                isAvailable = provider.isAvailable(job);
            }
            if (initialized && isAvailable) {
                em.getTransaction().begin();
                job.setState(State.IN_PROGRESS);
                job.setStarted(new Date());
                em.getTransaction().commit();

                success = provider.process(job);
                result = provider.getResult();
            } else {
                em.getTransaction().begin();
                job.setState(State.UNAVAILABLE);
                em.getTransaction().commit();
            }
        } catch (ClassNotFoundException | NoSuchMethodException |
                IllegalAccessException | InstantiationException | InvocationTargetException e) {
            logger.error(e.getMessage());
        } finally {
            if (job.getState() != State.UNAVAILABLE) {
                em.getTransaction().begin();
                job.setState(State.COMPLETED);
                job.setCompleted(new Date());
                job.setResult(result);
                job.setSuccess(success);
                em.getTransaction().commit();
            }
            isExecuting = false;
            if (logger.isInfoEnabled()) {
                logger.info(job.getUuid() + " - Provider: " + job.getProvider());
                logger.info(job.getUuid() + " - State: " + job.getState());
                logger.info(job.getUuid() + " - Success: " + job.getSuccess());
            }
        }
    }

    /**
     * Cancels the job
     */
    public void cancel() {
        if (provider != null) {
            provider.cancel();
        }
    }

    /**
     * Return the Job currently being executed
     * @return the Job object being executed
     */
    public Job getJob() {
        return job;
    }

    public boolean isExecuting() {
        return isExecuting;
    }

    public void waitFor() {
        while (isExecuting()) {
            // do nothing
        }
    }

    public Thread getThread() {
        return Thread.currentThread();
    }

}
