package com.lan.app.domain.exception;

public class GuestNotFoundException extends RuntimeException {
    public GuestNotFoundException(String id) {
        super("Guest not found: " + id);
    }
}
