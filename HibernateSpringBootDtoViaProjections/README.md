**[DTO Via Spring Data Projections](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjections)** 

**Note:** You may also like to read the recipe, ["How To Enrich DTO With Virtual Properties Via Spring Projections"](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaProjectionsAndVirtualProperties)

**Description:** Fetch only the needed data from the database via Spring Data Projections (DTO).

**Key points:**
- write an interface (projection) containing getters only for the columns that should be fetched from the database
- write the proper query returning a `List<projection>`
- if it is applicable, limit the number of returned rows (e.g., via `LIMIT`) 
- in this example, we can use query builder mechanism built into Spring Data repository infrastructure
     
**Note:** Using projections is not limited to use query builder mechanism built into Spring Data repository infrastructure. We can fetch projections via JPQL or native queries as well. For example, in this [application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDtoViaProjectionsAndJpql) we use a JPQL.
     
**Output example (select first 2 rows; select only "name" and "age"):**
<a href="#"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootDtoViaProjections/DTOs%20via%20Spring%20projections.png" align="center" height="251" width="658" ></a>

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

