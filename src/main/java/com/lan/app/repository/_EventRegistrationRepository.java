package com.lan.app.repository;

import com.lan.app.domain.EventRegistration;
import com.lan.app.domain.EventRegistrationDraft;

import java.util.List;

public interface _EventRegistrationRepository {
    EventRegistration get(String registrationId);
    EventRegistration create(EventRegistrationDraft draft);
    List<EventRegistration> list();
}
