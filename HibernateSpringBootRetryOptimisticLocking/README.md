
81. **[How To Retry Transactions After OptimisticLockException](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRetryOptimisticLocking)**

**Description:** This is a Spring Boot application that simulates a scenario that leads to an `OptimisticLockException`. When such exception occur, the application retry the corresponding transaction via [db-util](https://github.com/vladmihalcea/db-util) library developed by Vlad Mihalcea.

**Key points:**\
     - in pom.xml, add the db-util dependency\
     - Configure the `OptimisticConcurrencyControlAspect` bean\
     - Mark the method that is prone to throw `OptimisticLockException` with `@Retry(times = 10, on = OptimisticLockException.class)`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootRetryOptimisticLocking/Retry%20Optimisti%20Lock.png)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
