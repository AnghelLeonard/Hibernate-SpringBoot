**[Batch Inserts via `saveAll(Iterable<S> entities)` in MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsJpaRepository)**

**Description:** Batch inserts via `SimpleJpaRepository#saveAll(Iterable<S> entities)` method in MySQL

**Key points:**
- in `application.properties` set `spring.jpa.properties.hibernate.jdbc.batch_size`
- in `application.properties` set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)
- in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)
- in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)
- in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))
- in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts
- in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause insert batching to be disabled
- in entity, add `@Version` property to avoid extra-`SELECT` statements fired before batching (also prevent lost updates in multi-request transactions). Extra-`SELECT` statements are the effect of using `merge()` instead of `persist()`; behind the scene, `saveAll()` uses `save()`, which in case of non-new entities (entities that have IDs) will call `merge()`, which instruct Hibernate to fire a `SELECT` statement to make sure that there is no record in the database having the same identifier
- pay attention on the amount of inserts passed to `saveAll()` to not "overwhelm" the Persistence Context; normally the `EntityManager` should be flushed and cleared from time to time, but during the `saveAll()` execution you simply cannot do that, so if in `saveAll()` there is a list with a high amount of data, all that data will hit the Persistence Context (1st Level Cache) and will remain in memory until the flush time; using relatively small amount of data should be ok (in this example, each batch of 30 entities run in a separate transaction and Persistent Context)
- the `saveAll()` method return a `List<S>` containing the persisted entities; each persisted entity is added into this list; if you just don't need this `List` then it is created for nothing
- if is not needed, then ensure that Second Level Cache is disabled via `spring.jpa.properties.hibernate.cache.use_second_level_cache=false`
  
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

