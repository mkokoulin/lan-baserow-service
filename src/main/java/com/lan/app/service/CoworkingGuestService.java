package com.lan.app.service;

import com.lan.app.domain.CoworkingGuest;
import com.lan.app.repository.CoworkingGuestRepository;
import com.lan.app.service.command.UpdateCoworkingGuestCommand;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class CoworkingGuestService {

    CoworkingGuestRepository repo;

    public CoworkingGuestService(CoworkingGuestRepository repo) {
        this.repo = repo;
    }

    public CoworkingGuest get(UUID externalId) {
        return repo.get(externalId);
    }

    public CoworkingGuest create(String firstName, String lastName, String phone, String telegram) {
        return repo.create(firstName, lastName, phone, telegram);
    }

    public CoworkingGuest update(UUID externalId, UpdateCoworkingGuestCommand cmd) {
        return repo.update(externalId, cmd);
    }
}
