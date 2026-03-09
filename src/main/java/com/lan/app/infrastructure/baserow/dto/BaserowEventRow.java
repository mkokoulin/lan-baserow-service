package com.lan.app.infrastructure.baserow.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.UUID;

public record BaserowEventRow(
    @NotNull @JsonProperty("id") Integer id,
    @NotNull @JsonProperty("external_id") UUID externalId,
    @NotNull @NotBlank @JsonProperty("name") String name,
    @NotNull @NotBlank @JsonProperty("date_start") String dateStart,
    @NotNull @NotBlank @JsonProperty("date_end") String dateEnd,
    @NotNull @NotBlank @JsonProperty("description") String description,
    @JsonProperty("image") List<BaserowFile> image,
    @JsonProperty("external_registration_url") String externalRegistrationUrl,
    @NotNull @NotBlank @JsonProperty("registration_url") String registrationUrl,
    @JsonProperty("instagram_url") String instagramUrl,
    @JsonProperty("type") BaserowSingleSelect type,
    @JsonProperty("show_form") boolean showForm,
    @JsonProperty("notification_time") List<BaserowSelectOption> notificationTime,
    @JsonProperty("comment") String comment,
    @JsonProperty("show_event") List<BaserowSelectOption> showEvent
) {}
