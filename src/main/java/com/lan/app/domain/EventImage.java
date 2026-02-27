package com.lan.app.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EventImage(
    @NotNull @NotBlank String url
) {
}

