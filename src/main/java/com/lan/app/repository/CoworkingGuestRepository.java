package com.lan.app.repository;

import com.lan.app.domain.model.CoworkingGuest;
import com.lan.app.service.command.UpdateCoworkingGuestCommand;

import java.util.UUID;

public interface CoworkingGuestRepository {
    CoworkingGuest get(UUID externalId);
    CoworkingGuest create(String firstName, String lastName, String phone, String telegram);
    CoworkingGuest update(UUID externalId, UpdateCoworkingGuestCommand patch);
}
