**[How To Check That Transaction Timeout Works And Rollback At Expiration](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTransactionTimeout)**
 
**Note:** Do not test transaction timeout via `Thread.sleep()`! This is not working! Rely on two transaction and exclusive locks or even better rely on SQL sleep functions (e.g., MySQL, `SELECT SLEEP(n)` seconds, PostgreSQL, `SELECT PG_SLEEP(n)` seconds). Most RDBMS support a sleep function.

**Description:** This application contains several approaches for setting a timeout period for a transaction or query. The timeout is signaled by a speific timeout exception (e.g., `.QueryTimeoutException`). After timeout, the transaction is rollbacked. You can see this in the database and on log via a message of type: `Initiating transaction rollback; Rolling back JPA transaction on EntityManager [SessionImpl(... <open>)]`.

**Key points:**\
     - set global transaction timeout via `spring.transaction.default-timeout` in seconds (see, `application.properties`)\
     - set transaction timeout at method-level via `@Transactional(timeout = n)` in seconds\
     - set query timeout via JPA `javax.persistence.query.timeout` hint in milliseconds\
     - set query timeout via Hibrenate `org.hibernate.timeout` hint in seconds
     
**Note:** If you are using `TransactionTemplate` then the timeout can be set via `TransactionTemplate.setTimeout(n)` in seconds.

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
