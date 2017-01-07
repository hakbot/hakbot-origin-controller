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
package io.hakbot.providers;

import io.hakbot.controller.model.Job;

public interface AsynchronousProvider extends Provider {

    /**
     * Asynchronously process the job. This method will return immediately and
     * not wait for the job to complete.
     */
    void process(Job job);

    /**
     * Determines if the provider is currently running the specified job.
     */
    boolean isRunning(Job job);

    /**
     * Get the results of a provider.
     *
     * For AsynchronousProviders, getResult will typically perform a web
     * service (REST/SOAP) request out to the scanning service in order to
     * obtain the result of the scan.
     */
    void getResult(Job job);

}
