package com.jpa.repository.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public abstract class SlicePagingRepositoryImplementation<T> {

    private static final String NULL_ENTITY = "Entity cannot be null";
    
    @Autowired
    protected EntityManager entityManager;

    private final Class<T> entityClass;

    public SlicePagingRepositoryImplementation(Class<T> entityClass) {

        Objects.requireNonNull(entityClass, NULL_ENTITY);

        this.entityClass = entityClass;
    }

    public Slice<T> findAll(Pageable pageable) {
        final SimpleSliceJpaRepository sliceRepository
                = new SimpleSliceJpaRepository(entityClass, entityManager);
        return sliceRepository.findAll(pageable);
    }
    
    public Slice<T> findAll(Specification<T> spec, Pageable pageable) {
        SimpleSliceJpaRepository sliceRepository
                = new SimpleSliceJpaRepository(entityClass, entityManager);
        return sliceRepository.findAll(spec, pageable);
    }    

    public static class SimpleSliceJpaRepository<T, ID extends Serializable>
            extends SimpleJpaRepository<T, ID> {

        public SimpleSliceJpaRepository(Class<T> entityClass, EntityManager entityManager) {
            super(entityClass, entityManager);
        }

        @Override
        protected <S extends T> Page<S> readPage(TypedQuery<S> query, Class<S> entityClass,
                Pageable pageable, Specification<S> spec) {
    
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());

            final List<S> content = query.getResultList();            

            return new PageImpl<>(content, pageable, content.size());
        }
    }
}