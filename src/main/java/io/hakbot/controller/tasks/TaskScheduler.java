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
package io.hakbot.controller.tasks;

import alpine.event.LdapSyncEvent;
import alpine.tasks.AlpineTaskScheduler;

public class TaskScheduler extends AlpineTaskScheduler {

    // Holds an instance of TaskScheduler
    private static final TaskScheduler instance = new TaskScheduler();

    private TaskScheduler() {

        // Creates a new event that executes every 6 hours (21600000) after an initial 10 second (10000) delay
        scheduleEvent(new LdapSyncEvent(), 10000, 21600000);
    }

    /**
     * Return an instance of the TaskScheduler instance
     * @return a TaskScheduler instance
     */
    public static TaskScheduler getInstance() {
        return instance;
    }

}
