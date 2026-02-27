package com.lan.app.service.error;

import com.lan.app.domain.exception.ExternalServiceException;

public class BaserowBadRequestException extends ExternalServiceException {
    public BaserowBadRequestException(String message, Throwable cause) { super(message, cause); }
}