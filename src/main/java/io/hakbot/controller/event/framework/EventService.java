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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventService {

    private static EventService INSTANCE = new EventService();
    private Map<Class<? extends Event>, Map<Subscriber, List<Filter>>> subscriptionMap = new ConcurrentHashMap<>();

    private EventService() {
    }

    public static EventService getInstance() {
        return INSTANCE;
    }

    public void publish(Event event) {

        Map<Subscriber, List<Filter>> subscription = subscriptionMap.get(event.getClass());

        if (subscription != null) {
            for (Subscriber subscriber : subscription.keySet()) {
                for (Filter filter : subscription.get(subscriber)) {
                    if (filter == null || filter.apply(event)) {
                        subscriber.inform(event);
                    }
                }
            }
        }
    }

    public void subscribe(Class<? extends Event> eventType, Filter filter, Subscriber subscriber) {

        if (!subscriptionMap.containsKey(eventType)) {
            subscriptionMap.put(eventType, new HashMap<>());
        }

        Map<Subscriber, List<Filter>> subscriberListMap = subscriptionMap.get(eventType);

        if (!subscriberListMap.containsKey(subscriber)) {
            subscriberListMap.put(subscriber, new ArrayList<>());
        }

        if (!subscriberListMap.get(subscriber).contains(filter)) {
            subscriberListMap.get(subscriber).add(filter);
        }
    }

    public void unsubscribe(Subscriber subscriber) {
        for (Map<Subscriber, List<Filter>> subscriberListMap : subscriptionMap.values()) {
            subscriberListMap.remove(subscriber);
        }
    }

}