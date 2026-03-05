    package com.lan.app.domain;

public enum EventClient {
    LAN_SITE,
    LAN_BOT;

    public static EventClient fromBaserow(String raw) {
        if (raw == null || raw.isBlank()) {
            throw new IllegalArgumentException("type is empty");
        }

        return switch (raw.trim().toLowerCase()) {
            case "lan_site" -> LAN_SITE;
            case "lan_bot" -> LAN_BOT;
            default -> throw new IllegalArgumentException("Unknown type: " + raw);
        };
    }
}
