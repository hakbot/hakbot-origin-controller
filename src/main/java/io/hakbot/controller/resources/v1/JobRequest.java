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
package io.hakbot.controller.resources.v1;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobRequest {

    private String name;
    private JobRequestPlugin provider;
    private JobRequestPlugin publisher;

    @JsonProperty(required = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty(required = true)
    public JobRequestPlugin getProvider() {
        return provider;
    }

    public void setProvider(JobRequestPlugin provider) {
        this.provider = provider;
    }

    public JobRequestPlugin getPublisher() {
        return publisher;
    }

    public void setPublisher(JobRequestPlugin publisher) {
        this.publisher = publisher;
    }

    public class JobRequestPlugin {

        private String classname;
        private Object payload;

        @JsonProperty(value = "class", required = true)
        public String getClassname() {
            return classname;
        }

        public void setClass(String classname) {
            this.classname = classname;
        }

        @JsonProperty(required = true)
        public Object getPayload() {
            return payload;
        }

        public void setPayload(Object payload) {
            this.payload = payload;
        }
    }

}
