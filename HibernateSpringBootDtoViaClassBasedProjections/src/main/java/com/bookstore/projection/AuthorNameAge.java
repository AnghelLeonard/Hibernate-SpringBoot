package com.bookstore.projection;

import java.util.Objects;

public class AuthorNameAge {
    
    private String name;
    private int age;

    public AuthorNameAge(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.name);
        hash = 79 * hash + this.age;
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
        
        final AuthorNameAge other = (AuthorNameAge) obj;
        if (this.age != other.age) {
            return false;
        }
        
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        
        return true;
    }       
}
