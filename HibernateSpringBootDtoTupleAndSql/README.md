**[DTOs via javax.persistence.Tuple and Native SQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoTupleAndSql)**

**Description:** Fetching more data than needed is prone to performance penalities. Using DTOs allows us to extract only the needed data. In this application we rely on `javax.persistence.Tuple` and native SQL.

**Key points:**\
     - use `java.persistence.Tuple` and `EntityManager.createNativeQuery()`\
     - for using Spring Data Projections check this [recipe](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)  
