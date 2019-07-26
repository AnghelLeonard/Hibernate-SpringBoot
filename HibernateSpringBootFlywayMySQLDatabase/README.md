**[How To Migrate MySQL Database Using Flyway - Database Created Via `spring.flyway.schemas`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayMySQLDatabase)**

**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto` or set it to `validate` and rely on Flyway or Liquibase

**Description:** This application is an example of migrating a MySQL database when the database is created by Flyway via `spring.flyway.schemas`. In this case, the entities should be annotated with `@Table(schema = "bookstoredb")` or `@Table(catalog = "bookstoredb")`. Here, the database name is `bookstoredb`.

**Key points:**\
     - for Maven, in `pom.xml`, add the Flyway dependency\
     - remove (disable) `spring.jpa.hibernate.ddl-auto`\
     - in `application.properties`, set the JDBC URL as follows: `jdbc:mysql://localhost:3306/`\
     - in `application.properties`, add `spring.flyway.schemas=bookstoredb`, where `bookstoredb` is the database that should be created by Flyway (feel free to add your own database name)\
     - each entity that should be stored in this database should be annotated with, `@Table(schema/catalog = "bookstoredb")`\
     - each SQL file containing the schema update add it in `classpath:db/migration`\
     - each SQL file name it as `V1.1__Description.sql`, `V1.2__Description.sql`, ...
     
**Output of migration history example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootFlywayMySQLCatalog/flyway_schema_history%20table.png)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
