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
package com.restjob.controller.providers;

import java.util.Base64;

public abstract class BaseProvider implements Controllable {

    private String result;

    public void setResult(byte[] result) {
        if (result.length == 0) {
            return;
        }
        this.result = Base64.getEncoder().encodeToString(result);
    }

    public void setResult(Object result) {
        if (result == null) {
            return;
        }
        this.result = Base64.getEncoder().encodeToString(result.toString().getBytes());
    }

    public String getResult() {
        return result;
    }

}
