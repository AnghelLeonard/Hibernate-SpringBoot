
**[Improper Usage Of `@Fetch(FetchMode.JOIN)` May Causes N+1 Issues](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootFetchJoinAndQueries)**

**Note:** Let's assume three entities, `Author`, `Book` and `Publisher`. Between `Author` and `Book` there is a bidirectional-lazy `@OneToMany` association. Between `Author` and `Publisher` there is a unidirectional-lazy `@ManyToOne`. Between `Book` and `Publisher` there is no association.

Now, we want to fetch an book by id (`BookRepository#findById()`), including its author, and the author's publisher. In such cases, using Hibernate fetch mode, `@Fetch(FetchMode.JOIN)` works as expected. Using `JOIN FETCH` or entity graph is also working as expected.

Next, we want to fetch all books (`BookRepository#findAll()`), including its author, and the author's publisher. In such cases, using Hibernate fetch mode, `@Fetch(FetchMode.JOIN)` will cause N+1 issues. It will not trigger the expected `JOIN`. In this case, using `JOIN FETCH` or entity graph should be used.

**Key points:**
- using Hibernate fetch mode, `@Fetch(FetchMode.JOIN)` doesn't work for query-methods
- Hibernate fetch mode, `@Fetch(FetchMode.JOIN)` works in cases that fetches the entity by id (primary key) like using `EntityManager#find()`, Spring Data, `findById()`, `findOne()`.

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

