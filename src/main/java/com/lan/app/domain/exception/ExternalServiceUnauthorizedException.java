package com.lan.app.domain.exception;

public class ExternalServiceUnauthorizedException extends RuntimeException {
    public ExternalServiceUnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }
}