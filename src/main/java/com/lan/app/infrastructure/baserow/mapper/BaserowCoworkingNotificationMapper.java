package com.lan.app.infrastructure.baserow.mapper;

import com.lan.app.domain.CoworkingNotification;
import com.lan.app.infrastructure.baserow.dto.BaserowCoworkingNotificationRow;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BaserowCoworkingNotificationMapper {

    public CoworkingNotification toDomain(BaserowCoworkingNotificationRow row) {
        return new CoworkingNotification(
            row.externalId(),
            row.message(),
            row.sentAt()
        );
    }
}
