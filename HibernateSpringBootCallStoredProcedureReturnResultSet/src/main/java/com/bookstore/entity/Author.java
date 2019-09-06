package com.bookstore.entity;

import com.bookstore.dto.AuthorDto;
import java.io.Serializable;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.StoredProcedureParameter;

@Entity
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
            name = "FetchByGenreProcedure",
            procedureName = "FETCH_AUTHOR_BY_GENRE",
            resultClasses = {Author.class},
            parameters = {
                @StoredProcedureParameter(
                        name = "p_genre",
                        type = String.class,
                        mode = ParameterMode.IN)})
})
@SqlResultSetMapping(name = "AuthorDtoMapping",
        classes = @ConstructorResult(targetClass = AuthorDto.class,
                columns = {
                    @ColumnResult(name = "nickname"),
                    @ColumnResult(name = "age")}))
public class Author implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int age;
    private String name;
    private String genre;
    private String nickname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "Author{" + "id=" + id + ", age=" + age + ", name=" + name
                + ", genre=" + genre + ", nickname=" + nickname + '}';
    }
}
