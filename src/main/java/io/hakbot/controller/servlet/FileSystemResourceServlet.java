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
package io.hakbot.controller.servlet;

import io.hakbot.controller.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

// Adapted from http://stackoverflow.com/questions/132052/servlet-for-serving-static-content
public class FileSystemResourceServlet extends StaticResourceServlet {

    private static final Logger logger = Logger.getLogger(FileSystemResourceServlet.class);


    @Override
    protected StaticResource getStaticResource(HttpServletRequest request) throws IllegalArgumentException {

        String pathInfo = request.getPathInfo();

        if (pathInfo == null || pathInfo.isEmpty() || "/".equals(pathInfo)) {
            throw new IllegalArgumentException();
        }

        if (pathInfo.endsWith(".jsp") || pathInfo.endsWith(".jspx")) {
            return null;
        }

        ServletContext context = request.getServletContext();
        String pluginPath = context.getRealPath("/WEB-INF/plugins/");

        String name = "";
        try {
            name = URLDecoder.decode(pathInfo.substring(1), StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
        final File file = new File(pluginPath, name);

        return !file.exists() ? null : new StaticResource() {
            @Override
            public long getLastModified() {
                return file.lastModified();
            }
            @Override
            public InputStream getInputStream() throws IOException {
                return new FileInputStream(file);
            }
            @Override
            public String getFileName() {
                return file.getName();
            }
            @Override
            public long getContentLength() {
                return file.length();
            }
        };
    }

}