**[How To Check If A Transient Entity Exists Via Spring Query By Example (QBE)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootExampleApi)**
 
**Description:** This application is an example of using Spring Data Query By Example (QBE) to check if a transient entity exists in the database. Consider the `Book` entity and a Spring controller that exposes an endpoint as: `public String saveNewBook(@Validated @ModelAttribute Book book, ...)`. Before saving a new book, we must ensure that this book doesn't already exists in the database. Beside writting an explicit JPQL, we can rely on Spring Data Query Builder mechanism or, even better, on Query By Example (QBE) API. This is quite useful if the entity has a significant number of attributes and:

- we need a head-to-head comparison of each attribute value to the corresponding column value
- we consider that a `Book` exists if at least one attribute value matches a column value
- we want to compare only a subset of attributes

**Key points:**
- the repository, `BookRepository` extends `QueryByExampleExecutor`
- the application uses `<S extends T> boolean exists(Example<S> exmpl)` with the proper *probe* (an entity instance populated with the desired fields values)
- moreover, the *probe* relies on `ExampleMatcher` which defines the details on how to match particular fields

**Note:** Do not conclude that Query By Example (QBE) defines only the `exists()` method. Check out all methods [here](https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/query/QueryByExampleExecutor.html).
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
