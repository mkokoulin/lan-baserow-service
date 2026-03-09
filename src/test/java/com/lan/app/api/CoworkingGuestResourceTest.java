package com.lan.app.api;

import com.lan.app.api.dto.response.CoworkingGuestResponse;
import com.lan.app.api.dto.request.CreateCoworkingGuestRequest;
import com.lan.app.api.dto.request.UpdateCoworkingGuestRequest;
import com.lan.app.api.mapper.ApiCoworkingGuestMapper;
import com.lan.app.domain.CoworkingGuest;
import com.lan.app.service.CoworkingGuestService;
import com.lan.app.service.command.UpdateCoworkingGuestCommand;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CoworkingGuestResourceTest {

    @Mock
    CoworkingGuestService service;

    @Mock
    ApiCoworkingGuestMapper mapper;

    @InjectMocks
    CoworkingGuestResource resource;

    @Test
    void get_callsService_andReturnsMappedResponse() {
        var externalId = UUID.randomUUID();

        var guest = new CoworkingGuest(externalId, "A", "B", "@tg", "+374");
        var expected = new CoworkingGuestResponse(externalId, "A", "B", "@tg", "+374");

        when(service.get(externalId)).thenReturn(guest);
        when(mapper.toResponse(guest)).thenReturn(expected);

        var actual = resource.get(externalId);

        assertSame(expected, actual);

        verify(service).get(externalId);
        verify(mapper).toResponse(guest);
        verifyNoMoreInteractions(service, mapper);
    }

    @Test
    void create_returns201_created_setsLocation_andEntity() {
        var externalId = UUID.randomUUID();

        var req = new CreateCoworkingGuestRequest("Misha", "K.", "@misha", "+374000000");

        var createdGuest = new CoworkingGuest(externalId, "Misha", "K.", "@misha", "+374000000");

        var responseDto = new CoworkingGuestResponse(externalId, "Misha", "K.", "@misha", "+374000000");

        when(service.create("Misha", "K.", "+374000000", "@misha")).thenReturn(createdGuest);
        when(mapper.toResponse(createdGuest)).thenReturn(responseDto);

        Response resp = resource.create(req);

        assertEquals(201, resp.getStatus());
        assertNotNull(resp.getLocation());
        assertEquals("/coworking/guests/" + externalId, resp.getLocation().toString());
        assertSame(responseDto, resp.getEntity());

        verify(service).create("Misha", "K.", "+374000000", "@misha");
        verify(mapper).toResponse(createdGuest);
        verifyNoMoreInteractions(service, mapper);
    }

    @Test
    void update_mapsRequestToCommand_callsService_andReturnsMappedResponse() {
        var externalId = UUID.randomUUID();

        var req = new UpdateCoworkingGuestRequest("New", null, "+374111", "@new");
        var cmd = new UpdateCoworkingGuestCommand("New", null, "+374111", "@new");

        var updatedGuest = new CoworkingGuest(externalId, "New", "B", "@new", "+374111");
        var responseDto = new CoworkingGuestResponse(externalId, "New", "B", "@new", "+374111");

        when(mapper.toCommand(req)).thenReturn(cmd);
        when(service.update(externalId, cmd)).thenReturn(updatedGuest);
        when(mapper.toResponse(updatedGuest)).thenReturn(responseDto);

        var actual = resource.update(externalId, req);

        assertSame(responseDto, actual);

        verify(mapper).toCommand(req);
        verify(service).update(externalId, cmd);
        verify(mapper).toResponse(updatedGuest);
        verifyNoMoreInteractions(service, mapper);
    }

    @Test
    void create_passesCorrectArgs_toService_evenIfRequestOrderDiffers() {
        var req = new CreateCoworkingGuestRequest("Misha", "K.", "@misha", "+374000000");

        when(service.create(anyString(), anyString(), anyString(), anyString()))
            .thenReturn(new CoworkingGuest(UUID.randomUUID(), "Misha", "K.", "@misha", "+374000000"));
        when(mapper.toResponse(any())).thenReturn(new CoworkingGuestResponse(UUID.randomUUID(), "Misha", "K.", "@misha", "+374000000"));

        resource.create(req);

        var first = ArgumentCaptor.forClass(String.class);
        var last = ArgumentCaptor.forClass(String.class);
        var phone = ArgumentCaptor.forClass(String.class);
        var telegram = ArgumentCaptor.forClass(String.class);

        verify(service).create(first.capture(), last.capture(), phone.capture(), telegram.capture());

        assertEquals("Misha", first.getValue());
        assertEquals("K.", last.getValue());
        assertEquals("+374000000", phone.getValue());
        assertEquals("@misha", telegram.getValue());
    }
}