package com.lan.app.domain.exception;

import java.util.Map;
import java.util.UUID;

public class ResourceNotFoundException extends AppException {

    public ResourceNotFoundException(String resourceName, UUID externalId) {
        super(
            resourceName + " not found.",
            Map.of("externalId", externalId.toString())
        );
    }
}
