package com.bookstore.repository.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.SliceImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public abstract class SlicePagingRepositoryImplementation<T> {

    private final String NULL_ENTITY = "Entity cannot be null";
    private final String NULL_PAGEABLE = "Pageable cannot be null";
    private final String NULL_SPECIFICATION = "Specification cannot be null";
    private final String NULL_LOCK_MODE = "Lock mode cannot be null";
    private final String NULL_QUERY_HINTS = "Query hints cannot be null";
    private final String NULL_QUERY = "CriteriaQuery cannot be null";

    @Autowired
    private EntityManager entityManager;

    private final Class<T> entityClass;

    public SlicePagingRepositoryImplementation(final Class<T> entityClass) {
        Objects.requireNonNull(entityClass, NULL_ENTITY);

        this.entityClass = entityClass;
    }

    public Slice<T> findAll(Pageable pageable) {
        Objects.requireNonNull(pageable, NULL_PAGEABLE);

        return findAll((Specification<T>) null, pageable, entityClass,
                LockModeType.NONE, Collections.emptyMap());
    }

    public Slice<T> findAll(Pageable pageable, Map<String, Object> hints) {
        Objects.requireNonNull(pageable, NULL_PAGEABLE);
        Objects.requireNonNull(hints, NULL_QUERY_HINTS);

        return findAll((Specification<T>) null, pageable, entityClass, LockModeType.NONE, hints);
    }

    @Transactional
    public Slice<T> findAll(Pageable pageable, LockModeType lmt) {
        Objects.requireNonNull(pageable, NULL_PAGEABLE);
        Objects.requireNonNull(lmt, NULL_LOCK_MODE);

        return findAll((Specification<T>) null, pageable, entityClass, lmt, Collections.emptyMap());
    }

    @Transactional
    public Slice<T> findAll(Pageable pageable, LockModeType lmt, Map<String, Object> hints) {
        Objects.requireNonNull(pageable, NULL_PAGEABLE);
        Objects.requireNonNull(lmt, NULL_LOCK_MODE);
        Objects.requireNonNull(hints, NULL_QUERY_HINTS);

        return findAll((Specification<T>) null, pageable, entityClass, lmt, hints);
    }

    public Slice<T> findAll(Specification<T> spec, Pageable pageable) {
        Objects.requireNonNull(spec, NULL_SPECIFICATION);
        Objects.requireNonNull(pageable, NULL_PAGEABLE);

        return findAll(spec, pageable, entityClass, LockModeType.NONE, Collections.emptyMap());
    }

    public Slice<T> findAll(Specification<T> spec, Pageable pageable, Map<String, Object> hints) {
        Objects.requireNonNull(spec, NULL_SPECIFICATION);
        Objects.requireNonNull(pageable, NULL_PAGEABLE);
        Objects.requireNonNull(hints, NULL_QUERY_HINTS);

        return findAll(spec, pageable, entityClass, LockModeType.NONE, hints);
    }

    @Transactional
    public Slice<T> findAll(Specification<T> spec, Pageable pageable, LockModeType lmt) {
        Objects.requireNonNull(spec, NULL_SPECIFICATION);
        Objects.requireNonNull(pageable, NULL_PAGEABLE);
        Objects.requireNonNull(lmt, NULL_LOCK_MODE);

        return findAll(spec, pageable, entityClass, lmt, Collections.emptyMap());
    }

    @Transactional
    public Slice<T> findAll(Specification<T> spec, Pageable pageable,
            LockModeType lmt, Map<String, Object> hints) {
        Objects.requireNonNull(spec, NULL_SPECIFICATION);
        Objects.requireNonNull(pageable, NULL_PAGEABLE);
        Objects.requireNonNull(lmt, NULL_LOCK_MODE);
        Objects.requireNonNull(hints, NULL_QUERY_HINTS);

        return findAll(spec, pageable, entityClass, lmt, hints);
    }

    private Slice<T> findAll(Specification<T> spec, Pageable pageable,
            Class<T> entityClass, LockModeType lmt, Map<String, Object> hints) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> rootEntry = applySpecificationToCriteria(spec, entityClass, cq);

        CriteriaQuery<T> all = cq.select(rootEntry);

        if (pageable.getSort().isSorted()) {
            all.orderBy(QueryUtils.toOrders(pageable.getSort(), rootEntry, cb));
        }

        TypedQuery<T> query = applyRepositoryMethodMetadata(
                entityManager.createQuery(all), lmt, hints);

        return this.readSlice(query, pageable);
    }

    // ~ copy-paste from Spring SimpleJpaRepository
    private <S, U extends T> Root<U> applySpecificationToCriteria(
            Specification<U> spec, Class<U> entityClass, CriteriaQuery<S> query) {

        Objects.requireNonNull(entityClass, NULL_ENTITY);
        Objects.requireNonNull(query, NULL_QUERY);

        Root<U> root = query.from(entityClass);

        if (spec == null) {
            return root;
        }

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        Predicate predicate = spec.toPredicate(root, query, builder);

        if (predicate != null) {
            query.where(predicate);
        }

        return root;
    }

    private <S> TypedQuery<S> applyRepositoryMethodMetadata(
            TypedQuery<S> query, LockModeType lmt, Map<String, Object> hints) {

        query.setLockMode(lmt);
        hints.forEach(query::setHint);

        return query;
    }

    private Slice<T> readSlice(TypedQuery<T> query, Pageable pageable) {

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
