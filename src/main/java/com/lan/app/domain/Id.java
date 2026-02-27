package com.lan.app.domain;

import java.util.UUID;

public record Id(Integer internalId, UUID externalId) {
}
