package com.lan.app.infrastructure.baserow.repository;

import com.lan.app.domain.model.CoworkingNotification;
import com.lan.app.infrastructure.baserow.client.BaserowCoworkingNotificationClient;
import com.lan.app.infrastructure.baserow.mapper.BaserowCoworkingNotificationMapper;
import com.lan.app.repository.CoworkingNotificationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class BaserowCoworkingNotificationRepository implements CoworkingNotificationRepository {

    private final int coworkingNotificationTableId;

    private final BaserowCoworkingNotificationClient client;
    private final BaserowCoworkingNotificationMapper mapper;

    BaserowCoworkingNotificationRepository(
        @ConfigProperty(name = "baserow.coworking.notifications-table-id") int coworkingNotificationTableId,
        @RestClient BaserowCoworkingNotificationClient client,
        BaserowCoworkingNotificationMapper mapper
    ) {
        this.coworkingNotificationTableId = coworkingNotificationTableId;
        this.client = client;
        this.mapper = mapper;
    }

    public List<CoworkingNotification> list() {
        var resp = client.list(coworkingNotificationTableId);
        return resp.results().stream().map(mapper::toDomain).toList();
    }
}
