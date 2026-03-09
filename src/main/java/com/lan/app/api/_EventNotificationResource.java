package com.lan.app.api;

import com.lan.app.api.dto.response.EventNotificationResponse;
import com.lan.app.api.mapper.EventNotificationMapper;
import com.lan.app.service._EventNotificationService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;

@Path("/notifications")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class _EventNotificationResource {

    private final _EventNotificationService service;
    private final EventNotificationMapper mapper;

    public _EventNotificationResource(_EventNotificationService service, EventNotificationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GET
    public List<EventNotificationResponse> list(
        @QueryParam("page") @DefaultValue("1") int page,
        @QueryParam("size") @DefaultValue("50") int size,
        @QueryParam("id") List<UUID> id
    ) {
        var result = (id != null && !id.isEmpty())
            ? service.listByIds(id, page, size)
            : service.list(page, size);

        return result.stream().map(mapper::toResponse).toList();
    }

    @GET
    @Path("/{notificationId}")
    public EventNotificationResponse get(@PathParam("notificationId") UUID notificationId) {
        var e = service.get(notificationId);
        return mapper.toResponse(e);
    }
}
