package com.lan.app.api.mapper;

import com.lan.app.api.dto.RegistrationCreateRequest;
import com.lan.app.api.dto.RegistrationResponse;
import com.lan.app.domain.Registration;
import com.lan.app.service.command.CreateRegistrationCommand;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RegistrationMapper {

    public CreateRegistrationCommand toCommand(RegistrationCreateRequest req) {
        return new CreateRegistrationCommand(
            req.eventId(),
            req.guestId(),
            req.guestComment(),
            req.guestCount(),
            req.source()
        );
    }

    public RegistrationResponse toResponse(Registration registration) {
        return new RegistrationResponse(
            registration.id().externalId().toString(),
            registration.eventId().externalId().toString(),
            registration.guestId().externalId().toString(),
            registration.comment(),
            registration.guestCount()
        );
    }
}
