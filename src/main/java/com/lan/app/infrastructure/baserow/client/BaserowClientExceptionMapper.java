package com.lan.app.infrastructure.baserow.client;

import com.lan.app.domain.exception.ExternalServiceException;
import com.lan.app.service.error.*;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class BaserowClientExceptionMapper implements ResponseExceptionMapper<RuntimeException> {

    @Override
    public RuntimeException toThrowable(Response response) {
        String body = safeReadBody(response);

        int s = response.getStatus();
        return switch (s) {
            case 400 -> new BaserowBadRequestException("Baserow rejected request: " + body, null);
            case 401, 403 -> new BaserowUnauthorizedException("Baserow auth error: " + body, null);
            case 404 -> new BaserowBadRequestException("Baserow endpoint not found: " + body, null);
            default -> {
                if (s >= 500) yield new BaserowUnavailableException("Baserow server error: " + body, null);
                yield new ExternalServiceException("Baserow error " + s + ": " + body, null);
            }
        };
    }

    @Override
    public boolean handles(int status, MultivaluedMap<String, Object> headers) {
        return status >= 400;
    }

    private String safeReadBody(Response response) {
        try {
            if (response.hasEntity()) {
                Object entity = response.getEntity();
                if (entity instanceof String s) return s;

                InputStream is = response.readEntity(InputStream.class);
                if (is == null) return "<no body>";
                return new String(is.readAllBytes(), StandardCharsets.UTF_8);
            }
            return "<no body>";
        } catch (Exception e) {
            return "<failed to read body>";
        }
    }
}

