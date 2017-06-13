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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.jdo.annotations.Column;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Unique;
import java.io.Serializable;

@PersistenceCapable
@JsonInclude(JsonInclude.Include.NON_NULL)
public class JobArtifact implements Serializable {

    private static final long serialVersionUID = -834098044195814313L;

    public enum Type {
        REMOTE_INSTANCE,
        PROVIDER_PAYLOAD,
        PUBLISHER_PAYLOAD,
        PROVIDER_RESULT,
        PUBLISHER_RESULT
    }

    public enum MimeType {
        BINARY("application/octet-stream"),
        BZIP("application/x-bzip"),
        BZIP2("application/x-bzip2"),
        CSV("text/csv"),
        GZIP("application/x-gzip"),
        HTML("text/html"),
        JSON("application/json"),
        OBJECT("application/java-serialized-object"),
        PDF("application/pdf"),
        PLAIN_TEXT("text/plain"),
        XML("application/xml"),
        ZIP("application/zip");

        String mimeType;
        MimeType(String mimeType) {
            this.mimeType = mimeType;
        }
        public String value() {
            return mimeType;
        }
    }

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.INCREMENT)
    @JsonIgnore
    private long id;

    @Persistent
    @Column(name = "JOB_ID", allowsNull = "false")
    @JsonIgnore
    private long jobid;

    @Persistent
    @Unique(name = "JOBARTIFACT_UUID_IDX")
    @Column(name = "UUID", jdbcType = "VARCHAR", length = 36, allowsNull = "false")
    private String uuid;

    @Persistent
    @Column(name = "TYPE", jdbcType = "VARCHAR", length = 50, allowsNull = "false")
    private String type;

    @Persistent
    @Column(name = "MIMETYPE", jdbcType = "VARCHAR", length = 255, allowsNull = "false")
    private String mimetype;

    @Persistent
    @Column(name = "FILENAME", jdbcType = "VARCHAR", length = 255)
    private String filename;

    @Persistent
    @Column(name = "CONTENTS", jdbcType = "BLOB", allowsNull = "false")
    private byte[] contents;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getJobid() {
        return jobid;
    }

    public void setJobid(long jobid) {
        this.jobid = jobid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type.name();
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public void setMimetype(MimeType mimetype) {
        setMimetype(mimetype.value());
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public byte[] getContents() {
        return contents;
    }

    public void setContents(byte[] contents) {
        this.contents = contents;
    }
}
