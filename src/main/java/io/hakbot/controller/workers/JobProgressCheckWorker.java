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

import alpine.event.framework.Event;
import alpine.event.framework.EventService;
import alpine.event.framework.Subscriber;
import alpine.logging.Logger;
import io.hakbot.controller.event.JobProgressCheckEvent;
import io.hakbot.controller.event.JobPublishEvent;
import io.hakbot.controller.event.JobUpdateEvent;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.SystemAccount;
import io.hakbot.controller.persistence.QueryManager;
import io.hakbot.providers.AsynchronousProvider;
import org.apache.commons.lang3.StringUtils;
import java.lang.reflect.Constructor;

/**
 * The JobProgressCheckWorker is a Subscriber, that when a JobProgressCheckWorker
 * event is fired, will check on the status of the job. This class is designed to
 * work with AsynchronousProviders only. There is no need to check progress of
 * synchronous providers.
 *
 * @see JobProgressCheckEvent
 */
public class JobProgressCheckWorker implements Subscriber {

    private static final Logger LOGGER = Logger.getLogger(JobProgressCheckWorker.class);

    public void inform(Event e) {
        if (e instanceof JobProgressCheckEvent) {
            final JobProgressCheckEvent event = (JobProgressCheckEvent) e;

            final QueryManager qm = new QueryManager();
            final Job job = qm.getJob(event.getJobUuid(), new SystemAccount());
            qm.close();

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Progress update for job: " + event.getJobUuid());
            }

            try {
                final ExpectedClassResolver resolver = new ExpectedClassResolver();
                final Class clazz = resolver.resolveProvider(job);
                @SuppressWarnings("unchecked")
                final Constructor<?> constructor = clazz.getConstructor();
                // We only need to check status of asynchronous jobs
                final AsynchronousProvider provider = (AsynchronousProvider) constructor.newInstance();
                if (!provider.isRunning(job)) {
                    // Mark as complete first, then retrieve result. It may take a while to download result, so
                    // we don't what this attempted again, thus marking it complete first.
                    EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(State.COMPLETED));
                    provider.getResult(job);
                    // Now that the result has been downloaded check if a publisher was defined and if so, send event.
                    if (!StringUtils.isEmpty(job.getPublisher())) {
                        EventService.getInstance().publish(new JobPublishEvent(job.getUuid()));
                    }
                }
            } catch (Throwable ex) {
                LOGGER.error(ex.getMessage());
                EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(State.FAILED).message(ex.getMessage()));
            }
        }
    }
}
