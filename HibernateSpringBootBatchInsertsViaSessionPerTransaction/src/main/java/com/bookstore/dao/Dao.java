package com.bookstore.dao;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository
public class Dao<T, ID extends Serializable> implements GenericDao<T, ID> {

    private static final Logger logger = Logger.getLogger(Dao.class.getName());

    private static final int BATCH_SIZE = 30;       
    
    private final EntityManagerFactory entityManagerFactory;

    public Dao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }        
        
    @Override
    public <S extends T> void saveInBatch(Iterable<S> entities) {

        if (entities == null) {
            throw new IllegalArgumentException("The given Iterable of entities not be null!");
        }

        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        
        Session session = entityManager.unwrap(Session.class);
        session.setJdbcBatchSize(BATCH_SIZE);

        try {
            entityTransaction.begin();

            int i = 0;
            for (S entity: entities) {
                if (i % session.getJdbcBatchSize() == 0 && i > 0) {
                logger.log(Level.INFO, 
                        "Flushing the EntityManager containing {0} entities ...", session.getJdbcBatchSize());
                
                    entityTransaction.commit();
                    entityTransaction.begin();

                    entityManager.clear();
                }
                
                entityManager.persist(entity);
                i++;
            }

            logger.log(Level.INFO, 
                        "Flushing the remaining entities ...");
            
            entityTransaction.commit();
        } catch (RuntimeException e) {
            if (entityTransaction.isActive()) {
                entityTransaction.rollback();
            }
            
            throw e;
        } finally {
            entityManager.close();
        }
    }   
}
