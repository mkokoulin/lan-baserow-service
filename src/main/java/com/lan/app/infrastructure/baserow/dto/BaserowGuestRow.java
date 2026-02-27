package com.lan.app.infrastructure.baserow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BaserowGuestRow(
    @NotNull @JsonProperty("id") Integer id,
    @NotNull @NotBlank @JsonProperty("external_id") UUID externalId,
    @NotNull @NotBlank @JsonProperty("first_name") String firstName,
    @NotNull @NotBlank @JsonProperty("last_name") String lastName,
    @NotNull @NotBlank @JsonProperty("telegram") String telegram,
    @NotNull @NotBlank @JsonProperty("phone") String phone,
    @JsonProperty("source") BaserowSingleSelect source
) {}
