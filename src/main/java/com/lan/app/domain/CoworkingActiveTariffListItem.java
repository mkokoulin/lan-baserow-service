package com.lan.app.domain;

import java.time.Instant;
import java.util.UUID;

public record CoworkingActiveTariffListItem(
    UUID externalId,
    Integer daysUsed,
    Instant dateStart,
    Instant dateEnd
) {
}
