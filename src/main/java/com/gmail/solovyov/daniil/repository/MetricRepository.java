package com.gmail.solovyov.daniil.repository;

import com.gmail.solovyov.daniil.domain.Metric;

import java.util.List;

public interface MetricRepository {
    void saveAll(List<Metric> metrics);

    List<Metric> findAll();
}
