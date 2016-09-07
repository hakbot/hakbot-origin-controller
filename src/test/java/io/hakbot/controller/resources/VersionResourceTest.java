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
package io.hakbot.controller.resources;

import io.hakbot.controller.resources.v1.BaseResourceTest;
import io.hakbot.util.JsonUtil;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.Assert;
import org.junit.Test;
import javax.json.JsonObject;
import javax.ws.rs.core.Application;

public class VersionResourceTest extends BaseResourceTest {

    @Override
    protected Application configure() {
        return new ResourceConfig(
                configureClasses()
        );
    }

    @Test
    public void testVersion() {
        JsonObject jsonResponse = JsonUtil.toJsonObject(target(Target.VERSION.target)
                .request()
                .get(String.class));
        Assert.assertEquals("Hakbot Origin Controller", jsonResponse.getString("application"));
        Assert.assertTrue(jsonResponse.getString("version").startsWith("1.0"));
    }

}
