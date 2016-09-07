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
package io.hakbot.controller.resources.v1;

import io.hakbot.util.JsonUtil;
import org.junit.Assert;
import org.junit.Test;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class PublishersResourceTest extends BaseResourceTest {

    @Test
    public void testPublishersUnauthorized() {
        final Response response = target(Target.PUBLISHERS.target).request().get();
        Assert.assertEquals(401, response.getStatus());
    }

    @Test
    public void testPublishers() {
        boolean matched = false;
        JsonArray jsonResponse = JsonUtil.toJsonArray(target(Target.PUBLISHERS.target)
                .request()
                .header(HEADER_API_KEY_NAME, HEADER_API_KEY_VALUE)
                .get(String.class));
        for (JsonObject jsonObject : jsonResponse.getValuesAs(JsonObject.class)) {
            if ("io.hakbot.publishers.filesystem.FileSystemPublisher".equals(jsonObject.getString("class"))) {
                matched = true;
                Assert.assertEquals("File System", jsonObject.getString("name"));
                Assert.assertEquals("Publishes results to the file system.", jsonObject.getString("description"));
            }
        }
        Assert.assertEquals(true, matched);
        Assert.assertTrue(jsonResponse.size() > 1);
    }

}
