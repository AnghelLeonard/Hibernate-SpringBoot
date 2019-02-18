**[How To Use Hibernate Soft Deletes in a Spring Boot Application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSoftDeletes)**

**Description:** This application is an example of using Hibernate soft deletes in a Spring Boot application. 

**Key points:**\
     - in entities (e.g., `Tournament` entity) that should take advantage of soft deletes define a dedicated column to store the deletion status (e.g., `deleted`)\
     - these entities should be marked with Hibernate, `@Where` annotation like this: `@Where(clause = "deleted = false")`\
     - these entities should be marked with Hibernate, `@SQLDelete` annotation to trigger `UPDATE` SQLs in place of `DELETE` SQLs, as follows: `@SQLDelete(sql = "UPDATE tournament SET deleted = true WHERE id = ?")`\
     - for fetching all entities including those marked as deleted or for fetching only the entities marked as deleted we need to rely on SQL native queries

**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSoftDeletes/soft%20deletes.png)
