package com.lan.app.domain.exception;

import java.util.Map;

public class BusinessConflictException extends AppException {

    public BusinessConflictException(String message) {
        super(message);
    }

    public BusinessConflictException(String message, Map<String, Object> details) {
        super(message, details);
    }
}
