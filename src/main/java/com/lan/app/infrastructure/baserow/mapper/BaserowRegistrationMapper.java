package com.lan.app.infrastructure.baserow.mapper;

import com.lan.app.domain.*;
import com.lan.app.infrastructure.baserow.dto.BaserowRegistrationRow;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class BaserowRegistrationMapper {

    public Registration toDomain(BaserowRegistrationRow registration, UUID eventExternalId, UUID guestExternalId) {

        int eventInternal = registration.eventId().getFirst().id();
        int guestInternal = registration.guestId().getFirst().id();

        return new Registration(
            new Id(registration.id(), registration.externalId()),
            new Id(eventInternal, eventExternalId),
            new Id(guestInternal, guestExternalId),
            registration.guestCount(),
            registration.guestComment(),
            registration.source()
        );
    }
}
