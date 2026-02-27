package com.lan.app.domain.exception;

public class ExternalServiceRateLimitedException extends RuntimeException {
    public ExternalServiceRateLimitedException(String message, Throwable cause) {
        super(message, cause);
    }
}