**[How To Simulate `OptimisticLockException` In Version-less Optimistic Locking](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSimulateVersionlessOptimisticLocking)**

**Note:** Version-less optimistic locking doesn't work for detached entities (do not close the Persistence Context).

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic locking exception. So, running the application should end up with a Spring specific `ObjectOptimisticLockingFailureException` exception.

**Key points:**
- set up the version-less optimistic locking mechanism
- rely on two concurrent threads that call the same a `@Transactional` method used for updating data

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
