package com.lan.app.api.error;

import java.util.Map;

public record ApiError(
    String code,
    String message,
    String correlationId,
    Map<String, Object> details
) {}
