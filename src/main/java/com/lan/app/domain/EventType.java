package com.lan.app.domain;

public enum EventType {
    EVENT,
    FESTIVAL;

    public static EventType fromBaserow(String raw) {
        if (raw == null || raw.isBlank()) {
            throw new IllegalArgumentException("type is empty");
        }

        return switch (raw.trim().toLowerCase()) {
            case "event" -> EVENT;
            case "festival" -> FESTIVAL;
            default -> throw new IllegalArgumentException("Unknown type: " + raw);
        };
    }
}
