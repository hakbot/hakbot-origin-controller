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
package io.hakbot.publishers.kennasecurity;

import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.model.Job;
import io.hakbot.controller.plugin.RemoteInstance;
import io.hakbot.controller.plugin.RemoteInstanceAutoConfig;
import io.hakbot.providers.Provider;
import io.hakbot.publishers.BasePublisher;
import io.hakbot.util.JsonUtil;
import org.apache.commons.collections4.MapUtils;
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

public class KennaSecurityPublisher extends BasePublisher {

    // Setup logging
    private static final Logger logger = Logger.getLogger(KennaSecurityPublisher.class);

    private static Map<String, RemoteInstance> instanceMap = new RemoteInstanceAutoConfig().createMap(Type.PUBLISHER, "kennasecurity");

    private RemoteInstance remoteInstance;

    @Override
    public boolean initialize(Job job, Provider provider) {
        super.initialize(job, provider);

        JsonObject payload = JsonUtil.toJsonObject(job.getProviderPayload());
        remoteInstance = instanceMap.get(MapUtils.getString(payload, "instance"));
        if (remoteInstance == null) {
            job.addMessage("KennaSecurity instance cannot be found or is not defined.");
            return false;
        }
        return true;
    }

    public boolean publish(Job job) {
        File report = getResult(new File(System.getProperty("java.io.tmpdir")));
        if (report == null) {
            return false;
        }
        boolean success = false;
        try {
            Client client = ClientBuilder.newBuilder().register(MultiPartFeature.class).build();
            FileDataBodyPart filePart = new FileDataBodyPart("file", report);
            FormDataMultiPart formDataMultiPart = new FormDataMultiPart();
            FormDataMultiPart multipart = (FormDataMultiPart) formDataMultiPart.bodyPart(filePart);
            WebTarget target = client.target(remoteInstance.getUrl());
            target.request().header("X-Risk-Token", remoteInstance.getToken());
            Response response = target.request().post(Entity.entity(multipart, multipart.getMediaType()));
            success = response.getStatus() == 200;
            if (!success) {
                job.addMessage("Failed to upload result to KennaSecurity");
                job.addMessage(response.getStatusInfo().getReasonPhrase());
            }
            formDataMultiPart.close();
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return success;
    }

    public String getName() {
        return "KennaSecurity";
    }

    public String getDescription() {
        return "Publishes results to KennaSecurity via the file connector API.";
    }

}
