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

import io.hakbot.controller.event.framework.EventService;
import io.hakbot.controller.workers.JobManager;
import io.hakbot.controller.workers.JobProcessWorker;
import io.hakbot.controller.workers.JobProgressCheckWorker;
import io.hakbot.controller.workers.JobPublishWorker;
import io.hakbot.controller.workers.JobUpdateLogger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class EventSubsystemInitializer implements ServletContextListener {

    // Starts the EventService
    private static final EventService eventService = EventService.getInstance();

    public void contextInitialized(ServletContextEvent event) {
        // Starts the JobManager
        JobManager.getInstance();

        eventService.subscribe(JobProcessEvent.class, JobProcessWorker.class);
        eventService.subscribe(JobProgressCheckEvent.class, JobProgressCheckWorker.class);
        eventService.subscribe(JobPublishEvent.class, JobPublishWorker.class);
        eventService.subscribe(JobUpdateEvent.class, JobUpdateLogger.class);
    }

    public void contextDestroyed(ServletContextEvent event) {
        JobManager.getInstance().shutdown();

        eventService.unsubscribe(JobProcessWorker.class);
        eventService.unsubscribe(JobProgressCheckWorker.class);
        eventService.unsubscribe(JobPublishWorker.class);
        eventService.unsubscribe(JobUpdateLogger.class);
        eventService.shutdown();
    }
}