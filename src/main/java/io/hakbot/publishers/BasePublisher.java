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
package io.hakbot.publishers;

import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.JobArtifact;
import io.hakbot.controller.plugin.BasePlugin;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.IOException;

public abstract class BasePublisher extends BasePlugin implements Publisher {

    // Setup logging
    private static final Logger logger = Logger.getLogger(BasePublisher.class);

    private Job job;

    /**
     * This method is called prior to any other method and is intended to initialize
     * the instance of the publisher. This method can be overwritten if initialization
     * of the publisher is necessary.
     */
    public boolean initialize(Job job) {
        this.job = job;
        return true;
    }

    /**
     * Writes the contents of the JobArtifact to the specified directory. Returns a
     * File object referencing the result, or null if something goes wrong.
     */
    public File getResult(JobArtifact artifact, File directory) {
        try {
            String filename = artifact.getFilename();
            if (StringUtils.isEmpty(filename)) {
                filename = job.getUuid() + ".result";
            }
            File result = new File(directory, filename).getAbsoluteFile();
            FileUtils.writeByteArrayToFile(result, artifact.getContents());
            addProcessingMessage(job, "Result written to: " + result.getPath());
            return result;
        } catch (IOException e) {
            logger.error("Unable to write result from job: " + job.getUuid());
            logger.error(e.getMessage());
            addProcessingMessage(job, "Unable to write result to file");
            addProcessingMessage(job, e.getMessage());
        }
        return null;
    }

}
