package com.lan.app.api.mapper;

import com.lan.app.api.dto.response.CoworkingActiveTariffListItemResponse;
import com.lan.app.api.dto.response.CoworkingActiveTariffResponse;
import com.lan.app.domain.CoworkingActiveTariff;
import com.lan.app.domain.CoworkingActiveTariffListItem;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ApiCoworkingActiveTariffMapper {

    public CoworkingActiveTariffListItemResponse toListItem(CoworkingActiveTariffListItem r) {
        return new CoworkingActiveTariffListItemResponse(
            r.externalId(),
            r.daysUsed(),
            r.dateStart(),
            r.dateEnd()
        );
    }

    public CoworkingActiveTariffResponse toResponse(CoworkingActiveTariff r) {
        return new CoworkingActiveTariffResponse(
            r.id(),
            r.guestId(),
            r.daysUsed(),
            r.dateStart(),
            r.dateEnd(),
            r.tariffId()
        );
    }
}
