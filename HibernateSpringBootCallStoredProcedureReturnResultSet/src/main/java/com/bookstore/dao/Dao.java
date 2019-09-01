package com.bookstore.dao;

import com.bookstore.dto.AuthorDto;
import com.bookstore.entity.Author;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import org.hibernate.procedure.ProcedureOutputs;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class Dao {

    private static final String GENRE_PARAM = "p_genre";

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public List<Author> fetchByGenre1(String genre) {

        StoredProcedureQuery storedProcedure
                = entityManager.createNamedStoredProcedureQuery("FetchByGenreProcedure");
        storedProcedure.setParameter(GENRE_PARAM, genre);

        List<Author> storedProcedureResults;
        try {
            storedProcedureResults = storedProcedure.getResultList();
        } finally {
            storedProcedure.unwrap(ProcedureOutputs.class).release();
        }

        return storedProcedureResults;
    }

    @Transactional
    public List<Author> fetchByGenre2(String genre) {

        StoredProcedureQuery storedProcedure
                = entityManager.createStoredProcedureQuery("FETCH_AUTHOR_BY_GENRE", Author.class);

        storedProcedure.registerStoredProcedureParameter(GENRE_PARAM, String.class, ParameterMode.IN);
        storedProcedure.setParameter(GENRE_PARAM, genre);

        List<Author> storedProcedureResults;
        try {
            storedProcedureResults = storedProcedure.getResultList();
        } finally {
            storedProcedure.unwrap(ProcedureOutputs.class).release();
        }

        return storedProcedureResults;
    }

    @Transactional
    public List<AuthorDto> fetchByGenre3(String genre) {

        StoredProcedureQuery storedProcedure
                = entityManager.createStoredProcedureQuery(
                        "FETCH_NAME_AND_AGE_BY_GENRE", "AuthorDtoMapping");

        storedProcedure.registerStoredProcedureParameter(GENRE_PARAM, String.class, ParameterMode.IN);
        storedProcedure.setParameter(GENRE_PARAM, genre);

        List<AuthorDto> storedProcedureResults;
        try {
            storedProcedureResults = storedProcedure.getResultList();
        } finally {
            storedProcedure.unwrap(ProcedureOutputs.class).release();
        }

        return storedProcedureResults;
    }

    @Transactional
    public List<AuthorDto> fetchByGenre4(String genre) {

        StoredProcedureQuery storedProcedure
                = entityManager.createStoredProcedureQuery("FETCH_NAME_AND_AGE_BY_GENRE");

        storedProcedure.registerStoredProcedureParameter(GENRE_PARAM, String.class, ParameterMode.IN);
        storedProcedure.setParameter(GENRE_PARAM, genre);

        List<AuthorDto> storedProcedureResults;
        try {
            List<Object[]> storedProcedureObjects = storedProcedure.getResultList();

            storedProcedureResults = storedProcedureObjects.stream()
                    .map(result -> new AuthorDto(
                    (String) result[0],
                    (Integer) result[1]
            )).collect(Collectors.toList());
        } finally {
            storedProcedure.unwrap(ProcedureOutputs.class).release();
        }

        return storedProcedureResults;
    }
}
