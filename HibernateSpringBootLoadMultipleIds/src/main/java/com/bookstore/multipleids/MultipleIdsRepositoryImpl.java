package com.bookstore.multipleids;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.MultiIdentifierLoadAccess;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public abstract class MultipleIdsRepositoryImpl<T, ID extends Serializable>
        implements MultipleIdsRepository<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> entityClass;

    public MultipleIdsRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public List<T> fetchByMultipleIds(List<ID> ids) {

        Session session = entityManager.unwrap(Session.class);
        MultiIdentifierLoadAccess<T> multiLoadAccess
                = session.byMultipleIds(entityClass);
        List<T> result = multiLoadAccess.multiLoad(ids);

        return result;
    }

    @Override
    public List<T> fetchInBatchesByMultipleIds(List<ID> ids, int batchSize) {

        List<T> result = getMultiLoadAccess().withBatchSize(batchSize).multiLoad(ids);

        return result;
    }

    @Override
    public List<T> fetchBySessionCheckMultipleIds(List<ID> ids) {

        List<T> result = getMultiLoadAccess().enableSessionCheck(true).multiLoad(ids);

        return result;
    }

    @Override
    public List<T> fetchInBatchesBySessionCheckMultipleIds(List<ID> ids, int batchSize) {

        List<T> result = getMultiLoadAccess().enableSessionCheck(true)
                .withBatchSize(batchSize).multiLoad(ids);

        return result;
    }

    private MultiIdentifierLoadAccess<T> getMultiLoadAccess() {

        Session session = entityManager.unwrap(Session.class);

        return session.byMultipleIds(entityClass);
    }

}
