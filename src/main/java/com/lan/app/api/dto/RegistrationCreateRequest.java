package com.lan.app.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record RegistrationCreateRequest(
    @JsonProperty("eventId") @NotNull UUID eventId,
    @JsonProperty("guestId") @NotNull UUID guestId,
    @JsonProperty("guestComment") String guestComment,
    @JsonProperty("guestCount") @Min(1) int guestCount,
    @JsonProperty("source") String source
) {
}
