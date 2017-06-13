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

import alpine.Config;
import alpine.event.framework.EventService;
import alpine.logging.Logger;
import io.hakbot.HakbotConfigKey;
import io.hakbot.controller.event.JobProcessEvent;
import io.hakbot.controller.event.JobProgressCheckEvent;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.SystemAccount;
import io.hakbot.controller.persistence.QueryManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

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
    private static final Logger LOGGER = Logger.getLogger(JobManager.class);

    // Holds an instance of JobManager
    private static final JobManager INSTANCE = new JobManager();

    // Holds an in-memory queue of all UUIDs for jobs that need to be processed
    private Set<String> workQueue = Collections.synchronizedSet(new LinkedHashSet<String>());

    // Defines the interval that jobs will be permanently removed from the system
    private long jobPruneInterval;

    // A Principal implementation for system-wide object-level access control
    private SystemAccount systemAccount = new SystemAccount();

    // Defines a scheduled task that checks for new jobs and in-progress jobs
    private Timer jobSchedulerTimer = new Timer();

    // Defines a scheduled task that prunes the database of old jobs
    private Timer jobPruneTimer = new Timer();

    /**
     * Construct a new JobManager instance and setups up queues and scheduling
     */
    private JobManager() {
        LOGGER.info("Initializing JobManager");

        final int queueCheckInterval = Config.getInstance().getPropertyAsInt(HakbotConfigKey.QUEUE_CHECK_INTERVAL) * 1000; // in Seconds
        final long jobPruneCheckInterval = Config.getInstance().getPropertyAsLong(HakbotConfigKey.JOB_PRUNE_CHECK_INTERVAL) * 3600000; // in Hours
        this.jobPruneInterval = Config.getInstance().getPropertyAsLong(HakbotConfigKey.JOB_PRUNE_INTERVAL) * 86400000; // in Days

        // Creates a new JobSchedulerTask every x seconds (defined by queueCheckInterval)
        jobSchedulerTimer.schedule(new JobSchedulerTask(), 0, queueCheckInterval);

        // Creates a new JobPruneTask every x seconds (defined by jobPruneInterval)
        jobPruneTimer.schedule(new JobPruneTask(), 0, jobPruneCheckInterval);
    }

    /**
     * Return an instance of the JobManager instance
     * @return a JobManager instance
     */
    public static JobManager getInstance() {
        return INSTANCE;
    }

    /**
     * Polls for all unavailable jobs and those that are in queue and
     * sends events to start the jobs. This class also sends events to
     * update the status of all jobs marked in progress.
     */
    private class JobSchedulerTask extends TimerTask {
        public synchronized void run() {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Polling for in-progress jobs");
            }
            for (Job job: getInProcessJobs()) {
                if (workQueue.contains(job.getUuid())) {
                    workQueue.remove(job.getUuid());
                }
                EventService.getInstance().publish(new JobProgressCheckEvent(job.getUuid()));
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Polling for new jobs");
            }
            for (Job job: getWaitingJobs()) {
                if (workQueue.add(job.getUuid())) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("Adding job " + job.getUuid() + " to work queue");
                    }
                    EventService.getInstance().publish(new JobProcessEvent(job.getUuid()));
                }
            }
        }

        private List<Job> getInProcessJobs() {
            final List<Job> jobs = new ArrayList<>();
            final QueryManager qm = new QueryManager();
            jobs.addAll(qm.getJobs(State.IN_PROGRESS, QueryManager.OrderDirection.ASC, systemAccount));
            qm.close();
            return jobs;
        }

        private List<Job> getWaitingJobs() {
            final List<Job> jobs = new ArrayList<>();
            final QueryManager qm = new QueryManager();
            jobs.addAll(qm.getJobs(State.UNAVAILABLE, QueryManager.OrderDirection.ASC, systemAccount));
            jobs.addAll(qm.getJobs(State.IN_QUEUE, QueryManager.OrderDirection.ASC, systemAccount));
            qm.close();
            return jobs;
        }
    }

    /**
     * Removes old jobs from database
     */
    private class JobPruneTask extends TimerTask {
        public synchronized void run() {
            LOGGER.info("Starting Prune of Job Database");
            final Date now = new Date();
            final QueryManager qm = new QueryManager();
            final List<Job> allJobs = qm.getJobs(QueryManager.OrderDirection.DESC, systemAccount);
            for (Job job : allJobs) {
                if (!(job.getState() == State.CREATED || job.getState() == State.IN_QUEUE || job.getState() == State.IN_PROGRESS)) {
                    if (now.getTime() - (jobPruneInterval) >= getLastestTimestamp(job).getTime()) {
                        LOGGER.info("Pruning Job: " + job.getUuid());
                        qm.deleteJob(job.getUuid(), systemAccount);
                    }
                }
            }
            qm.close();
            LOGGER.info("Completed Prune of Job Database");
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

    public void shutdown() {
        jobSchedulerTimer.cancel();
        jobPruneTimer.cancel();
    }
}
