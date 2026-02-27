package com.lan.app.api.mapper;

import com.lan.app.domain.exception.ExternalServiceRateLimitedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

@Provider
public class ExternalRateLimitMapper implements ExceptionMapper<ExternalServiceRateLimitedException> {
    @Override public Response toResponse(ExternalServiceRateLimitedException e) {
        return Response.status(429).entity(Map.of("message", e.getMessage())).build();
    }
}