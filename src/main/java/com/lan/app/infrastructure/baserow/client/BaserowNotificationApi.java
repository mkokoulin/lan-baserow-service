package com.lan.app.infrastructure.baserow.client;

import com.lan.app.infrastructure.baserow.dto.BaserowGuestRow;
import com.lan.app.infrastructure.baserow.dto.BaserowListResponse;
import com.lan.app.infrastructure.baserow.dto.BaserowNotificationRow;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "baserow")
@RegisterClientHeaders(BaserowAuthHeaders.class)
@Path("/api/database/rows/table/{tableId}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface BaserowNotificationApi {
    @GET
    @Path("/")
    BaserowListResponse<BaserowNotificationRow> list(
        @PathParam("tableId") int tableId,
        @QueryParam("user_field_names") @DefaultValue("true") boolean userFieldNames
    );

    @GET
    @Path("/")
    BaserowListResponse<BaserowNotificationRow> findByExternalId(
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
