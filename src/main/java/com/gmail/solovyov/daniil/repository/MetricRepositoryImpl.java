package com.gmail.solovyov.daniil.repository;

import com.gmail.solovyov.daniil.domain.Metric;
import org.springframework.stereotype.Repository;

@Repository
public class MetricRepositoryImpl extends BaseRepository implements MetricRepository {

    private static final String CREATE_SCRIPT = "INSERT INTO METRIC (EVENT_ID, VALUE, PARAMETERS, EVENT_TIMESTAMP) " +
            "VALUES (?, ?, ?, ?)";

    @Override
    public void save(Metric metric) {
        jdbc.update(CREATE_SCRIPT, ps -> {
            int i = 0;
            ps.setInt(++i, metric.getEventId());
            ps.setLong(++i, metric.getValue());
            ps.setString(++i, metric.getParameters());
            ps.setTimestamp(++i, metric.getEventTimestamp());
        });
    }
}
