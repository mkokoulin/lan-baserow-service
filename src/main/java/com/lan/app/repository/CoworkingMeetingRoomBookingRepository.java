package com.lan.app.repository;

import com.lan.app.domain.model.CoworkingMeetingRoomBooking;
import com.lan.app.infrastructure.baserow.dto.CreateCoworkingMeetingRoomBookingRowRequest;
import com.lan.app.infrastructure.baserow.dto.UpdateCoworkingMeetingRoomBookingRowRequest;

import java.util.List;
import java.util.UUID;

public interface CoworkingMeetingRoomBookingRepository {
    List<CoworkingMeetingRoomBooking> list();
    CoworkingMeetingRoomBooking get(UUID id);
    CoworkingMeetingRoomBooking create(CreateCoworkingMeetingRoomBookingRowRequest request);
    CoworkingMeetingRoomBooking update(UUID externalId, UpdateCoworkingMeetingRoomBookingRowRequest patch);
}
