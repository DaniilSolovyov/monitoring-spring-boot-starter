package com.gmail.solovyov.daniil.repository.impl;

import com.gmail.solovyov.daniil.domain.Metric;
import com.gmail.solovyov.daniil.repository.MetricRepository;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class MetricRepositoryImpl implements MetricRepository {

    private static final String INSERT_SCRIPT =
            "INSERT INTO METRIC (EVENT_NAME, VALUE, PARAMETERS, EVENT_TIMESTAMP) VALUES (?,?,?,?)";

    private static final String FIND_ALL_SCRIPT =
            "SELECT ID, EVENT_NAME, VALUE, PARAMETERS, EVENT_TIMESTAMP FROM METRIC";

    private JdbcTemplate jdbcTemplate;

    public MetricRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(List<Metric> metrics) {
        jdbcTemplate.batchUpdate(INSERT_SCRIPT, getBatchArgs(metrics));
    }

    @Override
    public List<Metric> findAll() {
        return jdbcTemplate.query(FIND_ALL_SCRIPT, new BeanPropertyRowMapper<>(Metric.class));
    }

    private List<Object[]> getBatchArgs(List<Metric> metrics) {
        return metrics.stream()
                .map(metric -> new Object[]{
                        metric.getEventName(),
                        metric.getValue(),
                        metric.getParameters(),
                        metric.getEventTimestamp()
                }).collect(Collectors.toList());
    }
}
