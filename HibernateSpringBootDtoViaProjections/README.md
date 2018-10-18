**[DTOs Via Spring Data Projections](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)**

**Description:** Fetch only the needed data from the database via Spring Data Projections (DTOs)

**Key points:**\
     - write an interface (projection) containing getters only for the columns that should be fetched from the database\
     - write the proper query returning a `List<projection>`\
     - if is applicable, limit the number of returned rows (e.g., via `LIMIT`) - here, we can use query builder mechanism built into Spring Data repository infrastructure
     
**Output example (select first 2 rows; select only "name" and "city"):**
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaProjections/sample.png)   
