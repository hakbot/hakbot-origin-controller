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
package io.hakbot.publishers;

import io.hakbot.controller.model.Job;
import io.hakbot.providers.Provider;

import java.util.Base64;

public abstract class BasePublisher implements Publisher {

    private String result;
    private Provider provider;

    /**
     * This method is called prior to any other method and is intended to initialize
     * the instance of the publisher. This method can be overwritten if initialization
     * of the publisher is necessary.
     */
    public boolean initialize(Job job, Provider provider) {
        this.result = job.getResult();
        this.provider = provider;
        return true;
    }

    /**
     * Returns the Base64 decoded results.
     */
    public byte[] getResult() {
        return Base64.getDecoder().decode(result);
    }

    /**
     * Returns the provider that generated the results about to be published.
     */
    public Provider getProvider() {
        return provider;
    }

}
