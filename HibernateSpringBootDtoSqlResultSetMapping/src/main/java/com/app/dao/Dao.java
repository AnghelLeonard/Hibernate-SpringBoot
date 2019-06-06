package com.app.dao;

import com.app.dto.CategoryDto;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    public List<CategoryDto> fetchCategories() {
        Query query = entityManager.createNativeQuery(
                "SELECT t.name AS namet, m.name AS namem, b.name AS nameb "
                + "FROM middle_category m "
                + "INNER JOIN top_category t "
                + "ON t.id=m.top_category_id "
                + "INNER JOIN bottom_category b "
                + "ON m.id=b.middle_category_id",
                "CategoryDtoMapping");
        List<CategoryDto> result = query.getResultList();

        return result;
    }

    protected EntityManager getEntityManager() {
        return entityManager;
    }
}