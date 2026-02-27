package com.lan.app.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NotificationResponse(
    @NotNull @NotBlank @JsonProperty("id") String id,
    @NotNull @JsonProperty("leadHours") Integer leadHours,
    @NotNull @NotBlank @JsonProperty("message") String message,
    @NotBlank @JsonProperty("active") boolean active
) {
}
