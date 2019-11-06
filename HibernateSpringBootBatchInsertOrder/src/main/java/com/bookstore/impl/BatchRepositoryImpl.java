package com.bookstore.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import org.hibernate.dialect.Dialect;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class BatchRepositoryImpl<T, ID extends Serializable>
        extends SimpleJpaRepository<T, ID> implements BatchRepository<T, ID> {

    private static final Logger logger = Logger.getLogger(BatchRepositoryImpl.class.getName());

    private final EntityManager entityManager;

    public BatchRepositoryImpl(JpaEntityInformation entityInformation,
            EntityManager entityManager) {
        super(entityInformation, entityManager);

        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public <S extends T> void saveInBatch(Iterable<S> entities) {

        if (entities == null) {
            throw new IllegalArgumentException("The given Iterable of entities cannot be null!");
        }

        int i = 0;
        for (S entity : entities) {
            entityManager.persist(entity);

            i++;

            // Flush a batch of inserts and release memory
            if (i % batchSize() == 0 && i > 0) {
                logger.log(Level.INFO,
                        "Flushing the EntityManager containing {0} entities ...", i);

                entityManager.flush();
                entityManager.clear();
                i = 0;
            }
        }

        if (i > 0) {
            logger.log(Level.INFO,
                    "Flushing the remaining {0} entities ...", i);

            entityManager.flush();
            entityManager.clear();
        }
    }

    private static int batchSize() {

        int batchsize = Integer.valueOf(Dialect.DEFAULT_BATCH_SIZE); // default batch size

        Properties configuration = new Properties();
        try (InputStream inputStream = BatchRepositoryImpl.class.getClassLoader()
                .getResourceAsStream("application.properties")) {
            configuration.load(inputStream);
        } catch (IOException ex) {
            logger.log(Level.SEVERE,
                    "Cannot fetch batch size. Using further Dialect.DEFAULT_BATCH_SIZE{0}", ex);
            return batchsize;
        }

        String batchsizestr = configuration.getProperty(
                "spring.jpa.properties.hibernate.jdbc.batch_size");
        if (batchsizestr != null) {
            batchsize = Integer.valueOf(batchsizestr);
        }

        return batchsize;
    }
}
