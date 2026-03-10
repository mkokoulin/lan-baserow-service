package com.lan.app.service;

import com.lan.app.domain.model.CoworkingTariff;
import com.lan.app.repository.CoworkingTariffRepository;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class CoworkingTariffService {

    CoworkingTariffRepository repo;

    public CoworkingTariffService(CoworkingTariffRepository repo) {
        this.repo = repo;
    }

    public List<CoworkingTariff> list() {
        return repo.list();
    }

    public CoworkingTariff get(UUID externalId) {
        return repo.get(externalId);
    }
}
