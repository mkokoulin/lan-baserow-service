package com.lan.app.infrastructure.baserow.mapper;

import com.lan.app.application.idmapping.EntityType;
import com.lan.app.application.idmapping.ExternalIdResolver;
import com.lan.app.domain.Guest;
import com.lan.app.infrastructure.baserow.dto.BaserowGuestRow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BaserowGuestMapper {

    @Inject ExternalIdResolver externalIds;

    public Guest toDomain(BaserowGuestRow guest) {
        return new Guest(
            externalIds.toExternal(EntityType.GUEST, guest.id()),
            guest.firstName(),
            guest.lastName(),
            guest.telegram(),
            guest.phone(),
            guest.source().value()
        );
    }
}
