**[How To Generate Two Databases Via `schema-*.sql` And Match Entities To Them Via `@Table` In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootMatchEntitiesToTablesTwoSchemas)**

**Note:** As a rule, in real applications avoid generating schema via `hibernate.ddl-auto`. Use `schema-*.sql` file or better `Flyway` or `Liquibase`.

**Description:** This application is an example of using `schema-*.sql` to generate two databases in MySQL. The databases are matched at entity mapping via `@Table`.

**Key points:**
- in `application.properties`, set the JDBC URL without the database, e.g., `spring.datasource.url=jdbc:mysql://localhost:3306`
- in `application.properties`, disable DDL auto (just don't specify `hibernate.ddl-auto`) or set it to `validate`
- in `aaplication.properties`, instruct Spring Boot to initialize the schema from `schema-mysql.sql` file
- in `Author` entity, specify that the corresponding table (`author`) is in the database `authorsdb` via `@Table(schema="authorsdb")`
- in `Book` entity, specify that the corresponding table (`book`) is in the database `booksdb` via `@Table(schema="booksdb")`

**Output example:**
- Persisting a `Author` results in the following SQL: `insert into authorsdb.author (age, genre, name) values (?, ?, ?)`
- Persisting a `Book` results the following SQL: `insert into booksdb.book (isbn, title) values (?, ?)`

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
