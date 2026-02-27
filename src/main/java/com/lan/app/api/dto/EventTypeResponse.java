package com.lan.app.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EventTypeResponse(
    @JsonProperty("id") @NotNull int id,
    @JsonProperty("value") @NotBlank @NotBlank String value,
    @JsonProperty("color") String color
) {
}
