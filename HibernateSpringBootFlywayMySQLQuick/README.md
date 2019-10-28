**[How To Migrate MySQL Database Using Flyway - MySQL Database Created Via `createDatabaseIfNotExist`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayMySQLQuick)**

**Note:** For production, don't rely on `hibernate.ddl-auto` (or counterparts) to export schema DDL to the database. Simply remove (disable) `hibernate.ddl-auto`. Rely on Flyway or Liquibase.

**Description:** This application is an example of migrating a MySQL database via Flyway when the database exists (it is created before migration via MySQL specific parameter, `createDatabaseIfNotExist=true`). 

**Key points:**
- for Maven, in `pom.xml`, add the Flyway dependency
- remove (disable) `spring.jpa.hibernate.ddl-auto`
- in `application.properties`, set the JDBC URL as follows: `jdbc:mysql://localhost:3306/bookstoredb?createDatabaseIfNotExist=true`
- each SQL file containing the schema update add it in `classpath:db/migration`
- each SQL file name it as `V1.1__Description.sql`, `V1.2__Description.sql`, ...

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
