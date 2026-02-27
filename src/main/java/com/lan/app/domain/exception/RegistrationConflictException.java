package com.lan.app.domain.exception;

public class RegistrationConflictException extends RuntimeException {
    public RegistrationConflictException(String message) {
        super(message);
    }
}
