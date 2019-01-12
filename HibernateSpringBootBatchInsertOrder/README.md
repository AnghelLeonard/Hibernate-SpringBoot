**[How To Optimize Batch Inserts in Parent-Child Relationships With Cascade Persist](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchInsertOrder)**

**Description:** Let's suppose that we have a one-to-many relationship between `Tournament` and `TennisPlayer` entities. When we save a tournament, we save its players as well thanks to cascading. We want to create create a bunch of tournaments with players and save them in the database (e.g., a MySQL database) using the batch technique. By default, this will result in batching the players only. In order to batch the tournaments as well, we need to order inserts as in this application.

**Key points:**\
     - beside all setting specific to batching inserts in MySQL, we need to set up in application.properties the following property: `spring.jpa.properties.hibernate.order_inserts=true`

**Example without ordered inserts:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertOrder/sample2.png)

**Example with ordered inserts:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchInsertOrder/sample1.png)

