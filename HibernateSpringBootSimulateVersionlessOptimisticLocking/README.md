**[How To Simulate OptimisticLockException Shaped Via @Version](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSimulateVersionedOptimisticLocking)**

**Note:** Version-less optimistic locking doen't work for detached entities (do not close the persistence context).

**Description:** This is a Spring Boot application that simulates a scenario that leads to an `OptimisticLockException`. So, running the application should end up with an Spring specific `ObjectOptimisticLockingFailureException` exception.

**Key points:**\
     - set up version-less optimistic lcocking mechanism\
     - run a `@Transactional` method from two threads trying to update the same data

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
