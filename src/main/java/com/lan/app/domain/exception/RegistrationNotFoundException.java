package com.lan.app.domain.exception;

public class RegistrationNotFoundException extends RuntimeException {
    public RegistrationNotFoundException(String id) {
        super("Registration not found: " + id);
    }
}