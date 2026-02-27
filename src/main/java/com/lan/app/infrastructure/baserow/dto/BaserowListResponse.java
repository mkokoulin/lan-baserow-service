package com.lan.app.infrastructure.baserow.dto;

import java.util.List;

public record BaserowListResponse<T>(
       int count,
       String next,
       String previous,
       List<T> results
) {
}
