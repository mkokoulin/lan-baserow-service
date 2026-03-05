package com.lan.app.repository;

import com.lan.app.domain.CoworkingGuest;
import com.lan.app.service.command.UpdateCoworkingGuestCommand;

import java.util.UUID;

public interface CoworkingGuestRepository {
    CoworkingGuest create(String firstName, String lastName, String phone, String telegram);
    CoworkingGuest get(UUID externalId);
    CoworkingGuest update(UUID externalId, UpdateCoworkingGuestCommand patch);
}
