package com.lan.app.infrastructure.baserow.exception;

import java.util.Map;
import java.util.UUID;

public class BaserowNotFoundException extends BaserowException {

    public BaserowNotFoundException(String entityName, UUID externalId) {
        super(
            entityName + " not found in Baserow.",
            Map.of("externalId", externalId.toString())
        );
    }
}