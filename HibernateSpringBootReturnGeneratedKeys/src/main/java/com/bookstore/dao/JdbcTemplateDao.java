package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JdbcTemplateDao implements AuthorDao {

    private static final String SQL_INSERT
            = "INSERT INTO author (age, name, genre) VALUES (?, ?, ?);";

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional    
    public long insertAuthor(int age, String name, String genre) {

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection
                    .prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            
            // or
            // PreparedStatement ps = connection
            //      .prepareStatement(SQL_INSERT, new String[]{"id"});
            
            // or
            // PreparedStatement ps = connection
            //      .prepareStatement(SQL_INSERT, new int[] {1});
            
            ps.setInt(1, age);
            ps.setString(2, name);
            ps.setString(3, genre);
            
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }
}
