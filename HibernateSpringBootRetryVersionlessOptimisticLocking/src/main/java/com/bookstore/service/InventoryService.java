package com.bookstore.service;

import com.bookstore.repository.InventoryRepository;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    private static final Logger logger = Logger.getLogger(InventoryService.class.getName());

    private final ApplicationContext applicationContext;

    public InventoryService(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    private static final ExecutorService executor
            = Executors.newFixedThreadPool(2);

    public void forceOptimisticLockException() {

        // Start the first transaction
        // This transaction will decrease the inventory quantity by two units
        // Right before committing, this transaction is put on sleep for 5 seconds
        logger.info("Starting first transaction ...");
        InventoryRepository thread1
                = applicationContext.getBean(InventoryRepository.class, 2, 5000);
        executor.execute(thread1);

        // Give some time to the first transaction to start
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        // Start the second transaction
        // This transaction will decrease the inventory quantity by one unit (from 10 to 9)
        // This transaction will execute and commit without waiting
        // While this transaction is running, the first one sleeps, so when the
        // first transaction wakes up it will cause an OptimisticLockException since
        // this transaction will decrease the quantity from 10 to 9
        // But, at retry, it will work as expected since the first transaction will try to decrease the 
        // quantity from 9 to 7
        logger.info("Starting second transaction ...");
        InventoryRepository thread2
                = applicationContext.getBean(InventoryRepository.class, 1, 1);
        executor.execute(thread2);

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
