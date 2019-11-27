**[How `PESSIMISTIC_READ` And `PESSIMISTIC_WRITE` Works In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPessimisticLocks)**
 
**Description:** This application is an example of using `PESSIMISTIC_READ` and `PESSIMISTIC_WRITE` in MySQL. In a nutshell, each database system defines its own syntax for acquiring shared and exclusive locks and not all databases support both types of locks. Depending on `Dialect`, the syntax can vary for the same database as well (Hibernate relies on `Dialect` for chosing the proper syntax). In MySQL, `MySQL5Dialect` doesn't support locking, while InnoDB engine (`MySQL5InnoDBDialect` and `MySQL8Dialect`) supports shared and exclusive locks as expected.

**Key points:**
- rely on `@Lock(LockModeType.PESSIMISTIC_READ)` and `@Lock(LockModeType.PESSIMISTIC_WRITE)` on query-level
- for testing, use `TransactionTemplate` to trigger two concurrent transactions that read and write the same row
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
