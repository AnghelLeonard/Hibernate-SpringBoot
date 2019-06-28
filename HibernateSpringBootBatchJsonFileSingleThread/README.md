**[How To JDBC Batch a Big JSON File To MySQL Via a Single Thread And a Single Database Connection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileSingleThread)**

**Other examples:**
- same example based on `ExecutorService` is [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileExecutorService)
- same example based on `ForkJoinPool` is [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileForkJoin)

**Description:** This is a Spring Boot application that reads a relatively big JSON file (200000+ lines) and inserts its content in MySQL via batching using a single thread, the main thread, and a single database connection.

**Key points:**
- use MySql, `json` type
- read the file into a `List` (in this example using `Files.readAllLines()`)
- save the list in batches into MySQL
- for MySQL, in `application.properties`, you may want to attach to the JDBC URL the following:
     - `rewriteBatchedStatements=true` -> this setting will force sending the batched statements in a single request;
     - `cachePrepStmts=true` -> enable caching and is useful if you decide to set `prepStmtCacheSize`, `prepStmtCacheSqlLimit`, etc as well; without this setting the cache is disabled
     - `useServerPrepStmts=true` -> this way you switch to server-side prepared statements (may lead to signnificant performance boost); moreover, you avoid the `PreparedStatement` to be emulated at the JDBC Driver level; 
     - we use the following JDBC URL settings:\
     `...?cachePrepStmts=true&useServerPrepStmts=true&rewriteBatchedStatements=true&createDatabaseIfNotExist=true`
     - **Note: Older MySQL versions will not tolerate well to have toghether rewritting and server-side prepared statement activated. For being sure that these statements still valid please check the notes of the Connector/J that you are using**
- set the HikariCP to have a single connection
- this application uses `StopWatch` to measure the time needed to batch the file into the database
- in order to run the application you have to unzip the citylots.zip in the current location; this is the big JSON file collected from Internet;
- if you want to see details about the batch process simply activate the `DatasourceProxyBeanPostProcessor.java` component, uncomment `@Component`; this is needed because this application relies on DataSource-Proxy (for details, see [recipe 3](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceProxy))

--------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>     

