package com.lan.app.api.dto.response;

public record EventRegistrationResponse(
    String id,
    String eventId,
    String guestId,
    String guestComment,
    int guestCount
) {
}
