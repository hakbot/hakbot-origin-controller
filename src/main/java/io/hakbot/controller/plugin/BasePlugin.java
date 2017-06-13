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
package io.hakbot.controller.plugin;

import alpine.event.framework.EventService;
import io.hakbot.controller.event.JobUpdateEvent;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.JobArtifact;
import io.hakbot.controller.model.JobProperty;
import io.hakbot.controller.persistence.QueryManager;
import io.hakbot.controller.workers.State;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import java.util.List;
import java.util.Map;

public abstract class BasePlugin {

    /**
     * Adds a processing message to the job (asynchronously).
     */
    protected void addProcessingMessage(Job job, String message) {
        EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).message(message));
    }

    /**
     * Updates the status of a job (asynchronously).
     */
    protected void updateState(Job job, State state) {
        EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(state));
    }

    /**
     * Updates the status of a job (asynchronously) along with a processing message.
     */
    protected void updateState(Job job, State state, String... message) {
        EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(state).message(message));
    }

    /**
     * Returns the value for the specified job property key
     */
    protected String getJobProperty(Job job, String key) {
        final QueryManager qm = new QueryManager();
        final JobProperty prop = qm.getJobProperty(job, key);
        qm.close();
        if (prop == null || prop.getValue() == null || StringUtils.isBlank(prop.getValue())) {
            return null;
        } else {
            return prop.getValue();
        }
    }

    /**
     * Returns all the job properties for the specified job
     */
    protected List<JobProperty> getJobProperties(Job job) {
        final QueryManager qm = new QueryManager();
        final List<JobProperty> props = qm.getJobProperties(job);
        qm.close();
        return props;
    }

    /**
     * Sets the value for the specified job property key. Accepts null
     * values, but does not save them, thus failing gracefully.
     */
    protected void setJobProperty(Job job, String key, Object value) {
        if (key == null || value == null) {
            return;
        }
        final QueryManager qm = new QueryManager();
        qm.setJobProperty(job, key, value);
        qm.close();
    }

    /**
     * Sets values for the specified job property keys. Accepts null
     * values, but does not save them, thus failing gracefully.
     */
    protected void setJobProperties(Job job, Map<String, Object> properties) {
        final QueryManager qm = new QueryManager();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            if (entry.getKey() != null && entry.getValue() != null) {
                qm.setJobProperty(job, entry.getKey(), entry.getValue());
            }
        }
        qm.close();
    }

    protected JobArtifact addArtifact(Job job, JobArtifact.Type type, String mimeType, byte[] contents, String filename) {
        final QueryManager qm = new QueryManager();
        final JobArtifact artifact = qm.setJobArtifact(job, type, mimeType, contents, null, filename);
        qm.close();
        return artifact;
    }

    protected JobArtifact getArtifact(Job job, JobArtifact.Type type) {
        final  QueryManager qm = new QueryManager();
        final JobArtifact artifact = qm.getJobArtifact(job, type);
        final byte[] contents = artifact.getContents(); // Force this blob to be loaded when qm is still open
        qm.close();
        return artifact;
    }

    protected RemoteInstance getRemoteInstance(Job job) {
        final JobArtifact artifact = getArtifact(job, JobArtifact.Type.REMOTE_INSTANCE);
        final byte[] content = artifact.getContents();
        return (RemoteInstance) SerializationUtils.deserialize(content);
    }

    protected void setRemoteInstance(Job job, RemoteInstance remoteInstance) {
        final byte[] content = SerializationUtils.serialize(remoteInstance);
        final QueryManager qm = new QueryManager();
        qm.setJobArtifact(job, JobArtifact.Type.REMOTE_INSTANCE, JobArtifact.MimeType.OBJECT.value(), content, null, null);
        qm.close();
    }

    protected JobArtifact getProviderPayload(Job job) {
        return getArtifact(job, JobArtifact.Type.PROVIDER_PAYLOAD);
    }

    protected JobArtifact getPublisherPayload(Job job) {
        return getArtifact(job, JobArtifact.Type.PUBLISHER_PAYLOAD);
    }
}
