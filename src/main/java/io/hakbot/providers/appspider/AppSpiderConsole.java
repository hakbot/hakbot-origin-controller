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

import alpine.logging.Logger;
import alpine.util.UuidUtil;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.plugin.BasePlugin;
import io.hakbot.controller.plugin.Console;
import io.hakbot.controller.plugin.RemoteInstance;
import io.hakbot.providers.appspider.ws.NTOService;
import io.hakbot.providers.appspider.ws.NTOServiceSoap;
import java.util.Map;

public class AppSpiderConsole extends BasePlugin implements Console {

    // Setup logging
    private static final Logger LOGGER = Logger.getLogger(AppSpiderConsole.class);

    public Object console(Job job, Map parameters) {
        final RemoteInstance remoteInstance = getRemoteInstance(job);
        final NTOService service = new NTOService(remoteInstance.getURL(), AppSpiderConstants.SERVICE_NAME);
        final NTOServiceSoap soap = service.getNTOServiceSoap();
        final String token = UuidUtil.stripHyphens(job.getUuid());
        return soap.getStatus2(remoteInstance.getUsername(), remoteInstance.getPassword(), token);
    }

}
