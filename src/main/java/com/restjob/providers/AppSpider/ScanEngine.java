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
package com.restjob.providers.appspider;

import javax.xml.namespace.QName;
import java.net.URL;

public class ScanEngine {

    private String alias;
    private URL wsdlLocation;
    private String username;
    private String password;
    private QName serviceName = new QName("http://ntobjectives.com/webservices/", "NTOService");


    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public URL getWsdlLocation() {
        return wsdlLocation;
    }

    public void setWsdlLocation(URL wsdlLocation) {
        this.wsdlLocation = wsdlLocation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public QName getServiceName() {
        return serviceName;
    }

    public void setServiceName(QName serviceName) {
        this.serviceName = serviceName;
    }

}
