package com.jpa;

import java.io.Serializable;
import java.util.List;
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
    public List<T> findByName(String name) {
        List<T> results = entityManager.
                createQuery("select u from User u where u.name=:name")
                .setParameter("name", name)
                .getResultList();

        return results;
    }

    @Override
    public List<T> findByNameViaSession(String name) {
        
        Session session = entityManager.unwrap(Session.class);        
        List<T> results = session.createQuery("select u from User u where u.name=:name")
                .setParameter("name", name)
                .list();
        
        return results;
    }
}
