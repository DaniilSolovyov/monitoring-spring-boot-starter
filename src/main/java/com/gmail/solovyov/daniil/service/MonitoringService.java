package com.gmail.solovyov.daniil.service;

import com.gmail.solovyov.daniil.domain.Metric;

public interface MonitoringService {
    void monitor(Metric metric) throws InterruptedException;
}
