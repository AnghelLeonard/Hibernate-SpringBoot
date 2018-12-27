package com.jpa;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Scope("prototype")
public class BatchRepository implements Serializable, Runnable {

    private final List<String> jsonList;

    private static final Logger logger = Logger.getLogger(BatchRepository.class.getName());
    private static final String SQL_INSERT = "INSERT INTO lots (lot) VALUES (?)";

    public BatchRepository(List<String> jsonList) {
        this.jsonList = jsonList;
    }

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run() {
        Session hibernateSession = entityManager.unwrap(Session.class);
        hibernateSession.doWork(this::insertJson);
    }

    public void insertJson(Connection connection) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {

            int i = 1;
            for (String jsonLine : jsonList) {
                preparedStatement.setString(1, jsonLine);
                preparedStatement.addBatch();

                if (i % batchSize == 0) {
                    preparedStatement.executeBatch();
                    i = 0;
                }

                i++;
            }

            if (i > 1) {
                preparedStatement.executeBatch();
            }
            
            logger.log(Level.INFO, "Processed by {0}", Thread.currentThread().getName());

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL exception", e);
        }
    }
}
