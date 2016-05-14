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
import io.hakbot.providers.Provider;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

public abstract class BasePublisher implements Publisher {

    // Setup logging
    private static final Logger logger = Logger.getLogger(BasePublisher.class);

    private Job job;
    private Provider provider;

    /**
     * This method is called prior to any other method and is intended to initialize
     * the instance of the publisher. This method can be overwritten if initialization
     * of the publisher is necessary.
     */
    public boolean initialize(Job job, Provider provider) {
        this.job = job;
        this.provider = provider;
        return true;
    }

    /**
     * Returns the Base64 decoded results.
     */
    public byte[] getResult() {
        return Base64.getDecoder().decode(job.getResult());
    }

    /**
     * Returns the provider that generated the results about to be published.
     */
    public Provider getProvider() {
        return provider;
    }

    public File getResult(File directory) {
        String filename = job.getUuid();
        if (!StringUtils.isEmpty(provider.getResultExtension())) {
            filename = filename + "." + provider.getResultExtension();
        }

        File result = new File(directory, filename).getAbsoluteFile();
        try {
            FileUtils.writeByteArrayToFile(result, getResult());
            job.addMessage("Result written to: " + result.getPath());
        } catch (IOException e) {
            logger.error("Unable to write result from job: " + job.getUuid());
            logger.error(e.getMessage());
            job.addMessage(e.getMessage());
            return null;
        }
        return result;
    }

}
