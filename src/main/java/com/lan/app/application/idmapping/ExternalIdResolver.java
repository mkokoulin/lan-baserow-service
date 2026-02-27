package com.lan.app.application.idmapping;

import java.util.UUID;

public interface ExternalIdResolver {
    UUID toExternal(EntityType type, int internalId);
}