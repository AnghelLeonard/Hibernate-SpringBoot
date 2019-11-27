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

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

