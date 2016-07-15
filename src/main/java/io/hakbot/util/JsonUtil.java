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

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

/**
 * The JsonUtil class contains convenience methods for parsing JSON
 */
public class JsonUtil {

    private JsonUtil() { }

    /**
     * Creates a JsonObject (a Map implementation) from a decoded payload
     */
    public static JsonObject toJsonObject(String decodedPayload) {
        JsonReader jsonReader = Json.createReader(new StringReader(decodedPayload));
        return jsonReader.readObject();
    }

    /**
     * Returns the value for the specified parameter name included in the specified json object. If json
     * object is null or specified name cannot be found, method returns null.
     */
    public static String getString(JsonObject json, String name) {
        return (json != null && json.containsKey(name)) ? json.getString(name) : null;
    }

    /**
     * Returns the value for the specified parameter name included in the specified json object. If json
     * object is null or specified name cannot be found, method returns null.
     */
    public static boolean getBoolean(JsonObject json, String name) {
        return (json != null && json.containsKey(name)) && json.getBoolean(name);
    }

    /**
     * Returns the value for the specified parameter name included in the specified json object. If json
     * object is null or specified name cannot be found, method returns -1.
     */
    public static int getInt(JsonObject json, String name) {
        return (json != null && json.containsKey(name)) ? json.getInt(name) : -1 ;
    }

    /**
     * Returns the value for the specified parameter name included in the specified json object. If json
     * object is null or specified name cannot be found, method returns the specified default value.
     */
    public static int getInt(JsonObject json, String name, int defaultValue) {
        return (json != null && json.containsKey(name)) ? json.getInt(name, defaultValue) : defaultValue ;
    }

    /**
     * Returns a json object that is a child of the specified parent object with the specified name. If parent
     * is null or the specified name cannot be found, method will return null.
     */
    public static JsonObject getJsonChildObject(JsonObject parent, String name) {
        return (parent != null && parent.containsKey(name)) ? parent.getJsonObject(name) : null;
    }

    /**
     * Returns a string representation of a json object, or null if json object is null.
     */
    public static String toJsonString(JsonObject json) {
        return json != null ? json.toString() : null;
    }

    /**
     * Checks if the parameters specified (which are required) are used as
     * keys in the specified map
     */
    public static boolean requiredParams(JsonObject map, String... params) {
        for (String param: params) {
            if (!map.containsKey(param)) {
                return false;
            }
        }
        return true;
    }

}
