**[How to increment the version of the locked entity even if this entity was not modified `OPTIMISTIC_FORCE_INCREMENT`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOptimisticForceIncrement)**

**Description:** This application is a sample of how `OPTIMISTIC_FORCE_INCREMENT`. This is useful when you want to increment the version of the locked entity even if this entity was not modified. Via `OPTIMISTIC_FORCE_INCREMENT` the version is updated (incremented) at commit time.

**Key points:**\
     - use two entities, `Author` (which uses `@Version`) and `Book` involved in a lazy bidirectional `@OneToMany` relationship\
     - when we add a new `Book` Hibernate will trigger an `INSERT` statement against the `book` table, therefore the `author` table will not be modified\
     - but, even if the `author` table is not modified the `version` is forcibly increased (this is materialized in an `UPDATE` at commit time\
     - set `OPTIMISTIC_FORCE_INCREMENT` in the corresponding repository\
     - rely on two concurrent transactions to shape the scenario that will lead to an exception of type `ObjectOptimisticLockingFailureException`
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
