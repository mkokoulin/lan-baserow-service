package com.lan.app.api.dto;

public record UpdateCoworkingGuestRequest(
    String firstName,
    String lastName,
    String phone,
    String telegram
) {
}
