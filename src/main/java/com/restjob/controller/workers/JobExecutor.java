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
import com.restjob.controller.plugin.PluginMetadata;
import com.restjob.providers.Provider;
import com.restjob.publishers.Publisher;
import org.apache.commons.lang.StringUtils;

import javax.persistence.EntityManager;
import java.lang.reflect.Constructor;
import java.util.Date;

/**
 * This class is responsible for executing jobs.
 */
class JobExecutor implements Runnable {

    // Setup logging
    private static final Logger logger = Logger.getLogger(JobExecutor.class);

    private Job job;
    private boolean isExecuting = true;
    private EntityManager em;
    private Provider provider;
    private Publisher publisher;

    /**
     * Constructs a new JobExecutor instance for the specified Job
     * @param job A Job object
     */
    JobExecutor(EntityManager em, Job job) {
        this.em = em;
        this.job = job;
    }

    /**
     * Executes the job.
     */
    public void run() {
        if (job.getUuid() == null || job.getProviderPayload() == null) {
            return;
        }
        if (logger.isInfoEnabled()) {
            logger.info("Job: " + job.getUuid() + " is being executed.");
        }

        executeProvider();
        if (!StringUtils.isEmpty(job.getPublisher()) && job.getState() == State.COMPLETED) {
            executePublisher();
        }
        isExecuting = false;
    }

    private void executeProvider() {
        boolean initialized, isAvailable = false, success = false;
        String result = null;
        try {
            ExpectedClassResolver resolver = new ExpectedClassResolver();
            Class clazz = resolver.resolveProvider(job);
            @SuppressWarnings("unchecked")
            Constructor<?> constructor = clazz.getConstructor();
            this.provider = (Provider) constructor.newInstance();
            initialized = provider.initialize(job);
            em.getTransaction().begin();
            if (initialized) {
                isAvailable = provider.isAvailable(job);
            } else {
                job.setState(State.COMPLETED);
            }
            if (initialized && isAvailable) {
                job.setState(State.IN_PROGRESS);
                job.setStarted(new Date());
                em.getTransaction().commit();
                em.getTransaction().begin();

                success = provider.process(job);
                result = provider.getResult();
            } else if (initialized && !isAvailable){
                job.setState(State.UNAVAILABLE);
            }
        } catch (Throwable e) {
            logger.error(e.getMessage());
            job.addMessage(e.getMessage());
        } finally {
            if (job.getState() != State.UNAVAILABLE) {
                job.setState(State.COMPLETED);
                job.setCompleted(new Date());
                job.setResult(result);
                job.setSuccess(success);
            }
            em.getTransaction().commit();
            if (logger.isInfoEnabled()) {
                PluginMetadata pluginMetadata = new PluginMetadata(provider.getClass());
                logger.info(job.getUuid() + " - Provider: " + pluginMetadata.getName());
                logger.info(job.getUuid() + " - State: " + job.getState());
                logger.info(job.getUuid() + " - Success: " + job.getSuccess());
            }
        }
    }

    private void executePublisher() {
        boolean initialized, success = false;
        try {
            ExpectedClassResolver resolver = new ExpectedClassResolver();
            Class clazz = resolver.resolvePublisher(job);
            @SuppressWarnings("unchecked")
            Constructor<?> constructor = clazz.getConstructor();
            this.publisher = (Publisher) constructor.newInstance();
            initialized = publisher.initialize(job, provider);
            em.getTransaction().begin();
            if (initialized) {
                success = publisher.publish(job);
            }
        } catch (Throwable e) {
            logger.error(e.getMessage());
            job.addMessage(e.getMessage());
        } finally {
            if (success) {
                job.setState(State.PUBLISHED);
                job.setCompleted(new Date());
            }
            job.setSuccess(success);
            em.getTransaction().commit();
            if (logger.isInfoEnabled()) {
                PluginMetadata pluginMetadata = new PluginMetadata(publisher.getClass());
                logger.info(job.getUuid() + " - Publisher: " + pluginMetadata.getName());
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
