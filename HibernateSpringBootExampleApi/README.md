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
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
