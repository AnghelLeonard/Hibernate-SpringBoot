package com.bookstore.profiler;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionSynchronizationAdapter;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@Aspect
@Component
public class TransactionProfiler extends TransactionSynchronizationAdapter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void registerTransactionSyncrhonization() {
        TransactionSynchronizationManager.registerSynchronization(this);

    }

    @Override
    public void afterCompletion(int status) {
        logger.info("After completion (global) ...");
    }

    @Override
    public void afterCommit() {
        logger.info("After commit (global) ...");
    }

    @Override
    public void beforeCompletion() {
        logger.info("Before completion (global) ...");
    }

    @Override
    public void beforeCommit(boolean readOnly) {
        logger.info("Before commit (global) ...");
    }
}
