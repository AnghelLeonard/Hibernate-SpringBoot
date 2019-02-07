package com.jpa;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.NaturalId;

@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NaturalId(mutable = false)
    @Column(nullable = false, updatable = false, unique = true, length = 50)
    private String code;
    
    // @NaturalId(mutable = false)
    // @Column(nullable = false, updatable = false, unique = true)
    // private Long sku;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /*
    public Long getSku() {
        return sku;
    }

    public void setSku(Long sku) {
        this.sku = sku;
    } 
    */

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }
        Product naturalIdProduct = (Product) o;
        return Objects.equals(getCode(), naturalIdProduct.getCode());
        // including sku 
        // return Objects.equals(getCode(), naturalIdProduct.getCode())
            // && Objects.equals(getSku(), naturalIdProduct.getSku());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCode());
        // including sku
        // return Objects.hash(getCode(), getSku());
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", name=" + name + ", code=" + code + '}';
        // including sku
        // return "Product{" + "id=" + id + ", name=" + name + ", code=" + code + ", sku=" + sku + '}';
    }
}
