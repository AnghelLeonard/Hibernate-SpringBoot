package com.bookstore.jdbcTemplate.dto;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class AuthorExtractor {

    private final JdbcTemplate jdbcTemplate;

    public AuthorExtractor(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<AuthorDto> extract() {

        String sql = "SELECT a.id, a.name, a.genre, b.id, b.title "
                + "FROM author a INNER JOIN book b ON a.id = b.author_id";

        List<AuthorDto> result = jdbcTemplate.query(sql, (ResultSet rs) -> {

            final Map<Long, AuthorDto> authorsMap = new HashMap<>();
            while (rs.next()) {
                Long authorId = (rs.getLong("id"));
                AuthorDto author = authorsMap.get(authorId);
                if (author == null) {
                    author = new AuthorDto();
                    author.setId(rs.getLong("id"));
                    author.setName(rs.getString("name"));
                    author.setGenre(rs.getString("genre"));
                }

                BookDto book = new BookDto();
                book.setId(rs.getLong("id"));
                book.setTitle(rs.getString("title"));

                author.addBook(book);
                authorsMap.putIfAbsent(author.getId(), author);
            }

            return new ArrayList<>(authorsMap.values());
        });

        return result;
    }
}
