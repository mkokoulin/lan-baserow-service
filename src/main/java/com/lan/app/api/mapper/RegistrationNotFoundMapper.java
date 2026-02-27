package com.lan.app.api.mapper;

import com.lan.app.domain.exception.RegistrationNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.Map;

@Provider
public class RegistrationNotFoundMapper implements ExceptionMapper<RegistrationNotFoundException> {

    @Override
    public Response toResponse(RegistrationNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND)
            .entity(Map.of("message", e.getMessage()))
            .build();
    }
}