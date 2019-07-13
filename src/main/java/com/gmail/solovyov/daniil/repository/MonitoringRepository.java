package com.gmail.solovyov.daniil.repository;

import com.gmail.solovyov.daniil.domain.Monitoring;

import java.util.List;

public interface MonitoringRepository {
    List<Monitoring> findAll();
}
