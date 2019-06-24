
**[How To Retry Transactions After OptimisticLockException Shaped Via @Version](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRetryOptimisticLocking)**

**Note:** Optimistic locking via `@Version` works for detached entities as well.

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic locking exception. When such exception occur, the application retry the corresponding transaction via [db-util](https://github.com/vladmihalcea/db-util) library developed by Vlad Mihalcea.

**Key points:**\
     - in `pom.xml`, add the `db-util` dependency\
     - Configure the `OptimisticConcurrencyControlAspect` bean\
     - Mark the method that is prone to throw an optimistic locking exception with `@Retry(times = 10, on = OptimisticLockingFailureException.class)`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootRetryVersionedOptimisticLocking/Retry%20Optimistic%20Lock.png)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
