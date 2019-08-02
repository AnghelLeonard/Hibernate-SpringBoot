package com.bookstore.dao;

import java.io.Serializable;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly=true)
public class Dao<T, ID extends Serializable> implements GenericDao<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override    
    public Optional<T> find(Class<T> clazz, ID id) {

        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        return Optional.ofNullable(entityManager.find(clazz, id));
    }

    @Override
    public Optional<T> findViaSession(Class<T> clazz, ID id) {
        
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }
        
        Session session = entityManager.unwrap(Session.class);
        
        return Optional.ofNullable(session.get(clazz, id));
    }
       
}