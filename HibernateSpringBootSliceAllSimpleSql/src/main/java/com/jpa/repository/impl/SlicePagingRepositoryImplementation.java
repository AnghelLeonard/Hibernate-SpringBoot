package com.jpa.repository.impl;

import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public abstract class SlicePagingRepositoryImplementation<T> {

    public static final String NULL_ENTITY = "Entity cannot be null";
    public static final String NULL_PAGEABLE = "Pageable cannot be null";
    
    @Autowired
    private EntityManager entityManager;

    private final Class<T> entityClass;

    public SlicePagingRepositoryImplementation(Class<T> entityClass) {
        
        Objects.requireNonNull(entityClass, NULL_ENTITY);        
        
        this.entityClass = entityClass;
    }

    public Slice<T> findAll(Pageable pageable) {
        Objects.requireNonNull(pageable, NULL_PAGEABLE);        
        
        return findAll(pageable, entityClass);
    }

    private Slice<T> findAll(Pageable pageable, Class<T> entityClass) {

        final String sql = "SELECT e FROM " + entityClass.getSimpleName() + " e";
        TypedQuery<T> query = entityManager.createQuery(sql, entityClass);

        return this.readSlice(query, pageable);
    }

    private Slice<T> readSlice(final TypedQuery<T> query, final Pageable pageable) {

        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize() + 1);

        final List<T> content = query.getResultList();

        boolean hasNext = content.size() == (pageable.getPageSize() + 1);

        if (hasNext) {
            content.remove(content.size() - 1);
        }

        return new SliceImpl<>(content, pageable, hasNext);
    }
}