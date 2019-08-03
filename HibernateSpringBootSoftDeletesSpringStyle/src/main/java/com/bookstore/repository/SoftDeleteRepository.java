package com.bookstore.repository;

import com.bookstore.entity.BaseEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface SoftDeleteRepository<T extends BaseEntity, ID extends Long>
        extends JpaRepository<T, ID> {

    @Override
    @Transactional(readOnly = true)
    @Query("SELECT e FROM #{#entityName} e WHERE e.id=?1 AND e.deleted = false")
    public Optional<T> findById(ID id);

    @Override
    @Transactional(readOnly = true)
    default List<T> findAllById(Iterable<ID> ids) {
        List<T> results = new ArrayList<>();
        for (ID id : ids) {
            findById(id).ifPresent(results::add);
        }

        return results;
    }

    @Override
    @Transactional(readOnly = true)
    @Query("SELECT e FROM #{#entityName} e WHERE e.deleted = false")
    public List<T> findAll();
    
    @Transactional(readOnly = true)
    @Query("SELECT e FROM #{#entityName} e WHERE e.deleted = true")
    public List<T> findAllDeleted();

    @Override
    @Transactional(readOnly = true)
    @Query("SELECT e FROM #{#entityName} e WHERE e.deleted = false")
    public List<T> findAll(Sort sort);
    
    @Transactional(readOnly = true)
    @Query("SELECT e FROM #{#entityName} e WHERE e.deleted = true")
    public List<T> findAllDeleted(Sort sort);

    @Override
    @Transactional(readOnly = true)
    @Query("SELECT e FROM #{#entityName} e WHERE e.deleted = false")
    public Page<T> findAll(Pageable pageable);
    
    @Transactional(readOnly = true)
    @Query("SELECT e FROM #{#entityName} e WHERE e.deleted = true")
    public Page<T> findAllDeleted(Pageable pageable);

    @Override
    @Transactional(readOnly = true)
    @Query("SELECT COUNT(e) FROM #{#entityName} e WHERE e.deleted = false")
    public long count();
    
    @Transactional(readOnly = true)
    @Query("SELECT COUNT(e) FROM #{#entityName} e WHERE e.deleted = true")
    public long countDeleted();

    @Override
    @Query("UPDATE #{#entityName} e SET e.deleted=true WHERE e.id = ?1")
    @Transactional
    @Modifying
    public void deleteById(ID id);
    
    @Query("UPDATE #{#entityName} e SET e.deleted=false WHERE e.id = ?1")
    @Transactional
    @Modifying
    public void undeleteById(ID id);

    @Override
    @Transactional
    default void delete(T entity) {
        deleteById((ID) entity.getId());
    }

    @Override
    @Query("UPDATE #{#entityName} e SET e.deleted=true")
    @Transactional
    @Modifying
    public void deleteAll();

    @Override
    @Transactional
    default void deleteAll(Iterable<? extends T> entities) {
        entities.forEach(e -> deleteById((ID) e.getId()));
    }     
}