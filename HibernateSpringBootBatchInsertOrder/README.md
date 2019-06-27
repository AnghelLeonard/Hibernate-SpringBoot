**[How To Optimize Batch Inserts of Parent-Child Relationships and Cascade Persist](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertOrder)**

**Description:** Let's suppose that we have a one-to-many relationship between `Author` and `Book` entities. When we save an author, we save his/her books as well thanks to cascading. We want to create a bunch of authors with books and save them in the database (e.g., a MySQL database) using the batch technique. By default, this will result in batching each author and the books per author. In order to batch authors and books, we need to **order inserts** as in this application.

**Key points:**\
     - beside all setting specific to batching inserts in MySQL, we need to set up in application.properties the following property: `spring.jpa.properties.hibernate.order_inserts=true`

**Example without ordered inserts:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertOrder/batch%20inserts%20including%20associations%20no%20order%20of%20inserts.png)

**Example with ordered inserts:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertOrder/batch%20inserts%20including%20associations%20ordered%20inserts.png)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
