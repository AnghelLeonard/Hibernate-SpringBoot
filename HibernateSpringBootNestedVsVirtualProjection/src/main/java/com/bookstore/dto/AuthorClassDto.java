package com.bookstore.dto;

import java.util.Objects;

public class AuthorClassDto {
    
    private String genre;
    private String name;

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }       

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.genre);
        hash = 61 * hash + Objects.hashCode(this.name);
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
        
        final AuthorClassDto other = (AuthorClassDto) obj;
        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }
        
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        
        return true;
    }
          
}
