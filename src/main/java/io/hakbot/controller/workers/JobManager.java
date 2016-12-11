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

import io.hakbot.controller.Config;
import io.hakbot.controller.persistence.LocalPersistenceManagerFactory;
import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.SystemAccount;
import io.hakbot.controller.persistence.QueryManager;
import javax.jdo.PersistenceManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * The JobManager is used to store the current queued jobs waiting
 * to be executed and the jobs which are currently being executed.
 * It also implements a TimerTask which will check for new work to
 * do and another TimerTask which will cleanup references to
 * completed jobs. Both tasks intervals are configurable in
 * application.properties. If new work is found, the scheduler will
 * take one job off the queue every time the scheduled task runs.
 */
public class JobManager {

    // Setup logging
    private static final Logger logger = Logger.getLogger(JobManager.class);

    // Holds all work that has been requested to be performed and is currently in the queue.
    // Job objects will be removed from queue once the job has started
    private LinkedList<Job> workQueue;

    // Hold all of the JobExecutors currently in process
    private ArrayBlockingQueue<JobExecutor> executors;

    // Defines the value for the maximum number of concurrent jobs that can take place at a time
    private int maxJobSize;

    // Defines the value for the maximum number of items in the queue to prevent DoS attacks
    private int maxQueueSize;

    // Defines the interval that jobs will be permanently removed from the system
    private long jobPruneInterval;

    // A Principal implementation for system-wide object-level access control
    private SystemAccount systemAccount;

    // Holds an instance of JobManager
    private static final JobManager instance = new JobManager();

    /**
     * Construct a new JobManager instance and setups up queues and scheduling
     */
    private JobManager() {
        logger.info("Initializing JobManager");

        maxJobSize = Config.getInstance().getPropertyAsInt(Config.Key.MAX_JOB_SIZE);
        maxQueueSize = Config.getInstance().getPropertyAsInt(Config.Key.MAX_QUEUE_SIZE);

        int queueCheckInterval = Config.getInstance().getPropertyAsInt(Config.Key.QUEUE_CHECK_INTERVAL) * 1000; // in Seconds
        int jobCleanupInterval = Config.getInstance().getPropertyAsInt(Config.Key.JOB_CLEANUP_INTERVAL) * 1000; // in Seconds
        long jobPruneCheckInterval = Config.getInstance().getPropertyAsLong(Config.Key.JOB_PRUNE_CHECK_INTERVAL) * 3600000; // in Hours
        this.jobPruneInterval = Config.getInstance().getPropertyAsLong(Config.Key.JOB_PRUNE_INTERVAL) * 86400000; // in Days

        if (logger.isDebugEnabled()) {
            logger.debug("Max Job Size: " + maxJobSize);
            logger.debug("Max Queue Size: " + maxQueueSize);
            logger.debug("Queue Check Interval: " + queueCheckInterval);
            logger.debug("Job Cleanup Interval: " + jobCleanupInterval);
            logger.debug("Job Prune Check Interval: " + jobPruneCheckInterval);
            logger.debug("Job Prune Interval: " + jobPruneInterval);
        }

        executors = new ArrayBlockingQueue<>(maxJobSize);
        workQueue = new LinkedList<>();
        systemAccount = new SystemAccount();

        // Creates a new JobSchedulerTask every x seconds (defined by queueCheckInterval)
        Timer schTimer = new Timer();
        schTimer.schedule(new JobSchedulerTask(), 0, queueCheckInterval);

        // Creates a new JobCleanupTask every x seconds (defined by jobCleanupInterval)
        Timer cleanupTimer = new Timer();
        cleanupTimer.schedule(new JobCleanupTask(), 0, jobCleanupInterval);

        // Creates a new JobCleanupTask every x seconds (defined by jobPruneInterval)
        Timer pruneTimer = new Timer();
        pruneTimer.schedule(new JobPruneTask(), 0, jobPruneCheckInterval);
    }

    /**
     * Return an instance of the JobManager instance
     * @return a JobManager instance
     */
    public static JobManager getInstance() {
        return instance;
    }

    /**
     * Adds a new Job to the queue
     * @param job the Job object to add to queue
     */
    private synchronized void add(Job job) {
        if (inQueue(job)) {
            return;
        }
        if (logger.isDebugEnabled()) {
            logger.debug("Adding job to queue: " + job.getUuid());
        }
        if (workQueue.size() < maxQueueSize) {
            if (State.UNAVAILABLE != job.getState()) {
                PersistenceManager pm = LocalPersistenceManagerFactory.createPersistenceManager();
                pm.getFetchPlan().addGroup(Job.FetchGroup.ALL.getName());
                job = pm.getObjectById(Job.class, job.getId());
                job.addMessage("Job added to queue");
                job.setState(State.IN_QUEUE);
                job = pm.getObjectById(Job.class, job.getId());
                pm.close();
            }
            workQueue.add(job);
        }
    }

    /**
     * Removes a Job from the queue if found. If job is currently being executed,
     * a call to quit(Job) is made to gracefully quit the process. The state of
     * the specified job is marked as canceled.
     * @param job the Job object to remove from the queue or from current running processes
     */
    public synchronized void cancel(Job job) {
        PersistenceManager pm = LocalPersistenceManagerFactory.createPersistenceManager();
        pm.getFetchPlan().addGroup(Job.FetchGroup.ALL.getName());
        job = pm.getObjectById(Job.class, job.getId());
        if (logger.isDebugEnabled()) {
            logger.debug("Canceling job: " + job.getUuid());
        }
        // First, check the queue, if job exists in queue, remove it
        for (Job checkJob: workQueue) {
            if (checkJob.getUuid().equals(job.getUuid())) {
                job.addMessage("Job removed from queue");
                workQueue.remove(checkJob);
            }
        }

        // Next, check the list of current jobs in progress, if found, quit the process
        for (JobExecutor executor: executors) {
            if (executor.getJob().getUuid().equals(job.getUuid())) {
                job.addMessage("Job is executing - requesting cancellation");
                executor.cancel();
                sleep(2000); // sleep for 2 seconds
                executor.waitFor();
            }
        }
        job.setState(State.CANCELED);
        pm.close();
    }

    /**
     * Checks to see if the specified job is currently in progress.
     * @param job the job to check
     * @return true if job is in progress, false if not in progress
     */
    public boolean inProgress(Job job) {
        return getJobInProgress(job) != null;
    }

    /**
     * Returns a Job which is in progress. Returns null if the
     * specified uuid is not found in the list of jobs in progress.
     * @param job the job to check
     * @return a Job object matching the specified uuid
     */
    public Job getJobInProgress(Job job) {
        for (JobExecutor executor: executors) {
            if (executor.getJob().getUuid().equals(job.getUuid()))
                return executor.getJob();
        }
        return null;
    }

    /**
     * Returns a list of all jobs in progress
     * @return a list of Job objects
     */
    public List<Job> getJobsInProgress() {
        List<Job> jobs = new ArrayList<>();
        for (JobExecutor executor: executors) {
            jobs.add(executor.getJob());
        }
        return jobs;
    }

    /**
     * Returns a list of all jobs in the queue
     * @return a list of Job object
     */
    public List<Job> getJobsInQueue() {
        return new ArrayList<>(workQueue);
    }

    /**
     * Checks to see if the specified job is currently in the queue.
     * @param job the job to check
     * @return true if job is in the queue, false if not in the queue
     */
    public boolean inQueue(Job job) {
        return getJobInQueue(job) != null;
    }

    /**
     * Returns a Job which is in the queue. Returns null if the
     * specified job is not found in the list of jobs in the queue.
     * @param job the job to check
     * @return a Job object matching the specified uuid
     */
    public Job getJobInQueue(Job job) {
        for (Job j: workQueue) {
            if (j.getUuid().equals(job.getUuid())) {
                return job;
            }
        }
        return null;
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (Exception e) {
            // eat it
        }
    }

    /**
     * This class will look for slots available and remove new items
     * from the queue and add them to the list of jobs being performed
     * and execute the job.
     */
    private class JobSchedulerTask extends TimerTask {
        public synchronized void run() {
            if (logger.isDebugEnabled()) {
                logger.debug("Polling for new jobs");
            }

            for (Job job: getWaitingJobs()) {
                add(job);
            }

            if (executors.size() < maxJobSize && workQueue.size() > 0) {
                // There is an opening for new work to be performed. Get the job from the queue.
                Job job = workQueue.remove();

                // Create and start new JobExecutor
                JobExecutor executor = new JobExecutor(job);
                Thread thread = new Thread(executor);
                thread.start();

                // Add job to the list of jobs being executed
                executors.add(executor);
            }
        }

        private List<Job> getWaitingJobs() {
            List<Job> jobs = new ArrayList<>();
            QueryManager qm = new QueryManager();
            jobs.addAll(qm.getJobs(State.UNAVAILABLE, QueryManager.OrderDirection.ASC, Job.FetchGroup.ALL, systemAccount));
            jobs.addAll(qm.getJobs(State.CREATED, QueryManager.OrderDirection.ASC, Job.FetchGroup.ALL, systemAccount));
            return jobs;
        }
    }

    /**
     * This class will look for Jobs which are no loner being executed and cleanup references
     */
    private class JobCleanupTask extends TimerTask {
        public synchronized void run() {
            for (JobExecutor executor : executors) {
                // Check to see if the executor job is still executing
                if (!executor.isExecuting()) {
                    // Execution has completed. Remove reference
                    executors.remove(executor);
                }
            }
            // Search for and cancel all jobs marked IN_PROGRESS but aren't actually executing (interrupted)
            QueryManager qm = new QueryManager();
            List<Job> allJobs = qm.getJobs(State.IN_PROGRESS, QueryManager.OrderDirection.DESC, Job.FetchGroup.MINIMAL, systemAccount);
            for (Job job : allJobs) {
                //if (job != "origin") { //todo
                boolean executing = false;
                for (JobExecutor executor : executors) {
                    executing = job.getUuid().equals(executor.getJob().getUuid());
                }
                if (!executing) {
                    PersistenceManager pm = LocalPersistenceManagerFactory.createPersistenceManager();
                    job = pm.getObjectById(Job.class, job.getId());
                    pm.currentTransaction().begin();
                    job.setState(State.COMPLETED);
                    pm.currentTransaction().commit();
                    pm.close();
                }
                //}
            }
        }
    }

    /**
     * This class will look for Jobs which should be pruned from the database
     */
    private class JobPruneTask extends TimerTask {
        public synchronized void run() {
            logger.info("Starting Prune of Job Database");
            Date now = new Date();
            QueryManager qm = new QueryManager();
            List<Job> allJobs = qm.getJobs(QueryManager.OrderDirection.DESC, Job.FetchGroup.MINIMAL, systemAccount);
            for (Job job: allJobs) {
                if (!(job.getState() == State.CREATED)) {
                    if (now.getTime() - (jobPruneInterval) >= getLastestTimestamp(job).getTime()) {
                        if (!(inQueue(job) || inProgress(job))) {
                            logger.info("Pruning Job: " + job.getUuid());
                            qm.deleteJob(job.getUuid(), systemAccount);
                        }
                    }
                }
            }
            logger.info("Completed Prune of Job Database");
        }
    }

    private Date getLastestTimestamp(Job job) {
        if (job.getCompleted() != null) {
            return job.getCompleted();
        } else if (job.getStarted() != null) {
            return job.getStarted();
        } else {
            return job.getCreated();
        }
    }

}
