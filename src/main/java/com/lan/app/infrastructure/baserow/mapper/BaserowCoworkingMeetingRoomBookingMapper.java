package com.lan.app.infrastructure.baserow.mapper;

import com.lan.app.domain.model.CoworkingMeetingRoomBooking;
import com.lan.app.infrastructure.baserow.dto.BaserowCoworkingMeetingRoomBookingRow;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class BaserowCoworkingMeetingRoomBookingMapper {

    public CoworkingMeetingRoomBooking toDomain(BaserowCoworkingMeetingRoomBookingRow row, UUID guestId) {
        return new CoworkingMeetingRoomBooking(
            row.externalId(),
            guestId,
            row.dateStart(),
            row.dateEnd(),
            row.persons(),
            row.comment()
        );
    }
}
