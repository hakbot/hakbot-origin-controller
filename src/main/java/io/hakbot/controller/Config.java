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

import io.hakbot.controller.logging.Logger;
import java.io.IOException;
import java.util.Properties;

/**
 * The Config class is responsible for reading the application.properties file
 */
public final class Config {

    private static final Logger logger = Logger.getLogger(Config.class);
    private static final String propFile = "application.properties";
    private static Config instance;
    private static Properties properties;


    /**
     * Provides a statically typed way to specify the named pairs in a property
     * file. All names which have a corresponding value in the property file
     * should be added to the enum.
     */
    public enum	Key {
        APPLICATION_NAME         ("application.name"),
        APPLICATION_VERSION      ("application.version"),
        APPLICATION_TIMESTAMP    ("application.timestamp"),
        CORE_MULTIPLIER          ("core.multiplier"),
        QUEUE_CHECK_INTERVAL     ("queue.check.interval"),
        JOB_PRUNE_INTERVAL       ("job.prune.interval"),
        JOB_PRUNE_CHECK_INTERVAL ("job.prune.check.interval"),
        //MAX_JOB_SIZE             ("max.job.size"),
        MAX_QUEUE_SIZE           ("max.queue.size"),
        DATABASE_MODE            ("database.mode"),
        DATABASE_PORT            ("database.port"),
        PROVIDERS_ENALBED        ("providers.enabled"),
        PUBLISHERS_ENABLED       ("publishers.enabled"),
        ENFORCE_AUTHENTICATION   ("enforce.authentication"),
        ENFORCE_AUTHORIZATION    ("enforce.authorization"),
        LDAP_SERVER_URL          ("ldap.server.url"),
        LDAP_DOMAIN              ("ldap.domain"),
        GZIP_COMPRESSION_ENABLED ("proto.gzip.enabled");

        String propertyName;
        private Key(String item) {
            this.propertyName = item;
        }
    }

    /**
     * Returns an instance of the Config object
     * @return a Config object
     */
    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }
        if (properties == null) {
            instance.init();
        }
        return instance;
    }

    /**
     * Initialize the Config object. This method should only be called once.
     */
    private void init() {
        if (properties != null) {
            return;
        }

        logger.info("Initializing Configuration");
        properties = new Properties();
        try {
            properties.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            logger.error("Unable to load " + propFile);
        }
    }

    /**
     * Return the configured value for the specified Key
     * @param key The Key to return the configuration for
     * @return a String of the value of the configuration
     */
    public String getProperty(Key key) {
        return properties.getProperty(key.propertyName);
    }

    public int getPropertyAsInt(Key key) {
        try {
            return Integer.parseInt(getProperty(key));
        } catch (NumberFormatException e) {
            logger.error("Error parsing number from property: " + key.name());
            throw e;
        }
    }

    public long getPropertyAsLong(Key key) {
        try {
            return Long.parseLong(getProperty(key));
        } catch (NumberFormatException e) {
            logger.error("Error parsing number from property: " + key.name());
            throw e;
        }
    }

    public boolean getPropertyAsBoolean(Key key) {
        return "true".equalsIgnoreCase(getProperty(key));
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public int determineNumberOfThreads() {
        int cores = Runtime.getRuntime().availableProcessors();
        int multiplier = getPropertyAsInt(Key.CORE_MULTIPLIER);
        if (multiplier > 0) {
            return cores * multiplier;
        } else {
            return cores;
        }
    }

    public static boolean isUnitTestsEnabled() {
        return Boolean.valueOf(System.getProperty("hakbot.unittests.enabled", "false"));
    }

    public static void enableUnitTests() {
        System.setProperty("hakbot.unittests.enabled", "true");
    }

}
