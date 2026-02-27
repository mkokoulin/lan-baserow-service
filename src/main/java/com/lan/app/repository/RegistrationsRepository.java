package com.lan.app.repository;

import com.lan.app.domain.Registration;
import com.lan.app.domain.RegistrationDraft;

import java.util.List;

public interface RegistrationsRepository {
    Registration get(String registrationId);
    Registration create(RegistrationDraft draft);
    List<Registration> list();
}
