package com.gmail.solovyov.daniil.service;

public interface MonitoringService {
    void monitor(String eventName, long value, Object[] args, long timestamp);
}
