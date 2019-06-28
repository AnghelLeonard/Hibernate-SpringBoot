**[Batch Inserts via `saveAll(Iterable<S> entities)` in MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsJpaRepository)**

**Description:** Batch inserts via `SimpleJpaRepository#saveAll(Iterable<S> entities)` method in MySQL

**Key points:**\
     - in `application.properties` set `spring.jpa.properties.hibernate.jdbc.batch_size`\
     - in `application.properties` set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)\
     - in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)\
     - in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)\
     - in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))\
     - in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts\
     - in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause batching to be disabled\
     - in entity, add `@Version` property of type `Long` to avoid extra-`SELECT`s fired before batching (also prevent lost updates in multi-request transactions). Extra-`SELECT`s are the effect of using `merge()` instead of `persist()`; behind the scene, `saveAll()` uses `save()`, which in case of non-new entities (have IDs) will call `merge()`, which instruct Hibernate to fire a `SELECT` statement to make sure that there is no record in the database having the same identifier\
     - pay attention on the amount of inserts passed to `saveAll()` to not "overwhelm" the persistence context; normally the `EntityManager` should be flushed and cleared from time to time, but during the `saveAll()` execution you simply cannot do that, so if in `saveAll()` there is a list with a high amount of data, all that data will hit the persistence context (1st level cache) and will be in-memory until flush time. Using relatively small amount of data should be ok
  
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsJpaRepository/batch%20inserts%20via%20saveAll.png)

----------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
