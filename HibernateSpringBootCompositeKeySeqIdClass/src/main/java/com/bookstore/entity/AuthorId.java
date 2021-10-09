package com.bookstore.entity;

import java.io.Serializable;
import java.util.Objects;

public class AuthorId implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String name;
    
    private Long authorId;

    public AuthorId() {
    }

    public AuthorId(String name, Long authorId) {
        this.name = name;
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public Long getAuthorId() {
        return authorId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = (int) (23 * hash + this.authorId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        final AuthorId other = (AuthorId) obj;
        if (this.authorId != other.authorId) {
            return false;
        }
        
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        
        return true;
    }        

    @Override
    public String toString() {
        return "AuthorId{" + "name=" + name + ", authorId=" + authorId + '}';
    }        
}
