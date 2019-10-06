package com.bookstore.service;

import com.bookstore.entity.Chapter;
import com.bookstore.entity.EditorModification;
import com.bookstore.entity.AuthorModification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import java.util.logging.Logger;
import com.bookstore.repository.ChapterRepository;
import com.bookstore.repository.AuthorModificationRepository;
import com.bookstore.repository.EditorModificationRepository;

@Service
public class BookstoreService {

    private static final Logger log = Logger.getLogger(BookstoreService.class.getName());

    private final TransactionTemplate template;
    private final ChapterRepository chapterRepository;
    private final AuthorModificationRepository authorModificationRepository;
    private final EditorModificationRepository editorModificationRepository;

    public BookstoreService(ChapterRepository chapterRepository,
            AuthorModificationRepository authorModificationRepository,
            EditorModificationRepository editorModificationRepository,
            TransactionTemplate template) {
        this.chapterRepository = chapterRepository;
        this.authorModificationRepository = authorModificationRepository;
        this.editorModificationRepository = editorModificationRepository;
        this.template = template;
    }

    // running this application will
    // throw org.springframework.orm.ObjectOptimisticLockingFailureException
    public void editChapter() {
        template.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);
        template.execute(new TransactionCallbackWithoutResult() {
            @Override
            protected void doInTransactionWithoutResult(TransactionStatus status) {

                log.info("Starting first transaction ...");

                Chapter chapter = chapterRepository.findById(1L).orElseThrow();

                EditorModification editorModification = new EditorModification();
                editorModification.setDescription("Rewording first paragraph");
                editorModification.setModification("Deleted: ... Added: ...");
                editorModification.setChapter(chapter);

                template.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {

                        log.info("Starting second transaction ...");

                        Chapter chapter = chapterRepository.findById(1L).orElseThrow();

                        AuthorModification authorModification = new AuthorModification();
                        authorModification.setDescription("Deleting first paragraph");
                        authorModification.setModification("Deleted ...");
                        authorModification.setChapter(chapter);

                        authorModificationRepository.save(authorModification);

                        log.info("Commit second transaction ...");
                    }
                });

                editorModificationRepository.save(editorModification);

                log.info("Commit first transaction ...");
            }
        });

        log.info("Done!");
    }
}
