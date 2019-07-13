package com.gmail.solovyov.daniil.repository;

import com.gmail.solovyov.daniil.domain.Monitoring;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MonitoringRepositoryImpl extends BaseRepository implements MonitoringRepository {

    private static final String FIND_ALL_SCRIPT =
            "SELECT M.ID, E.NAME, M.VALUE, M.PARAMETERS, M.EVENT_TIMESTAMP FROM METRIC AS M " +
                    "LEFT JOIN EVENT E on M.EVENT_ID = E.ID";

    @Override
    public List<Monitoring> findAll() {
        return jdbc.query(FIND_ALL_SCRIPT, new BeanPropertyRowMapper<>(Monitoring.class));
    }
}
