**[How To Generate Schema Via schema-.sql And Match Table And Schema Via @Table In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootMatchSchemaAndTable)**

**Note:** As a rule, in real applications avoid generating schema via, `hibernate.ddl-auto`. Use `schema-.sql` file or better `Flyway` or `Liquibase`.

**Description:** This application is an example of using `schema-.sql` to generate two schemas in MySQL. The tables and schemas are matched at entity mapping via `@Table`.

**Key points:**\
     - in `application.properties`, set the JDBC URL without schema, e.g., `spring.datasource.url=jdbc:mysql://localhost:3306`\
     - in `application.properties`, disable DDL auto, e.g., `spring.jpa.hibernate.ddl-auto=none`\
     - in `aaplication.properties`, instruct Spring Boot to initialize the schema from `schema-mysql.sql` file\
     - in `Car` entity, specify that the schema is `db_cars` and the table is, `lux_cars`\
     - in `Driver` entity, specify that the schema is `db_drivers` and the table is, `lux_drivers`

**Output example:**
- Persisting a `Car` results in the following SQL: `insert into db_cars.lux_cars (name) values (?)`
- Persisting a `Driver` results the following SQL: `insert into db_drivers.lux_drivers (name) values (?)`

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
