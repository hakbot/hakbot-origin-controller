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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

// Adapted from http://stackoverflow.com/questions/132052/servlet-for-serving-static-content
public abstract class StaticResourceServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final long ONE_SECOND_IN_MILLIS = TimeUnit.SECONDS.toMillis(1);
    private static final String ETAG_HEADER = "W/\"%s-%s\"";
    private static final String CONTENT_DISPOSITION_HEADER = "inline;filename=\"%1$s\"; filename*=UTF-8''%1$s";
    private static final long DEFAULT_EXPIRE_TIME_IN_MILLIS = TimeUnit.DAYS.toMillis(30);
    private static final int DEFAULT_STREAM_BUFFER_SIZE = 102400;

    @Override
    protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        doRequest(request, response, true);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doRequest(request, response, false);
    }

    private void doRequest(HttpServletRequest request, HttpServletResponse response, boolean head) throws IOException {
        response.reset();
        StaticResource resource;

        try {
            resource = getStaticResource(request);
        }
        catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        if (resource == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String fileName = URLEncoder.encode(resource.getFileName(), StandardCharsets.UTF_8.name());
        boolean notModified = setCacheHeaders(request, response, fileName, resource.getLastModified());

        if (notModified) {
            response.sendError(HttpServletResponse.SC_NOT_MODIFIED);
            return;
        }

        setContentHeaders(response, fileName, resource.getContentLength());

        if (head) {
            return;
        }

        writeContent(response, resource);
    }

    /**
     * Returns the static resource associated with the given HTTP servlet request. This returns <code>null</code> when
     * the resource does actually not exist. The servlet will then return a HTTP 404 error.
     * @param request The involved HTTP servlet request.
     * @return The static resource associated with the given HTTP servlet request.
     * @throws IllegalArgumentException When the request is mangled in such way that it's not recognizable as a valid
     * static resource request. The servlet will then return a HTTP 400 error.
     */
    protected abstract StaticResource getStaticResource(HttpServletRequest request) throws IllegalArgumentException;

    private boolean setCacheHeaders(HttpServletRequest request, HttpServletResponse response, String fileName, long lastModified) {
        String eTag = String.format(ETAG_HEADER, fileName, lastModified);
        response.setHeader("ETag", eTag);
        response.setDateHeader("Last-Modified", lastModified);
        response.setDateHeader("Expires", System.currentTimeMillis() + DEFAULT_EXPIRE_TIME_IN_MILLIS);
        return notModified(request, eTag, lastModified);
    }

    private boolean notModified(HttpServletRequest request, String eTag, long lastModified) {
        String ifNoneMatch = request.getHeader("If-None-Match");

        if (ifNoneMatch != null) {
            String[] matches = ifNoneMatch.split("\\s*,\\s*");
            Arrays.sort(matches);
            return (Arrays.binarySearch(matches, eTag) > -1 || Arrays.binarySearch(matches, "*") > -1);
        }
        else {
            long ifModifiedSince = request.getDateHeader("If-Modified-Since");
            return (ifModifiedSince + ONE_SECOND_IN_MILLIS > lastModified); // That second is because the header is in seconds, not millis.
        }
    }

    private void setContentHeaders(HttpServletResponse response, String fileName, long contentLength) {
        response.setHeader("Content-Type", getServletContext().getMimeType(fileName));
        response.setHeader("Content-Disposition", String.format(CONTENT_DISPOSITION_HEADER, fileName));

        if (contentLength != -1) {
            response.setHeader("Content-Length", String.valueOf(contentLength));
        }
    }

    private void writeContent(HttpServletResponse response, StaticResource resource) throws IOException {
        try (
                ReadableByteChannel inputChannel = Channels.newChannel(resource.getInputStream());
                WritableByteChannel outputChannel = Channels.newChannel(response.getOutputStream())
        ) {
            ByteBuffer buffer = ByteBuffer.allocateDirect(DEFAULT_STREAM_BUFFER_SIZE);
            long size = 0;

            while (inputChannel.read(buffer) != -1) {
                buffer.flip();
                size += outputChannel.write(buffer);
                buffer.clear();
            }

            if (resource.getContentLength() == -1 && !response.isCommitted()) {
                response.setHeader("Content-Length", String.valueOf(size));
            }
        }
    }

}
