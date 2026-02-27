package com.lan.app.api;

import com.lan.app.api.dto.RegistrationCreateRequest;
import com.lan.app.api.dto.RegistrationResponse;
import com.lan.app.api.mapper.RegistrationMapper;
import com.lan.app.service.RegistrationService;
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
public class RegistrationResource {

    private final RegistrationService service;
    private final RegistrationMapper mapper;

    public RegistrationResource(RegistrationService service, RegistrationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GET
    public List<RegistrationResponse> list(
        @QueryParam("page") @DefaultValue("1") int page,
        @QueryParam("size") @DefaultValue("50") int size
    ) {
        return service.list().stream().map(mapper::toResponse).toList();
    }

    @POST
    public Response create(@NotNull @Valid RegistrationCreateRequest req, @Context UriInfo uriInfo) {
        var registration = service.create(mapper.toCommand(req));

        var location = uriInfo.getAbsolutePathBuilder().path(registration.id().toString()).build();

        return Response.created(location)
            .entity(mapper.toResponse(registration))
            .build();
    }

    @GET
    @Path("/{registrationId}")
    public RegistrationResponse get(@PathParam("registrationId") String registrationId) {
        var e = service.get(registrationId);
        return mapper.toResponse(e);
    }
}