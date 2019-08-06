package com.bookstore.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class Dao<T, ID extends Serializable> implements GenericDao<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public T findByName(String name) {
        T result = (T) entityManager.
                createQuery("SELECT a FROM Author a WHERE a.name=:name")
                .setParameter("name", name)
                .getSingleResult();

        return result;
    }

    @Override
    public T findByNameViaSession(String name) {

        Session session = entityManager.unwrap(Session.class);
        T result = (T) session.createQuery("SELECT a FROM Author a WHERE a.name=:name")
                .setParameter("name", name)
                .getSingleResult();

        return result;
    }
}