package com.lan.app.repository;

import com.lan.app.domain.Notification;

import java.util.List;
import java.util.UUID;

public interface NotificationRepository {
    Notification get(String notificationId);
    List<Notification> listByIds(List<UUID> ids, int page, int size);
    List<Notification> list(int page, int size);
}
