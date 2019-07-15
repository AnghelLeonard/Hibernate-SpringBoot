**[How To Use Spring Projections(DTOs) And Exclusive Full Joins (PostgreSQL)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaOuterExcludingJoins)**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaOuterExcludingJoins/DTO%20via%20exclusive%20full%20joins.png)

**Description:** This application is a proof of concept for using Spring Projections(DTOs) and exclusive full joins written via JPQL and native SQL (for PostgreSQL).

**Key points:**\
     - define two entities (e.g., `Author` and `Book` in a lazy bidirectional `@OneToMany` relationship)\
     - populate the database with some test data (e.g., check the file `resources/data-mysql.sql`)\
     - write interfaces (projections) that contains getters for the columns that should be fetched from the database (e.g., check `AuthorNameBookTitle.java`)\
     - write exclusive full joins queries using JPQL/SQL

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
