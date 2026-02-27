package com.lan.app.service.error;

import com.lan.app.domain.exception.ExternalServiceException;

public class BaserowUnavailableException extends ExternalServiceException {
    public BaserowUnavailableException(String message, Throwable cause) { super(message, cause); }
}