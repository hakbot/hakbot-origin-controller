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

import io.hakbot.controller.event.JobProcessEvent;
import io.hakbot.controller.event.JobPublishEvent;
import io.hakbot.controller.event.JobUpdateEvent;
import io.hakbot.controller.event.framework.Event;
import io.hakbot.controller.event.framework.EventService;
import io.hakbot.controller.event.framework.Subscriber;
import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.SystemAccount;
import io.hakbot.controller.persistence.QueryManager;
import io.hakbot.providers.AsynchronousProvider;
import io.hakbot.providers.Provider;
import io.hakbot.providers.SynchronousProvider;
import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.Constructor;

public class JobProcessWorker implements Subscriber {

    private static final Logger logger = Logger.getLogger(JobProcessWorker.class);

    public void inform(Event e) {
        if (e instanceof JobProcessEvent) {
            JobProcessEvent event = (JobProcessEvent)e;

            QueryManager qm = new QueryManager();
            Job job = qm.getJob(event.getJobUuid(), Job.FetchGroup.ALL, new SystemAccount());
            qm.close();

            logger.info("Job: " + event.getJobUuid() + " is being processed.");

            boolean initialized, isAvailable;
            try {
                ExpectedClassResolver resolver = new ExpectedClassResolver();
                Class clazz = resolver.resolveProvider(job);
                @SuppressWarnings("unchecked")
                Constructor<?> con = clazz.getConstructor();
                Provider provider = (AsynchronousProvider.class == clazz) ?
                        (AsynchronousProvider)con.newInstance() : (SynchronousProvider)con.newInstance();

                initialized = provider.initialize(job);
                if (initialized) {
                    EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).message("Initialized " + provider.getName()));
                    isAvailable = provider.isAvailable(job);
                } else {
                    EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(State.FAILED).message("Unable to initialize " + provider.getName()));
                    return; // Cannot continue.
                }

                if (isAvailable) {
                    EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(State.IN_PROGRESS));
                    if (provider instanceof AsynchronousProvider) {
                        // Asynchronously process a job. Another task will periodically poll for updates and status.
                        ((AsynchronousProvider)provider).process(job);
                    } else {
                        // Synchronous execution needs to wait for the process to complete, thus holding up a thread.
                        // The boolean result from the execution determines if the execution was successful or not.
                        boolean success = ((SynchronousProvider)provider).process(job);
                        String result = provider.getResult();
                        if (success) {
                            EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(State.COMPLETED).result(result));
                            // Check if a publisher was defined in the job definition and if so, send event
                            if (!StringUtils.isEmpty(job.getPublisher())) {
                                EventService.getInstance().publish(new JobPublishEvent(job.getUuid()).result(result));
                            }
                        } else {
                            EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(State.FAILED).result(result));
                        }
                    }
                } else {
                    EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(State.UNAVAILABLE));
                }
            } catch (Throwable ex) {
                logger.error(ex.getMessage());
                EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(State.FAILED).message(ex.getMessage()));
            }
        }
    }
}
