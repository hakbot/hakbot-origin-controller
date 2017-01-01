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
package io.hakbot.controller.workers;

import io.hakbot.controller.persistence.LocalPersistenceManagerFactory;
import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.plugin.PluginMetadata;
import io.hakbot.providers.Provider;
import io.hakbot.publishers.Publisher;
import org.apache.commons.lang3.StringUtils;
import javax.jdo.PersistenceManager;
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
    private Provider provider;
    private Publisher publisher;

    /**
     * Constructs a new JobExecutor instance for the specified Job
     * @param job A Job object
     */
    JobExecutor(Job job) {
        this.job = job;
    }

    /**
     * Executes the job.
     */
    public void run() {
        try {
            if (job.getUuid() == null || job.getProviderPayload() == null) {
                PersistenceManager pm = LocalPersistenceManagerFactory.createPersistenceManager();
                pm.getFetchPlan().addGroup(Job.FetchGroup.ALL.getName());
                job = pm.getObjectById(Job.class, job.getId());
                job.setState(State.CANCELED);
                pm.close();
                return;
            }
            if (logger.isInfoEnabled()) {
                logger.info("Job: " + job.getUuid() + " is being executed.");
            }

            //executeProvider();
            if (!StringUtils.isEmpty(job.getPublisher()) && job.getState() == State.COMPLETED) {
                //executePublisher();
            }
        } finally {
            isExecuting = false;
        }
    }
/*
    private void executeProvider() {
        boolean initialized, isAvailable = false, success = false;
        String result = null;
        PersistenceManager pm = LocalPersistenceManagerFactory.createPersistenceManager();
        pm.getFetchPlan().addGroup(Job.FetchGroup.ALL.getName());
        job = pm.getObjectById(Job.class, job.getId());
        try {
            ExpectedClassResolver resolver = new ExpectedClassResolver();
            Class clazz = resolver.resolveProvider(job);
            @SuppressWarnings("unchecked")
            Constructor<?> constructor = clazz.getConstructor();
            this.provider = (Provider) constructor.newInstance();
            initialized = provider.initialize(job);
            if (initialized) {
                job.addMessage("Initialized " + provider.getName());
                isAvailable = provider.isAvailable(job);
            } else {
                job.addMessage("Unable to initialize " + provider.getName());
                job.setState(State.COMPLETED);
                job = pm.getObjectById(Job.class, job.getId());
            }
            if (initialized && isAvailable) {
                job.setState(State.IN_PROGRESS);
                job.setStarted(new Date());
                //success = provider.process(job);
                result = provider.getResult();
                job = pm.getObjectById(Job.class, job.getId());
            } else if (initialized && !isAvailable){
                job.setState(State.UNAVAILABLE);
                job = pm.getObjectById(Job.class, job.getId());
            }
        } catch (Throwable e) {
            logger.error(e.getMessage());
            job.addMessage(e.getMessage());
            job = pm.getObjectById(Job.class, job.getId());
        } finally {
            if (job.getState() != State.UNAVAILABLE) {
                job.setState(State.COMPLETED);
                job.setCompleted(new Date());
                job.setResult(result);
                job.setSuccess(success);
            }
            job = pm.getObjectById(Job.class, job.getId());
            if (logger.isInfoEnabled()) {
                PluginMetadata pluginMetadata = new PluginMetadata(provider.getClass());
                logger.info(job.getUuid() + " - Provider: " + pluginMetadata.getName());
                logger.info(job.getUuid() + " - State: " + job.getState());
                logger.info(job.getUuid() + " - Success: " + job.getSuccess());
            }
            pm.close();
        }
    }

    private void executePublisher() {
        boolean initialized, success = false;
        PersistenceManager pm = LocalPersistenceManagerFactory.createPersistenceManager();
        pm.getFetchPlan().addGroup(Job.FetchGroup.ALL.getName());
        job = pm.getObjectById(Job.class, job.getId());
        try {
            ExpectedClassResolver resolver = new ExpectedClassResolver();
            Class clazz = resolver.resolvePublisher(job);
            @SuppressWarnings("unchecked")
            Constructor<?> constructor = clazz.getConstructor();
            this.publisher = (Publisher) constructor.newInstance();
            initialized = publisher.initialize(job, provider);
            if (initialized) {
                job.addMessage("Initialized " + publisher.getName());
                success = publisher.publish(job);
            } else {
                job.addMessage("Unable to initialize " + publisher.getName());
            }
        } catch (Throwable e) {
            logger.error(e.toString());
            job.addMessage(e.getMessage());
        } finally {
            if (success) {
                job.setState(State.PUBLISHED);
                job.setCompleted(new Date());
            }
            job.setSuccess(success);
            job = pm.getObjectById(Job.class, job.getId());
            if (logger.isInfoEnabled()) {
                PluginMetadata pluginMetadata = new PluginMetadata(publisher.getClass());
                logger.info(job.getUuid() + " - Publisher: " + pluginMetadata.getName());
                logger.info(job.getUuid() + " - State: " + job.getState());
                logger.info(job.getUuid() + " - Success: " + job.getSuccess());
            }
            pm.close();
        }
    }
*/
    /**
     * Cancels the job
     */
    public void cancel() {
        if (provider != null) {
            provider.cancel(job);
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
