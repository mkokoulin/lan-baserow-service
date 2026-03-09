package com.lan.app.api.dto.response;

import java.util.UUID;

public record CoworkingGuestResponse(
    UUID id,
    String firstName,
    String lastName,
    String telegram,
    String phone
) {
}
