package com.app.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class BatchRepository {

    private static final Logger logger = Logger.getLogger(BatchRepository.class.getName());
    private static final String SQL_INSERT = "INSERT INTO lots (lot) VALUES (?)";

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @PersistenceContext
    private EntityManager entityManager;

    public void batch(List<String> jsonList) {

        Session hibernateSession = entityManager.unwrap(Session.class);
        hibernateSession.doWork((connection) -> {

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

                logger.info(() -> "Processed by: " + Thread.currentThread().getName());

            } catch (SQLException e) {
                logger.log(Level.SEVERE, "SQL exception", e);
            }
        });
    }
}
