package com.lan.app.domain.exception;

public class CorruptedDataException extends RuntimeException {
    public CorruptedDataException(String message) {
        super(message);
    }

    public CorruptedDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
