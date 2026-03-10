package com.lan.app.domain.model;

import java.util.UUID;

public record CoworkingGuest(
    UUID externalId,
    String firstName,
    String lastName,
    String telegram,
    String phone
) {
}
