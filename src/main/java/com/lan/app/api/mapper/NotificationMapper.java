package com.lan.app.api.mapper;

import com.lan.app.api.dto.NotificationResponse;
import com.lan.app.domain.Notification;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NotificationMapper {

    public NotificationResponse toResponse(Notification notification) {
        return new NotificationResponse(
            notification.Id().externalId().toString(),
            notification.leadHours(),
            notification.message(),
            notification.active()
        );
    }
}
