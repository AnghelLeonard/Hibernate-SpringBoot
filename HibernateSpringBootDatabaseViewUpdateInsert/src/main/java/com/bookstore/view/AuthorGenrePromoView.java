package com.bookstore.view;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "author_genre_promo_view")
public class AuthorGenrePromoView implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    private String genre;
    private String promotionFlag;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPromotionFlag() {
        return promotionFlag;
    }

    public void setPromotionFlag(String promotionFlag) {
        this.promotionFlag = promotionFlag;
    }

    @Override
    public String toString() {
        return "AuthorView{" + "id=" + id + ", genre=" + genre
                + ", promotionFlag=" + promotionFlag + '}';
    }
}
