package com.lan.app.infrastructure.baserow.exception;

import java.util.Map;

public class BaserowUnavailableException extends BaserowException {

    public BaserowUnavailableException(String message, Throwable cause) {
        super(message, Map.of());
        initCause(cause);
    }
}