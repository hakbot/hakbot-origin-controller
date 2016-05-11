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
package com.restjob.publishers;

import com.restjob.controller.model.Job;
import com.restjob.controller.plugin.Plugin;
import com.restjob.providers.Provider;

public interface Publisher extends Plugin {

    /**
     * This method is called prior to any other method and is intended to initialize
     * the instance of the publisher.
     */
    boolean initialize(Job job, Provider provider);

    /**
     * Publishes the results from a job. Returns true if the publish was successful, false if not.
     */
    boolean publish(Job job);

    /**
     * Returns the provider that generated the results about to be published.
     */
    Provider getProvider();
}
