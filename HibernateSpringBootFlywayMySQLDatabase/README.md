**[How To Migrate MySQL Database Using Flyway - Database Created Via `spring.flyway.schemas`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayMySQLDatabase)**

**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate`. Rely on Flyway or Liquibase.

**Description:** This application is an example of migrating a MySQL database when the database is created by Flyway via `spring.flyway.schemas`. In this case, the entities should be annotated with `@Table(schema = "bookstoredb")` or `@Table(catalog = "bookstoredb")`. Here, the database name is `bookstoredb`.

**Key points:**
- for Maven, in `pom.xml`, add the Flyway dependency
- remove (disable) `spring.jpa.hibernate.ddl-auto` or set it to `validate`
- in `application.properties`, set the JDBC URL as follows: `jdbc:mysql://localhost:3306/`
- in `application.properties`, add `spring.flyway.schemas=bookstoredb`, where `bookstoredb` is the database that should be created by Flyway (feel free to add your own database name)
- each entity that should be stored in this database should be annotated with, `@Table(schema/catalog = "bookstoredb")`
- each SQL file containing the schema update add it in `classpath:db/migration`
- each SQL file name it as `V1.1__Description.sql`, `V1.2__Description.sql`, ...
     
**Output of migration history example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootFlywayMySQLDatabase/flyway_schema_history%20table.png)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

