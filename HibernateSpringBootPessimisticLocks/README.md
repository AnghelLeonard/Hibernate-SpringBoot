**[How `PESSIMISTIC_READ` And `PESSIMISTIC_WRITE` Works In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPessimisticLocks)**
 
**Description:** This application is an example of using `PESSIMISTIC_READ` and `PESSIMISTIC_WRITE` in MySQL. In a nutshell, each database system defines its own syntax for acquiring shared and exclusive locks and not all databases support both types of locks. Depending on dialect, the syntax can vary for the database as well. In MySQL, `MySQL5Dialect` doesn't support locking, while InnoDB engines (`MySQL5InnoDBDialect` and `MySQL8Dialect`) supports shared and exclusive locks as expected.

**Key points:**\
     - rely on `@Lock(LockModeType.PESSIMISTIC_READ)` and `@Lock(LockModeType.PESSIMISTIC_WRITE)` on query-level\
     - for testing, use `TransactionTemplate` to trigger two concurrent transactions that read and write the same row
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
