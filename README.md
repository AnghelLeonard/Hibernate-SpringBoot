[![HitCount](http://hits.dwyl.io/AnghelLeonard/Hibernate-SpringBoot.svg)](http://hits.dwyl.io/AnghelLeonard/Hibernate-SpringBoot)

# Best Performance Practices For Hibernate5 & Spring Boot 2

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>

**Hibernate & Spring Boot Samples**

1. **[How To Store UTC Timezone In MySQL](https://github.com/AnghelLeonard/Hibernate/tree/master/HibernateSpringBootUTCTimezone)**

**Description:** This application is a sample of how to store date, time, and timestamps in UTC time zone in MySQL.

**Key points:**\
     - `spring.jpa.properties.hibernate.jdbc.time_zone=UTC`\
     - `spring.datasource.url=jdbc:mysql://localhost:3306/screenshotdb?useLegacyDatetimeCode=false`
     
-----------------------------------------------------------------------------------------------------------------------    

2. **[View Binding/Extracted Params Via Log4J 2](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLog4j2ViewBindingParameters)**

**Description:** View the prepared statement binding/extracted parameters via Log4J 2 logger setting.

**Key points:**\
     - for Maven, in `pom.xml`, exclude Spring Boot's Default Logging\
     - for Maven, in `pom.xml`, Add Log4j 2 Dependency\
     - in `log4j2.xml` add, `<Logger name="org.hibernate.type.descriptor.sql" level="trace"/>`
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLog4j2ViewBindingParameters/log4j2%20display%20binding%20and%20extracted%20parameters.png)

-----------------------------------------------------------------------------------------------------------------------    

3. **[How To View Query Details Via "datasource-proxy"](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceProxy)**

**Description:** View the query details (query type, binding parameters, batch size, execution time, etc) via **[datasource-proxy](https://github.com/ttddyy/datasource-proxy)**

**Key points:**\
     - for Maven, add in `pom.xml` the `datasource-proxy` dependency\
     - create an bean post processor to intercept the `DataSource` bean\
     - wrap the `DataSource` bean via `ProxyFactory` and an implementation of `MethodInterceptor`
   
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceProxy/query%20details%20via%20datasource-proxy.png)

-----------------------------------------------------------------------------------------------------------------------    

4. **[How To Batch Inserts Via `saveAll(Iterable<S> entities)` In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsJpaRepository)**

**Description:** Batch inserts via `SimpleJpaRepository#saveAll(Iterable<S> entities)` method in MySQL

**Key points:**\
     - in `application.properties` set `spring.jpa.properties.hibernate.jdbc.batch_size`\
     - in `application.properties` set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)\
     - in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)\
     - in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)\
     - in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))\
     - in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts\
     - in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause batching to be disabled\
     - in entity, add `@Version` property to avoid extra-`SELECT`s fired before batching (also prevent lost updates in multi-request transactions). Extra-`SELECT`s are the effect of using `merge()` instead of `persist()`; behind the scene, `saveAll()` uses `save()`, which in case of non-new entities (have IDs) will call `merge()`, which instruct Hibernate to fire a `SELECT` statement to make sure that there is no record in the database having the same identifier\
     - pay attention on the amount of inserts passed to `saveAll()` to not "overwhelm" the persistence context; normally the `EntityManager` should be flushed and cleared from time to time, but during the `saveAll()` execution you simply cannot do that, so if in `saveAll()` there is a list with a high amount of data, all that data will hit the persistence context (1st level cache) and will be in-memory until flush time. Using relatively small amount of data should be ok
  
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsJpaRepository/batch%20inserts%20via%20saveAll.png)

-----------------------------------------------------------------------------------------------------------------------    

5. **[Batch Inserts Via EntityManager in MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsEntityManager)**

**Description:** Batch inserts via `EntityManager` in MySQL. This way you can easily control the `flush()` and `clear()` of the persistence context (1st level cache). This is not possible via SpringBoot, `saveAll(Iterable<S> entities)`. Another advantage is that you can call `persist()` instead of `merge()` - this is used behind the scene by the SpringBoot `saveAll(Iterable<S> entities)` and `save(S entity)`.

**Key points:**\
     - in `application.properties` set `spring.jpa.properties.hibernate.jdbc.batch_size`\
     - in `application.properties` set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)\
     - in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)\
     - in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)\
     - in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))\
     - in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts\
     - in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause batching to be disabled\
     - in DAO, flush and clear the persistence context from time to time; this way you avoid to "overwhelm" the persistence context\
     - if is not needed then ensure that Second Level Cache is disabled via `spring.jpa.properties.hibernate.cache.use_second_level_cache=false`
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsEntityManager/batch%20inserts%20via%20EntityManager.png)

-----------------------------------------------------------------------------------------------------------------------    

6. **[How To Batch Inserts Via JpaContext/EntityManager In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsEntityManagerViaJpaContext)**

**Description:** Batch inserts via `JpaContext/EntityManager` in MySQL.

**Key points:**\
     - in `application.properties` set `spring.jpa.properties.hibernate.jdbc.batch_size`\
     - in `application.properties` set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)\
     - in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)\
     - in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)\
     - in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))\
     - in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts\
     - in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause batching to be disabled\
     - the `EntityManager` is obtain per entity type via, `JpaContext#getEntityManagerByManagedType(Class<?> entity)`\
     - in DAO, flush and clear the persistence context from time to time; this way you avoid to "overwhelm" the persistence context
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsEntityManagerViaJpaContext/batch%20inserts%20via%20JpaContext.png)

-----------------------------------------------------------------------------------------------------------------------    

7. **[How To Exploit Session-Level Batching (Hibernate 5.2 Or Higher) In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsViaSession)**

**Description:** Batch inserts via Hibernate session-level batching (Hibernate 5.2 or higher) in MySQL.

**Key points:**\
     - in `application.properties` set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)\
     - in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)\
     - in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)\
     - in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))\
     - in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts\
     - in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause batching to be disabled\
     - the Hibernate `Session` is obtained by un-wrapping it via `EntityManager#unwrap(Session.class)`\
     - the batching size is set via `Session#setJdbcBatchSize(Integer size)` and get via `Session#getJdbcBatchSize()`\
     - in DAO, flush and clear the persistence context from time to time; this way you avoid to "overwhelm" the persistence context
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsViaSession/batch%20inserts%20via%20Session.png)

-----------------------------------------------------------------------------------------------------------------------    

8. **[Direct Fetching Via Spring Data `findById()`, JPA `EntityManager` And Hibernate `Session`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDirectFetching)**

**Description:** Direct fetching via Spring Data, `EntityManager` and Hibernate `Session` examples.

**Key points:**\
     - direct fetching via Spring Data uses `findById()`\
     - direct fetching via JPA `EntityManager` uses `find()`\
     - direct fetching via Hibernate `Session` uses `get()`

-----------------------------------------------------------------------------------------------------------------------    

9. **[DTOs Via Spring Data Projections](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)**

**Note:** You may also like to read the recipe, ["How To Enrich DTOs With Virtual Properties Via Spring Projections"](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaProjectionsAndVirtualProperties)

**Description:** Fetch only the needed data from the database via Spring Data Projections (DTOs)

**Key points:**\
     - write an interface (projection) containing getters only for the columns that should be fetched from the database\
     - write the proper query returning a `List<projection>`\
     - if is applicable, limit the number of returned rows (e.g., via `LIMIT`) - here, we can use query builder mechanism built into Spring Data repository infrastructure
     
**Note:** Using projections is not limited to use query builder mechanism built into Spring Data repository infrastructure. We can fetch projections via JPQL or native queries as well. For example, in this [application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjectionsAndJpql) we use a JPQL.
     
**Output example (select first 2 rows; select only "name" and "age"):**
<a href="#"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaProjections/DTOs%20via%20Spring%20projections.png" align="center" height="251" width="698" ></a>

-----------------------------------------------------------------------------------------------------------------------    

10. **[Attribute Lazy Loading](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootAttributeLazyLoadingBasic)**
  
**Description:** By default, the attributes of an entity are loaded eagerly (all at once). But, we can load them **lazy** as well. This is useful for column types that store large amounts of data: `CLOB`, `BLOB`, `VARBINARY`, etc or *details* that should be loaded on demand. In this application, we have an entity named `Author`. Its properties are: `id`, `name`, `genre`, `avatar` and `age`. And, we want to load the `avatar` lazy. So, the `avatar` should be loaded on demand.

**Key points:**\
     - in `pom.xml`, activate Hibernate *bytecode instrumentation* (e.g. use Maven *bytecode enhancement plugin*)\
     - in entity, annotate the columns that should be loaded lazy with `@Basic(fetch = FetchType.LAZY)`\
     - in `application.properties`, disable Open Session in View

**Check as well:**\
     - [Default Values For Lazy Loaded Attributes](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingDefaultValues)\
     - [Attribute Lazy Loading And Jackson Serialization](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingJacksonSerialization)
     
-----------------------------------------------------------------------------------------------------------------------    

11. **[How To Populate a Child-Side Parent Association via Proxy](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPopulatingChildViaProxy)**

**Description:** A `Proxy` can be useful when a child entity can be persisted with a reference to its parent (`@ManyToOne` or `@OneToOne` association). In such cases, fetching the parent entity from the database (execute the `SELECT` statement) is a performance penalty and a pointless action. Hibernate can set the underlying foreign key value for an uninitialized `Proxy`.

**Key points:**\
     - rely on `EntityManager#getReference()`\
     - in Spring, use `JpaRepository#getOne()` -> used in this example\
     - in Hibernate, use `load()`\
     - assume two entities, `Author` and `Book`, involved in a unidirectional `@ManyToOne` relationship (`Author` is the parent-side)\
     - we fetch the author via a `Proxy` (this will not trigger a `SELECT`), we create a new book, we set the `Proxy` as the author for this book and we save the book (this will trigger an `INSERT` in the `book` table)
     
**Output example:**\
     - the console output will reveal that only an `INSERT` is triggered, not the `SELECT`
     
-----------------------------------------------------------------------------------------------------------------------    

12. **[How To Quickly Reproduce The N+1 Performance Issue](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSimulateNPlus1)**

**Description:** The N+1 is an issue of lazy fetching (but, eager is not exempt). This application reproduce the N+1 behavior.

**Key points:**\
     - define two entities, `Author` and `Book` in a lazy bidirectional `@OneToMany` relationship\
     - fetch all `Book` lazy, so without `Author` (results in 1 query)\
     - loop the fetched `Book` collection and for each entry fetch the corresponding `Author` (results N queries)\
     - or, fetch all `Author` lazy, so without `Book` (results in 1 query)\
     - loop the fetched `Author` collection and for each entry fetch the corresponding `Book` (results N queries)
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSimulateNPlus1/simulate%20N%2B1.png)

-----------------------------------------------------------------------------------------------------------------------    

13. **[Optimize `SELECT DISTINCT` Via Hibernate `HINT_PASS_DISTINCT_THROUGH` Hint](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHintPassDistinctThrough)**

**Description:** Starting with Hibernate 5.2.2, we can optimize JPQL (HQL) query entites of type `SELECT DISTINCT` via `HINT_PASS_DISTINCT_THROUGH` hint.

**Key points:**\
     - use `@QueryHints(value = @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false"))`
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHintPassDistinctThrough/HINT_PASS_DISTINCT_THROUGH.png)

-----------------------------------------------------------------------------------------------------------------------    

14. **[How To Enable Dirty Tracking In A Spring Boot Application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnableDirtyTracking)**

**Description:** Prior to Hibernate version 5, the *dirty checking* mechanism relies on Java Reflection API. Starting with Hibernate version 5, the *dirty checking* mechanism relies on *bytecode enhancement*. This approach sustain a better performance, especially when you have a relatively large number of entitites.

**Key points:**\
     - add the corresponding `plugin` in `pom.xml` (use Maven bytecode enhancement plugin)
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootEnableDirtyTracking/Enable%20dirty%20tracking.png)

The bytecode enhancement effect can be seen on `Author.class` [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootEnableDirtyTracking/Bytecode%20Enhancement%20Author.class/Author.java)

-----------------------------------------------------------------------------------------------------------------------    

15. **[How To Use Java 8 Optional In Entities And Queries](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOptional)**

**Description:** This application is an example of how is correct to use the Java 8 `Optional` in entities and queries. 

**Key points:**\
     - use the Spring Data built-in query-methods that return `Optional` (e.g., `findById()`)\
     - write your own queries that return `Optional`\
     - use `Optional` in entities getters\
     - in order to run different scenarios check the file, `data-mysql.sql`

-----------------------------------------------------------------------------------------------------------------------    

16. **[How To Use OneToMany Bidirectional Correctly](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOneToManyBidirectional)**

**Description:** This application is a proof of concept of how is correct to implement the bidirectional `@OneToMany` association. 

**Key points:**\
     - always cascade from parent to child\
     - use `mappedBy` on the parent\
     - use `orphanRemoval` on parent in order to remove children without references\
     - use helper methods on parent to keep both sides of the association in sync\
     - use lazy fetching on both side of the association\
     - as entities identifiers, use assigned identifiers (business key, natural key (`@NaturalId`)) and/or database-generated identifiers and override (on child-side) properly the `equals()` and `hashCode()` methods as [here](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/)\
     - if `toString()` need to be overridden, then pay attention to involve only for the basic attributes fetched when the entity is loaded from the database
     
**Note:** Pay attention to remove operations, especially to removing child entities. The `CascadeType.REMOVE` and `orphanRemoval=true` may produce too many queries. Relying on *bulk* operations is most of the time the best way to go for deletions.  
     
-----------------------------------------------------------------------------------------------------------------------    

17. **[Query Fetching](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootQueryFetching)**

**Description:** This application is an example of how to write a query via `JpaRepository`, `EntityManager` and `Session`.

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
<a href="#"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootAutoGeneratorType/Hibernate%20Spring%20Boot%20Auto%20Generator%20Type.png" align="center" height="132" width="742" ></a>

-----------------------------------------------------------------------------------------------------------------------    

19. **[How To Avoid The Redundant save() Call](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRedundantSave)**

**Description:** This application is an example when calling `save()` for a managed entity is redundant.

**Key points:**\
     - Hibernate triggers `UPDATE` statements for managed entities without the need to explicitly call the `save()` method\
     - behind the scene, this redundancy implies a performance penalty as well

-----------------------------------------------------------------------------------------------------------------------    

20. **[Why To Avoid PostgreSQL (`BIG`)`SERIAL` In Batching Inserts Via Hibernate](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchingAndSerial)**

**Description:** In PostgreSQL, using `GenerationType.IDENTITY` will disable insert batching. The `(BIG)SERIAL` is acting "almost" like MySQL, `AUTO_INCREMENT`. In this application, we use the `GenerationType.SEQUENCE` which enable insert batching, and we optimize it via the `hi/lo` optimization algorithm.

**Key points:**\
     - use `GenerationType.SEQUENCE` instead of `GenerationType.IDENTITY`\
     - rely on the `hi/lo` algorithm to fetch multiple identifiers in a single database roundtrip (you can go even further and use the Hibernate `pooled` and `pooled-lo` identifier generators (these are optimizations of `hi/lo`))
   
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchingAndSerial/PostgreSQL%20(BIG)SERIAL%20and%20Batching%20Inserts.png)

-----------------------------------------------------------------------------------------------------------------------    

21. **[JPA Inheritance - Single Table](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSingleTableInheritance)**

**Description:** This application is a sample of JPA Single Table inheritance strategy (`SINGLE_TABLE`)

**Key points:**\
     - this is the default inheritance strategy (`@Inheritance(strategy=InheritanceType.SINGLE_TABLE)`)\
     - all the classes in an inheritance hierarchy are represented via a single table in a the database\
     - subclasses attributes non-nullability is ensured via `@NotNull` and MySQL triggers\
     - the default discriminator column memory footprint was optimized by declaring it of type `TINYINT`
   
**Output example (below is a single table obtained from 3 entities):**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSingleTableInheritance/Single%20table%20inheritance.png)

-----------------------------------------------------------------------------------------------------------------------    

22. **[How To Count And Assert SQL Statements](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCountSQLStatements)**

**Description:** This application is a sample of counting and asserting SQL statements triggered "behind the scene". Is very useful to count the SQL statements in order to ensure that your code is not generating more SQLs that you may think (e.g., N+1 can be easily detected by asserting the number of expected statements).

**Key points:**\
     - for Maven, in `pom.xml`, add dependencies for `datasource-proxy` and Vlad Mihalcea's `db-util`\
     - create the `ProxyDataSourceBuilder` with `countQuery()`\
     - reset the counter via `SQLStatementCountValidator.reset()`\
     - assert `INSERT`, `UPDATE`, `DELETE` and `SELECT` via `assertInsert/Update/Delete/Select/Count(long expectedNumberOfSql)`
   
**Output example (when the number of expected SQLs is not equal with the reality an exception is thrown):**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootCountSQLStatements/count%20and%20assert%20SQL.png)

-----------------------------------------------------------------------------------------------------------------------    

23. **[How To Setup JPA Callbacks](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJpaCallbacks)**

**Description:** This application is a sample of setting the JPA callbacks (`Pre/PostPersist`, `Pre/PostUpdate`, `Pre/PostRemove` and `PostLoad`).

**Key points:**\
     - in entity, write callback methods and use the proper annotations\
     - callback methods annotated on the bean class must return void and take no arguments
   
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootJpaCallbacks/JPA%20callbacks.png)

-----------------------------------------------------------------------------------------------------------------------    

24. **[How To Use `@MapsId` For Sharing Identifier In `@OneToOne` Relationship](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOneToOneMapsId)**

**Description:** Instead of *classical* unidirectional/bidirectional `@OneToOne` better rely on an unidirectional `@OneToOne` and `@MapsId`. This application is a proof of concept. 

**Key points:**\
     - use `@MapsId` on child side\
     - use `@JoinColumn` to customize the name of the Primary Key column\
     - basically, for `@OneToOne` associations, `@MapsId` will share the Primary Key with the parent table (`id` property acts as both Primary Key and Foreign Key)      
     
-----------------------------------------------------------------------------------------------------------------------    

25. **[How To Extract DTOs Via SqlResultSetMapping](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaSqlResultSetMappingEm)**

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

27. **[How To Extract DTOs Via javax.persistence.Tuple And Native SQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoTupleAndSql)**

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
     - use a proper constructor in the DTO class\
     - and use a query as `select new com.jpa.CarDto(c.name, c.color) from Car c`\
     - for using Spring Data Projections check this [recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections) 
     
**See also:**\
[Dto Via Constructor And Spring Data Query Builder Mechanism](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoConstructor)

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
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootElementCollectionNoOrderColumn/%40ElementCollection%20without%20%40OrderColumn.png)  

-----------------------------------------------------------------------------------------------------------------------    

34. **[How @ElementCollection With @OrderColumn Works](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootElementCollectionWithOrderColumn)**

**Description:** This application reveals the performance penalties of using `@ElementCollection`. In this case, with `@OrderColumn`. But, as you can see in this application, by adding `@OrderColumn` can mitigate some performance penalties when operations take place near the collection tail (e.g., add/remove at/from the end of the collection). Mainly, all elements situated before the adding/removing entry are left untouched, so the performance penalty can be ignored if we affect rows close to the collection tail.

**Key points:**\
     - an `@ElementCollection` doesn't have a Primary Key\
     - an `@ElementCollection` is mapped in a separate table\
     - pefer `@ElementCollection` with `@OrderColumn` when you have a lot of inserts and deletes from the collection tail\
     - the more items are inserted/removed from the beginning of this table the greater the performance penalty
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootElementCollectionWithOrderColumn/%40ElementCollection%20with%20%40OrderColumn.png)

-----------------------------------------------------------------------------------------------------------------------    

35. **[How To Avoid Lazy Initialization Issues Caused By Disabling Open Session In View Via Explicit (Default) Values](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSuppressLazyInitInOpenSessionInView)**

**Note: Before reading this item try to see if [Hibernate5Module](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJacksonHibernate5Module) is not what you are looking for.**

**Description:** The Open-Session in View anti-pattern is activated by default in SpringBoot. Now, imagine a lazy relationship (e.g., `@OneToMany`) between two entities, `Author` and `Book` (an author has associated more books). Next, a REST controller endpoint fetches an `Author` without the associated `Book`. But, the View (more precisely, Jackson), forces the lazy loading of the associated `Book` as well. Since OSIV will supply the already opened `Session`, the `Proxy` initializations take place successfully. The solution to avoid this performance penalty starts by disabling the OSIV. Further, explicitly initialize the un-fetched lazy associations. This way, the View will not force lazy loading.

**Key points:**\
     - disable OSIV by adding in `application.properties` this setting: `spring.jpa.open-in-view=false`\
     - fetch an `Author` entity and initialize its associated `Book` explicitly with (default) values (e.g., `null`)\
     - set `@JsonInclude(Include.NON_EMPTY)` on this entity-level to avoid rendering `null` or what is considered empty in the resulted JSON
     
 **NOTE:** If OSIV is enabled, the developer can still initialize the un-fetched lazy associations manually as long as he does this outside of a transaction to avoid flushing. But, why is this working? Since the `Session` is open, why the manually initialization of the associations of a managed entity doesn't trigger the flush? The answer can be found in the documentation of `OpenSessionInViewFilter` which specifies that: *This filter will by default not flush the Hibernate `Session`, with the flush mode set to `FlushMode.NEVER`. It assumes to be used in combination with service layer transactions that care for the flushing: The active transaction manager will temporarily change the flush mode to `FlushMode.AUTO` during a read-write transaction, with the flush mode reset to `FlushMode.NEVER` at the end of each transaction. If you intend to use this filter without transactions, consider changing the default flush mode (through the "flushMode" property).*    

-----------------------------------------------------------------------------------------------------------------------    

36. **[How To Use Spring Projections(DTOs) And Inner Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaInnerJoins)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaInnerJoins/DTO%20via%20inner%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and inner joins written via JPQL and native SQL (for MySQL).

**Key points:**\
     - define two entities (e.g., `Author` and `Book` in a lazy bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)\
     - write inner joins queries using JPQL/SQL
     
-----------------------------------------------------------------------------------------------------------------------    

37. **[How To Use Spring Projections(DTOs) And Left Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaLeftJoins)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaLeftJoins/DTO%20via%20left%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and left joins written via JPQL and native SQL (for MySQL).

**Key points:**\
     - define two entities (e.g., `Author` and `Book` in a lazy bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)\
     - write left joins queries using JPQL/SQL
     
-----------------------------------------------------------------------------------------------------------------------    

38. **[How To Use Spring Projections(DTOs) And Right Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaRightJoins)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaRightJoins/DTO%20via%20right%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and right joins written via JPQL and native SQL (for MySQL).

**Key points:**\
     - define two entities (e.g., `Author` and `Book` in a lazy bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)\
     - write right joins queries using JPQL/SQL
     
-----------------------------------------------------------------------------------------------------------------------    

39. **[How To Use Spring Projections(DTOs) And Inclusive Full Joins (PostgreSQL)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaFullJoins)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaFullJoins/DTO%20via%20inclusive%20full%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and inclusive full joins written via JPQL and native SQL (for PostgreSQL).

**Key points:**\
     - define two entities (e.g., `Author` and `Book` in a lazy bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)\
     - write inclusive full joins queries using JPQL/SQL
     
-----------------------------------------------------------------------------------------------------------------------    

40. **[How To Use Spring Projections(DTOs) And Exclusive Left Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaLeftExcludingJoins)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaLeftExcludingJoins/DTO%20via%20exclusive%20left%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and exclusive left joins written via JPQL and native SQL (for MySQL).

**Key points:**\
     - define two entities (e.g., `Author` and `Book` in a lazy bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)\
     - write exclusive left joins queries using JPQL/SQL
     
-----------------------------------------------------------------------------------------------------------------------    

41. **[How To Use Spring Projections(DTOs) And Exclusive Right Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaRightExcludingJoins)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaRightExcludingJoins/DTO%20via%20exclusive%20right%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and exclusive right joins written via JPQL and native SQL (for MySQL).

**Key points:**\
     - define two entities (e.g., `Author` and `Book` in a lazy bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)\
     - write exclusive right joins queries using JPQL/SQL
     
-----------------------------------------------------------------------------------------------------------------------    

42. **[How To Use Spring Projections(DTOs) And Exclusive Full Joins (PostgreSQL)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaOuterExcludingJoins)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaOuterExcludingJoins/DTO%20via%20exclusive%20full%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and exclusive full joins written via JPQL and native SQL (for PostgreSQL).

**Key points:**\
     - define two entities (e.g., `Author` and `Book` in a lazy bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)\
     - write exclusive full joins queries using JPQL/SQL
     
-----------------------------------------------------------------------------------------------------------------------    

43. **[Why You Should Avoid Time-Consuming Tasks In Spring Boot Post-Commits](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPostCommit)**

**Description:** This application is a proof of concept for using Spring post-commit hooks.

**Key points:**\
     - avoid time-consuming task in post-commits since the database connection will remain open until this code finshes
     
-----------------------------------------------------------------------------------------------------------------------    

44. **[How To Exploit Spring Projections(DTOs) And Join Unrelated Entities in Hibernate 5.1+](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoUnrelatedEntities)**

**Description:** This application is a proof of concept for using Spring Projections (DTOs) and join unrelated entities. Hibernate 5.1 introduced explicit joins on unrelated entities and the syntax and behaviour are similar to `SQL JOIN` statements.

**Key points:**\
     - define serveral entities (e.g., `Author` and `Book` unrelated entities)\
     - populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., `BookstoreDto`)\
     - write joins queries using JPQL/SQL (e.g., queries all authors names and book titles of the given price) 
     
-----------------------------------------------------------------------------------------------------------------------    

45. **[Why To Avoid Lombok `@EqualsAndHashCode` And `@Data` In Entities And How To Override `equals()` And `hashCode()`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLombokEqualsAndHashCode)**

**Description:** Entities should implement `equals()` and `hashCode()` as [here](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/). The main idea is that Hibernate requires that an entity is equal to itself across all its state transitions (*transient*, *attached*, *detached* and *removed*). Using Lombok `@EqualsAndHashCode` (or `@Data`) will not respect this requirment.

**Key points:**\
**AVOID THESE APPROACHES**\
     - Using Lombok default behavior of `@EqualsAndHashCode`\
     (entity: `LombokDefaultBook`, test: `LombokDefaultEqualsAndHashCodeTest`)\
     - Using Lombok  `@EqualsAndHashCode` with primary key only\
     (entity: `LombokIdBook`, test: `LombokEqualsAndHashCodeWithIdOnlyTest`)\
     - Rely on default `equals()` and `hashCode()`\
     (entity: `DefaultBook`, test: `DefaultEqualsAndHashCodeTest`)\
     - Rely on default `equals()` and `hashCode()` containing only the database-generated identifier\
     (entity: `IdBook`, test: `IdEqualsAndHashCodeTest`)

**PREFER THESE APPROACHES**\
     - Rely on business key (entity: `BusinessKeyBook`, test: `BusinessKeyEqualsAndHashCodeTest`)\
     - Rely on `@NaturalId` (entity: `NaturalIdBook`, test: `NaturalIdEqualsAndHashCodeTest`)\
     - Rely on manually assigned identifiers (entity: `IdManBook`, test: `IdManEqualsAndHashCodeTest`)\
     - Rely on database-generated identifiers (entity: `IdGenBook`, test: `IdGenEqualsAndHashCodeTest`)
     
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLombokEqualsAndHashCode/auto-generated%20primary%20key%20and%20equals%20-%20hashCode.png)   

-----------------------------------------------------------------------------------------------------------------------    

46. **[How To Avoid LazyInitializationException Via JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinFetch)**

**Description:** Typically, when we get a `LazyInitializationException` we tend to modify the relationship fetching type from `LAZY` to `EAGER`. That is bad! This is a [code smell](https://vladmihalcea.com/eager-fetching-is-a-code-smell/). Best way to avoid this exception is to rely on `JOIN FETCH` (if you plan to modify the fetched entities) or `JOIN` + DTOs (if the fetched data is only read). `JOIN FETCH` allows associations or collections of values to be initialized along with their parent objects using a single `SELECT`. This application is a `JOIN FETCH` example with entities. But, with some constraints, `JOIN FETCH` can be used with DTOs as well. An example is available [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaJoinFetch).

**Key points:**\
     - define two related entities (e.g., `Author` and `Book` in a one-to-many lazy bidirectional relationship)\
     - write a JPQL `JOIN FETCH` to fetch an author including his books\
     - write a JPQL `JOIN FETCH` to fetch a book including its author

**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootJoinFetch/hibernate%20spring%20boot%20join%20fetch.png) 

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
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDelayConnection/delay%20connection%20acquisition%201.png)
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDelayConnection/delay%20connection%20acquisition%202.png)

-----------------------------------------------------------------------------------------------------------------------    

49. **[How To Generate Sequences Of Identifiers Via Hibernate `hi/lo` Algorithm](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHiLo)**

 **Note:** If systems external to your application need to insert rows in your tables then don't rely on `hi/lo` since, in such cases, it may cause errors resulted from generating duplicated identifiers. Rely on `pooled` or `pooled-lo`.
 
**Description:** This is a Spring Boot example of using the `hi/lo` algorithm for generating 1000 identifiers in 10 database roundtrips for batching 1000 inserts in batches of 30 inserts. The `hi/lo` is a Hibernate algorithm is an optimization algorithm for generating sequences of identifiers.

**Key points:**\
     - use the `SEQUENCE` generator type (e.g., in PostgreSQL)\
     - configure the `hi/lo` algorithm as in `Author.java` entity
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHiLo/Hibernate%20hilo%20algorithm.png)

-----------------------------------------------------------------------------------------------------------------------    

50. **[How To Correctly Write a Bidirectional @ManyToMany Association](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootManyToManyBidirectional)**

**Description:** This application is a proof of concept of how is correct to implement the bidirectional `@ManyToMany` association. 

**Key points:**\
     - choose and set the owner of the relationship via `mappedBy`\
     - materialize the relationships collections via `Set` not `List`\
     - use helper methods on the owner of the relationship to keep both sides of the association in sync\
     - on the owner of the relationship use `CascadeType.PERSIST` and `CascadeType.MERGE`, but avoid `CascadeType.REMOVE/ALL`\
     - on the owner of the relationship set up join table\
     - `@ManyToMany` is lazy by default; keep it this way!\
     - as entities identifiers, use assigned identifiers (business key, natural key (`@NaturalId`)) and/or database-generated identifiers and override properly (on both sides) the `equals()` and `hashCode()` methods as [here](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/)\
     - if `toString()` need to be overridden, then pay attention to involve only for the basic attributes fetched when the entity is loaded from the database
     
-----------------------------------------------------------------------------------------------------------------------    

51. **[Prefer Set Instead of List in @ManyToMany Relationships](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootManyToManyBidirectionalListVsSet)**

**Description:** This is a Spring Boot example of removing rows in case of a bidirectional `@ManyToMany` using a `List` and a `Set`. The conclusion is that `Set` is much better! This applies to unidirectional as well!

**Key points:**\
     - using `Set` is much more efficent than `List`      
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootManyToManyBidirectionalListVsSet/manytomany%20use%20always%20set%20not%20list.png)

-----------------------------------------------------------------------------------------------------------------------    

52. **[How To View Query Details Via log4jdbc](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLog4JdbcViewBindingParameters)**

**Description:** View the query details via [log4jdbc](https://stackoverflow.com/questions/45346905/how-to-log-sql-queries-their-parameters-and-results-with-log4jdbc-in-spring-boo/45346996#45346996)

**Key points:**\
     - for Maven, in `pom.xml`, add `log4jdbc` dependency
     
**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLog4JdbcViewBindingParameters/query%20details%20via%20log4jdbc.png)

-----------------------------------------------------------------------------------------------------------------------    

53. **[How To View Binding Params Via TRACE](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLogTraceViewBindingParameters)**

**Description:** View the prepared statement binding/extracted parameters via `TRACE`

**Key points:**\
     - in application.properties add: `logging.level.org.hibernate.type.descriptor.sql=TRACE`
     
**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLogTraceViewBindingParameters/display%20binding%20and%20extracted%20parameters%20via%20TRACE.png) 

-----------------------------------------------------------------------------------------------------------------------    

54. **[How To Store `java.time.YearMonth` As `Integer` Or `Date` Via Hibernate Types Library](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootYearMonth)**

**Description:** [Hibernate Types](https://github.com/vladmihalcea/hibernate-types) is a set of extra types not supported by Hibernate by default. One of these types is `java.time.YearMonth`. This is a Spring Boot application that uses Hibernate Type to store this `YearMonth` in a MySQL database as integer or `Date`.

**Key points:**\
     - for Maven, add Hibernate Types as a dependency in pom.xml\
     - in entity use `@TypeDef` to map `typeClass` to `defaultForType` 
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootYearMonth/Hibernate%20Types%20library.png)     

-----------------------------------------------------------------------------------------------------------------------    

55. **[How To Execute SQL Functions In JPQL Query](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJpqlFunctionsParams)**

**Note**: Using SQL functions in `WHERE` part (not in `SELECT` part) in JPA 2.1 can be done via `function()` as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJpqlFunction).

**Description:** Trying to use SQL functions (standard or defined) in JPQL queries may result in exceptions if Hibernate will not recognize them and cannot parse the JPQL query. For example, the MySQL, `concat_ws` function is not recognized by Hibernate. This application is a Spring Boot application based on Hibernate 5.3, that registers the `concat_ws` function via `MetadataBuilderContributor` and inform Hibernate about it via, `metadata_builder_contributor` property. This example uses `@Query` and `EntityManager` as well, so you can see two use cases.

**Key points:**\
     - use Hibernate 5.3 (or, to be precisely, 5.2.18) (e.g., use Spring Boot 2.1.0.RELEASE)\
     - implement `MetadataBuilderContributor` and register the `concat_ws` MySQL function\
     - in application.properties, set `spring.jpa.properties.hibernate.metadata_builder_contributor` to point out to `MetadataBuilderContributor` implementation
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootJpqlFunctionsParams/SQL%20functions%20in%20JPQL%20parameters.png)       

-----------------------------------------------------------------------------------------------------------------------

56. **[Log Slow Queries Via "datasource-proxy"](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLogSlowQueries)**

**Description:** This application is a sample of logging only slow queries via **[datasource-proxy](https://github.com/ttddyy/datasource-proxy)**. A slow query is a query that has an exection time bigger than a specificed threshold in milliseconds.

**Key points:**\
     - for Maven, add in `pom.xml` the `datasource-proxy` dependency\
     - create an bean post processor to intercept the `DataSource` bean\
     - wrap the `DataSource` bean via `ProxyFactory` and an implementation of `MethodInterceptor`\
     - choose a threshold in milliseconds\
     - define a listener and override `afterQuery()`
   
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLogSlowQueries/log%20slow%20queries%20via%20datasource-proxy.png)

-----------------------------------------------------------------------------------------------------------------------    

57. **[Offset Pagination - Trigger `SELECT COUNT` Subquery And Return `Page<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageDtoOffsetPagination)**

**Description:** This application fetches data as `Page<dto>` via Spring Boot offset pagination. Most of the time, the data that should be paginated is *read-only* data. Fetching the data into entities should be done only if we plan to modify that data, therefore, fetching *read only* data as `Page<entity>` is not preferable since it may end up in a significant performance penalty. The `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. Therefore, there will be a single database roundtrip instead of two (typically, there is one query needed for fetching the data and one for counting the total number of records).

**Key points:**\
     - create a Spring projection (DTO) to contains getters only for the data that should be fetched\
     - write a repository that extends `PagingAndSortingRepository`\
     - fetch data via a JPQL or native query (that includes counting) into a `List<dto>`, and a `Pageable`\
     - use the fetched `List<dto>` and `Pageable` to create a `Page<dto>`
     
-----------------------------------------------------------------------------------------------------------------------    

58. **[Offset Pagination - Trigger `SELECT COUNT` Subquery And Return `List<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListDtoOffsetPagination)**

**Description:** This application fetches data as `List<dto>` via Spring Boot offset pagination. Most of the time, the data that should be paginated is *read-only* data. Fetching the data into entities should be done only if we plan to modify that data, therefore, fetching *read only* data as `List<entity>` is not preferable since it may end up in a significant performance penalty. The `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. Therefore, there will be a single database roundtrip instead of two (typically, there is one query needed for fetching the data and one for counting the total number of records).
 
**Key points:**\
     - create a Spring projection (DTO) to contains getters only for the data that should be fetched\
     - write a repository that extends `PagingAndSortingRepository`\
     - fetch data via a JPQL or native query (that includes counting) into a `List<dto>`
     
-----------------------------------------------------------------------------------------------------------------------    
     
59. **[How To Customize HikariCP Settings Via Properties](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHikariCPPropertiesKickoff)**

**If you use the `spring-boot-starter-jdbc` or `spring-boot-starter-data-jpa` "starters", you automatically get a dependency to HikariCP**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up HikariCP via `application.properties` only. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService`for simulating concurrent users. Check the HickariCP report revealing the connection pool status.

**Key points:**\
     - in `application.properties`, rely on `spring.datasource.hikari.*` to configure HikariCP     

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHikariCPPropertiesKickoff/HikariCP%20trace%20log.png)

-----------------------------------------------------------------------------------------------------------------------    

60. **[How To Customize HikariCP Settings Via Properties And `DataSourceBuilder`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderHikariCPKickoff)**

**If you use the `spring-boot-starter-jdbc` or `spring-boot-starter-data-jpa` "starters", you automatically get a dependency to HikariCP**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up HikariCP via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService` for simulating concurrent users. Check the HickariCP report revealing the connection pool status.

**Key points:**\
     - in `application.properties`, configure HikariCP via a custom prefix, e.g., `app.datasource.*`\
     - write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHikariCPPropertiesKickoff/HikariCP%20trace%20log.png)

-----------------------------------------------------------------------------------------------------------------------    

61. **[Running a SpringBoot Application Under Payara Server Using a Payara Data Source (JDBC Resource and Connection Pool)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/SpringBootPayaraMySqlKickoffApplication)**

**This application is detailed in this [DZone](https://dzone.com/articles/work-in-progress-1) article.**

-----------------------------------------------------------------------------------------------------------------------    

62. **[How To Customize BoneCP Settings Via Properties And `DataSourceBuilder`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderBoneCPKickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up BoneCP via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**\
     - in `pom.xml` add the BoneCP dependency\
     - in `application.properties`, configure BoneCP via a custom prefix, e.g., `app.datasource.*`\
     - write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceBuilderBoneCPKickoff/BoneCP%20trace%20log.png)

-----------------------------------------------------------------------------------------------------------------------    

63. **[How To Customize ViburDBCP Settings Via Properties And `DataSourceBuilder`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderViburDBCPKickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.
 
**Description:** This is a kickoff application that set up ViburDBCP via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**\
     - in `pom.xml` add the ViburDBCP dependency\
     - in `application.properties`, configure ViburDBCP via a custom prefix, e.g., `app.datasource.*`\
     - write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceBuilderViburDBCPKickoff/ViburDBCP%20log%20trace.png)

-----------------------------------------------------------------------------------------------------------------------    

64. **[How To Customize C3P0 Settings Via Properties And `DataSourceBuilder`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderC3P0Kickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.
 
**Description:** This is a kickoff application that set up C3P0 via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**\
     - in `pom.xml` add the C3P0 dependency\
     - in `application.properties`, configure C3P0 via a custom prefix, e.g., `app.datasource.*`\
     - write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceBuilderC3P0Kickoff/C3P0%20trace%20log.png)

-----------------------------------------------------------------------------------------------------------------------    

65. **[How To Customize DBCP2 Settings Via Properties And `DataSourceBuilder`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderDBCP2Kickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.
 
**Description:** This is a kickoff application that set up DBCP2 via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**\
     - in `pom.xml` add the DBCP2 dependency\
     - in `application.properties`, configure DBCP2 via a custom prefix, e.g., `app.datasource.*`\
     - write a `@Bean` that returns the `DataSource`
     
-----------------------------------------------------------------------------------------------------------------------    

66. **[How To Customize Tomcat Settings Via Properties And `DataSourceBuilder`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderTomcatKickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.
 
**Description:** This is a kickoff application that set up Tomcat via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**\
     - in `pom.xml` add the Tomcat dependency\
     - in `application.properties`, configure Tomcat via a custom prefix, e.g., `app.datasource.*`\
     - write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceBuilderTomcatKickoff/Tomcat%20trace%20log.png)

-----------------------------------------------------------------------------------------------------------------------    

67. **[How To Configure Two Data Sources With Two Connection Pools](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTwoDataSourceBuilderKickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that uses two data sources (two MySQL databases, one named `authorsdb` and one named `booksdb`) with two connection pools (each database uses its own HikariCP connection pool with different settings). Based on the above recipes is pretty easy to configure two connection pools from two different providers as well.

**Key points:**\
     - in `application.properties`, configure two HikariCP connection pools via a two custom prefixes, e.g., `app.datasource.ds1` and `app.datasource.ds2`\
     - write a `@Bean` that returns the first `DataSource` and mark it as `@Primary`\
     - write another `@Bean` that returns the second `DataSource`\
     - configure two `EntityManagerFactory` and point out the packages to scan for each of them\
     - put the domains and repositories for each `EntityManager` in the right packages
     
**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootTwoDataSourceBuilderKickoff/Two%20DataSources.png)

-----------------------------------------------------------------------------------------------------------------------    

68. **[How To Provide a Fluent API Via Setters For Building Entities](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFluentApiOnSetters)**

**Note**: If you want yo provide a Fluent API without altering setters then consider [this recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFluentApiAdditionalMethods).

**Description:** This is a sample application that alter the entities setters methods in order to empower a Fluent API.

**Key points:**\
     - in entities, return `this` instead of `void` in setters

**Fluent API example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootFluentApiOnSetters/fluent%20entity%20setters.png)

-----------------------------------------------------------------------------------------------------------------------    

69. **[How To Provide a Fluent API Via Additional Methods For Building Entities](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFluentApiAdditionalMethods)**

**Note**: If you want yo provide a Fluent API by altering setters then consider [this recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFluentApiOnSetters).

**Description:** This is a sample application that add in entities additional methods (e.g., for `setName`, we add `name`) methods in order to empower a Fluent API.

**Key points:**\
     - in entities, add for each setter an additional method that return `this` instead of `void`

**Fluent API example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootFluentApiAdditionalMethods/fluent%20api%20with%20additional%20methods.png)

-----------------------------------------------------------------------------------------------------------------------    

70. **[How To Implement `Slice<T> findAll()`](https://github.com/AnghelLeonard/Hibernate-SpringBoot)**

**Most probably this is all you want:** [How To Fetch `Slice<entity>`/`Slice<dto>` Via `fetchAll`/`fetchAllDto`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllViaFetchAll)

**Some implementations of `Slice<T> findAll()`:**
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllSimpleSql) is a thin implementation based on a hard-coded SQL: `"SELECT e FROM " + entityClass.getSimpleName() + " e;"`
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilder) is just another minimalist implementation based on `CriteriaBuilder` instead of hard-coded SQL
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilderAndSort) is an implementation that allows us to provide a `Sort`, so sorting results is possible
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilderSortAndSpecification) is an implementation that allows us to provide a `Sort` and a Spring Data `Specification`
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilderSortAndSpecificationAndQueryHints) is an implementation that allows us to provide a `Sort`, a `LockModeType`, a `QueryHints` and a Spring Data `Specification`
- [This](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllCriteriaBuilderSimpleJpaRepository) is an implementation that allows us to provide a Spring Data `Pageable` and/or `Specification` by extending the `SimpleJpaRepository` from Spring Data. Bascially, this implementation is the only one that returns `Page<T>` instead of `Slice<T>`, but it doesn't trigger the extra `SELECT COUNT` since it was eliminated by overriding the `Page<T> readPage(...)` method from `SimpleJpaRepository`. The main drawback is that by returing a `Page<T>` you don't know if there is a next page or the current one is the last. Nevertheless, there are workarounds to have this as well. In this implementation you cannot set `LockModeType` or query hints.

**Story**: Spring Boot provides an *offset* based built-in paging mechanism that returns a `Page` or `Slice`. Each of these APIs represents a page of data and some metadata. The main difference is that `Page` contains the total number of records, while `Slice` can only tell if there is another page available. For `Page`, Spring Boot provides a `findAll()` method capable to take as arguments a `Pageable` and/or a `Specification` or `Example`.  In order to create a `Page` that contains the total number of records, this method triggers an `SELECT COUNT` extra-query next to the query used to fetch the data of the current page. This can be a performance penalty since the `SELECT COUNT` query is triggered every time we request a page. In order to avoid this extra-query, Spring Boot provides a more relaxed API, the `Slice` API. Using `Slice` instead of `Page` removes the need of this extra `SELECT COUNT` query and returns the page (records) and some metadata without the total number of records. So, while `Slice` doesn't know the total number of records, it still can tell if there is another page available after the current one or this is the last page. The problem is that `Slice` work fine for queries containing the SQL, `WHERE` clause (including those that uses the query builder mechanism built into Spring Data), but it **doesn't work** for `findAll()`. This method will still return a `Page` instead of `Slice` therefore the `SELECT COUNT` query is triggered for `Slice<T> findAll(...);`.

**Description:** This is a suite of samples applications that provides different versions of a `Slice<T> findAll(...)`  method. We have from a minimalist implementation that relies on a hardcoded query as: `"SELECT e FROM " + entityClass.getSimpleName() + " e";` (this recipe), to a custom implementation that supports sorting, specification, lock mode and query hints to an implementation that relies on extending `SimpleJpaRepository`.

**Key points:**\
     - write an `abstract` class that expose the `Slice<T> findAll(...)` methods (`SlicePagingRepositoryImplementation`)\
     - implement the `findAll()` methods to return `Slice<T>` (or `Page<T>`, but without the total number of elements)\
     - return a `SliceImpl` (`Slice<T>`) or a `PageImpl` (`Page<T>`) without the total number of elements\
     - implement a new `readSlice()` method or override the `SimpleJpaRepository#readPage()` page to avoid `SELECT COUNT`\
     - pass the entity class (e.g., `Author.class`) to this `abstract` class via a class repository (`AuthorRepository`)

-----------------------------------------------------------------------------------------------------------------------    

71. **[Offset Pagination - Trigger `COUNT(*) OVER` And Return `List<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListDtoOffsetPaginationWF)**
 
**Description:** Typically, in offset pagination, there is one query needed for fetching the data and one for counting the total number of records. But, we can fetch this information in a single database rountrip via a `SELECT COUNT` subquery nested in the main `SELECT`. Even better, for databases vendors that support *Window Functions* there is a solution relying on `COUNT(*) OVER()` as in this application that uses this window function in a native query against MySQL 8. So, prefer this one instead of `SELECT COUNT` subquery.

**Key points:**\
     - create a DTO projection that contains getters for the data that should be fetched and an extra-column for mapping the return of the `COUNT(*) OVER()` window function\
     - write a native query relying on this window function

**Example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootListDtoOffsetPaginationWF/offset%20pagination%20via%20window%20function.png)

-----------------------------------------------------------------------------------------------------------------------    

72. **[How To Implement Keyset Pagination in Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootKeysetPagination)**

**Description:** When we rely on an *offset* paging we have the performance penalty induced by throwing away *n* records before reached the desired *offset*. Larger *n* leads to a significant performance penalty. When we have a large *n* is better to rely on *keyset* pagination which maintain a "constant" time for large datasets. In order to understand how bad *offset* can perform please check this [article](http://allyouneedisbackend.com/blog/2017/09/24/the-sql-i-love-part-1-scanning-large-table/):

Screenshot from that article (*offset* pagination):
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootKeysetPagination/offset%20pagination.png)

**Need to know if there are more records?**\
By its nature, *keyset* doesn't use a `SELECT COUNT` to fetch the number of total records. But, we a little tweak we can easily say if there are more records, therefore to show a button of type `Next Page`. Mainly, if you need such a thing then consider [this application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootKeysetPaginationNextPage). 

`public AuthorView fetchNextPage(long id, int limit) {`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`List<Author> authors = authorRepository.fetchAll(id, limit + 1);`

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`if (authors.size() == (limit + 1)) {`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`authors.remove(authors.size() - 1);`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`return new AuthorView(authors, true);`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`}`

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`return new AuthorView(authors, false);`\
`}`

Or, like this (rely on `Author.toString()` method):

`public Map<List<Author>, Boolean> fetchNextPage(long id, int limit) {`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`List<Author> authors = authorRepository.fetchAll(id, limit + 1);`
        
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`if(authors.size() == (limit + 1)) {`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`authors.remove(authors.size() -1);`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`return Collections.singletonMap(authors, true);`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`}`
        
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`return Collections.singletonMap(authors, false);`\
`}`

A `Previous Page` button can be implemented easily based on the first record.

**Key points:**\
     - choose the column(s) to act as the latest visited record (e.g., `id`)\
     - use the column(s) in the `WHERE` and `ORDER BY` clauses of your SQL
          
-----------------------------------------------------------------------------------------------------------------------    

73. **[How To Implement Offset Pagination in Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOffsetPagination)**

**Description:** This is a classical Spring Boot *offset* pagination example. However, is not advisable to use this approach in production because of its performance penalties explained further. 

When we rely on an *offset* pagination, we have the performance penalty induced by throwing away *n* records before reaching the desired *offset*. Larger *n* leads to a significant performance penalty. Another penalty is the extra-`SELECT` needed to count the total number of records. In order to understand how bad *offset* pagination can perform please check [this](http://allyouneedisbackend.com/blog/2017/09/24/the-sql-i-love-part-1-scanning-large-table/) article. A screenshot from that article is below:
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootKeysetPagination/offset%20pagination.png)
Nevertheless, maybe this example is a little bit extreme. For relatively small datasets, *offset* pagination is not so bad (it is close in performance to *keyset* pagination), and, since Spring Boot provides built-in support for *offset* pagination via the `Page` API, it is very easy to use it. However, depending on the case, we can optimize a little bit the *offset* pagination as in the following examples:

Fetch a page as a `Page`:
- [Trigger `COUNT(*) OVER` And Return `Page<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageDtoOffsetPaginationWF)
- [Trigger `COUNT(*) OVER` And Return `Page<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageEntityOffsetPaginationExtraColumnWF)
- [Trigger `SELECT COUNT` Subquery And Return `Page<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageDtoOffsetPagination)
- [Trigger `SELECT COUNT` Subquery And Return `Page<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageEntityOffsetPaginationExtraColumn)
- [Trigger `SELECT COUNT` Subquery And Return `Page<projection>` That Maps Entities And The Total Number Of Records Via Projection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageEntityOffsetPaginationProjection)

Fetch a page as a `List`:
- [Trigger `COUNT(*) OVER` And Return `List<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListDtoOffsetPaginationWF)
- [Trigger `COUNT(*) OVER` And Return `List<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListEntityOffsetPaginationExtraColumnWF)
- [Trigger `SELECT COUNT` Subquery And Return `List<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListDtoOffsetPagination)
- [Trigger `SELECT COUNT` Subquery And Return `List<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListEntityOffsetPaginationExtraColumn)
- [Trigger `SELECT COUNT` Subquery And Return `List<projection>` That Maps Entities And The Total Number Of Records Via Projection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListEntityOffsetPaginationProjection)

**But:** If *offset* pagination is causing you performance issues and you decide to go with *keyset* pagination then please check [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootKeysetPagination) (*keyset* pagination).

**Key points of classical *offset* pagination:**\
     - write a repository that extends `PagingAndSortingRepository`\
     - call or write methods that returns `Page<entity>`

**Examples of classical *offset* pagination:**\
     - call the built-in `findAll(Pageable)` without sorting:\
     `repository.findAll(PageRequest.of(page, size));`\
     - call the built-in `findAll(Pageable)` with sorting:\
     `repository.findAll(PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name")));`\
     - use Spring Data query creation to define new methods in your repository:\
     `Page<Author> findByName(String name, Pageable pageable);`\
     `Page<Author> queryFirst10ByName(String name, Pageable pageable);`
     
-----------------------------------------------------------------------------------------------------------------------    

74. **[How To Optimize Batch Inserts of Parent-Child Relationships In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertOrder)**

**Description:** Let's suppose that we have a one-to-many relationship between `Author` and `Book` entities. When we save an author, we save his/her books as well thanks to cascading all/persist. We want to create a bunch of authors with books and save them in the database (e.g., a MySQL database) using the batch technique. By default, this will result in batching each author and the books per author. In order to batch authors and books, we need to **order inserts** as in this application.

**Key points:**\
     - beside all setting specific to batching inserts in MySQL, we need to set up in application.properties the following property: `spring.jpa.properties.hibernate.order_inserts=true`

**Example without ordered inserts:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertOrder/batch%20inserts%20including%20associations%20no%20order%20of%20inserts.png)

**Example with ordered inserts:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertOrder/batch%20inserts%20including%20associations%20ordered%20inserts.png)

-----------------------------------------------------------------------------------------------------------------------    

75. **[How To Batch Updates In MySQL](#)**

**Implementations:**
- [Single entity update](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchUpdateOrderSingleEntity)
- [Parent-child relationship update](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchUpdateOrder)

**Description:** Batch updates in MySQL.

**Key points:**\
     - in `application.properties` set `spring.jpa.properties.hibernate.jdbc.batch_size`\
     - in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL, statements get rewritten into a single `String` buffer and sent in a single request)\
     - in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)\
     - in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))\
     - in case of using a parent-child relationship with cascade all/persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_updates=true` to optimize the batching by ordering updates\
     - before Hibernate 5, we need to set in `application.properties` a setting for enabling batching for versioned entities during update and delete operations (entities that contains `@Version` for implicit optimistic locking); this setting is: `spring.jpa.properties.hibernate.jdbc.batch_versioned_data=true`; starting with Hibernate 5, this setting should be `true` by default
   
**Output example for single entity:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchUpdateOrderSingleEntity/batch%20updates.png)     
   
**Output example for parent-child relationship:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchUpdateOrder/batch%20updates.png)

-----------------------------------------------------------------------------------------------------------------------    

76. **[How To Batch Deletes That Don't Involve Associations In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchDeleteSingleEntity)**

**Description:** Batch deletes that don't involve associations in MySQL.

**Note:** Spring `deleteAllInBatch()` and `deleteInBatch()` don't use delete batching and don't prevent *lost updates*. They rely on `Query.executeUpdate()` to trigger *bulk* operations. These operations are fast, but Hibernate doesn’t know which entities are removed, therefore, the Persistent Context is not updated accordingly (it is advisable to flush (before delete) and close/clear (after delete) the Persistent Context accordingly to avoid issues created by unflushed (if any) or outdated (if any) entities). The first one simply triggers a `delete from entity_name` statement and is very useful for deleting all records. The second one triggers a `delete from entity_name where id=? or id=? or id=? ...` statement, therefore, is prone to cause issues if the generated `DELETE` statement exceedes the maximum accepted size. This drawback can be controlled by deleting the data in chunks, relying on `IN` operator, and so on. *Bulk* operations are faster than batching which can be achieved via the `deleteAll()`, `deleteAll(Iterable<? extends T> entities)` or `delete()` method. Behind the scene, the two flavors of `deleteAll()` relies on `delete()`. The `delete()`/`deleteAll()` methods rely on `EntityManager.remove()` therefore the persistent context is synchronized accordingly.

**Key points for *classical* delete batching:**\
     - for delete batching rely on `deleteAll()`, `deleteAll(Iterable<? extends T> entities)` or `delete()` method\
     - in `application.properties` set `spring.jpa.properties.hibernate.jdbc.batch_size`\
     - in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL, statements get rewritten into a single `String` buffer and sent in a single request)\
     - in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)\
     - in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))\
     - before Hibernate 5, we need to set in `application.properties` a setting for enabling batching for versioned entities during update and delete operations (entities that contains `@Version` for implicit optimistic locking); this setting is: `spring.jpa.properties.hibernate.jdbc.batch_versioned_data=true`; starting with Hibernate 5, this setting should be `true` by default
    
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchDeleteSingleEntity/batch%20deletes.png)

-----------------------------------------------------------------------------------------------------------------------    

77. **[How To Batch Deletes In MySQL Via orphanRemoval=true](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchDeleteOrphanRemoval)**
 
**Description:** Batch deletes in MySQL via `orphanRemoval=true`.

**Note:** Spring `deleteAllInBatch()` and `deleteInBatch()` don't use batching, don't prevent *lost updates* and don't take advantage of `orphanRemoval=true`. They trigger *bulk* operations and the Persistent Context is not synchronized accordingly (it is advisable to flush (before delete) and close/clear (after delete) the Persistent Context accordingly to avoid issues created by unflushed (if any) or outdated (if any) entities). The first one simply triggers a `delete from entity_name` statement, while the second one triggers a `delete from entity_name where id=? or id=? or id=? ...` statement. Using these methods for deleting parent-entities and the associated entites (child-entities) requires explicit calls for both sides. For batching rely on `deleteAll()`, `deleteAll(Iterable<? extends T> entities)` or even better, on `delete()` method. Behind the scene, `deleteAll()` methods uses `delete()`. The `delete()` and `deleteAll()` methods rely on `EntityManager.remove()`, therefore, the persistent context is synchronized.

**Key points for using `deleteAll()/delete()`:**\
     - in this example, we have a `Author` entity and each author can have several `Book` (*one-to-many*)\
     - first, we use `orphanRemoval=true` and `CascadeType.ALL`\
     - second, we dissociate all `Book` from the corresponding `Author`\
     - third, we explicitly (manually) flush the persistent context; is time for `orphanRemoval=true` to enter into the scene; thanks to this setting, all disassociated books will be deleted; the generated `DELETE` statements are batched (if `orphanRemoval` is set to `false`, a bunch of updates will be executed instead of deletes)\
     - forth, we delete all `Author` via the `deleteAll()` or `delete()` method (since we have dissaciated all `Book`, the `Author` deletion will take advantage of batching as well)

-----------------------------------------------------------------------------------------------------------------------    

78. **[How To Batch Deletes In MySQL Via SQL "ON DELETE CASCADE"](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchDeleteCascadeDelete)**

**Description:** Batch deletes in MySQL via `ON DELETE CASCADE`. Auto-generated database schema will contain the `ON DELETE CASCADE` directive.

**Note:** Spring `deleteAllInBatch()` and `deleteInBatch()` don't use delete batching and don't prevent *lost updates*. They trigger *bulk* operations via `Query.executeUpdate()`, therefore, the persistent context is not synchronized accordingly (it is advisable to flush (before delete) and close/clear (after delete) the Persistent Context accordingly to avoid issues created by unflushed (if any) or outdated (if any) entities). The first one simply triggers a `delete from entity_name` statement, while the second one triggers a `delete from entity_name where id=? or id=? or id=? ...` statement. Both of them take advantage on `ON DELETE CASCADE` and are very efficient. For delete batching rely on `deleteAll()`, `deleteAll(Iterable<? extends T> entities)` or `delete()` method. Behind the scene, the two flavors of `deleteAll()` relies on `delete()`. Mixing batching with database automatic actions (`ON DELETE CASCADE`) will result in a partially synchronized persistent context.

**Key points:**\
     - in this application, we have a `Author` entity and each author can have several `Book` (*one-to-many*)\
     - first, we remove `orphanRemoval` or set it to `false`\
     - second, we use only `CascadeType.PERSIST` and `CascadeType.MERGE`\
     - third, we set `@OnDelete(action = OnDeleteAction.CASCADE)` next to `@OneToMany`\
     - fourth, we set `spring.jpa.properties.hibernate.dialect` to `org.hibernate.dialect.MySQL5InnoDBDialect`\
     - fifth, we run through each `deleteFoo()` method
        
**Output example:**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchDeleteCascadeDelete/batch%20delete%20via%20SQL%20cascade%20delete.png)

-----------------------------------------------------------------------------------------------------------------------    

79. **[How To Use Hibernate `@NaturalId` In Spring Boot Style](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNaturalIdImpl)**

**Alternative implementation:** In case that you want to avoid extending `SimpleJpaRepository` check this [implementation](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNaturalId).

**Description:** This is a SpringBoot application that maps a natural business key using Hibernate `@NaturalId`. This implementation allows us to use `@NaturalId` as it was provided by Spring.

**Key points:**
- in the entity (e.g., `Book`), mark the properties (business keys) that should act as natural IDs with `@NaturalId`; commonly, there is a single such property, but multiple are suppored as well as [here](https://docs.jboss.org/hibernate/orm/5.0/mappingGuide/en-US/html/ch07.html).
- for non-mutable ids, mark the columns as `@NaturalId(mutable = false)` and `@Column(nullable = false, updatable = false, unique = true, ...)`
- for mutable ids, mark the columns as `@NaturalId(mutable = true)` and `@Column(nullable = false, updatable = true, unique = true, ...)`
- override the `equals()` and `hashCode()` using the natural id(s)
- define a `@NoRepositoryBean` interface (`NaturalRepository`) to define two methods, named `findBySimpleNaturalId()` and `findByNaturalId()`
- provide an implementation for this interface (`NaturalRepositoryImpl`) relying on Hibernate, `Session`, `bySimpleNaturalId()` and `byNaturalId()` methods
- use `@EnableJpaRepositories(repositoryBaseClass = NaturalRepositoryImpl.class)` to register this implementation as the base class
- for the entity, write a classic repository
- inject this class in your services and call `findBySimpleNaturalId()` or `findByNaturalId()

-----------------------------------------------------------------------------------------------------------------------    

80. **[How To Set Up p6spy in Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootP6spy)**

**Description:** This is a Spring Boot application that uses [P6Spy](https://github.com/p6spy/p6spy). *P6Spy is a framework that enables database data to be seamlessly intercepted and logged with no code changes to the application.*

**Key points:**\
     - in `pom.xml`, add the P6Spy Maven dependency\
     - in `application.properties`, set up JDBC URL as, `jdbc:p6spy:mysql://localhost:3306/db_users`\
     - in `application.properties`, set up driver class name as, `com.p6spy.engine.spy.P6SpyDriver`\
     - in the application root folder add the file `spy.properties` (this file contains P6Spy configurations); in this application, the logs will be outputed to console, but you can easy switch to a file; more details about P6Spy configurations can be found in documentation

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootP6spy/p6spy.png)

-----------------------------------------------------------------------------------------------------------------------    

81. **[How To Retry Transactions After OptimisticLockException Shaped Via @Version](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRetryVersionedOptimisticLocking)**

**Note:** Optimistic locking via `@Version` works for detached entities as well.

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic locking exception. When such exception occur, the application retry the corresponding transaction via [db-util](https://github.com/vladmihalcea/db-util) library developed by Vlad Mihalcea.

**Key points:**\
     - in `pom.xml`, add the `db-util` dependency\
     - configure the `OptimisticConcurrencyControlAspect` bean\
     - mark the method (not annotated with `@Transactional`) that is prone to throw (or that calls a method that is prone to throw (this method can be annotated with `@Transactional`)) an optimistic locking exception with `@Retry(times = 10, on = OptimisticLockingFailureException.class)`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootRetryVersionedOptimisticLocking/Retry%20Optimistic%20Lock.png)

-----------------------------------------------------------------------------------------------------------------------    

82. **[How To Retry Transaction After OptimisticLockException Shaped Via Hibernate Version-less Optimistic Locking](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRetryVersionlessOptimisticLocking)**

**Note:** Optimistic locking via Hibernate version-less doesn't work for detached entities (don't close the persistent context).

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic locking exception (e.g., in Spring Boot, `OptimisticLockingFailureException`) via Hibernate version-less optimistic locking. When such exception occur, the application retry the corresponding transaction via [db-util](https://github.com/vladmihalcea/db-util) library developed by Vlad Mihalcea.

**Key points:**\
     - in `pom.xml`, add the `db-util` library dependency\
     - configure the `OptimisticConcurrencyControlAspect` bean\
     - annotate the corresponding entity (e.g., `Inventory`) with `@DynamicUpdate` and `@OptimisticLocking(type = OptimisticLockType.DIRTY)`\
     - mark the method (not annotated with `@Transactional`) that is prone to throw (or that calls a method that is prone to throw (this method can be annotated with `@Transactional`)) an optimistic locking exception with `@Retry(times = 10, on = OptimisticLockingFailureException.class)`

-----------------------------------------------------------------------------------------------------------------------    

83. **[How To Enrich DTOs With Virtual Properties Via Spring Projections](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjectionsAndVirtualProperties)**

**Note:** You may also like to read the recipe, ["How To Create DTOs Via Spring Data Projections"](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)

**Description:** Fetch only the needed data from the database via Spring Data Projections (DTOs) and enrich the result via virtual properties.

**Key points:**\
     - we fetch from the database only the user `name` and `city`\
     - in the projection interface, `UserDetail`, use the `@Value` and Spring SpEL to point to a backing property from the domain model (in this case, the domain model property `city` is exposed via the virtual property `livingin`)\
     - in the projection interface, `UserDetail`, use the `@Value` and Spring SpEL to enrich the result with two virtual properties that don't have a match in the domain model (in this case, `sessionid` and `status`)

**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaProjectionsAndVirtualProperties/dto%20spring%20projection%20and%20virtual%20properties.png)

-----------------------------------------------------------------------------------------------------------------------    

84. **[How To Use Query Creation Mechanism For JPA To Limit Result Size](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLimitResultSizeViaQueryCreator)**

**Description:** Spring Data comes with the query creation mechanism for JPA that is capable to interpret a query method name and convert it into a SQL query. This is possible as long as we respect the naming conventions of this mechanism. This is an application that exploit this mechanism to write queries that limit the result size. Basically, the name of the query method instructs Spring Data how to add the `LIMIT` (or similar clauses depending on the RDBMS) clause to the generated SQL queries.

**Key points:**\
     - define a Spring Data classic repository (e.g., `AuthorRepository`)\
     - write query methods respecting the query creation mechanism for JPA naming conventions     

**Examples:**\
    - `List<Author> findFirst5ByAge(int age);`\
    - `List<Author> findFirst5ByAgeGreaterThanEqual(int age);`\
    - `List<Author> findFirst5ByAgeLessThan(int age);`\
    - `List<Author> findFirst5ByAgeOrderByNameDesc(int age);`\
    - `List<Author> findFirst5ByGenreOrderByAgeAsc(String genre);`\
    - `List<Author> findFirst5ByAgeGreaterThanEqualOrderByNameAsc(int age);`\
    - `List<Author> findFirst5ByGenreAndAgeLessThanOrderByNameDesc(String genre, int age);`\
    - `List<AuthorDto> findFirst5ByOrderByAgeAsc();`\
    - `Page<Author> queryFirst10ByName(String name, Pageable p);`\
    - `Slice<Author> findFirst10ByName(String name, Pageable p);`
    
**The list of supported keywords is listed below:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLimitResultSizeViaQueryCreator/supported%20keywords%20inside%20method%20names.png)

-----------------------------------------------------------------------------------------------------------------------    

85. **[How To Generate A Schema Via `schema-*.sql`In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSchemaSql)**

**Note:** As a rule, in real applications avoid generating schema via `hibernate.ddl-auto`. Use `schema-*.sql` file or better `Flyway` or `Liquibase` migration tools.

**Description:** This application is an example of using `schema-*.sql` to generate a schema(database) in MySQL.

**Key points:**\
     - in `application.properties`, set the JDBC URL (e.g., `spring.datasource.url=jdbc:mysql://localhost:3306/bookstoredb?createDatabaseIfNotExist=true`)\
     - in `application.properties`, disable DDL auto (just don't use explicitly the `hibernate.ddl-auto` setting)\
     - in `application.properties`, instruct Spring Boot to initialize the schema from `schema-mysql.sql` file 

-----------------------------------------------------------------------------------------------------------------------    

86. **[How To Generate Two Databases Via `schema-*.sql` And Match Entities To Them Via `@Table` In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootMatchEntitiesToTablesTwoSchemas)**

**Note:** As a rule, in real applications avoid generating schema via `hibernate.ddl-auto`. Use `schema-*.sql` file or better `Flyway` or `Liquibase`.

**Description:** This application is an example of using `schema-*.sql` to generate two databases in MySQL. The databases are matched at entity mapping via `@Table`.

**Key points:**\
     - in `application.properties`, set the JDBC URL without the database, e.g., `spring.datasource.url=jdbc:mysql://localhost:3306`\
     - in `application.properties`, disable DDL auto (just don't specify `hibernate.ddl-auto`)\
     - in `aaplication.properties`, instruct Spring Boot to initialize the schema from `schema-mysql.sql` file\
     - in `Author` entity, specify that the corresponding table (`author`) is in the database `authorsdb` via `@Table(schema="authorsdb")`\
     - in `Book` entity, specify that the corresponding table (`book`) is in the database `booksdb` via `@Table(schema="booksdb")`

**Output example:**
- Persisting a `Author` results in the following SQL: `insert into authorsdb.author (age, genre, name) values (?, ?, ?)`
- Persisting a `Book` results the following SQL: `insert into booksdb.book (isbn, title) values (?, ?)`

-----------------------------------------------------------------------------------------------------------------------    

87. **[How To Stream Result Set Via Spring Data In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootStreamAndMySQL)**

**Note:** For web-applications, pagination should be the way to go, not streaming. But, if you choose streaming then keep in mind the golden rule: keep th result set as small as posible. Also, keep in mind that the Execution Plan might not be as efficient as when using SQL-level pagination.

**Description:** This application is an example of streaming the result set via Spring Data and MySQL. This example can be adopted for databases that fetches the entire result set in a single roundtrip causing performance penalties.

**Key points:**\
     - rely on forward-only result set (default in Spring Data)\
     - rely on read-only statement (add `@Transactional(readOnly=true)`)\
     - set the fetch-size set (e.g. 30, or row-by-row; `Integer.MIN_VALUE` (recommended in MySQL))      

-----------------------------------------------------------------------------------------------------------------------    

88. **[How To Migrate MySQL Database Using Flyway - Database Created Via `createDatabaseIfNotExist`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayMySQLQuick)**

**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate` and rely on Flyway or Liquibase

**Description:** This application is an example of migrating a MySQL database via Flyway when the database exists (it is created before migration via MySQL specific parameter, `createDatabaseIfNotExist=true`). 

**Key points:**\
     - for Maven, in `pom.xml`, add the Flyway dependency\
     - remove (disable) `spring.jpa.hibernate.ddl-auto`\
     - in `application.properties`, set the JDBC URL as follows: `jdbc:mysql://localhost:3306/bookstoredb?createDatabaseIfNotExist=true`\
     - each SQL file containing the schema update add it in `classpath:db/migration`\
     - each SQL file name it as `V1.1__Description.sql`, `V1.2__Description.sql`, ...

-----------------------------------------------------------------------------------------------------------------------    

89. **[How To Migrate MySQL Database Using Flyway - Database Created Via `spring.flyway.schemas`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayMySQLDatabase)**

**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate` and rely on Flyway or Liquibase

**Description:** This application is an example of migrating a MySQL database when the database is created by Flyway via `spring.flyway.schemas`. In this case, the entities should be annotated with `@Table(schema = "bookstoredb")` or `@Table(catalog = "bookstoredb")`. Here, the database name is `bookstoredb`.

**Key points:**\
     - for Maven, in `pom.xml`, add the Flyway dependency\
     - remove (disable) `spring.jpa.hibernate.ddl-auto`\
     - in `application.properties`, set the JDBC URL as follows: `jdbc:mysql://localhost:3306/`\
     - in `application.properties`, add `spring.flyway.schemas=bookstoredb`, where `bookstoredb` is the database that should be created by Flyway (feel free to add your own database name)\
     - each entity that should be stored in this database should be annotated with, `@Table(schema/catalog = "bookstoredb")`\
     - each SQL file containing the schema update add it in `classpath:db/migration`\
     - each SQL file name it as `V1.1__Description.sql`, `V1.2__Description.sql`, ...

**Output of migration history example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootFlywayMySQLDatabase/flyway_schema_history%20table.png)

-----------------------------------------------------------------------------------------------------------------------    

90. **[How To Auto-Create And Migrate Schemas For Two Data Sources (MySQL and PostgreSQL) Using Flyway](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayTwoVendors)**

**Note:** For production don't rely on `hibernate.ddl-auto` to create your schema. Remove (disable) `hibernate.ddl-auto` or set it to `validate` and rely on Flyway or Liquibase.

**Description:** This application is an example of auto-creating and migrating schemas for MySQL and PostgreSQL. In addition, each data source uses its own HikariCP connection pool. In case of MySQL, where *schema*=*database*, we auto-create the schema (`authorsdb`) based on `createDatabaseIfNotExist=true`. In case of PostgreSQL, where a database can have multiple schemas, we use the default `postgres` database and auto-create in it the schema, `booksdb`. For this we rely on Flyway, which is capable to create a missing schema.

**Key points:**\
     - for Maven, in `pom.xml`, add the Flyway dependency\
     - remove (disable) `spring.jpa.hibernate.ddl-auto`\
     - in `application.properties`, configure the JDBC URL for MySQL as, `jdbc:mysql://localhost:3306/authorsdb?createDatabaseIfNotExist=true` and for PostgreSQL as, `jdbc:postgresql://localhost:5432/postgres?currentSchema=booksdb`\
     - in `application.properties`, set `spring.flyway.enabled=false` to disable default behavior\
     - programmatically create a `DataSource` for MySQL and one for PostgreSQL\
     - programmatically create a `FlywayDataSource` for MySQL and one for PostgreSQL\
     - programmatically create an `EntityManagerFactory` for MySQL and one for PostgreSQL\
     - for MySQL, place the migration SQLs files in `db\migration\mysql`\
     - for PostgreSQL, place the migration SQLs files in `db\migration\postgresql`   

-----------------------------------------------------------------------------------------------------------------------    

91. **[How To Auto-Create And Migrate Two Schemas In PostgreSQL Using Flyway](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayPostgreSqlTwoSchemas)**

**Note:** or production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate` and rely on Flyway or Liquibase.

**Description:** This application is an example of auto-creating and migrating two schemas in PostgreSQL using Flyway. In addition, each data source uses its own HikariCP connection pool. In case of PostgreSQL, where a database can have multiple schemas, we use the default `postgres` database and auto-create two schemas, `authors` and `books`. For this we rely on Flyway, which is capable to create the missing schemas.

**Key points:**\
     - for Maven, in `pom.xml`, add the Flyway dependency\
     - remove (disable) `spring.jpa.hibernate.ddl-auto`\
     - in `application.properties`, configure the JDBC URL for `books` as `jdbc:postgresql://localhost:5432/postgres?currentSchema=books` and for `authors` as `jdbc:postgresql://localhost:5432/postgres?currentSchema=authors`\
     - in `application.properties`, set `spring.flyway.enabled=false` to disable default behavior\
     - programmatically create two `DataSource`, one for `books` and one for `authors`\
     - programmatically create two `FlywayDataSource`, one for `books` and one for `authors`\
     - programmatically create two `EntityManagerFactory`, one for `books` and one for `authors`\
     - for `books`, place the migration SQLs files in `db\migration\books`\
     - for `authors`, place the migration SQLs files in `db\migration\authors`   

-----------------------------------------------------------------------------------------------------------------------    

92. **[How To JOIN FETCH an @ElementCollection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootElementCollectionJoinFetch)**

**Description:** This application is an example applying `JOIN FETCH` to fetch an `@ElementCollection`.

**Key points:**\
     - by default, `@ElementCollection` is loaded lazy, keep it lazy\
     - use `JOIN FETCH` in the repository

-----------------------------------------------------------------------------------------------------------------------    

93. **[How To Map An Entity To a Query (@Subselect) in a Spring Boot Application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSubselect)**

**Note:** Consider using `@Subselect` only if using DTO + extra queries or map a database view to an entity is not a solution.

**Description:** This application is an example of mapping an entity to a query via Hibernate, `@Subselect`. Mainly, we have two entities in a bidirectional *one-to-many* association. An `Author` has wrote several `Book`. The idea is to write a *read-only* query to fetch from `Author` only some fields (e.g., DTO), but to have the posibility to call `getBooks()` and fetch the `Book` in a lazy manner as well. As you know, a classic DTO cannot be used, since such DTO is not managed and we cannot fetch managed associations. Via Hibernate, `@Subselect` we can map a *read-only*, *immutable* entity to a query. Being an entity, it can lazy load the managed associations. 

**Key points:**\
     - define a new entity that contains only the needed fields from the `Author` (including association to `Book`)\
     - for these fields, define only getters\
     - mark the entity as `@Immutable` since no write operations are allowed\
     - flush pending state transitions for the used entities by `@Synchronize`\
     - use `@Subselect` to write the needed query, map an entity to an SQL query
     
-----------------------------------------------------------------------------------------------------------------------    

94. **[How To Use Hibernate Soft Deletes in a Spring Boot Application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSoftDeletes)**

**Description:** This application is an example of using Hibernate soft deletes in a Spring Boot application. 

**Key points:**\
     - define an abstract class `BaseEntity` with a field named `deleted`\
     - the entities (e.g., `Author` and `Book` entities) that should take advantage of soft deletes should extend `BaseEntity`\
     - these entities should be marked with Hibernate, `@Where` annotation like this: `@Where(clause = "deleted = false")`\
     - these entities should be marked with Hibernate, `@SQLDelete` annotation to trigger `UPDATE` SQLs in place of `DELETE` SQLs, as follows: `@SQLDelete(sql = "UPDATE author SET deleted = true WHERE id = ?")`\
     - for fetching all entities including those marked as deleted or for fetching only the entities marked as deleted we need to rely on SQL native queries

**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSoftDeletes/soft%20deletes.png)

-----------------------------------------------------------------------------------------------------------------------    

95. **[How To Programmatically Configure HikariCP Via DataSourceBuilder](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootProgDataSourceBuilderHikariCP)**

**If you use the `spring-boot-starter-jdbc` or `spring-boot-starter-data-jpa` "starters", you automatically get a dependency to HikariCP**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up HikariCP via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService` for simulating concurrent users. Check the HickariCP report revealing the connection pool status.

**Key points:**\
     - write a `@Bean` that returns the `DataSource` configured programmatically via `DataSourceBuilder`

-----------------------------------------------------------------------------------------------------------------------    

96. **[How To Setup Spring Data JPA Auditing](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAudit)**
 
**Description:** Auditing is useful for maintaining history records. This can later help us in tracking user activities. 
 
**Key points:**\
     - create an abstract base entity (e.g., `BaseEntity`) and annotate it with `@MappedSuperclass` and `@EntityListeners({AuditingEntityListener.class})`\
     - in this base entity, add the following fields that will be automatically persisted:\
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- `@CreatedDate protected LocalDateTime created;`\
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- `@LastModifiedDate protected LocalDateTime lastModified;`\
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- `@CreatedBy protected U createdBy;`\
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- `@LastModifiedBy protected U lastModifiedBy;`\
     - enable auditing via `@EnableJpaAuditing(auditorAwareRef = "auditorAware")`\
     - provide an implementation for `AuditorAware` (this is needed for persisting the user that performed the modification; use Spring Security to return the currently logged-in user)\
     - expose this implementation via `@Bean`\
     - entites that should be audited should extend the base entity\
     - store the date-time in database in UTC
     
-----------------------------------------------------------------------------------------------------------------------

97. **[Hibernate Envers Auditing (`spring.jpa.hibernate.ddl-auto=create`)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnvers)**
 
**Description:** Auditing is useful for maintaining history records. This can later help us in tracking user activities. 
 
**Key points:**\
     - in `pom.xml` add the dependency `hibernate-envers` and JAXB API\
     - each entity that should be audited should be annotated with `@Audited`\
     - optionally, annotate entities with `@AuditTable` to rename the table used for auditing\
     - rely on `ValidityAuditStrategy` for fast database reads, but slower writes (slower than the default `DefaultAuditStrategy`)

-----------------------------------------------------------------------------------------------------------------------

98. **[Attributes Lazy Loading Via Subentities](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSubentities)**
 
**Description:** By default, the attributes of an entity are loaded eager (all at once). This application is an alternative to *Attribute Lazy Loading* from [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingBasic). This application uses a base class to isolate the attributes that should be loaded eagerly and subentities (entities that extends the base class) for isolating the attributes that should be loaded on demand.

<a href="#"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSubentities/attributes%20lazy%20loading%20via%20subentites.png" align="center" height="150" width="500" ></a>

**Key points:**\
     - create the base class (this is not an entity), `BaseAuthor`,  and annotate it with `@MappedSuperclass`\
     - create `AuthorShallow` subentity of `BaseAuthor` and don't add any attribute in it (this will inherit the attributes from the superclass)\
     - create `AuthorDeep` subentity of `BaseAuthor` and add to it the attributes that should be loaded on demand (e.g., `avatar`)\
     - map both subentities to the same table via `@Table(name = "author")`\
     - provide the typical repositories, `AuthorShallowRepository` and `AuthorDeepRepository`
     
**Run the following requests (via BookstoreController):**\
     - fetch all authors shallow (without avatars): `localhost:8080/authors/shallow`\
     - fetch all authors deep (with avatars): `localhost:8080/authors/deep`

-----------------------------------------------------------------------------------------------------------------------

99. **[DTOs Via Constructor And Spring Data Query Builder Mechanism](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoConstructor)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTOs allows us to extract only the needed data. In this application we rely on Constructor Expression and JPQL.

**Key points:**\
     - write a proper constructor in the DTO class\
     - rely on Spring Data Query Builder Mechanism for expression the SQL\
     - for using Spring Data Projections check this [recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections) 

**See also:**\
[Dto Via Constructor Expression and JPQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoConstructorExpression)

-----------------------------------------------------------------------------------------------------------------------

100. **[JOIN FETCH And DTOs](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaJoinFetch)**

**Description:** Combining `JOIN FETCH` and DTOs can be done under several constrains. Mainly, the JPQL containing the `JOIN FETCH` cannot be used to fetch only some columns from the involved entities (in such cases, `JOIN` is the proper choice). It must fetch all attributes of the involved entities. 

**Key points:**\
     - define two related entities (e.g., `Author` and `Book` in a one-to-many lazy bidirectional relationship)\
     - define the proper DTOs classes (e.g., `BookDto` and `AuthorDto`)\
     - the `BookDto` and `AuthorDto` may map only the needed columns, but the triggered SQL will fetch all of them anyway\
     - write a JPQL `JOIN FETCH` to fetch an author including his books

**Constrains:**\
     - this is ok: `SELECT a FROM Author a JOIN FETCH a.books`\
     - this is not ok: `SELECT a.age as age FROM Author a JOIN FETCH a.books` -> *org.hibernate.QueryException: query specified join fetching, but the owner of the fetched association was not present in the select list*\
     - this is not ok: `SELECT a FROM Author a JOIN FETCH a.books.title` ->  *org.hibernate.QueryException: illegal attempt to dereference collection [author0_.id.books] with element property reference [title]*

-----------------------------------------------------------------------------------------------------------------------

101. **[LEFT JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLeftJoinFetch)**

**Description:** Let's assume that we have two entities engaged in a one-to-many (or many-to-many) lazy bidirectional (or unidirectional) relationhip (e.g., `Author` has more `Book`). And, we want to trigger a single `SELECT` that fetches all `Author` and the corresponding `Book`. This is a job for `JOIN FETCH` which is converted behind the scene into a `INNER JOIN`. Being an `INNER JOIN`, the SQL will return only `Author` that have `Book`. If we want to return all `Author`, including those that doesn't have `Book`, then we can rely on `LEFT JOIN FETCH`. Similar, we can fetch all `Book`, including those with no registered `Author`.

**Key points:**\
     - define two related entities (e.g., `Author` and `Book` in a one-to-many lazy bidirectional relationship)\
     - write a JPQL `LEFT JOIN FETCH` to fetch all authors and books (fetch authors even if they don't have registered books)\
     - write a JPQL `LEFT JOIN FETCH` to fetch all books and authors (fetch books even if they don't have registered authors)

-----------------------------------------------------------------------------------------------------------------------

102. **[JOIN VS. JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinVSJoinFetch)**

**Description:** This is an application meant to reveal the differences between `JOIN` and `JOIN FETCH`. The important thing to keep in mind is that, in case of `LAZY` fetching, `JOIN` will not be capable to initialize the associations/collections along with their parent objects using a single SQL `SELECT`.  On the other hand, `JOIN FETCH` is capable to accomplish this kind of task. But, don't underestimate `JOIN`, because `JOIN` is the proper choice when we need to combine/join the columns of two (or more) tables in the same query, but we don't need to initialize the association on the returned entity (e.g., very useful for fetching DTOs).

**Key points:**\
     - define two related entities (e.g., `Author` and `Book` in a one-to-many lazy bidirectional relationship)\
     - write a JPQL `JOIN` and `JOIN FETCH` to fetch an author including his books\
     - write a JPQL `JOIN` and `JOIN FETCH` to fetch a book including its author
     
**Notice that:**\
     - via `JOIN`, fetching each `Author` of a `Book` (or each `Book` of an `Author`) may require additional `SELECT(s)` being prone to N+1 performance penalty\
     - via `JOIN FETCH`, fetching each `Author` of a `Book` (or each `Book` of an `Author`) required a single `SELECT`

-----------------------------------------------------------------------------------------------------------------------

103. **[Entity Inside Spring Projection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoEntityViaProjection)**
     
**Description:** If, for any reason, you need an entity in your Spring projection (DTO), then this application shows you how to do it via an example. In this case, there are two entities, `Author` and `Book`, involved in a lazy bidirectional one-to-many association  (it can be other association as well, or even no materialized association). And, we want to fetch in a Spring projection the authors as entities, `Author`, and the `title` of the books.

**Key points:**\
     - define two related entities (e.g., `Author` and `Book` in a one-to-many lazy bidirectional relationship)\
     - define the proper Spring projection having `public Author getAuthor()` and `public String getTitle()`\
     - write a JPQL to fetch data

-----------------------------------------------------------------------------------------------------------------------

104. **[Entity Inside Spring Projection (no association)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoEntityViaProjectionNoAssociation)**
     
**Description:** If, for any reason, you need an entity in your Spring projection (DTO), then this application shows you how to do it via an example. In this case, there are two entities, `Author` and `Book`, that have no materialized association between them, but, they share the `genre` attribute. We use this attribute to join authors with books via JPQL. And, we want to fetch in a Spring projection the authors as entities, `Author`, and the `title` of the books.

**Key points:**\
     - define two unrelated entities (e.g., `Author` and `Book`)\
     - define the proper Spring projection having `public Author getAuthor()` and `public String getTitle()`\
     - write a JPQL to fetch data

-----------------------------------------------------------------------------------------------------------------------

105. **[Avoid Entity In DTO Via Constructor Expression (no association)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAvoidEntityInDtoViaConstructor)**
     
**Description:** Let's assume that we have two entities, `Author` and `Book`. There is no materialized association between them, but, both entities shares an attribute named, `genre`. We want to use this attribute to join the tables corresponding to `Author` and `Book`, and fetch the result in a DTO. The result should contain the `Author` entity and only the `title` attribute from `Book`. Well, when you are in a scenario as here, it is strongly advisable to avoid fetching the DTO via *constructor expression*. This approach cannot fetch the data in a single `SELECT`, and is prone to N+1. Way better than this consists of using Spring projections, JPA `Tuple` or even Hibernate `ResultTransformer`. These approaches will fetch the data in a single `SELECT`. This application is a **DON'T DO THIS** example. Check the number of queries needed for fetching the data. In place, do it as here: [Entity Inside Spring Projection (no association)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoEntityViaProjectionNoAssociation).

-----------------------------------------------------------------------------------------------------------------------

106. **[How To DTO an @ElementCollection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoElementCollection)**

**Description:** This application is an example of fetching a DTO that includes attributes from an `@ElementCollection`.
 
**Key points:**\
     - by default, `@ElementCollection` is loaded lazy, keep it lazy\
     - use a Spring projection and `JOIN` in the repository 

-----------------------------------------------------------------------------------------------------------------------

107. **[Ordering The Set Of Associated Entities In @ManyToMany Via @OrderBy](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootManyToManySetAndOrderBy)**

**Description:** In case of `@ManyToMany` association, we always should rely on `Set` (not on `List`) for mapping the collection of associated entities (entities of the other parent-side). Why? Well, please see [Prefer Set Instead of List in @ManyToMany Relationships](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootManyToManyBidirectionalListVsSet). But, is well-known that `HashSet` doesn't have a predefined entry order of elements. If this is an issue then this application relies on `@OrderBy` which adds an `ORDER BY` clause in the SQL statement. The database will handle the ordering. Further, Hibernate will preserve the order via a `LinkedHashSet`.

This application uses two entities, `Author` and `Book`, involved in a lazy bidirectional many-to-many relationship. First, we fetch a `Book` by title. Further, we call `getAuthors()` to fetch the authors of this book. The fetched authors are ordered descending by name. The ordering is done by the database as a result of adding `@OrderBy("name DESC")`, and is preserved by Hibernate.

**Key points:**\
     - ask the database to handle ordering and Hibernate to preserve this order via `@OrderBy`\
     - this works with `HashSet`, but doesn't provide consistency across all transition states (e.g., over transient state)\
     - for consistency across transient state as well, consider using explicitly `LinkedHashSet` instead of `HashSet`

**Note:** Alternatively, we can use `@OrderColumn`. This gets materialized in an additional column in the junction table. This is needed for maintaining a permanent ordering of the related data.

-----------------------------------------------------------------------------------------------------------------------

108. **[Versioned Optimistic Locking And Detached Entities Sample](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootVersionedOptimisticLockingAndDettachedEntity)**

**Description:** This is a sample application that shows how versioned (`@Version`) optimistic locking and detached entity works. Running the application will result in an optimistic locking specific exception (e.g., the Spring Boot specific, `OptimisticLockingFailureException`).

**Key points:**\
     - in a transaction, fetch an entity via `findById(1L)`; commit transaction and close the persistence context\
     - in a second transaction, fetch another entity via `findById(1L)` and update it; commit the transaction and close the persistence context\
     - outside transactional context, update the detached entity (fetched in the first transaction)\
     - in a third transaction, call `save()` and pass to it the detached entity; trying to re-attach (`EntityManager.merge()`) the entity will end up in an optimistic locking exception since the version of the detached and just loaded entity don't match

-----------------------------------------------------------------------------------------------------------------------

109. **[How To Simulate OptimisticLockException Shaped Via @Version](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSimulateVersionedOptimisticLocking)**

**Note:** Optimistic locking via `@Version` works for detached entities as well.

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic lock exception. So, running the application should end up with a Spring specific `ObjectOptimisticLockingFailureException` exception.

**Key points:**\
     - set up versioned optimistic locking mechanism\
     - rely on two concurrent threads that call the same a `@Transactional` method used for updating data

-----------------------------------------------------------------------------------------------------------------------

110. **[How To Retry Transaction Via TransactionTemplate After OptimisticLockException Shaped Via @Version](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRetryVersionedOptimisticLockingTT)**

**Note:** Optimistic locking via `@Version` works for detached entities as well.

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic locking exception. When such exception occur, the application retry the corresponding transaction via [db-util](https://github.com/vladmihalcea/db-util) library developed by Vlad Mihalcea.

**Key points:**\
     - in `pom.xml`, add the `db-util` dependency\
     - configure the `OptimisticConcurrencyControlAspect` bean\
     - rely on `TransactionTemplate`

-----------------------------------------------------------------------------------------------------------------------

111. **[How To Simulate Version-less OptimisticLockException](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSimulateVersionlessOptimisticLocking)**

**Note:** Version-less optimistic locking doesn't work for detached entities (do not close the persistence context).

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic lock exception. So, running the application should end up with a Spring specific `ObjectOptimisticLockingFailureException` exception.

**Key points:**\
     - set up version-less optimistic locking mechanism\
     - rely on two concurrent threads that call the same a `@Transactional` method used for updating data

-----------------------------------------------------------------------------------------------------------------------

112. **[How To Retry Transaction Via TransactionTemplate After OptimisticLockException Shaped Via Hibernate Version-less Optimistic Locking Mechanism](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRetryVersionlessOptimisticLockingTT)**

**Note:** Version-less optimistic locking doesn't work for detached entities (do not close the persistence context).

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic locking exception. When such exception occur, the application retry the corresponding transaction via [db-util](https://github.com/vladmihalcea/db-util) library developed by Vlad Mihalcea.

**Key points:**\
     - in `pom.xml`, add the `db-util` dependency\
     - configure the `OptimisticConcurrencyControlAspect` bean\
     - rely on `TransactionTemplate`

-----------------------------------------------------------------------------------------------------------------------

113. **[HTTP Long Conversation Via Versioned Optimistic Locking And Detached Entities In Session](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHTTPLongConversationDetachedEntity)**

**Description:** This is a sample application that shows how to take advantage of versioned optimistic locking and detached entities in HTTP long conversations. The climax consists of storing the detached entities across multiple HTTP requests. Commonly, this can be accomplished via HTTP session. 

**Key points:**\
     - prepare the entity via `@Version`\
     - rely on `@SessionAttributes` for storing the detached entities    

**Sample output (check the message caused by optimistic locking exception):**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHTTPLongConversationDetachedEntity/http%20long%20conversations%20detached%20entity%20ole.png)

-----------------------------------------------------------------------------------------------------------------------

114. **[Filter Association Via Hibernate @Where](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFilterAssociation)**

**Note:** Rely on this approach only if you simply cannot use `JOIN FETCH WHERE` or `@NamedEntityGraph`.

**Description:** This application is a sample of using Hibernate `@Where` for filtering associations. 

**Key points:**\
     - use `@Where(clause = "condition to be met")` in entity (check the `Author` entity)

-----------------------------------------------------------------------------------------------------------------------

115. **[Batch Inserts In Spring Boot Style](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsSpringStyle)**

**Description:** Batch inserts (in MySQL) in Spring Boot style.

**Key points:**\
     - in `application.properties` set `spring.jpa.properties.hibernate.jdbc.batch_size`\
     - in `application.properties` set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)\
     - in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)\
     - in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)\
     - in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))\
     - in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts\
     - in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since the Hibernate `IDENTITY` will cause batching to be disabled\
     - if is not needed then ensure that Second Level Cache is disabled via `spring.jpa.properties.hibernate.cache.use_second_level_cache=false`
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsSpringStyle/batch%20inserts%20in%20spring%20boot%20style.png)

-----------------------------------------------------------------------------------------------------------------------

116. **[Offset Pagination - Trigger `COUNT(*) OVER` And Return `Page<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageEntityOffsetPaginationExtraColumnWF)**

**Description:** Typically, in offset pagination, there is one query needed for fetching the data and one for counting the total number of records. But, we can fetch this information in a single database rountrip via a `SELECT COUNT` subquery nested in the main `SELECT`. Even better, for databases vendors that support *Window Functions* there is a solution relying on `COUNT(*) OVER()` as in this application that uses this window function in a native query against MySQL 8. So, prefer this one instead of `SELECT COUNT` subquery.This application fetches data as `Page<entity>` via Spring Boot offset pagination, but, if the fetched data is *read-only*, then rely on `Page<dto>` as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageDtoOffsetPaginationWF). 

**Key points:**\
     - write a repository that extends `PagingAndSortingRepository`\
     - in the entity, add an extra column for representing the total number of records and annotate it as `@Column(insertable = false, updatable = false)`\
     - fetch data via a native query (that includes counting) into a `List<entity>`, and a `Pageable`\
     - use the fetched `List<entity>` and `Pageable` to create a `Page<entity>`

-----------------------------------------------------------------------------------------------------------------------

117. **[Offset Pagination - Trigger `SELECT COUNT` Subquery And Return `List<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListEntityOffsetPaginationExtraColumn)**

**Description:** This application fetches data as `List<entity>` via Spring Boot offset pagination. The `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. Therefore, there will be a single database roundtrip instead of two (typically, one query is needed for fetching the data and one for counting the total number of records).

**Key points:**\
     - write a repository that extends `PagingAndSortingRepository`\
     - in the `entity`, add an extra column for representing the total number of records and annotate it as `@Column(insertable = false, updatable = false)`\
     - fetch data via a native query (that includes `SELECT COUNT` subquery) into a `List<entity>` 

-----------------------------------------------------------------------------------------------------------------------

118. **[Offset Pagination - Trigger `SELECT COUNT` Subquery And Return `List<projection>` That Maps Entities And The Total Number Of Records Via Projection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListEntityOffsetPaginationProjection)**
   
**Description:** This application fetches data as `List<projection>` via Spring Boot offset pagination. The projection maps the entity and the total number of records. This information is fetched in a single database rountrip because the `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. Therefore, there will be a single database roundtrip instead of two (typically, there is one query needed for fetching the data and one for counting the total number of records). Use this approch only if the fetched data is not *read-only*. Otherwise, prefer `List<dto>` as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListDtoOffsetPagination).

**Key points:**\
     - write a Spring projection that maps the entity and the total number of records\
     - write a repository that extends `PagingAndSortingRepository`\
     - fetch data via a JPQL query (that includes `SELECT COUNT` subquery) into a `List<projection>`

-----------------------------------------------------------------------------------------------------------------------

119. **[Offset Pagination - Trigger `COUNT(*) OVER` And Return `List<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListEntityOffsetPaginationExtraColumnWF)**
 
**Description:** Typically, in offset pagination, there is one query needed for fetching the data and one for counting the total number of records. But, we can fetch this information in a single database rountrip via a `SELECT COUNT` subquery nested in the main `SELECT`. Even better, for databases vendors that support *Window Functions* there is a solution relying on `COUNT(*) OVER()` as in this application that uses this window function in a native query against MySQL 8. So, prefer this one instead of `SELECT COUNT` subquery.This application fetches data as `List<entity>` via Spring Boot offset pagination, but, if the fetched data is *read-only*, then rely on `List<dto>` as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListDtoOffsetPaginationWF). 

**Key points:**\
     - write a repository that extends `PagingAndSortingRepository`\
     - in the `entity`, add an extra column for representing the total number of records and annotate it as `@Column(insertable = false, updatable = false)`\
     - fetch data via a native query (that includes `COUNT(*) OVER` subquery) into a `List<entity>` 

-----------------------------------------------------------------------------------------------------------------------

120. **[Offset Pagination - Trigger `SELECT COUNT` Subquery And Return `Page<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageEntityOffsetPaginationExtraColumn)**

**Description:** This application fetches data as `Page<entity>` via Spring Boot offset pagination. Use this only if the fetched data will be modified. Otherwise, fetch `Page<dto>` as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageDtoOffsetPagination). The `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. Therefore, there will be a single database roundtrip instead of two (typically, there is one query needed for fetching the data and one for counting the total number of records).

**Key points:**\
     - write a repository that extends `PagingAndSortingRepository`\
     - in the entity, add an extra column for representing the total number of records and annotate it as `@Column(insertable = false, updatable = false)`\
     - fetch data via a native query (that includes counting) into a `List<entity>`, and a `Pageable`\
     - use the fetched `List<entity>` and `Pageable` to create a `Page<entity>`

-----------------------------------------------------------------------------------------------------------------------

121. **[Offset Pagination - Trigger `SELECT COUNT` Subquery And Return `Page<projection>` That Maps Entities And The Total Number Of Records Via Projection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageEntityOffsetPaginationProjection)**

**Description:** This application fetches data as `Page<projection>` via Spring Boot offset pagination. The projection maps the entity and the total number of records. This information is fetched in a single database rountrip because the `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. Therefore, there will be a single database roundtrip instead of two (typically, there is one query needed for fetching the data and one for counting the total number of records). Use this approch only if the fetched data is not *read-only*. Otherwise, prefer `Page<dto>` as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageDtoOffsetPagination).

**Key points:**\
     - define a Spring projection that maps the entity and the total number of records\
     - write a repository that extends `PagingAndSortingRepository`\
     - fetch data via a JPQL query (that includes counting) into a `List<projection>`, and a `Pageable`\
     - use the fetched `List<projection>` and `Pageable` to create a `Page<projection>`

-----------------------------------------------------------------------------------------------------------------------

122. **[Offset Pagination - Trigger `COUNT(*) OVER` And Return `Page<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageDtoOffsetPaginationWF)**

**Description:** Typically, in offset pagination, there is one query needed for fetching the data and one for counting the total number of records. But, we can fetch this information in a single database rountrip via a `SELECT COUNT` subquery nested in the main `SELECT`. Even better, for databases vendors that support *Window Functions* there is a solution relying on `COUNT(*) OVER()` as in this application that uses this window function in a native query against MySQL 8. So, prefer this one instead of `SELECT COUNT` subquery. This application return a `Page<dto>`.

**Key points:**\
     - create a Spring projection (DTO) to contains getters only for the data that should be fetched\
     - write a repository that extends `PagingAndSortingRepository`\
     - fetch data via a native query (that includes counting) into a `List<dto>`, and a `Pageable`\
     - use the fetched `List<dto>` and `Pageable` to create a `Page<dto>`

**Example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootListDtoOffsetPaginationWF/offset%20pagination%20via%20window%20function.png)

-----------------------------------------------------------------------------------------------------------------------

123. **[How To Fetch `Slice<entity>`/`Slice<dto>` Via `fetchAll`/`fetchAllDto`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllViaFetchAll)**

**Story**: Spring Boot provides an *offset* based built-in paging mechanism that returns a `Page` or `Slice`. Each of these APIs represents a page of data and some metadata. The main difference is that `Page` contains the total number of records, while `Slice` can only tell if there is another page available. For `Page`, Spring Boot provides a `findAll()` method capable to take as arguments a `Pageable` and/or a `Specification`.  In order to populate a `Page` containing the total number of records, this method triggers an `SELECT COUNT` extra-query next to the query used to fetch the current page . This can be a performance penalty since the `SELECT COUNT` query is triggered every time we request a page. In order to avoid this extra-query, Spring Boot provides a more relaxed API, the `Slice` API. Using `Slice` instead of `Page` removes the need of this extra `SELECT COUNT` query and returns the page (records) and some metadata without the total number of records. So, while `Slice` doesn't know the total number of records, it still can tell if there is another page available after the current one or this is the last page. The problem is that `Slice` work fine for queries containing the SQL, `WHERE` clause (including those that uses the query builder mechanism built into Spring Data), but it **doesn't work** for `findAll()`. This method will still return a `Page` instead of `Slice` therefore the `SELECT COUNT` query is triggered for `Slice<T> findAll(...);`.

**Workaround:**
The trick is to  simply define a method named `fetchAll()` that uses JPQL and `Pageable` to return `Slice<entity>`, and a method named `fetchAllDto()` that uses JPQL and `Pageable` as well to return `Slice<dto>`. So, avoid naming the method `findAll()`.

**Usage example:**\
`public Slice<Author> fetchNextSlice(int page, int size) {`\
&nbsp;&nbsp;&nbsp;&nbsp;`return authorRepository.fetchAll(PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "age")));`\
 `}`
 
 `public Slice<AuthorDto> fetchNextSliceDto(int page, int size) {`\
&nbsp;&nbsp;&nbsp;&nbsp;`return authorRepository.fetchAllDto(PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "age")));`\
 `}`

-----------------------------------------------------------------------------------------------------------------------

124. **[How To Use Spring Projections(DTOs) And Inclusive Full Joins (MySQL)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaFullJoinsMySQL)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaFullJoinsMySQL/DTO%20via%20inclusive%20full%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and inclusive full joins written in native SQL (for MySQL).

**Key points:**\
     - define two entities (e.g., `Author` and `Book` in a lazy bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)\
     - write inclusive full joins queries using native SQL

-----------------------------------------------------------------------------------------------------------------------

125. **[How To Declare Immutable Entities And Store Them In Second Level Cache (e.g., `EhCache`)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootImmutableEntity)**

**Description:** This application is a sample of declaring an immutable entity. Moreover, the immutable entity will be stored in Second Level Cache via `EhCache` implementation.

**Key points of declaring an immutable entity:**\
     - annotate the entity with `@Immutable (org.hibernate.annotations.Immutable)`\
     - avoid any kind of associations\
     - set `hibernate.cache.use_reference_entries configuration` to `true`
     
----------------------------------------------------------------------------------------------------------------------

126. **[How To Programmatically Customize HikariCP Settings Via `DataSourceBuilder`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderProgHikariCPKickoff)**

**If you use the `spring-boot-starter-jdbc` or `spring-boot-starter-data-jpa` "starters", you automatically get a dependency to HikariCP**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up HikariCP via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purpose the application uses an `ExecutorService` for simulating concurrent users. Check the HickariCP report revealing the connection pool status.

**Key points:**\
     - write a `@Bean` that returns the `DataSource` programmatically

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHikariCPPropertiesKickoff/HikariCP%20trace%20log.png)

----------------------------------------------------------------------------------------------------------------------

127. **[How To Use Hibernate `@NaturalIdCache` For Skipping The Entity Identifier Retrieval](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNaturalIdCache)**

**Description:** This is a SpringBoot - MySQL application that maps a natural business key using Hibernate `@NaturalId`. This implementation allows us to use `@NaturalId` as it was provided by Spring. Moreover, this application uses Second Level Cache (`EhCache`) and `@NaturalIdCache` for skipping the entity identifier retrieval.

**Key points:**
- enable Second Level Cache (`EhCache`)
- annotate entity with `@NaturalIdCache` for caching natural ids
- optionally, annotate entity with `@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Book")` for caching entites as well

**Output sample (for MySQL with `IDENTITY` generator, `@NaturalIdCache` and `@Cache`):**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootNaturalIdCache/Hibernate%20NaturalIdCache%20first%20query.png)

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootNaturalIdCache/Hibernate%20NaturalIdCache%20second%20query.png)

----------------------------------------------------------------------------------------------------------------------

128. **[How To Calculate Non-Persistent Property via JPA `@PostLoad`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCalculatePropertyPostLoad)**
 
**Description:** This application is an example of calculating a non-persistent property of an entity based on the persistent entity attributes. In this case, we will use JPA, `@PostLoad`.

**Key points:**\
     - annotate the non-persistent field and property with `@Transient`\
     - define a method annotated with `@PostLoad` that calculates this non-persistent property based on the persistent entity attributes

----------------------------------------------------------------------------------------------------------------------

129. **[How To Calculate Entity Persistent Property Via Hibernate `@Generated`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCalculatePropertyGenerated)**
 
**Description:** This application is an example of calculating an entity persistent property at `INSERT` and/or `UPDATE ` time via Hibernate, `@Generated`. 

**Key points:**\
 **Calculate at `INSERT` time:**\
      - annotate the corresponding persistent field with `@Generated(value = GenerationTime.INSERT)`\
      - annotate the corresponding persistent field with `@Column(insertable = false)`\
 **Calculate at `INSERT` and `UPDATE` time:**\
      - annotate the corresponding persistent field with `@Generated(value = GenerationTime.ALWAYS)`\
      - annotate the corresponding persistent field with `@Column(insertable = false, updatable = false)`

**Further, apply:**\
 **Method 1:**\
     - if the database schema is generated via JPA annotations (not recommended) then use `columnDefinition` element of `@Column` to specify as an SQL query expression the formula for calculating the persistent property\
 **Method 2:**\
     - if the database schema is not generated via JPA annotations (recommended way) then add the formula as part of schema in `CREATE TABLE`
     
**Note:** In production, you should not rely on `columnDefinition`. You should disable `hibernate.ddl-auto` (by omitting it) or set it to `validate`, and add the SQL query expression in `CREATE TABLE` (in this application, check the `discount` column in `CREATE TABLE`, file `schema-sql.sql`). Nevertheless, not even `schema-sql.sql` is ok in production. The best way is to rely on Flyway or Liquibase.

----------------------------------------------------------------------------------------------------------------------

130. **[How To Calculate Non-Persistent Property via Hibernate `@Formula`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCalculatePropertyFormula)**
 
**Description:** This application is an example of calculating a non-persistent property of an entity based on the persistent entity attributes. In this case, we will use Hibernate, `@Formula`.

**Key points:**\
     - annotate the non-persistent property with `@Transient`\
     - annotate the corresponding non-persistent field with `@Formula`\
     - as the value of `@Formula` add the SQL query expression that calculates the non-persistent property based on the persistent entity attributes

----------------------------------------------------------------------------------------------------------------------

131. **[How To Add `created`, `createdBy`, `lastModified` And `lastModifiedBy` In Entities Via Hibernate](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTimestampGeneration)**
 
**Note:** The same thing can be obtained via Spring Data JPA auditing as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAudit).

**Description:** This application is an example of adding in an entity the fields, `created`, `createdBy`, `lastModified` and `lastModifiedBy` via Hibernate support. These fields will be automatically generated/populated.

**Key points:**\
     - write an abstract class (e.g., `BaseEntity`) annotated with `@MappedSuperclass`\
     - in this abstract class, define a field named `created` and annotate it with the built-in `@CreationTimestamp` annotation\
     - in this abstract class, define a field named `lastModified` and annotate it with the built-in `@UpdateTimestamp` annotation\
     - in this abstract class, define a field named `createdBy` and annotate it with the `@CreatedBy` annotation\
     - in this abstract class, define a field named `lastModifiedBy` and annotate it with the `@ModifiedBy` annotation\
     - implement the `@CreatedBy` annotation via `AnnotationValueGeneration`\
     - implement the `@ModifiedBy` annotation via `AnnotationValueGeneration`\
     - every entity that want to take advantage of `created`, `createdBy`, `lastModified` and `lastModifiedBy` will extend the `BaseEntity`\
     - store the date-time in UTC

----------------------------------------------------------------------------------------------------------------------

132. **[Hibernate Envers Auditing (`schema-mysql.sql`)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnversSchemaSql)**
 
**Description:** Auditing is useful for maintaining history records. This can later help us in tracking user activities. 
 
**Key points:**\
     - in `pom.xml` add the dependency `hibernate-envers` and JAXB API\
     - each entity that should be audited should be annotated with `@Audited`\
     - optionally, annotate entities with `@AuditTable` to rename the table used for auditing\
     - rely on `ValidityAuditStrategy` for fast database reads, but slower writes (slower than the default `DefaultAuditStrategy`)\
     - remove (disable) `spring.jpa.hibernate.ddl-auto` for avoiding schema generated from JPA annotations\
     - create `schema-mysql.sql` and provide the SQL statements needed by Hibernate Envers\
     - if the schema is not autommatically found, then point it via `spring.jpa.properties.org.hibernate.envers.default_catalog` for MySQL or `spring.jpa.properties.org.hibernate.envers.default_schema` for the rest

----------------------------------------------------------------------------------------------------------------------

133. **[How To Programmatically Setup Flyway And MySQL `DataSource`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayMySQLProg)**
 
**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate` and rely on Flyway or Liquibase.

**Description:** This application is a kickoff for setting Flyway and MySQL `DataSource` programmatically.

**Key points:**\
     - for Maven, in `pom.xml`, add the Flyway dependency\
     - remove (disable) `spring.jpa.hibernate.ddl-auto`\
     - configure `DataSource` and Flyway programmatically

----------------------------------------------------------------------------------------------------------------------

134. **[How To Migrate PostgreSQL Database Using Flyway - Use The Default Database `postgres` And Schema `public`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayPostgreSQLQuick)**

**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate` and rely on Flyway or Liquibase.

**Description:** This application is an example of migrating a PostgreSQL database via Flyway for the default database `postgres` and schema `public`. 

**Key points:**\
     - for Maven, in `pom.xml`, add the Flyway dependency\
     - remove (disable) `spring.jpa.hibernate.ddl-auto`\
     - in `application.properties`, set the JDBC URL as follows: `jdbc:postgresql://localhost:5432/postgres`\
     - each SQL file containing the schema update add it in `classpath:db/migration`\
     - each SQL file name it as `V1.1__Description.sql`, `V1.2__Description.sql`, ...

----------------------------------------------------------------------------------------------------------------------

135. **[How To Migrate Schema Using Flyway In PostgreSQL - Use The Default Database `postgres` And Schema Created Via `spring.flyway.schemas`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayPostgreSqlSchema)**

**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate` and rely on Flyway or Liquibase.

**Description:** This application is an example of migrating a schema (`bookstore`) created by Flyway via `spring.flyway.schemas` in the default `postgres` database. In this case, the entities should be annotated with `@Table(schema = "bookstore")`.

**Key points:**\
     - for Maven, in `pom.xml`, add the Flyway dependency\
     - remove (disable) `spring.jpa.hibernate.ddl-auto`\
     - in `application.properties`, set the JDBC URL as follows: `jdbc:postgresql://localhost:5432/postgres`\
     - in `application.properties`, add `spring.flyway.schemas=bookstore`, where `bookstore` is the schema that should be created by Flyway in the `postgres` database (feel free to add your own database name)\
     - each entity that should be stored in this database should be annotated with, `@Table(schema = "bookstore")`\
     - each SQL file containing the schema update add it in `classpath:db/migration`\
     - each SQL file name it as `V1.1__Description.sql`, `V1.2__Description.sql`, ...

----------------------------------------------------------------------------------------------------------------------

136. **[How To Programmatically Setup Flyway And PostgreSQL `DataSource`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayPostgreSQLProg)**
 
**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate` and rely on Flyway or Liquibase.

**Description:** This application is a kickoff for setting Flyway and PostgreSQL `DataSource` programmatically.

**Key points:**\
     - for Maven, in `pom.xml`, add the Flyway dependency\
     - remove (disable) `spring.jpa.hibernate.ddl-auto`\
     - configure `DataSource` and Flyway programmatically

----------------------------------------------------------------------------------------------------------------------

137. **[How To Auto-Create And Migrate Two Databases In MySQL Using Flyway](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayMySQLTwoDatabases)**

**Note:** or production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate` and rely on Flyway or Liquibase.

**Description:** This application is an example of auto-creating and migrating two databases in MySQL using Flyway. In addition, each data source uses its own HikariCP connection pool. In case of MySQL, where a database is the same thing with schema, we create two databases, `authorsdb` and `booksdb`.

**Key points:**\
     - for Maven, in `pom.xml`, add the Flyway dependency\
     - remove (disable) `spring.jpa.hibernate.ddl-auto`\
     - in `application.properties`, configure the JDBC URL for `booksdb` as `jdbc:mysql://localhost:3306/booksdb?createDatabaseIfNotExist=true` and for `authorsdb` as `jdbc:mysql://localhost:3306/authorsdb?createDatabaseIfNotExist=true`\
     - in `application.properties`, set `spring.flyway.enabled=false` to disable default behavior\
     - programmatically create two `DataSource`, one for `booksdb` and one for `authorsdb`\
     - programmatically create two `FlywayDataSource`, one for `booksdb` and one for `authorsdb`\
     - programmatically create two `EntityManagerFactory`, one for `booksdb` and one for `authorsdb`\
     - for `booksdb`, place the migration SQLs files in `db\migration\booksdb`\
     - for `authorsdb`, place the migration SQLs files in `db\migration\authorsdb`

----------------------------------------------------------------------------------------------------------------------

138. **[Hibernate `hi/lo` Algorithm And External Systems Issue](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHiLoIssue)**

**Description:** This is a Spring Boot sample that exemplifies how the `hi/lo` algorithm may cause issues when the database is used by external systems as well. Such systems can safely generate non-duplicated identifiers (e.g., for inserting new records) only if they know about the `hi/lo` presence and its internal work. So, better rely on `pooled` or `pooled-lo` which doesn't have such issues.

**Key points:**\
     - use the `SEQUENCE` generator type (e.g., in PostgreSQL)\
     - configure the `hi/lo` algorithm as in `Author.java` entity\
     - insert a few records via `hi/lo`\
     - insert a few records natively (this acts as an external system that relies on `NEXTVAL('hilo_sequence')` and is not aware of `hi/lo` presence and/or behavior)
     
**Output sample:** Running this application should result in the following error:\
`ERROR: duplicate key value violates unique constraint "author_pkey"`\
`Detail: Key (id)=(2) already exists.`

----------------------------------------------------------------------------------------------------------------------

139. **[How To Generate Sequences Of Identifiers Via Hibernate `pooled` Algorithm](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPooled)**

 **Note:** Rely on `pooled-lo` or `pooled` especially if, beside your application, external systems needs to insert rows in your tables. Don't rely on `hi/lo` since, in such cases, it may cause errors resulted from generating duplicated identifiers.
 
**Description:** This is a Spring Boot example of using the `pooled` algorithm. The `pooled` is an optimization of `hi/lo`. This algorithm fetched from the database the current sequence value as the top boundary identifier (the current sequence value is computed as the previous sequence value + `increment_size`). This way, the application will use in-memory identifiers generated between the previous top boundary exclusive (aka, lowest boundary) and the current top boundary inclusive.

**Key points:**\
     - use the `SEQUENCE` generator type (e.g., in PostgreSQL)\
     - configure the `pooled` algorithm as in `Author.java` entity\
     - insert a few records via `pooled`\
     - insert a few records natively (this acts as an external system that relies on `NEXTVAL('hilo_sequence')` and is not aware of `pooled` presence and/or behavior)
     
**Conclusion:** In contrast to the classical `hi/lo` algorithm, the Hibernate `pooled` algorithm doesn't cause issues to external systems that wants to interact with our tables. In other words, external systems can concurrently insert rows in the tables relying on `pooled` algorithm. Nevertheless, old versions of Hibernate can raise exceptions caused by `INSERT` statements triggered by external systems that uses the lowest boundary as identifier. This is a good reason to update to Hibernate latest versions (e.g., Hibernate 5.x), which have fixed this issue.

----------------------------------------------------------------------------------------------------------------------

140. **[How To Generate Sequences Of Identifiers Via Hibernate `pooled-lo` Algorithm](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPooledLo)**

 **Note:** Rely on `pooled-lo` or `pooled` especially if, beside your application, external systems needs to insert rows in your tables. Don't rely on `hi/lo` since, in such cases, it may cause errors resulted from generating duplicated identifiers.
 
**Description:** This is a Spring Boot example of using the `pooled-lo` algorithm. The `pooled-lo` is an optimization of `hi/lo` similar with `pooled`. Only that, the strategy of this algorithm fetches from the database the current sequence value and use it as the in-memory lowest boundary identifier. The number of in-memory generated identifiers is equal to `increment_size`.

**Key points:**\
     - use the `SEQUENCE` generator type (e.g., in PostgreSQL)\
     - configure the `pooled-lo` algorithm as in `Author.java` entity\
     - insert a few records via `pooled-lo`\
     - insert a few records natively (this acts as an external system that relies on `NEXTVAL('hilo_sequence')` and is not aware of `pooled-lo` presence and/or behavior)  

----------------------------------------------------------------------------------------------------------------------

141. **[Fetching Associations In Batches Via `@BatchSize`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLoadBatchAssociation)**

**Note:** Fetching associations in the same query with their parent can be done via `JOIN FETCH` or `@NamedEntityGraph`. But, Hibernate allows us to fetch associations in batches as well via `@BatchSize` annotation. This may be useful when `JOIN FETCH` and `@NamedEntityGraph` doesn't seem to help.
 
**Description:** This application fetches all `Author` entities via a `SELECT` query. Further, calling the `getBooks()` method of the first `Author` entity will trigger another `SELECT` query that initializes the association of the first three `Author` entities returned by the previous `SELECT` query. This is the effect of `@BatchSize`.

**Key points:**\
     - `Author` and `Book` are in a lazy relationship (e.g., `@OneToMany` bidirectional relationship)\
     - `Author` association is annotated with `@BatchSize(size = 3)`

----------------------------------------------------------------------------------------------------------------------

142. **[How To Use Entity Graphs (`@NamedEntityGraph`) In Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedEntityGraph)**

**Note:** In a nutshell, *entity graphs* (aka, *fetch plans*) is a feature introduced in JPA 2.1 that help us to improve the performance of loading entities. Mainly, we specify the entity’s related associations and basic fields that should be loaded in a single `SELECT` statement. We can define multiple *entity graphs* for the same entity and *chain* any number of entities and even use *sub-graphs* to create complex *fetch plans*. To override the current `FetchType` semantics there are properties that can be set:

*Fetch Graph* (default), `javax.persistence.fetchgraph`\
The attributes present in `attributeNodes` are treated as `FetchType.EAGER`. The remaining attributes are treated as `FetchType.LAZY` regardless of the default/explicit `FetchType`.

*Load Graph*, `javax.persistence.loadgraph`\
The attributes present in `attributeNodes` are treated as `FetchType.EAGER`. The remaining attributes are treated according to their specified or default `FetchType`.

**Nevertheless, the JPA specs doesn't apply in Hibernate for the basic (`@Basic`) attributes.**. More details [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedEntityGraphBasicAttrs).

**Description:** This is a sample application of using *entity graphs* in Spring Boot.

**Key points:**\
     - define two entities, `Author` and `Book`, involved in a lazy bidirectional `@OneToMany` relationship\
     - in `Author` entity use the `@NamedEntityGraph` to define the *entity graph* (e.g., load in a single `SELECT` the authors and the associatated books)\
     - in `AuthorRepository` rely on Spring `@EntityGraph` annotation to indicate the *entity graph* defined at the previous step

----------------------------------------------------------------------------------------------------------------------

143. **[How To Use Entity Sub-graphs In Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedSubgraph)**

**Note:** In a nutshell, *entity graphs* (aka, *fetch plans*) is a feature introduced in JPA 2.1 that help us to improve the performance of loading entities. Mainly, we specify the entity’s related associations and basic fields that should be loaded in a single `SELECT` statement. We can define multiple *entity graphs* for the same entity and *chain* any number of entities and even use *sub-graphs* to create complex *fetch plans*. To override the current `FetchType` semantics there are properties that can be set:

*Fetch Graph* (default), `javax.persistence.fetchgraph`\
The attributes present in `attributeNodes` are treated as `FetchType.EAGER`. The remaining attributes are treated as `FetchType.LAZY` regardless of the default/explicit `FetchType`.

*Load Graph*, `javax.persistence.loadgraph`\
The attributes present in `attributeNodes` are treated as `FetchType.EAGER`. The remaining attributes are treated according to their specified or default `FetchType`.

**Nevertheless, the JPA specs doesn't apply in Hibernate for the basic (`@Basic`) attributes.**. More details [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedEntityGraphBasicAttrs).

**Description:** This is a sample application of using *entity sub-graphs* in Spring Boot. There is one example based on `@NamedSubgraph` and one based on the dot notation (.) in an ad-hoc *entity graph*.

**Key points:**\
     - define three entities, `Author`, `Book` and `Publisher` (`Author` and `Book` are involved in a lazy bidirectional `@OneToMany` relationship, `Book` and `Publisher` are also involved in a lazy bidirectional `@OneToMany` relationship; between `Author` and `Publisher` there is no relationship)
     
**Using `@NamedSubgraph`**\
     - in `Author` entity define an *entity graph* via  `@NamedEntityGraph`; load the authors and the associatated books and use `@NamedSubgraph` to define a *sub-graph* for loading the publishers associated with these books\
     - in `AuthorRepository` rely on Spring `@EntityGraph` annotation to indicate the *entity graph* defined at the previous step
     
**Using the dot notation (.)**\
     - in `PublisherRepository` define an ad-hoc *entity graph* that fetches all publishers with associated books, and further, the authors associated with these books (e.g., `@EntityGraph(attributePaths = {"books.author"})`.
     
----------------------------------------------------------------------------------------------------------------------

144. **[How To Define Ad-Hoc Entity Graphs In Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEntityGraphAttributePaths)**

**Note:** In a nutshell, *entity graphs* (aka, *fetch plans*) is a feature introduced in JPA 2.1 that help us to improve the performance of loading entities. Mainly, we specify the entity’s related associations and basic fields that should be loaded in a single `SELECT` statement. We can define multiple *entity graphs* for the same entity and *chain* any number of entities and even use *sub-graphs* to create complex *fetch plans*. To override the current `FetchType` semantics there are properties that can be set:

*Fetch Graph* (default), `javax.persistence.fetchgraph`\
The attributes present in `attributeNodes` are treated as `FetchType.EAGER`. The remaining attributes are treated as `FetchType.LAZY` regardless of the default/explicit `FetchType`.

*Load Graph*, `javax.persistence.loadgraph`\
The attributes present in `attributeNodes` are treated as `FetchType.EAGER`. The remaining attributes are treated according to their specified or default `FetchType`.

**Nevertheless, the JPA specs doesn't apply in Hibernate for the basic (`@Basic`) attributes.**. More details [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedEntityGraphBasicAttrs).

**Description:** This is a sample application of defining ad-hoc *entity graphs* in Spring Boot.

**Key points:**\
     - define two entities, `Author` and `Book`, involved in a lazy bidirectional `@OneToMany` relationship\
     - the *entity graph* should load in a single `SELECT` the authors and the associatated books\
     - in `AuthorRepository` rely on Spring `@EntityGraph(attributePaths = {"books"})` annotation to indicate the ad-hoc *entity graph*

----------------------------------------------------------------------------------------------------------------------

145. **[How To Use Entity Graphs For `@Basic` Attributes In Hibernate And Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedEntityGraphBasicAttrs)**

**Note:** In a nutshell, *entity graphs* (aka, *fetch plans*) is a feature introduced in JPA 2.1 that help us to improve the performance of loading entities. Mainly, we specify the entity’s related associations and basic fields that should be loaded in a single `SELECT` statement. We can define multiple *entity graphs* for the same entity and *chain* any number of entities and even use *sub-graphs* to create complex *fetch plans*. To override the current `FetchType` semantics there are properties that can be set:

*Fetch Graph* (default), `javax.persistence.fetchgraph`\
The attributes present in `attributeNodes` are treated as `FetchType.EAGER`. The remaining attributes are treated as `FetchType.LAZY` regardless of the default/explicit `FetchType`.

*Load Graph*, `javax.persistence.loadgraph`\
The attributes present in `attributeNodes` are treated as `FetchType.EAGER`. The remaining attributes are treated according to their specified or default `FetchType`.

**Nevertheless, the JPA specs doesn't apply in Hibernate for the basic (`@Basic`) attributes.** In other words, by default, attributes are annotated with `@Basic` which rely on the default fetch policy. The default fetch policy is `FetchType.EAGER`. These attributes are also loaded in case of *fetch graph* even if they are not explicitly specified via `@NamedAttributeNode`. Annotating the basic attributes that should not be fetched with `@Basic(fetch = FetchType.LAZY)` it is not enough. Both, *fetch graph* and *load graph* will ignore these settings as long as we don't add *bytecode enhancement* as well.

**The main drawback consists of the fact the these basic attributes are fetched `LAZY` by all other queries (e.g., `findById()`) not only by the queries using the entity graph, and most probably, you will not want this behavior.**

**Description:** This is a sample application of using *entity graphs* with `@Basic` attributes in Spring Boot.

**Key points:**\
     - define two entities, `Author` and `Book`, involved in a lazy bidirectional `@OneToMany` relationship\
     - in `Author` entity use the `@NamedEntityGraph` to define the *entity graph* (e.g., load the authors names (only the `name` basic attribute; ignore the rest) and the associatated books)\
     - add *bytecode enhancement*\
     - annotate the basic attributes that should be ignored by the *entity graph* with `@Basic(fetch = FetchType.LAZY)`\
     - in `AuthorRepository` rely on Spring `@EntityGraph` annotation to indicate the *entity graph* defined at the previous step

----------------------------------------------------------------------------------------------------------------------

146. **[How To Implement Soft Deletes Via `SoftDeleteRepository` In Spring Boot Application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSoftDeletesSpringStyle)**

**Note:** Spring Data built-in support for soft deletes is discussed in [DATAJPA-307](https://jira.spring.io/browse/DATAJPA-307).

**Description:** This application is an example of implementing soft deletes in Spring Data style via a repository named, `SoftDeleteRepository`. 

**Key points:**\
     - define an abstract class, `BaseEntity`, annotated with `@MappedSuperclass`\
     - in `BaseEntity` define a flag-field named `deleted` (default this field to `false` or in other words, not deleted)\
     - every entity that wants to take advantage of soft deletes should extend the `BaseEntity` classs\
     - write a `@NoRepositoryBean` named `SoftDeleteRepository` and extend `JpaRepository`\
     - override and implement the needed methods that provide the logic for soft deletes (check out the source code)\     
     - repositories of entities should extend `SoftDeleteRepository`
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSoftDeletes/soft%20deletes.png)

----------------------------------------------------------------------------------------------------------------------

147. **[How To Implement Concurrent Table Based Queue Via `SKIP_LOCKED` In MySQL 8](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootMySqlSkipLocked)**

**Description:** This application is an example of how to implement concurrent table based queue via `SKIP_LOCKED` in MySQL 8. `SKIP_LOCKED` can skip over locks achieved by other concurrent transactions, therefore is a great choice for implementing job queues. In this application, we run two concurrent transactions. The first transaction will lock the records with ids 1, 2 and 3. The second transaction will skip the records with ids 1, 2 and 3 and will lock the records with ids 4, 5 and 6.

**Key points:**\
     - define an entity that acts as a job queue (e.g., see the `Book` entity)\
     - in `BookRepository` setup `@Lock(LockModeType.PESSIMISTIC_WRITE)`\
     - in `BookRepository` use `@QueryHint` to setup `javax.persistence.lock.timeout` to `SKIP_LOCKED`\
     - rely on `org.hibernate.dialect.MySQL8Dialect` dialect\
     - run two concurrent transactions to see the effect of `SKIP_LOCKED`

----------------------------------------------------------------------------------------------------------------------

148. **[How To Implement Concurrent Table Based Queue Via `SKIP_LOCKED` In PostgreSQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPostgresSqlSkipLocked)**

**Description:** This application is an example of how to implement concurrent table based queue via `SKIP_LOCKED` in PostgreSQL. `SKIP_LOCKED` can skip over locks achieved by other concurrent transactions, therefore is a great choice for implementing job queues. In this application, we run two concurrent transactions. The first transaction will lock the records with ids 1, 2 and 3. The second transaction will skip the records with ids 1, 2 and 3 and will lock the records with ids 4, 5 and 6.

**Key points:**\
     - define an entity that acts as a job queue (e.g., see the `Book` entity)\
     - in `BookRepository` setup `@Lock(LockModeType.PESSIMISTIC_WRITE)`\
     - in `BookRepository` use `@QueryHint` to setup `javax.persistence.lock.timeout` to `SKIP_LOCKED`\
     - rely on `org.hibernate.dialect.PostgreSQL95Dialect` dialect\
     - run two concurrent transactions to see the effect of `SKIP_LOCKED`

----------------------------------------------------------------------------------------------------------------------

149. **[JPA Inheritance - Join Table](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinTableInheritance)**

**Description:** This application is a sample of JPA Join Table inheritance strategy (`JOINED`)

**Key points:**\
     - this inheritance strategy can be employed via `@Inheritance(strategy=InheritanceType.JOINED)`\
     - all the classes in an inheritance hierarchy (a.k.a., subclasses) are represented via individual tables\
     - by default, subclass-tables contains a primary key column that acts as a foreign key  as well - this foreign key references the *base class* table primary key\
     - customizing this foreign key can be done by annotating the subclasses with `@PrimaryKeyJoinColumn`  

----------------------------------------------------------------------------------------------------------------------

150. **[JPA Inheritance - Table-per-class](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTablePerTableInheritance)**

**Description:** This application is a sample of JPA Table-per-class inheritance strategy (`TABLE_PER_CLASS`)

**Key points:**\
     - this inheritance strategy doesn't allow the usage of the `IDENTITY` generator\
     - this inheritance strategy can be employed via `@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)`\
     - all the classes in an inheritance hierarchy (a.k.a., subclasses) are represented via individual tables\
     - each subclass-table stores the columns inherited from the superclass-table (*base class*)

----------------------------------------------------------------------------------------------------------------------

151. **[JPA Inheritance - `@MappedSuperclas`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootMappedSuperclass)**

**Description:** This application is a sample of using the JPA `@MappedSuperclass`.

**Key points:**\
     - the *base class* is not an entity, it can be `abstract`, and is annotated with `@MappedSuperclass`\
     - subclasses of the *base class* are mapped in tables that contains columns for the inherited attributes and for their own attibutes\
     - when the *base class* doens't need to be an entity, the `@MappedSuperclass` is the proper alternative to the JPA table-per-class inheritance strategy

----------------------------------------------------------------------------------------------------------------------

152. **[How To Avoid Lazy Initialization Issues Caused By Disabling Open Session In View Via Hibernate5Module](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJacksonHibernate5Module)**
 
**Note:** [Hibernate5Module](https://github.com/FasterXML/jackson-datatype-hibernate) is an *add-on module for Jackson JSON processor which handles Hibernate datatypes; and specifically aspects of lazy-loading*.
 
**Description:** By default, in Spring Boot, the Open Session in View anti-pattern is enabled. Now, imagine a lazy relationship (e.g., `@OneToMany`) between two entities, `Author` and `Book` (an author has associated more books). Next, a REST controller endpoint fetches an `Author` without the associated `Book`. But, the View (more precisely, Jackson), forces the lazy loading of the associated `Book` as well. Since OSIV will supply the already opened `Session`, the `Proxy` initializations take place successfully. 

Of course, the correct decision is to disable OSIV by setting it to `false`, but this will not stop Jackson to try to force the lazy initialization of the associated `Book` entities. Running the code again will result in an exception of type: *Could not write JSON: failed to lazily initialize a collection of role: com.bookstore.entity.Author.books, could not initialize proxy - no Session; nested exception is com.fasterxml.jackson.databind.JsonMappingException: failed to lazily initialize a collection of role: com.bookstore.entity.Author.books, could not initialize proxy - no Session*. 

Well, among the Hibernate5Module features we have support for dealing with this aspect of lazy loading and eliminate this exception. Even if OSIV will continue to be enabled (not recommended), Jackson will not use the `Session` opened via OSIV.

**Key points:**\
     - add the Hibernate5Module dependency in `pom.xml`\
     - add a `@Bean` that returns an instance of `Hibernate5Module`\
     - annotate the `Author` bean with `@JsonInclude(Include.NON_EMPTY)` to exclude `null` or what is considered empty from the returned JSON
     
**Note:** The presence of Hibernate5Module instructs Jackson to initialize the lazy associations with default values (e.g., a lazy associated collection will be initialized with `null`). Hibernate5Module doesn't work for lazy loded attributes. For such case consider [this](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingJacksonSerialization) item.

----------------------------------------------------------------------------------------------------------------------

153. **[How To View Binding Params Via `profileSQL=true` In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLogBindingParametersMySQL)**

**Description:** View the prepared statement binding parameters via `profileSQL=true` in MySQL.

**Key points:**\
     - in `application.properties` append `logger=Slf4JLogger&profileSQL=true` to the JDBC URL (e.g., `jdbc:mysql://localhost:3306/bookstoredb?createDatabaseIfNotExist=true&logger=Slf4JLogger&profileSQL=true`)
     
**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLogBindingParametersMySQL/display%20binding%20via%20profileSQL%3Dtrue.png)

----------------------------------------------------------------------------------------------------------------------

154. **[How To Shuffle Small Result Sets](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOrderByRandom)**
 
**Description:** This application is an example of shuffling small results sets. **DO NOT USE** this technique for large results sets, since is extremely expensive.

**Key points:**\
     - write a JPQL `SELECT` query and append to it `ORDER BY RAND()`\
     - each RDBMS support a function similar to `RAND()` (e.g., in PostgreSQL is `random()`)

----------------------------------------------------------------------------------------------------------------------

155. **[The Best Way To Remove Parent And Child Entities](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCascadeChildRemoval)**

**Description:** Commonly, deleting a parent and the associated children via `CascadeType.REMOVE`  and/or `orphanRemoval=true` involved several SQL statements (e.g., each child is deleted in a dedicated `DELETE` statement). This is far from being efficient, therefore other approaches should be employed. Consider `Author` and `Book` in a `OneToMany` relationship.

**Best way to delete when:**\
     - One `Author` is in Persistent Context, no `Book`\
     - More `Author` are in the Persistent Context, no `Book`\
     - One `Author` and the associated `Book` are in Persistent Context\
     - No `Author` or `Book` is in Persistent Context
     
**Note:** The most efficient way to delete all entities can be done via the built-in `deleteAllInBatch()`. 

----------------------------------------------------------------------------------------------------------------------

156. **[How To *Bulk* Updates](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBulkUpdates)**

**Description:** *Bulk* operations (updates and deletes) are fastest than batching, can benefit from indexing, but they have two main drawbacks:

- *bulk* updates/deletes may leave the Persistent Context in an outdated state (prevent this by flushing the Persistent Context before update/delete and close/clear it after the update/delete to avoid issues created by potentially unflushed or outdated entities)
- *bulk* updates/deletes don't benefit of application-level optimistic locking mechanisms, therefore the *lost updates* not prevented (it is advisable to signal these updates by explicitly incrementing `version` (if any is present)).

This application provides examples of *bulk* updates for `Author` and `Book` entities (between `Author` and `Book` there is a bidirectional lazy `@OneToMany` relationship). Both, `Author` and `Book`, has a `version` field.

----------------------------------------------------------------------------------------------------------------------

157. **[Why You Should Avoid Unidirectional `@OneToMany` And Prefer Bidirectional `@OneToMany` Relationship](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOneToManyUnidirectinal)**

**Description:** As a rule of thumb, unidirectional `@OneToMany` association is less efficient than the bidirectional `@OneToMany` or the unidirectional `@ManyToOne` associations. This application is a sample that exposes the DML statements generated for reads, writes and removal operations when the unidirectional `@OneToMany` mapping is used.

**Key points:**\
     - regular unidirectional `@OneToMany` is less efficient than bidirectional `@OneToMany` relationship\
     - using `@OrderColumn` come with some optimizations for removal operations but is still less efficient than bidirectional `@OneToMany` relationship\
     - using `@JoinColumn` eliminates the junction table but is still less efficient than bidirectional `@OneToMany` relationship\
     - using `Set` instead of `List` or bidirectional `@OneToMany` with `@JoinColumn` relationship (e.g., `@ManyToOne @JoinColumn(name = "author_id", updatable = false, insertable = false)`) still performs worse than bidirectional `@OneToMany` relationship

----------------------------------------------------------------------------------------------------------------------

158. **[How To Use Subqeries in JPQL `WHERE`/`HAVING` Clause](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSubqueryInWhere)**
 
**Description:** This application is an example of using subqueries in JPQL `WHERE` clause (you can easily use it in `HAVING` clause as well).

**Key points:**\
Keep in mind that subqueries and joins queries may not be semantically equivalent. Joins may returns duplicates that can be removed via `DISTINCT`. 

Even if the Execution Plan is specific to the database, historically speaking joins are faster than subqueries among different databases, but this is not a rule (e.g., the amount of data may significantly influence the results). Of course, do not conclude that subqueries are just a replacement for joins that doesn't deserve attention. Tuning subqueries can increases their performance as well, but this is an SQL wide topic. So, benchmark! Benchmark! Benchmark!

**As a rule of thumb, prefer subqueries only if you cannot use joins, or if you can prove that they are faster than the alternative joins.**

----------------------------------------------------------------------------------------------------------------------

159. **[How To Execute SQL Functions In `WHERE` Part Of JPQL Query And JPA 2.1](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJpqlFunctionsParams)**

Note: Using SQL functions in `SELECT` part (not in `WHERE` part) can be done as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJpqlFunctionsParams).

**Description:** Starting with JPA 2.1, a JPQL query can call SQL functions in the `WHERE` part via `function()`. This application is an example of calling the MySQL, `concat_ws` function, but user defined (custom) functions can be used as well.

**Key points:**\
     - use JPA 2.1, `function()` 

----------------------------------------------------------------------------------------------------------------------

160. **[Calling Stored Procedure That Returns A Value](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCallStoredProcedureReturnValue)**
 
**Description:** This application is an example of calling a MySQL stored procedure that returns a value (e.g., an `Integer`).

**Key points:**\
     - rely on `@NamedStoredProcedureQuery` to shape the stored procedure in the entity\
     - rely on `@Procedure` in repository

----------------------------------------------------------------------------------------------------------------------

161. **[Calling Stored Procedure That Returns A Result Set (Entity And DTO)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCallStoredProcedureReturnResultSet)**
 
**Description:** This application is an example of calling a MySQL stored procedure that returns a result set. The application fetches entities (e.g., `List<Author>`) and DTO (e.g., `List<AuthorDto>`).

**Key points:**\
     - rely on `EntiyManager` since Spring Data `@Procedure` will not work

----------------------------------------------------------------------------------------------------------------------

162. **[Calling Stored Procedure That Returns A Result Set Via Native Query](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCallStoredProcedureNativeCall)**
 
**Description:** This application is an example of calling a MySQL stored procedure that returns a result set (entity or DTO) via a native query.
 
**Key points:**\
     - rely on a native call as `@Query(value = "{CALL FETCH_AUTHOR_BY_GENRE (:p_genre)}", nativeQuery = true)`
