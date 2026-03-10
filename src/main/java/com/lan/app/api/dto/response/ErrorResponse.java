package com.lan.app.api.dto.response;

import java.util.Map;

public record ErrorResponse(
    String code,
    String message,
    Map<String, Object> details
) {
}
