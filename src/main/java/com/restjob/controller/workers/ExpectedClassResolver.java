/*
 * This file is part of RESTjob Controller.
 *
 * RESTjob Controller is free software: you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 *
 * RESTjob Controller is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * RESTjob Controller. If not, see http://www.gnu.org/licenses/.
 */
package com.restjob.controller.workers;

import com.restjob.controller.Config;
import com.restjob.controller.ConfigItem;
import com.restjob.controller.model.Job;

import java.util.ArrayList;
import java.util.List;

public class ExpectedClassResolver {

    private static List<String> expectedClasses = new ArrayList<>();
    static {
        String[] classes = Config.getInstance().getProperty(ConfigItem.PROVIDERS_ENALBED).split(",");
        for (String clazz : classes) {
            expectedClasses.add(clazz.trim());
        }
    }

    /**
     * Resolves the Class for the specified Job's provider. The provider needs to be whitelisted
     * in order to be resolved. If provider is not whitelisted, an ExpectedClassResolverException
     * is thrown.
     */
    public Class resolveClass(Job job) throws ClassNotFoundException, ExpectedClassResolverException {
        if (expectedClasses.contains(job.getProvider())) {
            return Class.forName(job.getProvider(), false, this.getClass().getClassLoader());
        } else {
            throw new ExpectedClassResolverException();
        }
    }

}
