package com.app.repository;

import java.util.List;
import javax.persistence.LockModeType;
import javax.persistence.QueryHint;
import com.app.entity.Article;
import com.app.entity.ArticleStatus;
import org.hibernate.LockOptions;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @QueryHints({
        @QueryHint(name = "javax.persistence.lock.timeout", value = "" + LockOptions.SKIP_LOCKED)})
    public List<Article> findTop2ByStatus(ArticleStatus status, Sort sort);
}
