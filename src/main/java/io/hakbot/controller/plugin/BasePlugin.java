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
import io.hakbot.controller.workers.State;

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
}
