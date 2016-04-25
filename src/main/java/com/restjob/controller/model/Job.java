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
package com.restjob.controller.model;

import com.restjob.controller.workers.State;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "JOB", indexes = { @Index(columnList = "uuid", unique = true) })
@NamedQueries({
        @NamedQuery(name="Job.getJobs",
                query="SELECT j FROM Job j order by j.created asc"),
        @NamedQuery(name="Job.getJobByUuid",
                query="SELECT j FROM Job j where j.uuid=:uuid"),
        @NamedQuery(name="Job.getJobsByState",
                query="SELECT j FROM Job j where j.state=:state order by j.created asc")
})
public class Job implements Serializable {

    private static final long serialVersionUID = -4385863885620110686L;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Column(name = "UUID", nullable = false, length = 36)
    private String uuid;

    @Column(name = "provider", nullable = false)
    private String provider;

    @Column(name = "message")
    private String message;

    @Column(name = "payload")
    private String payload;

    @Column(name = "created")
    private Date created;

    @Column(name = "started")
    private Date started;

    @Column(name = "completed")
    private Date completed;

    @Column(name = "state", nullable = false, length = 12)
    private String state;

    @Column(name = "success")
    private boolean success;

    @Lob
    @Column(name = "result")
    private String result;

    @JsonIgnore
    @Version
    @Column(name = "VERSION", nullable = false)
    private Long version;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    @PrePersist
    public void prePersist() {
        if (this.created == null) {
            setCreated(new Date());
        }
        if (this.state == null) {
            setState(State.CREATED);
        }
        if (this.uuid == null) {
            setUuid(UUID.randomUUID().toString());
        }
    }

    @Override
    public boolean equals(Object object) {
        Job job = (Job)object;
        return getUuid().equals(job.getUuid());
    }

}
