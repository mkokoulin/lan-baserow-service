package com.lan.app.domain;

//import java.util.UUID;

public record Registration(
    Id id,
//    UUID externalId,
    Id eventId,
    Id guestId,
    Integer guestCount,
    String comment,
    String source
) {
}
