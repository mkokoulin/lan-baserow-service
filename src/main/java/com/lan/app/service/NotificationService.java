package com.lan.app.service;

import com.lan.app.domain.Notification;
import com.lan.app.repository.NotificationRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class NotificationService {

    private final NotificationRepository repo;

    public NotificationService(NotificationRepository repo) {
        this.repo = repo;
    }

    public List<Notification> list(int page, int size) {
        return repo.list(page, size);
    }

    public List<Notification> listByIds(List<UUID> ids, int page, int size) {
        return repo.listByIds(ids, page, size);
    }

    public Notification get(UUID externalNotificationId) {
        return repo.get(externalNotificationId.toString());
    }
}
