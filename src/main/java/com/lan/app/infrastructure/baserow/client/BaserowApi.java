package com.lan.app.infrastructure.baserow.client;

import com.lan.app.infrastructure.baserow.dto.BaserowEventRow;
import com.lan.app.infrastructure.baserow.dto.BaserowListResponse;
import com.lan.app.infrastructure.baserow.dto.BaserowRegistrationRow;
import jakarta.enterprise.inject.Default;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.annotation.RegisterClientHeaders;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.Map;

@RegisterRestClient(configKey = "baserow")
@RegisterClientHeaders(BaserowAuthHeaders.class)
@Path("/api/database/rows/table/{tableId}") // без слэша в конце
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@ClientHeaderParam(name = "Authorization", value = "Token ${baserow.token}")
public interface BaserowApi<T> {

    @GET
    @Path("/")
    T findByExternalId(
        @PathParam("tableId") int tableId,
        @QueryParam("user_field_names") @DefaultValue("true") boolean userFieldNames,
        @QueryParam("filter__external_id__equal") String externalId
    );

    @GET
    @Path("/{rowId}/")
    T getByRowId(
        @PathParam("tableId") int tableId,
        @PathParam("rowId") int rowId,
        @QueryParam("user_field_names") @DefaultValue("true") boolean userFieldNames
    );

    @GET
    @Path("/?user_field_names=true")
    BaserowListResponse<T> list(
        @PathParam("tableId") int tableId
    );

    @GET
    @Path("/?user_field_names=true")
    BaserowListResponse<T> findByFilter(
        @PathParam("tableId") int tableId,
        @QueryParam("filters") String filtersJson
    );

    @POST
    @Path("/?user_field_names=true")
    BaserowRegistrationRow createRow(
        @PathParam("tableId") int tableId,
        Map<String, Object> body
    );
}