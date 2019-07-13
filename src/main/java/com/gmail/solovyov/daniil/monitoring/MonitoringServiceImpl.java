package com.gmail.solovyov.daniil.monitoring;

import com.gmail.solovyov.daniil.event.EventRepository;
import com.gmail.solovyov.daniil.metric.Metric;
import com.gmail.solovyov.daniil.metric.MetricRepository;
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
        eventRepository.save(eventName);

        Metric metric = new Metric();
        metric.setEventName(eventName);
        metric.setValue(value);
        metric.setParameters(argsToString(args));
        metric.setEventTimestamp(new Timestamp(timestamp));

        metricRepository.save(metric);
    }

    public String argsToString(Object[] args) {
        return Arrays.stream(args)
                .map(Object::toString)
                .collect(Collectors.joining(","));
    }
}