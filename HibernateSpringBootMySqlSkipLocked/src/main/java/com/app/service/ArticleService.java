package com.app.service;

import java.util.List;
import com.app.entity.Article;
import com.app.entity.ArticleStatus;
import org.springframework.stereotype.Service;
import com.app.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class ArticleService {

    @Autowired
    private TransactionTemplate template;

    private final ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    public void fetchArticlesViaTwoTransactions() {
        template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {
                List<Article> articles = articleRepository
                        .findTop2ByStatus(ArticleStatus.PENDING, new Sort(Sort.Direction.ASC, "id"));

                template.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {
                        List<Article> articles = articleRepository
                                .findTop2ByStatus(ArticleStatus.PENDING, new Sort(Sort.Direction.ASC, "id"));
                        System.out.println("Inner transaction: " + articles);
                    }
                });
                System.out.println("Outer transaction: " + articles);
            }
        });
    }
}
