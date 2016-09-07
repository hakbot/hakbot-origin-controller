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

public class JobResourceTest extends BaseResourceTest {

    @Test
    public void testAllJobs() {
        JsonArray jsonResponse = JsonUtil.toJsonArray(target(Target.JOB.target)
                .request()
                .header(HEADER_API_KEY_NAME, HEADER_API_KEY_VALUE)
                .get(String.class));
        Assert.assertEquals(10, jsonResponse.size());
        for (JsonObject jsonObject : jsonResponse.getValuesAs(JsonObject.class)) {
            System.out.println(jsonObject.getString("uuid"));
        }
    }

    @Test
    public void testJobByUuid() {
        JsonObject jsonResponse = JsonUtil.toJsonObject(target(Target.JOB.target)
                .path("00000000-0000-0000-0000-000000000001")
                .request()
                .header(HEADER_API_KEY_NAME, HEADER_API_KEY_VALUE)
                .get(String.class));
        Assert.assertEquals("00000000-0000-0000-0000-000000000001", jsonResponse.getString("uuid"));
        Assert.assertEquals("Job 1", jsonResponse.getString("name"));
        Assert.assertEquals("io.hakbot.providers.shell.ShellProvider", jsonResponse.getString("provider"));
        Assert.assertTrue(jsonResponse.getInt("created") > 0);
        Assert.assertEquals("CREATED", jsonResponse.getString("state"));
        Assert.assertEquals(false, jsonResponse.getBoolean("success"));
        Assert.assertTrue(jsonResponse.getInt("startedByApiKeyId") > 0);
    }

}
