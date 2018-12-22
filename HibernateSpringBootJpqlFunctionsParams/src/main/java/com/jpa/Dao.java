package com.jpa;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class Dao<T, ID extends Serializable> implements GenericDao<T, ID> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public String fetchNameAndAmount(String symbol, Instant instant) {

        return (String) entityManager.createQuery(
                "select concat_ws(r.name, :symbol, r.amount, :instant) from Royalty r WHERE r.id = 1"
        )
                .setParameter("symbol", symbol)
                .setParameter("instant", instant)
                .getSingleResult();
    }

}
