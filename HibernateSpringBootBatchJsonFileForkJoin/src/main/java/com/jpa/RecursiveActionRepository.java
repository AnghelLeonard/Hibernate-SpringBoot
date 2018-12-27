package com.jpa;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Scope("prototype")
public class RecursiveActionRepository extends RecursiveAction {

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private ApplicationContext applicationContext;

    private final List<String> jsonList;

    private static final Logger logger = Logger.getLogger(RecursiveActionRepository.class.getName());
    private static final String SQL_INSERT = "INSERT INTO lots (lot) VALUES (?)";

    public RecursiveActionRepository(List<String> jsonList) {
        this.jsonList = jsonList;
    }   

    @Override
    public void compute() {
        if (jsonList.size() > batchSize) {
            ForkJoinTask.invokeAll(createSubtasks());
        } else {
            Session hibernateSession = entityManager.unwrap(Session.class);
            hibernateSession.doWork(this::insertJson);
        }
    }

    private List<RecursiveActionRepository> createSubtasks() {
        List<RecursiveActionRepository> subtasks = new ArrayList<>();

        int size = jsonList.size();

        List<String> jsonListOne = jsonList.subList(0, (size + 1) / 2);
        List<String> jsonListTwo = jsonList.subList((size + 1) / 2, size);

        subtasks.add(applicationContext.getBean(
                RecursiveActionRepository.class, new ArrayList<>(jsonListOne)));
        subtasks.add(applicationContext.getBean(
                RecursiveActionRepository.class, new ArrayList<>(jsonListTwo)));

        return subtasks;
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