package com.jpa;

import com.vladmihalcea.concurrent.Retry;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    @Autowired
    private ApplicationContext applicationContext;

    private static final Logger logger = Logger.getLogger(InventoryService.class.getName());

    public Inventory firstTransactionFetchAndReturn() {

        EntityManagerFactory entityManagerFactory
                = applicationContext.getBean(EntityManagerFactory.class);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        logger.info("First transaction fetch entity and return ...");
        entityTransaction.begin();

        Inventory firstInventory = entityManager.find(Inventory.class, 1L);

        entityTransaction.commit();

        return firstInventory;
    }

    public void secondTransactionFetchAndUpdate() {

        EntityManagerFactory entityManagerFactory
                = applicationContext.getBean(EntityManagerFactory.class);

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            logger.info("Second transaction fetch entity and update the quantity (this increase version by 1) ...");
            entityTransaction.begin();

            Inventory firstInventory = entityManager.find(Inventory.class, 1L);
            firstInventory.setQuantity(firstInventory.getQuantity() - 2);

            entityTransaction.commit();
        } catch (RuntimeException e) {

            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }

            throw (e);
        }
    }

    // Retrying doesn't help
    // At merging, Hibernate triggers a SELECT that return a version different
    // from the version of the detached, firstInventory, so an OLE is thrown
    @Retry(times = 10, on = OptimisticLockException.class)
    public void thirdTransactionIsReattachingAndResultsInOLE(Inventory firstInventory) {

        EntityManagerFactory entityManagerFactory
                = applicationContext.getBean(EntityManagerFactory.class);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            logger.info("Third transaction try (and retry) to merge the entity fetched by the first transaction without success ...");
            entityTransaction.begin();

            entityManager.merge(firstInventory);

            entityTransaction.commit();
        } catch (RuntimeException e) {

            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }

            throw (e);
        }
    }
}
