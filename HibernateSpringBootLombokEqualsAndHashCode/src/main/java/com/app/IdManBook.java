package com.app;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class IdManBook implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String title;
    private String isbn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        IdManBook other = (IdManBook) obj;
        return Objects.equals(id, other.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
