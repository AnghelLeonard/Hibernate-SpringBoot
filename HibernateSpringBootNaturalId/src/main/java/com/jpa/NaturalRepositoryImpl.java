package com.jpa;

import java.io.Serializable;
import java.util.Map;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.NaturalIdLoadAccess;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public abstract class NaturalRepositoryImpl<T, NID extends Serializable>
        implements NaturalRepository<T, NID> {

    @PersistenceContext
    private EntityManager entityManager;

    private final Class<T> entityClass;

    public NaturalRepositoryImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public Optional<T> findBySimpleNaturalId(NID naturalId) {

        Optional<T> entity = entityManager.unwrap(Session.class)
                .bySimpleNaturalId(entityClass)
                .loadOptional(naturalId);

        return entity;
    }

    @Override
    public Optional<T> findByNaturalId(Map<String, Object> naturalIds) {

        NaturalIdLoadAccess<T> loadAccess
                = entityManager.unwrap(Session.class).byNaturalId(entityClass);
        naturalIds.forEach(loadAccess::using);

        return loadAccess.loadOptional();
    }

}
