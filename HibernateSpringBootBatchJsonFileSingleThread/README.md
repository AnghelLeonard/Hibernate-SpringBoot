**[How To Batch a Big JSON File To MySQL Via a Single Thread And a Single Database Connection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileSingleThread)**

**Other examples:**
- same example based on `ExecutorService` is [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileExecutorService)
- same example based on `ForkJoinPool` is [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileForkJoin)

**Description:** This is a Spring Boot application that reads a relatively big JSON file (200000+ lines) and inserts its content in MySQL via batching using a single thread, the main thread.

**Key points:**\
     - read the file into a `List` of a certain capacity, for example equal or bigger than your batch; by default the batch is of 300 lines, and the temporary list is 300 * 64\
     - when the list is full save it in batches into MySQL, clear the list, and fill it again\
     - set the HikariCP to have a single connection\
     - in order to run the application you have to unzip the citylots.zip in the current location; this is the big JSON file collected from Internet;\
     - this application uses `StopWatch` to measure the time needed to transfer the file into the database\
     - if you want to see details about the batch process simply activate the DatasourceProxyBeanPostProcessor.java component, uncomment `@Component`; This is needed because this application relies on DataSource-Proxy (for details, see [recipe 3](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceProxy))
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>     

