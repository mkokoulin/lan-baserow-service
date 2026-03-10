package com.lan.app.service;

import com.lan.app.domain.model.CoworkingNotification;
import com.lan.app.repository.CoworkingNotificationRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CoworkingNotificationService {

    CoworkingNotificationRepository repo;

    public CoworkingNotificationService(CoworkingNotificationRepository repo) {
        this.repo = repo;
    }

    public List<CoworkingNotification> list() {
        return repo.list();
    }
}
