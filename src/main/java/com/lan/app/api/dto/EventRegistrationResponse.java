package com.lan.app.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EventRegistrationResponse(
    @NotNull @NotBlank @JsonProperty("id") String id,
    @NotNull @NotBlank @JsonProperty("eventId") String eventId,
    @NotNull @NotBlank @JsonProperty("guestId") String guestId,
    @NotNull @NotBlank @JsonProperty("guestComment") String guestComment,
    @Min(1) @JsonProperty("guestCount") int guestCount
) {
}
