package com.lan.app.infrastructure.baserow.repository;

import com.lan.app.infrastructure.baserow.client.BaserowCoworkingMeetingRoomBookingClient;
import com.lan.app.infrastructure.baserow.mapper.BaserowCoworkingMeetingRoomBookingMapper;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class BaserowCoworkingMeetingRoomBookingRepository {

    private final int coworkingMeetingRoomBookingTableId;

    private final BaserowCoworkingMeetingRoomBookingClient client;
    private final BaserowCoworkingMeetingRoomBookingMapper mapper;

    BaserowCoworkingMeetingRoomBookingRepository(
        @ConfigProperty(name = "baserow.coworking.meeting-room-bookings-table-id") int coworkingMeetingRoomBookingTableId,
        @RestClient BaserowCoworkingMeetingRoomBookingClient meetingRoomBookingClient,
        BaserowCoworkingMeetingRoomBookingMapper mapper
    ) {
        this.coworkingMeetingRoomBookingTableId = coworkingMeetingRoomBookingTableId;
        this.client = meetingRoomBookingClient;
        this.mapper = mapper;
    }
}
