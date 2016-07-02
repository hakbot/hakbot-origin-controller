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

import java.io.IOException;
import java.io.InputStream;

// Adapted from http://stackoverflow.com/questions/132052/servlet-for-serving-static-content
interface StaticResource {

    /**
     * Returns the file name of the resource. This must be unique across all static resources. If any, the file
     * extension will be used to determine the content type being set. If the container doesn't recognize the
     * extension, then you can always register it as <code>&lt;mime-type&gt;</code> in <code>web.xml</code>.
     * @return The file name of the resource.
     */
    String getFileName();

    /**
     * Returns the last modified timestamp of the resource in milliseconds.
     * @return The last modified timestamp of the resource in milliseconds.
     */
    long getLastModified();

    /**
     * Returns the content length of the resource. This returns <code>-1</code> if the content length is unknown.
     * In that case, the container will automatically switch to chunked encoding if the response is already
     * committed after streaming. The file download progress may be unknown.
     * @return The content length of the resource.
     */
    long getContentLength();

    /**
     * Returns the input stream with the content of the resource. This method will be called only once by the
     * servlet, and only when the resource actually needs to be streamed, so lazy loading is not necessary.
     * @return The input stream with the content of the resource.
     * @throws IOException When something fails at I/O level.
     */
    InputStream getInputStream() throws IOException;

}
