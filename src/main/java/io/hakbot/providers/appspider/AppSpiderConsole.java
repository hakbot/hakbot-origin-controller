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
package io.hakbot.providers.appspider;

import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.persistence.QueryManager;
import io.hakbot.controller.plugin.Console;
import io.hakbot.controller.plugin.Plugin;
import io.hakbot.controller.plugin.RemoteInstance;
import io.hakbot.controller.plugin.RemoteInstanceAutoConfig;
import io.hakbot.providers.appspider.ws.NTOService;
import io.hakbot.providers.appspider.ws.NTOServiceSoap;
import io.hakbot.util.UuidUtil;
import java.util.Map;


public class AppSpiderConsole implements Console {

    // Setup logging
    private static final Logger logger = Logger.getLogger(AppSpiderConsole.class);

    public Object console(Job job, Map parameters) {
        // Retrieve the alias of the remote instance we're conducting the scan with
        QueryManager qm = new QueryManager();
        String alias = qm.getJobProperty(job, AppSpiderConstants.PROP_INSTANCE_ALIAS).getValue();

        // Resolve the remote instance via it's alias
        RemoteInstanceAutoConfig autoConfig = new RemoteInstanceAutoConfig();
        RemoteInstance remoteInstance = autoConfig.resolveInstance(Plugin.Type.PROVIDER, AppSpiderConstants.PLUGIN_ID, alias);

        NTOService service = new NTOService(remoteInstance.getURL(), AppSpiderConstants.SERVICE_NAME);
        NTOServiceSoap soap = service.getNTOServiceSoap();
        String token = UuidUtil.stripHyphens(job.getUuid());
        return soap.getStatus2(remoteInstance.getUsername(), remoteInstance.getPassword(), token);
    }

}
