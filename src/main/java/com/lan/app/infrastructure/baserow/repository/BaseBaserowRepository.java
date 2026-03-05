package com.lan.app.infrastructure.baserow.repository;

import com.lan.app.domain.exception.*;
import jakarta.ws.rs.WebApplicationException;
import org.jboss.logging.Logger;

public abstract class BaseBaserowRepository {

    private final Logger log;
    private final int tableId;
    private final String repoName;

    protected BaseBaserowRepository(Logger log, int tableId, String repoName) {
        this.log = log;
        this.tableId = tableId;
        this.repoName = repoName;
    }

    protected RuntimeException mapHttpException(String op, WebApplicationException e, String ctx) {
        int status = e.getResponse() != null ? e.getResponse().getStatus() : 0;

        log.errorf(e, "Baserow HTTP error. repo=%s op=%s tableId=%d status=%d %s",
            repoName, op, tableId, status, ctx
        );

        return switch (status) {
            case 401, 403 -> new ExternalServiceUnauthorizedException("External service auth error", e);
            case 409 -> new RegistrationConflictException("Conflict");
            case 429 -> new ExternalServiceRateLimitedException("External service rate limited", e);
            default -> new ExternalServiceUnavailableException("External service error (status " + status + ")", e);
        };
    }
}

