package com.lan.app.api.error;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;

import java.util.Map;
import java.util.stream.Collectors;

@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException ex) {
        var details = ex.getConstraintViolations().stream()
            .collect(Collectors.toMap(
                v -> v.getPropertyPath().toString(),
                v -> v.getMessage(),
                (a, b) -> a
            ));

        return Response.status(Response.Status.BAD_REQUEST)
            .entity(new ApiError("VALIDATION_ERROR", "Request validation failed", null, Map.copyOf(details)))
            .type(MediaType.APPLICATION_JSON)
            .build();
    }
}
