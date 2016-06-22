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

import io.hakbot.controller.logging.Logger;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventService {

    private static final EventService instance = new EventService();
    private static final Logger logger = Logger.getLogger(EventService.class);
    private Map<Class<? extends Event>, ArrayList<Class<? extends Subscriber>>> subscriptionMap = new ConcurrentHashMap<>();

    private EventService() { }

    public static EventService getInstance() {
        return instance;
    }

    public void publish(Event event) {
        ArrayList<Class<? extends Subscriber>> subscriberClasses = subscriptionMap.get(event.getClass());
        for (Class clazz: subscriberClasses) {
            try {
                Subscriber subscriber = (Subscriber)clazz.newInstance();
                subscriber.inform(event);
            } catch (InstantiationException | IllegalAccessException e) {
                logger.error(e.getMessage());
            }
        }
    }

    public void subscribe(Class<? extends Event> eventType, Class<? extends Subscriber> subscriberType) {
        if (!subscriptionMap.containsKey(eventType)) {
            subscriptionMap.put(eventType, new ArrayList<>());
        }

        ArrayList<Class<? extends Subscriber>> subscribers = subscriptionMap.get(eventType);

        if (!subscribers.contains(subscriberType)) {
            subscribers.add(subscriberType);
        }
    }

    public void unsubscribe(Class<? extends Subscriber> subscriberType) {
        for (ArrayList<Class<? extends Subscriber>> list : subscriptionMap.values()) {
            list.remove(subscriberType);
        }
    }

}