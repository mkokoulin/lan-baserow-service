package com.lan.app.api.error;

import com.lan.app.domain.exception.ExternalServiceException;
import com.lan.app.service.error.BaserowBadRequestException;
import com.lan.app.service.error.BaserowUnauthorizedException;
import com.lan.app.service.error.BaserowUnavailableException;
import jakarta.ws.rs.core.*;
import jakarta.ws.rs.ext.*;

import java.util.Map;

@Provider
public class ExternalServiceExceptionMapper implements ExceptionMapper<ExternalServiceException> {

    @Override
    public Response toResponse(ExternalServiceException ex) {
        if (ex instanceof BaserowBadRequestException) {
            return Response.status(Response.Status.BAD_GATEWAY)
                .entity(new ApiError("BASEROW_BAD_REQUEST", "Upstream rejected our request", null, Map.of("upstream", ex.getMessage())))
                .type(MediaType.APPLICATION_JSON)
                .build();
        }

        if (ex instanceof BaserowUnauthorizedException) {
            return Response.status(Response.Status.BAD_GATEWAY)
                .entity(new ApiError("BASEROW_AUTH_ERROR", "Upstream auth error", null, Map.of("upstream", ex.getMessage())))
                .type(MediaType.APPLICATION_JSON)
                .build();
        }

        if (ex instanceof BaserowUnavailableException) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE)
                .entity(new ApiError("BASEROW_UNAVAILABLE", "Upstream temporarily unavailable", null, Map.of("upstream", ex.getMessage())))
                .type(MediaType.APPLICATION_JSON)
                .build();
        }

        return Response.status(Response.Status.BAD_GATEWAY)
            .entity(new ApiError("UPSTREAM_ERROR", "External service error", null, Map.of("upstream", ex.getMessage())))
            .type(MediaType.APPLICATION_JSON)
            .build();
    }
}
