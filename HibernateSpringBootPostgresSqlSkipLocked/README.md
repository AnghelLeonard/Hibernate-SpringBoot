**[How To Implement Concurrent Table Based Queue Via `SKIP_LOCKED` In PostgreSQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPostgresSqlSkipLocked)**

**Description:** This application is an example of how to implement concurrent table based queue via `SKIP_LOCKED` in PostgreSQL. `SKIP_LOCKED` can skip over locks achieved by other concurrent transactions, therefore is a great choice for implementing job queues. In this application, we run two concurrent transactions. The first transaction will lock the records with ids 1, 2 and 3. The second transaction will skip the records with ids 1, 2 and 3 and will lock the records with ids 4, 5 and 6.

**Key points:**\
     - define an entity that acts as a job queue (e.g., see the `Book` entity)\
     - in `BookRepository` setup `@Lock(LockModeType.PESSIMISTIC_WRITE)`\
     - in `BookRepository` use `@QueryHint` to setup `javax.persistence.lock.timeout` to `SKIP_LOCKED`\
     - rely on `org.hibernate.dialect.PostgreSQL95Dialect` dialect\
     - run two concurrent transactions to see the effect of `SKIP_LOCKED`
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
