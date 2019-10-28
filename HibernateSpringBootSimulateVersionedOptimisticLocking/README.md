**[How To Simulate `OptimisticLockException` Shaped Via `@Version`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSimulateVersionedOptimisticLocking)**

**Note:** Optimistic locking via `@Version` works for detached entities as well.

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic locking exception. So, running the application should end up with a Spring specific `ObjectOptimisticLockingFailureException` exception.

**Key points:**
- set up versioned optimistic locking mechanism
- rely on two concurrent threads that call the same `@Transactional` method used for updating data

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
