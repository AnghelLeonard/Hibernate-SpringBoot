package com.bookstore.impl;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation = Propagation.NEVER)
public class BatchRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements BatchRepository<T, ID> {

    private static final Logger logger = Logger.getLogger(BatchRepositoryImpl.class.getName());

    private final EntityManager entityManager;

    public BatchRepositoryImpl(JpaEntityInformation entityInformation,
            EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.entityManager = entityManager;
    }

    @Override
    public <S extends T> void saveInBatch(Iterable<S> entities) {

        if (entities == null) {
            throw new IllegalArgumentException("The given Iterable of entities cannot be null!");
        }

        BatchExecutor batchExecutor = SpringContext.getBean(BatchExecutor.class);
        batchExecutor.saveInBatch(entities);
    }
}
