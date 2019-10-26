**[How To Generate A Schema Via `schema-*.sql` In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSchemaSql)**

**Note:** As a rule, in real applications avoid generating schema via `hibernate.ddl-auto`. Use `schema-*.sql` file or better `Flyway` or `Liquibase` migration tools.

**Description:** This application is an example of using `schema-*.sql` to generate a schema(database) in MySQL.

**Key points:**
- in `application.properties`, set the JDBC URL (e.g., `spring.datasource.url=jdbc:mysql://localhost:3306/bookstoredb?createDatabaseIfNotExist=true`)
- in `application.properties`, disable DDL auto (just don't add explicitly the `hibernate.ddl-auto` setting)
- in `application.properties`, instruct Spring Boot to initialize the schema from `schema-mysql.sql` file  
     
-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
