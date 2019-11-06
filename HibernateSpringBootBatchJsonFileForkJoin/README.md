**[How To JDBC Batch a Big JSON File To MySQL Via ForkJoinPool And HikariCP](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileForkJoin)**
 
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

-------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
