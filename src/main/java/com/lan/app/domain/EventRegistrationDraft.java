package com.lan.app.domain;

import java.util.UUID;

public record EventRegistrationDraft(
    UUID eventId,
    UUID guestId,
    Integer guestCount,
    String comment,
    String source
) {
}
