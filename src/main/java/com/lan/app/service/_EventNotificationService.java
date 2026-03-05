package com.lan.app.service;

import com.lan.app.domain.EventNotification;
import com.lan.app.repository._EventNotificationRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class _EventNotificationService {

    private final _EventNotificationRepository repo;

    public _EventNotificationService(_EventNotificationRepository repo) {
        this.repo = repo;
    }

    public List<EventNotification> list(int page, int size) {
        return repo.list(page, size);
    }

    public List<EventNotification> listByIds(List<UUID> ids, int page, int size) {
        return repo.listByIds(ids, page, size);
    }

    public EventNotification get(UUID externalNotificationId) {
        return repo.get(externalNotificationId.toString());
    }
}
