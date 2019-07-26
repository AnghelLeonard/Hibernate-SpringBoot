**[How To Auto-Create And Migrate Schema Using Flyway In SpringBoot And MySQL (kickoff)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayKickoff2)**

**Note:** For production don't rely on `hibernate.ddl-auto` to create your schema. Set `hibernate.ddl-auto` to `none` or `validate` and rely on Flyway or Liquibase.

**Description:** This application is a kickoff of using Flyway in SpringBoot for auto-creating and migrating database schema in MySQL. We set the database name in the JDBC URL as usual, and use the MySQL specific hint `createDatabaseIfNotExist=true` for creating the missing schema.

**Key points:**\
     - for Maven, in pom.xml, add the Flyway dependency\
     - in application.properties, set `spring.jpa.hibernate.ddl-auto=none`\
     - in application.properties, set the JDBC URL with the schema, e.g., `jdbc:mysql://localhost:3306/db_cars?createDatabaseIfNotExist=true`\
     - each SQL file containing the schema update add it in `classpath:db/migration`\
     - each SQL file name it as `V1.1__Description.sql`, `V1.2__Description.sql`, ...
     
**Output of migrationg history example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootFlywayKickoff/flyway_schema_history.png)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
