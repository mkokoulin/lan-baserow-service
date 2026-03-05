package com.lan.app.infrastructure.baserow.repository;

import com.lan.app.domain.EventGuest;
import com.lan.app.domain.exception.ExternalServiceUnavailableException;
import com.lan.app.domain.exception.GuestNotFoundException;
import com.lan.app.infrastructure.baserow.client._BaserowEventGuestApi;
import com.lan.app.infrastructure.baserow.mapper._BaserowEventGuestMapper;
import com.lan.app.repository._EventGuestRepository;
import jakarta.enterprise.context.Dependent;
import jakarta.json.Json;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@Dependent
public class _BaserowEventGuestRepository extends BaseBaserowRepository implements _EventGuestRepository {

    private static final Logger LOG = Logger.getLogger(_BaserowEventGuestRepository.class);

    private final _BaserowEventGuestApi api;
    private final _BaserowEventGuestMapper mapper;
    private final int tableId;

    _BaserowEventGuestRepository(
        @RestClient _BaserowEventGuestApi api,
        _BaserowEventGuestMapper mapper,
        @ConfigProperty(name = "baserow.events.guests-table-id") int tableId
    ) {
        super(LOG, tableId, "BaserowGuestsRepository");
        this.api = api;
        this.mapper = mapper;
        this.tableId = tableId;
    }

    @Override
    public EventGuest get(String externalGuestId) {
        var filter = Json.createObjectBuilder()
            .add("external_id", externalGuestId)
            .build()
            .toString();

        try {
            var resp = api.findByExternalId(tableId, true, filter);

            var row = resp.results().stream().findFirst()
                .orElseThrow(() -> new GuestNotFoundException(externalGuestId));

            return mapper.toDomain(row);

        } catch (WebApplicationException e) {
            throw mapHttpException("get", e, "filter=" + filter);
        } catch (RuntimeException e) {
            throw e;

        } catch (Exception e) {
            LOG.errorf(e, "Baserow call failed. op=get tableId=%d filter=%s", tableId, filter);
            throw new ExternalServiceUnavailableException("Baserow call failed", e);
        }
    }
}
