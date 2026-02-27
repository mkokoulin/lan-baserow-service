package com.lan.app.repository;

import com.lan.app.domain.Guest;
import com.lan.app.domain.exception.ExternalServiceUnavailableException;
import com.lan.app.domain.exception.GuestNotFoundException;
import com.lan.app.infrastructure.baserow.client.BaserowGuestApi;
import com.lan.app.infrastructure.baserow.mapper.BaserowGuestMapper;
import jakarta.enterprise.context.Dependent;
import jakarta.json.Json;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

@Dependent
public class BaserowGuestsRepository extends BaseBaserowRepository implements GuestsRepository {

    private static final Logger LOG = Logger.getLogger(BaserowGuestsRepository.class);

    private final BaserowGuestApi api;
    private final BaserowGuestMapper mapper;
    private final int tableId;

    BaserowGuestsRepository(
        @RestClient BaserowGuestApi api,
        BaserowGuestMapper mapper,
        @ConfigProperty(name = "baserow.guests-table-id") int tableId
    ) {
        super(LOG, tableId, "BaserowGuestsRepository");
        this.api = api;
        this.mapper = mapper;
        this.tableId = tableId;
    }

    @Override
    public Guest get(String externalGuestId) {
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
