**[How To Batch Deletes That Don't Involve Associations In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchDeleteSingleEntity)**

**Description:** Batch deletes that don't involve associations in MySQL.

**Note:** Spring `deleteAllInBatch()` and `deleteInBatch()` don't use *classical* delete batching. They rely on `Query.executeUpdate()` to trigger *bulk* operations. These operations are fast, but Hibernate doesn’t know which entities are removed, therefore, the Persistent Context is not synchronized accordingly (it is advisable to flush (before delete) and close/clear (after delete) the Persistent Context accordingly to avoid issues created by unflushed (if any) or outdated (if any) entities). The first one simply triggers a `delete from entity_name` statement and is very useful for deleting all records. The second one triggers a `delete from entity_name where id=? or id=? or id=? ...` statement, therefore, is prone to cause issues if the generated `DELETE` statement exceedes the maximum accepted size. This drawback can be controlled by deleting the data in chunks that results in `DELETE` statements that don't exceed the maximum accepted size. The maximum accepted size depends on RDBMS (e.g., for MySQL, execute `SHOW VARIABLES LIKE 'max_allowed_packet';`). Nevertheless, this approach is very fast. Is faster than *classical* delete batching which can be achieved via the `deleteAll()`, `deleteAll(Iterable<? extends T> entities)` or `delete()` method. Behind the scene, the two flavors of `deleteAll()` relies on `delete()`. The `delete()`/`deleteAll()` methods rely on `EntityManager.remove()` therefore the persistent context is synchronized accordingly.

**Key points for *classical* delete batching:**\
     - for *classical* delete batching rely on `deleteAll()`, `deleteAll(Iterable<? extends T> entities)` or `delete()` method\
     - in `application.properties` set `spring.jpa.properties.hibernate.jdbc.batch_size`\
     - in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL, statements get rewritten into a single `String` buffer and sent in a single request)\
     - in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)\
     - in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))\
     - before Hibernate 5, we need to set in `application.properties` a setting for enabling batching for versioned entities during update and delete operations (entities that contains `@Version` for implicit optimistic locking); this setting is: `spring.jpa.properties.hibernate.jdbc.batch_versioned_data=true`; starting with Hibernate 5, this setting should be `true` by default
    
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchDeleteSingleEntity/batch%20deletes.png)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
