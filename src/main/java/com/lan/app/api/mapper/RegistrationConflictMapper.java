package com.lan.app.api.mapper;

import com.lan.app.domain.exception.RegistrationConflictException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

@Provider
public class RegistrationConflictMapper implements ExceptionMapper<RegistrationConflictException> {
    @Override public Response toResponse(RegistrationConflictException e) {
        return Response.status(Response.Status.CONFLICT).entity(Map.of("message", e.getMessage())).build();
    }
}
