**[How `PESSIMISTIC_WRITE` Works With `UPDATE`/`INSERT` And `DELETE` Operations](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPessimisticLocksDelInsUpd)**
 
**Description:** This application is an example of triggering `UPDATE`, `INSERT` and `DELETE` operations in the context of `PESSIMISTIC_WRITE` locking against MySQL. While `UPDATE` and `DELETE` are blocked until the exclusive lock is released, `INSERT` depends on the transaction isolation level. Typically, even with exclusive locks, inserts are possible (e.g., in PostgreSQL). In MySQL, for the default isolation level, `REPEATABLE READ`, inserts are prevented against a range of locked entries, but, if we switch to `READ_COMMITTED`, then MySQL acts as PostgreSQL as well.

**Key points:**
- start *Transaction A* and trigger a `SELECT` with `PESSIMISTIC_WRITE` to acquire an exclusive lock
- start a concurrent *Transaction B* that triggers an `UPDATE`, `INSERT` or `DELETE` on the rows locked by *Transaction A*
- in case of `UPDATE`, `DELETE` and `INSERT` + `REPEATABLE_READ`, *Transaction B* is blocked until it timeouts or *Transaction A* releases the exclusive lock
- in case of `INSERT` + `READ_COMMITTED`, *Transaction B* can insert in the range of rows locked by *Transaction A* even if *Transaction A* is holding an exclusive lock on this range     
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
