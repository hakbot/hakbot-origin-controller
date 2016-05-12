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
package io.hakbot.util;

import java.util.HashMap;
import java.util.Map;


public class PayloadUtil {

    private PayloadUtil() { }

    /**
     * Creates a Map of key/value pairs (parameters) from a decoded payload
     * String. Pairs are delineated by a double colon (::)
     */
    public static Map<String, String> toParameters(String decodedPayload) {
        return toParameters(decodedPayload, "::");
    }

    /**
     * Creates a Map of key/value pairs (parameters) from a decoded payload
     * String.
     */
    public static Map<String, String> toParameters(String decodedPayload, String delineator) {
        Map<String, String> map = new HashMap<>();
        String[] parts = decodedPayload.split(delineator);
        for (String part: parts) {
            if (part.contains("=")) {
                String[] pair = part.split("=");
                map.put(pair[0], pair[1]);
            } else {
                map.put(part, null);
            }
        }
        return map;
    }

    /**
     * Checks if the parameters specified (which are required) are used as
     * keys in the specified map
     */
    public static boolean requiredParams(Map<String, String> map, String... params) {
        for (String param: params) {
            if (!map.containsKey(param)) {
                return false;
            }
        }
        return true;
    }

}
