package com.bookstore.dao;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class Dao<T, ID extends Serializable> implements GenericDao<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public String fetchTitleAndPrice(String symbol, Instant instant) {

        return (String) entityManager.createQuery(
                "SELECT concat_ws(b.title, :symbol, b.price, :instant) FROM Book b WHERE b.id = 1"
        )
                .setParameter("symbol", symbol)
                .setParameter("instant", instant)
                .getSingleResult();
    }

}
