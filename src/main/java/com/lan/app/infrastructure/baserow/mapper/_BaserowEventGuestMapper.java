package com.lan.app.infrastructure.baserow.mapper;

import com.lan.app.domain.EventGuest;
import com.lan.app.domain.Id;
import com.lan.app.infrastructure.baserow.dto.BaserowEventGuestRow;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class _BaserowEventGuestMapper {

    public EventGuest toDomain(BaserowEventGuestRow eventGuest) {
        return new EventGuest(
            new Id(eventGuest.id(), eventGuest.externalId()),
            eventGuest.firstName(),
            eventGuest.lastName(),
            eventGuest.telegram(),
            eventGuest.phone(),
            eventGuest.source().value()
        );
    }
}
