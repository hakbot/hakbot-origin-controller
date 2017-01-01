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

import io.hakbot.controller.event.JobUpdateEvent;
import io.hakbot.controller.event.framework.EventService;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.JobProperty;
import io.hakbot.controller.persistence.QueryManager;
import io.hakbot.controller.workers.State;
import java.util.List;
import java.util.Map;

public abstract class BasePlugin implements Plugin {

    /**
     * Adds a processing message to the job (asynchronously).
     */
    public void addProcessingMessage(Job job, String message) {
        EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).message(message));
    }

    /**
     * Updates the status of a job (asynchronously).
     */
    public void updateState(Job job, State state) {
        EventService.getInstance().publish(new JobUpdateEvent(job.getUuid()).state(state));
    }

    /**
     * Returns the value for the specified job property key
     */
    public String getJobProperty(Job job, String key) {
        QueryManager qm = new QueryManager();
        JobProperty prop = qm.getJobProperty(job, key);
        qm.close();
        return prop.getValue();
    }

    /**
     * Returns all the job properties for the specified job
     */
    public List<JobProperty> getJobProperties(Job job) {
        QueryManager qm = new QueryManager();
        List<JobProperty> props = qm.getJobProperties(job);
        qm.close();
        return props;
    }

    /**
     * Sets the value for the specified job property key
     */
    public void setJobProperty(Job job, String key, Object value) {
        QueryManager qm = new QueryManager();
        qm.setJobProperty(job, key, value);
        qm.close();
    }

    /**
     * Sets values for the specified job property keys
     */
    public void setJobProperties(Job job, Map<String, Object> properties) {
        QueryManager qm = new QueryManager();
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            qm.setJobProperty(job, entry.getKey(), entry.getValue());
        }
        qm.close();
    }

}
