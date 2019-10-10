package com.bookstore.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;

public class AuthorId implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;
    
    @Column(name = "age")
    private int age;

    public AuthorId() {
    }

    public AuthorId(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + this.age;
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
        if (this.age != other.age) {
            return false;
        }
        
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        
        return true;
    }        

    @Override
    public String toString() {
        return "AuthorId{" + "name=" + name + ", age=" + age + '}';
    }        
}
