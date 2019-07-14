package com.gmail.solovyov.daniil.service.impl;

import com.gmail.solovyov.daniil.domain.Metric;
import com.gmail.solovyov.daniil.monitoring.MonitoringProperties;
import com.gmail.solovyov.daniil.repository.MetricRepository;
import com.gmail.solovyov.daniil.service.MonitoringService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;


@Service
public class MonitoringServiceImpl implements MonitoringService {

    private static final Logger logger = LoggerFactory.getLogger(MonitoringService.class);

    private MetricRepository metricRepository;
    private BlockingQueue<Metric> queue;
    private Integer batchSize;
    private Integer batchThreshold;
    private ScheduledExecutorService executorService;

    public MonitoringServiceImpl(MetricRepository metricRepository, MonitoringProperties monitoringProperties) {
        this.metricRepository = metricRepository;
        this.queue = new ArrayBlockingQueue<>(monitoringProperties.getQueueSize());
        this.batchSize = monitoringProperties.getBatchSize();
        this.batchThreshold = monitoringProperties.getBatchThreshold();
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    @PostConstruct
    public void asyncTask() {

        Runnable batchInsert = () -> {
            if (queue.size() > batchThreshold) {
                List<Metric> batch = new ArrayList<>(batchSize);
                queue.drainTo(batch, batchSize);
                metricRepository.saveAll(batch);
                logger.debug("Batch size - {}", batch.size());
            }
        };

        executorService.scheduleAtFixedRate(batchInsert, 1, 1, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void destroy() {
        executorService.shutdown();
    }

    @Override
    public void monitor(Metric metric) throws InterruptedException {
            queue.put(metric);
    }
}
