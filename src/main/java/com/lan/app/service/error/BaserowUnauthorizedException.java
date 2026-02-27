package com.lan.app.service.error;

import com.lan.app.domain.exception.ExternalServiceException;

public class BaserowUnauthorizedException extends ExternalServiceException {
    public BaserowUnauthorizedException(String message, Throwable cause) { super(message, cause); }
}