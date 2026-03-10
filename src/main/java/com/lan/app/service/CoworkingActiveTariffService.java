package com.lan.app.service;

import com.lan.app.domain.model.CoworkingActiveTariff;
import com.lan.app.domain.model.CoworkingActiveTariffListItem;
import com.lan.app.repository.CoworkingActiveTariffRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CoworkingActiveTariffService {

    CoworkingActiveTariffRepository repo;

    public CoworkingActiveTariffService(CoworkingActiveTariffRepository repo) {
        this.repo = repo;
    }

    public List<CoworkingActiveTariffListItem> list() {
        return repo.list();
    }

    public CoworkingActiveTariff get(UUID externalId) {
        return repo.get(externalId);
    }
}
