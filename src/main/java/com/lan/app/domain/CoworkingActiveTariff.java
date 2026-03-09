package com.lan.app.domain;

import java.time.Instant;
import java.util.UUID;

public record CoworkingActiveTariff(
    UUID id,
    UUID guestId,
    Integer daysUsed,
    Instant dateStart,
    Instant dateEnd,
    UUID tariffId
) {}
