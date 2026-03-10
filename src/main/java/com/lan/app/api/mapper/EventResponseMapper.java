package com.lan.app.api.mapper;

import com.lan.app.api.dto.response.EventResponse;
import com.lan.app.domain.model.Event;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class EventResponseMapper {
    public EventResponse toResponse(Event e) {
        List<String> showEvent = new ArrayList<>();

        for (var event : e.showEvent()) {
            showEvent.add(event.toString());
        }

        return new EventResponse(
            e.id().externalId(),
            e.name(),
            Objects.toString(e.dateStart()),
            Objects.toString(e.dateEnd()),
            e.description(),
            e.externalRegistrationUrl().toString(),
            e.registrationUrl().toString(),
            e.instagramUrl().toString(),
            e.type().toString().toLowerCase(),
            e.showForm(),
            e.notificationTime(),
            e.comment(),
            showEvent
        );
    }
}
