**[The Best Way To Fetch Parent And Children In Different Queries](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootParentChildSeparateQueries)** 

**Note:** Fetching *read-only* data should be done via DTO, not managed entities. But, there is no tragedy to fetch read-only entities in a context as follows:

- we need all attributes of the entity (so, a DTO just mirrors an entity)
- we manipulate a small number of entities (e.g., an author with several books)
- we use `@Transactional(readOnly = true)`

Under these circumstances, let's tackle a common case that I saw quite a lot. There is even an SO answer about it (don't do this):

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootParentChildSeparateQueries/Fetch%20Parent%20And%20Children%20In%20Different%20Queries.png)

**Description:** Let's assume that `Author` and `Book` are involved in a bidirectional-lazy `@OneToMany` association. At first request (query), we fetch an `Author`. The `Author` is detached. At second request (query), we want to load the `Book` associated to this `Author`. 

Imagine an user that loads a certain `Author` (without the associated `Book`). The user may be interested  or not in the `Book`, therefore, we don't load them with the `Author`. If the user is interested in the `Book` then he will click a button of type, *View books*. Now, we have to return the `List<Book>` associated to this `Author`.

But, we don't want to load the `Author` again (for example, we don't care about *lost updates* of `Author`), we just want to load the associated `Book` in a single `SELECT`. A common (not recommended) approach is to load the `Author` again (e.g., via `findById(author.getId())`) and call the `author.getBooks()`. But, this end up in two `SELECT` statements. One `SELECT` for loading the `Author`, and another `SELECT` after we *force* the collection initialization. We *force* collection initialization because it will not be initialize if we simply return it. In order to trigger the collection initialization the developer call `books.size()` or he rely on `Hibernate.initialize(books);`. 

But, we can avoid such solution by relying on an explicit JPQL or Query Builder property expressions. This way, there will be a single `SELECT` and no need to call `size()` or `Hibernate.initialize();`

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

