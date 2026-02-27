package com.lan.app.repository;

import com.lan.app.application.idmapping.EntityType;
import com.lan.app.application.idmapping.ExternalIdResolver;
import com.lan.app.application.idmapping.InternalIdResolver;
import com.lan.app.domain.Registration;
import com.lan.app.domain.RegistrationDraft;
import com.lan.app.domain.exception.*;
import com.lan.app.infrastructure.baserow.client.BaserowRegistrationApi;
import com.lan.app.infrastructure.baserow.dto.BaserowRegistrationRow;
import com.lan.app.infrastructure.baserow.mapper.BaserowRegistrationMapper;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Dependent
public class BaserowRegistrationsRepository extends BaseBaserowRepository implements RegistrationsRepository {

    private static final Logger LOG = Logger.getLogger(BaserowRegistrationsRepository.class);

    private final BaserowRegistrationApi api;
    private final BaserowRegistrationMapper mapper;
    private final Validator validator;
    private final ExternalIdResolver externalIds;
    private final InternalIdResolver internalIds;
    private final int tableId;

    @Inject
    public BaserowRegistrationsRepository(
        @RestClient BaserowRegistrationApi api,
        BaserowRegistrationMapper mapper,
        Validator validator,
        ExternalIdResolver externalIds,
        InternalIdResolver internalIds,
        @ConfigProperty(name = "baserow.registrations-table-id") int tableId
    ) {
        super(LOG, tableId, "BaserowRegistrationsRepository");
        this.api = api;
        this.mapper = mapper;
        this.validator = validator;
        this.tableId = tableId;
        this.externalIds = externalIds;
        this.internalIds = internalIds;
    }

    public Registration create(RegistrationDraft registration) {
        var body = toCreatePayload(registration);

        try {
            var response = api.createRow(tableId, body, true);

            var eventExternalId = externalIds.toExternal(EntityType.EVENT, response.eventId().getFirst().id());
            var guestExternalId = externalIds.toExternal(EntityType.GUEST, response.guestId().getFirst().id());

            return mapper.toDomain(response, eventExternalId, guestExternalId);

        } catch (WebApplicationException e) {
            throw mapHttpException("create", e, "payload=" + body);

        } catch (RuntimeException e) {
            throw e;

        } catch (Exception e) {
            LOG.errorf(e, "Baserow call failed. op=create tableId=%d payload=%s", tableId, body);
            throw new ExternalServiceUnavailableException("Baserow call failed", e);
        }
    }

    @Override
    public Registration get(String externalRegistrationId) {

        try {
            var resp = api.findByExternalId(tableId, true, externalRegistrationId);

            var row = resp.results().stream().findFirst()
                .orElseThrow(() -> new RegistrationNotFoundException(externalRegistrationId));

            var eventExternalId = externalIds.toExternal(EntityType.EVENT, row.eventId().getFirst().id());
            var guestExternalId = externalIds.toExternal(EntityType.GUEST, row.guestId().getFirst().id());

            return mapper.toDomain(row, eventExternalId, guestExternalId);

        } catch (WebApplicationException e) {
            throw mapHttpException("get", e, "externalId=" + externalRegistrationId);
        } catch (RuntimeException e) {
            throw e;

        } catch (Exception e) {
            LOG.errorf(e, "Baserow call failed. op=get tableId=%d externalId=%s", tableId, externalRegistrationId);
            throw new ExternalServiceUnavailableException("Baserow call failed", e);
        }
    }

    public List<Registration> list() {
        try {
            var resp = api.list(tableId, true);

            return resp.results().stream()
                .filter(this::isValidAndLog)
                .map(r -> {
                    var eventExternalId = externalIds.toExternal(EntityType.EVENT, r.eventId().getFirst().id());
                    var guestExternalId = externalIds.toExternal(EntityType.GUEST, r.guestId().getFirst().id());
                    return mapper.toDomain(r, eventExternalId, guestExternalId);
                }).toList();

        } catch (WebApplicationException e) {
            throw mapHttpException("list", e, "");

        } catch (RuntimeException e) {
            throw e;

        } catch (Exception e) {
            LOG.errorf(e, "Baserow call failed. op=list tableId=%d", tableId);
            throw new ExternalServiceUnavailableException("Baserow call failed", e);
        }
    }

    private boolean isValidAndLog(BaserowRegistrationRow row) {
        var violations = validator.validate(row);
        if (violations.isEmpty()) return true;

        var compact = violations.stream()
            .map(v -> v.getPropertyPath() + ": " + v.getMessage())
            .toList();

        LOG.warnf("Skip invalid baserow row id=%s violations=%s", row.id(), compact);
        return false;
    }

    private Map<String, Object> toCreatePayload(RegistrationDraft r) {
        var body = new HashMap<String, Object>();

        int eventInternalId = internalIds.toInternal(EntityType.EVENT, r.eventId());
        int guestInternalId = internalIds.toInternal(EntityType.GUEST, r.guestId());

        body.put("event_id", List.of(eventInternalId));
        body.put("guest_id", List.of(guestInternalId));

        if (r.comment() != null && !r.comment().isBlank()) {
            body.put("guest_comment", r.comment());
        }
        if (r.guestCount() != null) {
            body.put("guest_count", r.guestCount());
        }
        if (r.source() != null && !r.source().isBlank()) {
            body.put("source", r.source());
        }

        return body;
    }

}
