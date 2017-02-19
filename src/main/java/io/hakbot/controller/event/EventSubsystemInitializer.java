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
package io.hakbot.controller.event;

import alpine.event.LdapSyncEvent;
import alpine.event.framework.EventService;
import alpine.tasks.LdapSyncTask;
import io.hakbot.controller.tasks.TaskScheduler;
import io.hakbot.controller.workers.JobManager;
import io.hakbot.controller.workers.JobProcessWorker;
import io.hakbot.controller.workers.JobProgressCheckWorker;
import io.hakbot.controller.workers.JobPublishWorker;
import io.hakbot.controller.workers.JobUpdateLogger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class EventSubsystemInitializer implements ServletContextListener {

    // Starts the EventService
    private static final EventService EVENT_SERVICE = EventService.getInstance();

    public void contextInitialized(ServletContextEvent event) {
        EVENT_SERVICE.subscribe(JobProcessEvent.class, JobProcessWorker.class);
        EVENT_SERVICE.subscribe(JobProgressCheckEvent.class, JobProgressCheckWorker.class);
        EVENT_SERVICE.subscribe(JobPublishEvent.class, JobPublishWorker.class);
        EVENT_SERVICE.subscribe(JobUpdateEvent.class, JobUpdateLogger.class);
        EVENT_SERVICE.subscribe(LdapSyncEvent.class, LdapSyncTask.class);

        // Starts the JobManager and TaskScheduler
        JobManager.getInstance();
        TaskScheduler.getInstance();
    }

    public void contextDestroyed(ServletContextEvent event) {
        JobManager.getInstance().shutdown();
        TaskScheduler.getInstance().shutdown();

        EVENT_SERVICE.unsubscribe(JobProcessWorker.class);
        EVENT_SERVICE.unsubscribe(JobProgressCheckWorker.class);
        EVENT_SERVICE.unsubscribe(JobPublishWorker.class);
        EVENT_SERVICE.unsubscribe(JobUpdateLogger.class);
        EVENT_SERVICE.unsubscribe(LdapSyncTask.class);

        EVENT_SERVICE.shutdown();
    }
}