package com.citylots.forkjoin;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class JoiningComponent {

    private static final String SQL_INSERT = "INSERT INTO lots (lot) VALUES (?)";

    private final JdbcTemplate jdbcTemplate;

    public JoiningComponent(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void executeBatch(List<String> jsonList) {

        jdbcTemplate.batchUpdate(SQL_INSERT,
                new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement pStmt, int i)
                    throws SQLException {
                String jsonLine = jsonList.get(i);
                pStmt.setString(1, jsonLine);
            }

            @Override
            public int getBatchSize() {
                return jsonList.size();
            }
        });
    }
}
