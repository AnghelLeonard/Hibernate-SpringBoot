package com.bookstore.service;

import com.bookstore.AuthorDto;
import com.bookstore.entity.Author;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

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

    public List<AuthorDto> fetchNicknameAndAgeByGenre() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("FETCH_NICKNAME_AND_AGE_BY_GENRE")
                .returningResultSet("AuthorResultSet",
                        BeanPropertyRowMapper.newInstance(AuthorDto.class));

        Map<String, Object> authors = simpleJdbcCall.execute(Map.of("p_genre", "Anthology"));
        
        return (List<AuthorDto>) authors.get("AuthorResultSet");
    }

    public List<Author> fetchAnthologyAuthors() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("FETCH_AUTHOR_BY_GENRE")
                .returningResultSet("AuthorResultSet",
                        BeanPropertyRowMapper.newInstance(Author.class));

        Map<String, Object> authors = simpleJdbcCall.execute(Map.of("p_genre", "Anthology"));

        return (List<Author>) authors.get("AuthorResultSet");
    }

}
