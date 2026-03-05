package com.lan.app.domain;

import java.util.UUID;

public record CoworkingGuest(
    UUID externalId,
    String firstName,
    String lastName,
    String telegram,
    String phone
) {
}
