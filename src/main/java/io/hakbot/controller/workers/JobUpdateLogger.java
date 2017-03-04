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
import io.hakbot.controller.event.JobPublishEvent;
import io.hakbot.controller.event.JobUpdateEvent;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.JobArtifact;
import io.hakbot.controller.model.SystemAccount;
import io.hakbot.controller.persistence.QueryManager;
import io.hakbot.providers.SynchronousProvider;
import org.apache.commons.lang3.StringUtils;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * The JobUpdateLogger is a Subscriber, that when a JobUpdateEvent is fired,
 * will update specific aspects of a job including job state, processing
 * messages, and the automatic updating of various timestamps.
 *
 * @see JobUpdateEvent
 */
public class JobUpdateLogger implements Subscriber {

    private static final Logger logger = Logger.getLogger(JobUpdateLogger.class);

    public void inform(Event e) {
        if(e instanceof JobUpdateEvent) {
            JobUpdateEvent event = (JobUpdateEvent)e;
            QueryManager qm = new QueryManager();
            Job job = qm.getJob(event.getJobUuid(), new SystemAccount());
            if (job != null) {
                if (event.getMessages() != null) {
                    for (String message: event.getMessages()) {
                        if (StringUtils.isNotBlank(message)) {
                            addMessage(job, message);
                        }
                    }
                }
                if (event.getState() != null) {
                    // Check to see if the job already failed. If so, do not update state anymore
                    if (job.getState() != State.FAILED) {
                        State state = event.getState();
                        if (job.getState() != state) {
                            addMessage(job, "Job state changed to " + state.getValue());
                        }
                        job.setState(event.getState());
                        if (state == State.CANCELED || state == State.COMPLETED || state == State.FAILED || state == State.PUBLISHED) {
                            job.setCompleted(new Date());
                        } else if (state == State.IN_PROGRESS) {
                            job.setStarted(new Date());
                        } else if (state == State.CREATED) {
                            job.setCreated(new Date());
                        }
                    }
                }
                if (event.getResult() != null) {
                    qm.setJobArtifact(job, JobArtifact.Type.PROVIDER_RESULT, JobArtifact.MimeType.BINARY.value(), event.getResult().getBytes(), null, null);
                }
                qm.updateJob(job);

                // Job has been updated, now check if a publisher was defined and if so, send event.
                if (event.getState() == State.COMPLETED && !StringUtils.isEmpty(job.getPublisher())) {
                    // First check to see if provider is sync or async. sync providers will be informed to
                    // publish here, while async provider will be informed to publish in JobProgressCheckWorker
                    ExpectedClassResolver resolver = new ExpectedClassResolver();
                    try {
                        Class clazz = resolver.resolveProvider(job);
                        if (SynchronousProvider.class.isAssignableFrom(clazz)) {
                            EventService.getInstance().publish(new JobPublishEvent(job.getUuid()));
                        }
                    } catch (ClassNotFoundException | ExpectedClassResolverException ex) {
                        logger.error(ex.getMessage());
                    }
                }
            }
            qm.close();
        }
    }

    private void addMessage(Job job, String message) {
        if (StringUtils.isEmpty(message)) {
            return;
        }
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String timestamp = formatter.format(date);
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(job.getMessage())) {
            sb.append(job.getMessage()).append("\n");
        }
        sb.append(timestamp).append(" - ").append(message);
        job.setMessage(sb.toString());
    }
}
