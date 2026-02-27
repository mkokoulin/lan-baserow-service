package com.lan.app.domain.exception;

public class ExternalServiceException extends RuntimeException {
    public ExternalServiceException(String message, Throwable cause) { super(message, cause); }
}
