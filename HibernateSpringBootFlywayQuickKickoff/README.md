
**[How To Migrate Schema Using Flyway in MySQL When Schema Exists](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFlywayQuickKickoff)**

**Note:** For production don't rely on `hibernate.ddl-auto` to create your schema. Set `hibernate.ddl-auto` to `none` or `validate` and rely on Flyway or Liquibase.

**Description:** This application is an example of migrating a MySQL schema when the schema exists (is created before migration).

**Key points:**\
     - for Maven, in pom.xml, add the Flyway dependency\
     - in application.properties, set `spring.jpa.hibernate.ddl-auto=none`\
     - in application.properties, set the JDBC URL with the schema, e.g., `jdbc:mysql://localhost:3306/db_cars`\
     - each SQL file containing the schema update add it in `classpath:db/migration`\
     - each SQL file name it as `V1.1__Description.sql`, `V1.2__Description.sql`, ...
