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

import io.hakbot.controller.event.JobPublishEvent;
import io.hakbot.controller.event.JobUpdateEvent;
import io.hakbot.controller.event.framework.Event;
import io.hakbot.controller.event.framework.EventService;
import io.hakbot.controller.event.framework.Subscriber;
import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.SystemAccount;
import io.hakbot.controller.persistence.QueryManager;
import io.hakbot.publishers.Publisher;
import java.lang.reflect.Constructor;

public class JobPublishWorker implements Subscriber {

    private static final Logger logger = Logger.getLogger(JobPublishWorker.class);

    public void inform(Event e) {
        if (e instanceof JobPublishEvent) {
            JobPublishEvent event = (JobPublishEvent)e;

            QueryManager qm = new QueryManager();
            Job job = qm.getJob(event.getJobUuid(), Job.FetchGroup.ALL, new SystemAccount());
            qm.close();

            logger.info("Job: " + event.getJobUuid() + " is being processed.");

            boolean initialized;
            try {
                ExpectedClassResolver resolver = new ExpectedClassResolver();
                Class clazz = resolver.resolvePublisher(job);
                @SuppressWarnings("unchecked")
                Constructor<?> con = clazz.getConstructor();
                Publisher publisher = (Publisher)con.newInstance();

                initialized = publisher.initialize(job, null); //todo: remove provider in near future
                if (initialized) {
                    EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).message("Initialized " + publisher.getName()));
                    boolean success = publisher.publish(job);
                    if (success) {
                        EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(State.PUBLISHED));
                    } else {
                        EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(State.FAILED));
                    }
                } else {
                    EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(State.FAILED).message("Unable to initialize " + publisher.getName()));
                    //return; // Cannot continue.
                }
            } catch (Throwable ex) {
                logger.error(ex.getMessage());
                EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(State.FAILED).message(ex.getMessage()));
            }
        }
    }
}
