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

import io.hakbot.controller.event.JobUpdateEvent;
import io.hakbot.controller.logging.Logger;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A publish/subscribe (pubsub) event service that provides the ability to publish events and
 * asynchronously inform all subscribers to subscribed events.
 */
public abstract class BaseEventService {

    private Logger logger = Logger.getLogger(BaseEventService.class);
    private Map<Class<? extends Event>, ArrayList<Class<? extends Subscriber>>> subscriptionMap = new ConcurrentHashMap<>();
    private ExecutorService ftexecutor = Executors.newFixedThreadPool(1);
    private ExecutorService stexecutor = Executors.newSingleThreadExecutor();

    void setExecutorService(ExecutorService executor) {
        this.ftexecutor = executor;
    }

    void setLogger(Logger logger) {
        this.logger = logger;
    }

    /**
     * Publishes events. Published events will get dispatched to all subscribers in the order in which they
     * subscribed. Subscribers are informed asynchronously one after the next.
     * @param event An Event to publish
     */
    public void publish(Event event) {
        logger.debug("Dispatching event: " + event.getClass().toString());
        ArrayList<Class<? extends Subscriber>> subscriberClasses = subscriptionMap.get(event.getClass());
        for (Class clazz: subscriberClasses) {
            logger.debug("Alerting subscriber " + clazz.getName());

            // We don't want database update events to have to wait in the same queue as job processing events
            // Eventually make this configurable.
            ExecutorService executorService = event.getClass()==JobUpdateEvent.class ? stexecutor : ftexecutor;

            executorService.submit(() -> {
                try {
                    Subscriber subscriber = (Subscriber)clazz.newInstance();
                    subscriber.inform(event);
                } catch (InstantiationException | IllegalAccessException e) {
                    logger.error("An error occurred while informing subscriber: " + e.getMessage());
                }
            });
        }
    }

    /**
     * Subscribes to an event. Subscribes are automatically notified of all events for which they are
     * subscribed.
     * @param eventType The type of event to subscribe to
     * @param subscriberType The Subscriber that gets informed when the type of event is published
     */
    public void subscribe(Class<? extends Event> eventType, Class<? extends Subscriber> subscriberType) {
        if (!subscriptionMap.containsKey(eventType)) {
            subscriptionMap.put(eventType, new ArrayList<>());
        }
        ArrayList<Class<? extends Subscriber>> subscribers = subscriptionMap.get(eventType);
        if (!subscribers.contains(subscriberType)) {
            subscribers.add(subscriberType);
        }
    }

    /**
     * Unsubscribes a subscriber. All event types the subscriber has subscribed to will be
     * unsubscribed. Once unsubscribed, the subscriber will no longer be informed of published
     * events.
     * @param subscriberType The Subscriber to unsubscribe.
     */
    public void unsubscribe(Class<? extends Subscriber> subscriberType) {
        for (ArrayList<Class<? extends Subscriber>> list : subscriptionMap.values()) {
            list.remove(subscriberType);
        }
    }

    /**
     * Shuts down the executioner. Once shut down, future work will not be performed. This should
     * only be called prior to the application being shut down.
     */
    public void shutdown() {
        logger.info("Shutting down JobEventService");
        ftexecutor.shutdownNow();
        stexecutor.shutdownNow();
    }

}