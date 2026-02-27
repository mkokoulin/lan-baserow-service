package com.lan.app.repository;

import com.lan.app.domain.Event;
import com.lan.app.domain.exception.ExternalServiceUnavailableException;
import com.lan.app.infrastructure.baserow.client.BaserowEventApi;
import com.lan.app.infrastructure.baserow.mapper.BaserowEventMapper;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.UUID;

@Dependent
public class BaserowEventsRepository extends BaseBaserowRepository implements EventsRepository {

    private static final Logger LOG = Logger.getLogger(BaserowEventsRepository.class);

    private final BaserowEventApi api;
    private final BaserowEventMapper mapper;
    private final int tableId;

    @Inject
    public BaserowEventsRepository(
        @RestClient BaserowEventApi api,
        BaserowEventMapper mapper,
        @ConfigProperty(name = "baserow.events-table-id") int tableId
    ) {
        super(LOG, tableId, "BaserowEventsRepository");
        this.api = api;
        this.mapper = mapper;
        this.tableId = tableId;
    }

    public List<Event> list(int page, int size) {
        try {
            var resp = api.list(tableId, true);
            return resp.results().stream().map(mapper::toDomain).toList();
        } catch (WebApplicationException e) {
            throw mapHttpException("list", e, "");

        } catch (RuntimeException e) {
            throw e;

        } catch (Exception e) {
            LOG.errorf(e, "Baserow call failed. op=list tableId=%d page=%d size=%d", tableId, page, size);
            throw new ExternalServiceUnavailableException("Baserow call failed", e);
        }
    }

    public Event get(UUID externalEventId) {

        try {
            var row = api.findByExternalId(tableId, true, externalEventId.toString());
            return mapper.toDomain (row.results().getFirst());

        } catch (WebApplicationException e) {
            throw mapHttpException("get", e,
                "externalEventId=" + externalEventId + " externalEventId=" + externalEventId);

        } catch (RuntimeException e) {
            throw e;

        } catch (Exception e) {
            LOG.errorf(e, "Baserow call failed. op=get tableId=%d externalEventId=%s externalEventId=%d",
                tableId, externalEventId, externalEventId);
            throw new ExternalServiceUnavailableException("Baserow call failed", e);
        }
    }
}