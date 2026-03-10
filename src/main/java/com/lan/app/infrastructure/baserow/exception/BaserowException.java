package com.lan.app.infrastructure.baserow.exception;

import com.lan.app.domain.exception.AppException;

import java.util.Map;

public abstract class BaserowException extends AppException {

    protected BaserowException(String message, Map<String, Object> details) {
        super(message, details);
    }

    protected BaserowException(String message) {
        super(message);
    }
}