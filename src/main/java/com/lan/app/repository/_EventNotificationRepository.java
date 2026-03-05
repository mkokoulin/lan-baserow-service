package com.lan.app.repository;

import com.lan.app.domain.EventNotification;

import java.util.List;
import java.util.UUID;

public interface _EventNotificationRepository {
    EventNotification get(String notificationId);
    List<EventNotification> listByIds(List<UUID> ids, int page, int size);
    List<EventNotification> list(int page, int size);
}
