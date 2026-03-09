package com.lan.app.api.dto.response;

import java.util.UUID;

public record CoworkingTariffResponse(
    UUID id,
    String name,
    Integer price,
    String meetingRoom,
    boolean fixedDesk,
    boolean filterCoffeeAndTea,
    boolean printoutScan,
    boolean luggageStorage
) {
}
