**[How to increment the version of the locked entity even if this entity was not modified `PESSIMISTIC_FORCE_INCREMENT`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPesimisticForceIncrement)**

**Description:** This application is a sample of how `PESSIMISTIC_FORCE_INCREMENT` works in MySQL. This is useful when you want to increment the version of the locked entity even if this entity was not modified. Via `PESSIMISTIC_FORCE_INCREMENT` the version is updated (incremented) immediately (the entity version update is guaranteed to succeed immediately after acquiring the row-level lock). The incrementation takes place before the entity is returned to the data access layer.

**Key points:**\
     - use two entities, `Author` (which uses `@Version`) and `Book` involved in a lazy bidirectional `@OneToMany` relationship\
     - when we add a new `Book` to an author Hibernate will trigger an `INSERT` statement against the `book` table, therefore the `author` table will not be modified\
     - but, even if the `author` table is not modified the `version` is forcibly increased (this is materialized in an `UPDATE` triggered immediately after aquiring the row-level lock)\
     - set `PESSIMISTIC_FORCE_INCREMENT` in the corresponding repository\
     - rely on two concurrent transactions to shape two scenarios: one that will lead to an exception of type `OptimisticLockException` and one that will lead to `QueryTimeoutException`
     
**Note:** Pay attention to the MySQL dialect: `MySQL5Dialect` (MyISAM) doesn't support row-level locking, `MySQL5InnoDBDialect` (InnoDB) acquires row-level lock via `FOR UPDATE` (timeout can be set), `MySQL8Dialect` (InnoDB) acquires row-level lock via `FOR UPDATE NOWAIT`.
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
