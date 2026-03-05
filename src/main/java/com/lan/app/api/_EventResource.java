package com.lan.app.api;

import com.lan.app.api.dto.EventResponse;
import com.lan.app.api.mapper.EventResponseMapper;
import com.lan.app.service._EventService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;
import java.util.UUID;

@Path("/events")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class _EventResource {

    private final _EventService service;
    private final EventResponseMapper mapper;

    public _EventResource(_EventService service, EventResponseMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GET
    public List<EventResponse> list(
        @QueryParam("page") @DefaultValue("1") int page,
        @QueryParam("size") @DefaultValue("50") int size
    ) {
        return service.list(page, size).stream().map(mapper::toResponse).toList();
    }

    @GET
    @Path("/{eventId}")
    public EventResponse get(@PathParam("eventId") UUID eventId) {
        var e = service.get(eventId);
        return mapper.toResponse(e);
    }
}
