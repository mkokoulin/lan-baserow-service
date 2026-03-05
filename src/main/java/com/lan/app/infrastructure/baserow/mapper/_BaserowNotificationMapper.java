package com.lan.app.infrastructure.baserow.mapper;

import com.lan.app.application.idmapping.ExternalIdResolver;
import com.lan.app.domain.Id;
import com.lan.app.domain.EventNotification;
import com.lan.app.infrastructure.baserow.dto.BaserowNotificationRow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class _BaserowNotificationMapper {

    @Inject
    ExternalIdResolver externalIds;

    public EventNotification toDomain(BaserowNotificationRow notification) {
        return new EventNotification(
            new Id(notification.id(), notification.externalId()),
            notification.leadHours(),
            notification.message(),
            notification.active()
        );
    }
}
