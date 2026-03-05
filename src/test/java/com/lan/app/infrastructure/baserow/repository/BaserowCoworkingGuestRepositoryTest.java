//package com.lan.app.infrastructure.baserow.repository;
//
//import com.lan.app.domain.CoworkingGuest;
//import com.lan.app.infrastructure.baserow.client.BaserowClient;
//import com.lan.app.infrastructure.baserow.dto.BaserowCoworkingGuestRow;
//import com.lan.app.infrastructure.baserow.dto.CreateCoworkingGuestRowRequest;
//import com.lan.app.infrastructure.baserow.mapper.BaserowCoworkingGuestMapper;
//
//import org.junit.jupiter.api.Test;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class BaserowCoworkingGuestRepositoryTest {
//
//    @Test
//    void create_sendsCorrectDataToClient() {
//
//        // fake client
//        class FakeClient implements BaserowClient {
//            int capturedTableId;
//            boolean capturedUserFieldNames;
//            CreateCoworkingGuestRowRequest capturedBody;
//
//            @Override
//            public Optional<Integer> findRowIdByExternalId(int tableId, java.util.UUID externalId) {
//                return Optional.empty();
//            }
//
//            @Override
//            public BaserowCoworkingGuestRow createCoworkingGuest(
//                int tableId,
//                CreateCoworkingGuestRowRequest body,
//                boolean userFieldNames
//            ) {
//                this.capturedTableId = tableId;
//                this.capturedBody = body;
//                this.capturedUserFieldNames = userFieldNames;
//
//                return new BaserowCoworkingGuestRow(
//                    1,
//                    body.externalId(),
//                    body.firstName(),
//                    body.lastName(),
//                    body.phone(),
//                    body.telegram()
//                );
//            }
//        }
//
//        var fakeClient = new FakeClient();
//
//        var mapper = new BaserowCoworkingGuestMapper() {
//            @Override
//            public CoworkingGuest toDomain(BaserowCoworkingGuestRow row) {
//                return new CoworkingGuest(
//                    row.externalId(),
//                    row.firstName(),
//                    row.lastName(),
//                    row.phone(),
//                    row.telegram()
//                );
//            }
//        };
//
//        var repo = new BaserowCoworkingGuestRepository(
//            123,
//            fakeClient,
//            null,
//            mapper
//        );
//
//        // act
//        var result = repo.create("Misha", "K", "+37499111222", "@mk");
//
//        // assert
//        assertNotNull(result);
//
//        assertEquals(123, fakeClient.capturedTableId);
//        assertTrue(fakeClient.capturedUserFieldNames);
//
//        assertEquals("Misha", fakeClient.capturedBody.firstName());
//        assertEquals("K", fakeClient.capturedBody.lastName());
//        assertEquals("+37499111222", fakeClient.capturedBody.phone());
//        assertEquals("@mk", fakeClient.capturedBody.telegram());
//
//        // UUID проверяем что вообще сгенерился
//        assertNotNull(fakeClient.capturedBody.externalId());
//        assertEquals(36, fakeClient.capturedBody.externalId().length());
//    }
//}