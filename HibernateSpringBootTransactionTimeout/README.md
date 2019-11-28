**[How To Check That Transaction Timeout And Rollback At Expiration Works As Expected](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTransactionTimeout)**
 
**Note:** Do not test transaction timeout via `Thread.sleep()`! This is not working! Rely on two transactions and exclusive locks or even better rely on SQL sleep functions (e.g., MySQL, `SELECT SLEEP(n)` seconds, PostgreSQL, `SELECT PG_SLEEP(n)` seconds). Most RDBMS supports a sleep function flavor.

**Description:** This application contains several approaches for setting a timeout period for a transaction or query. The timeout is signaled by a specific timeout exception (e.g., `.QueryTimeoutException`). After timeout, the transaction is rolled back. You can see this in the database (visually or query) and on log via a message of type: `Initiating transaction rollback; Rolling back JPA transaction on EntityManager [SessionImpl(... <open>)]`.

**Key points:**
- set global transaction timeout via `spring.transaction.default-timeout` in seconds (see, `application.properties`)
- set transaction timeout at method-level or class-level via `@Transactional(timeout = n)` in seconds
- set query timeout via JPA `javax.persistence.query.timeout` hint in milliseconds
- set query timeout via Hibrenate `org.hibernate.timeout` hint in seconds
     
**Note:** If you are using `TransactionTemplate` then the timeout can be set via `TransactionTemplate.setTimeout(n)` in seconds.

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

