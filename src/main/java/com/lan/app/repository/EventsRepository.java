package com.lan.app.repository;

import com.lan.app.domain.Event;

import java.util.List;
import java.util.UUID;

public interface EventsRepository {
    List<Event> list(int page, int size);
    Event get(UUID eventId);
}
