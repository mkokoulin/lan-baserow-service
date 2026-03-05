package com.lan.app.domain;

public record EventNotification(
    Id Id,
    Integer leadHours,
    String message,
    Boolean active
) {
}
