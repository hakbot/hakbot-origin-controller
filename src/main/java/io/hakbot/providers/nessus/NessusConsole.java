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
package io.hakbot.providers.nessus;

import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.JobProperty;
import io.hakbot.controller.persistence.QueryManager;
import io.hakbot.controller.plugin.Console;
import io.hakbot.controller.plugin.Plugin;
import io.hakbot.controller.plugin.RemoteInstance;
import io.hakbot.controller.plugin.RemoteInstanceAutoConfig;
import net.continuumsecurity.ClientFactory;
import net.continuumsecurity.v6.ScanClientV6;

import javax.json.JsonObject;
import javax.security.auth.login.LoginException;
import java.util.Map;


public class NessusConsole implements Console {

    // Setup logging
    private static final Logger logger = Logger.getLogger(NessusConsole.class);

    public Object console(Job job, Map parameters) {

        String serverUrl;
        String username;
        String password;
        boolean validateCerts;

        // Retrieve the alias of the remote instance we're conducting the scan with along with the scan id
        QueryManager qm = new QueryManager();
        JobProperty alias = qm.getJobProperty(job, NessusConstants.PROP_INSTANCE_ALIAS);
        if (alias != null) {
            // Resolve the remote instance via it's alias
            RemoteInstanceAutoConfig autoConfig = new RemoteInstanceAutoConfig();
            RemoteInstance remoteInstance = autoConfig.resolveInstance(Plugin.Type.PROVIDER, NessusConstants.PLUGIN_ID, alias.getValue());
            serverUrl = remoteInstance.getUrl();
            username = remoteInstance.getUsername();
            password = remoteInstance.getPassword();
            validateCerts = remoteInstance.isValidateCertificates();
        } else {
            // Remote instance wasn't specified, grab the individual parameters instead
            serverUrl = qm.getJobProperty(job, NessusConstants.PROP_SERVER_URL).getValue();
            username = qm.getJobProperty(job, NessusConstants.PROP_SCAN_USERNAME).getValue();
            password = qm.getJobProperty(job, NessusConstants.PROP_SCAN_PASSWORD).getValue();
            validateCerts = Boolean.parseBoolean(qm.getJobProperty(job, NessusConstants.PROP_SERVER_VALIDATE_CERTS).getValue());
        }

        // Retrieve the unique scan id assigned by Nessus when the scan was started
        JobProperty property = qm.getJobProperty(job, NessusConstants.PROP_SCAN_ID);
        if (property == null) {
            return null;
        }

        try {
            ScanClientV6 scan = (ScanClientV6) ClientFactory.createScanClient(serverUrl, 6, !validateCerts);
            scan.login(username, password);
            JsonObject details = scan.getScanDetails(property.getValue());
            scan.logout();
            return details.toString();
        } catch (LoginException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

}
