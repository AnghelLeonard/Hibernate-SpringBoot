**[How To Batch a Big JSON File To MySQL Via ForkJoinPool And HikariCP](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileForkJoin)**

**Other examples:**
- same example based on a single thread is here
- same example based on `ExecutorService` is here

**Description:** This is a Spring Boot application that reads a relatively big JSON file (200000+ lines) and inserts its content in MySQL via batching using `ForkJoinPool` and HikariCP.

**Key points:**\
     - read the file into a `List` of a certain capacity, for example equal or bigger than your batch; by default the batch is of 300 lines, and the temporary list is 300 * 64\
     - when the list is full save it in batches into MySQL, clear the list, and fill it again\
     - set the HikariCP to provide a number of database connections that ensure that the database achives a minimum context switching (e.g., 2 * number of CPU cores)\
     - this application uses `StopWatch` to measure the time needed to transfer the file into the database\
     - in order to run the application you have to unzip the citylots.zip in the current location; this is the big JSON file collected from Internet;\
     - if you want to see details about the batch process simply activate the DatasourceProxyBeanPostProcessor.java component, uncomment `@Component`; This is needed because this application relies on DataSource-Proxy (for details, see [recipe 3](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDataSourceProxy))
