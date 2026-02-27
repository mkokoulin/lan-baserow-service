package com.lan.app.infrastructure.baserow.idmapping;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lan.app.application.idmapping.EntityType;
import com.lan.app.application.idmapping.ExternalIdResolver;
import com.lan.app.application.idmapping.InternalIdResolver;
import com.lan.app.domain.exception.CorruptedDataException;
import com.lan.app.infrastructure.baserow.client.BaserowEventApi;
import com.lan.app.infrastructure.baserow.client.BaserowGuestApi;
import com.lan.app.infrastructure.baserow.client.BaserowNotificationApi;
import com.lan.app.infrastructure.baserow.client.BaserowRegistrationApi;
import com.lan.app.infrastructure.baserow.dto.BaserowEventRow;
import com.lan.app.infrastructure.baserow.dto.BaserowGuestRow;
import com.lan.app.infrastructure.baserow.dto.BaserowRegistrationRow;
import jakarta.ws.rs.NotFoundException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.UUID;

@ApplicationScoped
public class BaserowIdResolver implements ExternalIdResolver, InternalIdResolver {

    private final BaserowEventApi eventApi;
    private final BaserowGuestApi guestApi;
    private final BaserowRegistrationApi registrationApi;
    private final BaserowNotificationApi notificationApi;

    @ConfigProperty(name = "baserow.events-table-id")
    int eventsTableId;

    @ConfigProperty(name = "baserow.guests-table-id")
    int guestsTableId;

    @ConfigProperty(name = "baserow.registrations-table-id")
    int registrationsTableId;

    @ConfigProperty(name = "baserow.notifications-table-id")
    int notificationsTableId;

    public BaserowIdResolver(
        @RestClient BaserowEventApi eventApi,
        @RestClient BaserowGuestApi guestApi,
        @RestClient BaserowRegistrationApi registrationApi,
        @RestClient BaserowNotificationApi notificationApi
    ) {
        this.eventApi = eventApi;
        this.guestApi = guestApi;
        this.registrationApi = registrationApi;
        this.notificationApi = notificationApi;
    }

    @Override
    public UUID toExternal(EntityType type, int internalId) {
        String existing = switch (type) {
            case EVENT -> requireExternalId(
                eventApi.getByRowId(eventsTableId, internalId, true),
                type,
                internalId
            );
            case GUEST -> requireExternalId(
                guestApi.getByRowId(guestsTableId, internalId, true),
                type,
                internalId
            );
            case REGISTRATION -> requireExternalId(
                registrationApi.getByRowId(registrationsTableId, internalId, true),
                type,
                internalId
            );
            case NOTIFICATION -> requireExternalId(
                notificationApi.getByRowId(notificationsTableId, internalId, true),
                type,
                internalId
            );
        };

        return parseUuidOrThrow(type, internalId, existing);
    }

    @Override
    public int toInternal(EntityType type, UUID externalId) {
        var ext = externalId.toString();

        return switch (type) {
            case EntityType.EVENT -> singleInternalId(
                type,
                eventApi.list(eventsTableId, true).results(),
                ext
            );
            case EntityType.GUEST -> singleInternalId(
                type,
                guestApi.list(guestsTableId, true).results(),
                ext
            );
            case EntityType.REGISTRATION -> singleInternalId(
                type,
                registrationApi.list(registrationsTableId, true).results(),
                ext
            );
            case EntityType.NOTIFICATION -> singleInternalId(
                type,
                notificationApi.list(notificationsTableId, true).results(),
                ext
            );
        };
    }

    private String requireExternalId(Object row, EntityType type, int internalId) {
        if (row instanceof BaserowEventRow r) {
            return r.externalId().toString();
        }
        if (row instanceof BaserowGuestRow r) return r.externalId().toString();
        if (row instanceof BaserowRegistrationRow r) return r.externalId().toString();
        throw new CorruptedDataException("Unknown row DTO for " + type + " internalId=" + internalId);
    }

    private int singleInternalId(EntityType type, java.util.List<?> results, String externalId) {
        if (results == null || results.isEmpty()) {
            throw new NotFoundException(type + " not found for external_id=" + externalId);
        }
        if (results.size() > 1) {
            throw new CorruptedDataException("Duplicate external_id=" + externalId + " for " + type);
        }

        Object row = results.getFirst();
        if (row instanceof BaserowEventRow r) return r.id();
        if (row instanceof BaserowGuestRow r) return r.id();
        if (row instanceof BaserowRegistrationRow r) return r.id();

        throw new CorruptedDataException("Unknown row DTO in list results for " + type);
    }

    private UUID parseUuidOrThrow(EntityType type, int internalId, String value) {
        try {
            return UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new CorruptedDataException(
                "Invalid external_id='" + value + "' for " + type + " internalId=" + internalId,
                e
            );
        }
    }

    public record ExternalIdPatch(@JsonProperty("external_id") String externalId) {}
}
