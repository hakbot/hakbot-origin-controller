/*
 * This file is part of RESTjob Controller.
 *
 * RESTjob Controller is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * RESTjob Controller is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * RESTjob Controller. If not, see http://www.gnu.org/licenses/.
 */
package com.restjob.controller.workers;

/**
 * If a job returns a result code other than 0, the process executed successfully,
 * but did not complete its task
 */
public class JobException extends Exception {

    private static final long serialVersionUID = -1012079758106474396L;

    private int exitCode;

    /**
     * Construct a new JobException
     * @param exitCode The exit code return by the command of the job
     */
    public JobException(int exitCode) {
        this.exitCode = exitCode;
    }

    /**
     * Returns the exit code the command returned
     * @return the exit code
     */
    public int getExitCode() {
        return exitCode;
    }

}
