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
package io.hakbot.controller.persistence;

import io.hakbot.controller.Config;
import io.hakbot.controller.ConfigItem;
import io.hakbot.controller.logging.Logger;
import org.apache.commons.lang3.StringUtils;
import org.h2.tools.Server;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.SQLException;

public class LocalPersistenceInitializer implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(LocalPersistenceInitializer.class);
    private static Server dbServer;

    public void contextInitialized(ServletContextEvent event) {
        startDbServer();
    }

    public void contextDestroyed(ServletContextEvent event) {
        stopDbServer();
    }

    private void startDbServer() {
        String mode = Config.getInstance().getProperty(ConfigItem.DATABASE_MODE);
        int port = Config.getInstance().getPropertyAsInt(ConfigItem.DATABASE_PORT);

        if (StringUtils.isEmpty(mode) || !(mode.equals("server") || mode.equals("embedded"))) {
            logger.error("Database mode not specified. Expected values are 'server' or 'embedded'");
        }

        if (dbServer != null || mode.equals("embedded")) {
            return;
        }
        String[] args = new String[]{
                "-tcp",
                "-tcpPort", String.valueOf(port),
                "-tcpAllowOthers"
        };
        try {
            logger.info("Attempting to start database service");
            dbServer = Server.createTcpServer(args).start();
            logger.info("Database service started");
        } catch (SQLException e) {
            logger.error("Unable to start database service: " + e.getMessage());
            stopDbServer();
            System.exit(1);
        }
    }

    private void stopDbServer() {
        logger.info("Shutting down database service");
        if (dbServer != null)
            dbServer.shutdown();
    }

}
