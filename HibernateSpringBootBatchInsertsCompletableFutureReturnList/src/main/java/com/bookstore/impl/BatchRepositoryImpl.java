package com.bookstore.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.NEVER)
public class BatchRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements BatchRepository<T, ID> {

    private final EntityManager entityManager;

    public BatchRepositoryImpl(JpaEntityInformation entityInformation,
            EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.entityManager = entityManager;
    }

    @Override
    public <S extends T> List<S> saveInBatch(List<S> entities) {

        if (entities == null) {
            throw new IllegalArgumentException("The given Iterable of entities cannot be null!");
        }

        BatchExecutor batchExecutor = SpringContext.getBean(BatchExecutor.class);
        try {
            return batchExecutor.saveInBatch(entities);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
            // log exception
        } catch (ExecutionException ex) {
            // log exception
        }
        
        return Collections.emptyList();
    }
}
