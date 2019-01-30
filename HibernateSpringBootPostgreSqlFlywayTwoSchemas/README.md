
**[How To Auto-Create And Migrate Two Schemas In PostgreSQL Using Flyway](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPostgreSqlFlywayTwoSchemas)**

**Note:** For production don't rely on `hibernate.ddl-auto` to create your schema. Set `hibernate.ddl-auto` to `none` or `validate` and rely on Flyway or Liquibase.

**Description:** This application is an example of auto-creating and migrating two schemas in PostgreSQL using Flyway. In addition, each data source uses its own HikariCP connection pool. In case of PostgreSQL, where a database can have multiple schemas, we use the default `postgres` database and auto-create in it two schemas, `players_db` and `coaches_db`. For this we rely on Flyway, which is capable to create the missing schemas.

**Key points:**\
     - for Maven, in pom.xml, add the Flyway dependency\
     - in application.properties, configure the JDBC URL for `players_db` as `jdbc:postgresql://localhost:5432/postgres?currentSchema=players_db` and for `coaches_db` as `jdbc:postgresql://localhost:5432/postgres?currentSchema=coaches_db`\
     - in application.properties, set `spring.flyway.enabled=false` to disable default behavior\
     - programmatically create two `DataSource`, one for `players_db` and one for `coaches_db`\
     - programmatically create two `FlywayDataSource`, one for `players_db` and one for `coaches_db`\
     - programmatically create two `EntityManagerFactory`, one for `players_db` and one for `coaches_db`\
     - for `players_db`, place the migration SQLs files in `db\migration\playersdb`\
     - for `coaches_db`, place the migration SQLs files in `db\migration\coachesdb`    

