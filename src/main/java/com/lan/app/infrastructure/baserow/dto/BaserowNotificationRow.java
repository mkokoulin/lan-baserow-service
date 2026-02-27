package com.lan.app.infrastructure.baserow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BaserowNotificationRow(
    @NotNull @JsonProperty("id") Integer id,
    @NotNull @NotBlank @JsonProperty("external_id") UUID externalId,
    @NotNull @JsonProperty("lead_hours") Integer leadHours,
    @NotNull @JsonProperty("message") String message,
    @NotBlank @JsonProperty("active") boolean active
) {}
