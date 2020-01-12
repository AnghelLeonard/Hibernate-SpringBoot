**[The Best Way To Fetch Parent And Children In Different Queries](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootParentChildSeparateQueries)** 
 
**Description:** Let's assume that `Author` and `Book` are involved in a bidirectional-lazy `@OneToMany` association. At first request (query), we fetch an `Author`. The `Author` is detached. At second request (query), we want to load the `Book` of this `Author`. We don't want to load the `Author` again (e.g., we don't care about lost updates), we just want to load the associated `Book` in a single `SELECT`. A common approach is to load the `Author` again (e.g., via `findById()`) and call the `getBooks()`. But, this will trigger two `SELECT` statements. Moreover, the collection is not initialized if we simply return it. In order to trigger the collection initialization the developer call `books.size()` or he rely on `Hibernate.initialize(books);`. But, we can avoid such an approach by relying on an explicit JPQL or Query Builder property expressions. This way, there will be a single `SELECT` and no need to call `size()` or `Hibernate.initialize();`

**Key points:**
- use an explicit JPQL
- use Query Builder propery expressions

This item is detailed in my book, [Spring Boot Persistence Best Practices](https://www.amazon.com/gp/product/1484256255).
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

