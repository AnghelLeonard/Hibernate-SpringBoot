package com.bookstore.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.StopWatch;

@Component
public class BatchExecutor<T> {

    private static final Logger logger = Logger.getLogger(BatchExecutor.class.getName());

    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    private final TransactionTemplate txTemplate;
    private final EntityManager entityManager;

    private static final ExecutorService executor = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors() - 1);

    public BatchExecutor(TransactionTemplate txTemplate, EntityManager entityManager) {
        this.txTemplate = txTemplate;
        this.entityManager = entityManager;
    }

    public <S extends T> List<S> saveInBatch(List<S> entities)
            throws InterruptedException, ExecutionException {

        txTemplate.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);

        final AtomicInteger count = new AtomicInteger();
        CompletableFuture<List<S>>[] futures = entities.stream()
                .collect(Collectors.groupingBy(c -> count.getAndIncrement() / batchSize))
                .values()
                .stream()
                .map(this::executeBatch)
                .toArray(CompletableFuture[]::new);

        CompletableFuture<Void> run = CompletableFuture.allOf(futures);

        StopWatch timer = new StopWatch();
        timer.start();

        run.get();
        List<S> result = run.thenApply(l -> {
            List<S> persistedAll = new ArrayList<>();

            for (CompletableFuture<List<S>> future : futures) {
                persistedAll.addAll(future.join());
            }
            return persistedAll;
        }).get();

        timer.stop();

        logger.info(() -> "\nBatch time: " + timer.getTotalTimeMillis()
                + " ms (" + timer.getTotalTimeSeconds() + " s)");

        return result;
    }

    public <S extends T> CompletableFuture<List<S>> executeBatch(List<S> list) {

        return CompletableFuture.supplyAsync(() -> {
            return txTemplate.execute(new TransactionCallback<List<S>>() {
                @Override
                public List<S> doInTransaction(TransactionStatus status) {

                    final List<S> listBatch = new ArrayList<>();
                    for (S entity : list) {
                        entityManager.persist(entity);
                        listBatch.add(entity);
                    }

                    return listBatch;
                }
            });
        }, executor);
    }
}
