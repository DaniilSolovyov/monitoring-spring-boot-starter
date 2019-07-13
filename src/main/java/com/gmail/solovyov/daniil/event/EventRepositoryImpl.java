package com.gmail.solovyov.daniil.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepositoryImpl implements EventRepository {

//    private static final String FIND_EVENT_ID_BY_NAME = "SELECT ID FROM EVENT WHERE NAME = ?";

    private static final String SAVE_SCRIPT = "INSERT INTO EVENT (NAME) VALUES (?) ON CONFLICT (NAME) DO NOTHING";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public EventRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(String name) {
        jdbcTemplate.update(SAVE_SCRIPT, name);
    }

//    @Override
//    public int findEventIdByName(String name) {
////        return jdbcTemplate.update(FIND_EVENT_ID_BY_NAME, name);
////        return jdbcTemplate.queryForObject(FIND_EVENT_ID_BY_NAME, name);
////        Integer id = jdbcTemplate.queryForObject(FIND_EVENT_ID_BY_NAME, Integer.class, name);
////        return id != null ? id : 0;
//        DataAccessUtils.requiredSingleResult(jdbcTemplate.query(FIND_EVENT_ID_BY_NAME, name, resultSet -> resultSet.getInt());
//    }

//    @Override
//    public int save(String name) {
//        return 0;
//    }

}
