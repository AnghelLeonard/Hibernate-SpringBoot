**[How To Check If A Transient Entity Exists In The Database Via Spring Query By Example (QBE)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootExampleApi)**
  
**Description:** This application is an example of using Spring Data Query By Example (QBE) to check if a transient entity exists in the database. Consider the `Book` entity and a Spring controller that exposes an endpoint as: `public String checkBook(@Validated @ModelAttribute Book book, ...)`. Beside writting an explicit JPQL, we can rely on Spring Data Query Builder mechanism or, even better, on Query By Example (QBE) API. In this context, QBE API is quite useful if the entity has a significant number of attributes and:

- for all attributes, we need a head-to-head comparison of each attribute value to the corresponding column value
- for a subset of attributes, we need a head-to-head comparison of each attribute value to the corresponding column value
- for a subset of attributes, we return true at first match between an attribute value and the corresponding column value
- any other scenario

**Key points:**
- the repository, `BookRepository` extends `QueryByExampleExecutor`
- the application uses `<S extends T> boolean exists(Example<S> exmpl)` with the proper *probe* (an entity instance populated with the desired fields values)
- moreover, the *probe* relies on `ExampleMatcher` which defines the details on how to match particular fields

**Note:** Do not conclude that Query By Example (QBE) defines only the `exists()` method. Check out all methods [here](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/query/QueryByExampleExecutor.html).
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

