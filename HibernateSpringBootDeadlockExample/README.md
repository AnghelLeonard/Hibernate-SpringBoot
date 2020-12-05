**[How to simulate a deadlock](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDeadlockExample)**
 
**Description:** This application is an example of causing a database deadlock in MySQL. This application produces an exception of type: `com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException: Deadlock found when trying to get lock; try restarting transaction`. However, the database will retry until one of the transaction (A) succeeds.

**Key points:**
- start *Transaction (A)* and trigger a `SELECT` with `PESSIMISTIC_WRITE` to acquire an exclusive lock to table `author`
- *Transaction (A)* update `author` genre with success and sleeps for 10s
- after 5s, start a concurrent *Transaction B* that trigger a `SELECT` with `PESSIMISTIC_WRITE` to acquire an exclusive lock to table `book`
- *Transaction (B)* update `book` title with success and sleeps for 10s
- *Transaction (A)* wakes up and attempt to update the book but it cannot acquire the lock holded by *Transaction (B)*
- *Transaction (B)* wakes up and attempt to update the author but it cannot acquire the lock holded by *Transaction (A)*
- DEADLOCK
- database retry and succeeds after *Transaction (B)* releases the lock
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>
 
-----------------------------------------------------------------------------------------------------------------------    
