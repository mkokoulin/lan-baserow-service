package com.lan.app.api.dto.response;

import java.time.Instant;
import java.util.UUID;

public record CoworkingNotificationResponse (
    UUID id,
    String message,
    Instant sentAt
) {}
