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
     - in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause batching to be disabled\
     - in entity, add `@Version` property of type `Long` to avoid extra-`SELECT`s fired before batching (also prevent lost updates in multi-request transactions). Extra-`SELECT`s are the effect of using `merge()` instead of `persist()`. Behind the scene, `saveAll()` uses `save()`, which in case of non-new entities (have IDs) will call `merge()`, which instruct Hibernate to fire a `SELECT` statement to make sure that there is no record in the database having the same identifier.\
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
     - starting with Hibernate 5.2, `ResultTransformer` is deprecated, but until a replacement will be available (in Hibernate 6.0) it can be used ([read further](https://discourse.hibernate.org/t/hibernate-resulttransformer-is-deprecated-what-to-use-instead/232))\
     - for using Spring Data Projections check this [recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)

-----------------------------------------------------------------------------------------------------------------------    

33. **[How @ElementCollection Without @OrderColumn Works](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoBlazeEntityView)**

**Description:** This application reveals the performance penalties of using `@ElementCollection`. In this case, without `OrderColumn`.

**Key points:**\
     - an `@ElementCollection` doesn't have a Primary Key\
     - an `@ElementCollection` is mapped in a separate table\
     - avoid `@ElementCollection` when you have a lot of inserts and deletes in/from it since the database has to delete all existing rows in order to add a new one or delete one\
     - the more items we have in this table the greater the performance penalty
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootJpaCallbacks/sample.png)     
