# Best Performance Practices For Hibernate 5 & Spring Boot 2

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>

**Hibernate & Spring Boot Samples**

1. **[How To Store UTC Timezone In MySQL](https://github.com/AnghelLeonard/Hibernate/tree/master/HibernateSpringBootUTCTimezone)**

**Description:** How to store date, time, and timestamps in UTC time zone in MySQL

**Key points:**\
     - `spring.jpa.properties.hibernate.jdbc.time_zone=UTC`\
     - `spring.datasource.url=jdbc:mysql://localhost:3306/db_screenshot?useLegacyDatetimeCode=false`
     
-----------------------------------------------------------------------------------------------------------------------    

2. **[How To View Binding Params Via Log4J 2](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLog4j2ViewBindingParameters)**

**See also recipe 52 for using log4jdbc and 53 for logging using TRACE**

**Description:** View the prepared statement binding parameters via Log4J 2 logger setting

**Key points:**\
     - in pom.xml, exclude Spring Boot's Default Logging\
     - in pom.xml, Add Log4j 2 Dependency\
     - in log4j2.xml add, `<Logger name="org.hibernate.type.descriptor.sql" level="trace"/>`
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLog4j2ViewBindingParameters/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

3. **[How To View Query Details Via "datasource-proxy"](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceProxy)**

**Description:** View the query details (query type, binding parameters, batch size, etc) via **[datasource-proxy](https://github.com/ttddyy/datasource-proxy)**

**Key points:**\
     - add in pom.xml the datasource-proxy dependency\
     - create an bean post processor to intercept the `DataSource` bean\
     - wrap the `DataSource` bean via `ProxyFactory` and an implementation of `MethodInterceptor`
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceProxy/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

4. **[How To Batch Inserts Via `saveAll(Iterable<S> entities)` In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsJpaRepository)**

**Description:** Batch inserts via `SimpleJpaRepository#saveAll(Iterable<S> entities)` method in MySQL

**Key points:**\
     - in application.properties set `spring.jpa.properties.hibernate.jdbc.batch_size`\
     - in application.properties set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)\
     - in application.properties set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)\
     - in application.properties set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)\
     - in application.properties set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))\
     - in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause batching to be disabled\
     - in entity, add `@Version` property of type `Long` to avoid extra-`SELECT`s fired before batching (also prevent lost updates in multi-request transactions). Extra-`SELECT`s are the effect of using `merge()` instead of `persist()`. Behind the scene, `saveAll()` uses `save()`, which in case of non-new entities (have IDs) will call `merge()`, which instruct Hibernate to fire a `SELECT` statement to make sure that there is no record in the database having the same identifier.\
     - in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts\
     - pay attention on the amount of inserts passed to `saveAll()` to not "overwhelm" the persistence context. Normally the `EntityManager` should be flushed and cleared from time to time, but during the `saveAll()` execution you simply cannot do that, so if in `saveAll()` there is a list with a high amount of data, all that data will hit the persistence context (1st level cache) and will be in-memory until flush time. Using relatively small amount of data should be ok. For large amount of data, please check the next example.
  
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsJpaRepository/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

5. **[How To Batch Inserts Via EntityManager In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsEntityManager)**

**Description:** Batch inserts via `EntityManager` in MySQL. This way you can easily control the `flush()` and `clear()` of the persistence context (1st level cache). This is not possible via SpringBoot, `saveAll(Iterable<S> entities)`. Another advantage is that you can call `persist()` instead of `merge()` - this is used behind the scene by the SpringBoot `saveAll(Iterable<S> entities)` and `save(S entity)`.

**Key points:**\
     - in application.properties set `spring.jpa.properties.hibernate.jdbc.batch_size`\
     - in application.properties set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)\
     - in application.properties set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)\
     - in application.properties set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)\     
     - in application.properties set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))\
     - in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts\
     - in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause batching to be disabled\
     - in DAO, flush and clear the persistence context from time to time. This way you avoid to "overwhelm" the persistence context. 
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsEntityManager/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

6. **[How To Batch Inserts Via JpaContext/EntityManager In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsEntityManagerViaJpaContext)**

**Description:** Batch inserts via `JpaContext/EntityManager` in MySQL.

**Key points:**\
     - in application.properties set `spring.jpa.properties.hibernate.jdbc.batch_size`\
     - in application.properties set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)\
     - in application.properties set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)\
     - in application.properties set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)\
     - in application.properties set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))\
     - in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts\
     - in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause batching to be disabled\
     - the `EntityManager` is obtain per entity type via, `JpaContext#getEntityManagerByManagedType(Class<?> entity)`\
     - in DAO, flush and clear the persistence context from time to time. This way you avoid to "overwhelm" the persistence context. 
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsEntityManagerViaJpaContext/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

7. **[How To Exploit Session-Level Batching (Hibernate 5.2 Or Higher) In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsViaSession)**

**Description:** Batch inserts via Hibernate session-level batching (Hibernate 5.2 or higher) in MySQL.

**Key points:**\
     - in application.properties set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)\
     - in application.properties set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)\
     - in application.properties set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)\
     - in application.properties set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))\
     - in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts\
     - in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause batching to be disabled\
     - the Hibernate `Session` is obtained by un-wrapping it via `EntityManager#unwrap(Session.class)`\
     - the batching size is set via `Session#setJdbcBatchSize(Integer size)` and get via `Session#getJdbcBatchSize()`\
     - in DAO, flush and clear the persistence context from time to time. This way you avoid to "overwhelm" the persistence context. 
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsViaSession/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

8. **[How To Use Direct Fetching Via Spring Data/EntityManager/Session](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDirectFetching)**

**Description:** Direct fetching via Spring Data, `EntityManager` and Hibernate `Session` examples.

**Key points:**\
     - direct fetching via Spring Data uses `findById()`\
     - direct fetching via `EntityManager` uses `find()`\
     - direct fetching via Hibernate `Session` uses `get()`

-----------------------------------------------------------------------------------------------------------------------    

9. **[How To Create DTOs Via Spring Data Projections](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)**

**Description:** Fetch only the needed data from the database via Spring Data Projections (DTOs)

**Key points:**\
     - write an interface (projection) containing getters only for the columns that should be fetched from the database\
     - write the proper query returning a `List<projection>`\
     - if is applicable, limit the number of returned rows (e.g., via `LIMIT`) - here, we can use query builder mechanism built into Spring Data repository infrastructure
     
**Output example (select first 2 rows; select only "name" and "city"):**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaProjections/sample.png)  

-----------------------------------------------------------------------------------------------------------------------    

10. **[How To Load Attributes Lazily](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyFetching)**

**Description:** By default, the attributes of an entity are loaded eager (all at once). We can load them **lazy** as well. This is useful for column types that store large amounts of data: `CLOB`, `BLOB`, `VARBINARY`, etc.

**Key points:**\
     - in pom.xml, activate Hibernate bytecode instrumentation (e.g. use Maven bytecode enhancement plugin as follows)\
     - mark the columns that should be loaded lazy with `@Basic(fetch = FetchType.LAZY)`
     
**Run the following requests:**\
     - create a new user: `localhost:8080/new`\
     - fetch the user without avatar (this is a picture, therefore a large amount of data): `localhost:8080/user`\
     - fetch the user with avatar (loaded lazy): `localhost:8080/avatar`

-----------------------------------------------------------------------------------------------------------------------    

11. **[How To Populate A Child-Side Parent Association Via Proxy](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPopulatingChildViaProxy)**

**Description:** A `Proxy` can be useful when a child entity can be persisted with a reference to its parent. In such cases, fetching the parent entity from the database (execute the `SELECT` statement) is a performance penalty and a pointless action. Hibernate can set the underlying foreign key value for an uninitialized `Proxy`.

**Key points:**\
     - rely on `EntityManager#getReference()`\
     - in Spring, use `JpaRepository#getOne()` -> used in this example\
     - in Hibernate, use `load()`\
     - here, we have two entities, `Tournament` and `TennisPlayer`, and a tournament can have multiple players (`@OneToMany`)\
     - we fetch the tournament via a `Proxy` (this will not trigger a `SELECT`), we create a new tennis player, we set the `Proxy` as the tournament for this player and we save the player (this will trigger an `INSERT` in the tennis players table, `tennis_player`)
     
**Output example:**\
     - the console output will reveal that only an `INSERT` is triggered, not the `SELECT`
     
-----------------------------------------------------------------------------------------------------------------------    

12. **[How To Reproduce N+1 Performance Issue](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSimulateNPlus1)**

**Description:** N+1 is an issue of lazy fetching (but, eager is not exempt). This application reproduce the N+1 behavior.

**Key points:**\
     - define two entities, `Category` and `Product` having a `@OneToMany` relationship\
     - fetch all `Product` lazy, so without `Category` (results in 1 query)\
     - loop the fetched `Product` collection and for each entry fetch the corresponding `Category` (results N queries)
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSimulateNPlus1/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

13. **[How To Optimize Distinct SELECTs Via HINT_PASS_DISTINCT_THROUGH Hint](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHintPassDistinctThrough)**

**Description:** Starting with Hibernate 5.2.2, we can optimize `SELECT DISTINCT` via `HINT_PASS_DISTINCT_THROUGH` hint

**Key points:**\
     - use `@QueryHints(value = @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false"))`
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHintPassDistinctThrough/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

14. **[How To Enable Dirty Tracking](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnableDirtyTracking)**

**Description:** Prior to Hibernate version 5, the dirty checking mechanism relies on Java Reflection API. Starting with Hibernate version 5, the dirty checking mechanism relies on bytecode enhancement. This approach sustain a better performance, especially when you have a relatively large number of entitites.

**Key points:**\
     - add the corresponding `plugin` in pom.xml (use Maven bytecode enhancement plugin)
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootEnableDirtyTracking/sample.png)

The bytecode enhancement effect can be seen on `User.class` [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootEnableDirtyTracking/Bytecode%20Enhancement%20User.class/User.java)

-----------------------------------------------------------------------------------------------------------------------    

15. **[How To Use Java 8 Optional In Entities And Queries](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOptional)**

**Description:** This application is a proof of concept of how is correct to use the Java 8 `Optional` in entities and queries. 

**Key points:**\
     - use the Spring Data built-in query-methods that return `Optional` (e.g., `findById()`)\
     - write your own queries that return `Optional`\
     - use `Optional` in entities getters\
     - in order to run different scenarios check the file, data-mysql.sql

-----------------------------------------------------------------------------------------------------------------------    

16. **[How To Use OneToMany Bidirectional Correctly](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOneToManyBidirectional)**

**Description:** This application is a proof of concept of how is correct to implement the bidirectional `@OneToMany` association. 

**Key points:**\
     - always cascade from parent to child\
     - use `mappedBy` on the parent\
     - use `orphanRemoval` on parent in order to remove children without references\
     - use helper methods on parent to keep both sides of the association in sync\
     - use lazy fetch\
     - use a natural/business key or use entity identifier and override `equlas()` and `hashCode()` as [here](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/)         

-----------------------------------------------------------------------------------------------------------------------    

17. **[How To Use Query Fetching](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootQueryFetching)**

**Description:** This application is a proof of concept of how to write a query via `JpaRepository`, `EntityManager` and `Session`.

**Key points:**\
     - for `JpaRepository` use `@Query` or Spring Data Query Creation\
     - for `EntityManager` and `Session` use the `createQuery()` method 
     
-----------------------------------------------------------------------------------------------------------------------    

18. **[How To Avoid MySQL & Hibernate 5 AUTO Generator Type](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAutoGeneratorType)**

**Description:** In MySQL & Hibernate 5, the `GenerationType.AUTO` generator type will result in using the `TABLE` generator. This adds a significant performance penalty. Turning this behavior to `IDENTITY` generator can be obtained by using `GenerationType.IDENTITY` or the native generator.

**Key points:**\
     - use `GenerationType.IDENTITY` instead of `GenerationType.AUTO`\
     - use the native generator - exemplified in this application
   
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootAutoGeneratorType/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

19. **[How To Avoid The Redundant save() Call](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRedundantSave)**

**Description:** This application is an example when calling `save()` for a managed entity is redundant.

**Key points:**\
     - Hibernate triggers `UPDATE` statements for managed entities without the need to explicitly call the `save()` method\
     - behind the scene, this redundancy implies a performance penalty as well

-----------------------------------------------------------------------------------------------------------------------    

20. **[How To Handle PostgreSQL (BIG)SERIAL And Batching Inserts](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchingAndSerial)**

**Description:** In PostgreSQL, using `GenerationType.IDENTITY` will disable insert batching. The `(BIG)SERIAL` is acting "almost" like MySQL, `AUTO_INCREMENT`. In this application, we use the `GenerationType.SEQUENCE` which enable insert batching, and we optimize it via the `hi/lo` optimization algorithm.

**Key points:**\
     - use `GenerationType.SEQUENCE` instead of `GenerationType.IDENTITY`\
     - rely on the `hi/lo` algorithm to fetch multiple identifiers in a single database roundtrip (you can go even further and use the Hibernate `pooled` and `pooled-lo` identifier generators (these are optimizations of `hi/lo`))
   
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchingAndSerial/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

21. **[How To Write JPA Inheritance - Single Table](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSingleTableInheritance)**

**Description:** This application is a sample of JPA Single Table inheritance strategy (`SINGLE_TABLE`)

**Key points:**\
     - this is the default inheritance strategy (`@Inheritance(strategy=InheritanceType.SINGLE_TABLE)`)\
     - all the classes in a hierarchy are mapped to a single table in a the database
   
**Output example (below is a single table obtained from 4 entities):**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSingleTableInheritance/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

22. **[How To Count And Assert SQL Statements](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCountSQLStatements)**

**Description:** This application is a sample of counting and asserting SQL statements triggered "behind the scene". Is very useful to count the SQL statements in order to ensure that your code is not generating more SQLs that you may think (e.g., N+1 can be easily detected by asserting the number of expected statements).

**Key points:**\
     - in pom.xml add dependencies for `datasource-proxy` and Vlad Mihalcea's `db-util`\
     - create the `ProxyDataSourceBuilder` with `countQuery()`\
     - reset the counter via `SQLStatementCountValidator.reset()`\
     - assert `INSERT`, `UPDATE`, `DELETE` and `SELECT` via `assertInsert/Update/Delete/Select/Count(long expectedNumberOfSql)`
   
**Output example (when the number of expected SQLs is not equal with the reality an exception is thrown):**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootCountSQLStatements/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

23. **[How To Use JPA Callbacks](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJpaCallbacks)**

**Description:** This application is a sample of enabling the JPA callbacks (`Pre/PostPersist`, `Pre/PostUpdate`, `Pre/PostRemove` and `PostLoad`).

**Key points:**\
     - in entity, write callback methods and use the proper annotations\
     - callback methods annotated on the bean class must return void and take no arguments
   
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootJpaCallbacks/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

24. **[How To Write @OneToOne Via @MapsId](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOneToOneMapsId)**

**Description:** Instead of a bidirectional `@OneToOne` better rely on an unidirectional `@OneToOne` and `@MapsId`. This application is a proof of concept. 

**Key points:**\
     - use `@MapsId` on child side\
     - basically, for `@OneToOne` associations, this will share the Primary Key with the parent table
     
-----------------------------------------------------------------------------------------------------------------------    

25. **[How To Extract DTOs Via SqlResultSetMapping](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSqlResultSetMapping)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTOs allows us to extract only the needed data. In this application we rely on `SqlResultSetMapping` and `EntityManager`.

**Key points:**\
     - use `SqlResultSetMapping` and `EntityManager`\
     - for using Spring Data Projections check this [recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)
     
-----------------------------------------------------------------------------------------------------------------------    

26. **[How To Extract DTOs Via SqlResultSetMapping & NamedNativeQuery](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSqlResultSetMappingAndNamedNativeQuery)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTOs allows us to extract only the needed data. In this application we rely on `SqlResultSetMapping`, `NamedNativeQuery` and `EntityManager`.

**Key points:**\
     - use `SqlResultSetMapping`, `NamedNativeQuery` and `EntityManager`\
     - for using Spring Data Projections check this [recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)
     
-----------------------------------------------------------------------------------------------------------------------    

27. **[How To Extract DTOs  Via javax.persistence.Tuple And Native SQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoTupleAndSql)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTOs allows us to extract only the needed data. In this application we rely on `javax.persistence.Tuple` and native SQL.

**Key points:**\
     - use `java.persistence.Tuple` and `EntityManager.createNativeQuery()`\
     - for using Spring Data Projections check this [recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)     

-----------------------------------------------------------------------------------------------------------------------    

28. **[How To Extract DTOs Via javax.persistence.Tuple And JPQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoTupleAndJpql)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTOs allows us to extract only the needed data. In this application we rely on `javax.persistence.Tuple` and JPQL.

-----------------------------------------------------------------------------------------------------------------------    

29. **[How To Extract DTOs Via Constructor Expression And JPQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoConstructorExpression)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTOs allows us to extract only the needed data. In this application we rely on Constructor Expression and JPQL.

**Key points:**\
     - use a proper constructor in the DTO class and use a query as `select new com.jpa.CarDto(c.name, c.color) from Car c`\
     - for using Spring Data Projections check this [recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections) 
     
-----------------------------------------------------------------------------------------------------------------------    

30. **[How To Extract DTOs Via ResultTransformer And Native SQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoResultTransformer)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTOs allows us to extract only the needed data. In this application we rely on Hibernate, `ResultTransformer` and native SQL.

**Key points:**\
     - use `AliasToBeanConstructorResultTransformer` for DTOs without setters, with constructor\
     - use `Transformers.aliasToBean()` for DTOs with setters\
     - use `EntityManager.createNativeQuery()` and `unwrap(org.hibernate.query.NativeQuery.class)`\
     - starting with Hibernate 5.2, `ResultTransformer` is deprecated, but until a replacement will be available (in Hibernate 6.0) it can be used ([read further](https://discourse.hibernate.org/t/hibernate-resulttransformer-is-deprecated-what-to-use-instead/232))\
     - for using Spring Data Projections check this [recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections) 

-----------------------------------------------------------------------------------------------------------------------    

31. **[How To Extract DTOs Via ResultTransformer And JPQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoResultTransformerJpql)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTOs allows us to extract only the needed data. In this application we rely on Hibernate, `ResultTransformer` and JPQL.

**Key points:**\
     - use `AliasToBeanConstructorResultTransformer` for DTOs without setters, with constructor\
     - use `Transformers.aliasToBean()` for DTOs with setters\
     - use `EntityManager.createQuery()` and `unwrap(org.hibernate.query.Query.class)`\
     - starting with Hibernate 5.2, `ResultTransformer` is deprecated, but until a replacement will be available (in Hibernate 6.0) it can be used ([read further](https://discourse.hibernate.org/t/hibernate-resulttransformer-is-deprecated-what-to-use-instead/232))\
     - for using Spring Data Projections check this [recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)
     
-----------------------------------------------------------------------------------------------------------------------    

32. **[How To Extract DTOs Via Blaze-Persistence Entity Views](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoBlazeEntityView)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTOs allows us to extract only the needed data. In this application we rely on [Blaze-Persistence](https://persistence.blazebit.com/) entity views.

**Key points:**\
     - add in pom.xml dependencies specific to Blaze-Persistence\
     - configure Blaze-Persistence, `CriteriaBuilderFactory` and `EntityViewManager`\
     - write an entity view via an interface in Blaze-Persistence fashion\
     - write a Spring-centric repository by extending `EntityViewRepository`\
     - call method of this repository such as, `findAll()`, `findOne()`, etc\
     - for using Spring Data Projections check this [recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)

-----------------------------------------------------------------------------------------------------------------------    

33. **[How @ElementCollection Without @OrderColumn Works](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootElementCollectionNoOrderColumn)**

**Description:** This application reveals the possible performance penalties of using `@ElementCollection`. In this case, without `@OrderColumn`. As you can see in the next recipe (34) adding `@OrderColumn` can mitigate some performance penalties.

**Key points:**\
     - an `@ElementCollection` doesn't have a Primary Key\
     - an `@ElementCollection` is mapped in a separate table\
     - avoid `@ElementCollection` when you have a lot of inserts and deletes in/from it since the database has to delete all existing rows in order to add a new one or delete one\
     - the more items we have in this table the greater the performance penalty
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootElementCollectionNoOrderColumn/sample.png)  

-----------------------------------------------------------------------------------------------------------------------    

34. **[How @ElementCollection With @OrderColumn Works](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootElementCollectionWithOrderColumn)**

**Description:** This application reveals the performance penalties of using `@ElementCollection`. In this case, with `@OrderColumn`. But, as you can see in this application, by adding `@OrderColumn` can mitigate some performance penalties when operations take place near the collection tail (e.g., add/remove at/from the end of the collection). Mainly, all elements situated before the adding/removing entry are left untouched, so the performance penalty can be ignored if we affect rows close to the collection tail.

**Key points:**\
     - an `@ElementCollection` doesn't have a Primary Key\
     - an `@ElementCollection` is mapped in a separate table\
     - pefer `@ElementCollection` with `@OrderColumn` when you have a lot of inserts and deletes from the collection tail\
     - the more items are inserted/removed from the beginning of this table the greater the performance penalty
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootElementCollectionWithOrderColumn/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

35. **[How To Avoid Lazy Initialization Caused By Open Session In View Anti-Pattern (1 Session / 1 HTTP Request-Response)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSuppressLazyInitInOpenSessionInView)**

**Description:** The Open-Session in View anti-pattern is activated by default in SpringBoot. If you prefer to use it then it is recommended to mitigate its performance penalties as much as possible. One optimization consist in marking the `Connection` as read-only which would allow the database server to avoid writing to the transaction log. Another optimization consist in explicitly setting the lazy properties of the fetched entities when you don't want them to be lazy initialized.

**Key points:**\
     - fetch a entity and set the lazy properties explicitly\
     - you can do this is the service or controller layer, depending where it fits better to your case, but outside of an explicit transaction\
     - why is this working? why we can set the property of a managed entity and not trigger the flush? well, the answer can be found in the documentation of `OpenSessionInViewFilter` which specifies that:
     
 **NOTE:** This filter will by default not flush the Hibernate `Session`, with the flush mode set to `FlushMode.NEVER`. It assumes to be used in combination with service layer transactions that care for the flushing: The active transaction manager will temporarily change the flush mode to `FlushMode.AUTO` during a read-write transaction, with the flush mode reset to `FlushMode.NEVER` at the end of each transaction. If you intend to use this filter without transactions, consider changing the default flush mode (through the "flushMode" property).
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSuppressLazyInitInOpenSessionInView/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

36. **[How To Use Spring Projections(DTOs) And Inner Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaInnerJoins)**

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and inner joins written via JPQL and native SQL (for MySQL).

**Key points:**\
     - define serveral entities (e.g., `Tournament` and `Player` in a bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., `TournamentPlayerNameDto`, `PlayerRankNameDto`, `TournamentIdNameDto`)\
     - write inner joins queries using JPQL/SQL, for example:\
     - Query the tournaments of all players (`localhost:8080/tournamentsOfPlayersNamesInnerJoinJpql`)\
     - Query all tournaments that have players with rank smaller or equal to "rank" (`localhost:8080/tournamentsIdNameByRankInnerJoinSql`)
     
-----------------------------------------------------------------------------------------------------------------------    

37. **[How To Use Spring Projections(DTOs) And Left Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaLeftJoins)**

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and left joins written via JPQL and native SQL (for MySQL).

**Key points:**\
     - define serveral entities (e.g., `Tournament` and `Player` in a bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., `TournamentPlayerNameDto`)\
     - write left joins queries using JPQL/SQL, for example:\
     - Query all players even if they are not in tournaments (`localhost:8080/allPlayersLeftJoinJpql`)\
     - Query all tournaments even if they don't have players (`localhost:8080/allTournamentsLeftJoinJpql`)
     
-----------------------------------------------------------------------------------------------------------------------    

38. **[How To Use Spring Projections(DTOs) And Right Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaRightJoins)**

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and right joins written via JPQL and native SQL (for MySQL).

**Key points:**\
     - define serveral entities (e.g., `Tournament` and `Player` in a bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., `TournamentPlayerNameDto`)\
     - write right joins queries using JPQL/SQL, for example:\
     - Query all tournaments even if they don't have players (`localhost:8080/allTournamentsRightJoinJpql`)\
     - Query all players even if they are not in tournaments (`localhost:8080/allPlayersRightJoinJpql`)
     
-----------------------------------------------------------------------------------------------------------------------    

39. **[How To Use Spring Projections(DTOs) And Full Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaFullJoins)**

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and right joins written via JPQL and native SQL (for PostgreSQL; MySQL does not support `FULL JOINS`).

**Key points:**\
     - define serveral entities (e.g., `Tournament` and `Player` in a bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-postgresql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., `TournamentPlayerNameDto`)\
     - write right joins queries using JPQL/SQL, for example:\
     - Query all tournaments and players (`localhost:8080/allTournamentsAndPlayersFullJoinJpql`)   
     
-----------------------------------------------------------------------------------------------------------------------    

40. **[How To Use Spring Projections(DTOs) And Left Excluding Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaLeftExcludingJoins)**

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and left excluding joins written via JPQL and native SQL (we use MySQL).

**Key points:**\
     - define serveral entities (e.g., `Tournament` and `Player` in a bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-postgresql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., `TournamentPlayerNameDto`)\
     - write left excluding joins queries using JPQL/SQL, for example:\
     - Query all players that are not in tournaments (`localhost:8080/allPlayersLeftExcludingJoinJpql`)\
     - Query all tournaments that don't have players (`localhost:8080/allTournamentsLeftExcludingJoinJpql`)
     
-----------------------------------------------------------------------------------------------------------------------    

41. **[How To Use Spring Projections(DTOs) And Right Excluding Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaRightExcludingJoins)**

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and right excluding joins written via JPQL and native SQL (we use MySQL).

**Key points:**\
     - define serveral entities (e.g., `Tournament` and `Player` in a bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-postgresql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., `TournamentPlayerNameDto`)\
     - write right excluding joins queries using JPQL/SQL, for example:\
     - Query all players that are not in tournaments (`localhost:8080/allPlayersRightExcludingJoinJpql`)\
     - Query all tournaments that don't have players (`localhost:8080/allTournamentsRightExcludingJoinJpql`)
     
-----------------------------------------------------------------------------------------------------------------------    

42. **[How To Use Spring Projections(DTOs) And Outer Excluding Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaOuterExcludingJoins)**

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and outer excluding joins written via JPQL and native SQL (we use PostgreSQL).

**Key points:**\
     - define serveral entities (e.g., `Tournament` and `Player` in a bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-postgresql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., `TournamentPlayerNameDto`)\
     - write outer excluding joins queries using JPQL/SQL, for example:\
     - Query all tournaments that don't have players and all players that don't participate in tournaments (`localhost:8080/allTournamentsWithoutPlayersAndViceversaOuterExcludingJoinJpql`)
     
-----------------------------------------------------------------------------------------------------------------------    

43. **[How To Use Use Spring Post Commits](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPostCommit)**

**Description:** This application is a proof of concept for using Spring post-commit hooks.

**Key points:**\
     - avoid time-consuming task in post-commits since the database connection will remain open until this code finshes

-----------------------------------------------------------------------------------------------------------------------    

44. **[How To Exploit Spring Projections(DTOs) And Join Unrelated Entities in Hibernate 5.1+](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoUnrelatedEntities)**

**Description:** This application is a proof of concept for using Spring Projections (DTOs) and join unrelated entities. Hibernate 5.1 introduced explicit joins on unrelated entities and the syntax and behaviour are similar to `SQL JOIN` statements.

**Key points:**\
     - define serveral entities (e.g., `Patient` and `Clinic` unrelated entities)\
     - populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., `PatientNameAndMedicalHistoryDto`)\
     - write joins queries using JPQL/SQL, for example:\
     - Query all patients names and medical history with no current treatment (`localhost:8080/allPatientsNameAndMedicalHistoryNoTreatmentInnerJoinJpql`)

-----------------------------------------------------------------------------------------------------------------------    

45. **[Why To Avoid Lombok @EqualsAndHashCode in Entities](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLombokEqualsAndHashCode)**

**Description:** Entities should implement `equals()` and `hashCode()` as [here](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/). The main idea is that Hibernate requires that an entity is equal to itself across all its state transitions (*transient*, *attached*, *detached* and *removed*). Using Lombok `@EqualsAndHashCode` will not respect this requirment.

**Key points:**\
**AVOID THESE APPROACHES**\
     - Using Lombok default behavior of `@EqualsAndHashCode`\
     (entity: `LombokDefaultProduct`, test: `LombokDefaultEqualsAndHashCodeTest`)\
     - Using Lombok  `@EqualsAndHashCode` with primary key only\
     (entity: `LombokIdProduct`, test: `LombokEqualsAndHashCodeWithIdOnlyTest`)\
     - Rely on default `equals()` and `hashCode()`\
     (entity: `DefaultProduct`, test: `DefaultEqualsAndHashCodeTest`)\
     - Implement `equals()` and `hashCode()` based only on primary key\
     (entity: `IdProduct`, test: `IdEqualsAndHashCodeTest`)

**PREFER THESE APPROACHES**\
     - Rely on `@NaturalId` (entity: `NaturalIdProduct`, test: `NaturalIdEqualsAndHashCodeTest`)\
     - Rely on primary key (entity: `GoodProduct`, test: `GoodEqualsAndHashCodeTest`)
     
**Good implementation of equals() and hashCode():**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLombokEqualsAndHashCode/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

46. **[How To Avoid LazyInitializationException Via JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinFetch)**

**Description:** Typically, when we get a `LazyInitializationException` we tend to modify the relationship fetching type from `LAZY` to `EAGER`. That is bad! This is a [code smell](https://vladmihalcea.com/eager-fetching-is-a-code-smell/). Best way to avoid this exception is to rely on `JOIN FETCH` + DTOs (if needed). This application is a `JOIN FETCH` example with no DTOs. But, based on the DTOs examples from this repo, you can easily adapt it to use DTOs as well.

**Key points:**\
     - define two related entities (e.g., `Category` and `Product` in a one-to-many lazy bidirectional relationship)\
     - write a JPQL `JOIN FETCH` to fetch a category including products\
     - write a JPQL `JOIN FETCH` to fetch all products including categories

**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootJoinFetch/sample%201.png) 
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootJoinFetch/sample2.png)

-----------------------------------------------------------------------------------------------------------------------    

47. **[How To Merge Entity Collections](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootMergeCollections)**

**Description:** This is a Spring Boot example based on the following [article](https://vladmihalcea.com/merge-entity-collections-jpa-hibernate/). Is a functional implementation of the Vlad's example. It is highly recommended to read that article.

**Key points:**\
     - Remove the existing database rows that are no longer found in the incoming collection\
     - Update the existing database rows which can be found in the incoming collection\
     - Add the rows found in the incoming collection, which cannot be found in the current database snapshot
     
-----------------------------------------------------------------------------------------------------------------------    

48. **[How To Delay Connection Acquisition As Needed (Hibernate 5.2.10)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDelayConnection)**

**Description:** This is a Spring Boot example that exploits Hibernate 5.2.10 capability of delaying the connection acquisition as needed. Normally, a database connection is aquried immediately after calling a method annotated with `@Transactional`. If this method contains some time-consuming tasks before the first SQL statement then the connection is holded for nothing. But, Hibernate 5.2.10 allows us to delay the connection acquisition as needed. This example rely on HikariCP as the default connection pool for Spring Boot.

**Key points:**\
     - set `spring.datasource.hikari.auto-commit=false` in application.properties\
     - set `spring.jpa.properties.hibernate.connection.provider_disables_autocommit=true` in application.properties
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDelayConnection/sample1.png)
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDelayConnection/sample2.png)

-----------------------------------------------------------------------------------------------------------------------    

49. **[How To Fetch Primary Keys Via Hibernate hi/lo Algorithm](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHiLo)**

**Description:** This is a Spring Boot example of using the hi/lo algorithm for fetching 1000 PKs in 10 database roundtrips for batching 1000 inserts in batches of 10 inserts. The hi/lo algorithm is an optimization algorithm for generating sequences of identifiers.

**Key points:**\
     - use the `SEQUENCE` generator type (e.g., in PostgreSQL)\
     - configure the hi/lo algorithm as in `Player.java` entity
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHiLo/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

50. **[How To Correctly Write a Bidirectional @ManyToMany Association](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootManyToManyBidirectional)**

**Description:** This is a Spring Boot proof of concept for writting a bidirectional `@ManyToMany` association.

**Key points:**\
     - we use two entities, `Tournament` and `Player`, and a tournament can have multiple players and a player can participate to multiple tournaments
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootManyToManyBidirectional/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

51. **[Prefer Set Instead of List in @ManyToMany Relationships](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootManyToManyBidirectionalListVsSet)**

**Description:** This is a Spring Boot example of removing rows in case of a bidirectional `@ManyToMany` using a `List` and a `Set`. The conclusion is that `Set` is much better! This applies to unidirectional as well!

**Key points:**\
     - using `Set` is much more efficent than `List`      
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootManyToManyBidirectionalListVsSet/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

52. **[How To View Binding Params Via log4jdbc](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLog4JdbcViewBindingParameters)**

**Description:** View the prepared statement binding parameters via [log4jdbc](https://stackoverflow.com/questions/45346905/how-to-log-sql-queries-their-parameters-and-results-with-log4jdbc-in-spring-boo/45346996#45346996)

**Key points:**\
     - in pom.xml, Add `log4jdbc` Dependency\
     - in application.properties add: `logging.level.resultsettable=info, logging.level.sqltiming=info, logging.level.sqlonly=fatal, logging.level.audit=fatal, logging.level.resultset=fatal, logging.level.connection=fatal`   

-----------------------------------------------------------------------------------------------------------------------    

53. **[How To View Binding Params Via TRACE](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLogTraceViewBindingParameters)**

**Description:** View the prepared statement binding parameters via `TRACE`

**Key points:**\
     - in application.properties add: `logging.level.org.hibernate.type.descriptor.sql: TRACE`  

-----------------------------------------------------------------------------------------------------------------------    

54. **[How To Store java.time.YearMonth As Integer Or Date Via Hibernate Types](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootYearMonth)**

**Description:** [Hibernate Types](https://github.com/vladmihalcea/hibernate-types) is a set of extra types not supported by Hibernate by default. One of these types is `java.time.YearMonth`. This is a Spring Boot application that uses Hibernate Type to store this `YearMonth` in a MySQL database as integer or `Date`.

**Key points:**\
     - for Maven, add Hibernate Types as a dependency in pom.xml\
     - in entity use `@TypeDef` to map `typeClass` to `defaultForType` 
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootYearMonth/sample.png)     

-----------------------------------------------------------------------------------------------------------------------    

55. **[How To Execute SQL Functions With Multiple Parameters in a JPQL Query](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJpqlFunctionsParams)**

**Description:** Trying to use SQL functions (MySQL, PostgreSQL, etc) in JPQL queries may result in exceptions if Hibernate will not recognize them and cannot parse the JPQL query. For example, the MySQL, `concat_ws` function is not recognized by Hibernate. This application is a Spring Boot application based on Hibernate 5.3, that registers the `concat_ws` function via `MetadataBuilderContributor` and inform Hibernate about it via, `metadata_builder_contributor` property. This example uses `@Query` and `EntityManager` as well, so you can see two use cases.

**Key points:**\
     - use Hibernate 5.3 (or, to be precisely, 5.2.18) (e.g., use Spring Boot 2.1.0.RELEASE)\
     - implement `MetadataBuilderContributor` and register the `concat_ws` MySQL function\
     - in application.properties, set `spring.jpa.properties.hibernate.metadata_builder_contributor` to point out to `MetadataBuilderContributor` implementation
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootJpqlFunctionsParams/sample.png)    

-----------------------------------------------------------------------------------------------------------------------    

56. **[How To JDBC Batch a Big JSON File To MySQL Via a Single Thread And a Single Database Connection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileSingleThread)**

**Other examples:**
- same example based on `ExecutorService` is [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileExecutorService)
- same example based on `ForkJoinPool` is [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileForkJoin)

**Description:** This is a Spring Boot application that reads a relatively big JSON file (200000+ lines) and inserts its content in MySQL via batching using a single thread, the main thread.

**Key points:**
- using MySQL, `json` type
- read the file into a `List` of a certain capacity, for example equal or bigger than your batch; by default the batch is of 300 lines, and the temporary list is 300 * 64
- when the list is full save it in batches into MySQL, clear the list, and fill it again
- for MySQL, in application.properties, you may want to attach to the JDBC URL the following:
     - `rewriteBatchedStatements=true` -> this setting will force sending the batched statements in a single request;
     - `cachePrepStmts=true` -> enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled
     - `useServerPrepStmts=true` -> this way you switch to server-side prepared statements (may lead to signnificant performance boost); moreover, you avoid the `PreparedStatement` to be emulated at the JDBC Driver level; 
     - we use the following JDBC URL settings:\
     `...?cachePrepStmts=true&useServerPrepStmts=true&rewriteBatchedStatements=true&createDatabaseIfNotExist=true`
     - **Note: Older MySQL versions will not tolerate well to have toghether rewritting and server-side prepared statement activated. For being sure that these statements still valid please check the notes of the Connector/J that you are using**
- set the HikariCP to have a single connection
- this application uses `StopWatch` to measure the time needed to transfer the file into the database
- in order to run the application you have to unzip the citylots.zip in the current location; this is the big JSON file collected from Internet;
- if you want to see details about the batch process simply activate the DatasourceProxyBeanPostProcessor.java component, uncomment `@Component`; This is needed because this application relies on DataSource-Proxy (for details, see [recipe 3](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceProxy))

-----------------------------------------------------------------------------------------------------------------------    

57. **[How To JDBC Batch a Big JSON File To MySQL Via ExecutorService And HikariCP](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileExecutorService)**

**Other examples:**
- same example based on a single thread is [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileSingleThread)
- same example based on `ForkJoinPool` is [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileForkJoin)

**Description:** This is a Spring Boot application that reads a relatively big JSON file (200000+ lines) and inserts its content in MySQL via batching using `ExecutorService` and HikariCP.

**Key points:**
- using MySQL, `json` type
- read the file into a `List` of a certain capacity, for example equal or bigger than your batch; by default the batch is of 300 lines, and the temporary list is 300 * 64
- when the list is full save it in batches into MySQL, clear the list, and fill it again
- for MySQL, in application.properties, you may want to attach to the JDBC URL the following:
     - `rewriteBatchedStatements=true` -> this setting will force sending the batched statements in a single request;
     - `cachePrepStmts=true` -> enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled
     - `useServerPrepStmts=true` -> this way you switch to server-side prepared statements (may lead to signnificant performance boost); moreover, you avoid the `PreparedStatement` to be emulated at the JDBC Driver level; 
     - we use the following JDBC URL settings:\
     `...?cachePrepStmts=true&useServerPrepStmts=true&rewriteBatchedStatements=true&createDatabaseIfNotExist=true`
     - **Note: Older MySQL versions will not tolerate well to have toghether rewritting and server-side prepared statement activated. For being sure that these statements still valid please check the notes of the Connector/J that you are using**
- set the HikariCP to provide a number of database connections that ensure that the database achives a minimum context switching (e.g., 2 * number of CPU cores)
- this application uses `StopWatch` to measure the time needed to transfer the file into the database
- in order to run the application you have to unzip the citylots.zip in the current location; this is the big JSON file collected from Internet;
- if you want to see details about the batch process simply activate the DatasourceProxyBeanPostProcessor.java component, uncomment `@Component`; This is needed because this application relies on DataSource-Proxy (for details, see [recipe 3](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceProxy))

-----------------------------------------------------------------------------------------------------------------------    

58. **[How To JDBC Batch a Big JSON File To MySQL Via ForkJoinPool And HikariCP](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileForkJoin)**

**Other examples:**
- same example based on a single thread is [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileSingleThread)
- same example based on `ExecutorService` is [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileExecutorService)

**Description:** This is a Spring Boot application that reads a relatively big JSON file (200000+ lines) and inserts its content in MySQL via batching using `ForkJoinPool` and HikariCP.

**Key points:**
- using MySQL, `json` type
- read the file into a `List` of a certain capacity, for example equal or bigger than your batch; by default the batch is of 300 lines, and the temporary list is 300 * 64
- the list is halved and subtasks are created until the list size is small than the batch size (e.g., by default smaller than 300)
- when the list is full save it in batches into MySQL, clear the list, and fill it again
- for MySQL, in application.properties, you may want to attach to the JDBC URL the following:
     - `rewriteBatchedStatements=true` -> this setting will force sending the batched statements in a single request;
     - `cachePrepStmts=true` -> enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled
     - `useServerPrepStmts=true` -> this way you switch to server-side prepared statements (may lead to signnificant performance boost); moreover, you avoid the `PreparedStatement` to be emulated at the JDBC Driver level; 
     - we use the following JDBC URL settings:\
     `...?cachePrepStmts=true&useServerPrepStmts=true&rewriteBatchedStatements=true&createDatabaseIfNotExist=true`
     - **Note: Older MySQL versions will not tolerate well to have toghether rewritting and server-side prepared statement activated. For being sure that these statements still valid please check the notes of the Connector/J that you are using**
- set the HikariCP to provide a number of database connections that ensure that the database achives a minimum context switching (e.g., 2 * number of CPU cores)
- this application uses `StopWatch` to measure the time needed to transfer the file into the database
- in order to run the application you have to unzip the citylots.zip in the current location; this is the big JSON file collected from Internet;
- if you want to see details about the batch process simply activate the DatasourceProxyBeanPostProcessor.java component, uncomment `@Component`; This is needed because this application relies on DataSource-Proxy (for details, see [recipe 3](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceProxy))

-----------------------------------------------------------------------------------------------------------------------    

59. **[How To Configure HikariCP Via application.properties](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHikariCPPropertiesKickoff)**

**If you use the `spring-boot-starter-jdbc` or `spring-boot-starter-data-jpa` "starters", you automatically get a dependency to HikariCP**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up HikariCP via application.properties. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService`for simulating concurrent users. Check the HickariCP report revealing the connection pool status.

**Key points:**\
     - in application.properties, rely on `spring.datasource.hikari.*` to configure HikariCP     

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHikariCPPropertiesKickoff/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

60. **[How To Configure HikariCP Via DataSourceBuilder](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderHikariCPKickoff)**

**If you use the `spring-boot-starter-jdbc` or `spring-boot-starter-data-jpa` "starters", you automatically get a dependency to HikariCP**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up HikariCP via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService` for simulating concurrent users. Check the HickariCP report revealing the connection pool status.

**Key points:**\
     - in pom.xml, add the `spring-boot-configuration-processor` dependency\
     - in application.properties, configure HikariCP via a custom prefix, e.g., `app.datasource.*`\
     - write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHikariCPPropertiesKickoff/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

61. **[Running a SpringBoot Application Under Payara Server Using a Payara Data Source (JDBC Resource and Connection Pool)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/SpringBootPayaraMySqlKickoffApplication)**

**This application is detailed in this [DZone](https://dzone.com/articles/work-in-progress-1) article.**

-----------------------------------------------------------------------------------------------------------------------    

62. **[How To Configure BoneCP Via DataSourceBuilder](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderBoneCPKickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up BoneCP via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**\
     - in pom.xml, add the `spring-boot-configuration-processor` dependency\
     - in pom.xml add the BoneCP dependency\
     - in application.properties, configure BoneCP via a custom prefix, e.g., `app.datasource.*`\
     - write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceBuilderBoneCPKickoff/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

63. **[How To Configure ViburDBCP Via DataSourceBuilder](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderViburDBCPKickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up ViburDBCP via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**\
     - in pom.xml, add the `spring-boot-configuration-processor` dependency\
     - in pom.xml add the ViburDBCP dependency\
     - in application.properties, configure ViburDBCP via a custom prefix, e.g., `app.datasource.*`\
     - write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceBuilderViburDBCPKickoff/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

64. **[How To Configure C3P0 Via DataSourceBuilder](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderC3P0Kickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up C3P0 via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**\
     - in pom.xml, add the `spring-boot-configuration-processor` dependency\
     - in pom.xml add the C3P0 dependency\
     - in application.properties, configure C3P0 via a custom prefix, e.g., `app.datasource.*`\
     - write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceBuilderC3P0Kickoff/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

65. **[How To Configure DBCP2 Via DataSourceBuilder](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderDBCP2Kickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up DBCP2 via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**\
     - in pom.xml, add the `spring-boot-configuration-processor` dependency\
     - in pom.xml add the DBCP2 dependency\
     - in application.properties, configure DBCP2 via a custom prefix, e.g., `app.datasource.*`\
     - write a `@Bean` that returns the `DataSource`
     
-----------------------------------------------------------------------------------------------------------------------    

66. **[How To Configure Tomcat Connection Pool Via DataSourceBuilder](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderTomcatKickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up Tomcat Connection Pool via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**\
     - in pom.xml, add the `spring-boot-configuration-processor` dependency\
     - in pom.xml add the Tomcat Connection Pool dependency\
     - in application.properties, configure Tomcat Connection Pool via a custom prefix, e.g., `app.datasource.*`\
     - write a `@Bean` that returns the `DataSource`     

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceBuilderTomcatKickoff/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

67. **[How To Configure Two Data Sources With Two Connection Pools](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTwoDataSourceBuilderKickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that uses two data sources (two MySQL databases, one named `players_db` and one named `coaches_db`) with two connection pools (each database uses its own HikariCP connection pool with different settings). Based on the above recipes is pretty easy to configure two connection pools from two different providers as well.

**Key points:**\
     - in pom.xml, add the `spring-boot-configuration-processor` dependency\
     - in application.properties, configure two HikariCP connection pools via a two custom prefixes, e.g., `app.datasource.ds1` and `app.datasource.ds2`\
     - write a `@Bean` that returns the first `DataSource` and mark it as `@Primary`\
     - write another `@Bean` that returns the second `DataSource`\
     - configure two `EntityManagerFactory` and point out the packages to scan for each of them\
     - put the domains and repositories for each `EntityManager` in the right packages
     
**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootTwoDataSourceBuilderKickoff/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

68. **[How To Provide a Fluent API Via Setters For Building Entities](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFluentApiOnSetters)**

**Note**: If you want yo provide a Fluent API without altering setters then consider [this recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFluentApiAdditionalMethods).

**Description:** This is a sample application that alter the entities setters methods in order to empower a Fluent API.

**Key points:**\
     - in entities, return `this` instead of `void` in setters

**Fluent API example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootFluentApiOnSetters/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

69. **[How To Provide a Fluent API Via Additional Methods For Building Entities](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFluentApiAdditionalMethods)**

**Note**: If you want yo provide a Fluent API by altering setters then consider [this recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFluentApiOnSetters).

**Description:** This is a sample application that add in entities additional methods (e.g., for `setName`, we add `name`) methods in order to empower a Fluent API.

**Key points:**\
     - in entities, add for each setter an additional method that return `this` instead of `void`

**Fluent API example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootFluentApiAdditionalMethods/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

70. **[How To Remove The Extra SELECT COUNT Query in Spring Boot Slice Paging with findAll()](https://github.com/AnghelLeonard/Hibernate-SpringBoot)**

**Available implementations:**:
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllSimpleSql) is a thin implementation based on a hard-coded SQL: `"SELECT e FROM " + entityClass.getSimpleName() + " e;"`
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilder) is just another minimalist implementation based on `CriteriaBuilder` instead of hard-coded SQL
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilderAndSort) is an implementation that allows us to provide a `Sort`, so sorting results is possible
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilderSortAndSpecification) is an implementation that allows us to provide a `Sort` and a Spring Data `Specification`
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilderSortAndSpecificationAndQueryHints) is an implementation that allows us to provide a `Sort`, a `LockModeType`, a `QueryHints` and a Spring Data `Specification`
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilderSimpleJpaRepository) is an implementation that allows us to provide a Spring Data `Pageable` and/or `Specification` by extending the `SimpleJpaRepository` from Spring Data. Bascially, this implementation is the only one that returns `Page<T>` instead of `Slice<T>`, but it doesn't trigger the extra `SELECT COUNT` since it was eliminated by overriding the `Page<T> readPage(...)` method from `SimpleJpaRepository`. The main drawback is that by returing a `Page<T>` you don't know if there is a next page or the current one is the last. Nevertheless, there are workarounds to have this as well. In this implementation you cannot set `LockModeType` or query hints.

**Story**: Spring Boot provides an *offset* based built-in paging mechanism that returns a `Page` or `Slice`. Each of these APIs represents a page of data and some metadata. The main difference is that `Page` contains the total number of records, while `Slice` can only tell if there is another page available. For `Page`, Spring Boot provides a `findAll()` method capable to take as arguments a `Pageable` and/or a `Specification`.  In order to populate a `Page` containing the total number of records, this method triggers an `SELECT COUNT` extra-query next to the query used to fetch the current page . This can be a performance penalty since the `SELECT COUNT` query is triggered every time we request a page. In order to avoid this extra-query, Spring Boot provides a more relaxed API, the `Slice` API. Using `Slice` instead of `Page` removes the need of this extra `SELECT COUNT` query and returns the page (records) and some metadata without the total number of records. So, while `Slice` doesn't know the total number of records, it still can tell if there is another page available after the current one or this is the last page. The problem is that `Slice` work fine for queries containing the SQL, `WHERE` clause (including those that uses the query builder mechanism built into Spring Data), but it **doesn't work** for `findAll()`. This method will still return a `Page` instead of `Slice` therefore the `SELECT COUNT` query is triggered for `Slice<T> findAll(...);`.

**Description:** This is a suite of samples applications that provides different versions of a `Slice<T> findAll(...)`  method. We have from a minimalist implementation that relies on a hardcoded query as: `"SELECT e FROM " + entityClass.getSimpleName() + " e";` (this recipe), to a custom implementation that supports sorting, specification, lock mode and query hints to an implementation that relies on extending `SimpleJpaRepository`.

**Key points:**\
     - write an `abstract` class that expose the `Slice<T> findAll(...)` methods (`SlicePagingRepositoryImplementation`)\
     - implement the `findAll()` methods to return `Slice<T>` (or `Page<T>`, but without the total number of elements)\
     - return a `SliceImpl` (`Slice<T>`) or a `PageImpl` (`Page<T>`) without the total number of elements\
     - implement a new `readSlice()` method or override the `SimpleJpaRepository#readPage()` page to avoid `SELECT COUNT`\
     - pass the entity class (e.g., `Player.class`) to this `abstract` class via a class repository (`PlayerRepository`)

-----------------------------------------------------------------------------------------------------------------------    

71. **[How To Remove The Extra SELECT COUNT Query in Spring Boot Paging Via Window Functions](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootWindowFunctionPaging)**

**Description:** When we rely on an *offset* paging we have the performance penalty induced by throwing away *n* records before reached the desired *offset*. Larger *n* leads to a significant performance penalty. But, this is not the only performance penalty. Most of the time we want to count the total number of rows to calculate the total number of possible pages, so this is an extra `SELECT COUNT`. So, if we don't want to go with *keyset* pagination and avoid counting that total number of records, which can be very costly, we have to tacke this performance penalty somehow. For databases vendors that support *Window Functions* there is a solution relying on `COUNT(*) OVER()` as in this application that uses this window function in a native query against MySQL 8.

**Key points:**\
     - create a DTO projection to cover the extra-column added by the `COUNT(*) OVER()` window function\
     - write a native query relying on this window function

**Example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootWindowFunctionPaging/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

72. **[How To Implement Keyset Pagination in Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootKeysetPagination)**

**Note**: For a list of pros and cons of *offset vs keyset* please check my book: [Java Persistence Performance Illustrated Guide](https://leanpub.com/java-persistence-performance-illustrated-guide).

**Description:** When we rely on an *offset* paging we have the performance penalty induced by throwing away *n* records before reached the desired *offset*. Larger *n* leads to a significant performance penalty. When we have a large *n* is better to rely on *keyset* pagination which maintain a "constant" time for large datasets. In order to understand how bad *offset* can perform please check this [article](http://allyouneedisbackend.com/blog/2017/09/24/the-sql-i-love-part-1-scanning-large-table/):

Screenshot from that article (*offset* pagination):
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootKeysetPagination/offset.png)

**Need to know if there are more records?**\
By its nature, *keyset* doesn't use a `SELECT COUNT` to fetch the number of total records. But, we a little tweak we can easily say if there are more records, therefore to show a button of type `Next Page`. Mainly, if you need such a thing then consider [this application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootKeysetPaginationNextPage). 

`public Map<List<Player>, Boolean> fetchNextSlice(long id, int limit) {`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`List<Player> players = playerRepository.fetchAllPlayers(id, limit + 1);`
        
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`if(players.size() == (limit + 1)) {`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`players.remove(players.size() -1);`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`return Collections.singletonMap(players, true);`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`}`
        
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`return Collections.singletonMap(players, false);`\
`}`

A `Previous Page` button can be implemented easily based on the first record.

**Key points:**\
     - choose a column to act as the latest visited record (e.g., `id`)\
     - use this column in the `WHERE` clause of your SQL
          
-----------------------------------------------------------------------------------------------------------------------    

73. **[How To Implement Offset Pagination in Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOffsetPagination)**

**Note:** For a list of pros and cons of *offset vs keyset* please check my book: [Java Persistence Performance Illustrated Guide](https://leanpub.com/java-persistence-performance-illustrated-guide).

**Description:** When we rely on an *offset* paging we have the performance penalty induced by throwing away *n* records before reached the desired *offset*. Larger *n* leads to a significant performance penalty. Another penalty is the extra-`SELECT` needed to count the total number of records. But, for small datasets, *offset* and *keysey* provides almost the same performances. Spring Boot provides built-in support for *offset* pagination via the `Page` API, therefore it is very easy to use it in an application.

**Important note:** This application extract records as entities, but if all you want to do is to read this data as pages then consider DTOs to avoid consuming memory and CPU for nothing. As a rule, extract entites only if you plan to modify them. In this case, we need a native SQL or another approach instead of `Page<T>`.

**But:** If *offset* pagination is causing you performance issues then please check recipes: [70](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilderSortAndSpecificationAndQueryHints) (slice technique for find all records), [71](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootWindowFunctionPaging) (*offset* with window functions) and [72](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootKeysetPagination) (*keyset* pagination).

**Key points:**\
     - write a repository that extends `PagingAndSortingRepository`\
     - call or write methods that returns `Page`

**Examples:**\
     - call the built-in `findAll(Pageable)` without sorting:\
     `repository.findAll(PageRequest.of(page, size));`\
     - call the built-in `findAll(Pageable)` with sorting:\
     `repository.findAll(PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name")));`\
     - use Spring Data query creation to define new methods in your repository:\
     `Page<Player> findByName(String name, Pageable pageable);`\
     `Page<Player> queryFirst10ByName(String name, Pageable pageable);`

-----------------------------------------------------------------------------------------------------------------------    

74. **[How To Optimize Batch Inserts of Parent-Child Relationships and Cascade Persist](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertOrder)**

**Description:** Let's suppose that we have a one-to-many relationship between `Tournament` and `TennisPlayer` entities. When we save a tournament, we save its players as well thanks to cascading. We want to create a bunch of tournaments with players and save them in the database (e.g., a MySQL database) using the batch technique. By default, this will result in batching each tournament and the players per tournament. In order to batch tournaments and players, we need to order inserts as in this application.

**Key points:**\
     - beside all setting specific to batching inserts in MySQL, we need to set up in application.properties the following property: `spring.jpa.properties.hibernate.order_inserts=true`

**Example without ordered inserts:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertOrder/sample2.png)

**Example with ordered inserts:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertOrder/sample1.png)

-----------------------------------------------------------------------------------------------------------------------    

75. **[How To Batch Updates In MySQL](#)**

**Implementations:**
- [Single entity update](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchUpdateOrderSingleEntity)
- [Parent-child relationship update](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchUpdateOrder)

**Description:** Batch updates in MySQL.

**Key points:**\
     - in application.properties set `spring.jpa.properties.hibernate.jdbc.batch_size`\
     - in application.properties set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL, statements get rewritten into a single `String` buffer and sent in a single request)\
     - in application.properties set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)\
     - in application.properties set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))\
     - in case of using a parent-child relationship with cascade all/merge (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_updates=true` to optimize the batching by ordering updates\
     - before Hibernate 5, we need to set in application.properties a setting for enabling batching for versioned entities during update
and delete operations (entities that contains `@Version` for implicit optimistic locking). This setting is: `spring.jpa.properties.hibernate.jdbc.batch_versioned_data=true`. Starting with Hibernate 5, this setting should be `true` by default.
   
**Output example for single entity:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchUpdateOrderSingleEntity/batch_update.png)

**Output example for parent-child relationship:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchUpdateOrder/batch_update.png)

-----------------------------------------------------------------------------------------------------------------------    

76. **[How To Batch Deletes In MySQL (no relationships)](#)**

**Description:** Batch deletes in MySQL having a single entity class (no relationships).

**Note:** Spring `deleteAllInBatch()` and `deleteInBatch()` don't use batching. The first one simply triggers a `delete from entity_name` statement, while the second one triggers a `delete from entity_name where id=? or id=? or id=? ...` statement.

**Key points:**\
     - rely on `SimpleJpaRepository#delete()` method\
     - in application.properties set `spring.jpa.properties.hibernate.jdbc.batch_size`\
     - in application.properties set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL, statements get rewritten into a single `String` buffer and sent in a single request)\
     - in application.properties set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)\
     - in application.properties set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))\     
     - before Hibernate 5, we need to set in application.properties a setting for enabling batching for versioned entities during update
and delete operations (entities that contains `@Version` for implicit optimistic locking). This setting is: `spring.jpa.properties.hibernate.jdbc.batch_versioned_data=true`. Starting with Hibernate 5, this setting should be `true` by default.
   
**Output example for single entity:**

**FIRST APPROACH: deleteAllInBatch()**\
Output sample (no batch is hapenning):\
Name:DATA_SOURCE_PROXY, Connection:25, Time:38, Success:True\
Type:Prepared, Batch:False, QuerySize:1, BatchSize:0\
Query:["delete from tennis_player"]
        
`playerRepository.deleteAllInBatch();`
        
**SECOND APPROACH: deleteInBatch()**\
Output sample (no batch is happening):\
Name:DATA_SOURCE_PROXY, Connection:25, Time:24, Success:True\
Type:Prepared, Batch:False, QuerySize:1, BatchSize:0\
Query:["delete from tennis_player where id=? or id=? or id=? or id=? or id=? ...]\
Params:[(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20)]
        
`playerRepository.deleteInBatch(players);`
        
**THIRD APPROACH: use delete()**\
Output sample: batch is happening\
Name:DATA_SOURCE_PROXY, Connection:25, Time:467, Success:True\
Type:Prepared, Batch:True, QuerySize:1, BatchSize:7\
Query:["delete from tennis_player where id=? and version=?"]\
Params:[(1,0),(2,0),(3,0),(4,0),(5,0),(6,0),(7,0)]\
...

`players.forEach(playerRepository::delete);`
