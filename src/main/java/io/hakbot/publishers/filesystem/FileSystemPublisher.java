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
package io.hakbot.publishers.filesystem;

import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.providers.Provider;
import io.hakbot.publishers.BasePublisher;
import io.hakbot.util.JsonUtil;

import javax.json.JsonObject;
import java.io.File;

public class FileSystemPublisher extends BasePublisher {

    // Setup logging
    private static final Logger logger = Logger.getLogger(FileSystemPublisher.class);

    private String publishPath;

    @Override
    public boolean initialize(Job job, Provider provider) {
        super.initialize(job, provider);

        JsonObject payload = JsonUtil.toJsonObject(job.getPublisherPayload());
        if (!JsonUtil.requiredParams(payload, "publishPath")) {
            job.addMessage("Invalid request. Expected parameter: [publishPath]");
            return false;
        }
        publishPath = JsonUtil.getString(payload, "publishPath");
        if (publishPath.startsWith("~" + File.separator)) {
            publishPath = System.getProperty("user.home") + publishPath.substring(1);
        }
        return true;
    }

    public boolean publish(Job job) {
        File path = new File(publishPath).getAbsoluteFile();
        if (!path.exists()) {
            job.addMessage("Specified publishPath does not exist.");
            return false;
        } else if (!path.isDirectory()) {
            job.addMessage("Specified publishPath is not a valid directory.");
            return false;
        } else if (!path.canWrite()) {
            job.addMessage("Cannot write to the specified publishPath.");
            return false;
        }
        File report = getResult(path);
        return report != null;
    }

    public String getName() {
        return "File System";
    }

    public String getDescription() {
        return "Publishes results to the file system.";
    }
}
