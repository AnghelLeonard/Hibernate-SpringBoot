**[How To Calculate An Entity Property At `INSERT` Or `UPDATE` Time Via Hibernate `@Generated`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCalculatePropertyGenerated)**
 
**Description:** This application is an example of calculating an entity property at `INSERT` or `UPDATE ` time via Hibernate `@Generated` annotation.

**Key points:**\
     - annotate the entity property that should be calculated with `@Generated(value = GenerationTime.ALWAYS)` or `@Generated(value = GenerationTime.INSERT)`\
     - if  the database schema is generated via JPA annotations then use `columnDefinition` element of `@Column` 
     
**Note:** In production, you should not rely on this practice. You should set `hibernate.ddl-auto` to `none` or `validate`), and add the SQL query expression in `CREATE TABLE` (in this example, check `schema-sql.sql`). Nevertheless, not even `schema-sql.sql` is ok in production. The best way is to rely on Flyway or Liquibase).
         
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
