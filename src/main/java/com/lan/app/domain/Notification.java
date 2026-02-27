package com.lan.app.domain;

//import java.util.UUID;

public record Notification(
    Id Id,
    Integer leadHours,
    String message,
    Boolean active
) {
}
