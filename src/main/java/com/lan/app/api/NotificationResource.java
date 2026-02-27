package com.lan.app.api;

import com.lan.app.api.dto.NotificationResponse;
import com.lan.app.api.mapper.NotificationMapper;
import com.lan.app.service.NotificationService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;

@Path("/notifications")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class NotificationResource {

    private final NotificationService service;
    private final NotificationMapper mapper;

    public NotificationResource(NotificationService service, NotificationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GET
    public List<NotificationResponse> list(
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
    public NotificationResponse get(@PathParam("notificationId") UUID notificationId) {
        var e = service.get(notificationId);
        return mapper.toResponse(e);
    }
}
