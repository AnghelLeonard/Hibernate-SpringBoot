package com.bookstore.service;

import com.bookstore.dto.AuthorDto;
import com.bookstore.dto.NicknameAndAgeDto;
import com.bookstore.dto.GenreAndAgeDto;
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
    
    public void fetchNicknameGenreAndAgeByGenre() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("FETCH_NICKNAME_GENRE_AND_AGE_BY_GENRE")
                .returningResultSet("#result-set-1",
                        BeanPropertyRowMapper.newInstance(NicknameAndAgeDto.class))
                .returningResultSet("#result-set-2",
                        BeanPropertyRowMapper.newInstance(GenreAndAgeDto.class));
        
        Map<String, Object> authors = simpleJdbcCall.execute(Map.of("p_genre", "Anthology"));

        List<NicknameAndAgeDto> nicknameAndAge = (List<NicknameAndAgeDto>) authors.get("#result-set-1");
        List<GenreAndAgeDto> genreAndAge = (List<GenreAndAgeDto>) authors.get("#result-set-2");
        
        System.out.println("Nickname and age:" + nicknameAndAge);
        System.out.println("Genre and age:" + genreAndAge);
    }

    public List<Author> fetchAnthologyAuthors() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("FETCH_AUTHOR_BY_GENRE")
                .returningResultSet("AuthorResultSet",
                        BeanPropertyRowMapper.newInstance(Author.class));

        Map<String, Object> authors = simpleJdbcCall.execute(Map.of("p_genre", "Anthology"));

        return (List<Author>) authors.get("AuthorResultSet");
    }

    public AuthorDto fetchNicknameAndAgeById() {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("FETCH_NICKNAME_AND_AGE_BY_ID");

        Map<String, Object> author = simpleJdbcCall.execute(Map.of("p_id", 1));

        AuthorDto authorDto = new AuthorDto();
        authorDto.setNickname((String) author.get("o_nickname"));
        authorDto.setAge((int) author.get("o_age"));

        return authorDto;
    }
}
