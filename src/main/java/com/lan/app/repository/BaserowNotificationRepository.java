package com.lan.app.repository;

import com.lan.app.domain.Notification;
import com.lan.app.domain.exception.ExternalServiceUnavailableException;
import com.lan.app.domain.exception.NotificationNotFoundException;
import com.lan.app.infrastructure.baserow.client.BaserowNotificationApi;
import com.lan.app.infrastructure.baserow.mapper.BaserowNotificationMapper;
import jakarta.enterprise.context.Dependent;
import jakarta.json.Json;
import jakarta.ws.rs.WebApplicationException;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Dependent
public class BaserowNotificationRepository  extends BaseBaserowRepository implements NotificationRepository {

    private static final Logger LOG = Logger.getLogger(BaserowNotificationRepository.class);

    private final BaserowNotificationApi api;
    private final BaserowNotificationMapper mapper;
    private final int tableId;

    BaserowNotificationRepository(
        @RestClient BaserowNotificationApi api,
        BaserowNotificationMapper mapper,
        @ConfigProperty(name = "baserow.notifications-table-id") int tableId
    ) {
        super(LOG, tableId, "BaserowNotificationRepository");
        this.api = api;
        this.mapper = mapper;
        this.tableId = tableId;
    }

    @Override
    public Notification get(String externalNotificationId) {
        var filter = Json.createObjectBuilder()
            .add("external_id", externalNotificationId)
            .build()
            .toString();

        try {
            var resp = api.findByExternalId(tableId, true, filter);

            var row = resp.results().stream().findFirst()
                .orElseThrow(() -> new NotificationNotFoundException(externalNotificationId));

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

    public List<Notification> list(int page, int size) {
        try {
            var resp = api.list(tableId, true);
            var result = resp.results().stream().map(mapper::toDomain).toList();

            return result;
        } catch (WebApplicationException e) {
            throw mapHttpException("list", e, "");

        } catch (RuntimeException e) {
            throw e;

        } catch (Exception e) {
            LOG.errorf(e, "Baserow call failed. op=list tableId=%d page=%d size=%d", tableId, page, size);
            throw new ExternalServiceUnavailableException("Baserow call failed", e);
        }
    }


    public List<Notification> listByIds(List<UUID> ids, int page, int size) {
        try {
            var resp = api.list(tableId, true);

            if (ids == null || ids.isEmpty()) {
                return resp.results().stream()
                    .map(mapper::toDomain)
                    .toList();
            }

            var wanted = Set.copyOf(ids);

            return resp.results().stream()
                .filter(r -> wanted.contains(r.externalId())) // если externalId() -> UUID
                .map(mapper::toDomain)
                .toList();
        } catch (WebApplicationException e) {
            throw mapHttpException("list", e, "");

        } catch (RuntimeException e) {
            throw e;

        } catch (Exception e) {
            LOG.errorf(e, "Baserow call failed. op=list tableId=%d page=%d size=%d", tableId, page, size);
            throw new ExternalServiceUnavailableException("Baserow call failed", e);
        }
    }
}
