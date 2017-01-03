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

import io.hakbot.controller.event.JobProgressCheckEvent;
import io.hakbot.controller.event.JobUpdateEvent;
import io.hakbot.controller.event.framework.Event;
import io.hakbot.controller.event.framework.EventService;
import io.hakbot.controller.event.framework.Subscriber;
import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.SystemAccount;
import io.hakbot.controller.persistence.QueryManager;
import io.hakbot.providers.AsynchronousProvider;
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

    private static final Logger logger = Logger.getLogger(JobProgressCheckWorker.class);

    public void inform(Event e) {
        if (e instanceof JobProgressCheckEvent) {
            JobProgressCheckEvent event = (JobProgressCheckEvent) e;

            QueryManager qm = new QueryManager();
            Job job = qm.getJob(event.getJobUuid(), Job.FetchGroup.MINIMAL, new SystemAccount());
            qm.close();

            if (logger.isDebugEnabled()) {
                logger.debug("Progress update for job: " + event.getJobUuid());
            }

            try {
                ExpectedClassResolver resolver = new ExpectedClassResolver();
                Class clazz = resolver.resolveProvider(job);
                @SuppressWarnings("unchecked")
                Constructor<?> constructor = clazz.getConstructor();
                // We only need to check status of asynchronous jobs
                AsynchronousProvider provider = (AsynchronousProvider)constructor.newInstance();
                if (provider.initialize(job)) {
                    if (!provider.isRunning(job)) {
                        // todo: Get result and update status
                    }
                } else {
                    EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(State.FAILED).message("Unable to initialize " + provider.getName()));
                }

            } catch (Throwable ex) {
                logger.error(ex.getMessage());
                EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(State.FAILED).message(ex.getMessage()));
            }
        }
    }
}
