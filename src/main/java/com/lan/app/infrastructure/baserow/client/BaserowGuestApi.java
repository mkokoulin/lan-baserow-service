package com.lan.app.infrastructure.baserow.client;

import com.lan.app.infrastructure.baserow.dto.BaserowEventRow;
import com.lan.app.infrastructure.baserow.dto.BaserowGuestRow;
import com.lan.app.infrastructure.baserow.dto.BaserowListResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "baserow")
@RegisterClientHeaders(BaserowAuthHeaders.class)
@Path("/api/database/rows/table/{tableId}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface BaserowGuestApi {

    @GET
    @Path("/")
    BaserowListResponse<BaserowGuestRow> list(
        @PathParam("tableId") int tableId,
        @QueryParam("user_field_names") @DefaultValue("true") boolean userFieldNames
    );

    @GET
    @Path("/")
    BaserowListResponse<BaserowGuestRow> findByExternalId(
        @PathParam("tableId") int tableId,
        @QueryParam("user_field_names") @DefaultValue("true") boolean userFieldNames,
        @QueryParam("filter__external_id__equal") String externalId
    );

    @GET
    @Path("/{rowId}/")
    BaserowGuestRow getByRowId(
        @PathParam("tableId") int tableId,
        @PathParam("rowId") int rowId,
        @QueryParam("user_field_names") @DefaultValue("true") boolean userFieldNames
    );
}
