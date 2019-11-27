
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

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
