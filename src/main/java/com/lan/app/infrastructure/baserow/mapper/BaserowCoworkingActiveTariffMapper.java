package com.lan.app.infrastructure.baserow.mapper;

import com.lan.app.domain.model.CoworkingActiveTariff;
import com.lan.app.domain.model.CoworkingActiveTariffListItem;
import com.lan.app.infrastructure.baserow.dto.BaserowCoworkingActiveTariffRow;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class BaserowCoworkingActiveTariffMapper {

    public CoworkingActiveTariffListItem toListItem(BaserowCoworkingActiveTariffRow row) {
        return new CoworkingActiveTariffListItem(
            row.externalId(),
            row.daysUsed(),
            row.dateStart(),
            row.dateEnd()
        );
    }

    public CoworkingActiveTariff toDomain(BaserowCoworkingActiveTariffRow row, UUID guestId, UUID tariffId) {
        return new CoworkingActiveTariff(
            row.externalId(),
            guestId,
            row.daysUsed(),
            row.dateStart(),
            row.dateEnd(),
            tariffId
        );
    }
}
