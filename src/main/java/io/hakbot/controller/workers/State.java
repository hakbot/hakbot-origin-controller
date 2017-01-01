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

/**
 * This Enum class represents all of the possible states a Job can have.
 * <li>In Queue - The job is in the queue and is waiting for available job slots to become available.</li>
 * <li>In Progress - The job is currently being executed.</li>
 * <li>Completed - The job has successfully executed.</li>
 * <li>Canceled - The thread executing a job was requested to abort resulting in an canceled job.</li>
 * <li>Paused - The thread executing a job was requested to pause execution. Paused jobs still take up available job slots because the threads executing them are still active.</li>
 * <li>Unavailable - A job in the queue was scheduled to be executed (in progress) but the provider was not available to process the job.</li>
 * <li>Failed - The job encountered an error. Failed jobs are not retried.</li>
 */
public enum	State {

    CREATED("Created"),
    IN_QUEUE("In Queue"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed"),
    PUBLISHED("Published"),
    CANCELED("Canceled"),
    UNAVAILABLE("Unavailable"),
    FAILED("Failed");

    String stateName;
    State(String state) {
        this.stateName = state;
    }

    public String getValue() {
        return stateName;
    }

    public static State parse(String stateName) {
        State state = null; // Default
        for (State item : State.values()) {
            if (item.getValue().equalsIgnoreCase(stateName)) {
                state = item;
                break;
            }
        }
        return state;
    }

}
