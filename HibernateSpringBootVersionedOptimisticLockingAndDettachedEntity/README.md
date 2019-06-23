**[Versioned Optimistic Locking And Detached Entities Sample](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootManyToManySetAndOrderBy)**

**Description:** This is a sample application that shows how versioned (`@Version`) optimistic locking and detached entity works. Running the application will result in an optimistic locking specific exception (e.g., the Spring Boot specific, `OptimisticLockingFailureException`).

**Key points:**\
     - in a transaction, fetch an entity via `findById(1L)`; commit transaction and close the persistence context\
     - in a second transaction, fetch an another entity via `findById(1L)` and update it; commit the transaction and close the persistence context\
     - outside transactional context, update the detached entity (fetched in the first transaction)\
     - in a third transaction, call `save()` and pass to it the detached entity; trying to re-attach (`EntityManager.merge()`) the entity will end up in an optimistic locking exception since the version of the detached and just loaded entity don't match

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
