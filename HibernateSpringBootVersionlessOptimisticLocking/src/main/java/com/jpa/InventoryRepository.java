package com.jpa;

import com.vladmihalcea.concurrent.Retry;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.OptimisticLockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope("prototype")
public class InventoryRepository implements Serializable, Runnable {

    private final int quantity;
    private final int delay;

    @Autowired
    private ApplicationContext applicationContext;

    private static final Logger logger = Logger.getLogger(InventoryRepository.class.getName());

    public InventoryRepository(int quantity, int delay) {
        this.quantity = quantity;
        this.delay = delay;
    }

    @Override
    @Retry(times = 10, on = OptimisticLockException.class)
    public void run() {

        EntityManagerFactory entityManagerFactory
                = applicationContext.getBean(EntityManagerFactory.class);

        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction entityTransaction = entityManager.getTransaction();

        try {
            entityTransaction.begin();

            Inventory inventory = entityManager.find(Inventory.class, 1L);

            if ((inventory.getQuantity() - quantity) >= 0) {
                inventory.setQuantity(inventory.getQuantity() - quantity);
            }

            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            entityTransaction.commit();

            logger.info("Transaction committed ...");
        } catch (RuntimeException e) {

            if (is(e, OptimisticLockException.class)) {
                logger.log(Level.INFO, "OptimisticLockException has occured ...");
                logger.log(Level.INFO, "Retry mechanism enter into the scene ...");
            }

            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }

            throw (e);
        }
    }

    public static <T extends Throwable> boolean is(Throwable exception, Class<T> type) {
        Throwable unwrappedException = exception;

        while (unwrappedException != null) {
            if (type.isInstance(unwrappedException)) {
                return true;
            }

            unwrappedException = unwrappedException.getCause();
        }

        return false;
    }
}
