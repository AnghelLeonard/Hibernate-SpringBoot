**[How to increment the version of the locked entity even if this entity was not modified `PESSIMISTIC_FORCE_INCREMENT`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPesimisticForceIncrement)**

**Description:** This application is a sample of how `PESSIMISTIC_FORCE_INCREMENT` works in MySQL. This is useful when you want to increment the version of the locked entity even if this entity was not modified. Via `PESSIMISTIC_FORCE_INCREMENT` the version is updated (incremented) immediately (the entity version update is guaranteed to succeed immediately after acquiring the row-level lock). The incrementation takes place before the entity is returned to the data access layer.

**Key points:**\
     - use a root entity, `Chapter` (which uses `@Version`)\
     - two entities, `AuthorModification` and `EditorModification` are use to apply modifications to a chapter\
     - between each of these two entities and the root entity there is a lazy unidirectional `@OneToOne` relationship\
     - for each modification, Hibernate will trigger an `INSERT` statement against the corresponding table (`author_modification` or `editor_modification`), therefore the `chapter` table will not be modified\
     - but, `Chapter` entity version is needed to ensure that modifications are applied sequentially (the author and editor are notified if a modificaton was added since the chapter copy was loaded)\
     - the `version` is forcibly increased at each modification (this is materialized in an `UPDATE` triggered immediately after aquiring the row-level lock)\
     - set `PESSIMISTIC_FORCE_INCREMENT` in the corresponding repository\
     - rely on two concurrent transactions to shape two scenarios: one that will lead to an exception of type `OptimisticLockException` and one that will lead to `QueryTimeoutException`          
     
**Note:** Pay attention to the MySQL dialect: `MySQL5Dialect` (MyISAM) doesn't support row-level locking, `MySQL5InnoDBDialect` (InnoDB) acquires row-level lock via `FOR UPDATE` (timeout can be set), `MySQL8Dialect` (InnoDB) acquires row-level lock via `FOR UPDATE NOWAIT`.
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
