**[How To Retry Transactions After OptimisticLockException Shaped Via Hibernate Versionless Optimistic Locking](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootVersionlessOptimisticLocking)**

**Note:** Optimistic locking via Hibernate versionless doesn't work for detached entities (don't close the persistent context).

**Description:** This is a Spring Boot application that simulates a scenario that leads to an `OptimisticLockException` via Hibernate versionless optimistic locking. When such exception occur, the application retry the corresponding transaction via [db-util](https://github.com/vladmihalcea/db-util) library developed by Vlad Mihalcea.

**Key points:**\
     - in pom.xml, add the db-util dependency\
     - Configure the `OptimisticConcurrencyControlAspect` bean\
     - Annotate the corresponding entity (e.g., `Inventory`) with `@DynamicUpdate` and `@OptimisticLocking(type = OptimisticLockType.DIRTY)` - more details in my book, ["Java Persistence Performance Illustrated Guide"](https://leanpub.com/java-persistence-performance-illustrated-guide)\
     - Mark the method that is prone to throw `OptimisticLockException` with `@Retry(times = 10, on = OptimisticLockException.class)`
     
-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
