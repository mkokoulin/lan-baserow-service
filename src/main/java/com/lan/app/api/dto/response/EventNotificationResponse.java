package com.lan.app.api.dto.response;

public record EventNotificationResponse(
    String id,
    Integer leadHours,
    String message,
    boolean active
) {
}
