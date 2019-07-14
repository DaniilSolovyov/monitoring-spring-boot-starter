package com.gmail.solovyov.daniil.service;

import com.gmail.solovyov.daniil.repository.EventRepository;
import com.gmail.solovyov.daniil.domain.Metric;
import com.gmail.solovyov.daniil.repository.MetricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.stream.Collectors;

@Service
public class MonitoringServiceImpl implements MonitoringService {

    private EventRepository eventRepository;
    private MetricRepository metricRepository;

    @Autowired
    public MonitoringServiceImpl(EventRepository eventRepository, MetricRepository metricRepository) {
        this.eventRepository = eventRepository;
        this.metricRepository = metricRepository;
    }

    @Override
    public void monitor(String eventName, long value, Object[] args, long timestamp) {
        Integer eventId = eventRepository.findIdByName(eventName);
        eventId = eventId == null ? eventRepository.create(eventName) : eventId;

        Metric metric = new Metric();
        metric.setEventId(eventId);
        metric.setValue(value);
        metric.setParameters(argsToString(args));
        metric.setEventTimestamp(new Timestamp(timestamp));

        metricRepository.save(metric);
    }

    private String argsToString(Object[] args) {
        return Arrays.stream(args)
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }
}
