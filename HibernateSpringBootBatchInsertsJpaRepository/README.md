**[Hibernate SpringBoot Batch Inserts via `saveAll(Iterable<S> entities)` in MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsJpaRepository)**

**Description:** Batch inserts via `SimpleJpaRepository#saveAll(Iterable<S> entities)` method in MySQL

**Key points:**\
     - in application.properties set `spring.jpa.properties.hibernate.jdbc.batch_size`\
     - in application.properties set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)\
     - in application.properties set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)\
     - in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause batching to be disabled\
     - in entity, add `@Version` property of type `Long` to avoid extra-`SELECT`s fired before batching (also prevent lost updates in multi-request transactions). Extra-`SELECT`s are the effect of using `merge()` instead of `persist()`. Behind the scene, `saveAll()` uses `save()`, which in case of non-new entities (have IDs) will call `merge()`, which instruct Hibernate to fire a `SELECT` statement to make sure that there is no record in the database having the same identifier.\
     - pay attention on the amount of inserts passed to `saveAll()` to not "overwhelm" the persistence context. Normally the `EntityManager` should be flushed and cleared from time to time, but during the `saveAll()` execution you simply cannot do that, so if in `saveAll()` there is a list with a high amount of data, all that data will hit the persistence context (1st level cache) and will be in-memory until flush time. Using relatively small amount of data should be ok.
  
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsJpaRepository/sample.png)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
