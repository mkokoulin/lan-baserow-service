package com.lan.app.api.mapper;

import com.lan.app.api.dto.request.EventRegistrationCreateRequest;
import com.lan.app.api.dto.response.EventRegistrationResponse;
import com.lan.app.domain.model.EventRegistration;
import com.lan.app.service.command.CreateEventRegistrationCommand;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EventRegistrationMapper {

    public CreateEventRegistrationCommand toCommand(EventRegistrationCreateRequest req) {
        return new CreateEventRegistrationCommand(
            req.eventId(),
            req.guestId(),
            req.guestComment(),
            req.guestCount(),
            req.source()
        );
    }

    public EventRegistrationResponse toResponse(EventRegistration registration) {
        return new EventRegistrationResponse(
            registration.id().externalId().toString(),
            registration.eventId().externalId().toString(),
            registration.guestId().externalId().toString(),
            registration.comment(),
            registration.guestCount()
        );
    }
}
