package com.lan.app.domain.model;

public record EventNotification(
    Id Id,
    Integer leadHours,
    String message,
    Boolean active
) {
}
