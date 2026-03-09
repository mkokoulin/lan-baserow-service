package com.lan.app.infrastructure.baserow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record BaserowCoworkingNotificationRow(
    @NotNull @JsonProperty("external_id") UUID externalId,
    @NotNull @JsonProperty("message") String message,
    @NotNull @JsonProperty("sent_at") Instant sentAt
    ) {
}
