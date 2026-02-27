package com.lan.app.infrastructure.baserow.mapper;

import com.lan.app.domain.*;
import com.lan.app.infrastructure.baserow.dto.BaserowEventRow;
import com.lan.app.infrastructure.baserow.dto.BaserowFile;
import com.lan.app.infrastructure.baserow.dto.BaserowSelectOption;
import jakarta.enterprise.context.ApplicationScoped;

import java.net.URI;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class BaserowEventMapper {
    public Event toDomain(BaserowEventRow event) {
        BaserowFile firstImage = firstOrNull(event.image());

        EventImage image = firstImage != null ? new EventImage(firstImage.url()) : new EventImage("");

        var rawShowEvent = toValues(event.showEvent());

        List<EventClient> showEvent = new ArrayList<>();

        for (var rawEvent : rawShowEvent) {
            showEvent.add(EventClient.fromBaserow(rawEvent));
        }

        return new Event(
            new Id(event.id(), event.externalId()),
            event.name(),
            parseInstantOrNull(event.dateStart()),
            parseInstantOrNull(event.dateEnd()),
            event.description(),
            image,
            URI.create(event.externalRegistrationUrl()),
            URI.create(event.registrationUrl()),
            URI.create(event.instagramUrl()),
            EventType.fromBaserow(event.type().value()),
            event.showForm(),
            toValues(event.notificationTime()),
            event.comment(),
            showEvent
        );
    }

    private static List<String> toValues(List<BaserowSelectOption> opts) {
        if (opts == null) return List.of();
        return opts.stream()
            .map(BaserowSelectOption::value)
            .filter(v -> v != null && !v.isBlank())
            .toList();
    }

    private static <T> T firstOrNull(java.util.List<T> list) {
        return (list == null || list.isEmpty()) ? null : list.get(0);
    }

    static Instant parseInstantOrNull(String raw) {
        if (raw == null || raw.isBlank()) return null;
        try {
            return Instant.parse(raw);
        } catch (Exception e) {
            return OffsetDateTime.parse(raw).toInstant();
        }
    }
}

