package com.bookstore.service;

import com.bookstore.entity.Inventory;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private final ApplicationContext applicationContext;

    public InventoryService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private static final Logger logger = Logger.getLogger(InventoryService.class.getName());

    public Inventory firstTransactionFetchAndReturn() {

        EntityManagerFactory entityManagerFactory
                = applicationContext.getBean(EntityManagerFactory.class);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        logger.info("First transaction fetches entity and return ...");
        entityTransaction.begin();

        Inventory firstInventory = entityManager.find(Inventory.class, 1L);

        entityTransaction.commit();
        logger.info("First transaction committed successfully...");

        return firstInventory;
    }

    public void secondTransactionMergesInventory(Inventory firstInventory) {

        EntityManagerFactory entityManagerFactory
                = applicationContext.getBean(EntityManagerFactory.class);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            logger.info("Second transaction merges the detached entity ...");
            entityTransaction.begin();

            entityManager.merge(firstInventory);

            entityTransaction.commit();
            logger.info("Third transaction committed successfully...");
        } catch (RuntimeException e) {

            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }

            throw (e);
        }
    }
}
