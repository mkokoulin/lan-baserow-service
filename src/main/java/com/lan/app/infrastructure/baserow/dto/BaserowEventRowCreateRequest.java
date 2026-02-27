package com.lan.app.infrastructure.baserow.dto;

public record BaserowEventRowCreateRequest(
    String name,
    String description,
    String date_start
) {}
