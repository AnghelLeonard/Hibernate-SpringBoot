
**[How To Auto-Create And Migrate Schemas For Two Data Sources (MySQL and PostgreSQL) Using Flyway](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayTwoVendors)**

**Note:** For production don't rely on `hibernate.ddl-auto` to create your schema. Set `hibernate.ddl-auto` to `none` or `validate` and rely on Flyway or Liquibase.

**Description:** This application is an example of auto-creating and migrating schemas for MySQL and PostgreSQL. In addition, each data source uses its own HikariCP connection pool. In case of MySQL, where schema=database, we auto-create the schema (`players_db`) based on `createDatabaseIfNotExist=true`. In case of PostgreSQL, where a database can have multiple schemas, we use the default `postgres` database and auto-create in it the schema, `coaches_db`. For this we rely on Flyway, which is capable to create a missing schema.

**Key points:**\
     - for Maven, in pom.xml, add the Flyway dependency\
     - in application.properties, configure the JDBC URL for MySQL as, `jdbc:mysql://localhost:3306/players_db?createDatabaseIfNotExist=true&useSSL=false` and for PostgreSQL as, `jdbc:postgresql://localhost:5432/postgres?currentSchema=coaches_db`\
     - in application.properties, set `spring.flyway.enabled=false` to disable default behavior\
     - programmatically create a `DataSource` for MySQL and one for PostgreSQL\
     - programmatically create a `FlywayDataSource` for MySQL and one for PostgreSQL\
     - programmatically create an `EntityManagerFactory` for MySQL and one for PostgreSQL\
     - for MySQL, place the migration SQLs files in `db\migration\mysql`\
     - for PostgreSQL, place the migration SQLs files in `db\migration\postgresql`    

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
