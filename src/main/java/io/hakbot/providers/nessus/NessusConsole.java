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

import alpine.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.plugin.BasePlugin;
import io.hakbot.controller.plugin.Console;
import io.hakbot.controller.plugin.RemoteInstance;
import net.continuumsecurity.ClientFactory;
import net.continuumsecurity.v6.ScanClientV6;
import javax.json.JsonObject;
import javax.security.auth.login.LoginException;
import java.util.Map;

public class NessusConsole extends BasePlugin implements Console {

    // Setup logging
    private static final Logger logger = Logger.getLogger(NessusConsole.class);

    public Object console(Job job, Map parameters) {
        RemoteInstance remoteInstance = getRemoteInstance(job);
        String scanId = getJobProperty(job, NessusConstants.PROP_SCAN_ID);
        try {
            ScanClientV6 scan = (ScanClientV6) ClientFactory.createScanClient(remoteInstance.getUrl(), 6, !remoteInstance.isValidateCertificates());
            scan.login(remoteInstance.getUsername(), remoteInstance.getPassword());
            JsonObject details = scan.getScanDetails(scanId);
            scan.logout();
            return details.toString();
        } catch (LoginException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

}
