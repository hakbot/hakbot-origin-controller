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
package io.hakbot.controller.resources;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.json.JsonObject;

public class JobRequest {

    private String provider;
    private String providerPayload;
    private String publisher;
    private String publisherPayload;
    private String name;

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getProviderPayload() {
        return providerPayload;
    }

    @JsonProperty("providerPayload")
    public void setProviderPayload(String providerPayload) {
        this.providerPayload = providerPayload;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublisherPayload() {
        return publisherPayload;
    }

    public void setPublisherPayload(String publisherPayload) {
        this.publisherPayload = publisherPayload;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public class Payload {



    }
}
