
**[How To Retry Transactions After `OptimisticLockException` Exception (`@Version`)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRetryVersionedOptimisticLocking)**

**Note:** Optimistic locking mechanism via `@Version` works for detached entities as well.

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic locking exception. When such exception occur, the application retry the corresponding transaction via [db-util](https://github.com/vladmihalcea/db-util) library developed by Vlad Mihalcea.

**Key points:**
- for Maven, in `pom.xml`, add the `db-util` dependency
- configure the `OptimisticConcurrencyControlAspect` bean
- mark the method (not annotated with `@Transactional`) that is prone to throw (or that calls a method that is prone to throw (this method can be annotated with `@Transactional`)) an optimistic locking exception with `@Retry(times = 10, on = OptimisticLockingFailureException.class)`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootRetryVersionedOptimisticLocking/Retry%20Optimistic%20Lock.png)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

