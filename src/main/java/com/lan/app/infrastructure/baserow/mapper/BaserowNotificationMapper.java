package com.lan.app.infrastructure.baserow.mapper;

import com.lan.app.application.idmapping.EntityType;
import com.lan.app.application.idmapping.ExternalIdResolver;
import com.lan.app.domain.Id;
import com.lan.app.domain.Notification;
import com.lan.app.infrastructure.baserow.dto.BaserowNotificationRow;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class BaserowNotificationMapper {

    @Inject
    ExternalIdResolver externalIds;

    public Notification toDomain(BaserowNotificationRow notification) {
        return new Notification(
            new Id(notification.id(), notification.externalId()),
            notification.leadHours(),
            notification.message(),
            notification.active()
        );
    }
}
