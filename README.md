[![Tweet](https://img.shields.io/twitter/url/http/shields.io.svg?style=social)](https://twitter.com/intent/tweet?text=Collection%20of%20best%20practices%20for%20Java%20persistence%20performance%20in%20Spring%20Boot%20applications&url=https://github.com/AnghelLeonard/Hibernate-SpringBoot&hashtags=springdata,hibernate,jpa,springboot)

# Best Performance Practices Hibernate 5/6 & Spring Boot 2

<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

**Hibernate & Spring Boot Samples**

1. **[How To Store UTC Timezone In MySQL](https://github.com/AnghelLeonard/Hibernate/tree/master/HibernateSpringBootUTCTimezone)**

**Description:** This application is a sample of how to store date, time, and timestamps in UTC time zone. The second setting, `useLegacyDatetimeCode` is needed only for MySQL. Otherwise, set only `hibernate.jdbc.time_zone`.

**Key points:**
- `spring.jpa.properties.hibernate.jdbc.time_zone=UTC`
- `spring.datasource.url=jdbc:mysql://localhost:3306/screenshotdb?useLegacyDatetimeCode=false`
     
-----------------------------------------------------------------------------------------------------------------------    

2. **[View Binding/Extracted Params Via Log4J 2](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLog4j2ViewBindingParameters)**

**Description:** View the prepared statement binding/extracted parameters via Log4J 2 logger setting.

**Key points:**
- for Maven, in `pom.xml`, exclude Spring Boot's Default Logging
- for Maven, in `pom.xml`, Add Log4j 2 Dependency
- in `log4j2.xml` add, `<Logger name="org.hibernate.type.descriptor.sql" level="trace"/>`
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLog4j2ViewBindingParameters/log4j2%20display%20binding%20and%20extracted%20parameters.png)

-----------------------------------------------------------------------------------------------------------------------    

3. **[How To View Query Details Via DataSource-Proxy Library](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceProxy)**

**Description:** View the query details (query type, binding parameters, batch size, execution time, etc) via **[DataSource-Proxy](https://github.com/ttddyy/datasource-proxy)**

**Key points:**
- for Maven, add in `pom.xml` the `datasource-proxy` dependency
- create an bean post processor to intercept the `DataSource` bean
- wrap the `DataSource` bean via `ProxyFactory` and an implementation of `MethodInterceptor`

**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceProxy/query%20details%20via%20datasource-proxy.png)

-----------------------------------------------------------------------------------------------------------------------    

4. **[Batch Inserts via `saveAll(Iterable<S> entities)` in MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsJpaRepository)**

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

5. **[Batch Inserts Via EntityManager (MySQL)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsEntityManager)**

**Description:** This application is a sample of batching inserts via `EntityManager` in MySQL. This way you can easily control the `flush()` and `clear()` cycles of the Persistence Context (1st Level Cache) inside the current transaction. This is not possible via Spring Boot, `saveAll(Iterable<S> entities)`, since this method executes a single flush per transaction. Another advantage is that you can call `persist()` instead of `merge()` - this is used behind the scene by the SpringBoot `saveAll(Iterable<S> entities)` and `save(S entity)`.

If you want to execute a batch per transaction (recommended) then check this [example](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsEntityManagerBatchPerTransaction).

**Key points:**
- in `application.properties` set `spring.jpa.properties.hibernate.jdbc.batch_size`
- in `application.properties` set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)
- in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)
- in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)
- in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))
- in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts
- in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause insert batching to be disabled
- in your DAO layer, flush and clear the Persistence Context from time to time (e.g. for each batch); this way you avoid to "overwhelm" the Persistence Context
- if is not needed, then ensure that Second Level Cache is disabled via `spring.jpa.properties.hibernate.cache.use_second_level_cache=false`

**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsEntityManager/batch%20inserts%20via%20EntityManager.png)

-----------------------------------------------------------------------------------------------------------------------    

6. **[How To Batch Inserts Via JpaContext/EntityManager In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsEntityManagerViaJpaContext)**

**Description:** Batch inserts via `JpaContext/EntityManager` in MySQL.

**Key points:**
- in `application.properties` set `spring.jpa.properties.hibernate.jdbc.batch_size`
- in `application.properties` set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)
- in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)
- in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)
- in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))
- in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts
- in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause insert batching to be disabled
- the `EntityManager` is obtain per entity type via, `JpaContext#getEntityManagerByManagedType(Class<?> entity)`
- in DAO, flush and clear the Persistence Context from time to time; this way you avoid to "overwhelm" the Persistence Context
- if is not needed, then ensure that Second Level Cache is disabled via `spring.jpa.properties.hibernate.cache.use_second_level_cache=false`

**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsEntityManagerViaJpaContext/batch%20inserts%20via%20JpaContext.png)

-----------------------------------------------------------------------------------------------------------------------    

7. **[How To Exploit Session-Level Batching (Hibernate 5.2 Or Higher) In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsViaSession)**

**Description:** Batch inserts via Hibernate session-level batching (Hibernate 5.2 or higher) in MySQL.

**Key points:**
- in `application.properties` set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)
- in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)
- in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)
- in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))
- in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts
- in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause insert batching to be disabled
- the Hibernate `Session` is obtained by un-wrapping it via `EntityManager#unwrap(Session.class)`
- the batching size is set via `Session#setJdbcBatchSize(Integer size)` and get via `Session#getJdbcBatchSize()`
- in DAO, flush and clear the Persistence Context from time to time; this way you avoid to "overwhelm" the Persistence Context
- if is not needed, then ensure that Second Level Cache is disabled via `spring.jpa.properties.hibernate.cache.use_second_level_cache=false`
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsViaSession/batch%20inserts%20via%20Session.png)

-----------------------------------------------------------------------------------------------------------------------    

8. **[Direct Fetching Via Spring Data `findById()`, JPA `EntityManager` And Hibernate `Session`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDirectFetching)**

**Description:** Direct fetching via Spring Data, `EntityManager` and Hibernate `Session` examples.

**Key points:**
- direct fetching via Spring Data uses `findById()`
- direct fetching via JPA `EntityManager` uses `find()`
- direct fetching via Hibernate `Session` uses `get()`

-----------------------------------------------------------------------------------------------------------------------    

9. **[DTO Via Spring Data Projections](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)**

**Note:** You may also like to read the recipe, ["How To Enrich DTOs With Virtual Properties Via Spring Projections"](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaProjectionsAndVirtualProperties)

**Description:** Fetch only the needed data from the database via Spring Data Projections (DTO).

**Key points:**
- write an interface (projection) containing getters only for the columns that should be fetched from the database
- write the proper query returning a `List<projection>`
- if it is applicable, limit the number of returned rows (e.g., via `LIMIT`) 
- in this example, we can use query builder mechanism built into Spring Data repository infrastructure
     
**Note:** Using projections is not limited to use query builder mechanism built into Spring Data repository infrastructure. We can fetch projections via JPQL or native queries as well. For example, in this [application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjectionsAndJpql) we use a JPQL.
     
**Output example (select first 2 rows; select only "name" and "age"):**
<a href="#"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaProjections/DTOs%20via%20Spring%20projections.png" align="center" height="251" width="698" ></a>

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

10. **[How To Use Hibernate Attribute Lazy Loading](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootAttributeLazyLoadingBasic)**
  
**Description:** By default, the attributes of an entity are loaded eagerly (all at once). But, we can load them **lazy** as well. This is useful for column types that store large amounts of data: `CLOB`, `BLOB`, `VARBINARY`, etc or *details* that should be loaded on demand. In this application, we have an entity named `Author`. Its properties are: `id`, `name`, `genre`, `avatar` and `age`. And, we want to load the `avatar` lazy. So, the `avatar` should be loaded on demand.

**Key points:**
- in `pom.xml`, activate Hibernate *bytecode enhancement* (e.g. use Maven *bytecode enhancement plugin*)
- in entity, annotate the attributes that should be loaded lazy with `@Basic(fetch = FetchType.LAZY)`
- in `application.properties`, disable Open Session in View    

**Check as well:**\
     - [Default Values For Lazy Loaded Attributes](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingDefaultValues)\
     - [Attribute Lazy Loading And Jackson Serialization](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingJacksonSerialization)
     
-----------------------------------------------------------------------------------------------------------------------    

11. **[How To Populate a Child-Side Parent Association via Proxy](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPopulatingChildViaProxy)**

**Description:** A Hibernate proxy can be useful when a child entity can be persisted with a reference to its parent (`@ManyToOne` or `@OneToOne` association). In such cases, fetching the parent entity from the database (execute the `SELECT` statement) is a performance penalty and a pointless action, because Hibernate can set the underlying foreign key value for an uninitialized proxy.

**Key points:**
- rely on `EntityManager#getReference()`
- in Spring, use `JpaRepository#getOne()` -> used in this example
- in Hibernate, use `load()`
- assume two entities, `Author` and `Book`, involved in a unidirectional `@ManyToOne` association (`Author` is the parent-side)
- we fetch the author via a proxy (this will not trigger a `SELECT`), we create a new book, we set the proxy as the author for this book and we save the book (this will trigger an `INSERT` in the `book` table)
     
**Output example:**
- the console output will reveal that only an `INSERT` is triggered, and no `SELECT`
     
-----------------------------------------------------------------------------------------------------------------------    

12. **[How To Quickly Reproduce The N+1 Performance Issue](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSimulateNPlus1)**

**Description:** The N+1 is an issue of lazy fetching (but, eager is not exempt). This application reproduce the N+1 behavior.

**Key points:**
- define two entities, `Author` and `Book` in a lazy bidirectional `@OneToMany` association
- fetch all `Book` lazy, so without `Author` (results in 1 query)
- loop the fetched `Book` collection and for each entry fetch the corresponding `Author` (results N queries)
- or, fetch all `Author` lazy, so without `Book` (results in 1 query)
- loop the fetched `Author` collection and for each entry fetch the corresponding `Book` (results N queries)
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSimulateNPlus1/simulate%20N%2B1.png)

-----------------------------------------------------------------------------------------------------------------------    

13. **[Optimize `SELECT DISTINCT` Via Hibernate `HINT_PASS_DISTINCT_THROUGH` Hint](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHintPassDistinctThrough)**

**Description:** Starting with Hibernate 5.2.2, we can optimize JPQL (HQL) query entites of type `SELECT DISTINCT` via `HINT_PASS_DISTINCT_THROUGH` hint. Keep in mind that this hint is useful only for JPQL (HQL) JOIN FETCH-ing queries. Is not useful for scalar queries (e.g., `List<Integer>`), DTO or [HHH-13280](https://hibernate.atlassian.net/browse/HHH-13280). In such cases, the `DISTINCT` JPQL keyword is needed to be passed to the underlying SQL query. This will instruct the database to remove duplicates from the result set. 

**Key points:**
- use `@QueryHints(value = @QueryHint(name = HINT_PASS_DISTINCT_THROUGH, value = "false"))`
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHintPassDistinctThrough/HINT_PASS_DISTINCT_THROUGH.png)

-----------------------------------------------------------------------------------------------------------------------    

14. **[How To Enable Dirty Tracking In A Spring Boot Application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnableDirtyTracking)**

**Note:** The Hibernate *Dirty Checking* mechanism is responsible to identify the entitites modifications at flush-time and to trigger the corresponding `UPDATE` statements in our behalf.

**Description:** Prior to Hibernate version 5, the *Dirty Checking* mechanism relies on Java Reflection API for checking every property of every managed entity. Starting with Hibernate version 5, the *Dirty Checking* mechanism can rely on the *Dirty Tracking* mechanism (which is the capability of an entity to track its own attributes changes) which requires Hibernate *Bytecode Enhancement* to be present in the application. The *Dirty Tracking* mechanism sustain a better performance, especially when you have a relatively large number of entitites. 

For *Dirty Tracking*, during *Bytecode Enhancement* process, the entity classes bytecode is instrumented by Hibernate by adding a *tracker*, `$$_hibernate_tracker`. At flush time, Hibernate will use this *tracker* to discover the entities changes (each entity *tracker* will report the changes). This is better than checking every property of every managed entity.

Commonly (by default), the instrumentation takes place at build-time, but it can be configured to take place at runtime or deploy-time as well. It is preferable to take place at build-time for avoiding an overhead in the runtime.

Adding *Bytecode Enhancement* and enabling *Dirty Tracking* can be done via a plugin added via Maven or Gradle (Ant can be used as well). We use Maven, therefore we add it in `pom.xml`.

**Key points:**
- Hibernate come with *Bytecode Enhancement* plugins for Maven, Gradle (Ant can be used as well)
- for Maven, add the *Bytecode Enhancement* plugin in the `pom.xml` file
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootEnableDirtyTracking/Enable%20dirty%20tracking.png)

The *Bytecode Enhancement* effect can be seen on `Author.class` [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootEnableDirtyTracking/Bytecode%20Enhancement%20Author.class/Author.java). Notice how the bytecode was instrumented with `$$_hibernate_tracker`.

-----------------------------------------------------------------------------------------------------------------------    

15. **[Use Java 8 `Optional` In Entities And Queries](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOptional)**
 
**Description:** This application is an example of how is correct to use the Java 8 `Optional` in entities and queries. 

**Key points:**
- use the Spring Data built-in query-methods that return `Optional` (e.g., `findById()`)
- write your own queries that return `Optional`
- use `Optional` in entities getters
- in order to run different scenarios check the file, `data-mysql.sql`

-----------------------------------------------------------------------------------------------------------------------    

16. **[The Best Way To Map The `@OneToMany` Bidirectional Association](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOneToManyBidirectional)**

**Description:** This application is a proof of concept of how is correct to implement the bidirectional `@OneToMany` association from the performance perspective. 

**Key points:**
- always cascade from parent to child
- use `mappedBy` on the parent
- use `orphanRemoval` on parent in order to remove children without references
- use helper methods on parent to keep both sides of the association in sync
- use lazy fetching on both side of the association
- as entities identifiers, use assigned identifiers (business key, natural key (`@NaturalId`)) and/or database-generated identifiers and override (on child-side) properly the `equals()` and `hashCode()` methods as [here](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/)
- if `toString()` need to be overridden, then pay attention to involve only the basic attributes fetched when the entity is loaded from the database
     
**Note:** Pay attention to remove operations, especially to removing child entities. The `CascadeType.REMOVE` and `orphanRemoval=true` may produce too many queries. In such scenarios, relying on *bulk* operations is most of the time the best way to go for deletions.
     
-----------------------------------------------------------------------------------------------------------------------    

17. **[Query Fetching](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootQueryFetching)**

**Description:** This application is an example of how to write a query via `JpaRepository`, `EntityManager` and `Session`.

**Key points:**
- for `JpaRepository` use `@Query` or Spring Data Query Creation
- for `EntityManager` and `Session` use the `createQuery()` method 
     
-----------------------------------------------------------------------------------------------------------------------    

18. **[Why And How To Avoid The `AUTO` Generator Type In Hibernate 5 And MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAutoGeneratorType)**

**Description:** In MySQL & Hibernate 5, the `GenerationType.AUTO` generator type will result in using the `TABLE` generator. This adds a significant performance penalty. Turning this behavior to `IDENTITY` generator can be obtained by using `GenerationType.IDENTITY` or the *native* generator.
 
**Key points:**
- use `GenerationType.IDENTITY` instead of `GenerationType.AUTO`
- use the *native* generator - exemplified in this application
   
**Output example:**\
<a href="#"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootAutoGeneratorType/Hibernate%20Spring%20Boot%20Auto%20Generator%20Type.png" align="center" height="132" width="742" ></a>

-----------------------------------------------------------------------------------------------------------------------    

19. **[How To Avoid The Redundant save() Call](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRedundantSave)**

**Description:** This application is an example when calling `save()` for an entity is redundant (not necessary).

**Key points:**
- at flush time, Hibernate relies on *dirty checking* mechanism to determine the potential modifications in entities 
- for each modification, Hibernate automatically triggers the corresponding `UPDATE` statement without the need to explicitly call the `save()` method
- behind the scene, this redundancy (calling `save()` when is not necessarily) doesn't affect the number of triggered queries, but it implies a performance penalty in the underlying Hibernate processes

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

20. **[Why To Avoid PostgreSQL (`BIG`)`SERIAL` In Batching Inserts Via Hibernate](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchingAndSerial)**

**Description:** In PostgreSQL, using `GenerationType.IDENTITY` will disable insert batching. The `(BIG)SERIAL` is acting "almost" like MySQL, `AUTO_INCREMENT`. In this application, we use the `GenerationType.SEQUENCE` which permits insert batching, and we optimize it via the `hi/lo` optimization algorithm. 

**Key points:**
- use `GenerationType.SEQUENCE` instead of `GenerationType.IDENTITY`
- rely on the `hi/lo` algorithm to fetch a *hi* value in a database roundtrip (the *hi* value is useful for generating a certain/given number of identifiers in-memory; until you haven't exhausted all in-memory identifiers there is no need to fetch another *hi*) 
- you can go even further and use the Hibernate `pooled` and `pooled-lo` identifier generators (these are optimizations of `hi/lo` that allows external services to use the database without causing duplication keys errors)
- optimize batching via `spring.datasource.hikari.data-source-properties.reWriteBatchedInserts=true`
   
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchingAndSerial/PostgreSQL%20(BIG)SERIAL%20and%20Batching%20Inserts.png)

-----------------------------------------------------------------------------------------------------------------------    

21. **[JPA Inheritance - `SINGLE_TABLE`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSingleTableInheritance)**

**Description:** This application is a sample of using JPA Single Table inheritance strategy (`SINGLE_TABLE`).

**Key points:**
- this is the default inheritance strategy (`@Inheritance(strategy=InheritanceType.SINGLE_TABLE)`)
- all the classes in an inheritance hierarchy are represented via a single table in the database
- subclasses attributes non-nullability is ensured via `@NotNull` and MySQL triggers
- the default discriminator column memory footprint was optimized by declaring it of type `TINYINT`
   
**Output example (below is a single table obtained from 3 entities):**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSingleTableInheritance/Single%20table%20inheritance.png)

-----------------------------------------------------------------------------------------------------------------------    

22. **[Count and Assert SQL Statements](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCountSQLStatements)**

**Description:** This application is a sample of counting and asserting SQL statements triggered "behind the scene". Is very useful to count the SQL statements in order to ensure that your code is not generating more SQL statements that you may think (e.g., N+1 can be easily detected by asserting the number of expected statements).

**Key points:**
- for Maven, in `pom.xml`, add dependencies for DataSource-Proxy library and Vlad Mihalcea's db-util library
- create the `ProxyDataSourceBuilder` with `countQuery()`
- reset the counter via `SQLStatementCountValidator.reset()`
- assert `INSERT`, `UPDATE`, `DELETE` and `SELECT` via `assertInsert/Update/Delete/Select/Count(long expectedNumberOfSql)`
   
**Output example (when the number of expected SQLs is not equal with the reality an exception is thrown):**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootCountSQLStatements/count%20and%20assert%20SQL.png)

-----------------------------------------------------------------------------------------------------------------------    

23. **[How To Setup JPA Callbacks](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJpaCallbacks)**

**Description:** This application is a sample of setting the JPA callbacks (`Pre/PostPersist`, `Pre/PostUpdate`, `Pre/PostRemove` and `PostLoad`).

**Key points:**
- in entity, write callback methods and use the proper annotations
- callback methods annotated on the bean class must return `void` and take no arguments
   
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootJpaCallbacks/JPA%20callbacks.png)

-----------------------------------------------------------------------------------------------------------------------    

24. **[How To Use `@MapsId` For Sharing Identifier In `@OneToOne` Relationship](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOneToOneMapsId)**

**Description:** Instead of *regular* unidirectional/bidirectional `@OneToOne` better rely on an unidirectional `@OneToOne` and `@MapsId`. This application is a proof of concept. 

**Key points:**
- use `@MapsId` on child side
- use `@JoinColumn` to customize the name of the primary key column
- mainly, for `@OneToOne` associations, `@MapsId` will share the primary key with the parent table (`id` property acts as both primary key and foreign key)    
     
**Note:**
- `@MapsId` can be used for `@ManyToOne` as well   
     
-----------------------------------------------------------------------------------------------------------------------    

25. **[How To Fetch DTO Via `SqlResultSetMapping` And `EntityManager`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaSqlResultSetMappingEm)** 

**Description:** Fetching more data than needed is prone to performance penalities. Using DTO allows us to extract only the needed data. In this application we rely on `SqlResultSetMapping` and `EntityManager`.
 
**Key points:**
- use `SqlResultSetMapping` and `EntityManager`
- for using Spring Data Projections check this [item](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)
     
-----------------------------------------------------------------------------------------------------------------------    

26. **[How To Fetch DTO Via `SqlResultSetMapping` And `NamedNativeQuery`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSqlResultSetMappingAndNamedNativeQuery)**

**Note:** If you want to rely on the `{EntityName}.{RepositoryMethodName}` naming convention for simply creating in the repository interface methods with the same name as of native named query then skip this application and [check this one](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSqlResultSetMappingAndNamedNativeQuery2).

**Description:** Fetching more data than needed is prone to performance penalities. Using DTO allows us to extract only the needed data. In this application we rely on `SqlResultSetMapping`, `NamedNativeQuery`.
 
**Key points:**
- use `SqlResultSetMapping`, `NamedNativeQuery`
- for using Spring Data Projections check this [item](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)
     
-----------------------------------------------------------------------------------------------------------------------    

27. **[How To Fetch DTO Via `javax.persistence.Tuple` And Native SQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoTupleAndSql)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTO allows us to extract only the needed data. In this application we rely on `javax.persistence.Tuple` and native SQL.

**Key points:**
- use `java.persistence.Tuple` in a Spring repository and mark the query as `nativeQuery = true`
- for using Spring Data Projections check this [item](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections) 

-----------------------------------------------------------------------------------------------------------------------    

28. **[How To Fetch DTO via `javax.persistence.Tuple` and JPQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoTupleAndJpql)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTO allows us to extract only the needed data. In this application we rely on `javax.persistence.Tuple` and JPQL.

**Key points:**
- use `java.persistence.Tuple` in a Spring repository
- for using Spring Data Projections check this [item](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)

-----------------------------------------------------------------------------------------------------------------------    

29. **[How To Fetch DTO Via Constructor Expression and JPQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoConstructorExpression)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTO allows us to extract only the needed data. In this application we rely on Constructor Expression and JPQL.

**Key points:**
- write a proper constructor in the DTO class
- use a query as `SELECT new com.bookstore.dto.AuthorDto(a.name, a.age) FROM Author a`
- for using Spring Data Projections check this [item](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections) 

**See also:**\
[How To Fetch DTO Via Constructor And Spring Data Query Builder Mechanism](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoConstructor)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

30. **[How To Fetch DTO Via `ResultTransformer` And Native SQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoResultTransformer)**

**Description:** Fetching more data than needed is prone to performance penalties. Using DTO allows us to extract only the needed data. In this application we rely on Hibernate, `ResultTransformer` and native SQL.

**Key points:**
- use `AliasToBeanConstructorResultTransformer` for DTO without setters, but with constructor
- use `Transformers.aliasToBean()` for DTO with setters
- use `EntityManager.createNativeQuery()` and `unwrap(org.hibernate.query.NativeQuery.class)`
- starting with Hibernate 5.2, `ResultTransformer` is deprecated, but until a replacement will be available (probably in Hibernate 6.0) it can be used ([read further](https://discourse.hibernate.org/t/hibernate-resulttransformer-is-deprecated-what-to-use-instead/232))
- for using Spring Data Projections check this [recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections) 

-----------------------------------------------------------------------------------------------------------------------    

31. **[How To Fetch DTO Via `ResultTransformer` and JPQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoResultTransformerJpql)**
 
**Description:** Fetching more data than needed is prone to performance penalties. Using DTO allows us to extract only the needed data. In this application we rely on Hibernate, `ResultTransformer` and JPQL.

**Key points:**
- use `AliasToBeanConstructorResultTransformer` for DTO without setters, with constructor
- use `Transformers.aliasToBean()` for DTO with setters
- use `EntityManager.createQuery()` and `unwrap(org.hibernate.query.Query.class)`
- starting with Hibernate 5.2, `ResultTransformer` is deprecated, but until a replacement will be available (in Hibernate 6.0) it can be used ([read further](https://discourse.hibernate.org/t/hibernate-resulttransformer-is-deprecated-what-to-use-instead/232))
- for using Spring Data Projections check this [item](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)
     
-----------------------------------------------------------------------------------------------------------------------    

32. **[How To Fetch DTO Via Blaze-Persistence Entity Views](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoBlazeEntityView)** 

**Description:** Fetching more data than needed is prone to performance penalities. Using DTO allows us to extract only the needed data. In this application we rely on [Blaze-Persistence](https://persistence.blazebit.com/) entity views.
 
**Key points:**
- for Maven, add in `pom.xml` the dependencies specific to Blaze-Persistence
- configure Blaze-Persistence via `CriteriaBuilderFactory` and `EntityViewManager`
- write an *entity view* via an interface in Blaze-Persistence fashion
- write a Spring-centric repository by extending `EntityViewRepository`
- call method of this repository such as, `findAll()`, `findOne()`, etc
- for using Spring Data Projections check this [item](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)

-----------------------------------------------------------------------------------------------------------------------    

33. **[How Regular `@ElementCollection` (Without `@OrderColumn`) Works](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootElementCollectionNoOrderColumn)**

**Description:** This application reveals the possible performance penalties of using `@ElementCollection`. In this case, without `@OrderColumn`. As you can see in the next item (34) adding `@OrderColumn` can mitigate some performance penalties.

**Key points:**
- an `@ElementCollection` doesn't have a primary key
- an `@ElementCollection` is mapped in a separate table
- avoid `@ElementCollection` when you have a lot of inserts/deletes on this collection; inserts/deletes will cause Hibernate to delete all the existing table rows, process the collection in-memory, and re-insert the remaining table rows to mirror the collection from memory
- the more entries we have in this collection the greater the performance penalty will be
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootElementCollectionNoOrderColumn/%40ElementCollection%20without%20%40OrderColumn.png)  

-----------------------------------------------------------------------------------------------------------------------    

34. **[How `@ElementCollection` With `@OrderColumn` Works](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootElementCollectionWithOrderColumn)**

**Description:** This application reveals the performance penalties of using `@ElementCollection`. In this case, with `@OrderColumn`. But, as you can see in this application (in comparison with item 33), by adding `@OrderColumn` can mitigate some performance penalties when operations takes place near the collection tail (e.g., add/remove at/from the end of the collection). Mainly, all elements situated before the adding/removing entry are left untouched, so the performance penalty can be ignored if we affect rows close to the collection tail.

**Key points:**
- an `@ElementCollection` doesn't have a primary key
- an `@ElementCollection` is mapped in a separate table
- prefer `@ElementCollection` with `@OrderColumn` when you have a lot of inserts and deletes near the collection tail
- the more elements are inserted/removed from the beginning of the collection the greater the performance penalty will be
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootElementCollectionWithOrderColumn/%40ElementCollection%20with%20%40OrderColumn.png)

-----------------------------------------------------------------------------------------------------------------------    

35. **[How To Avoid Lazy Initialization Issues Caused By Disabling Open Session In View Via Explicit (Default) Values](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSuppressLazyInitInOpenSessionInView)**

**Note: Before reading this item try to see if [Hibernate5Module](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJacksonHibernate5Module) is not what you are looking for.**
 
**Description:** The Open-Session in View anti-pattern is activated by default in SpringBoot. Now, imagine a lazy association (e.g., `@OneToMany`) between two entities, `Author` and `Book` (an author has associated more books). Next, a REST controller endpoint fetches an `Author` without the associated `Book`. But, the View (more precisely, Jackson), forces the lazy loading of the associated `Book` as well. Since OSIV will supply the already opened `Session`, the proxies initializations take place successfully. The solution to avoid this performance penalty starts by disabling the OSIV. Further, explicitly initialize the un-fetched lazy associations. This way, the View will not force lazy loading.

**Key points:**
- disable OSIV by adding in `application.properties` this setting: `spring.jpa.open-in-view=false`
- fetch an `Author` entity and initialize its associated `Book` explicitly with (default) values (e.g., `null`)
- set `@JsonInclude(Include.NON_EMPTY)` on this entity-level to avoid rendering `null` or what is considered empty in the resulted JSON
     
 **NOTE:** If OSIV is enabled, the developer can still initialize the un-fetched lazy associations manually as long as he does this outside of a transaction to avoid flushing. But, why is this working? Since the `Session` is open, why the manually initialization of the associations of a managed entity doesn't trigger the flush? The answer can be found in the documentation of `OpenSessionInViewFilter` which specifies that: *This filter will by default not flush the Hibernate `Session`, with the flush mode set to `FlushMode.NEVER`. It assumes to be used in combination with service layer transactions that care for the flushing: The active transaction manager will temporarily change the flush mode to `FlushMode.AUTO` during a read-write transaction, with the flush mode reset to `FlushMode.NEVER` at the end of each transaction. If you intend to use this filter without transactions, consider changing the default flush mode (through the "flushMode" property).*         

-----------------------------------------------------------------------------------------------------------------------    

36. **[How To Use Spring Projections(DTO) And Inner Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaInnerJoins)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaInnerJoins/DTO%20via%20inner%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTO) and inner joins written via JPQL and native SQL (for MySQL).

**Key points:**
- define two entities (e.g., `Author` and `Book` in a (lazy) bidirectional `@OneToMany` association)
- populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)
- write interfaces (Spring projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)
- write inner joins queries using JPQL/SQL
     
-----------------------------------------------------------------------------------------------------------------------    

37. **[How To Use Spring Projections(DTO) And Left Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaLeftJoins)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaLeftJoins/DTO%20via%20left%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTO) and left joins written via JPQL and native SQL (for MySQL).

**Key points:**
- define two entities (e.g., `Author` and `Book` in a (lazy) bidirectional `@OneToMany` association)
- populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)
- write interfaces (Spring projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)
- write left joins queries using JPQL/SQL
     
-----------------------------------------------------------------------------------------------------------------------    

38. **[How To Use Spring Projections(DTO) And Right Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaRightJoins)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaRightJoins/DTO%20via%20right%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTO) and right joins written via JPQL and native SQL (for MySQL).

**Key points:**
- define two entities (e.g., `Author` and `Book` in a (lazy) bidirectional `@OneToMany` association)
- populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)
- write interfaces (Spring projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)
- write right joins queries using JPQL/SQL
     
-----------------------------------------------------------------------------------------------------------------------    

39. **[How To Use Spring Projections(DTO) And Inclusive Full Joins (PostgreSQL)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaFullJoins)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaFullJoins/DTO%20via%20inclusive%20full%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTO) and inclusive full joins written via JPQL and native SQL (for PostgreSQL).

**Key points:**
- define two entities (e.g., `Author` and `Book` in a (lazy) bidirectional `@OneToMany` association)
- populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)
- write interfaces (Spring projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)
- write inclusive full joins queries using JPQL/SQL
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

40. **[How To Use Spring Projections(DTO) And Exclusive Left Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaLeftExcludingJoins)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaLeftExcludingJoins/DTO%20via%20exclusive%20left%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTO) and exclusive left joins written via JPQL and native SQL (for MySQL).

**Key points:**
- define two entities (e.g., `Author` and `Book` in a (lazy) bidirectional `@OneToMany` association)
- populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)
- write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)
- write exclusive left joins queries using JPQL/SQL
     
-----------------------------------------------------------------------------------------------------------------------    

41. **[How To Use Spring Projections(DTO) And Exclusive Right Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaRightExcludingJoins)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaRightExcludingJoins/DTO%20via%20exclusive%20right%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTO) and exclusive right joins written via JPQL and native SQL (for MySQL).

**Key points:**
- define two entities (e.g., `Author` and `Book` in a (lazy) bidirectional `@OneToMany` association)
- populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)
- write interfaces (Spring projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)
- write exclusive right joins queries using JPQL/SQL
     
-----------------------------------------------------------------------------------------------------------------------    

42. **[How To Use Spring Projections(DTO) And Exclusive Full Joins (PostgreSQL)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaFullOuterExcludingJoins)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaFullOuterExcludingJoins/DTO%20via%20exclusive%20full%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTO) and exclusive full joins written via JPQL and native SQL (for PostgreSQL).

**Key points:**
- define two entities (e.g., `Author` and `Book` in a (lazy) bidirectional `@OneToMany` association)
- populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)
- write interfaces (Spring projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)
- write exclusive full joins queries using JPQL/SQL
     
-----------------------------------------------------------------------------------------------------------------------    

43. **[Why You Should Avoid Time-Consuming Tasks In Spring Boot Post-Commit Hooks](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPostCommit)**

**Description:** This application is a proof of concept for using Spring post-commit hooks and how they may affect the persistence layer performance.

**Key points:**
- avoid time-consuming tasks in Spring post-commit hooks since the database connection will remain open until this code finshes
     
-----------------------------------------------------------------------------------------------------------------------    

44. **[How To Exploit Spring Projections(DTO) And Join Unrelated Entities In Hibernate 5.1+](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoUnrelatedEntities)**

**Description:** This application is a proof of concept for using Spring Projections (DTO) and join unrelated entities. Hibernate 5.1 introduced explicit joins on unrelated entities and the syntax and behaviour are similar to SQL `JOIN` statements.

**Key points:**
- define serveral entities (e.g., `Author` and `Book` unrelated entities)
- populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)
- write interfaces (Spring projections) that contains getters for the columns that should be fetched from the database (e.g., `BookstoreDto`)
- write joins queries using JPQL/SQL (e.g., queries all authors names and book titles of the given price)  
     
-----------------------------------------------------------------------------------------------------------------------    

45. **[Why To Avoid Lombok `@EqualsAndHashCode` And `@Data` In Entities And How To Override `equals()` And `hashCode()`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLombokEqualsAndHashCode)**
 
**Description:** Entities should implement `equals()` and `hashCode()` as [here](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/). The main idea is that Hibernate requires that an entity is equal to itself across all its state transitions (*transient*, *attached*, *detached* and *removed*). Using Lombok `@EqualsAndHashCode` (or `@Data`) will not respect this requirment.

**Key points:**\
**AVOID THESE APPROACHES**
- Using Lombok default behavior of `@EqualsAndHashCode`
(entity: `LombokDefaultBook`, test: `LombokDefaultEqualsAndHashCodeTest`)
- Using Lombok  `@EqualsAndHashCode` with primary key only
(entity: `LombokIdBook`, test: `LombokEqualsAndHashCodeWithIdOnlyTest`)
- Rely on default `equals()` and `hashCode()`
(entity: `DefaultBook`, test: `DefaultEqualsAndHashCodeTest`)
- Rely on default `equals()` and `hashCode()` containing only the database-generated identifier
(entity: `IdBook`, test: `IdEqualsAndHashCodeTest`)

**PREFER THESE APPROACHES**
- Rely on business key (entity: `BusinessKeyBook`, test: `BusinessKeyEqualsAndHashCodeTest`)
- Rely on `@NaturalId` (entity: `NaturalIdBook`, test: `NaturalIdEqualsAndHashCodeTest`)
- Rely on manually assigned identifiers (entity: `IdManBook`, test: `IdManEqualsAndHashCodeTest`)
- Rely on database-generated identifiers (entity: `IdGenBook`, test: `IdGenEqualsAndHashCodeTest`)
     
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLombokEqualsAndHashCode/auto-generated%20primary%20key%20and%20equals%20-%20hashCode.png)   

-----------------------------------------------------------------------------------------------------------------------    

46. **[How To Avoid `LazyInitializationException` Via `JOIN FETCH`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinFetch)**

**See also:**
- [LEFT JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLeftJoinFetch)
- [JOIN VS. JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinVSJoinFetch)

**Description:** Typically, when we get a `LazyInitializationException` we tend to modify the association fetching type from `LAZY` to `EAGER`. That is very bad! This is a [code smell](https://vladmihalcea.com/eager-fetching-is-a-code-smell/). Best way to avoid this exception is to rely on `JOIN FETCH` (if you plan to modify the fetched entities) or `JOIN` + DTO (if the fetched data is only read). `JOIN FETCH` allows associations to be initialized along with their parent objects using a single `SELECT`. This is particularly useful for fetching associated collections. 

This application is a `JOIN FETCH` example for avoiding `LazyInitializationException`. 

**Key points:**
- define two related entities (e.g., `Author` and `Book` in a `@OneToMany` lazy-bidirectional association)
- write a JPQL `JOIN FETCH` to fetch an author including his books
- write a JPQL `JOIN FETCH` (or `JOIN`) to fetch a book including its author

**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootJoinFetch/hibernate%20spring%20boot%20join%20fetch.png) 

-----------------------------------------------------------------------------------------------------------------------    

47. **[How To Merge Entity Collections](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootMergeCollections)**

**Description:** This is a Spring Boot example based on the following [article](https://vladmihalcea.com/merge-entity-collections-jpa-hibernate/). Is a functional implementation of the Vlad's example. It is highly recommended to read that article.

**Key points:**
- remove the existing database rows that are no longer found in the incoming collection
- update the existing database rows which can be found in the incoming collection
- add the rows found in the incoming collection, which cannot be found in the current database snapshot
     
-----------------------------------------------------------------------------------------------------------------------    

48. **[How To Delay Connection Acquisition As Needed (Hibernate 5.2.10)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDelayConnection)**

**Description:** This is a Spring Boot example that exploits Hibernate 5.2.10 capability of delaying the connection acquisition as needed. By default, in *resource-local* mode, a database connection is aquried immediately after calling a method annotated with `@Transactional`. If this method contains some time-consuming tasks before the first SQL statement then the connection is hold open for nothing. But, Hibernate 5.2.10 allows us to delay the connection acquisition as needed. This example rely on HikariCP as the default connection pool for Spring Boot.

**Key points:**
- set `spring.datasource.hikari.auto-commit=false` in application.properties
- set `spring.jpa.properties.hibernate.connection.provider_disables_autocommit=true` in `application.properties`
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDelayConnection/delay%20connection%20acquisition%201.png)
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDelayConnection/delay%20connection%20acquisition%202.png)

-----------------------------------------------------------------------------------------------------------------------    

49. **[How To Generate Sequences Of Identifiers Via Hibernate `hi/lo` Algorithm](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHiLo)**

 **Note:** If systems external to your application need to insert rows in your tables then don't rely on `hi/lo` algorithm since, in such cases, it may cause errors resulted from generating duplicated identifiers. Rely on `pooled` or `pooled-lo` algorithms (optimizations of `hi/lo`).
 
**Description:** This is a Spring Boot example of using the `hi/lo` algorithm for generating 1000 identifiers in 10 database roundtrips for batching 1000 inserts in batches of 30. 

**Key points:**
- use the `SEQUENCE` generator type (e.g., in PostgreSQL)
- configure the `hi/lo` algorithm as in `Author.java` entity
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHiLo/Hibernate%20hilo%20algorithm.png)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

50. **[The Best Way To Implement A Bidirectional `@ManyToMany` Association](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootManyToManyBidirectional)**

**Description:** This application is a proof of concept of how it is correct to implement the bidirectional `@ManyToMany` association from the performance perspective. 

**Key points:**
- choose an owning and a `mappedBy` side
- materialize the relationships collections via `Set` not `List`
- use helper methods on the owner of the relationship to keep both sides of the association in sync
- on the owner of the relationship use `CascadeType.PERSIST` and `CascadeType.MERGE`, but avoid `CascadeType.REMOVE/ALL`
- on the owner of the relationship set up join table
- `@ManyToMany` is lazy by default; keep it this way!
- as entities identifiers, use assigned identifiers (business key, natural key (`@NaturalId`)) and/or database-generated identifiers and override (on both sides) properly the `equals()` and `hashCode()` methods as [here](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/)
- if `toString()` need to be overridden, then pay attention to involve only for the basic attributes fetched when the entity is loaded from the database
     
-----------------------------------------------------------------------------------------------------------------------    

51. **[Prefer `Set` Instead of `List` in `@ManyToMany` Associations](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootManyToManyBidirectionalListVsSet)**

**Description:** This is a Spring Boot example of removing rows in case of a bidirectional `@ManyToMany` using `List`, respectively `Set`. The conclusion is that `Set` is much better! This applies to unidirectional as well!

**Key points:**
- using `Set` is much more efficent than `List`    
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootManyToManyBidirectionalListVsSet/manytomany%20use%20always%20set%20not%20list.png)

-----------------------------------------------------------------------------------------------------------------------    

52. **[How To View Query Details Via `log4jdbc`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLog4JdbcViewBindingParameters)**

**Description:** View the query details via [log4jdbc](https://github.com/candrews/log4jdbc-spring-boot-starter).

**Key points:**
- for Maven, in `pom.xml`, add `log4jdbc` dependency
     
**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLog4JdbcViewBindingParameters/query%20details%20via%20log4jdbc.png)

-----------------------------------------------------------------------------------------------------------------------    

53. **[How To View Binding Params Via TRACE](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLogTraceViewBindingParameters)**

**Description:** View the prepared statement binding/extracted parameters via `TRACE`.

**Key points:**
- in `application.properties` add: `logging.level.org.hibernate.type.descriptor.sql=TRACE`
- or, even better (for filtering SQLs capabilities), in a Logback specific configuration file add the proper logger
     
**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLogTraceViewBindingParameters/display%20binding%20and%20extracted%20parameters%20via%20TRACE.png) 

-----------------------------------------------------------------------------------------------------------------------    

54. **[How To Store `java.time.YearMonth` As `Integer` Or `Date` Via Hibernate Types Library](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootYearMonth)**

**Description:** [Hibernate Types](https://github.com/vladmihalcea/hibernate-types) is a set of extra types not supported by default in Hibernate Core. One of these types is `java.time.YearMonth`. This is a Spring Boot application that uses Hibernate Type to store this `YearMonth` in a MySQL database as integer or date.

**Key points:**
- for Maven, add Hibernate Types as a dependency in `pom.xml`
- in entity use `@TypeDef` to map `typeClass` to `defaultForType`  
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootYearMonth/Hibernate%20Types%20library.png)     

-----------------------------------------------------------------------------------------------------------------------    

55. **[How To Execute SQL Functions In JPQL Query](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJpqlFunctionsParams)**

**Note**: Using SQL functions in the `WHERE` part (not in the `SELECT` part) of  query in JPA 2.1 can be done via `function()` as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJpqlFunction).

**Description:** Trying to use SQL functions (standard or defined) in JPQL queries may result in exceptions if Hibernate will not recognize them and cannot parse the JPQL query. For example, the MySQL, `concat_ws` function is not recognized by Hibernate. This application is a Spring Boot application based on Hibernate 5.3, that registers the `concat_ws` function via `MetadataBuilderContributor` and inform Hibernate about it via, `metadata_builder_contributor` property. This example uses `@Query` and `EntityManager` as well, so you can see two use cases.

**Key points:**
- use Hibernate 5.3 (or, to be precisely, 5.2.18) (e.g., use Spring Boot 2.1.0.RELEASE)
- implement `MetadataBuilderContributor` and register the `concat_ws` MySQL function
- in `application.properties`, set `spring.jpa.properties.hibernate.metadata_builder_contributor` to point out Hibernate to `MetadataBuilderContributor` implementation
          
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootJpqlFunctionsParams/SQL%20functions%20in%20JPQL%20parameters.png)       

-----------------------------------------------------------------------------------------------------------------------

56. **[Log Slow Queries Via DataSource-Proxy](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLogSlowQueries)**

**Description:** This application is a sample of logging only slow queries via **[DataSource-Proxy](https://github.com/ttddyy/datasource-proxy)**. A slow query is a query that has an execution time bigger than a specificed threshold in milliseconds.

**Key points:**
- for Maven, add in `pom.xml` the DataSource-Proxy dependency
- create an bean post processor to intercept the `DataSource` bean
- wrap the `DataSource` bean via `ProxyFactory` and an implementation of `MethodInterceptor`
- choose a threshold in milliseconds
- define a listener and override `afterQuery()`
   
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLogSlowQueries/log%20slow%20queries%20via%20datasource-proxy.png)

-----------------------------------------------------------------------------------------------------------------------    

57. **[Offset Pagination - Trigger `SELECT COUNT` Subquery And Return `Page<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageDtoOffsetPagination)**

**Description:** This application fetches data as `Page<dto>` via Spring Boot offset pagination. Most of the time, the data that should be paginated is *read-only* data. Fetching the data into entities should be done only if we plan to modify that data, therefore, fetching *read only* data as `Page<entity>` is not preferable since it may end up in a significant performance penalty. The `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. Therefore, there will be a single database roundtrip instead of two (typically, there is one query needed for fetching the data and one for counting the total number of records).

**Key points:**
- create a Spring projection (DTO) to contains getters only for the columns that should be fetched
- write a repository that extends `PagingAndSortingRepository`
- fetch data via a JPQL or native query (that includes counting) into a `List<dto>`
- use the fetched `List<dto>` and the proper `Pageable` to create a `Page<dto>`
     
-----------------------------------------------------------------------------------------------------------------------    

58. **[Offset Pagination - Trigger `SELECT COUNT` Subquery And Return `List<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListDtoOffsetPagination)**

**Description:** This application fetches data as `List<dto>` via Spring Boot offset pagination. Most of the time, the data that should be paginated is *read-only* data. Fetching the data into entities should be done only if we plan to modify that data, therefore, fetching *read only* data as `List<entity>` is not preferable since it may end up in a significant performance penalty. The `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. Therefore, there will be a single database roundtrip instead of two (typically, there is one query needed for fetching the data and one for counting the total number of records).
 
**Key points:**
- create a Spring projection (DTO) to contains getters only for the columns that should be fetched
- write a repository that extends `PagingAndSortingRepository`
- fetch data via a JPQL or native query (that includes counting) into a `List<dto>`
     
-----------------------------------------------------------------------------------------------------------------------    
     
59. **[How To Customize HikariCP Settings Via Properties](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHikariCPPropertiesKickoff)**

**If you use the `spring-boot-starter-jdbc` or `spring-boot-starter-data-jpa` "starters", you automatically get a dependency to HikariCP**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up HikariCP via `application.properties` only. The `jdbcUrl` is set up for a MySQL database. For testing purposes, the application uses an `ExecutorService`for simulating concurrent users. Check the HickariCP report revealing the connection pool status.

**Key points:**
- in `application.properties`, rely on `spring.datasource.hikari.*` to configure HikariCP     

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHikariCPPropertiesKickoff/HikariCP%20trace%20log.png)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

60. **[How To Customize HikariCP Settings Via Properties And `DataSourceBuilder`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderHikariCPKickoff)**

**If you use the `spring-boot-starter-jdbc` or `spring-boot-starter-data-jpa` "starters", you automatically get a dependency to HikariCP**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up HikariCP via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purposes, the application uses an `ExecutorService` for simulating concurrent users. Check the HickariCP report revealing the connection pool status.

**Key points:**
- in `application.properties`, configure HikariCP via a custom prefix, e.g., `app.datasource.*`
- write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHikariCPPropertiesKickoff/HikariCP%20trace%20log.png)

-----------------------------------------------------------------------------------------------------------------------    

61. **[Running a SpringBoot Application Under Payara Server Using a Payara Data Source (JDBC Resource and Connection Pool)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/SpringBootPayaraMySqlKickoffApplication)**

**This application is detailed in this [DZone](https://dzone.com/articles/work-in-progress-1) article.**

-----------------------------------------------------------------------------------------------------------------------    

62. **[How To Customize BoneCP Settings Via Properties And `DataSourceBuilder`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderBoneCPKickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up BoneCP via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purposes, the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**
- in `pom.xml` add the BoneCP dependency
- in `application.properties`, configure BoneCP via a custom prefix, e.g., `app.datasource.*`
- write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceBuilderBoneCPKickoff/BoneCP%20trace%20log.png)

-----------------------------------------------------------------------------------------------------------------------    

63. **[How To Customize ViburDBCP Settings Via Properties And `DataSourceBuilder`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderViburDBCPKickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.
 
**Description:** This is a kickoff application that set up ViburDBCP via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purposes, the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**
- in `pom.xml` add the ViburDBCP dependency
- in `application.properties`, configure ViburDBCP via a custom prefix, e.g., `app.datasource.*`
- write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceBuilderViburDBCPKickoff/ViburDBCP%20log%20trace.png)

-----------------------------------------------------------------------------------------------------------------------    

64. **[How To Customize C3P0 Settings Via Properties And `DataSourceBuilder`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderC3P0Kickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.
 
**Description:** This is a kickoff application that set up C3P0 via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purposes, the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**
- in `pom.xml` add the C3P0 dependency
- in `application.properties`, configure C3P0 via a custom prefix, e.g., `app.datasource.*`
- write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceBuilderC3P0Kickoff/C3P0%20trace%20log.png)

-----------------------------------------------------------------------------------------------------------------------    

65. **[How To Customize DBCP2 Settings Via Properties And `DataSourceBuilder`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderDBCP2Kickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up DBCP2 via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purposes, the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**
- in `pom.xml` add the DBCP2 dependency
- in `application.properties`, configure DBCP2 via a custom prefix, e.g., `app.datasource.*`
- write a `@Bean` that returns the `DataSource`
     
-----------------------------------------------------------------------------------------------------------------------    

66. **[How To Customize Tomcat Settings Via Properties And `DataSourceBuilder`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderTomcatKickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.
 
**Description:** This is a kickoff application that set up Tomcat via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purposes, the application uses an `ExecutorService` for simulating concurrent users. 

**Key points:**
- in `pom.xml` add the Tomcat dependency
- in `application.properties`, configure Tomcat via a custom prefix, e.g., `app.datasource.*`
- write a `@Bean` that returns the `DataSource`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceBuilderTomcatKickoff/Tomcat%20trace%20log.png)

-----------------------------------------------------------------------------------------------------------------------    

67. **[How To Configure Two Data Sources With Two Connection Pools](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTwoDataSourceBuilderKickoff)**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that uses two data sources (two MySQL databases, one named `authorsdb` and one named `booksdb`) with two connection pools (each database uses its own HikariCP connection pool with different settings). Based on the above items is pretty easy to configure two connection pools from two different providers as well.

**Key points:**
- in `application.properties`, configure two HikariCP connection pools via a two custom prefixes, e.g., `app.datasource.ds1` and `app.datasource.ds2`
- write a `@Bean` that returns the first `DataSource` and mark it as `@Primary`
- write another `@Bean` that returns the second `DataSource`
- configure two `EntityManagerFactory` and point out the packages to scan for each of them
- put the domains and repositories for each `EntityManager` in the right packages

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootTwoDataSourceBuilderKickoff/Two%20DataSources.png)

-----------------------------------------------------------------------------------------------------------------------    

68. **[How To Provide a Fluent API Via Setters For Building Entities](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFluentApiOnSetters)**

**Note**: If you want yo provide a Fluent API without altering setters then consider [this item](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFluentApiAdditionalMethods).

**Description:** This is a sample application that alter the entities setters methods in order to empower a Fluent API.

**Key points:**
- in entitites, return `this` instead of `void` in setters

**Fluent API example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootFluentApiOnSetters/fluent%20entity%20setters.png)

-----------------------------------------------------------------------------------------------------------------------    

69. **[How To Provide a Fluent API Via Additional Methods For Building Entities](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFluentApiAdditionalMethods)**

**Note**: If you want yo provide a Fluent API by altering setters then consider [this item](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFluentApiOnSetters).

**Description:** This is a sample application that add in entities additional methods (e.g., for `setName`, we add `name`) methods in order to empower a Fluent API.

**Key points:**
- in entities, add for each setter an additional method that return `this` instead of `void`

**Fluent API example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootFluentApiAdditionalMethods/fluent%20api%20with%20additional%20methods.png)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

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

**Key points:**
- write an `abstract` class that expose the `Slice<T> findAll(...)` methods (`SlicePagingRepositoryImplementation`)
- implement the `findAll()` methods to return `Slice<T>` (or `Page<T>`, but without the total number of elements)
- return a `SliceImpl` (`Slice<T>`) or a `PageImpl` (`Page<T>`) without the total number of elements
- implement a new `readSlice()` method or override the `SimpleJpaRepository#readPage()` page to avoid `SELECT COUNT`
- pass the entity class (e.g., `Author.class`) to this `abstract` class via a class repository (`AuthorRepository`)

-----------------------------------------------------------------------------------------------------------------------    

71. **[Offset Pagination - Trigger `COUNT(*) OVER` And Return `List<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListDtoOffsetPaginationWF)**
 
**Description:** Typically, in offset pagination, there is one query needed for fetching the data and one for counting the total number of records. But, we can fetch this information in a single database rountrip via a `SELECT COUNT` subquery nested in the main `SELECT`. Even better, for databases vendors that support *Window Functions* there is a solution relying on `COUNT(*) OVER()` as in this application that uses this window function in a native query against MySQL 8. So, prefer this one instead of `SELECT COUNT` subquery.

**Key points:**
- create a DTO projection that contains getters for the columns that should be fetched and an extra-column for mapping the return of the `COUNT(*) OVER()` window function
- write a native query relying on this window function

**Example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootListDtoOffsetPaginationWF/offset%20pagination%20via%20window%20function.png)

-----------------------------------------------------------------------------------------------------------------------    

72. **[How To Implement Keyset Pagination in Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootKeysetPagination)**

**Description:** When we rely on an *offset* paging we have the performance penalty induced by throwing away *n* records before reached the desired *offset*. Larger *n* leads to a significant performance penalty. When we have a large *n* is better to rely on *keyset* pagination which maintain a "constant" time for large datasets. In order to understand how bad *offset* can perform please check this [article](http://allyouneedisbackend.com/blog/2017/09/24/the-sql-i-love-part-1-scanning-large-table/):

Screenshot from that article (*offset* pagination):
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootKeysetPagination/offset%20pagination.png)

**Need to know if there are more records?**\
By its nature, *keyset* doesn't use a `SELECT COUNT` to fetch the number of total records. But, with a little tweak, we can easily say if there are more records, therefore to show a button of type `Next Page`. Mainly, if you need such a thing then consider [this application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootKeysetPaginationNextPage) whose climax is listed below:

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

**Key points:**
- choose the column(s) to act as the latest visited record (e.g., `id`)
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

**Key points of classical *offset* pagination:**
- write a repository that extends `PagingAndSortingRepository`
- call or write methods that returns `Page<entity>`

**Examples of classical *offset* pagination:**
- call the built-in `findAll(Pageable)` without sorting:\
`repository.findAll(PageRequest.of(page, size));`
- call the built-in `findAll(Pageable)` with sorting:\
`repository.findAll(PageRequest.of(page, size, new Sort(Sort.Direction.ASC, "name")));`
- use Spring Data query creation to define new methods in your repository:\
`Page<Author> findByName(String name, Pageable pageable);`\
`Page<Author> queryFirst10ByName(String name, Pageable pageable);`
     
-----------------------------------------------------------------------------------------------------------------------    

74. **[How To Optimize Batch Inserts of Parent-Child Relationships In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertOrder)**

**Description:** Let's suppose that we have a one-to-many relationship between `Author` and `Book` entities. When we save an author, we save his books as well thanks to cascading all/persist. We want to create a bunch of authors with books and save them in the database (e.g., a MySQL database) using the batch technique. By default, this will result in batching each author and the books per author (one batch for the author and one batch for the books, another batch for the author and another batch for the books, and so on). In order to batch authors and books, we need to **order inserts** as in this application.

**Key points:**
Beside all setting specific to batching inserts in MySQL, we need to set up in `application.properties` the following property: `spring.jpa.properties.hibernate.order_inserts=true`

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

**Key points:**
- in `application.properties` set `spring.jpa.properties.hibernate.jdbc.batch_size`
- in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL, statements get rewritten into a single string buffer and sent in a single request)
- in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)
- in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))
- in case of using a parent-child relationship with cascade all/persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_updates=true` to optimize the batching by ordering updates
- before Hibernate 5, we need to set in `application.properties` a setting for enabling batching for versioned entities during update and delete operations (entities that contains `@Version` for implicit optimistic locking); this setting is: `spring.jpa.properties.hibernate.jdbc.batch_versioned_data=true`; starting with Hibernate 5, this setting should be `true` by default
   
**Output example for single entity:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchUpdateOrderSingleEntity/batch%20updates.png)     
   
**Output example for parent-child relationship:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchUpdateOrder/batch%20updates.png)

-----------------------------------------------------------------------------------------------------------------------    

76. **[How To Batch Deletes That Don't Involve Associations In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchDeleteSingleEntity)**

**Description:** Batch deletes that don't involve associations in MySQL.

**Note:** Spring `deleteAllInBatch()` and `deleteInBatch()` don't use delete batching and don't take advantage of automatic optimstic locking mechanism to prevent *lost updates* (e.g., `@Version` is ignored). They rely on `Query.executeUpdate()` to trigger *bulk* operations. These operations are fast, but Hibernate doesn’t know which entities are removed, therefore, the Persistence Context is not updated accordingly (it's up to you to flush (before delete) and close/clear (after delete) the Persistence Context accordingly to avoid issues created by unflushed (if any) or outdated (if any) entities). The first one (`deleteAllInBatch()`) simply triggers a `delete from entity_name` statement and is very useful for deleting all records. The second one (`deleteInBatch()`) triggers a `delete from entity_name where id=? or id=? or id=? ...` statement, therefore, is prone to cause issues if the generated `DELETE` statement exceedes the maximum accepted size. This issue can be controlled by deleting the data in chunks, relying on `IN` operator, and so on. *Bulk* operations are faster than batching which can be achieved via the `deleteAll()`, `deleteAll(Iterable<? extends T> entities)` or `delete()` method. Behind the scene, the two flavors of `deleteAll()` relies on `delete()`. The `delete()`/`deleteAll()` methods rely on `EntityManager.remove()` therefore the Persistence Context is synchronized accordingly. Moreover, if automatic optimstic locking mechanism (to prevent *lost updates*) is enabled then it will be used.

**Key points for *regular* delete batching:**
- for deleting in batches rely on `deleteAll()`, `deleteAll(Iterable<? extends T> entities)` or `delete()` method
- in `application.properties` set `spring.jpa.properties.hibernate.jdbc.batch_size`
- in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL, statements get rewritten into a single string buffer and sent in a single request)
- in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)
- in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))
- before Hibernate 5, we need to set in `application.properties` a setting for enabling batching for versioned entities during update and delete operations (entities that contains `@Version` for implicit optimistic locking); this setting is: `spring.jpa.properties.hibernate.jdbc.batch_versioned_data=true`; starting with Hibernate 5, this setting should be `true` by default
    
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchDeleteSingleEntity/batch%20deletes.png)

-----------------------------------------------------------------------------------------------------------------------    

77. **[How To Batch Deletes In MySQL Via orphanRemoval=true](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchDeleteOrphanRemoval)**
 
**Description:** Batch deletes in MySQL via `orphanRemoval=true`.

**Note:** Spring `deleteAllInBatch()` and `deleteInBatch()` don't use delete batching and don't take advantage of cascading removal, `orphanRemoval` and automatic optimstic locking mechanism to prevent *lost updates* (e.g., `@Version` is ignored). They rely on `Query.executeUpdate()` to trigger *bulk* operations. These operations are fast, but Hibernate doesn’t know which entities are removed, therefore, the Persistence Context is not updated accordingly (it's up to you to flush (before delete) and close/clear (after delete) the Persistence Context accordingly to avoid issues created by unflushed (if any) or outdated (if any) entities). The first one (`deleteAllInBatch()`) simply triggers a `delete from entity_name` statement and is very useful for deleting all records. The second one (`deleteInBatch()`) triggers a `delete from entity_name where id=? or id=? or id=? ...` statement, therefore, is prone to cause issues if the generated `DELETE` statement exceedes the maximum accepted size. This issue can be controlled by deleting the data in chunks, relying on `IN` operator, and so on. *Bulk* operations are faster than batching which can be achieved via the `deleteAll()`, `deleteAll(Iterable<? extends T> entities)` or `delete()` method. Behind the scene, the two flavors of `deleteAll()` relies on `delete()`. The `delete()`/`deleteAll()` methods rely on `EntityManager.remove()` therefore the Persistence Context is synchronized accordingly. If automatic optimstic locking mechanism (to prevent *lost updates*) is enabled then it will be used. Moreover, cascading removals and `orphanRemoval` works as well.

**Key points for using `deleteAll()/delete()`:**
- in this example, we have a `Author` entity and each author can have several `Book` (*one-to-many*)
- first, we use `orphanRemoval=true` and `CascadeType.ALL`
- second, we dissociate all `Book` from the corresponding `Author`
- third, we explicitly (manually) flush the Persistent Context; is time for `orphanRemoval=true` to enter into the scene; thanks to this setting, all disassociated books will be deleted; the generated `DELETE` statements are batched (if `orphanRemoval` is set to `false`, a bunch of updates will be executed instead of deletes)
- forth, we delete all `Author` via the `deleteAll()` or `delete()` method (since we have dissaciated all `Book`, the `Author` deletion will take advantage of batching as well)

-----------------------------------------------------------------------------------------------------------------------    

78. **[How To Batch Deletes In MySQL Via SQL `ON DELETE CASCADE`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchDeleteCascadeDelete)**

**Description:** Batch deletes in MySQL via `ON DELETE CASCADE`. Auto-generated database schema will contain the `ON DELETE CASCADE` directive.

**Note:** Spring `deleteAllInBatch()` and `deleteInBatch()` don't use delete batching and don't take advantage of cascading removal, `orphanRemoval` and automatic optimistic locking mechanism to prevent *lost updates* (e.g., `@Version` is ignored), but both of them take advantage on `ON DELETE CASCADE` and are very efficient. They trigger *bulk* operations via `Query.executeUpdate()`, therefore, the Persistence Context is not synchronized accordingly (it's up to you to flush (before delete) and close/clear (after delete) the Persistence Context accordingly to avoid issues created by unflushed (if any) or outdated (if any) entities). The first one simply triggers a `delete from entity_name` statement, while the second one triggers a `delete from entity_name where id=? or id=? or id=? ...` statement. For delete in batches rely on `deleteAll()`, `deleteAll(Iterable<? extends T> entities)` or `delete()` method. Behind the scene, the two flavors of `deleteAll()` relies on `delete()`. Mixing batching with database automatic actions (`ON DELETE CASCADE`) will result in a partially synchronized Persistent Context.

**Key points:**
- in this application, we have a `Author` entity and each author can have several `Book` (*one-to-many*)
- first, we remove `orphanRemoval` or set it to `false`
- second, we use only `CascadeType.PERSIST` and `CascadeType.MERGE`
- third, we set `@OnDelete(action = OnDeleteAction.CASCADE)` next to `@OneToMany`
- fourth, we set `spring.jpa.properties.hibernate.dialect` to `org.hibernate.dialect.MySQL5InnoDBDialect` (or, `MySQL8Dialect`)
- fifth, we run through a set of `deleteFoo()` methods that uses *bulk* and batching deletes as well

**Output example:**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchDeleteCascadeDelete/batch%20delete%20via%20SQL%20cascade%20delete.png)

-----------------------------------------------------------------------------------------------------------------------    

79. **[How To Use Hibernate `@NaturalId` In Spring Boot Style](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNaturalIdImpl)**

**Alternative implementation:** In case that you want to avoid extending `SimpleJpaRepository` check this [implementation](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNaturalId).

**Description:** This is a SpringBoot application that maps a natural business key using Hibernate `@NaturalId`. This implementation allows us to use `@NaturalId` as it was provided by Spring.

**Key points:**
- in the entity (e.g., `Book`), mark the properties (business keys) that should act as natural IDs with `@NaturalId`; commonly, there is a single such property, but multiple are suppored as well as [here](https://docs.jboss.org/hibernate/orm/5.0/mappingGuide/en-US/html/ch07.html)
- for non-mutable ids, mark the columns as `@NaturalId(mutable = false)` and `@Column(nullable = false, updatable = false, unique = true, ...)`
- for mutable ids, mark the columns as `@NaturalId(mutable = true)` and `@Column(nullable = false, updatable = true, unique = true, ...)`
- override the `equals()` and `hashCode()` using the natural id(s)
- define a `@NoRepositoryBean` interface (`NaturalRepository`) to define two methods, named `findBySimpleNaturalId()` and `findByNaturalId()`
- provide an implementation for this interface (`NaturalRepositoryImpl`) relying on Hibernate, `Session`, `bySimpleNaturalId()` and `byNaturalId()` methods
- use `@EnableJpaRepositories(repositoryBaseClass = NaturalRepositoryImpl.class)` to register this implementation as the base class
- for the entity, write a classic repository
- inject this class in your services and call `findBySimpleNaturalId()` or `findByNaturalId()`

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

80. **[How To Set Up P6Spy in Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootP6spy)**

**Description:** This is a Spring Boot application that uses [P6Spy](https://github.com/p6spy/p6spy). *P6Spy is a framework that enables database data to be seamlessly intercepted and logged with no code changes to the application.*

**Key points:**
- in `pom.xml`, add the P6Spy Maven dependency
- in `application.properties`, set up JDBC URL as, `jdbc:p6spy:mysql://localhost:3306/db_users`
- in `application.properties`, set up driver class name as, `com.p6spy.engine.spy.P6SpyDriver`
- in the application root folder add the file `spy.properties` (this file contains P6Spy configurations); in this application, the logs will be outputed to console, but you can easy switch to a file; more details about P6Spy configurations can be found in documentation

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootP6spy/p6spy.png)

-----------------------------------------------------------------------------------------------------------------------    

81. **[How To Retry Transactions After `OptimisticLockException` Exception (`@Version`)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRetryVersionedOptimisticLocking)**

**Note:** Optimistic locking mechanism via `@Version` works for detached entities as well.

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic locking exception. When such exception occur, the application retry the corresponding transaction via [db-util](https://github.com/vladmihalcea/db-util) library developed by Vlad Mihalcea.

**Key points:**
- for Maven, in `pom.xml`, add the `db-util` dependency
- configure the `OptimisticConcurrencyControlAspect` bean
- mark the method (not annotated with `@Transactional`) that is prone to throw (or that calls a method that is prone to throw (this method can be annotated with `@Transactional`)) an optimistic locking exception with `@Retry(times = 10, on = OptimisticLockingFailureException.class)`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootRetryVersionedOptimisticLocking/Retry%20Optimistic%20Lock.png)

-----------------------------------------------------------------------------------------------------------------------    

82. **[How To Retry Transaction After `OptimisticLockException` Exception (Hibernate Version-less Optimistic Locking Mechanism)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRetryVersionlessOptimisticLocking)**

**Note:** Optimistic locking mechanism via Hibernate version-less doesn't work for detached entities (don't close the Persistent Context).

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic locking exception (e.g., in Spring Boot, `OptimisticLockingFailureException`) via Hibernate version-less optimistic locking. When such exception occur, the application retry the corresponding transaction via [db-util](https://github.com/vladmihalcea/db-util) library developed by Vlad Mihalcea.

**Key points:**
- for Maven, in `pom.xml`, add the `db-util` library dependency
- configure the `OptimisticConcurrencyControlAspect` bean
- annotate the corresponding entity (e.g., `Inventory`) with `@DynamicUpdate` and `@OptimisticLocking(type = OptimisticLockType.DIRTY)`
- mark the method (not annotated with `@Transactional`) that is prone to throw (or that calls a method that is prone to throw (this method can be annotated with `@Transactional`)) an optimistic locking exception with `@Retry(times = 10, on = OptimisticLockingFailureException.class)`

-----------------------------------------------------------------------------------------------------------------------    

83. **[How To Enrich DTO With Virtual Properties Via Spring Projections](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjectionsAndVirtualProperties)**

**Note:** You may also like to read the recipe, ["How To Create DTO Via Spring Data Projections"](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)

**Description:** This is an application sample that fetches only the needed columns from the database via Spring Data Projections (DTO) and enrich the result via virtual properties.

**Key points:**
- we fetch from the database only the author `name` and `age`
- in the projection interface, `AuthorNameAge`, use the `@Value` and Spring SpEL to point to a backing property from the domain model (in this case, the domain model property `age` is exposed via the virtual property `years`)
- in the projection interface, `AuthorNameAge`, use the `@Value` and Spring SpEL to enrich the result with two virtual properties that don't have a match in the domain model (in this case, `rank` and `books`)

**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaProjectionsAndVirtualProperties/dto%20spring%20projection%20and%20virtual%20properties.png)

-----------------------------------------------------------------------------------------------------------------------    

84. **[How To Use Query Creation Mechanism For JPA To Limit Result Size](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLimitResultSizeViaQueryCreator)**

**Description:** Spring Data comes with the query creation mechanism for JPA that is capable to interpret a query method name and convert it into a SQL query in the proper dialect. This is possible as long as we respect the naming conventions of this mechanism. This is an application that exploit this mechanism to write queries that limit the result size. Basically, the name of the query method instructs Spring Data how to add the `LIMIT` (or similar clauses depending on the RDBMS) clause to the generated SQL queries.

**Key points:**
- define a Spring Data regular repository (e.g., `AuthorRepository`)
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

85. **[How To Generate A Schema Via `schema-*.sql` In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSchemaSql)**

**Note:** As a rule, in real applications avoid generating schema via `hibernate.ddl-auto` or set it to `validate`. Use `schema-*.sql` file or better `Flyway` or `Liquibase` migration tools.

**Description:** This application is an example of using `schema-*.sql` to generate a schema(database) in MySQL.

**Key points:**
- in `application.properties`, set the JDBC URL (e.g., `spring.datasource.url=jdbc:mysql://localhost:3306/bookstoredb?createDatabaseIfNotExist=true`)
- in `application.properties`, disable DDL auto (just don't add explicitly the `hibernate.ddl-auto` setting)
- in `application.properties`, instruct Spring Boot to initialize the schema from `schema-mysql.sql` file 

-----------------------------------------------------------------------------------------------------------------------    

86. **[How To Generate Two Databases Via `schema-*.sql` And Match Entities To Them Via `@Table` In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootMatchEntitiesToTablesTwoSchemas)**

**Note:** As a rule, in real applications avoid generating schema via `hibernate.ddl-auto` or set it to `validate`. Use `schema-*.sql` file or better `Flyway` or `Liquibase`.

**Description:** This application is an example of using `schema-*.sql` to generate two databases in MySQL. The databases are matched at entity mapping via `@Table`.

**Key points:**
- in `application.properties`, set the JDBC URL without the database, e.g., `spring.datasource.url=jdbc:mysql://localhost:3306`
- in `application.properties`, disable DDL auto (just don't specify `hibernate.ddl-auto`)
- in `aaplication.properties`, instruct Spring Boot to initialize the schema from `schema-mysql.sql` file
- in `Author` entity, specify that the corresponding table (`author`) is in the database `authorsdb` via `@Table(schema="authorsdb")`
- in `Book` entity, specify that the corresponding table (`book`) is in the database `booksdb` via `@Table(schema="booksdb")`

**Output example:**
- Persisting a `Author` results in the following SQL: `insert into authorsdb.author (age, genre, name) values (?, ?, ?)`
- Persisting a `Book` results the following SQL: `insert into booksdb.book (isbn, title) values (?, ?)`

-----------------------------------------------------------------------------------------------------------------------    

87. **[How To Stream Result Set Via Spring Data In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootStreamAndMySQL)**

**Note:** For web-applications, pagination should be the way to go, not streaming. But, if you choose streaming then keep in mind the golden rule: keep th result set as small as posible. Also, keep in mind that the Execution Plan might not be as efficient as when using SQL-level pagination.

**Description:** This application is an example of streaming the result set via Spring Data and MySQL. This example can be adopted for databases that fetches the entire result set in a single roundtrip causing performance penalties.

**Key points:**
- rely on *forward-only* result set (default in Spring Data)
- rely on *read-only* statement (add `@Transactional(readOnly=true)`)
- set the fetch-size set (e.g. 30, or row-by-row; `Integer.MIN_VALUE` (recommended in MySQL))
- for MySQL, set `Statement` fetch-size to `Integer.MIN_VALUE`, or add `useCursorFetch=true` to the JDBC URL and set `Statement` fetch-size to a positive integer (e.g., 30)

-----------------------------------------------------------------------------------------------------------------------    

88. **[How To Migrate MySQL Database Using Flyway - MySQL Database Created Via `createDatabaseIfNotExist`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayMySQLQuick)**

**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate`. Rely on Flyway or Liquibase.

**Description:** This application is an example of migrating a MySQL database via Flyway when the database exists (it is created before migration via MySQL specific parameter, `createDatabaseIfNotExist=true`). 

**Key points:**
- for Maven, in `pom.xml`, add the Flyway dependency
- remove (disable) `spring.jpa.hibernate.ddl-auto`
- in `application.properties`, set the JDBC URL as follows: `jdbc:mysql://localhost:3306/bookstoredb?createDatabaseIfNotExist=true`
- each SQL file containing the schema update add it in `classpath:db/migration`
- each SQL file name it as `V1.1__Description.sql`, `V1.2__Description.sql`, ...

-----------------------------------------------------------------------------------------------------------------------    

89. **[How To Migrate MySQL Database Using Flyway - Database Created Via `spring.flyway.schemas`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayMySQLDatabase)**

**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate`. Rely on Flyway or Liquibase.

**Description:** This application is an example of migrating a MySQL database when the database is created by Flyway via `spring.flyway.schemas`. In this case, the entities should be annotated with `@Table(schema = "bookstoredb")` or `@Table(catalog = "bookstoredb")`. Here, the database name is `bookstoredb`.

**Key points:**
- for Maven, in `pom.xml`, add the Flyway dependency
- remove (disable) `spring.jpa.hibernate.ddl-auto`
- in `application.properties`, set the JDBC URL as follows: `jdbc:mysql://localhost:3306/`
- in `application.properties`, add `spring.flyway.schemas=bookstoredb`, where `bookstoredb` is the database that should be created by Flyway (feel free to add your own database name)
- each entity that should be stored in this database should be annotated with, `@Table(schema/catalog = "bookstoredb")`
- each SQL file containing the schema update add it in `classpath:db/migration`
- each SQL file name it as `V1.1__Description.sql`, `V1.2__Description.sql`, ...

**Output of migration history example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootFlywayMySQLDatabase/flyway_schema_history%20table.png)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

90. **[How To Auto-Create And Migrate Schemas For Two Data Sources (MySQL and PostgreSQL) Using Flyway](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayTwoVendors)**
 
**Note:** For production don't rely on `hibernate.ddl-auto` to create your schema. Remove (disable) `hibernate.ddl-auto` or set it to `validate`. Rely on Flyway or Liquibase.

**Description:** This application is an example of auto-creating and migrating schemas for MySQL and PostgreSQL. In addition, each data source uses its own HikariCP connection pool. In case of MySQL, where *schema*=*database*, we auto-create the schema (`authorsdb`) based on `createDatabaseIfNotExist=true`. In case of PostgreSQL, where a database can have multiple schemas, we use the default `postgres` database and auto-create in it the schema, `booksdb`. For this we rely on Flyway, which is capable to create a missing schema.

**Key points:**
- for Maven, in `pom.xml`, add the Flyway dependency
- remove (disable) `spring.jpa.hibernate.ddl-auto` or set it to `validate`
- in `application.properties`, configure the JDBC URL for MySQL as, `jdbc:mysql://localhost:3306/authorsdb?createDatabaseIfNotExist=true` and for PostgreSQL as, `jdbc:postgresql://localhost:5432/postgres?currentSchema=booksdb`
- in `application.properties`, set `spring.flyway.enabled=false` to disable default behavior
- programmatically create a `DataSource` for MySQL and one for PostgreSQL
- programmatically create a `FlywayDataSource` for MySQL and one for PostgreSQL
- programmatically create an `EntityManagerFactory` for MySQL and one for PostgreSQL
- for MySQL, place the migration SQLs files in `db\migration\mysql`
- for PostgreSQL, place the migration SQLs files in `db\migration\postgresql`    

-----------------------------------------------------------------------------------------------------------------------    

91. **[How To Auto-Create And Migrate Two Schemas In PostgreSQL Using Flyway](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayPostgreSqlTwoSchemas)**

**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate`. Rely on Flyway or Liquibase.

**Description:** This application is an example of auto-creating and migrating two schemas in PostgreSQL using Flyway. In addition, each data source uses its own HikariCP connection pool. In case of PostgreSQL, where a database can have multiple schemas, we use the default `postgres` database and auto-create two schemas, `authors` and `books`. For this we rely on Flyway, which is capable to create the missing schemas.

**Key points:**
- for Maven, in `pom.xml`, add the Flyway dependency
- remove (disable) `spring.jpa.hibernate.ddl-auto` or set it to `validate`
- in `application.properties`, configure the JDBC URL for `books` as `jdbc:postgresql://localhost:5432/postgres?currentSchema=books` and for `authors` as `jdbc:postgresql://localhost:5432/postgres?currentSchema=authors`
- in `application.properties`, set `spring.flyway.enabled=false` to disable default behavior
- programmatically create two `DataSource`, one for `books` and one for `authors`
- programmatically create two `FlywayDataSource`, one for `books` and one for `authors`
- programmatically create two `EntityManagerFactory`, one for `books` and one for `authors`
- for `books`, place the migration SQLs files in `db\migration\books`
- for `authors`, place the migration SQLs files in `db\migration\authors`   

-----------------------------------------------------------------------------------------------------------------------    

92. **[How To `JOIN FETCH` an `@ElementCollection`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootElementCollectionJoinFetch)**

**Description:** This application is an example applying `JOIN FETCH` to fetch an `@ElementCollection`.
 
**Key points:**
- by default, `@ElementCollection` is loaded lazy, keep it lazy
- use `JOIN FETCH` in the repository 

-----------------------------------------------------------------------------------------------------------------------    

93. **[How To Map An Entity To a Query (`@Subselect`) in a Spring Boot Application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSubselect)**

**Note:** Consider using `@Subselect` only if using DTO, DTO and extra queries, or map a database view to an entity is not a solution.

**Description:** This application is an example of mapping an entity to a query via Hibernate, `@Subselect`. Mainly, we have two entities in a bidirectional *one-to-many* association. An `Author` has wrote several `Book`. The idea is to write a *read-only* query to fetch from `Author` only some fields (e.g., DTO), but to have the posibility to call `getBooks()` and fetch the `Book` in a lazy manner as well. As you know, a classic DTO cannot be used, since such DTO is not managed and we cannot navigate the associations (don’t support any managed associations to other entities). Via Hibernate `@Subselect` we can map a *read-only* and *immutable* entity to a query. This time, we can lazy navigate the associations. 

**Key points:**
- define a new entity that contains only the needed fields from the `Author` (including association to `Book`)
- for these fields, define only getters
- mark the entity as `@Immutable` since no write operations are allowed
- flush pending state transitions for the used entities by `@Synchronize`
- use `@Subselect` to write the needed query, map an entity to an SQL query
     
-----------------------------------------------------------------------------------------------------------------------    

94. **[How To Use Hibernate Soft Deletes In A Spring Boot Application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSoftDeletes)**

**Description:** This application is an example of using Hibernate soft deletes in a Spring Boot application. 

**Key points:**
- define an `abstract` class `BaseEntity` with a field named `deleted`
- the entities (e.g., `Author` and `Book` entities) that should take advantage of soft deletes should extend `BaseEntity`
- these entities should be marked with Hibernate, `@Where` annotation like this: `@Where(clause = "deleted = false")`
- these entities should be marked with Hibernate, `@SQLDelete` annotation to trigger `UPDATE` SQLs in place of `DELETE` SQLs, as follows: `@SQLDelete(sql = "UPDATE author SET deleted = true WHERE id = ?")`
- for fetching all entities including those marked as deleted or for fetching only the entities marked as deleted we need to rely on SQL native queries

**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSoftDeletes/soft%20deletes.png)

-----------------------------------------------------------------------------------------------------------------------    

95. **[How To Programmatically Customize HikariCP Settings Via `DataSourceBuilder`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderProgHikariCPKickoff)**

**If you use the `spring-boot-starter-jdbc` or `spring-boot-starter-data-jpa` "starters", you automatically get a dependency to HikariCP**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up HikariCP via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purposes, the application uses an `ExecutorService` for simulating concurrent users. Check the HickariCP report revealing the connection pool status.

**Key points:**
- write a `@Bean` that returns the `DataSource` programmatically

-----------------------------------------------------------------------------------------------------------------------    

96. **[How To Setup Spring Data JPA Auditing](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAudit)**
 
**Description:** Auditing is useful for maintaining history records. This can later help us in tracking user activities. 
 
**Key points:**
- create an `abstract` base entity (e.g., `BaseEntity`) and annotate it with `@MappedSuperclass` and `@EntityListeners({AuditingEntityListener.class})`
- in this base entity, add the following fields that will be automatically persisted:\
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- `@CreatedDate protected LocalDateTime created;`\
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- `@LastModifiedDate protected LocalDateTime lastModified;`\
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- `@CreatedBy protected U createdBy;`\
     &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- `@LastModifiedBy protected U lastModifiedBy;`
- enable auditing via `@EnableJpaAuditing(auditorAwareRef = "auditorAware")`
- provide an implementation for `AuditorAware` (this is needed for persisting the user that performed the modification; use Spring Security to return the currently logged-in user)
- expose this implementation via `@Bean`
- entites that should be audited should extend the base entity
- store the date-time in database in UTC
     
-----------------------------------------------------------------------------------------------------------------------

97. **[Hibernate Envers Auditing (`spring.jpa.hibernate.ddl-auto=create`)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnvers)**
 
**Description:** Auditing is useful for maintaining history records. This can later help us in tracking user activities. 
 
**Key points:**
- each entity that should be audited should be annotated with `@Audited`
- optionally, annotate entities with `@AuditTable` to rename the table used for auditing
- rely on `ValidityAuditStrategy` for fast database reads, but slower writes (slower than the default `DefaultAuditStrategy`)

-----------------------------------------------------------------------------------------------------------------------

98. **[Attributes Lazy Loading Via Subentities](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSubentities)**
 
**Description:** By default, the attributes of an entity are loaded eager (all at once). This application is an alternative to *How To Use Hibernate Attribute Lazy Loading* from [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingBasic). This application uses a base class to isolate the attributes that should be loaded eagerly and subentities (entities that extends the base class) for isolating the attributes that should be loaded on demand.

<a href="#"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSubentities/attributes%20lazy%20loading%20via%20subentites.png" align="center" height="150" width="500" ></a>

**Key points:**
- create the base class (this is not an entity), `BaseAuthor`,  and annotate it with `@MappedSuperclass`
- create `AuthorShallow` subentity of `BaseAuthor` and don't add any attribute in it (this will inherit the attributes from the superclass)
- create `AuthorDeep` subentity of `BaseAuthor` and add to it the attributes that should be loaded on demand (e.g., `avatar`)
- map both subentities to the same table via `@Table(name = "author")`
- provide the typical repositories, `AuthorShallowRepository` and `AuthorDeepRepository`
     
**Run the following requests (via BookstoreController):**
- fetch all authors shallow (without avatars): `localhost:8080/authors/shallow`
- fetch all authors deep (with avatars): `localhost:8080/authors/deep`

**Check as well:**
- [Attribute Lazy Loading (basic)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootAttributeLazyLoadingBasic)
- [Default Values For Lazy Loaded Attributes](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingDefaultValues)
- [Attribute Lazy Loading And Jackson Serialization](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingJacksonSerialization)
     

-----------------------------------------------------------------------------------------------------------------------

99. **[DTO Via Constructor And Spring Data Query Builder Mechanism](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoConstructor)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTO allows us to extract only the needed data. In this application we rely on constructor and Spring Data Query Builder Mechanism.
 
**Key points:**
- write a proper constructor in the DTO class
- rely on Spring Data Query Builder Mechanism to write the SQL
- for using Spring Data Projections check this [item](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections) 

**See also:**\
[Dto Via Constructor Expression and JPQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoConstructorExpression)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

100. **[How To Page The Result Set of a `JOIN`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinPagination)**

**Description:** Using `JOIN` is very useful for fetching DTOs (data that is never modified, not in the current or subsequent requests). For example, consider two entities, `Author` and `Book` in a lazy-bidirectional `@OneToMany` association. And, we want to fetch a subset of columns from the parent table (`author`) and a subset of columns from the child table (`book`). This job is a perfect fit for `JOIN` which can pick up columns from different tables and build a *raw* result set. This way we fetch only the needed data. Moreover, we may want to serve the result set in pages (e.g., via `LIMIT`). This application contains several approaches for accomplishing this task with offset pagination.

**Key points:**
- pagination via `Page` (with `SELECT COUNT` and `COUNT(*) OVER()` window function)
- pagination via `Slice` and `List`
- pagination via `DENSE_RANK()` for avoiding the truncation of the result set (an author can be fetched with only a subset of his books)

-----------------------------------------------------------------------------------------------------------------------

101. **[`LEFT JOIN FETCH`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLeftJoinFetch)**

**See also:**
- [How To Avoid LazyInitializationException Via JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinFetch)
- [JOIN VS. JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinVSJoinFetch)

**Description:** Let's assume that we have two entities engaged in a one-to-many (or many-to-many) lazy bidirectional (or unidirectional) relationship (e.g., `Author` has more `Book`). And, we want to trigger a single `SELECT` that fetches all `Author` and the corresponding `Book`. This is a job for `JOIN FETCH` which is converted behind the scene into a `INNER JOIN`. Being an `INNER JOIN`, the SQL will return only `Author` that have `Book`. If we want to return all `Author`, including those that doesn't have `Book`, then we can rely on `LEFT JOIN FETCH`. Similar, we can fetch all `Book`, including those with no registered `Author`. This can be done via `LEFT JOIN FETCH` or `LEFT JOIN`.

**Key points:**
- define two related entities (e.g., `Author` and `Book` in a one-to-many lazy bidirectional relationship)
- write a JPQL `LEFT JOIN FETCH` to fetch all authors and books (fetch authors even if they don't have registered books)
- write a JPQL `LEFT JOIN FETCH` to fetch all books and authors (fetch books even if they don't have registered authors)

-----------------------------------------------------------------------------------------------------------------------

102. **[`JOIN` VS. `JOIN FETCH`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinVSJoinFetch)**

**See also:**
- [How To Avoid LazyInitializationException Via JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinFetch)
- [LEFT JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLeftJoinFetch)
     
**Description:** This is an application meant to reveal the differences between `JOIN` and `JOIN FETCH`. The important thing to keep in mind is that, in case of `LAZY` fetching, `JOIN` will not be capable to initialize the associated collections along with their parent objects using a single SQL `SELECT`.  On the other hand, `JOIN FETCH` is capable to accomplish this kind of task. But, don't underestimate `JOIN`, because `JOIN` is the proper choice when we need to combine/join the columns of two (or more) tables in the same query, but we don't need to initialize the associated collections on the returned entity (e.g., very useful for fetching DTO).

**Key points:**
- define two related entities (e.g., `Author` and `Book` in a one-to-many lazy-bidirectional relationship)
- write a JPQL `JOIN` and `JOIN FETCH` to fetch an author including his books
- write a JPQL `JOIN` to fetch a book (1)
- write a JPQL `JOIN` to fetch a book including its author (2)
- write a `JOIN FETCH` to fetch a book including its author
     
**Notice that:**
- via `JOIN`, fetching `Book` of `Author` requires additional `SELECT` statements being prone to N+1 performance penalty
- via `JOIN` (1), fetching `Author` of `Book` requires additional `SELECT` statements being prone to N+1 performance penalty
- via `JOIN` (2), fetching `Author` of `Book` works exactly as `JOIN FETCH` (requires a single `SELECT`)
- via `JOIN FETCH`, fetching each `Author` of a `Book` requires a single `SELECT`

-----------------------------------------------------------------------------------------------------------------------

103. **[Entity Inside Spring Projection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoEntityViaProjection)**
     
**Description:** If, for some reason, you need an entity in your Spring projection (DTO), then this application shows you how to do it via an example. In this case, there are two entities, `Author` and `Book`, involved in a lazy bidirectional one-to-many association  (it can be other association as well, or even no materialized association). And, we want to fetch in a Spring projection the authors as entities, `Author`, and the `title` of the books.

**Key points:**
- define two related entities (e.g., `Author` and `Book` in a one-to-many lazy bidirectional relationship)
- define the proper Spring projection having `public Author getAuthor()` and `public String getTitle()`
- write a JPQL to fetch data

-----------------------------------------------------------------------------------------------------------------------

104. **[Entity Inside Spring Projection (no association)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoEntityViaProjectionNoAssociation)**
     
**Description:** If, for some reason, you need an entity in your Spring projection (DTO), then this application shows you how to do it via an example. In this case, there are two entities, `Author` and `Book`, that have no materialized association between them, but, they share the `genre` attribute. We use this attribute to join authors with books via JPQL. And, we want to fetch in a Spring projection the authors as entities, `Author`, and the `title` of the books.

**Key points:**
- define two unrelated entities (e.g., `Author` and `Book`)
- define the proper Spring projection having `public Author getAuthor()` and `public String getTitle()`
- write a JPQL to fetch data

-----------------------------------------------------------------------------------------------------------------------

105. **[Avoid Entity In DTO Via Constructor Expression (no association)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAvoidEntityInDtoViaConstructor)**
     
**Description:** Let's assume that we have two entities, `Author` and `Book`. There is no materialized association between them, but, both entities shares an attribute named, `genre`. We want to use this attribute to join the tables corresponding to `Author` and `Book`, and fetch the result in a DTO. The result should contain the `Author` entity and only the `title` attribute from `Book`. Well, when you are in a scenario as here, it is strongly advisable to avoid fetching the DTO via *constructor expression*. This approach cannot fetch the data in a single `SELECT`, and is prone to N+1. Way better than this consists of using Spring projections, JPA `Tuple` or even Hibernate `ResultTransformer`. These approaches will fetch the data in a single `SELECT`. This application is a **DON'T DO THIS** example. Check the number of queries needed for fetching the data. In place, do it as here: [Entity Inside Spring Projection (no association)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoEntityViaProjectionNoAssociation).

-----------------------------------------------------------------------------------------------------------------------

106. **[How To DTO an `@ElementCollection`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoElementCollection)**

**Description:** This application is an example of fetching a DTO that includes attributes from an `@ElementCollection`.
 
**Key points:**
- by default, `@ElementCollection` is loaded lazy, keep it lazy
- use a Spring projection and `JOIN` in the repository  

-----------------------------------------------------------------------------------------------------------------------

107. **[Ordering The `Set` Of Associated Entities In `@ManyToMany` Association Via `@OrderBy`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootManyToManySetAndOrderBy)**

**Description:** In case of `@ManyToMany` association, we always should rely on `Set` (not on `List`) for mapping the collection of associated entities (entities of the other parent-side). Why? Well, please see [Prefer Set Instead of List in @ManyToMany Relationships](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootManyToManyBidirectionalListVsSet). But, is well-known that `HashSet` doesn't have a predefined entry order of elements. If this is an issue then this application relies on `@OrderBy` which adds an `ORDER BY` clause in the SQL statement. The database will handle the ordering. Further, Hibernate will preserve the order via a `LinkedHashSet`.

This application uses two entities, `Author` and `Book`, involved in a lazy bidirectional many-to-many relationship. First, we fetch a `Book` by title. Further, we call `getAuthors()` to fetch the authors of this book. The fetched authors are ordered descending by name. The ordering is done by the database as a result of adding `@OrderBy("name DESC")`, and is preserved by Hibernate.

**Key points:**
- ask the database to handle ordering and Hibernate to preserve this order via `@OrderBy`
- this works with `HashSet`, but doesn't provide consistency across all transition states (e.g., *transient* state)
- for consistency across the *transient* state as well, consider using explicitly `LinkedHashSet` instead of `HashSet`

**Note:** Alternatively, we can use `@OrderColumn`. This gets materialized in an additional column in the junction table. This is needed for maintaining a permanent ordering of the related data.

-----------------------------------------------------------------------------------------------------------------------

108. **[Versioned Optimistic Locking And Detached Entities Sample](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootVersionedOptimisticLockingAndDettachedEntity)**

**Description:** This is a sample application that shows how versioned (`@Version`) optimistic locking and detached entity works. Running the application will result in an optimistic locking specific exception (e.g., the Spring Boot specific, `OptimisticLockingFailureException`).

**Key points:**
- in a transaction, fetch an entity via `findById(1L)`; commit transaction and close the Persistence Context
- in a second transaction, fetch another entity via `findById(1L)` and update it; commit the transaction and close the Persistence Context
- outside transactional context, update the detached entity (fetched in the first transaction)
- in a third transaction, call `save()` and pass to it the detached entity; trying to merge (`EntityManager.merge()`) the entity will end up in an optimistic locking exception since the version of the detached and just loaded entity don't match

-----------------------------------------------------------------------------------------------------------------------

109. **[How To Simulate `OptimisticLockException` Shaped Via `@Version`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSimulateVersionedOptimisticLocking)**

**Note:** Optimistic locking via `@Version` works for detached entities as well.

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic locking exception. So, running the application should end up with a Spring specific `ObjectOptimisticLockingFailureException` exception.

**Key points:**
- set up versioned optimistic locking mechanism
- rely on two concurrent threads that call the same `@Transactional` method used for updating data

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

110. **[How To Retry Transaction Via `TransactionTemplate` After `OptimisticLockException` Exception (`@Version`)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRetryVersionedOptimisticLockingTT)**

**Note:** Optimistic locking via `@Version` works for detached entities as well.

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic locking exception. When such exception occurs, the application retry the corresponding transaction via [db-util](https://github.com/vladmihalcea/db-util) library developed by Vlad Mihalcea.

**Key points:**
- in `pom.xml`, add the `db-util` dependency
- configure the `OptimisticConcurrencyControlAspect` bean
- rely on `TransactionTemplate`

-----------------------------------------------------------------------------------------------------------------------

111. **[How To Simulate `OptimisticLockException` In Version-less Optimistic Locking](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSimulateVersionlessOptimisticLocking)**

**Note:** Version-less optimistic locking doesn't work for detached entities (do not close the Persistence Context).

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic locking exception. So, running the application should end up with a Spring specific `ObjectOptimisticLockingFailureException` exception.

**Key points:**
- set up the version-less optimistic locking mechanism
- rely on two concurrent threads that call the same a `@Transactional` method used for updating data

-----------------------------------------------------------------------------------------------------------------------

112. **[How To Retry Transaction Via `TransactionTemplate` After `OptimisticLockException` Shaped Via Hibernate Version-less Optimistic Locking Mechanism](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRetryVersionlessOptimisticLockingTT)**

**Note:** Version-less optimistic locking doesn't work for detached entities (do not close the Persistence Context).

**Description:** This is a Spring Boot application that simulates a scenario that leads to an optimistic locking exception. When such exception occur, the application retry the corresponding transaction via [db-util](https://github.com/vladmihalcea/db-util) library developed by Vlad Mihalcea.

**Key points:**
- for Maven, in `pom.xml`, add the `db-util` dependency
- configure the `OptimisticConcurrencyControlAspect` bean
- rely on `TransactionTemplate`

-----------------------------------------------------------------------------------------------------------------------

113. **[HTTP Long Conversation Via Versioned Optimistic Locking And Detached Entities In The HTTP Session](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHTTPLongConversationDetachedEntity)**

**Description:** This is a sample application that shows how to take advantage of versioned optimistic locking and detached entities in HTTP long conversations. The climax consists of storing the detached entities across multiple HTTP requests. Commonly, this can be accomplished via HTTP session. 

**Key points:**
- prepare the entity via `@Version`
- rely on `@SessionAttributes` for storing the detached entities   

**Sample output (check the message caused by optimistic locking exception):**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHTTPLongConversationDetachedEntity/http%20long%20conversations%20detached%20entity%20ole.png)

-----------------------------------------------------------------------------------------------------------------------

114. **[Filter Association Via Hibernate `@Where`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFilterAssociation)**

**Note:** Rely on this approach only if you simply cannot use `JOIN FETCH WHERE` or `@NamedEntityGraph`.

**Description:** This application is a sample of using Hibernate `@Where` for filtering associations. 

**Key points:**
- use `@Where(clause = "condition to be met")` in entity (check the `Author` entity)

-----------------------------------------------------------------------------------------------------------------------

115. **[Batch Inserts In Spring Boot Style](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsSpringStyle)**

**Description:** Batch inserts (in MySQL) in Spring Boot style.

**Key points:**
- in `application.properties` set `spring.jpa.properties.hibernate.jdbc.batch_size`
- in `application.properties` set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)
- in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)
- in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)
- in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))
- in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts
- in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since the Hibernate `IDENTITY` will cause insert batching to be disabled
- if is not needed then ensure that Second Level Cache is disabled via `spring.jpa.properties.hibernate.cache.use_second_level_cache=false`
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsSpringStyle/batch%20inserts%20in%20spring%20boot%20style.png)

-----------------------------------------------------------------------------------------------------------------------

116. **[Offset Pagination - Trigger `COUNT(*) OVER` And Return `Page<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageEntityOffsetPaginationExtraColumnWF)**

**Description:** Typically, in offset pagination, there is one query needed for fetching the data and one for counting the total number of records. But, we can fetch this information in a single database rountrip via a `SELECT COUNT` subquery nested in the main `SELECT`. Even better, for databases vendors that support *Window Functions* there is a solution relying on `COUNT(*) OVER()` as in this application that uses this window function in a native query against MySQL 8. So, prefer this one instead of `SELECT COUNT` subquery.This application fetches data as `Page<entity>` via Spring Boot offset pagination, but, if the fetched data is *read-only*, then rely on `Page<dto>` as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageDtoOffsetPaginationWF). 

**Key points:**
- write a repository that extends `PagingAndSortingRepository`
- in the entity, add an extra column for representing the total number of records and annotate it as `@Column(insertable = false, updatable = false)`
- fetch data via a native query (that includes counting) into a `List<entity>`
- use the fetched `List<entity>` and `Pageable` to create a `Page<entity>`

-----------------------------------------------------------------------------------------------------------------------

117. **[Offset Pagination - Trigger `SELECT COUNT` Subquery And Return `List<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListEntityOffsetPaginationExtraColumn)**
 
**Description:** This application fetches data as `List<entity>` via Spring Boot offset pagination. The `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. Therefore, there will be a single database roundtrip instead of two (typically, one query is needed for fetching the data and one for counting the total number of records).

**Key points:**
- write a repository that extends `PagingAndSortingRepository`
- in the `entity`, add an extra column for representing the total number of records and annotate it as `@Column(insertable = false, updatable = false)`
- fetch data via a native query (that includes `SELECT COUNT` subquery) into a `List<entity>`

-----------------------------------------------------------------------------------------------------------------------

118. **[Offset Pagination - Trigger `SELECT COUNT` Subquery And Return `List<projection>` That Maps Entities And The Total Number Of Records Via Projection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListEntityOffsetPaginationProjection)**
   
**Description:** This application fetches data as `List<projection>` via Spring Boot offset pagination. The projection maps the entity and the total number of records. This information is fetched in a single database rountrip because the `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. Therefore, there will be a single database roundtrip instead of two (typically, there is one query needed for fetching the data and one for counting the total number of records). Use this approch only if the fetched data is not *read-only*. Otherwise, prefer `List<dto>` as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListDtoOffsetPagination).

**Key points:**
- write a Spring projection that maps the entity and the total number of records
- write a repository that extends `PagingAndSortingRepository`
- fetch data via a JPQL query (that includes `SELECT COUNT` subquery) into a `List<projection>`

-----------------------------------------------------------------------------------------------------------------------

119. **[Offset Pagination - Trigger `COUNT(*) OVER` And Return `List<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListEntityOffsetPaginationExtraColumnWF)**
 
**Description:** Typically, in offset pagination, there is one query needed for fetching the data and one for counting the total number of records. But, we can fetch this information in a single database rountrip via a `SELECT COUNT` subquery nested in the main `SELECT`. Even better, for databases vendors that support *Window Functions* there is a solution relying on `COUNT(*) OVER()` as in this application that uses this window function in a native query against MySQL 8. So, prefer this one instead of `SELECT COUNT` subquery.This application fetches data as `List<entity>` via Spring Boot offset pagination, but, if the fetched data is *read-only*, then rely on `List<dto>` as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootListDtoOffsetPaginationWF). 

**Key points:**
- write a repository that extends `PagingAndSortingRepository`
- in the `entity`, add an extra column for representing the total number of records and annotate it as `@Column(insertable = false, updatable = false)`
- fetch data via a native query (that includes `COUNT(*) OVER` subquery) into a `List<entity>`  

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

120. **[Offset Pagination - Trigger `SELECT COUNT` Subquery And Return `Page<entity>` Via Extra Column](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageEntityOffsetPaginationExtraColumn)**

**Description:** This application fetches data as `Page<entity>` via Spring Boot offset pagination. Use this only if the fetched data will be modified. Otherwise, fetch `Page<dto>` as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageDtoOffsetPagination). The `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. Therefore, there will be a single database roundtrip instead of two (typically, there is one query needed for fetching the data and one for counting the total number of records).

**Key points:**
- write a repository that extends `PagingAndSortingRepository`
- in the entity, add an extra column for representing the total number of records and annotate it as `@Column(insertable = false, updatable = false)`
- fetch data via a native query (that includes counting) into a `List<entity>`
- use the fetched `List<entity>` and `Pageable` to create a `Page<entity>`

-----------------------------------------------------------------------------------------------------------------------

121. **[Offset Pagination - Trigger `SELECT COUNT` Subquery And Return `Page<projection>` That Maps Entities And The Total Number Of Records Via Projection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageEntityOffsetPaginationProjection)**
 
**Description:** This application fetches data as `Page<projection>` via Spring Boot offset pagination. The projection maps the entity and the total number of records. This information is fetched in a single database rountrip because the `SELECT COUNT` triggered for counting the total number of records is a subquery of the main `SELECT`. 

**Key points:**
- define a Spring projection that maps the entity and the total number of records
- write a repository that extends `PagingAndSortingRepository`
- fetch data via a JPQL query into a `List<projection>`
- use the fetched `List<projection>` and `Pageable` to create a `Page<projection>`

-----------------------------------------------------------------------------------------------------------------------

122. **[Offset Pagination - Trigger `COUNT(*) OVER` And Return `Page<dto>`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPageDtoOffsetPaginationWF)**

**Description:** Typically, in offset pagination, there is one query needed for fetching the data and one for counting the total number of records. But, we can fetch this information in a single database rountrip via a `SELECT COUNT` subquery nested in the main `SELECT`. Even better, for databases vendors that support *Window Functions* there is a solution relying on `COUNT(*) OVER()` as in this application that uses this window function in a native query against MySQL 8. So, prefer this one instead of `SELECT COUNT` subquery. This application return a `Page<dto>`.

**Key points:**
- create a Spring projection (DTO) to contains getters only for the columns that should be fetched
- write a repository that extends `PagingAndSortingRepository`
- fetch data via a native query (that includes counting) into a `List<dto>`
- use the fetched `List<dto>` and `Pageable` to create a `Page<dto>`

**Example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootListDtoOffsetPaginationWF/offset%20pagination%20via%20window%20function.png)

-----------------------------------------------------------------------------------------------------------------------

123. **[How To Fetch `Slice<entity>`/`Slice<dto>` Via `fetchAll`/`fetchAllDto`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSliceAllViaFetchAll)**

**Story**: Spring Boot provides an *offset* based built-in paging mechanism that returns a `Page` or `Slice`. Each of these APIs represents a page of data and some metadata. The main difference is that `Page` contains the total number of records, while `Slice` can only tell if there is another page available. For `Page`, Spring Boot provides a `findAll()` method capable to take as arguments a `Pageable` and/or a `Specification` or `Example`.  In order to create a `Page` that contains the total number of records, this method triggers an `SELECT COUNT` extra-query next to the query used to fetch the data of the current page . This can be a performance penalty since the `SELECT COUNT` query is triggered every time we request a page. In order to avoid this extra-query, Spring Boot provides a more relaxed API, the `Slice` API. Using `Slice` instead of `Page` removes the need of this extra `SELECT COUNT` query and returns the page (records) and some metadata without the total number of records. So, while `Slice` doesn't know the total number of records, it still can tell if there is another page available after the current one or this is the last page. The problem is that `Slice` work fine for queries containing the SQL, `WHERE` clause (including those that uses the query builder mechanism built into Spring Data), but it **doesn't work** for `findAll()`. This method will still return a `Page` instead of `Slice` therefore the `SELECT COUNT` query is triggered for `Slice<T> findAll(...);`.

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

**Description:** This application is a proof of concept for using Spring Projections(DTO) and inclusive full joins written in native SQL (for MySQL).

**Key points:**
- define two entities (e.g., `Author` and `Book` in a lazy bidirectional `@OneToMany` relationship)
- populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)
- write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)
- write inclusive full joins queries using native SQL

-----------------------------------------------------------------------------------------------------------------------

125. **[How To Declare Immutable Entities And Store Them In Second Level Cache (e.g., `EhCache`)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootImmutableEntity)**

**Description:** This application is a sample of declaring an immutable entity. Moreover, the immutable entity will be stored in Second Level Cache via `EhCache` implementation.

**Key points of declaring an immutable entity:**
- annotate the entity with `@Immutable (org.hibernate.annotations.Immutable)`
- avoid any kind of associations
- set `hibernate.cache.use_reference_entries configuration` to `true`
     
----------------------------------------------------------------------------------------------------------------------

126. **[How To Programmatically Customize HikariCP Settings Via `DataSourceBuilder`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceBuilderProgHikariCPKickoff)**

**If you use the `spring-boot-starter-jdbc` or `spring-boot-starter-data-jpa` "starters", you automatically get a dependency to HikariCP**

**Note:** The best way to tune the connection pool parameters consist in using [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) by Vlad Mihalcea. Via [Flexy Pool](https://github.com/vladmihalcea/flexy-pool) you can find the optim settings that sustain high-performance of your connection pool.

**Description:** This is a kickoff application that set up HikariCP via `DataSourceBuilder`. The `jdbcUrl` is set up for a MySQL database. For testing purposes, the application uses an `ExecutorService` for simulating concurrent users. Check the HickariCP report revealing the connection pool status.

**Key points:**
- write a `@Bean` that returns the `DataSource` programmatically

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootHikariCPPropertiesKickoff/HikariCP%20trace%20log.png)

----------------------------------------------------------------------------------------------------------------------

127. **[How To Use Hibernate `@NaturalIdCache` For Skipping The Entity Identifier Retrieval](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNaturalIdCache)**

**Description:** This is a SpringBoot - MySQL application that maps a natural business key using Hibernate `@NaturalId`. This implementation allows us to use `@NaturalId` as it was provided by Spring. Moreover, this application uses Second Level Cache (`EhCache`) and `@NaturalIdCache` for skipping the entity identifier retrieval from the database.

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

**Key points:**
- annotate the non-persistent field and property with `@Transient`
- define a method annotated with `@PostLoad` that calculates this non-persistent property based on the persistent entity attributes

----------------------------------------------------------------------------------------------------------------------

129. **[How To Calculate Entity Persistent Property Via Hibernate `@Generated`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCalculatePropertyGenerated)**
 
**Description:** This application is an example of calculating an entity persistent property at `INSERT` and/or `UPDATE ` time via Hibernate, `@Generated`. 

**Key points:**

 **Calculate at `INSERT` time:**
- annotate the corresponding persistent field with `@Generated(value = GenerationTime.INSERT)`
- annotate the corresponding persistent field with `@Column(insertable = false)`

 **Calculate at `INSERT` and `UPDATE` time:**
- annotate the corresponding persistent field with `@Generated(value = GenerationTime.ALWAYS)`
- annotate the corresponding persistent field with `@Column(insertable = false, updatable = false)`

**Further, apply:**

 **Method 1:**
- if the database schema is generated via JPA annotations (not recommended) then use `columnDefinition` element of `@Column` to specify as an SQL query expression the formula for calculating the persistent property

 **Method 2:**
 - if the database schema is not generated via JPA annotations (recommended way) then add the formula as part of schema in `CREATE TABLE`
     
**Note:** In production, you should not rely on `columnDefinition`. You should disable `hibernate.ddl-auto` (by omitting it) or set it to `validate`, and add the SQL query expression in `CREATE TABLE` (in this application, check the `discount` column in `CREATE TABLE`, file `schema-sql.sql`). Nevertheless, not even `schema-sql.sql` is ok in production. The best way is to rely on Flyway or Liquibase.
         
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

130. **[How To Calculate Non-Persistent Property via Hibernate `@Formula`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCalculatePropertyFormula)**
 
**Description:** This application is an example of calculating a non-persistent property of an entity based on the persistent entity attributes. In this case, we will use Hibernate, `@Formula`.

**Key points:**
- annotate the non-persistent property with `@Transient`
- annotate the non-persistent field with `@Formula`
- as the value of `@Formula` add the SQL query expression that calculates this non-persistent property based on the persistent entity attributes

----------------------------------------------------------------------------------------------------------------------

131. **[How To Add `created`, `createdBy`, `lastModified` And `lastModifiedBy` In Entities Via Hibernate](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTimestampGeneration)**
 
**Note:** The same thing can be obtained via Spring Data JPA auditing as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAudit).

**Description:** This application is an example of adding in an entity the fields, `created`, `createdBy`, `lastModified` and `lastModifiedBy` via Hibernate support. These fields will be automatically generated/populated.

**Key points:**
- write an `abstract` class (e.g., `BaseEntity`) annotated with `@MappedSuperclass`
- in this `abstract` class, define a field named `created` and annotate it with the built-in `@CreationTimestamp` annotation
- in this `abstract` class, define a field named `lastModified` and annotate it with the built-in `@UpdateTimestamp` annotation
- in this `abstract` class, define a field named `createdBy` and annotate it with the `@CreatedBy` annotation
- in this `abstract` class, define a field named `lastModifiedBy` and annotate it with the `@ModifiedBy` annotation
- implement the `@CreatedBy` annotation via `AnnotationValueGeneration`
- implement the `@ModifiedBy` annotation via `AnnotationValueGeneration`
- every entity that want to take advantage of `created`, `createdBy`, `lastModified` and `lastModifiedBy` will extend the `BaseEntity`
- store the date-time in UTC

----------------------------------------------------------------------------------------------------------------------

132. **[Hibernate Envers Auditing (`schema-mysql.sql`)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnversSchemaSql)**
 
**Description:** Auditing is useful for maintaining history records. This can later help us in tracking user activities. 
 
**Key points:**
- each entity that should be audited should be annotated with `@Audited`
- optionally, annotate entities with `@AuditTable` to rename the table used for auditing
- rely on `ValidityAuditStrategy` for fast database reads, but slower writes (slower than the default `DefaultAuditStrategy`)
- remove (disable) `spring.jpa.hibernate.ddl-auto` or set it to `validate` for avoiding schema generated from JPA annotations
- create `schema-mysql.sql` and provide the SQL statements needed by Hibernate Envers
- if the schema is not automatically found, then point it via `spring.jpa.properties.org.hibernate.envers.default_catalog` for MySQL or `spring.jpa.properties.org.hibernate.envers.default_schema` for the rest

----------------------------------------------------------------------------------------------------------------------

133. **[How To Programmatically Setup Flyway And MySQL `DataSource`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayMySQLProg)**
 
**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate`. Rely on Flyway or Liquibase.

**Description:** This application is a kickoff for setting Flyway and MySQL `DataSource` programmatically.

**Key points:**
- for Maven, in `pom.xml`, add the Flyway dependency
- remove (disable) `spring.jpa.hibernate.ddl-auto` or set it to `validate`
- configure `DataSource` and Flyway programmatically

----------------------------------------------------------------------------------------------------------------------

134. **[How To Migrate PostgreSQL Database Using Flyway - Use The Default Database `postgres` And Schema `public`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayPostgreSQLQuick)**

**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate`. Rely on Flyway or Liquibase.

**Description:** This application is an example of migrating a PostgreSQL database via Flyway for the default database `postgres` and schema `public`. 

**Key points:**
- for Maven, in `pom.xml`, add the Flyway dependency
- remove (disable) `spring.jpa.hibernate.ddl-auto` or set it to `validate`
- in `application.properties`, set the JDBC URL as follows: `jdbc:postgresql://localhost:5432/postgres`
- each SQL file containing the schema update add it in `classpath:db/migration`
- each SQL file name it as `V1.1__Description.sql`, `V1.2__Description.sql`, ...

----------------------------------------------------------------------------------------------------------------------

135. **[How To Migrate Schema Using Flyway In PostgreSQL - Use The Default Database `postgres` And Schema Created Via `spring.flyway.schemas`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayPostgreSqlSchema)**

**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate`. Rely on Flyway or Liquibase.

**Description:** This application is an example of migrating a schema (`bookstore`) created by Flyway via `spring.flyway.schemas` in the default `postgres` database. In this case, the entities should be annotated with `@Table(schema = "bookstore")`.

**Key points:**
- for Maven, in `pom.xml`, add the Flyway dependency
- remove (disable) `spring.jpa.hibernate.ddl-auto` or set it to `validate`
- in `application.properties`, set the JDBC URL as follows: `jdbc:postgresql://localhost:5432/postgres`
- in `application.properties`, add `spring.flyway.schemas=bookstore`, where `bookstore` is the schema that should be created by Flyway in the `postgres` database (feel free to add your own database name)
- each entity that should be stored in this database should be annotated with, `@Table(schema = "bookstore")`
- each SQL file containing the schema update add it in `classpath:db/migration`
- each SQL file name it as `V1.1__Description.sql`, `V1.2__Description.sql`, ...

----------------------------------------------------------------------------------------------------------------------

136. **[How To Programmatically Setup Flyway And PostgreSQL `DataSource`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayPostgreSQLProg)**
 
**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate`. Rely on Flyway or Liquibase.

**Description:** This application is a kickoff for setting Flyway and PostgreSQL `DataSource` programmatically.

**Key points:**
- for Maven, in `pom.xml`, add the Flyway dependency
- remove (disable) `spring.jpa.hibernate.ddl-auto` or set it to `validate`
- configure `DataSource` and Flyway programmatically

----------------------------------------------------------------------------------------------------------------------

137. **[How To Auto-Create And Migrate Two Databases In MySQL Using Flyway](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayMySQLTwoDatabases)**

**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate`. Rely on Flyway or Liquibase.

**Description:** This application is an example of auto-creating and migrating two databases in MySQL using Flyway. In addition, each data source uses its own HikariCP connection pool. In case of MySQL, where a database is the same thing with schema, we create two databases, `authorsdb` and `booksdb`.

**Key points:**
- for Maven, in `pom.xml`, add the Flyway dependency
- remove (disable) `spring.jpa.hibernate.ddl-auto` or set it to `validate`
- in `application.properties`, configure the JDBC URL for `booksdb` as `jdbc:mysql://localhost:3306/booksdb?createDatabaseIfNotExist=true` and for `authorsdb` as `jdbc:mysql://localhost:3306/authorsdb?createDatabaseIfNotExist=true`
- in `application.properties`, set `spring.flyway.enabled=false` to disable default behavior
- programmatically create two `DataSource`, one for `booksdb` and one for `authorsdb`
- programmatically create two `FlywayDataSource`, one for `booksdb` and one for `authorsdb`
- programmatically create two `EntityManagerFactory`, one for `booksdb` and one for `authorsdb`
- for `booksdb`, place the migration SQLs files in `db\migration\booksdb`
- for `authorsdb`, place the migration SQLs files in `db\migration\authorsdb`    

----------------------------------------------------------------------------------------------------------------------

138. **[Hibernate `hi/lo` Algorithm And External Systems Issue](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHiLoIssue)**

**Description:** This is a Spring Boot sample that exemplifies how the `hi/lo` algorithm may cause issues when the database is used by external systems as well. Such systems can safely generate non-duplicated identifiers (e.g., for inserting new records) only if they know about the `hi/lo` presence and its internal work. So, better rely on `pooled` or `pooled-lo` algorithm which doesn't cause such issues.

**Key points:**
- use the `SEQUENCE` generator type (e.g., in PostgreSQL)
- configure the `hi/lo` algorithm as in `Author.java` entity
- insert a few records via `hi/lo`
- insert a few records natively (this acts as an external system that relies on `NEXTVAL('hilo_sequence')` and is not aware of `hi/lo` presence and/or behavior)
     
**Output sample:** Running this application should result in the following error:\
`ERROR: duplicate key value violates unique constraint "author_pkey"`\
`Detail: Key (id)=(2) already exists.`

----------------------------------------------------------------------------------------------------------------------

139. **[How To Generate Sequences Of Identifiers Via Hibernate `pooled` Algorithm](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPooled)**
 
 **Note:** Rely on `pooled-lo` or `pooled` especially if, beside your application, external systems needs to insert rows in your tables. Don't rely on `hi/lo` since, in such cases, it may cause errors resulted from generating duplicated identifiers.
 
**Description:** This is a Spring Boot example of using the `pooled` algorithm. The `pooled` is an optimization of `hi/lo`. This algorithm fetched from the database the current sequence value as the top boundary identifier (the current sequence value is computed as the previous sequence value + `increment_size`). This way, the application will use in-memory identifiers generated between the previous top boundary exclusive (aka, lowest boundary) and the current top boundary inclusive. 

**Key points:**
- use the `SEQUENCE` generator type (e.g., in PostgreSQL)
- configure the `pooled` algorithm as in `Author.java` entity
- insert a few records via `pooled`
- insert a few records natively (this acts as an external system that relies on `NEXTVAL('hilo_sequence')` and is not aware of `pooled` presence and/or behavior)
     
**Conclusion:** In contrast to the classical `hi/lo` algorithm, the Hibernate `pooled` algorithm doesn't cause issues to external systems that wants to interact with our tables. In other words, external systems can concurrently insert rows in the tables relying on `pooled` algorithm. Nevertheless, old versions of Hibernate can raise exceptions caused by `INSERT` statements triggered by external systems that uses the lowest boundary as identifier. This is a good reason to update to Hibernate latest versions (e.g., Hibernate 5.x), which have fixed this issue.

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

140. **[How To Generate Sequences Of Identifiers Via Hibernate `pooled-lo` Algorithm](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPooledLo)**

**Note:** Rely on `pooled-lo` or `pooled` especially if, beside your application, external systems needs to insert rows in your tables. Don't rely on `hi/lo` since, in such cases, it may cause errors resulted from generating duplicated identifiers.

**Description:** This is a Spring Boot example of using the `pooled-lo` algorithm. The `pooled-lo` is an optimization of `hi/lo` similar with `pooled`. Only that, the strategy of this algorithm fetches from the database the current sequence value and use it as the in-memory lowest boundary identifier. The number of in-memory generated identifiers is equal to `increment_size`.

**Key points:**
- use the `SEQUENCE` generator type (e.g., in PostgreSQL)
- configure the `pooled-lo` algorithm as in `Author.java` entity
- insert a few records via `pooled-lo`
- insert a few records natively (this acts as an external system that relies on `NEXTVAL('hilo_sequence')` and is not aware of `pooled-lo` presence and/or behavior)    

----------------------------------------------------------------------------------------------------------------------

141. **[Fetching Associations In Batches Via `@BatchSize`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLoadBatchAssociation)**
 
**Description:** This application uses Hibernate specific `@BatchSize` at class/entity-level and collection-level. Consider `Author` and `Book` entities invovled in a bidirectional-lazy `@OneToMany` association.

- First use case fetches all `Author` entities via a `SELECT` query. Further, calling the `getBooks()` method of the first `Author` entity will trigger another `SELECT` query that initializes the collections of the first three `Author` entities returned by the previous `SELECT` query. This is the effect of `@BatchSize` at `Author`'s collection-level.

- Second use case fetches all `Book` entities via a `SELECT` query. Further, calling the `getAuthor()` method of the first `Book` entity will trigger another `SELECT` query that initializes the authors of the first three `Book` entities returned by the previous `SELECT` query. This is the effect of `@BatchSize` at `Author` class-level.

**Note:** Fetching associated collections in the same query with their parent can be done via `JOIN FETCH` or entity graphs as well. Fetching children with their parents in the same query can be done via `JOIN FETCH`, entity graphs and `JOIN` as well.

**Key points:**
- `Author` and `Book` are in a lazy relationship (e.g., `@OneToMany` bidirectional relationship)
- `Author` entity is annotated with `@BatchSize(size = 3)`
- `Author`'s collection is annotated with `@BatchSize(size = 3)`

----------------------------------------------------------------------------------------------------------------------

142. **[How To Use Entity Graphs (`@NamedEntityGraph`) In Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedEntityGraph)**

**Note:** In a nutshell, *entity graphs* (aka, *fetch plans*) is a feature introduced in JPA 2.1 that help us to improve the performance of loading entities. Mainly, we specify the entity’s related associations and basic fields that should be loaded in a single `SELECT` statement. We can define multiple *entity graphs* for the same entity and *chain* any number of entities and even use *sub-graphs* to create complex *fetch plans*. To override the current `FetchType` semantics there are properties that can be set:

*Fetch Graph* (default), `javax.persistence.fetchgraph`\
The attributes present in `attributeNodes` are treated as `FetchType.EAGER`. The remaining attributes are treated as `FetchType.LAZY` regardless of the default/explicit `FetchType`.

*Load Graph*, `javax.persistence.loadgraph`\
The attributes present in `attributeNodes` are treated as `FetchType.EAGER`. The remaining attributes are treated according to their specified or default `FetchType`.

**Nevertheless, the JPA specs doesn't apply in Hibernate for the basic (`@Basic`) attributes.**. More details [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedEntityGraphBasicAttrs).

**Description:** This is a sample application of using *entity graphs* in Spring Boot.

**Key points:**
- define two entities, `Author` and `Book`, involved in a lazy bidirectional `@OneToMany` association
- in `Author` entity use the `@NamedEntityGraph` to define the *entity graph* (e.g., load in a single `SELECT` the authors and the associatated books)
- in `AuthorRepositry` rely on Spring `@EntityGraph` annotation to indicate the *entity graph* defined at the previous step

----------------------------------------------------------------------------------------------------------------------

143. **[How To Use Entity Sub-graphs In Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedSubgraph)**

**Note:** In a nutshell, *entity graphs* (aka, *fetch plans*) is a feature introduced in JPA 2.1 that help us to improve the performance of loading entities. Mainly, we specify the entity’s related associations and basic fields that should be loaded in a single `SELECT` statement. We can define multiple *entity graphs* for the same entity and *chain* any number of entities and even use *sub-graphs* to create complex *fetch plans*. To override the current `FetchType` semantics there are properties that can be set:

*Fetch Graph* (default), `javax.persistence.fetchgraph`\
The attributes present in `attributeNodes` are treated as `FetchType.EAGER`. The remaining attributes are treated as `FetchType.LAZY` regardless of the default/explicit `FetchType`.

*Load Graph*, `javax.persistence.loadgraph`\
The attributes present in `attributeNodes` are treated as `FetchType.EAGER`. The remaining attributes are treated according to their specified or default `FetchType`.

**Nevertheless, the JPA specs doesn't apply in Hibernate for the basic (`@Basic`) attributes.**. More details [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedEntityGraphBasicAttrs).

**Description:** This is a sample application of using *entity sub-graphs* in Spring Boot. There is one example based on `@NamedSubgraph` and one based on the dot notation (.) in an ad-hoc *entity graph*.

**Key points:**
- define three entities, `Author`, `Book` and `Publisher` (`Author` and `Book` are involved in a lazy bidirectional `@OneToMany` relationship, `Book` and `Publisher` are also involved in a lazy bidirectional `@OneToMany` relationship; between `Author` and `Publisher` there is no relationship)
     
**Using `@NamedSubgraph`**
- in `Author` entity define an *entity graph* via  `@NamedEntityGraph`; load the authors and the associatated books and use `@NamedSubgraph` to define a *sub-graph* for loading the publishers associated with these books
- in `AuthorRepository` rely on Spring `@EntityGraph` annotation to indicate the *entity graph* defined at the previous step
     
**Using the dot notation (.)**
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

**Key points:**
- define two entities, `Author` and `Book`, involved in a lazy bidirectional `@OneToMany` relationship
- the *entity graph* should load in a single `SELECT` the authors and the associatated books
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

**Key points:**
- define two entities, `Author` and `Book`, involved in a lazy bidirectional `@OneToMany` association
- in `Author` entity use the `@NamedEntityGraph` to define the *entity graph* (e.g., load the authors names (only the `name` basic attribute; ignore the rest) and the associatated books)
- add *bytecode enhancement*
- annotate the basic attributes that should be ignored by the *entity graph* with `@Basic(fetch = FetchType.LAZY)`
- in `AuthorRepository` rely on Spring `@EntityGraph` annotation to indicate the *entity graph* defined at the previous step

----------------------------------------------------------------------------------------------------------------------

146. **[How To Implement Soft Deletes Via `SoftDeleteRepository` In Spring Boot Application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSoftDeletesSpringStyle)**

**Note:** Spring Data built-in support for soft deletes is discussed in [DATAJPA-307](https://jira.spring.io/browse/DATAJPA-307).

**Description:** This application is an example of implementing soft deletes in Spring Data style via a repository named, `SoftDeleteRepository`. 

**Key points:**
- define an `abstract` class, `BaseEntity`, annotated with `@MappedSuperclass`
- in `BaseEntity` define a flag-field named `deleted` (default this field to `false` or in other words, not deleted)
- every entity that wants to take advantage of soft deletes should extend the `BaseEntity` classs
- write a `@NoRepositoryBean` named `SoftDeleteRepository` and extend `JpaRepository`
- override and implement the needed methods that provide the logic for soft deletes (check out the source code)
- repositories of entities should extend `SoftDeleteRepository`
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSoftDeletes/soft%20deletes.png)

----------------------------------------------------------------------------------------------------------------------

147. **[How To Implement Concurrent Table Based Queue Via `SKIP_LOCKED` In MySQL 8](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootMySqlSkipLocked)**

**Description:** This application is an example of how to implement concurrent table based queue via `SKIP_LOCKED` in MySQL 8. `SKIP_LOCKED` can skip over locks achieved by other concurrent transactions, therefore is a great choice for implementing job queues. In this application, we run two concurrent transactions. The first transaction will lock the records with ids 1, 2 and 3. The second transaction will skip the records with ids 1, 2 and 3 and will lock the records with ids 4, 5 and 6.

**Key points:**
- define an entity that acts as a job queue (e.g., see the `Book` entity)
- in `BookRepository` setup `@Lock(LockModeType.PESSIMISTIC_WRITE)`
- in `BookRepository` use `@QueryHint` to setup `javax.persistence.lock.timeout` to `SKIP_LOCKED`
- rely on `org.hibernate.dialect.MySQL8Dialect` dialect
- run two concurrent transactions to see the effect of `SKIP_LOCKED`

----------------------------------------------------------------------------------------------------------------------

148. **[How To Implement Concurrent Table Based Queue Via `SKIP_LOCKED` In PostgreSQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPostgresSqlSkipLocked)**

**Description:** This application is an example of how to implement concurrent table based queue via `SKIP_LOCKED` in PostgreSQL. `SKIP_LOCKED` can skip over locks achieved by other concurrent transactions, therefore is a great choice for implementing job queues. In this application, we run two concurrent transactions. The first transaction will lock the records with ids 1, 2 and 3. The second transaction will skip the records with ids 1, 2 and 3 and will lock the records with ids 4, 5 and 6.

**Key points:**
- define an entity that acts as a job queue (e.g., see the `Book` entity)
- in `BookRepository` setup `@Lock(LockModeType.PESSIMISTIC_WRITE)`
- in `BookRepository` use `@QueryHint` to setup `javax.persistence.lock.timeout` to `SKIP_LOCKED`
- rely on `org.hibernate.dialect.PostgreSQL95Dialect` dialect
- run two concurrent transactions to see the effect of `SKIP_LOCKED`

----------------------------------------------------------------------------------------------------------------------

149. **[JPA Inheritance - `JOINED`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinTableInheritance)**

**Description:** This application is a sample of JPA Join Table inheritance strategy (`JOINED`)

**Key points:**
- this inheritance strategy can be employed via `@Inheritance(strategy=InheritanceType.JOINED)`
- all the classes in an inheritance hierarchy (a.k.a., subclasses) are represented via individual tables
- by default, subclass-tables contains a primary key column that acts as a foreign key  as well - this foreign key references the *base class* table primary key
- customizing this foreign key can be done by annotating the subclasses with `@PrimaryKeyJoinColumn`  

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

150. **[JPA Inheritance - `TABLE_PER_CLASS`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTablePerClassInheritance)**

**Description:** This application is a sample of JPA Table-per-class inheritance strategy (`TABLE_PER_CLASS`)

**Key points:**
- this inheritance strategy doesn't allow the usage of the `IDENTITY` generator
- this inheritance strategy can be employed via `@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)`
- all the classes in an inheritance hierarchy (a.k.a., subclasses) are represented via individual tables
- each subclass-table stores the columns inherited from the superclass-table (*base class*)

----------------------------------------------------------------------------------------------------------------------

151. **[JPA Inheritance - `@MappedSuperclass`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootMappedSuperclass)**

**Description:** This application is a sample of using the JPA `@MappedSuperclass`.

**Key points:**
- the *base class* is not an entity, it can be `abstract`, and is annotated with `@MappedSuperclass`
- subclasses of the *base class* are mapped in tables that contains columns for the inherited attributes and for their own attibutes
- when the *base class* doens't need to be an entity, the `@MappedSuperclass` is the proper alternative to the JPA table-per-class inheritance strategy

----------------------------------------------------------------------------------------------------------------------

152. **[How To Avoid Lazy Initialization Issues Caused By Disabling Open Session In View Via Hibernate5Module](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJacksonHibernate5Module)**
 
**Note:** [Hibernate5Module](https://github.com/FasterXML/jackson-datatype-hibernate) is an *add-on module for Jackson JSON processor which handles Hibernate datatypes; and specifically aspects of lazy-loading*.
 
**Description:** By default, in Spring Boot, the Open Session in View anti-pattern is enabled. Now, imagine a lazy relationship (e.g., `@OneToMany`) between two entities, `Author` and `Book` (an author has associated more books). Next, a REST controller endpoint fetches an `Author` without the associated `Book`. But, the View (more precisely, Jackson), forces the lazy loading of the associated `Book` as well. Since OSIV will supply the already opened `Session`, the `Proxy` initializations take place successfully. 

Of course, the correct decision is to disable OSIV by setting it to `false`, but this will not stop Jackson to try to force the lazy initialization of the associated `Book` entities. Running the code again will result in an exception of type: *Could not write JSON: failed to lazily initialize a collection of role: com.bookstore.entity.Author.books, could not initialize proxy - no Session; nested exception is com.fasterxml.jackson.databind.JsonMappingException: failed to lazily initialize a collection of role: com.bookstore.entity.Author.books, could not initialize proxy - no Session*. 

Well, among the Hibernate5Module features we have support for dealing with this aspect of lazy loading and eliminate this exception. Even if OSIV will continue to be enabled (not recommended), Jackson will not use the `Session` opened via OSIV.

**Key points:**
- for Maven, add the Hibernate5Module dependency in `pom.xml`
- add a `@Bean` that returns an instance of `Hibernate5Module`
- annotate the `Author` bean with `@JsonInclude(Include.NON_EMPTY)` to exclude `null` or what is considered empty from the returned JSON
     
**Note:** The presence of Hibernate5Module instructs Jackson to initialize the lazy associations with default values (e.g., a lazy associated collection will be initialized with `null`). Hibernate5Module doesn't work for lazy loaded attributes. For such case consider [this](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAttributeLazyLoadingJacksonSerialization) item.

----------------------------------------------------------------------------------------------------------------------

153. **[How To View Binding Params Via `profileSQL=true` In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLogBindingParametersMySQL)**

**Description:** View the prepared statement binding parameters via `profileSQL=true` in MySQL.

**Key points:**
- in `application.properties` append `logger=Slf4JLogger&profileSQL=true` to the JDBC URL (e.g., `jdbc:mysql://localhost:3306/bookstoredb?createDatabaseIfNotExist=true&logger=Slf4JLogger&profileSQL=true`)
     
**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLogBindingParametersMySQL/display%20binding%20via%20profileSQL%3Dtrue.png)

----------------------------------------------------------------------------------------------------------------------

154. **[How To Shuffle Small Result Sets](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOrderByRandom)**
 
**Description:** This application is an example of shuffling small results sets. **DO NOT USE** this technique for large results sets, since is extremely expensive.

**Key points:**
- write a JPQL `SELECT` query and append to it `ORDER BY RAND()`
- each RDBMS support a function similar to `RAND()` (e.g., in PostgreSQL is `random()`)

----------------------------------------------------------------------------------------------------------------------

155. **[The Best Way To Remove Parent And Child Entities Via Bulk Deletions](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCascadeChildRemoval)**

**Description:** Commonly, deleting a parent and the associated children via `CascadeType.REMOVE`  and/or `orphanRemoval=true` involved several SQL statements (e.g., each child is deleted in a dedicated `DELETE` statement). When the number of entities is significant, this is far from being efficient, therefore other approaches should be employed. 

Consider `Author` and `Book` in a bidirectional-lazy `@OneToMany` association. This application exposes the best way to delete the parent(s) and the associated children in four scenarios listed below. These approaches relies on *bulk* deletions, therefore they are not useful if you want the deletions to take advantage of automatic optimistic locking mechanisms (e.g., via `@Version`): 

**Best way to delete author(s) and the associated books via *bulk* deletions when:**
- One `Author` is in Persistent Context, no `Book`
- More `Author` are in the Persistent Context, no `Book`
- One `Author` and the associated `Book` are in Persistent Context
- No `Author` or `Book` is in Persistent Context
     
**Note:** The most efficient way to delete all entities via a *bulk* deletion can be done via the built-in `deleteAllInBatch()`.

----------------------------------------------------------------------------------------------------------------------

156. **[How To *Bulk* Updates](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBulkUpdates)**

**Description:** *Bulk* operations (updates and deletes) are faster than batching, can benefit from indexing, but they have three main drawbacks:

- *bulk* updates/deletes may leave the Persistence Context in an outdated state (it's up to you to prevent this issue by flushing the Persistence Context before update/delete and close/clear it after the update/delete to avoid issues created by potentially unflushed or outdated entities)
- *bulk* updates/deletes don't benefit of automatic optimistic locking mechanisms (e.g., `@Version` is ignored), therefore the *lost updates* are not prevented (it is advisable to signal these updates by explicitly incrementing `version` (if any is present))
- *bulk* deletes cannot take advantage of cascading removals (`CascadeType.REMOVE`) and `orphanRemoval`

This application provides examples of *bulk* updates for `Author` and `Book` entities (between `Author` and `Book` there is a bidirectional lazy `@OneToMany` association). Both, `Author` and `Book`, has a `version` field.

----------------------------------------------------------------------------------------------------------------------

157. **[Why You Should Avoid Unidirectional `@OneToMany` And Prefer Bidirectional `@OneToMany` Relationship](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOneToManyUnidirectional)**

**Description:** As a rule of thumb, unidirectional `@OneToMany` association is less efficient than the bidirectional `@OneToMany` or the unidirectional `@ManyToOne` associations. This application is a sample that exposes the DML statements generated for reads, writes and removal operations when the unidirectional `@OneToMany` mapping is used.

**Key points:**
- regular unidirectional `@OneToMany` is less efficient than bidirectional `@OneToMany` association
- using `@OrderColumn` come with some optimizations for removal operations but is still less efficient than bidirectional `@OneToMany` association
- using `@JoinColumn` eliminates the junction table but is still less efficient than bidirectional `@OneToMany` association
- using `Set` instead of `List` or bidirectional `@OneToMany` with `@JoinColumn` relationship (e.g., `@ManyToOne @JoinColumn(name = "author_id", updatable = false, insertable = false)`) still performs worse than bidirectional `@OneToMany` association

----------------------------------------------------------------------------------------------------------------------

158. **[How To Use Subqeries in JPQL `WHERE`/`HAVING` Clause](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSubqueryInWhere)**
 
**Description:** This application is an example of using subqueries in JPQL `WHERE` clause (you can easily use it in `HAVING` clause as well).

**Key points:**\
Keep in mind that subqueries and joins queries may or may not be semantically equivalent (joins may returns duplicates that can be removed via `DISTINCT`). 

Even if the Execution Plan is specific to the database, historically speaking joins are faster than subqueries among different databases, but this is not a rule (e.g., the amount of data may significantly influence the results). Of course, do not conclude that subqueries are just a replacement for joins that doesn't deserve attention. Tuning subqueries can increases their performance as well, but this is an SQL wide topic. So, benchmark! Benchmark! Benchmark!

**As a rule of thumb, prefer subqueries only if you cannot use joins, or if you can prove that they are faster than the alternative joins.**

----------------------------------------------------------------------------------------------------------------------

159. **[How To Execute SQL Functions In `WHERE` Part Of JPQL Query And JPA 2.1](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJpqlFunctionsParams)**

Note: Using SQL functions in `SELECT` part (not in `WHERE` part) of the query can be done as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJpqlFunctionsParams).

**Description:** Starting with JPA 2.1, a JPQL query can call SQL functions in the `WHERE` part via `function()`. This application is an example of calling the MySQL, `concat_ws` function, but user defined (custom) functions can be used as well.

**Key points:**
- use JPA 2.1, `function()`  

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

160. **[Calling Stored Procedure That Returns A Value](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCallStoredProcedureReturnValue)**
 
**Description:** This application is an example of calling a MySQL stored procedure that returns a value (e.g., an `Integer`).

**Key points:**
- rely on `@NamedStoredProcedureQuery` to shape the stored procedure in the entity
- rely on `@Procedure` in repository

----------------------------------------------------------------------------------------------------------------------

161. **[Calling Stored Procedure That Returns A Result Set (Entity And DTO)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCallStoredProcedureReturnResultSet)**
 
**Description:** This application is an example of calling a MySQL stored procedure that returns a result set. The application fetches entities (e.g., `List<Author>`) and DTO (e.g., `List<AuthorDto>`).

**Key points:**
- rely on `EntiyManager` since Spring Data `@Procedure` will not work

----------------------------------------------------------------------------------------------------------------------

162. **[Calling Stored Procedure That Returns A Result Set Via Native Query](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCallStoredProcedureNativeCall)**
 
**Description:** This application is an example of calling a MySQL stored procedure that returns a result set (entity or DTO) via a native query.
 
**Key points:**
- rely on a native call as `@Query(value = "{CALL FETCH_AUTHOR_BY_GENRE (:p_genre)}", nativeQuery = true)`

----------------------------------------------------------------------------------------------------------------------

163. **[Calling Stored Procedure That Returns A Result Set Via `JdbcTemplate`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCallStoredProcedureJdbcTemplate)**

**Note:** Most probably you'll like to process the result set via `BeanPropertyRowMapper` as [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCallStoredProcedureJdbcTemplateBeanPropertyRowMapper). This is less verbose than the approach used here. Nevertheless, this approach is useful to understand how the result set looks like.

**Description:** This application is an example of calling a MySQL stored procedure that returns a result set via `JdbcTemplate`.
 
**Key points:**
- rely on `JdbcTemplate` and `SimpleJdbcCall`

----------------------------------------------------------------------------------------------------------------------

164. **[How To Obtain Auto-Generated Keys](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootReturnGeneratedKeys)**
 
**Description:** This application is an example of retrieving the database auto-generated primary keys.

**Key points:**
- JPA style, retrieve the auto-generated keys via `getId()`
- JDBC style, retrieve the auto-generated keys via `JdbcTemplate`
- JDBC style, retrieve the auto-generated keys via `SimpleJdbcInsert`

----------------------------------------------------------------------------------------------------------------------

165. **[How To Unproxy A Proxy](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootUnproxyAProxy)**

**Description:** A Hibernate proxy can be useful when a child entity can be persisted with a reference to its parent (`@ManyToOne` or `@OneToOne` association). In such cases, fetching the parent entity from the database (execute the `SELECT` statement) is a performance penalty and a pointless action. Hibernate can set the underlying foreign key value for an uninitialized proxy. This topic is discussed [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPopulatingChildViaProxy).

A proxy can be unproxied via `Hibernate.unproxy()`. This method is available starting with Hibernate 5.2.10.

**Key points:**
- fetch a proxy via `JpaRepository#getOne()`
- unproxy the fetched proxy via `Hibernate.unproxy()`     

----------------------------------------------------------------------------------------------------------------------

166. **[How To Convert `Boolean` To Yes/No Via `AttributeConverter`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootMapBooleanToYesNo)**
 
**Description:** This application is an example of converting a `Boolean` to *Yes*/*No* strings via `AttributeConverter`. This kind of conversions are needed when we deal with legacy databases that connot be changed. In this case, the legacy database stores the booleans as *Yes*/*No*.

**Key points:**
- implement a custom converter via `AttributeConverter`

----------------------------------------------------------------------------------------------------------------------

167. **[How Efficient Is Just `@OManyToOne`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJustManyToOne)**

**Note:** The `@ManyToOne` association maps exactly to the one-to-many table relationship. The underlying foreign key is under child-side control in unidirectional or bidirectional relationship.

**Description:** This application shows that using only `@ManyToOne` is quite efficient. On the other hand, using only `@OneToMany` is far away from being efficient. Always, prefer bidirectional `@OneToMany` or unidirectional `@ManyToOne`. Consider two entities, `Author` and `Book` in a unidirectional `@ManyToOne` relationship.

**Key points:**
- Adding a new book is efficient
- Fetching all books of an author is efficient via a JPQL
- Pagination of books is efficient
- Remove a book is efficient
- Even if the fetched collection is not managed, *dirty checking* mechanism works as expected

----------------------------------------------------------------------------------------------------------------------

168. **[How To Use `JOIN FETCH` And `Pageable` Pagination](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinFetchPageable)**

**Description:** Trying to combine `JOIN FETCH`/`LEFT JOIN FETCH` and `Pageable` results in an exception of type `org.hibernate.QueryException: query specified join fetching, but the owner of the fetched association was not present in the select list`. This application is a sample of how to avoid this exception.

**Key points:**
- use `countQuery`
- use entity graph
     
**Note:** Fixing the above exception will lead to an warning of type HHH000104, `firstResult / maxResults specified with collection fetch; applying in memory!`. If this warning is a performance issue, and most probably it is, then follow by reading [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHHH000104).

----------------------------------------------------------------------------------------------------------------------

169. **[How To Avoid HHH000104 And Use Pagination Of Parent-Child](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHHH000104)**

**Description:** HHH000104 is a Hibernate warning that tell us that pagination of a result set is tacking place in memory. For example, consider the `Author` and `Book` entities in a lazy-bidirectional `@OneToMany` association and the following query: 

  `@Transactional`\
  `@Query(value = "SELECT a FROM Author a LEFT JOIN FETCH a.books WHERE a.genre = ?1",`\
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`countQuery = "SELECT COUNT(a) FROM Author a WHERE a.genre = ?1")`\
  `Page<Author> fetchWithBooksByGenre(String genre, Pageable pageable);`

Calling `fetchWithBooksByGenre()` works fine only that the following warning is signaled: `HHH000104: firstResult / maxResults specified with collection fetch; applying in memory!` Obviously, having pagination in memory cannot be good from performance perspective. This application implement a solution for moving pagination at database-level.

**Key points:**
- use three or two JPQL queries for fetching `Page` of entities in read-write or read-only mode
- use two JPQL queries for fetching `Slice` or `List` of entities in read-write or read-only mode

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

170. **[What `@Transactional(readOnly=true)` Actually Do](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTransactionalReadOnlyMeaning)**
 
 **Description:** This application is meant to reveal what is the difference between `@Transactional(readOnly = false)` and `@Transactional(readOnly = true)`. In a nuthsell, `readOnly = false` (default) fetches entites in *read-write* mode (managed). Before Spring 5.1, `readOnly = true` just set `FlushType.MANUAL/NEVER`, therefore the automatic *dirty checking mechanism* will not take action since there is no flush. In other words, Hibernate keep in the Persistent Context the fetched entities and the hydrated (loaded) state. By comparing the entity state with the hydrated state, the *dirty checking mechanism* can decide to trigger `UPDATE` statements in our behalf. But, the *dirty checking mechanism* take place at flush time, therefore, without a flush, the hydrated state is kept in Persistent Context for nothing, representing a performance penalty. Starting with Spring 5.1, the *read-only* mode is propagated to Hibernate, therefore the hydrated state is discarded immediately after loading the entities. Even if the *read-only* mode discards the hydrated state the entities are still loaded in the Persistent Context, therefore, for *read-only* data, relying on DTO (Spring projection) is better.

**Key points:**
- `readOnly = false` load data in *read-write* mode (managed)
- `readOnly = true` discard the hydrated state (starting with Spring 5.1)
     
----------------------------------------------------------------------------------------------------------------------
     
171. **[Get Transaction Id In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTransactionId)**
  
 **Description:** This application is an example of getting the current database transaction id in MySQL. Only read-write database transactions gets an id in MySQL. Every database has a specific query for getting the transaction id. [Here](https://vladmihalcea.com/current-database-transaction-id/) it is a list of these queries.

**Key points:**
- rely on the following query, `SELECT tx.trx_id FROM information_schema.innodb_trx tx WHERE tx.trx_mysql_thread_id = connection_id()`

----------------------------------------------------------------------------------------------------------------------

172. **[Inspect Persistent Context](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootInspectPersistentContext)**

**Description:** This application is a sample of inspecting the Persistent Context content via `org.hibernate.engine.spi.PersistenceContext`.

**Key points:**
- get the current Persistent Context via Hibernate `SharedSessionContractImplementor`
- rely on `PersistenceContext` [API](https://docs.jboss.org/hibernate/orm/5.4/javadocs/org/hibernate/engine/spi/PersistenceContext.html)

----------------------------------------------------------------------------------------------------------------------

173. **[How To Extract Tables Metadata](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTablesMetadata)**

**Description:** This application is an example of using the Hibernate SPI, `org.hibernate.integrator.spi.Integrator` for extracting tables metadata.

**Key points:**
- implement `org.hibernate.integrator.spi.Integrator` and override `integrate()` method to return `metadata.getDatabase()`
- register this `Integrator` via `LocalContainerEntityManagerFactoryBean`

----------------------------------------------------------------------------------------------------------------------

174. **[How To Map `@ManyToOne` Relationship To A SQL Query Via The Hibernate `@JoinFormula`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinFormula)**

**Description:** This application is an example of mapping the JPA `@ManyToOne` relationship to a SQL query via the Hibernate `@JoinFormula` annotation. We start with two entities, `Author` and `Book`, involved in a unidirectional `@ManyToOne` relationship. Each book has a price. While we fetch a book by id (let's call it book `A`), we want to fetch another book `B` of the same author whose price is the next smaller price in comparison with book `A` price.

**Key points:**
- fetching the book `B` is done via `@JoinFormula`

----------------------------------------------------------------------------------------------------------------------

175. **[How To Fetch Data From A MySQL Database View](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDatabaseView)**

**Description:** This application is an example of fetching a read-only MySQL database view in a JPA immutable entity.

**Key points:**
- the database view is available in `data-mysql.sql` file
- the entity used to map the database view is `GenreAndTitleView.java`

----------------------------------------------------------------------------------------------------------------------

176. **[How To Update/Insert/Delete Data From/In A MySQL Database View](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDatabaseViewUpdateInsertDelete)**

**Description:** This application is an example of updating, inserting and deleting data in a MySQL database view. Every update/insert/delete will automatically update the contents of the underlying table(s).

**Key points:**
- the database views are available in `data-mysql.sql` file
- respect [MySQL](https://dev.mysql.com/doc/refman/8.0/en/view-updatability.html) requirements for updatable and insertable database views

----------------------------------------------------------------------------------------------------------------------

177. **[How To Prevent A MySQL Database View From Updating/Inserting Rows That Are Not Visible Through It Via `WITH CHECK OPTION`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDatabaseViewWithCheckOption)**

**Description:** This application is an example of preventing inserts/updates of a MySQL view that are not visible through this view via `WITH CHECK OPTION`. In other words, whenever you insert or update a row of the base tables through a view, MySQL ensures that the this operation is conformed with the definition of the view.

**Key points:**
- add `WITH CHECK OPTION` to the view
- this application will throw an exception of type `java.sql.SQLException: CHECK OPTION failed 'bookstoredb.author_anthology_view`

----------------------------------------------------------------------------------------------------------------------

178. **[How To Efficiently Assign A Database Temporary Sequence Of Values To Rows](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAssignSequentialNumber)**
 
**Description:** This application is an example of assigning a database temporary sequence of values to rows via the window function, `ROW_NUMBER()`. This window function is available in almost all databases, and starting with version 8.x is available in MySQL as well.

**Key points:**
- commonly, you don't need to fetch in the result set the temporary sequence of values produced by `ROW_NUMBER()` (you will use it internally, in the query, usually in the `WHERE` clause and CTEs), but, this time, let's write a Spring projection (DTO) that contains a getter for the column generated by `ROW_NUMBER`as well
- write several native querys relying on `ROW_NUMBER()` window function
     
**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootAssignSequentialNumber/assign%20sequential%20number%20to%20rows.png)

----------------------------------------------------------------------------------------------------------------------

179. **[How To Efficiently Finding Top N Rows Of Every Group](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTopNRowsPerGroup)**
 
**Description:** This application is an example of finding top N rows of every group.

**Key points:**
- write a native query relying on `ROW_NUMBER()` window function
     
**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootTopNRowsPerGroup/Finding%20top%20N%20rows%20of%20every%20group.png)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

180. **[How To Implement Pagination Via `ROW_NUMBER()` Window Function](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPaginationRowNumber)**

**Description:** This application is an example of using `ROW_NUMBER()` (and `COUNT(*) OVER()` for counting all elements) window function to implement pagination.

**Key points:**
- use a native query relying on `ROW_NUMBER()`
- we don't return a page as `Page` or `Slice`, we return it as `List`, therefore `Pageable` is not used

----------------------------------------------------------------------------------------------------------------------

181. **[Why the `@Transactional` annotation is being ignored](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootWhyTransactionalIsIgnored)**
 
**Description:** This application is an example of fixing the case when `@Transactional` annotation is ignored. Most of the time, this annotation is ignored in the following scenarios:

1. `@Transactional` was added to a `private`, `protected` or `package-protected` method
2. `@Transactional` was added to a method defined in the same class where it is invoked

**Key points:**
- write a helper service and move the `@Transactional` methods there
- ensure that these methods are declared as `public`
- call `@Transactional` methods from other services

----------------------------------------------------------------------------------------------------------------------

182. **[How To Generate Custom Sequence IDs](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCustomSequenceGenerator)**

**Description:** This is a Spring Boot example of using the `hi/lo` algorithm and a custom implementation of `SequenceStyleGenerator` for generating custom sequence IDs (e.g, `A-0000000001`, `A-0000000002`, ...).

**Key points:**
- extend `SequenceStyleGenerator` and override the `configure()` and `generate()` methods
- set this generator in entities

----------------------------------------------------------------------------------------------------------------------

183. **[How To Map `Clob` And `Blob` To `byte[]` And `String`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootMappingLobToByteString)**
 
**Description:** This application is an example of mapping `Clob` and `Blob` as `byte[]` and `String`. 

**Key points:**
- this is vey easy to use but the application doesn't take advantage of JDBC driver LOB-specific optimizations
     
----------------------------------------------------------------------------------------------------------------------
     
184. **[How To Map To JDBC’s `LOB` Locators `Clob` And `Blob`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootMappingLobToClobAndBlob)**
 
**Description:** This application is an example of mapping to JDBC's `LOB` locators `Clob` and `Blob`.

**Key points:**
- this takes advantage of JDBC driver LOB-specific optimizations

----------------------------------------------------------------------------------------------------------------------

185. **[How To Fetch Certain Subclass From An `SINGLE_TABLE` Inheritance Hierarchy](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSpecificSubclassFromInheritance)**

**Description:** This application is a sample of fetching a certain subclass from a `SINGLE_TABLE` inheritance hierarchy. This is useful when the dedicated repository of the subclass doesn't automatically add in the `WHERE` clause a `dtype` based condition for fetching only the needed subclass.

**Key points:**
- explicitly add in the `WHERE` clause a `TYPE` check 

----------------------------------------------------------------------------------------------------------------------

186. **[How To Define An Association That Reference `@NaturalId`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootReferenceNaturalId)**

**Description:** This is a SpringBoot application that defines a `@ManyToOne` relationship that doesn't reference a primary key column. It references a Hibernate `@NaturalId` column.

**Key points:**
- rely on `@JoinColumn(referencedColumnName = "natural_id_column")`

----------------------------------------------------------------------------------------------------------------------

187. **[How To Implement Advanced Search Via `Specification`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSearchViaSpecifications)**
 
**Description:** This application is an example of implementing an advanced search via `Specification` API. Mainly, you can give the search filters to a generic `Specification` and fetch the result set. Pagination is supported as well. You can chain expressions via logical `AND` and `OR` to create compound filters. Nevertheless, there is room for extensions to add brackets support (e.g., `(x AND y) OR (x AND z)`), more operations, conditions parser and so on and forth.

**Key points:**
- write a generic `Specification`       

----------------------------------------------------------------------------------------------------------------------

188. **[How To Create `Specification` Query Fetch Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSpecificationQueryFetchJoins)**

**Description:** This application contains two examples of how to define `JOIN` in `Specification` to emulate JPQL join-fetch operations.

**Key points:**
- the first approach trigger two `SELECT` statements and the pagination is done in memory (very bad!)
- the second approach trigger three `SELECT` statements but the pagination is done in the database
- in both approaches the `JOIN` is defined in a `Specification` implementation

----------------------------------------------------------------------------------------------------------------------

189. **[DTO Via Spring Data Projections (Projection Interface In Repository Interface)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjectionsIntefaceInRepo)** 

**Note:** You may also like to read the recipe, ["How To Enrich DTO With Virtual Properties Via Spring Projections"](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaProjectionsAndVirtualProperties)

**Description:** Fetch only the needed data from the database via Spring Data Projections (DTO). The projection interface is defined as a `static` interface (can be non-`static` as well) in the repository interface.

**Key points:**
- write an interface (projection) containing getters only for the columns that should be fetched from the database
- write the proper query returning a `List<projection>`
- if is applicable, limit the number of returned rows (e.g., via `LIMIT`) - here, we can use query builder mechanism built into Spring Data repository infrastructure
     
**Note:** Using projections is not limited to use query builder mechanism built into Spring Data repository infrastructure. We can fetch projections via JPQL or native queries as well. For example, in this [application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjectionsAndJpql) we use a JPQL.
     
**Output example (select first 2 rows; select only "name" and "age"):**
<a href="#"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaProjections/DTOs%20via%20Spring%20projections.png" align="center" height="251" width="658" ></a>

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

190. **[How To Ensure/Validate That Only One Association Is Non-Null](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootChooseOnlyOneAssociation)**
 
 **Description:** Consider an entity named `Review`. This entity defines three `@ManyToOne` relationships to `Book`, `Article` and `Magazine`. A review can be associated with either a book, a magazine or an article. To validate this constraint, we can rely on  [Bean Validation](https://beanvalidation.org/) as in this application.

**Key points:**
- rely on Bean Validation to validate that only one association is non-`null`
- expose the constraint via a custom annotation (`@JustOneOfMany`) added at class-level to the `Review` entity
- for preventing native query to break our constraint add the validation at database level as well (e.g., in MySQL add a `TRIGGER`)
     
----------------------------------------------------------------------------------------------------------------------
     
191. **[Quickest Mapping Of Java Enums](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnumStringInt)**
  
**Description:** This application uses `EnumType.ORDINAL` and `EnumType.STRING` for mapping Java `enum` type to database. As a rule of thumb, strive to keep the data types as small as possible (e.g., for `EnumType.ORDINAL` use `TINYINT/SMALLINT`, while for `EnumType.STRING` use `VARCHAR(max_needed_bytes)`). Relying on `EnumType.ORDINAL` should be more efficient but is less expressive than `EnumType.STRING`.

**Key points:**
- strive for smallest data types (e.g., for `EnumType.ORDINAL` set `@Column(columnDefinition = "TINYINT")`)

----------------------------------------------------------------------------------------------------------------------

192. **[How To Map Java `enum` To Database Via `AttributeConverter`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnumAttributeConverter)**
 
**Description:** This application maps a Java `enum` via `AttributeConverter`. In other words, it maps the `enum` values `HORROR`, `ANTHOLOGY` and `HISTORY` to the integers `1`, `2` and `3` and viceversa. This allows us to set the column type as `TINYINT/SMALLINT` which is less space-consuming than `VARCHAR(9)` needed in this case.

**Key points:**
- define a custom `AttributeConverter`
- annotate with `@Converter` the corresponding entity field

----------------------------------------------------------------------------------------------------------------------

193. **[How To Map Java `enum` To PostgreSQL `enum` Type](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnumPostgreSQLCustomType)**
 
**Description:** This application maps a Java `enum` type to PostgreSQL `enum` type.

**Key points:**
- define a custom Hibernate `EnumType`
- register this custom `EnumType` via `package-info.java`
- annotate the corresponding entity field `@Type`

----------------------------------------------------------------------------------------------------------------------

194. **[How To Map Java `enum` To PostgreSQL `enum` Type Via Hibernate Types Library](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnumPostgreSQLHibernateTypes)**
 
**Description:** This application maps a Java `enum` type to PostgreSQL `enum` type via [Hibernate Types](https://github.com/vladmihalcea/hibernate-types) library.

**Key points:**
- install Hibernate Types library via `pom.xml`
- use `@TypeDef` to specify the needed type class
- annotate the corresponding entity field with `@Type`

----------------------------------------------------------------------------------------------------------------------

195. **[How To Handle JSON in MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJsonToMySQL)**

**Description:** [Hibernate Types](https://github.com/vladmihalcea/hibernate-types) is a library of extra types not supported by Hibernate Core by default. This is a Spring Boot application that uses this library to persist JSON data (JSON Java `Object`) in a MySQL `json` column and for querying JSON data from the MySQL `json` column to JSON Java `Object`. Updates are supported as well.

**Key points:**
- for Maven, add Hibernate Types as a dependency in `pom.xml`
- in entity use `@TypeDef` to map `typeClass` to `JsonStringType`

----------------------------------------------------------------------------------------------------------------------

196. **[How To Handle JSON in PostgreSQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJsonToPostgreSQL)**

**Description:** [Hibernate Types](https://github.com/vladmihalcea/hibernate-types) is a library of extra types not supported by Hibernate Core by default. This is a Spring Boot application that uses this library to persist JSON data (JSON Java `Object`) in a PostgreSQL `json` column and for querying JSON data from the PostgreSQL `json` column to JSON Java `Object`. Updates are supported as well.

**Key points:**
- for Maven, add Hibernate Types as a dependency in `pom.xml`
- in entity use `@TypeDef` to map `typeClass` to `JsonBinaryType`

----------------------------------------------------------------------------------------------------------------------

197. **[How To Increment The Version Of The Locked Entity Even If This Entity Was Not Modified `OPTIMISTIC_FORCE_INCREMENT`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOptimisticForceIncrement)**

**Description:** This application is a sample of how `OPTIMISTIC_FORCE_INCREMENT` works in MySQL. This is useful when you want to increment the version of the locked entity even if this entity was not modified. Via `OPTIMISTIC_FORCE_INCREMENT` the version is updated (incremented) at the end of the currently running transaction.

**Key points:**
- use a root entity, `Chapter` (which uses `@Version`)
- several editors load a chapter and perform modifications mapped via the `Modification` entity
- between `Modification` (child-side) and `Chapter` (parent-side) there is a lazy unidirectional `@ManyToOne` association
- for each modification, Hibernate will trigger an `INSERT` statement against the `modification` table, therefore the `chapter` table will not be modified by editors
- but, `Chapter` entity version is needed to ensure that modifications are applied sequentially (the author and editor are notified if a modificaton was added since the chapter copy was loaded)
- the `version` is forcibly increased at each modification (this is materialized in an `UPDATE` triggered against the `chapter` table at the end of the currently running transaction)
- set `OPTIMISTIC_FORCE_INCREMENT` in the corresponding repository
- rely on two concurrent transactions to shape the scenario that will lead to an exception of type `ObjectOptimisticLockingFailureException`      

----------------------------------------------------------------------------------------------------------------------

198. **[How To Increment The Version Of The Locked Entity Even If This Entity Was Not Modified `PESSIMISTIC_FORCE_INCREMENT`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPesimisticForceIncrement)**

**Description:** This application is a sample of how `PESSIMISTIC_FORCE_INCREMENT` works in MySQL. This is useful when you want to increment the version of the locked entity even if this entity was not modified. Via `PESSIMISTIC_FORCE_INCREMENT` the version is updated (incremented) immediately (the entity version update is guaranteed to succeed immediately after acquiring the row-level lock). The incrementation takes place before the entity is returned to the data access layer.

**Key points:**
- use a root entity, `Chapter` (which uses `@Version`)
- several editors load a chapter and perform modifications mapped via the `Modification` entity
- between `Modification` (child-side) and `Chapter` (parent-side) there is a lazy unidirectional `@ManyToOne` association
- for each modification, Hibernate will trigger an `INSERT` statement against the `modification` table, therefore the `chapter` table will not be modified by editors
- but, `Chapter` entity `version` is needed to ensure that modifications are applied sequentially (each editor is notified if a modificaton was added since his chapter copy was loaded and he must re-load the chapter)
- the `version` is forcibly increased at each modification (this is materialized in an `UPDATE` triggered against the `chapter` table immediately after aquiring the row-level lock)
- set `PESSIMISTIC_FORCE_INCREMENT` in the corresponding repository
- rely on two concurrent transactions to shape two scenarios: one that will lead to an exception of type `OptimisticLockException` and one that will lead to `QueryTimeoutException`                   
     
**Note:** Pay attention to the MySQL dialect: `MySQL5Dialect` (MyISAM) doesn't support row-level locking, `MySQL5InnoDBDialect` (InnoDB) acquires row-level lock via `FOR UPDATE` (timeout can be set), `MySQL8Dialect` (InnoDB) acquires row-level lock via `FOR UPDATE NOWAIT`.

----------------------------------------------------------------------------------------------------------------------

199. **[How `PESSIMISTIC_READ` And `PESSIMISTIC_WRITE` Works In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPessimisticLocks)**
 
**Description:** This application is an example of using `PESSIMISTIC_READ` and `PESSIMISTIC_WRITE` in MySQL. In a nutshell, each database system defines its own syntax for acquiring shared and exclusive locks and not all databases support both types of locks. Depending on `Dialect`, the syntax can vary for the same database as well (Hibernate relies on `Dialect` for chosing the proper syntax). In MySQL, `MySQL5Dialect` doesn't support locking, while InnoDB engine (`MySQL5InnoDBDialect` and `MySQL8Dialect`) supports shared and exclusive locks as expected.

**Key points:**
- rely on `@Lock(LockModeType.PESSIMISTIC_READ)` and `@Lock(LockModeType.PESSIMISTIC_WRITE)` on query-level
- for testing, use `TransactionTemplate` to trigger two concurrent transactions that read and write the same row

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

200. **[How `PESSIMISTIC_WRITE` Works With `UPDATE`/`INSERT` And `DELETE` Operations](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPessimisticLocksDelInsUpd)**
 
**Description:** This application is an example of triggering `UPDATE`, `INSERT` and `DELETE` operations in the context of `PESSIMISTIC_WRITE` locking against MySQL. While `UPDATE` and `DELETE` are blocked until the exclusive lock is released, `INSERT` depends on the transaction isolation level. Typically, even with exclusive locks, inserts are possible (e.g., in PostgreSQL). In MySQL, for the default isolation level, `REPEATABLE READ`, inserts are prevented against a range of locked entries, but, if we switch to `READ_COMMITTED`, then MySQL acts as PostgreSQL as well.

**Key points:**
- start *Transaction A* and trigger a `SELECT` with `PESSIMISTIC_WRITE` to acquire an exclusive lock
- start a concurrent *Transaction B* that triggers an `UPDATE`, `INSERT` or `DELETE` on the rows locked by *Transaction A*
- in case of `UPDATE`, `DELETE` and `INSERT` + `REPEATABLE_READ`, *Transaction B* is blocked until it timeouts or *Transaction A* releases the exclusive lock
- in case of `INSERT` + `READ_COMMITTED`, *Transaction B* can insert in the range of rows locked by *Transaction A* even if *Transaction A* is holding an exclusive lock on this range     

----------------------------------------------------------------------------------------------------------------------

201. **[How To Check That Transaction Timeout And Rollback At Expiration Works As Expected](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTransactionTimeout)**
 
**Note:** Do not test transaction timeout via `Thread.sleep()`! This is not working! Rely on two transactions and exclusive locks or even better rely on SQL sleep functions (e.g., MySQL, `SELECT SLEEP(n)` seconds, PostgreSQL, `SELECT PG_SLEEP(n)` seconds). Most RDBMS supports a sleep function flavor.

**Description:** This application contains several approaches for setting a timeout period for a transaction or query. The timeout is signaled by a specific timeout exception (e.g., `.QueryTimeoutException`). After timeout, the transaction is rolled back. You can see this in the database (visually or query) and on log via a message of type: `Initiating transaction rollback; Rolling back JPA transaction on EntityManager [SessionImpl(... <open>)]`.

**Key points:**
- set global transaction timeout via `spring.transaction.default-timeout` in seconds (see, `application.properties`)
- set transaction timeout at method-level or class-level via `@Transactional(timeout = n)` in seconds
- set query timeout via JPA `javax.persistence.query.timeout` hint in milliseconds
- set query timeout via Hibrenate `org.hibernate.timeout` hint in seconds
     
**Note:** If you are using `TransactionTemplate` then the timeout can be set via `TransactionTemplate.setTimeout(n)` in seconds.

----------------------------------------------------------------------------------------------------------------------

202. **[How To Define A Composite Primary Key Via `@Embeddable`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCompositeKeyEmbeddable)**

**Description:** This application is a proof of concept of how to define a composite key via `@Embeddable` and `@EmbeddedId`. This application uses two entities, `Author` and `Book` involved in a lazy bidirectional `@OneToMany` association. The identifier of `Author` is composed by `name` and `age` via `AuthorId` class. The identifier of `Book` is just a regular auto-generated numeric value.

**Key points:**
- the composite key class (e.g., `AuthorId`) is `public`
- the composite key class must implement `Serializable`
- the composite key must define `equals()` and `hashCode()`
- the composite key must define a no-arguments constructor

----------------------------------------------------------------------------------------------------------------------

203. **[How To Define A Composite Primary Key Via `@IdClass`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCompositeKeyIdClass)**

**Description:** This application is a proof of concept of how to define a composite key via `@IdClass`. This application uses two entities, `Author` and `Book` involved in a lazy bidirectional `@OneToMany` association. The identifier of `Author` is composed by `name` and `age` via `AuthorId` class. The identifier of `Book` is just a typical auto-generated numeric value.

**Key points:**
- the composite key class (e.g., `AuthorId`) is `public`
- the composite key class must implement `Serializable`
- the composite key must define `equals()` and `hashCode()`
- the composite key must define a no-arguments constructor
     
**Note**: The `@IdClass` can be useful when we cannot modify the compsite key class. Otherwise, rely on [`@Embeddable`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCompositeKeyEmbeddable).

----------------------------------------------------------------------------------------------------------------------

204. **[How To Define A Relationship in an `@Embeddable` Composite Primary Key](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCompositeKeyEmbeddableMapRel)**

**Description:** This application is a proof of concept of how to define a relationship in an `@Embeddable` composite key. The composite key is `AuthorId` and it belongs to the `Author` class.

**Key points:**
- the composite key class (e.g., `AuthorId`) is `public`
- the composite key class must implement `Serializable`
- the composite key must define `equals()` and `hashCode()`
- the composite key must define a no-arguments constructor

----------------------------------------------------------------------------------------------------------------------

205. **[How To Load Multiple Entities By Id](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLoadMultipleIds)**

**Description:** This is a SpringBoot application that loads multiple entities by id via a `@Query` based on the `IN` operator and via the Hibernate 5 `MultiIdentifierLoadAccess` interface.

**Key points:**
- for using the `IN` operator in a `@Query` simply add the query in the proper repository
- for using Hibernate 5 `MultiIdentifierLoadAccess` in Spring Data style provide the proper implementation
- among its advantages, the `MultiIdentifierLoadAccess` implementation allows us to load entities by multiple ids in batches and by inspecting or not the current Persistent Context (by default, the Persistent Context is not inspected to see if the entities are already loaded or not) 

----------------------------------------------------------------------------------------------------------------------

206. **[Fetching All Entity Attributes As Spring Projection (DTO)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinDtoAllFields)**

**Description:** This application is a sample of fetching all attributes of an entity (`Author`) as a Spring projection (DTO). Commonly, a DTO contains a subset of attributes, but, sometimes we need to fetch the whole entity as a DTO. In such cases, we have to pay attention to the chosen approach. Choosing wisely can spare us from performance penalties.

**Key points:** 
- fetching the result set as a `List<Object[]>` or `List<AuthorDto>` via a JPQL of type `SELECT a FROM Author a` **WILL** fetch the result set as entities in Persistent Context as well - avoid this approach
- fetching the result set as a `List<Object[]>` or `List<AuthorDto>` via a JPQL of type `SELECT a.id AS id, a.name AS name, ... FROM Author a` will **NOT** fetch the result set in Persistent Context - this is efficient
- fetching the result set as a `List<Object[]>` or `List<AuthorDto>` via a native SQL of type `SELECT id, name, age, ... FROM author` will **NOT** fetch the result set in Persistent Context - but, this approach is pretty slow
- fetching the result set as a `List<Object[]>` via Spring Data query builder mechanism **WILL** fetch the result set in Persistent Context - avoid this approach
- fetching the result set as a `List<AuthorDto>` via Spring Data query builder mechanism will **NOT** fetch the result set in Persistent Context
- fetching the result set as *read-only* entitites (e.g., via the built-in `findAll()` method) should be considered after JPQL with explicit list of columns to be fetched and query builder mechanism

----------------------------------------------------------------------------------------------------------------------

207. **[How To Efficiently Fetch Spring Projection Including `@ManyToOne` Or `@OneToOne` Associations](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNestedVsVirtualProjection)**

**Description:** This application fetches a Spring projection including the `@ManyToOne` association via different approaches. It can be easily adapted for `@OneToOne` association as well.

**Key points:**
- fetching raw data is the fastest approach

----------------------------------------------------------------------------------------------------------------------

208. **[Pay Attention To Spring Projections That Include Associated Collections](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootProjectionAndCollections)**

**Description:** This application inspect the Persistent Context content during fetching Spring projections that includes collections of associations. In this case, we focus on a `@OneToMany` association. Mainly, we want to fetch only some attributes from the parent-side and some attributes from the child-side. 

----------------------------------------------------------------------------------------------------------------------

209. **[Reusing Spring projection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootReuseProjection)**
 
**Description:** This application is a sample of reusing an interface-based Spring projection. This is useful to avoid defining multiple interface-based Spring projections in order to cover a range of queries that fetches different subsets of fields.

**Key points:**
- define an interface-based Spring projection containing getters for the wider case 
- rely on class-level `@JsonInclude(JsonInclude.Include.NON_DEFAULT)` annotation to avoid serialization of default fields (e.g., fields that are not available in the current projection and are `null` - these fields haven't been fetched in the current query)
- this is useful to Jackson that will not serialize in the resulted JSON the missing fields (e.g., `null` fields)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

210. **[Dynamic Spring projection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDynamicProjection)**
 
**Description:** This application is a sample of using dynamic Spring projections.

**Key points:**
- declare query-methods in a generic manner (e.g., `<T> List<T> findByGenre(String genre, Class<T> type);`)

----------------------------------------------------------------------------------------------------------------------

211. **[Batch Inserts Via EntityManager With Batch Per Transaction (MySQL)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsEntityManagerBatchPerTransaction)**

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

----------------------------------------------------------------------------------------------------------------------

212. **[How To JDBC Batch a Big JSON File To MySQL Via ForkJoinPool And HikariCP](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileForkJoin)**

**Description:** This is a Spring Boot application that reads a relatively big JSON file (200000+ lines) and inserts its content in MySQL via batching using `ForkJoinPool`, `JdbcTemplate` and HikariCP.

**Key points:**
- using MySQL, `json` type
- read the file content into a `List` 
- the list is halved and subtasks are created until the list size is small than the batch size (e.g., by default smaller than 30)
- for MySQL, in application.properties, you may want to attach to the JDBC URL the following:
     - `rewriteBatchedStatements=true` -> this setting will force sending the batched statements in a single request;
     - `cachePrepStmts=true` -> enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled
     - `useServerPrepStmts=true` -> this way you switch to server-side prepared statements (may lead to signnificant performance boost); moreover, you avoid the `PreparedStatement` to be emulated at the JDBC Driver level; 
     - we use the following JDBC URL settings:\
     `...?cachePrepStmts=true&useServerPrepStmts=true&rewriteBatchedStatements=true&createDatabaseIfNotExist=true`
     - **Note: Older MySQL versions will not tolerate well to have toghether rewritting and server-side prepared statement activated. For being sure that these statements still valid please check the notes of the Connector/J that you are using**
- set the HikariCP to provide a number of database connections that ensure that the database achives a minimum context switching (e.g., 2 * number of CPU cores)
- this application uses `StopWatch` to measure the time needed to transfer the file into the database
- in order to run the application you have to unzip the `citylots.zip` in the current location; this is the big JSON file collected from Internet;
- if you want to see details about the batch process simply activate the `DatasourceProxyBeanPostProcessor.java` component by uncomment the line, `// @Component`; This is needed because this application relies on DataSource-Proxy (for details, see the following [item](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceProxy))

----------------------------------------------------------------------------------------------------------------------

213. **[Batch Inserts In Spring Boot Style Via `CompletableFuture`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsCompletableFuture)**

**Description:** This application is a sample of using `CompletableFuture` for batching inserts. This `CompletableFuture` uses an `Executor` that has the number of threads equal with the number of your computer cores. Usage is in Spring style.
     
----------------------------------------------------------------------------------------------------------------------
     
214. **[How To Optimize Batch Inserts of Parent-Child Relationships And Batch Per Transaction (MySQL)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertOrderBatchPerTransaction)**

**Description:** Let's suppose that we have a one-to-many relationship between `Author` and `Book` entities. When we save an author, we save his books as well thanks to cascading all/persist. We want to create a bunch of authors with books and save them in the database (e.g., a MySQL database) using the batch technique. By default, this will result in batching each author and the books per author (one batch for the author and one batch for the books, another batch for the author and another batch for the books, and so on). In order to batch authors and books, we need to **order inserts** as in this application.

Moreover, this example commits the database transaction after each batch excecution. This way we avoid long-running transactions and, in case of a failure, we rollback only the failed batch and don't lose the previous batches. For each batch, the Persistent Context is flushed and cleared, therefore we maintain a thin Persistent Context. This way the code is not prone to memory errors and performance penalties caused by slow flushes.

**Key points:**
- beside all setting specific to batching inserts in MySQL, we need to set up in `application.properties` the following property: `spring.jpa.properties.hibernate.order_inserts=true`
- in your DAO layer, commit the database transaction after each batch execution

**Example without ordered inserts:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertOrder/batch%20inserts%20including%20associations%20no%20order%20of%20inserts.png)

**Example with ordered inserts:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertOrder/batch%20inserts%20including%20associations%20ordered%20inserts.png)

----------------------------------------------------------------------------------------------------------------------

215. **[Batch Inserts In Spring Boot Style And Batch Per Transaction](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsSpringStyleBatchPerTransaction)**

**Description:** Batch inserts (in MySQL) in Spring Boot style. This example commits the database transaction after each batch excecution. This way we avoid long-running transactions and, in case of a failure, we rollback only the failed batch and don't lose the previous batches.

**Key points:**
- in `application.properties` set `spring.jpa.properties.hibernate.jdbc.batch_size`
- in `application.properties` set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)
- in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)
- in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)
- in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))
- in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts
- in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since the Hibernate `IDENTITY` will cause insert batching to be disabled
- in your DAO layer, commit the database transaction after each batch execution
- if is not needed then ensure that Second Level Cache is disabled via `spring.jpa.properties.hibernate.cache.use_second_level_cache=false`
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsSpringStyle/batch%20inserts%20in%20spring%20boot%20style.png)

----------------------------------------------------------------------------------------------------------------------

216. **[`IN` Clause Parameter Padding](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootINListPadding)**
 
**Description:** This application is an example of using Hibernate `IN` cluase parameter padding. This way we can reduce the number of Execution Plans. Mainly, Hibernate is padding parameters as follows: 

- for 3 and 4 parameters -> it uses 4 bind parameters (2^2)
- for 5, 6, 7 and 8 parameters -> it uses 8 bind parameters (2^3)
- for 9, 10, 11, 12, 13, 14, 15 and 16 parameters -> it uses 16 parameters (2^4)
- ...

**Key points:**
- in `application.properties` set `spring.jpa.properties.hibernate.query.in_clause_parameter_padding=true`

----------------------------------------------------------------------------------------------------------------------

217. **[DTO Via Spring Data Class-Based Projections](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaClassBasedProjections)** 

**Description:** Fetch only the needed data from the database via Spring Data Projections (DTO). In this case, via class-based projections.

**Key points:**
- write an class (projection) containing a constructor, getters, setters, `equals()` and `hashCode()` only for the columns that should be fetched from the database
- write the proper query returning a `List<projection>`
- if it is applicable, limit the number of returned rows (e.g., via `LIMIT`) 
- in this example, we can use query builder mechanism built into Spring Data repository infrastructure
     
**Note:** Using projections is not limited to use query builder mechanism built into Spring Data repository infrastructure. We can fetch projections via JPQL or native queries as well. For example, in this [application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjectionsAndJpql) we use a JPQL.
     
**Output example (select first 2 rows; select only "name" and "age"):**
<a href="#"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaProjections/DTOs%20via%20Spring%20projections.png" align="center" height="251" width="658" ></a>

----------------------------------------------------------------------------------------------------------------------

218. **[Session-Level Batching (Hibernate 5.2 or Higher) in MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsViaSession)**

**Description:** Batch inserts via Hibernate session-level batching (Hibernate 5.2 or higher) in MySQL. This example commits the database transaction after each batch excecution. This way we avoid long-running transactions and, in case of a failure, we rollback only the failed batch and don't lose the previous batches. For each batch, the Persistent Context is flushed and cleared, therefore we maintain a thin Persistent Context. This way the code is not prone to memory errors and performance penalties caused by slow flushes.

**Key points:**
- in `application.properties` set `spring.jpa.properties.hibernate.generate_statistics` (just to check that batching is working)
- in `application.properties` set JDBC URL with `rewriteBatchedStatements=true` (optimization for MySQL)
- in `application.properties` set JDBC URL with `cachePrepStmts=true` (enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled)
- in `application.properties` set JDBC URL with `useServerPrepStmts=true` (this way you switch to server-side prepared statements (may lead to signnificant performance boost))
- in case of using a parent-child relationship with cascade persist (e.g. one-to-many, many-to-many) then consider to set up `spring.jpa.properties.hibernate.order_inserts=true` to optimize the batching by ordering inserts
- in entity, use the [assigned generator](https://vladmihalcea.com/how-to-combine-the-hibernate-assigned-generator-with-a-sequence-or-an-identity-column/) since MySQL `IDENTITY` will cause insert batching to be disabled
- the Hibernate `Session` is obtained by un-wrapping it via `EntityManager#unwrap(Session.class)`
- the batching size is set via `Session#setJdbcBatchSize(Integer size)` and get via `Session#getJdbcBatchSize()`
- in your DAO layer, commit the database transaction after each batch execution
- if is not needed, then ensure that Second Level Cache is disabled via `spring.jpa.properties.hibernate.cache.use_second_level_cache=false`
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertsViaSession/batch%20inserts%20via%20Session.png)

----------------------------------------------------------------------------------------------------------------------

219. **[Use Read-Only Entity Whenever You Plan To Propagate Entity Changes To The Database In A Future Persistent Context](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootReadOnlyQueries)**
 
**Description:** This application highlights the difference betweeen loading entities in *read-write* vs. *read-only* mode. If you plan to modify the entities in a future Persistent Context then fetch them as *read-only* in the current Persistent Context.

**Key points:**
- in the current Persistent Context, fetch entities in *read-only* mode 
- modifiy the entities in the current Persistent Context or in detached state (the potential modifications done in the current Persistent Context will not be propagated to the database at flush time)
- in a subsequent Persistent Context, merge the detached entity and propagate changes to the database

**Note:** If you never plan to modify the fetched result set then use DTO (e.g., Spring projection), not *read-only* entities.

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

220. **[How To Publish Domain Events From Aggregate Root](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDomainEvents)**
 
**Note:** Domain events should be used with extra-caution! The best practices for using them are revealed in my book, [Spring Boot Persistence Best Practices](https://www.apress.com/us/book/9781484256251).
 
**Description:** Starting with Spring Data Ingalls release publishing domain events by aggregate roots becomes easier. Entities managed by repositories are aggregate roots. In a Domain-Driven Design application, these aggregate roots usually publish domain events. Spring Data provides an annotation `@DomainEvents` you can use on a method of your aggregate root to make that publication as easy as possible. A method annotated with `@DomainEvents` is automatically invoked by Spring Data whenever an entity is saved using the right repository. Moreover, Spring Data provides the  `@AfterDomainEventsPublication` annotation to indicate the method that should be automatically called for clearing events after publication. Spring Data Commons comes with a convenient template base class (`AbstractAggregateRoot`) to help to register domain events and is using the publication mechanism implied by `@DomainEvents` and `@AfterDomainEventsPublication`. The events are registered by calling the `AbstractAggregateRoot.registerEvent()` method. The registered domain events are published if we call one of the *save* methods (e.g., `save()`) of the Spring Data repository and cleared after publication.

This is a sample application that relies on `AbstractAggregateRoot` and its `registerEvent()` method. We have two entities, `Book` and `BookReview` involved in a lazy-bidirectional `@OneToMany` association. A new book review is saved in `CHECK` status and a `CheckReviewEvent` is published. This event handler is responsible to check the review grammar, content, etc and switch the review status from `CHECK` to `ACCEPT` or `REJECT` and propagate the new status to the database. So, this event is registered before saving the book review in `CHECK` status and is published automatically after we call the `BookReviewRepository.save()` method. After publication, the event is cleared.

**Key points:**
- the entity (aggregate root) that publish events should extend `AbstractAggregateRoot` and provide a method for registering events
- here, we register a single event (`CheckReviewEvent`), but more can be registered 
- event handling take place is `CheckReviewEventHandler` in an asynchronous manner via `@Async`

----------------------------------------------------------------------------------------------------------------------

221. **[How To Use Hibernate Query Plan Cache](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootQueryPlanCache)**
 
**Description:** This application is an example of testing the Hibernate Query Plan Cache (QPC). Hibernate QPC is enabled by default and, for entity queries (JPQL and Criteria API), the QPC has a size of 2048, while for native queries it has a size of 128. Pay attention to alter these values to accommodate all queries
 executed by your application. If the number of exectued queries is higher than the QPC size (especially for entity queries) then you will start to experiment performance penalties caused by entity compilation time added for each query execution. 
 
 In this application, you can adjust the QPC size in `application.properties`. Mainly, there are 2 JPQL queries and a QPC of size 2. Switching from size 2 to size 1 will cause the compilation of one JPQL query at each execution. Measuring the times for 5000 executions using a QPC of size 2, respectively 1 reveals the importance of QPC in terms of time.

**Key points:**
- for JPQL and Criteria API you can set the QPC via `hibernate.query.plan_cache_max_size`
- for native queries you can set the QPC via `hibernate.query.plan_parameter_metadata_max_size`

----------------------------------------------------------------------------------------------------------------------

222. **[How To Cache Entities And Query Results In Second Level Cache (EhCache)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHibernateSLCEhCacheKickoff)**

**Description:** This is a SpringBoot application that enables Hibernate Second Level Cache and EhCache provider. It contains an example of caching entities and an example of caching a query result.

**Key points:**
- enable Second Level Cache (`EhCache`)
- rely on `@Cache`
- rely on JPA hint `HINT_CACHEABLE`

----------------------------------------------------------------------------------------------------------------------

223. **[Spring Boot Caching Kickoff](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSpringCacheEhCacheKickoff)**

**Description:** This is a SpringBoot application representing a kickoff application for Spring Boot caching and `EhCache`.

**Key points:**
- setup `EhCache`
- rely on Spring caching annotations

----------------------------------------------------------------------------------------------------------------------

224. **[How To Fetch Entity Via `SqlResultSetMapping` And `NamedNativeQuery`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSqlResultSetMappingAndNamedNativeQueryEntity)**
 
**Note:** If you want to rely on the `{EntityName}.{RepositoryMethodName}` naming convention for simply creating in the repository interface methods with the same name as of native named query then skip this application and [check this one](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSqlResultSetMappingAndNamedNativeQueryEntity2).
 
**Description:** This is a sample application of using `SqlResultSetMapping`, `NamedNativeQuery` and `EntityResult` for fetching single entity and multiple entities as `List<Object[]>`.
 
**Key points:**
- use `SqlResultSetMapping`, `NamedNativeQuery` and `EntityResult`

----------------------------------------------------------------------------------------------------------------------

225. **[How To Load Multiple Entities By Id Via Specification](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLoadMultipleIdsSpecification)**

**Description:** This is a SpringBoot application that loads multiple entities by id via a `@Query` based on the `IN` operator and via `Specification`.

**Key points:**
- for using the `IN` operator in a `@Query` simply add the query in the proper repository
- for using a `Specification` rely on `javax.persistence.criteria.Root.in()`

----------------------------------------------------------------------------------------------------------------------

226. **[How To Fetch DTO Via A Custom `ResultTransformer`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoCustomResultTransformer)**
 
**Description:** Fetching more *read-only* data than needed is prone to performance penalties. Using DTO allows us to extract only the needed data. Sometimes, we need to fetch a DTO made of a subset of properties (columns) from a parent-child association. For such cases, we can use SQL `JOIN` that can pick up the desired columns from the involved tables. But, `JOIN` returns an `List<Object[]>` and most probably you will need to represent it as a `List<ParentDto>`, where a `ParentDto` instance has a `List<ChildDto>`. For such cases, we can rely on a custom Hibernate `ResultTransformer`. This application is a sample of writing a custom `ResultTransformer`.

**Key points:**
- implement the `ResultTransformer` interface

----------------------------------------------------------------------------------------------------------------------

227. **[How To Efficiently Chunk A Java List](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/ChunkList)**
 
**Description:** Is a common scenario to have a big `List` and to need to chunk it in multiple smaller `List` of given size. For example, if we want to employee a concurrent batch implementation we need to give to each thread a sublist of items. Chunking a list can be done via Google Guava, `Lists.partition(List list, int size)` [method](https://guava.dev/releases/22.0/api/docs/com/google/common/collect/Lists.html#partition-java.util.List-int-) or Apache Commons Collections, `ListUtils.partition(List list, int size)` [method](https://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/ListUtils.html#partition(java.util.List,%20int)). But, it can be implemented in plain Java as well. This application exposes 6 ways to do it. The trade-off is between the speed of implementation and speed of execution. For example, while the implementation relying on grouping collector is not performing very well, it is quite simple and fast to write it.

**Key points:**
- the fastest execution is provided by `Chunk.java` class which relies on the built-in `List.subList()` method
     
**Time-performance trend graphic for chunking 500, 1_000_000, 10_000_000 and 20_000_000 items in lists of 5 items:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/ChunkList/head-to-head.png)

----------------------------------------------------------------------------------------------------------------------

228. **[How To Implement Complex Data Integrity Constraints And Rules](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDatabaseTriggers)**
 
**Description:** Consider the `Book` and `Chapter` entities. A book has a maximum accepted number of pages (`book_pages`) and the author should not exceed this number. When a chapter is ready for review, the author is submitting it. At this point, the publisher should check that the currently total number of pages doesn't exceed the allowed `book_pages`:

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDatabaseTriggers/MySQL_Trigger_For_Complex_Data_Integrity_Checks.png)

This kind of checks or constraints are easy to implement via database triggers. This application relies on a MySQL trigger to empower our complex contraint (`check_book_pages`).

**Key points:**
- define a MySQL trigger that run after each insert (if you want to run it after each update as well then extract the trigger logic into a function and call it from two triggers - this is specific to MySQL, while is PostgreSQL we have `AFTER INSERT OR AFTER UPDATE`)

----------------------------------------------------------------------------------------------------------------------

229. **[How To Check If A Transient Entity Exists In The Database Via Spring Query By Example (QBE)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootExampleApi)**

**Description:** This application is an example of using Spring Data Query By Example (QBE) to check if a transient entity exists in the database. Consider the `Book` entity and a Spring controller that exposes an endpoint as: `public String checkBook(@Validated @ModelAttribute Book book, ...)`. Beside writting an explicit JPQL, we can rely on Spring Data Query Builder mechanism or, even better, on Query By Example (QBE) API. In this context, QBE API is quite useful if the entity has a significant number of attributes and:

- for all attributes, we need a head-to-head comparison of each attribute value to the corresponding column value
- for a subset of attributes, we need a head-to-head comparison of each attribute value to the corresponding column value
- for a subset of attributes, we return true at first match between an attribute value and the corresponding column value
- any other scenario

**Key points:**
- the repository, `BookRepository` extends `QueryByExampleExecutor`
- the application uses `<S extends T> boolean exists(Example<S> exmpl)` with the proper *probe* (an entity instance populated with the desired fields values)
- moreover, the *probe* relies on `ExampleMatcher` which defines the details on how to match particular fields

**Note:** Do not conclude that Query By Example (QBE) defines only the `exists()` method. Check out all methods [here](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/query/QueryByExampleExecutor.html).
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

230. **[Best Way To Use `@Transactional`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTransactionalInRepository)**
 
**Description:** This application is meant to highlight that the best place to use `@Transactional` for user defined query-methods is in repository interface, and afterwards, depending on situation, on service-methods level.

**Key points:**
- this application is dissected in my book, **Spring Boot Persistence Best Practices**. 

-----------------------------------------------------------------------------------------------------------------------    

231. **[How To Use JPA `JOINED` Inheritance Strategy And Visitor Design Pattern](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinedAndVisitor)**
 
**Description:** This application is an example of using JPA `JOINED` inheritance strategy and [Visitor](https://sourcemaking.com/design_patterns/visitor/java/1) pattern.

**Key points:**
- this application allows us to define multiple visitors and apply the one that we want

-----------------------------------------------------------------------------------------------------------------------    

232. **[How To Use JPA `JOINED` Inheritance Strategy And Strategy Design Pattern](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinedAndStrategy)**
 
**Description:** This application is an example of using JPA `JOINED` inheritance strategy and [Strategy](https://sourcemaking.com/design_patterns/strategy) pattern.

**Key points:**
- this application allows us to define multiple strategies and apply the one that we want

-----------------------------------------------------------------------------------------------------------------------    

233. **[How Spring Transaction Propagation Work](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTransactionPropagation)**
 
**Description:** This folder holds several applications that shows how each Spring transaction propagation works.

**Key points:**
- it is strongly recommended to follow these examples in the context of my book, [Spring Boot Persistence Best Practices](https://www.apress.com/us/book/9781484256251).

-----------------------------------------------------------------------------------------------------------------------

234. **[How To Use JPA `GenerationType.AUTO` And UUID Identifiers](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAutoUUID)**
 
**Description:** This application is an example of using the JPA `GenerationType.AUTO` for assigning automatically UUID identifiers.

**Key points:**
- store UUID in a `BINARY(16)` column

-----------------------------------------------------------------------------------------------------------------------

235. **[How To Manually Assign UUID Identifiers](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootAssignedUUID)**
 
**Description:** This application is an example of manually assigning UUID identifiers.

**Key points:**
- store UUID in a `BINARY(16)` column

-----------------------------------------------------------------------------------------------------------------------

236. **[How To Use Hibernate `uuid2` For Generating UUID Identifiers](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootUUID2)**
 
**Description:** This application is an example of using the Hibernate RFC 4122 compliant UUID generator, `uuid2`.

**Key points:**
- store UUID in a `BINARY(16)` column

-----------------------------------------------------------------------------------------------------------------------

237. **[How Hibernate Session-Level Repeatable Reads Works](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSessionRepeatableReads)**

**Description:** This Spring Boot application is a sample that reveals how Hibernate session-level repeatable reads works. Persistence Context guarantees session-level repeatable reads. Check out how it works.

**Key points:**
- rely on two transactions implemented via `TransactionTemplate`

**Note:** For a detailed explanation of this application consider my book, [Spring Boot Persistence Best Practices](https://www.apress.com/us/book/9781484256251)

-----------------------------------------------------------------------------------------------------------------------

238. **[Why To Avoid Hibernate-specific `hibernate.enable_lazy_load_no_trans`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnableLazyLoadNoTrans)**
 
**Description:** This application is an example of using Hibernate-specific `hibernate.enable_lazy_load_no_trans`. Check out the application log to see how transactions and database connections are used. 

**Key points:**
- always avoid Hibernate-specific `hibernate.enable_lazy_load_no_trans`

-----------------------------------------------------------------------------------------------------------------------

239. **[The Best Way To Clone Entities](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCloneEntity)**
 
**Description:** This application is an example of cloning entities. The best way to achieve this goal relies on copy-constructors. This way we can control what we copy. Here we use a bidirectional-lazy `@ManyToMany` association between `Author` and `Book`.

**Key points:**
- clone an `Author` (only the `genre`) and associate the corresponding books
- clone an `Author` (only the `genre`) and clone the books as well

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

240. **[How To Include In The `UPDATE` Statement Only The Modified Columns Via Hibernate `@DynamicUpdate`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDynamicUpdate)**

**Description:** This application is an example of using the Hibernate-specific, `@DynamicUpdate`. By default, even if we modify only a subset of columns, the triggered `UPDATE` statements will include all columns. By simply annotating the corresponding entity at class-level with `@DynamicUpdate` the generated `UPDATE` statement will include only the modified columns. 

**Key points:**
- pro: avoid updating unmodified indexes (useful for heavy indexing)
- con: cannot reuse the same `UPDATE` for different subsets of columns via JDBC statements caching (each triggered `UPDATE` string will be cached and reused accordingly)

-----------------------------------------------------------------------------------------------------------------------    

241. **[How To Log Spring Data JPA Repository Query-Method Execution Time](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRepoIntercept)**
 
**Description:** This application is an example of logging execution time for a repository query-method.

**Key points:**
- write an AOP component (see `RepositoryProfiler`)

-----------------------------------------------------------------------------------------------------------------------    

242. **[How To Take Control Before/After Transaction Commits/Completes Via Callbacks](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTransactionCallback)**
 
**Description:** This application is an example of using the `TransactionSynchronizationAdapter` for overriding `beforeCommit()`, `beforeCompletion()`, `afterCommit()` and `afterCompletion()` callbacks globally (application-level) and at method-level.

**Key points:**
- application-level: write an AOP component (see `TransactionProfiler`)
- method-level: use `TransactionSynchronizationManager.registerSynchronization()`

-----------------------------------------------------------------------------------------------------------------------    

243. **[How To Fetch DTO Via `SqlResultSetMapping` And `NamedNativeQuery` Using `{EntityName}.{RepositoryMethodName}` Naming Convention](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSqlResultSetMappingAndNamedNativeQuery2)**
 
**Description:** Fetching more data than needed is prone to performance penalities. Using DTO allows us to extract only the needed data. In this application we rely on `SqlResultSetMapping`, `NamedNativeQuery` and the `{EntityName}.{RepositoryMethodName}` naming convention. This convention allows us to create in the repository interface methods with the same name as of native named query.
 
**Key points:**
- use `SqlResultSetMapping`, `NamedNativeQuery`
- for using Spring Data Projections check this [item](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)

-----------------------------------------------------------------------------------------------------------------------    

244. **[How To Fetch Entity Via `SqlResultSetMapping` And `NamedNativeQuery` Using `{EntityName}.{RepositoryMethodName}` Naming Convention](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSqlResultSetMappingAndNamedNativeQueryEntity2)**
 
**Description:** This is a sample application of using `SqlResultSetMapping`, `NamedNativeQuery` and `EntityResult` for fetching single entity and multiple entities as `List<Object[]>`. In this application we rely on the `{EntityName}.{RepositoryMethodName}` naming convention. This convention allows us to create in the repository interface methods with the same name as of native named query.
 
**Key points:**
- use `SqlResultSetMapping`, `NamedNativeQuery` and `EntityResult`

-----------------------------------------------------------------------------------------------------------------------    

245. **[How To Use JPA Named Queries `@NamedQuery` And Spring Projection (DTO)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSpringProjectionAnnotatedNamedQuery)**
 
**Description:** This application is an example of combining JPA named queries `@NamedQuery` and Spring projections (DTO). For queries names we use the `{EntityName}.{RepositoryMethodName}` naming convention. This convention allows us to create in the repository interface methods with the same name as of named query.
 
**Key points:**
- define the named queries
- define the proper Spring projection

-----------------------------------------------------------------------------------------------------------------------    

246. **[How To Use JPA Named Native Queries `@NamedNativeQuery` And Spring Projection (DTO)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSpringProjectionAnnotatedNamedNativeQuery)**
 
**Description:** This application is an example of combining JPA named native queries `@NamedNativeQuery` and Spring projections (DTO). For queries names we use the `{EntityName}.{RepositoryMethodName}` naming convention. This convention allows us to create in the repository interface methods with the same name as of named native query.
 
**Key points:**
- define the named native queries
- define the proper Spring projection

-----------------------------------------------------------------------------------------------------------------------    

247. **[How To Use JPA Named Queries Via a Properties File](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedQueriesInPropertiesFile)**
  
**Description:** JPA named (native) queries are commonly written via `@NamedQuery` and `@NamedNativeQuery` annotations in entity classes. Spring Data allows us to write our named (native) queries in a typical `*.properties` file inside the `META-INF` folder of your classpath. This way, we avoid modifying our entities. This application shows you how to do it.

**Warning:** Cannot use native queries with dynamic sorting (`Sort`). Nevertheless, using `Sort` in named queries works fine. Moreover, using `Sort` in `Pageable` works fine for both, named queries and named native queries. At least this is how it behave in Spring Boot 2.2.2. From this point of view, this approach is better than using `@NamedQuery`/`@NamedNativeQuery` or `orm.xml` file.
 
**Key points:**
- define the named (native) queries in a file, `META-INF/jpa-named-queries.properties`
- follow the Spring `{EntityName}.{RepositoryMethodName}` naming convention for a quick and slim implementation

-----------------------------------------------------------------------------------------------------------------------    

248. **[How To Use JPA Named Queries Via The `orm.xml` File](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedQueriesInOrmXml)**
  
**Description:** JPA named (native) queries are commonly written via `@NamedQuery` and `@NamedNativeQuery` annotations in entity classes. Spring Data allows us to write our named (native) queries in a typical `orm.xml` file inside the `META-INF` folder of your classpath. This way, we avoid modifying our entities. This application shows you how to do it.

**Warning:** Pay attention that, via this approach, we cannot use named (native) queries with dynamic sorting (`Sort`). Using `Sort` in `Pageable` is ignored, therefore you need to explicitly add `ORDER BY` in the queries. At least this is how it behave in Spring Boot 2.2.2. A better approach relies on using a [properties](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedQueriesInPropertiesFile) file for listing the named (native) queries. In this case, dynamic `Sort` works for named queries, but not for named native queries. Using `Sort` in `Pageable` works as expected in named (native) queries. 
 
**Key points:**
- define the named (native) queries in a file, `META-INF/orm.xml`
- follow the Spring `{EntityName}.{RepositoryMethodName}` naming convention for a quick and slim implementation

-----------------------------------------------------------------------------------------------------------------------    

249. **[How To Use JPA Named Queries Via Annotations](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedQueriesViaAnnotations)**
  
**Description:** JPA named (native) queries are commonly written via `@NamedQuery` and `@NamedNativeQuery` annotations in entity classes.  This application shows you how to do it. 

**Warning:** Pay attention that, via this approach, we cannot use named (native) queries with dynamic sorting (`Sort`). Using `Sort` in `Pageable` is ignored, therefore you need to explicitly add `ORDER BY` in the queries. At least this is how it behave in Spring Boot 2.2.2. A better approach relies on using a [properties](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNamedQueriesInPropertiesFile) file for listing the named (native) queries. In this case, dynamic `Sort` works for named queries, but not for named native queries. Using `Sort` in `Pageable` works as expected in named (native) queries. And, you don't need to modify/pollute entitites with the above annotations.
 
**Key points:**
- use `@NamedQuery` and `@NamedNativeQuery` annotations in entity classes
- follow the Spring `{EntityName}.{RepositoryMethodName}` naming convention for a quick and slim implementation
- avoid `Sort` and `Pageable`

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

250. **[How To Use JPA Named Queries Via Properties File And Spring Projection (DTO)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSpringProjectionPropertiesNamedQuery)**
 
**Description:** This application is an example of combining JPA named queries listed in a properties file and Spring projections (DTO). For queries names we use the `{EntityName}.{RepositoryMethodName}` naming convention. This convention allows us to create in the repository interface methods with the same name as of named query.
  
**Key points:**
- define the named queries in a properties file (e.g., `jpa-named-queries.properties`) in a folder named `META-INF` the application classpath 
- define the proper Spring projection

-----------------------------------------------------------------------------------------------------------------------    

251. **[How To Use JPA Named Native Queries Via Properties File And Spring Projection (DTO)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSpringProjectionPropertiesNamedNativeQuery)**
 
**Description:** This application is an example of combining JPA named native queries listed in a properties file and Spring projections (DTO). For queries names we use the `{EntityName}.{RepositoryMethodName}` naming convention. This convention allows us to create in the repository interface methods with the same name as of named native query.
  
**Key points:**
- define the named native queries in a properties file (e.g., `jpa-named-queries.properties`) in a folder named `META-INF` the application classpath 
- define the proper Spring projection

-----------------------------------------------------------------------------------------------------------------------    

252. **[How To Use JPA Named Queries Via `orm.xml` File And Spring Projection (DTO)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSpringProjectionOrmXmlNamedQuery)**
 
**Description:** This application is an example of combining JPA named queries listed in `orm.xml` file and Spring projections (DTO). For queries names we use the `{EntityName}.{RepositoryMethodName}` naming convention. This convention allows us to create in the repository interface methods with the same name as of named query.
  
**Key points:**
- define the named queries in `orm.xml` file in a folder named `META-INF` the application classpath 
- define the proper Spring projection

-----------------------------------------------------------------------------------------------------------------------    

253. **[How To Use JPA Named Native Queries Via `orm.xml` File And Spring Projection (DTO)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSpringProjectionOrmXmlNamedNativeQuery)**
 
**Description:** This application is an example of combining JPA named native queries listed in `orm.xml` file and Spring projections (DTO). For queries names we use the `{EntityName}.{RepositoryMethodName}` naming convention. This convention allows us to create in the repository interface methods with the same name as of named native query.
  
**Key points:**
- define the named native queries in `orm.xml` file in a folder named `META-INF` the application classpath 
- define the proper Spring projection

-----------------------------------------------------------------------------------------------------------------------    

254. **[How To Dto Via Named Native Query And Result Set Mapping Via `orm.xml`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoSqlResultSetMappingNamedNativeQueryOrmXml)**
 
**Description:** Fetching more data than needed is prone to performance penalities. Using DTO allows us to extract only the needed data. In this application we rely on named native queries and result set mapping via `orm.xml` and the `{EntityName}.{RepositoryMethodName}` naming convention. This convention allows us to create in the repository interface methods with the same name as of native named query.
 
**Key points:**
- use `<named-native-query/>` and `<sql-result-set-mapping/>` to map the native query to `AuthorDto` class

-----------------------------------------------------------------------------------------------------------------------    

255. **[How To Use Spring Projections(DTO) And Cross Joins](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaCrossJoins)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaCrossJoins/DTO%20via%20cross%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTO) and cross joins written via JPQL and native SQL (for MySQL).

**Key points:**
- define two entities (e.g., `Book` and `Format` 
- populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)
- write interfaces (Spring projections) that contains getters for the columns that should be fetched from the database (e.g., check `BookTitleAndFormatType.java`)
- write cross joins queries using JPQL/SQL

-----------------------------------------------------------------------------------------------------------------------    

256. **[Calling Stored Procedure That Returns A Result Set Via `JdbcTemplate` And `BeanPropertyRowMapper`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCallStoredProcedureJdbcTemplateBeanPropertyRowMapper)**
 
**Description:** This application is an example of calling a MySQL stored procedure that returns a result set via `JdbcTemplate` and `BeanPropertyRowMapper`.
 
**Key points:**
- rely on `JdbcTemplate`, `SimpleJdbcCall` and `BeanPropertyRowMapper`

-----------------------------------------------------------------------------------------------------------------------    

257. **[Defining Entity Listener Class Via `@EntityListeners`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEntityListener)**

**Description:** This application is a sample of using the JPA `@MappedSuperclass` and `@EntityListeners` with JPA callbacks.

**Key points:**
- the *base class* ,`Book`, is not an entity, it can be `abstract`, and is annotated with `@MappedSuperclass` and `@EntityListeners(BookListener.class)`
- `BookListener` defines JPA callbacks (e.g., `@PrePersist`)
- subclasses of the *base class* are mapped in tables that contains columns for the inherited attributes and for their own attibutes
- when any entity that is a subclass of `Book` is persisted, loaded, updated, etc the corresponding JPA callbacks are called

-----------------------------------------------------------------------------------------------------------------------    

258. **[Improper Usage Of `@Fetch(FetchMode.JOIN)` May Causes N+1 Issues](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFetchJoinAndQueries)**

**Advice:** Always evaluate `JOIN FETCH` and entities graphs before deciding to use `FetchMode.JOIN`. The `FetchMode.JOIN` fetch mode always triggers an `EAGER` load so the children are loaded when the parents are. Beside this drawback, `FetchMode.JOIN` may return duplicate results. You’ll have to remove the duplicates yourself (e.g. storing the result in a `Set`). But, if you decide to go with `FetchMode.JOIN` at least pay attention to avoid N+1 issues discussed below.

**Note:** Let's assume three entities, `Author`, `Book` and `Publisher`. Between `Author` and `Book` there is a bidirectional-lazy `@OneToMany` association. Between `Author` and `Publisher` there is a unidirectional-lazy `@ManyToOne`. Between `Book` and `Publisher` there is no association.

Now, we want to fetch a book by id (`BookRepository#findById()`), including its author, and the author's publisher. In such cases, using Hibernate fetch mode, `@Fetch(FetchMode.JOIN)` works as expected. Using `JOIN FETCH` or entity graph is also working as expected.

Next, we want to fetch all books (`BookRepository#findAll()`), including their authors, and the authors publishers. In such cases, using Hibernate fetch mode, `@Fetch(FetchMode.JOIN)` will cause N+1 issues. It will not trigger the expected `JOIN`. In this case, using `JOIN FETCH` or entity graph should be used.

**Key points:**
- using Hibernate fetch mode, `@Fetch(FetchMode.JOIN)` doesn't work for query-methods
- Hibernate fetch mode, `@Fetch(FetchMode.JOIN)` works in cases that fetches the entity by id (primary key) like using `EntityManager#find()`, Spring Data, `findById()`, `findOne()`.

-----------------------------------------------------------------------------------------------------------------------    

259. **[How To Efficiently Assign A Database Temporary Ranking Of Values To Rows via `RANK()`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRankFunction)**
 
**Description:** This application is an example of assigning a database temporary ranking of values to rows via the window function, `RANK()`. This window function is available in almost all databases, and starting with version 8.x is available in MySQL as well.

**Key points:**
- commonly, you don't need to fetch in the result set the temporary ranking of values produced by `RANK()` (you will use it internally, in the query, usually in the `WHERE` clause and CTEs), but, this time, let's write a Spring projection (DTO) that contains a getter for the column generated by `RANK()` as well
- write several native querys relying on `RANK()` window function
     
**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootRankFunction/assign%20ranking%20to%20rows.png)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

260. **[How To Efficiently Assign A Database Temporary Ranking Of Values To Rows via `DENSE_RANK()`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDenseRankFunction)**
 
**Description:** This application is an example of assigning a database temporary ranking of values to rows via the window function, `DENSE_RANK()`. In comparison with the `RANK()` window function, `DENSE_RANK()` avoid gaps within partition. This window function is available in almost all databases, and starting with version 8.x is available in MySQL as well.

**Key points:**
- commonly, you don't need to fetch in the result set the temporary ranking of values produced by `DENSE_RANK()` (you will use it internally, in the query, usually in the `WHERE` clause and CTEs), but, this time, let's write a Spring projection (DTO) that contains a getter for the column generated by `DENSE_RANK()` as well
- write several native querys relying on `DENSE_RANK()` window function
     
**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDenseRankFunction/assign%20ranking%20to%20rows.png)

-----------------------------------------------------------------------------------------------------------------------    

261. **[How To Efficiently Distribute The Number Of Rows In The Specified (N) Number Of Groups Via `NTILE(N)`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNTilleFunction)**
 
**Description:** This application is an example of distributing the number of rows in the specified (N) number of groups via the window function, `NTILE(N)`. This window function is available in almost all databases, and starting with version 8.x is available in MySQL as well.

**Key points:**
- commonly, you don't need to fetch in the result set the temporary ranking of values produced by `NTILE()` (you will use it internally, in the query, usually in the `WHERE` clause and CTEs), but, this time, let's write a Spring projection (DTO) that contains a getter for the column generated by `NTILE()` as well
- write several native querys relying on `NTILE()` window function
     
**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootNTilleFunction/grouping%20rows%20via%20NTILE.png) 

-----------------------------------------------------------------------------------------------------------------------    

262. **[How To Write Derived Count And Delete Queries](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDerivedCountAndDelete)**

**Description:** Spring Data comes with the Query Builder mechanism for JPA that is capable to interpret a query method name (known as a derived query) and convert it into a SQL query in the proper dialect. This is possible as long as we respect the naming conventions of this mechanism. Beside the well-known query of type `find...`, Spring Data supports derived count queries and derived delete queries.

**Key points:**
- a derived count query starts with `count...` (e.g., `long countByGenre(String genre)`) - Spring Data will generate a `SELECT COUNT(...) FROM ...` query 
- a derived delete query can return the number of deleted records or the list of the deleted records
- a derived delete query that returns the number of deleted records starts with `delete...` or `remove...` and returns `long` (e.g., `long deleteByGenre(String genre)`) - Spring Data will trigger first a `SELECT` to fetch entities in the Persistence Context, and, afterwards, it triggers a `DELETE` for each entity that must be deleted 
- a derived delete query that returns the list of deleted records starts with `delete...` or `remove...` and returns `List<entity>` (e.g., `List<Author> removeByGenre(String genre)`) - Spring Data will trigger first a `SELECT` to fetch entities in the Persistence Context, and, afterwards, it triggers a `DELETE` for each entity that must be deleted 

-----------------------------------------------------------------------------------------------------------------------    

263. **[Working With Spring Data Property Expressions](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPropertyExpressions)** 
 
**Description:** Property expressions can refer to a direct property of the managed entity. However, you can also define constraints by traversing nested properties. This application is a sample of traversing nested properties for fetching entities and DTOs.

**Key points:**
- Assume an `Author` has several `Book` and each book has several `Review` (between `Author` and `Book` there is a bidirectional-lazy `@oneToMany` association, and, between `Book` and `Review` there is also a bidirectional-lazy `@OneToMany` association)
- Assume that we fetched a `Review` and we want to know the `Author` of the `Book` that has received this `Review`
- via property expressions, we can write in `AuthorRepository` the following query that will be processed by the Spring Data Query Builder mechanism: `Author findByBooksReviews(Review review);`
- Behind the scene Spring Data will produce a `SELECT` with two `LEFT JOIN`
- In this case, the method creates the property traversal `books.reviews`. The algorithm starts by interpreting the entire part (`BooksReviews`) as the property and checks the domain class for a property with that name (uncapitalized). If the algorithm succeeds, it uses that property. If not, the algorithm splits up the source at the camel case parts from the right side into a head and a tail and tries to find the corresponding property — in our example, `Books` and `Reviews`. If the algorithm finds a property with that head, it takes the tail and continues building the tree down from there, splitting the tail up in the way just described. If the first split does not match, the algorithm moves the split point to the left and continues.
- Although this algorithm should work for most cases, it is possible for the algorithm to select the wrong property. Suppose the `Author` class has an `booksReview` property as well. The algorithm would match in the first split round already, choose the wrong property, and fail (as the type of `booksReview` probably has no code property). To resolve this ambiguity you can use _ inside your method name to manually define traversal points. So our method name would be as follows: `Author findByBooks_Reviews(Review review);`
- More examples (including DTOs) are available in the application

-----------------------------------------------------------------------------------------------------------------------    

264. **[The Best Way To Fetch Parent And Children In Different Queries](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootParentChildSeparateQueries)** 

**Note:** Fetching *read-only* data should be done via DTO, not managed entities. But, there is no tragedy to fetch read-only entities in a context as follows:

- we need all attributes of the entity (so, a DTO just mirrors an entity)
- we manipulate a small number of entities (e.g., an author with several books)
- we use `@Transactional(readOnly = true)`

Under these circumstances, let's tackle a common case that I saw quite a lot. There is even an SO answer about it (don't do this):

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootParentChildSeparateQueries/Fetch%20Parent%20And%20Children%20In%20Different%20Queries.png)

**Description:** Let's assume that `Author` and `Book` are involved in a bidirectional-lazy `@OneToMany` association. Imagine an user that loads a certain `Author` (without the associated `Book`). The user may be interested  or not in the `Book`, therefore, we don't load them with the `Author`. If the user is interested in the `Book` then he will click a button of type, *View books*. Now, we have to return the `List<Book>` associated to this `Author`.

So, at first request (query), we fetch an `Author`. The `Author` is detached. At second request (query), we want to load the `Book` associated to this `Author`. But, we don't want to load the `Author` again (for example, we don't care about *lost updates* of `Author`), we just want to load the associated `Book` in a single `SELECT`. A common (not recommended) approach is to load the `Author` again (e.g., via `findById(author.getId())`) and call the `author.getBooks()`. But, this end up in two `SELECT` statements. One `SELECT` for loading the `Author`, and another `SELECT` after we *force* the collection initialization. We *force* collection initialization because it will not be initialize if we simply return it. In order to trigger the collection initialization the developer call `books.size()` or he rely on `Hibernate.initialize(books);`. 

But, we can avoid such solution by relying on an explicit JPQL or Query Builder property expressions. This way, there will be a single `SELECT` and no need to call `size()` or `Hibernate.initialize();`

**Key points:**
- use an explicit JPQL
- use Query Builder propery expressions

This item is detailed in my book, [Spring Boot Persistence Best Practices](https://www.amazon.com/gp/product/1484256255).

-----------------------------------------------------------------------------------------------------------------------    

265. **[How To Optimize The Merge Operation Using Update](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSaveAndMerge)** 
 
**Description:** Behind the built-in Spring Data `save()` there is a call of `EntityManager#persist()` or `EntityManager#merge()`. It is important to know this aspect in several cases. Among this cases, we have the entity update case (simple update or update batching). 

Consider `Author` and `Book` involved in a bidirectional-lazy `@OneToMany` association. And, we load an `Author`, detach it, update it in the *detached* state, and save it to the database via `save()` method. Calling `save()` will come with the following two issues resulting from calling `merge()` behind the scene:

- there will be two SQL statements, one `SELECT` (merge) and one `UPDATE`
- the `SELECT` will contain a `LEFT OUTER JOIN` to fetch the associated `Book` as well (we don't need the books!)

How about triggering only the `UPDATE` instead of this? The solution relies on calling `Session#update()`. Calling `Session.update()` requires to unwrap the `Session` via `entityManager.unwrap(Session.class)`.

**Key points:**
- calling `Session.update()` will trigger only the `UPDATE` (there is no `SELECT`)
- `Session.update()` works with *versioned* optimistic locking mechanism as well (so, *lost updates* are prevented)

-----------------------------------------------------------------------------------------------------------------------    

266. **[How To NOT Use Spring Data `Streamable`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootStreamable)**
 
**Description:** This application is a sample of fetching `Streamable<entity>` and `Streamable<dto>`. But, more important, this application contains three examples of how to **not** use `Streamable`. It is very tempting and comfortable to fetch a `Streamable` result set and chop it via `filter()`, `map()`, `flatMap()`, and so on until we obtain only the needed data instead of writing a query (e.g., JPQL) that fetches exactly the needed result set from the database. Mainly, we just throw away some of the fetched data to keep only the needed data. But, **is not advisable** to follow such practices because fetching more data than needed can cause significant performance penalties. 

Moreover, pay attention to combining two or more `Streamable` via the `and()` method. The returned result may be different from what you are expecting to see. Each `Streamable` produces a separate SQL statement and the final result set is a concatenation of the intermediate results sets (prone to duplicate values).

**Key points:**
- don't fetch more columns than needed just to drop a part of them (e.g., via `map()`)
- don't fetch more rows than needed just to throw away a part of it (e.g., via `filter()`)
- pay attention on combining `Streamable` via `and()`; each `Streamable` produces a separate SQL statement and the final result set is a concatenation of the intermediate results sets (prone to duplicate values)

-----------------------------------------------------------------------------------------------------------------------    

267. **[How To Return Custom `Streamable` Wrapper Types](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootWrapperTypeStreamable)**
 
**Description:** A common practice consists of exposing dedicated wrappers types for collections resulted after mapping a query result set. This way, on a single query execution, the API can return multiple results. After we call a query-method that return a collection, we can pass it to a wrapper class by manually instantiation of that wrapper-class. But, we can avoid the manually instantiation if the code respects the following key points.

**Key points:**
- the type implements `Streamable`
- the type exposes a constructor (used in this example) or a `static` factory method named `of(…)` or `valueOf(…)` taking `Streamable` as argument

----------------------------------------------------------------------------------------------------------------------- 

268. **[How To Use In Spring Boot JPA 2.1 Schema Generation And Data Loading](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSchemaGeneration)**
 
**Description:** JPA 2.1 come with schema generation features. This feature can setup the database or export the generated commands to a file. The parameters that we should set are:

- `spring.jpa.properties.javax.persistence.schema-generation.database.action`: Instructs the persistence provider how to setup the database. Possible values include: `none`, `create`, `drop-and-create`, `drop`

- `javax.persistence.schema-generation.scripts.action`: Instruct the persistence provider which scripts to create. Possible values include: `none`, `create`, `drop-and-create`, `drop`.

- `javax.persistence.schema-generation.scripts.create-target`: Indicate the target location of the create script generated by the persistence provider. This can be as a file URL or a `java.IO.Writer`.

- `javax.persistence.schema-generation.scripts.drop-target`: Indicate the target location of the drop script generated by the persistence provider. This can be as a file URL or a `java.IO.Writer`.

Moreover, we can instruct the persistence provider to load data from a file into the database via: `spring.jpa.properties.javax.persistence.sql-load-script-source`. The value of this property represents the file location and it can be a file URL or a `java.IO.Writer`.

**Key points:**
- the settings are available in `application.properties`

----------------------------------------------------------------------------------------------------------------------- 

269. **[How To Return A Map Result From A Spring Data Query Method](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootResultSetMap)**
 
**Description:** Sometimes, we need to write in repositories certain query-methods that return a `Map` instead of a `List` or a `Set`. For example, when we need a `Map<Id, Entity>` or we use `GROUP BY` and we need a `Map<Group, Count>`. This application shows you how to do it via `default` methods directly in repository.

**Key points:**
- rely on `default` methods and `Collectors.toMap()`

----------------------------------------------------------------------------------------------------------------------- 

270. **[How To Handle Entities Inheritance With Spring Data Repositories](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinTableRepositoryInheritance)**

**Description:** Consider one of the JPA inheritance strategies (e.g., `JOINED`). Handling entities inheritance With Spring Data repositories can be done as follows:

- [Single Table](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSingleTableRepositoryInheritance)
- [Joined](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinTableRepositoryInheritance)
- [Table per class](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTablePerClassRepositoryInheritance)
- [Mapped Superclass](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootMappedSuperclassRepository)

----------------------------------------------------------------------------------------------------------------------- 

271. **[Log Slow Queries Via Hibernate 5.4.5](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLogSlowQueries545)**

**Description:** This application is a sample of logging only slow queries via Hibernate 5.4.5, `hibernate.session.events.log.LOG_QUERIES_SLOWER_THAN_MS` property. A slow query is a query that has an execution time bigger than a specificed threshold in milliseconds.

**Key points:**
- in `application.properties` add `hibernate.session.events.log.LOG_QUERIES_SLOWER_THAN_MS`
   
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLogSlowQueries545/log%20slow%20queries%20Hibernate%205.4.5.png)

----------------------------------------------------------------------------------------------------------------------- 

272. **[DTO Via JDK14 Records And Spring Data Query Builder Mechanism](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoRecordConstructor)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTO allows us to extract only the needed data. In this application we rely on JDK14 Records feature and Spring Data Query Builder Mechanism.

From Openjdk JEP359:

*Records provide a compact syntax for declaring classes which are transparent holders for shallowly immutable data.*
 
**Key points:**
Define the `AuthorDto` as:

`public record AuthorDto(String name, int age) implements Serializable {}`

----------------------------------------------------------------------------------------------------------------------- 

273. **[How To Fetch DTO Via JDK14 Records, Constructor Expression and JPQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoRecordConstructorExpression)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTO allows us to extract only the needed data. In this application we rely on JDK 14 Records, Constructor Expression and JPQL.

From Openjdk JEP359:

*Records provide a compact syntax for declaring classes which are transparent holders for shallowly immutable data.*

**Key points:**

Define the `AuthorDto` as:

`public record AuthorDto(String name, int age) implements Serializable {}`

----------------------------------------------------------------------------------------------------------------------- 

274. **[How To Fetch DTO Via JDK14 Records And A Custom `ResultTransformer`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoRecordResultTransformer)**
  
**Description:** Fetching more *read-only* data than needed is prone to performance penalties. Using DTO allows us to extract only the needed data. Sometimes, we need to fetch a DTO made of a subset of properties (columns) from a parent-child association. For such cases, we can use SQL `JOIN` that can pick up the desired columns from the involved tables. But, `JOIN` returns an `List<Object[]>` and most probably you will need to represent it as a `List<ParentDto>`, where a `ParentDto` instance has a `List<ChildDto>`. For such cases, we can rely on a custom Hibernate `ResultTransformer`. This application is a sample of writing a custom `ResultTransformer`.

As DTO, we rely on JDK 14 Records. From Openjdk JEP359:

*Records provide a compact syntax for declaring classes which are transparent holders for shallowly immutable data.*

**Key points:**
- define the Java Records as `AuthorDto` and `BookDto`
- implement the `ResultTransformer` interface

----------------------------------------------------------------------------------------------------------------------- 

275. **[DTO Via JDK14 Records, `JdbcTemplate` And `ResultSetExtractor`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoRecordJbcTemplate)** 

**Description:** Fetching more data than needed is prone to performance penalities. Using DTO allows us to extract only the needed data. In this application we rely on JDK14 Records feature, `JdbcTemplate` and `ResultSetExtractor`.

From Openjdk JEP359:

*Records provide a compact syntax for declaring classes which are transparent holders for shallowly immutable data.*
 
**Key points:**

- define the Java Records as `AuthorDto` and `BookDto`
- use `JdbcTemplate` and `ResultSetExtractor`

----------------------------------------------------------------------------------------------------------------------- 

276. **[Dynamic Spring projection (DTO class)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDynamicProjectionClass)**
  
**Description:** This application is a sample of using dynamic Spring projections via DTO classes.

**Key points:**
- declare query-methods in a generic manner (e.g., `<T> List<T> findByGenre(String genre, Class<T> type);`)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

277. **Batch Inserts In Spring Boot Style Via `CompletableFuture` And Return `List<S>`**
 
**Description:** This application is a sample of using `CompletableFuture` for batching inserts. This `CompletableFuture` uses an `Executor` that has the number of threads equal with the number of your computer cores. Usage is in Spring style. It returns `List<S>`:

- [Batch Inserts In Spring Boot Style Via `CompletableFuture` And Return `List<S>` (1)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsCompletableFutureReturnList)
- [Batch Inserts In Spring Boot Style Via `CompletableFuture` And Return `List<S>` (2)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsCompletableFutureReturnGivenList)

-----------------------------------------------------------------------------------------------------------------------    

278. **[How to simulate a deadlock](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDeadlockExample)**
 
**Description:** This application is an example of causing a database deadlock in MySQL. This application produces an exception of type: `com.mysql.cj.jdbc.exceptions.MySQLTransactionRollbackException: Deadlock found when trying to get lock; try restarting transaction`. However, the database will retry until transaction (A) succeeds.

**Key points:**
- start *Transaction (A)* and trigger a `SELECT` with `PESSIMISTIC_WRITE` to acquire an exclusive lock to table `author`
- *Transaction (A)* update `author` genre with success and sleeps for 10s
- after 5s, start a concurrent *Transaction B* that trigger a `SELECT` with `PESSIMISTIC_WRITE` to acquire an exclusive lock to table `book`
- *Transaction (B)* update `book` title with success and sleeps for 10s
- *Transaction (A)* wakes up and attempt to update the book but it cannot acquire the lock holded by *Transaction (B)*
- *Transaction (B)* wakes up and attempt to update the author but it cannot acquire the lock holded by *Transaction (A)*
- DEADLOCK
- database retry and succeeds after *Transaction (B)* releases the lock

-----------------------------------------------------------------------------------------------------------------------    

279. **[How To Define A Composite Primary Key Having An Explicit Part and a Generated Part Via Sequence](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCompositeKeySeqIdClass)**

**Description:** This application is a proof of concept of how to define a composite key having an explicit part (`name`) and a generated part (`authorId` via `SEQUENCE` generator).

**Key points:**
- use `@IdClass`

-----------------------------------------------------------------------------------------------------------------------    

280. **[How To Intercept The Generated SQL For Logging Or Altering](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootInterceptSql)**
 
**Description:** Sometimes we need to intercept the generated SQL that originates from Spring Data, `EntityManager`, Criteria API, `JdbcTemplate` and so on. This can be done as in this sample application. After interception, you can log, modify or even return a brand new SQL that will be executed in the end.

**Key points:**
- define an implementation of Hibernate `StatementInspector` SPI
- configure this SPI in `application.properties` via `spring.jpa.properties.hibernate.session_factory.statement_inspector`

-----------------------------------------------------------------------------------------------------------------------    

281.**[Force inline params in Criteria API](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootForceInlineParams)**

**NOTE** Use this with high precaution since you open the gate for SQL injections.
 
**Description:** Sometimes we need to force inline params in Criteria API. By default, numeric parameters are inlined, but string parameters are not.

**Key points:**
- configure in `application.properties` the setting `spring.jpa.properties.hibernate.criteria.literal_handling_mode` as `inline`

-----------------------------------------------------------------------------------------------------------------------    

282. **[Using Arthur Gavlyukovskiy's data source decorator](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceDecorator)**

**Description:** [Arthur Gavlyukovskiy](https://github.com/gavlyukovskiy/spring-boot-data-source-decorator) provide a suite of Spring Boot starters for quickly integrate [P6Spy](https://github.com/p6spy/p6spy), [Datasource Proxy](https://github.com/ttddyy/datasource-proxy), and [FlexyPool](https://github.com/vladmihalcea/flexy-pool). In this example, we add Datasource Proxy, but please consider [this](https://github.com/gavlyukovskiy/spring-boot-data-source-decorator) for more details.

**Key points:**
- for Maven, in `pom.xml`, add the `datasource-proxy-spring-boot-starter` starter
- in `application.properties` enable `DEBUG` level for logging

-----------------------------------------------------------------------------------------------------------------------    

282. **[Using Java records as Hibernate embeddable](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRecordAndEmbeddables)**
 
**Description:** This application is an example of using Java records as embeddable. This is available starting with Hibernate 6.0, but it was refined to be more accessible and easy to use in Hibernate 6.2

**Key points:**
- add Hibernate 6.2 (this is not default in Spring Boot 3.0.2 used here)
- define a record (`Contact`)
- add this record in an entity (`Author`) via `@Embedded`
- fetch data into a DTO represented by another record (`AuthorDto`)
