package com.gmail.solovyov.daniil.repository;

import com.gmail.solovyov.daniil.monitoring.MonitoringConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class BaseRepository {
    @Autowired
    @Qualifier(MonitoringConfig.MONITORING)
    protected JdbcTemplate jdbc;
}
