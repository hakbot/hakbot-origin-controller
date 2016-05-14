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
package io.hakbot.controller.model;

import io.hakbot.controller.workers.State;
import org.apache.commons.lang3.StringUtils;
import java.io.Serializable;
import java.util.Date;

public class Job implements Serializable {

    private static final long serialVersionUID = 3956171171879917556L;

    private String uuid;
    private String provider;
    private String publisher;
    private String message;
    private String providerPayload;
    private String publisherPayload;
    private Date created;
    private Date started;
    private Date completed;
    private String state;
    private boolean success;
    private String result;


    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addMessage(String message) {
        if (StringUtils.isEmpty(message)) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        if (!StringUtils.isEmpty(getMessage())) {
            sb.append(getMessage()).append("\n");
        }
        sb.append(message);
        setMessage(sb.toString());
    }

    public String getProviderPayload() {
        return providerPayload;
    }

    public void setProviderPayload(String payload) {
        this.providerPayload = payload;
    }

    public String getPublisherPayload() {
        return publisherPayload;
    }

    public void setPublisherPayload(String payload) {
        this.publisherPayload = payload;
    }

    public Date getCreated() {
        if (created != null) {
            return new Date(created.getTime());
        }
        return null;
    }

    public void setCreated(Date created) {
        this.created = new Date(created.getTime());
    }

    public Date getStarted() {
        if (started != null) {
            return new Date(started.getTime());
        }
        return null;
    }

    public void setStarted(Date started) {
        this.started = new Date(started.getTime());
    }

    public Date getCompleted() {
        if (completed != null) {
            return new Date(completed.getTime());
        }
        return null;
    }

    public void setCompleted(Date completed) {
        this.completed = new Date(completed.getTime());
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public State getState () {
        return State.parse(this.state);
    }

    public void setState(State state) {
        this.state = state.getValue();
    }

}
