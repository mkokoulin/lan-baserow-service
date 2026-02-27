package com.lan.app.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EventImageResponse(
    @JsonProperty("url") @NotNull @NotBlank String url
) {
}
