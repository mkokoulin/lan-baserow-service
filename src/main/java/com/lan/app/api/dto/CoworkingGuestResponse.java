package com.lan.app.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record CoworkingGuestResponse(
    @NotNull @NotBlank @JsonProperty("id") UUID id,
    @NotNull @NotBlank @JsonProperty("firstName") String firstName,
    @NotNull @NotBlank @JsonProperty("lastName") String lastName,
    @NotNull @NotBlank @JsonProperty("telegram") String telegram,
    @NotNull @NotBlank @JsonProperty("phone") String phone
) {
}
