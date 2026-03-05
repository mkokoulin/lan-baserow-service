package com.lan.app.infrastructure.baserow.client;

import com.lan.app.infrastructure.baserow.dto.BaserowEventRow;
import com.lan.app.infrastructure.baserow.dto.BaserowListResponse;
import com.lan.app.infrastructure.baserow.dto.BaserowRegistrationRow;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Map;

@RegisterRestClient(configKey = "baserow")
@RegisterClientHeaders(BaserowAuthHeaders.class)
@Path("/api/database/rows/table/{tableId}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface _BaserowEventRegistrationApi {

    @GET
    @Path("/")
    BaserowListResponse<BaserowRegistrationRow> findByExternalId(
        @PathParam("tableId") int tableId,
        @QueryParam("user_field_names") @DefaultValue("true") boolean userFieldNames,
        @QueryParam("filter__external_id__equal") String externalId
    );

    @GET
    @Path("/")
    BaserowListResponse<BaserowRegistrationRow> list(
        @PathParam("tableId") int tableId,
        @QueryParam("user_field_names") @DefaultValue("true") boolean userFieldNames
    );

    @GET
    @Path("/{rowId}/")
    BaserowEventRow getByRowId(
        @PathParam("tableId") int tableId,
        @PathParam("rowId") int rowId,
        @QueryParam("user_field_names") @DefaultValue("true") boolean userFieldNames
    );

    @POST
    @Path("/")
    BaserowRegistrationRow createRow(
        @PathParam("tableId") int tableId,
        Map<String, Object> body,
        @QueryParam("user_field_names") @DefaultValue("true") boolean userFieldNames
    );
}
