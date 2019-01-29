**[How To Auto-Create And Migrate Schema Using Flyway In SpringBoot And MySQL (kickoff)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayKickoff)**

**Note:** For production don't rely on `hibernate.ddl-auto` to create your schema. Set `hibernate.ddl-auto` to `none` or `validate` and rely on Flyway or Liquibase.

**Description:** This application is a kickoff of using Flyway in SpringBoot for auto-creating and migrating database schema in MySQL. The drawback here is that we need to point out the schema in entities via, `@Table(catalog = '...')` since is not present in the JDBC URL.  Basically, we remove the schema name from connection URL and use `flyway.schemas` option to provide the schema name and Flyway will create the missing schema for us. 

**Key points:**\
     - for Maven, in pom.xml, add the Flyway dependency\
     - in application.properties, set the JDBC URL without specifying the schema, e.g., `jdbc:mysql://localhost:3306/`\
     - in application.properties, set the schemas that should be migrated, e.g., `spring.flyway.schemas=db_cars`\
     - each SQL file containing the schema update add it in `classpath:db/migration`\
     - each SQL file name it as `V1.1__Description.sql`, `V1.2__Description.sql`, ...
     
**Output of migrationg history example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootFlywayKickoff/flyway_schema_history.png)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
