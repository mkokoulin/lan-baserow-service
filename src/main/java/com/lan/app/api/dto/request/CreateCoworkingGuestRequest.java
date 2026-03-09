package com.lan.app.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateCoworkingGuestRequest(
    @NotNull @NotBlank @JsonProperty("firstName") String firstName,
    @NotNull @NotBlank @JsonProperty("lastName") String lastName,
    @NotNull @NotBlank @JsonProperty("telegram") String telegram,
    @NotNull @NotBlank @JsonProperty("phone") String phone
) {}