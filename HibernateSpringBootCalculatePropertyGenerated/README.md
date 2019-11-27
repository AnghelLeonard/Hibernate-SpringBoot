**[How To Calculate Entity Persistent Property Via Hibernate `@Generated`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCalculatePropertyGenerated)**
 
**Description:** This application is an example of calculating an entity persistent property at `INSERT` and/or `UPDATE ` time via Hibernate, `@Generated`. 

**Key points:**

 **Calculate at `INSERT` time:**
- annotate the corresponding persistent field with `@Generated(value = GenerationTime.INSERT)`
- annotate the corresponding persistent field with `@Column(insertable = false)`

 **Calculate at `INSERT` and `UPDATE` time:**
- annotate the corresponding persistent field with `@Generated(value = GenerationTime.ALWAYS)`
- annotate the corresponding persistent field with `@Column(insertable = false, updatable = false)`

**Further, apply:**

 **Method 1:**
- if the database schema is generated via JPA annotations (not recommended) then use `columnDefinition` element of `@Column` to specify as an SQL query expression the formula for calculating the persistent property

 **Method 2:**
 - if the database schema is not generated via JPA annotations (recommended way) then add the formula as part of schema in `CREATE TABLE`
     
**Note:** In production, you should not rely on `columnDefinition`. You should disable `hibernate.ddl-auto` (by omitting it) or set it to `validate`, and add the SQL query expression in `CREATE TABLE` (in this application, check the `discount` column in `CREATE TABLE`, file `schema-sql.sql`). Nevertheless, not even `schema-sql.sql` is ok in production. The best way is to rely on Flyway or Liquibase.
         
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

