**[How To Calculate Entity Persistent Property](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCalculateProperty)**
 
**Description:** This application is an example of calculating an entity persistent property at `INSERT` and `UPDATE ` time. 

**Key points:**\
     - annotate the corresponding persistent field with `@Column(insertable = false, updatable = false)`\
**Method 1:**\
     - if the database schema is generated via JPA annotations (not recommended) then use `columnDefinition` element of `@Column` to specify as an SQL query expression the formula for calculating the persistent property\
**Method 2:**\
     - if the database schema is not generated via JPA annotations (recommended way) then add the formula as part of schema in `CREATE TABLE`
     
**Note:** In production, you should not rely on `columnDefinition`. You should disable `hibernate.ddl-auto` (by omitting it) or set it to `validate`, and add the SQL query expression in `CREATE TABLE` (in this application, check the `discount` column in `CREATE TABLE`, file `schema-sql.sql`). Nevertheless, not even `schema-sql.sql` is ok in production. The best way is to rely on Flyway or Liquibase.
         
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
