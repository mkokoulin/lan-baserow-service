package com.lan.app.service;

import com.lan.app.domain.Event;
import com.lan.app.repository._EventRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class _EventService {

    private final _EventRepository repo;

    public _EventService(_EventRepository repo) {
        this.repo = repo;
    }

    public List<Event> list(int page, int size) {
        return repo.list(page, size);
    }

    public Event get(UUID externalEventId) {
        return repo.get(externalEventId);
    }
}