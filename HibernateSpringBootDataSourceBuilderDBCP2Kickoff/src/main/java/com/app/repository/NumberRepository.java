package com.app.repository;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class NumberRepository implements Serializable, Runnable {

    private static final Logger logger = Logger.getLogger(NumberRepository.class.getName());
    private static final String SQL_INSERT = "INSERT INTO ints (nr) VALUES (?)";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void run() {
        Session hibernateSession = entityManager.unwrap(Session.class);
        hibernateSession.doWork(this::insertSample);
    }

    public void insertSample(Connection connection) {
        try ( PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT)) {

            preparedStatement.setInt(1, new Random().nextInt());
            preparedStatement.execute();

            Thread.sleep((int) (Math.random() * 1000));

            logger.log(Level.INFO, "Processed by {0}", Thread.currentThread().getName());

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "SQL exception", e);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
