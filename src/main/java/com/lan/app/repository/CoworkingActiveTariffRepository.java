package com.lan.app.repository;

import com.lan.app.domain.CoworkingActiveTariff;
import com.lan.app.domain.CoworkingActiveTariffListItem;

import java.util.List;
import java.util.UUID;

public interface CoworkingActiveTariffRepository {
    List<CoworkingActiveTariffListItem> list();
    CoworkingActiveTariff get(UUID externalId);
}
