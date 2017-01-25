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
package io.hakbot.controller.event.framework;

import io.hakbot.controller.Config;
import io.hakbot.controller.logging.Logger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A publish/subscribe (pubsub) event service that provides the ability to publish events and
 * asynchronously inform all subscribers to subscribed events.
 *
 * This event service is specific to the processing of Jobs.
 */
public class JobEventService extends BaseEventService {

    private static final JobEventService instance = new JobEventService();
    private static final Logger logger = Logger.getLogger(JobEventService.class);
    private static final ExecutorService executor =
            Executors.newFixedThreadPool(Config.getInstance().determineNumberOfThreads());

    static {
        instance.setExecutorService(executor);
        instance.setLogger(logger);
    }

    private JobEventService() { }

    public static JobEventService getInstance() {
        return instance;
    }

}