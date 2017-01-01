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
package io.hakbot.providers.shell;

import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.workers.JobException;
import io.hakbot.controller.workers.State;
import io.hakbot.providers.BaseProvider;
import io.hakbot.providers.SynchronousProvider;
import io.hakbot.util.JsonUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import javax.json.JsonObject;
import java.io.IOException;
import java.io.InputStream;

public class ShellProvider extends BaseProvider implements SynchronousProvider {

    // Setup logging
    private static final Logger logger = Logger.getLogger(ShellProvider.class);
    private Process process;
    private String command;

    public boolean initialize(Job job) {
        JsonObject payload = JsonUtil.toJsonObject(job.getProviderPayload());
        if (!JsonUtil.requiredParams(payload, "command")) {
            addProcessingMessage(job, "Invalid request. Expected parameters: [command]");
            return false;
        }
        command = JsonUtil.getString(payload, "command");
        return true;
    }

    public boolean process(Job job) {
        InputStream inputStream = null;
        InputStream errorStream = null;
        try {
            ProcessBuilder pb = new ProcessBuilder(command.split(" "));
            process = pb.start();
            int exitCode = process.waitFor();
            inputStream = process.getInputStream();
            errorStream = process.getErrorStream();
            byte[] stdout = IOUtils.toByteArray(inputStream);
            byte[] stderr = IOUtils.toByteArray(errorStream);
            if (logger.isDebugEnabled()) {
                logger.debug("STDOUT:");
                logger.debug(new String(stdout));
                logger.debug("STDERR:");
                logger.debug(new String(stderr));
            }
            super.setResult(stdout);
            if (exitCode != 0) {
                if (StringUtils.isEmpty(super.getResult())) {
                    setResult(stderr);
                }
                throw new JobException(exitCode);
            }
            return true;
        } catch (IOException | InterruptedException e) {
            addProcessingMessage(job, "Could not execute job.");
            addProcessingMessage(job, e.getMessage());
        } catch (JobException e) {
            addProcessingMessage(job, "Job terminated abnormally. Exit code: " + e.getExitCode());
            addProcessingMessage(job, e.getMessage());
        } finally {
            IOUtils.closeQuietly(inputStream);
            IOUtils.closeQuietly(errorStream);
        }
        return false;
    }

    public boolean cancel(Job job) {
        updateState(job, State.CANCELED);
        process.destroy();
        if (process.isAlive()) {
            process.destroyForcibly();
        }
        return !process.isAlive();
    }

    public String getName() {
        return "Shell";
    }

    public String getDescription() {
        return "Executes a shell command or script and captures the output from STDOUT/STDERR.";
    }

    public String getResultMimeType() {
        return "text/plain";
    }

    public String getResultExtension() {
        return "txt";
    }

}
