package com.lan.app.api.dto.response;

import java.time.Instant;
import java.util.UUID;

public record CoworkingActiveTariffListItemResponse(
    UUID id,
    Integer daysUsed,
    Instant dateStart,
    Instant dateEnd
) {
}
