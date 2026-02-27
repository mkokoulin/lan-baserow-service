package com.lan.app.service;

import com.lan.app.domain.Event;
import com.lan.app.repository.EventsRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class EventService {

    private final EventsRepository repo;

    public EventService(EventsRepository repo) {
        this.repo = repo;
    }

    public List<Event> list(int page, int size) {
        return repo.list(page, size);
    }

    public Event get(UUID externalEventId) {
        return repo.get(externalEventId);
    }
}