package com.lan.app.service;

import com.lan.app.domain.Registration;
import com.lan.app.domain.RegistrationDraft;
import com.lan.app.repository.RegistrationsRepository;
import com.lan.app.service.command.CreateRegistrationCommand;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class RegistrationService {
    private final RegistrationsRepository repo;

    public RegistrationService(RegistrationsRepository repo) {
        this.repo = repo;
    }

    public Registration get(String registrationId) {
        return repo.get(registrationId);
    }

    public Registration create(CreateRegistrationCommand cmd) {
        return repo.create(toDraft(cmd));
    }

    public List<Registration> list() {
        return repo.list();
    }

    private RegistrationDraft toDraft(CreateRegistrationCommand cmd) {
       return new RegistrationDraft(
           cmd.eventId(),
           cmd.guestId(),
           cmd.guestCount(),
           cmd.comment(),
           cmd.source()
       );
    }
}
