package com.lan.app.api.mapper;

import com.lan.app.domain.exception.ExternalServiceUnauthorizedException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;


@Provider
public class ExternalUnauthorizedMapper implements ExceptionMapper<ExternalServiceUnauthorizedException> {
    @Override public Response toResponse(ExternalServiceUnauthorizedException e) {
        return Response.status(Response.Status.UNAUTHORIZED).entity(Map.of("message", e.getMessage())).build();
    }
}
