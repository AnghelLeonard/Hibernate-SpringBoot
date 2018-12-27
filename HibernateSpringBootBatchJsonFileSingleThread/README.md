**[How To Batch a Big JSON File To MySQL Via a Single Thread And a Single Database Connection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchJsonFileSingleThread)**

**Other examples:**
- same example based on `ExecutorService` is here
- same example based on `ForkJoinPool` is here

**Description:** This is a Spring Boot application that reads a relatively big JSON file (200000+ lines) and inserts its content in MySQL via batching using a single thread, the main thread.

**Key points:**\
     - read the file into a `List` of a certain capacity, for example equal or bigger than your batch; by default the batch is of 300 lines, and the temporary list is 300 * 64\
     - when the list is full save it in batches into MySQL, clear the list, and fill it again\
     - set the HikariCP to have a single connection

