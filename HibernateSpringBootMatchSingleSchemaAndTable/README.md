**[How To Generate A Schema Via schema-.sql In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootMatchSingleSchemaAndTable)**

**Note:** As a rule, in real applications avoid generating schema via, `hibernate.ddl-auto`. Use `schema-.sql` file or better `Flyway` or `Liquibase`.

**Description:** This application is an example of using `schema-.sql` to generate a schema(database) in MySQL.

**Key points:**\
     - in `application.properties`, set the JDBC URL. E.g., `spring.datasource.url=jdbc:mysql://localhost:3306/db_cars?createDatabaseIfNotExist=true`\
     - in `application.properties`, disable DDL auto, e.g., `spring.jpa.hibernate.ddl-auto=none`\
     - in `aaplication.properties`, instruct Spring Boot to initialize the schema from `schema-mysql.sql` file  
