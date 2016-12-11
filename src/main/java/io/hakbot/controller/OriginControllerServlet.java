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
package io.hakbot.controller;

import io.hakbot.controller.auth.KeyManager;
import io.hakbot.controller.logging.Logger;
import io.hakbot.controller.workers.JobManager;
import io.swagger.jaxrs.config.SwaggerContextService;
import io.swagger.models.Info;
import io.swagger.models.License;
import io.swagger.models.Swagger;
import io.swagger.models.auth.ApiKeyAuthDefinition;
import io.swagger.models.auth.In;
import org.glassfish.jersey.servlet.ServletContainer;
import javax.crypto.SecretKey;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;

/**
 * The OriginControllerServlet is the main servlet which extends
 * the Jersey ServletContainer. It is responsible for setting up
 * the runtime environment by initializing the
 * {@link JobManager JobManager}
 * and setting the path to properties files used for
 * {@link Config Config}(uration).
 */
public final class OriginControllerServlet extends ServletContainer {

    private static final long serialVersionUID = -133386507668410112L;
    private static final Logger logger = Logger.getLogger(OriginControllerServlet.class);

    /**
     * Overrides the servlet init method and loads sets the InputStream necessary
     * to load application.properties and creates the first instance of
     * {@link JobManager JobManager} thus starting
     * its scheduled tasks.
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        logger.info("Starting " + Config.getInstance().getProperty(Config.Key.APPLICATION_NAME));
        super.init(config);

        Info info = new Info()
                .title(Config.getInstance().getProperty(Config.Key.APPLICATION_NAME) + " API")
                .version(Config.getInstance().getProperty(Config.Key.APPLICATION_VERSION))
                .description("Vendor-Neutral Security Tool Automation Controller")
                .license(new License()
                        .name("GPL v3.0")
                        .url("http://www.gnu.org/licenses/gpl-3.0.txt"));

        Swagger swagger = new Swagger().info(info);
        swagger.securityDefinition("X-Api-Key", new ApiKeyAuthDefinition("X-Api-Key", In.HEADER));
        new SwaggerContextService().withServletConfig(config).updateSwagger(swagger);

        KeyManager keyManager = KeyManager.getInstance();
        if (!keyManager.keyPairExists()) {
            try {
                KeyPair keyPair = keyManager.generateKeyPair();
                keyManager.save(keyPair);
            } catch (NoSuchAlgorithmException e) {
                logger.error("An error occurred generating new keypair");
                logger.error(e.getMessage());
            } catch (IOException e) {
                logger.error("An error occurred saving newly generated keypair");
                logger.error(e.getMessage());
            }
        }
        if (!keyManager.secretKeyExists()) {
            try {
                SecretKey secretKey = keyManager.generateSecretKey();
                keyManager.save(secretKey);
            } catch (NoSuchAlgorithmException e) {
                logger.error("An error occurred generating new secret key");
                logger.error(e.getMessage());
            } catch (IOException e) {
                logger.error("An error occurred saving newly generated secret key");
                logger.error(e.getMessage());
            }
        }

        JobManager.getInstance(); // starts the JobManager
    }

    /**
     * Overrides the servlet destroy method and shuts down the servlet
     */
    @Override
    public void destroy() {
        logger.info("Stopping " + Config.getInstance().getProperty(Config.Key.APPLICATION_NAME));
        super.destroy();
    }

}
