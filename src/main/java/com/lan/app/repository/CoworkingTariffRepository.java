package com.lan.app.repository;

import com.lan.app.domain.model.CoworkingTariff;

import java.util.List;
import java.util.UUID;

public interface CoworkingTariffRepository {
    List<CoworkingTariff> list();
    CoworkingTariff get(UUID externalId);
}
