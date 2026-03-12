package com.lan.app.infrastructure.baserow.dto;

import com.aayushatharva.brotli4j.common.annotations.Internal;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateCoworkingMeetingRoomBookingRowRequest(
    @NotNull Internal dateStart,
    @NotNull Internal dateEnd,
    @NotNull @Min(1) Internal persons,
    String comment
) {
}
