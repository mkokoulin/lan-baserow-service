package com.lan.app.domain.exception;

import java.util.Map;

public abstract class AppException extends RuntimeException {

    private final Map<String, Object> details;

    protected AppException(String message) {
        super(message);
        this.details = Map.of();
    }

    protected AppException(String message, Map<String, Object> details) {
        super(message);
        this.details = details == null ? Map.of() : Map.copyOf(details);
    }

    public Map<String, Object> details() {
        return details;
    }
}
