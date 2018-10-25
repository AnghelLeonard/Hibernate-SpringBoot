package com.jpa;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.hibernate.transform.AliasToBeanConstructorResultTransformer;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class Dao<T, ID extends Serializable> implements GenericDao<T, ID> {
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public <S extends T> S persist(S entity) {
        
        Objects.requireNonNull(entity, "Cannot persist a null entity");
        
        entityManager.persist(entity);
        
        return entity;
    }
    
    @Transactional(readOnly = true)
    public List<CarDtoNoSetters> fetchCarsNoSetters() {
        Query query = entityManager
                .createNativeQuery("select c.name as name, c.color as color from Car c")
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(
                        new AliasToBeanConstructorResultTransformer(
                                CarDtoNoSetters.class.getConstructors()[0]
                        )
                );
        
        List<CarDtoNoSetters> result = query.getResultList();
        
        return result;
    }
    
    @Transactional(readOnly = true)
    public List<CarDtoWithSetters> fetchCarsWithSetters() {
        Query query = entityManager
                .createNativeQuery("select c.name as name, c.color as color from Car c")
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(
                        Transformers.aliasToBean(CarDtoWithSetters.class)
                );        

        List<CarDtoWithSetters> result = query.getResultList();
        
        return result;
    }    
    
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
