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
import io.hakbot.controller.plugin.BasePlugin;
import io.hakbot.controller.workers.ExpectedClassResolver;
import io.hakbot.controller.workers.ExpectedClassResolverException;
import io.hakbot.providers.Provider;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Base64;

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
     * Returns the Base64 decoded results.
     */
    public byte[] getResult() {
        return Base64.getDecoder().decode(job.getResult());
    }

    public File getResult(File directory) {
        String filename = job.getUuid();

        try {
            ExpectedClassResolver resolver = new ExpectedClassResolver();
            Class clazz = resolver.resolveProvider(job);
            @SuppressWarnings("unchecked")
            Constructor<?> con = clazz.getConstructor();
            Provider provider = (Provider)con.newInstance();

            if (!StringUtils.isEmpty(provider.getResultExtension())) {
                filename = filename + "." + provider.getResultExtension();
            }

            File result = new File(directory, filename).getAbsoluteFile();

            FileUtils.writeByteArrayToFile(result, getResult());
            addProcessingMessage(job, "Result written to: " + result.getPath());
            return result;
        } catch (ClassNotFoundException | ExpectedClassResolverException | NoSuchMethodException |
                InstantiationException | InvocationTargetException | IllegalAccessException |  IOException e) {
            logger.error("Unable to write result from job: " + job.getUuid());
            logger.error(e.getMessage());
            addProcessingMessage(job, e.getMessage());
        }
        return null;
    }

}
