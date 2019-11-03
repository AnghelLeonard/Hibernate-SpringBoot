package com.bookstore.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

@JsonInclude(Include.NON_DEFAULT)
public final class AuthorDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private final int age;
    private final String name;
    private final String genre;
    private final String email;
    private final String address;

    public AuthorDto(Map<String, Object> attrs) {
        this.age = (int) attrs.getOrDefault("age", 0);
        this.name = (String) attrs.get("name");
        this.genre = (String) attrs.get("genre");
        this.email = (String) attrs.get("email");
        this.address = (String) attrs.get("address");
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + this.age;
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.genre);
        hash = 61 * hash + Objects.hashCode(this.email);
        hash = 61 * hash + Objects.hashCode(this.address);
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

        final AuthorDto other = (AuthorDto) obj;
        if (this.age != other.age) {
            return false;
        }

        if (!Objects.equals(this.name, other.name)) {
            return false;
        }

        if (!Objects.equals(this.genre, other.genre)) {
            return false;
        }

        if (!Objects.equals(this.email, other.email)) {
            return false;
        }

        if (!Objects.equals(this.address, other.address)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "AuthorDto{" + "age=" + age + ", name=" 
                + name + ", genre=" + genre + ", email=" + email + ", address=" + address + '}';
    }    
}
