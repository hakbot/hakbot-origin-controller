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
package io.hakbot.publishers.threadfix;

import alpine.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.model.JobArtifact;
import io.hakbot.controller.plugin.RemoteInstance;
import io.hakbot.controller.plugin.RemoteInstanceAutoConfig;
import io.hakbot.publishers.BasePublisher;
import io.hakbot.util.JsonUtil;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ThreadFixPublisher extends BasePublisher {

    // Setup logging
    private static final Logger LOGGER = Logger.getLogger(ThreadFixPublisher.class);

    private static Map<String, RemoteInstance> instanceMap = new RemoteInstanceAutoConfig().createMap(Type.PUBLISHER, "threadfix");

    private RemoteInstance remoteInstance;
    private int appId;

    @Override
    public boolean initialize(Job job) {
        super.initialize(job);

        final JsonObject payload = JsonUtil.toJsonObject(getPublisherPayload(job).getContents());
        if (!JsonUtil.requiredParams(payload, "appId")) {
            addProcessingMessage(job, "Invalid request. Expected parameter: [appId]");
            return false;
        }
        remoteInstance = instanceMap.get(JsonUtil.getString(payload, "instance"));
        if (remoteInstance == null) {
            addProcessingMessage(job, "ThreadFix remote instance cannot be found or is not defined.");
            return false;
        }
        appId = JsonUtil.getInt(payload, "appId");
        return true;
    }

    public boolean publish(Job job) {
        final JobArtifact artifact = getArtifact(job, JobArtifact.Type.PROVIDER_RESULT);
        final File report = getResult(artifact, new File(System.getProperty("java.io.tmpdir")));
        if (report == null) {
            return false;
        }

        boolean success = false;
        try {
            final Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
            final FileDataBodyPart filePart = new FileDataBodyPart("file", report);
            final FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
            final FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.bodyPart(filePart);
            final WebTarget target = client.target(remoteInstance.getUrl() + "/applications/" + appId + "/upload?apiKey=" + remoteInstance.getApiKey());
            final Response response = target.request().post(Entity.entity(multipart, multipart.getMediaType()));
            success = response.getStatus() == 200;
            if (!success) {
                addProcessingMessage(job, "Failed to upload result to ThreadFix");
                addProcessingMessage(job, response.getStatusInfo().getReasonPhrase());
            }
            formDataMultiPart.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage());
        }
        return success;
    }

    public String getName() {
        return "ThreadFix";
    }

    public String getDescription() {
        return "Publishes results to ThreadFix Community or Enterprise edition for vulnerability aggregation and normalization.";
    }

}
