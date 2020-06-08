package com.bookstore.dto;

public class AuthorGenreDto {

    private String genre;

    public AuthorGenreDto(String genre) {
        this.genre = genre;
    }   
    
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }                
}
