
**[Batch Inserts Via EntityManager With Batch Per Transaction (MySQL)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsEntityManagerBatchPerTransaction)**

**Description:** This application is a sample of batching inserts via `EntityManager` in MySQL. This way you can easily control the `flush()` and `clear()` cycles of the Persistence Context (1st Level Cache) inside the current transaction. This is not possible via Spring Boot, `saveAll(Iterable<S> entities)`, since this method executes a single flush per transaction. Another advantage is that you can call `persist()` instead of `merge()` - this is used behind the scene by the SpringBoot `saveAll(Iterable<S> entities)` and `save(S entity)`.

Moreover, this example commits the database transaction after each batch excecution. This way we avoid long-running transactions and, in case of a failure, we rollback only the failed batch and don't lose the previous batches. For each batch, the Persistent Context is flushed and cleared, therefore we maintain a thin Persistent Context. This way the code is not prone to memory errors and performance penalties caused by slow flushes.

**Key points:**
- in `application.properties` set `spring.jpa.properties.hibernate.jdbc.batch_size`
- in `application.properties` set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)
- in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)
- in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)
- in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))
- in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts
- in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause insert batching to be disabled
- in your DAO layer, flush and clear the Persistence Context from time to time (e.g. for each batch); this way you avoid to "overwhelm" the Persistence Context
- in your DAO layer, commit the database transaction after each batch execution
- if is not needed, then ensure that Second Level Cache is disabled via `spring.jpa.properties.hibernate.cache.use_second_level_cache=false`
    
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsEntityManager/batch%20inserts%20via%20EntityManager.png)

--------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
