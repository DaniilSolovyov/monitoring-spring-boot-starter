package com.gmail.solovyov.daniil.repository;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class EventRepositoryImpl extends BaseRepository implements EventRepository {

    private static final String CREATE_SCRIPT = "INSERT INTO EVENT (NAME) VALUES (?)";
    private static final String FIND_ID_BY_NAME_SCRIPT = "SELECT ID FROM EVENT WHERE NAME = ?";

    @Override
    public Integer findIdByName(String name) {
        List<Integer> list = jdbc.query(FIND_ID_BY_NAME_SCRIPT, new Object[]{name}, (rs, i) -> rs.getInt(1));
        return DataAccessUtils.singleResult(list);
    }

    @Override
    public Integer create(String name) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbc.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(CREATE_SCRIPT, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, name);
            return ps;
        }, keyHolder);

        return (Integer) keyHolder.getKeys().get("id");
    }
}
