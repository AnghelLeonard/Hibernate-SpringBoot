**[How To Retry Transaction After `OptimisticLockException` Exception (Hibernate Version-less Optimistic Locking Mechanism)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRetryVersionlessOptimisticLocking)**

**Note:** Optimistic locking mechanism via Hibernate version-less doesn't work for detached entities (don't close the Persistent Context).

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic locking exception (e.g., in Spring Boot, `OptimisticLockingFailureException`) via Hibernate version-less optimistic locking. When such exception occur, the application retry the corresponding transaction via [db-util](https://github.com/vladmihalcea/db-util) library developed by Vlad Mihalcea.

**Key points:**
- for Maven, in `pom.xml`, add the `db-util` library dependency
- configure the `OptimisticConcurrencyControlAspect` bean
- annotate the corresponding entity (e.g., `Inventory`) with `@DynamicUpdate` and `@OptimisticLocking(type = OptimisticLockType.DIRTY)`
- mark the method (not annotated with `@Transactional`) that is prone to throw (or that calls a method that is prone to throw (this method can be annotated with `@Transactional`)) an optimistic locking exception with `@Retry(times = 10, on = OptimisticLockingFailureException.class)`
     
-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
