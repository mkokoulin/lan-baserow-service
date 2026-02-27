package com.lan.app.application.idmapping;

import java.util.UUID;

public interface InternalIdResolver {
    int toInternal(EntityType type, UUID externalId);
}
