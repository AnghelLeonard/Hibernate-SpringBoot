# Hibernate & Spring Boot (2.0.5)

[![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg)](https://leanpub.com/java-persistence-performance-illustrated-guide){height="36px" width="36px"}

**Hibernate & Spring Boot Samples**

1. **[Hibernate SpringBoot UTC Timezone And MySQL](https://github.com/AnghelLeonard/Hibernate/tree/master/HibernateSpringBootUTCTimezone)**

**Description:** How to store date, time, and timestamps in UTC time zone in MySQL

**Key points:**\
     - spring.jpa.properties.hibernate.jdbc.time_zone=UTC\
     - spring.datasource.url=jdbc:mysql://localhost:3306/db_screenshot **?useLegacyDatetimeCode=false**
     
-----------------------------------------------------------------------------------------------------------------------    

2. **[Hibernate SpringBoot View Binding Params Via Log4J 2](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootLog4j2ViewBindingParameters)**

**Description:** View the prepared statement binding parameters via Log4J 2 logger setting

**Key points:**\
     - in pom.xml, exclude Spring Boot's Default Logging\
     - in pom.xml, Add Log4j 2 Dependency\
     - in log4j2.xml add, `<Logger name="org.hibernate.type.descriptor.sql" level="trace"/>`
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootLog4j2ViewBindingParameters/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

3. **[Hibernate SpringBoot View Query Details Via "datasource-proxy"](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceProxy)**

**Description:** View the query details (query type, binding parameters, batch size, etc) via **[datasource-proxy](https://github.com/ttddyy/datasource-proxy)**

**Key points:**\
     - add in pom.xml the datasource-proxy dependency\
     - create an bean post processor to intercept the `DataSource` bean\
     - wrap the `DataSource` bean via `ProxyFactory` and an implementation of `MethodInterceptor`
   
**Output example:**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDataSourceProxy/sample.png)

-----------------------------------------------------------------------------------------------------------------------    

4. **[Hibernate SpringBoot Batch Inserts via `saveAll(Iterable<S> entities)` in MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertsJpaRepository)**

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
