package com.lan.app.api.mapper;

import com.lan.app.api.dto.response.CoworkingNotificationResponse;
import com.lan.app.domain.model.CoworkingNotification;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApiCoworkingNotificationMapper {

    public CoworkingNotificationResponse toResponse(CoworkingNotification r) {
        return new CoworkingNotificationResponse(
            r.externalId(),
            r.message(),
            r.sentAt()
        );
    }
}
