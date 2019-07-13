package com.gmail.solovyov.daniil.monitoring;

public interface MonitoringService {
    void monitor(String eventName, long value, Object[] args, long timestamp);
}
