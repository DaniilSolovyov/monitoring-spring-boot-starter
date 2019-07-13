package com.gmail.solovyov.daniil.metric;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class MetricRepositoryImpl implements MetricRepository {

    private static final String SAVE_SCRIPT = "INSERT INTO METRIC (EVENT_ID, VALUE, PARAMETERS, EVENT_TIMESTAMP) " +
            "VALUES ((SELECT ID FROM EVENT WHERE NAME = ?), ?, ?, ?)";

    private static final String FIND_ALL_SCRIPT =
            "SELECT M.ID, E.NAME, M.VALUE, M.PARAMETERS, M.EVENT_TIMESTAMP FROM METRIC AS M " +
                    "LEFT JOIN EVENT E on M.EVENT_ID = E.ID";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MetricRepositoryImpl(@Qualifier("monitoring") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Metric metric) {
//        jdbcTemplate.update(SAVE_SCRIPT, metric.getEventName(), metric.getValue(), metric.getParameters(),
//                metric.getEventTimestamp());
        jdbcTemplate.update(SAVE_SCRIPT, ps -> {
            int i = 0;
            ps.setString(++i, metric.getEventName());
            ps.setLong(++i, metric.getValue());
            ps.setString(++i, metric.getParameters());
            ps.setTimestamp(++i, metric.getEventTimestamp());
        });
    }

    @Override
    public List<Metric> findAll() {
//        return jdbcTemplate.query(FIND_ALL_SCRIPT, new BeanPropertyRowMapper<>(Metric.class));
//        return jdbcTemplate.query(FIND_ALL_SCRIPT, this::mapRow);
        return jdbcTemplate.query(FIND_ALL_SCRIPT, (rs, rowNumber) -> {
            int i = 0;
            Metric metric = new Metric();
            metric.setId(rs.getInt(++i));
            metric.setEventName(rs.getString(++i));
            metric.setParameters(rs.getString(++i));
            Timestamp timestamp = rs.getTimestamp("EVENT_TIMESTAMP");
//        Timestamp timestamp = rs.getTimestamp(++i);
//        rs.getTime
            metric.setEventTimestamp(timestamp);
            return metric;
        });
    }

//    private void setValues(PreparedStatementSetter ps){
//
//    }

//    private Metric mapRow(ResultSet rs, int rowNumber) throws SQLException {
//        int i = 0;
//        Metric metric = new Metric();
//        metric.setId(rs.getInt(++i));
//        metric.setEventName(rs.getString(++i));
//        metric.setParameters(rs.getString(++i));
//        Timestamp timestamp = rs.getTimestamp("EVENT_TIMESTAMP");
////        Timestamp timestamp = rs.getTimestamp(++i);
////        rs.getTime
//        metric.setEventTimestamp(timestamp);
//        return metric;
//    }
}
