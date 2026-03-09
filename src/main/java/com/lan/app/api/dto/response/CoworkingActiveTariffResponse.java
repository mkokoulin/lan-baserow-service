package com.lan.app.api.dto.response;

import java.time.Instant;
import java.util.UUID;

public record CoworkingActiveTariffResponse(
    UUID id,
    UUID guestId,
    Integer daysUsed,
    Instant dateStart,
    Instant dateEnd,
    UUID tariffId
) {
}
