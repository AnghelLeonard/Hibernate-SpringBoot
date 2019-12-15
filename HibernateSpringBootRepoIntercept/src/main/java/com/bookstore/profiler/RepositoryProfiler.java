package com.bookstore.profiler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class RepositoryProfiler {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(public * org.springframework.data.repository.Repository+.*(..))")
    public void intercept() {
    }

    @Around("intercept()")
    public Object profile(ProceedingJoinPoint joinPoint) {

        long startMs = System.currentTimeMillis();

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            logger.error(e.getMessage(), e);
            // do whatever you want with the exception
        }

        long elapsedMs = System.currentTimeMillis() - startMs;

        // you may like to use logger.debug
        logger.info(joinPoint.getTarget() + "." + joinPoint.getSignature()
                + ": Execution time: " + elapsedMs + " ms");

        // pay attention that this line may return null
        return result;
    }
}
