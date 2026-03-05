package com.lan.app.service;

import com.lan.app.domain.EventRegistration;
import com.lan.app.domain.EventRegistrationDraft;
import com.lan.app.repository._EventRegistrationRepository;
import com.lan.app.service.command.CreateEventRegistrationCommand;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class _EventRegistrationService {
    private final _EventRegistrationRepository repo;

    public _EventRegistrationService(_EventRegistrationRepository repo) {
        this.repo = repo;
    }

    public EventRegistration get(String registrationId) {
        return repo.get(registrationId);
    }

    public EventRegistration create(CreateEventRegistrationCommand cmd) {
        return repo.create(toDraft(cmd));
    }

    public List<EventRegistration> list() {
        return repo.list();
    }

    private EventRegistrationDraft toDraft(CreateEventRegistrationCommand cmd) {
       return new EventRegistrationDraft(
           cmd.eventId(),
           cmd.guestId(),
           cmd.guestCount(),
           cmd.comment(),
           cmd.source()
       );
    }
}
