package com.lan.app.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record EventResponse(
    @JsonProperty("id") @NotNull @NotBlank UUID id,
    @JsonProperty("name") @NotNull @NotBlank String name,
    @JsonProperty("dateStart") @NotNull @NotBlank String dateStart,
    @JsonProperty("dateEnd") @NotNull @NotBlank String dateEnd,
    @JsonProperty("description") String description,
    @JsonProperty("externalRegistrationUrl") String externalRegistrationUrl,
    @JsonProperty("registrationUrl") String registrationUrl,
    @JsonProperty("instagramUrl") String instagramUrl,
    @JsonProperty("type") String type,
    @JsonProperty("showForm") @NotBlank boolean showForm,
    @JsonProperty("notifications") List<String> notifications,
    @JsonProperty("comment") String comment,
    @JsonProperty("showEvent") @NotBlank List<String> showEvent
) {
}
