package com.lan.app.domain;

import java.util.UUID;

public record Guest(
    UUID id,
    String firstName,
    String lastName,
    String telegram,
    String phone,
    String source
) {
}
