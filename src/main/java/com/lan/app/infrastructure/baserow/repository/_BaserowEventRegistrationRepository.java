package com.lan.app.infrastructure.baserow.repository;

import com.lan.app.application.idmapping.EntityType;
import com.lan.app.application.idmapping.ExternalIdResolver;
import com.lan.app.application.idmapping.InternalIdResolver;
import com.lan.app.domain.EventRegistration;
import com.lan.app.domain.EventRegistrationDraft;
import com.lan.app.domain.exception.*;
import com.lan.app.infrastructure.baserow.client._BaserowEventRegistrationApi;
import com.lan.app.infrastructure.baserow.dto.BaserowRegistrationRow;
import com.lan.app.infrastructure.baserow.mapper._BaserowRegistrationMapper;
import com.lan.app.repository._EventRegistrationRepository;
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
public class _BaserowEventRegistrationRepository extends BaseBaserowRepository implements _EventRegistrationRepository {

    private static final Logger LOG = Logger.getLogger(_BaserowEventRegistrationRepository.class);

    private final _BaserowEventRegistrationApi api;
    private final _BaserowRegistrationMapper mapper;
    private final Validator validator;
    private final ExternalIdResolver externalIds;
    private final InternalIdResolver internalIds;
    private final int tableId;

    @Inject
    public _BaserowEventRegistrationRepository(
        @RestClient _BaserowEventRegistrationApi api,
        _BaserowRegistrationMapper mapper,
        Validator validator,
        ExternalIdResolver externalIds,
        InternalIdResolver internalIds,
        @ConfigProperty(name = "baserow.events.registrations-table-id") int tableId
    ) {
        super(LOG, tableId, "BaserowRegistrationsRepository");
        this.api = api;
        this.mapper = mapper;
        this.validator = validator;
        this.tableId = tableId;
        this.externalIds = externalIds;
        this.internalIds = internalIds;
    }

    public EventRegistration create(EventRegistrationDraft registration) {
        var body = toCreatePayload(registration);

        try {
            var response = api.createRow(tableId, body, true);

            var eventExternalId = externalIds.toExternal(EntityType.EVENT, response.eventId().getFirst().id());
            var guestExternalId = externalIds.toExternal(EntityType.EVENT_GUEST, response.guestId().getFirst().id());

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
    public EventRegistration get(String externalRegistrationId) {

        try {
            var resp = api.findByExternalId(tableId, true, externalRegistrationId);

            var row = resp.results().stream().findFirst()
                .orElseThrow(() -> new RegistrationNotFoundException(externalRegistrationId));

            var eventExternalId = externalIds.toExternal(EntityType.EVENT, row.eventId().getFirst().id());
            var guestExternalId = externalIds.toExternal(EntityType.EVENT_GUEST, row.guestId().getFirst().id());

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

    public List<EventRegistration> list() {
        try {
            var resp = api.list(tableId, true);

            return resp.results().stream()
                .filter(this::isValidAndLog)
                .map(r -> {
                    var eventExternalId = externalIds.toExternal(EntityType.EVENT, r.eventId().getFirst().id());
                    var guestExternalId = externalIds.toExternal(EntityType.EVENT_GUEST, r.guestId().getFirst().id());
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

    private Map<String, Object> toCreatePayload(EventRegistrationDraft r) {
        var body = new HashMap<String, Object>();

        int eventInternalId = internalIds.toInternal(EntityType.EVENT, r.eventId());
        int guestInternalId = internalIds.toInternal(EntityType.EVENT_GUEST, r.guestId());

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
