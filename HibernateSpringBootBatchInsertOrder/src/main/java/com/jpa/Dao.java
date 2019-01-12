package com.jpa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class Dao<T, ID extends Serializable> implements GenericDao<T, ID> {

    private static final Logger logger = Logger.getLogger(Dao.class.getName());
       
    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public <S extends T> S persist(S entity) {
        entityManager.persist(entity);

        return entity;
    }

    @Override
    public <S extends T> Iterable<S> saveInBatch(Iterable<S> entities) {

        if(entities == null) {
            throw new IllegalArgumentException("The given Iterable of entities not be null!");
        }
        
        int i = 0;

        List<S> result = new ArrayList<>();

        for (S entity : entities) {
            result.add(persist(entity));

            i++;

            // Flush a batch of inserts and release memory
            if (i % batchSize == 0 && i > 0) {
                logger.log(Level.INFO, 
                        "Flushing the EntityManager containing {0} tournaments ...", i);
                
                entityManager.flush();
                entityManager.clear();
                i = 0;
            }
        }                
                
        if (i > 0) {
            logger.log(Level.INFO, 
                        "Flushing the remaining {0} tournaments ...", i);
            
            entityManager.flush();
            entityManager.clear();
        }    

        return result;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
