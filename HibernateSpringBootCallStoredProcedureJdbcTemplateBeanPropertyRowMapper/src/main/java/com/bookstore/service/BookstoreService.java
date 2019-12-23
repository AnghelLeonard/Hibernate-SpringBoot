package com.bookstore.service;

import com.bookstore.entity.Author;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookstoreService {

    private final JdbcTemplate jdbcTemplate;

    public BookstoreService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    void init() {
        jdbcTemplate.setResultsMapCaseInsensitive(true);
    }

    @Transactional
    public void fetchAnthologyAuthors() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("FETCH_AUTHOR_BY_GENRE")
                .returningResultSet("AuthorResultSet",
                        BeanPropertyRowMapper.newInstance(Author.class));

        Map<String, Object> authors = simpleJdbcCall.execute(Map.of("p_genre", "Anthology"));

        System.out.println("Result: " + authors.get("AuthorResultSet"));
    }
}
