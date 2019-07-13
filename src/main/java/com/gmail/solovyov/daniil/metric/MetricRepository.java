package com.gmail.solovyov.daniil.metric;

import java.util.List;

public interface MetricRepository {
    void save(Metric metric);

    List<Metric> findAll();
}
