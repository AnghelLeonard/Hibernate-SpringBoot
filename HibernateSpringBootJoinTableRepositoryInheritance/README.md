**[How To Shape JPA Inheritance In Repositories To Avoid Query-Methods Duplication](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinTableRepositoryInheritance)**

**Description:** Consider the JPA Single Table inheritance strategy (`JOINED`). It can be any other JPA inheritance (e.g., `SINGLE_TABLE`), but let's use this one here. And, the following entity hierarchy:

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSingleTableRepositoryInheritance/Single%20table%20inheritance.png)
    
For these three entities, `Book`, `Paperback` and `Ebook`, we have the corresponding repositories, `BookRepository`, `PaperbackRepository` and `EbookRepository`. But, if we write a query-method as `findByTitle()` we should duplicate it in each of these repositories in order to call `BookRepository#findByTitle()`, `PaperbackRepository#findByTitle()` and `EBookRepository#findByTitle()`. But, we know that `Paperback` and `Ebook` are actually subclasses of `Book`, therefore they inherit the `Book` class. It will be useful to do the same thing for our repositories. It will be better to write the `findById()` query-method only once and use it in all these repositories instead of duplicating it in each repository. This application shows you how to do it.

**Key points:**
- define the `findByTitle()` in a `@NoRepositoryBean` class (let's name this class, `BookBaseRepository`)
- next, `BookRepository`, `PaperbackRepository` and `EbookRepository` extends `BookBaseRepository`
- for queries express via `@Query` use `#{#entityName}` and Spring will replace it with the proper entity name

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
