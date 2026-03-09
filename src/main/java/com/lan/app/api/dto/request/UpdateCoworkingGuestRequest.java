package com.lan.app.api.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateCoworkingGuestRequest(
    @NotNull @NotBlank String firstName,
    @NotNull @NotBlank String lastName,
    @NotNull @NotBlank String phone,
    String telegram
) {
}
