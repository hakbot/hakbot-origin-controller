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
package com.restjob.controller.providers.shell;

import com.restjob.controller.logging.Logger;
import com.restjob.controller.model.Job;
import com.restjob.controller.providers.BaseProvider;
import com.restjob.controller.workers.JobException;
import com.restjob.controller.workers.State;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

public class ShellProvider extends BaseProvider {

    // Setup logging
    private static final Logger logger = Logger.getLogger(ShellProvider.class);
    private Process process;

    public boolean process(Job job) {
        InputStream inputStream = null;
        try {
            ProcessBuilder pb = new ProcessBuilder(job.getPayload().split(" "));
            process = pb.start();
            int exitCode = process.waitFor();
            super.setResult(IOUtils.toByteArray(process.getInputStream()));
            if (exitCode != 0) {
                throw new JobException(exitCode);
            }
        } catch (IOException | InterruptedException e) {
            String message = "Could not execute job.";
            logger.error(message);
            logger.error(e.getMessage());
            job.setMessage(message);
            job.setSuccess(false);
        } catch (JobException e) {
            String message = "Job terminated abnormally. Exit code: " + e.getExitCode();
            logger.error(message);
            logger.error(e.getMessage());
            job.setMessage(message);
            job.setSuccess(false);
        } finally {
            IOUtils.closeQuietly(inputStream);
            job.setCompleted(new Date());
        }
        // If message is null, the encoding process was successful
        if (job.getMessage() == null) {
            job.setMessage("Job execution successful");
            job.setSuccess(true);
        } else {
            job.setSuccess(false);
        }
        job.setState(State.COMPLETED);
        job.setCompleted(new Date());
        return job.getSuccess();
    }

    public boolean cancel() {
        process.destroy();
        if (process.isAlive()) {
            process.destroyForcibly();
        }
        return !process.isAlive();
    }

}
