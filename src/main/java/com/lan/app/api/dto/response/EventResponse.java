package com.lan.app.api.dto.response;

import java.util.List;
import java.util.UUID;

public record EventResponse(
    UUID id,
    String name,
    String dateStart,
    String dateEnd,
    String description,
    String externalRegistrationUrl,
    String registrationUrl,
    String instagramUrl,
    String type,
    boolean showForm,
    List<String> notifications,
    String comment,
    List<String> showEvent
) {
}
