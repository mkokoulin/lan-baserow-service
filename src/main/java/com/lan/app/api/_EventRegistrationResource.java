package com.lan.app.api;

import com.lan.app.api.dto.request.EventRegistrationCreateRequest;
import com.lan.app.api.dto.response.EventRegistrationResponse;
import com.lan.app.api.mapper.EventRegistrationMapper;
import com.lan.app.service._EventRegistrationService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.util.List;

@Path("/registrations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class _EventRegistrationResource {

    private final _EventRegistrationService service;
    private final EventRegistrationMapper mapper;

    public _EventRegistrationResource(_EventRegistrationService service, EventRegistrationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GET
    public List<EventRegistrationResponse> list(
        @QueryParam("page") @DefaultValue("1") int page,
        @QueryParam("size") @DefaultValue("50") int size
    ) {
        return service.list().stream().map(mapper::toResponse).toList();
    }

    @POST
    public Response create(@NotNull @Valid EventRegistrationCreateRequest req, @Context UriInfo uriInfo) {
        var registration = service.create(mapper.toCommand(req));

        var location = uriInfo.getAbsolutePathBuilder().path(registration.id().toString()).build();

        return Response.created(location)
            .entity(mapper.toResponse(registration))
            .build();
    }

    @GET
    @Path("/{registrationId}")
    public EventRegistrationResponse get(@PathParam("registrationId") String registrationId) {
        var e = service.get(registrationId);
        return mapper.toResponse(e);
    }
}