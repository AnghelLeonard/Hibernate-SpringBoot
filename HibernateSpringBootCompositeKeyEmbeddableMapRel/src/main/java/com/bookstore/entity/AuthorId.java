package com.bookstore.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class AuthorId implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    @JoinColumn(name = "publisher")
    private Publisher publisher;

    @Column(name = "name")
    private String name;

    public AuthorId() {
    }

    public AuthorId(Publisher publisher, String name) {
        this.publisher = publisher;
        this.name = name;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.publisher);
        hash = 97 * hash + Objects.hashCode(this.name);
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
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        
        if (!Objects.equals(this.publisher, other.publisher)) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "AuthorId{ " + "publisher=" + publisher + ", name=" + name + '}';
    }   
}
