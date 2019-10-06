package com.bookstore.service;

import com.bookstore.entity.Chapter;
import com.bookstore.entity.Introduction;
import com.bookstore.entity.Summary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import java.util.logging.Logger;
import com.bookstore.repository.ChapterRepository;
import com.bookstore.repository.IntroductionRepository;
import com.bookstore.repository.SummaryRepository;

@Service
public class BookstoreService {

    private static final Logger log = Logger.getLogger(BookstoreService.class.getName());

    private final TransactionTemplate template;
    private final ChapterRepository chapterRepository;
    private final IntroductionRepository introductionRepository;
    private final SummaryRepository summaryRepository;

    public BookstoreService(ChapterRepository chapterRepository,
            IntroductionRepository introductionRepository,
            SummaryRepository summaryRepository,
            TransactionTemplate template) {
        this.chapterRepository = chapterRepository;
        this.introductionRepository = introductionRepository;
        this.summaryRepository = summaryRepository;
        this.template = template;
    }

    // running this application will
    // throw org.springframework.orm.ObjectOptimisticLockingFailureException
    public void buildChapter() {
        template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                log.info("Starting first transaction ...");

                Chapter chapter = chapterRepository.findById(1L).orElseThrow();

                Introduction intro = new Introduction();
                intro.setContent("In this chapter, ...");
                intro.setChapter(chapter);

                template.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {

                        log.info("Starting second transaction ...");

                        Chapter chapter = chapterRepository.findById(1L).orElseThrow();

                        Summary summary = new Summary();
                        summary.setContent("Let's summarize ...");
                        summary.setChapter(chapter);

                        summaryRepository.save(summary);

                        log.info("Commit second transaction ...");
                    }
                });

                introductionRepository.save(intro);

                log.info("Commit first transaction ...");
            }
        });

        System.out.println("Done!");
    }
}
