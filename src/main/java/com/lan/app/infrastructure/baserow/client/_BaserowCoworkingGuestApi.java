package com.lan.app.infrastructure.baserow.client;

import com.lan.app.infrastructure.baserow.dto.BaserowCoworkingGuestRow;
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
public interface _BaserowCoworkingGuestApi {

    @GET
    @Path("/")
    BaserowListResponse<BaserowCoworkingGuestRow> list(
        @PathParam("tableId") int tableId,
        @QueryParam("user_field_names") @DefaultValue("true") boolean userFieldNames
    );

    @GET
    @Path("/{rowId}/")
    BaserowCoworkingGuestRow getByRowId(
        @PathParam("tableId") int tableId,
        @PathParam("rowId") int rowId,
        @QueryParam("user_field_names") @DefaultValue("true") boolean userFieldNames
    );
}
