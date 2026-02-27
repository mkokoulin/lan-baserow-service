package com.lan.app.api.mapper;

import com.lan.app.domain.exception.ExternalServiceUnavailableException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

@Provider
public class ExternalUnavailableMapper implements ExceptionMapper<ExternalServiceUnavailableException> {
    @Override public Response toResponse(ExternalServiceUnavailableException e) {
        return Response.status(Response.Status.SERVICE_UNAVAILABLE).entity(Map.of("message", e.getMessage())).build();
    }
}
