package com.lan.app.infrastructure.baserow.exception;

import com.lan.app.domain.exception.ResourceNotFoundException;

import java.util.UUID;

public class BaserowNotFoundException extends ResourceNotFoundException {

    public BaserowNotFoundException(String entityName, UUID externalId) {
        super(
            entityName + " not found in Baserow.",
            externalId
        );
    }
}