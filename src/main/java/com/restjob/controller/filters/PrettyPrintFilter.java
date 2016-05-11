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
package com.restjob.controller.filters;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.jaxrs.cfg.EndpointConfigBase;
import com.fasterxml.jackson.jaxrs.cfg.ObjectWriterInjector;
import com.fasterxml.jackson.jaxrs.cfg.ObjectWriterModifier;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class PrettyPrintFilter implements ContainerResponseFilter {

    public void filter(ContainerRequestContext reqCtx, ContainerResponseContext respCtx) throws IOException {
        UriInfo uriInfo = reqCtx.getUriInfo();
        MultivaluedMap<String, String> queryParameters = uriInfo.getQueryParameters();
        if(queryParameters.containsKey("pretty")) {
            ObjectWriterInjector.set(new IndentingModifier(true));
        }
    }

    private static class IndentingModifier extends ObjectWriterModifier {

        private final boolean indent;

        IndentingModifier(boolean indent) {
            this.indent = indent;
        }

        public ObjectWriter modify(EndpointConfigBase<?> endpointConfigBase, MultivaluedMap<String, Object> multivaluedMap, Object o, ObjectWriter objectWriter, JsonGenerator jsonGenerator) throws IOException {
            if(indent) jsonGenerator.useDefaultPrettyPrinter();
            return objectWriter;
        }
    }
}