**[Working With Spring Data Property Expressions](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPropertyExpressions)** 
 
**Description:** Property expressions can refer to a direct property of the managed entity. However, you can also define constraints by traversing nested properties. This application is a sample of traversing nested properties for fetching entities and DTOs.

**Key points:**
- Assume an `Author` has several `Book` and each book has several `Review` (between `Author` and `Book` there is a bidirectional-lazy `@oneToMany` association, and, between `Book` and `Review` there is also a bidirectional-lazy `@OneToMany` association)
- Assume that we fetched a `Review` and we want to know the `Author` of the `Book` that has received this `Review`
- via property expressions, we can write in `AuthorRepository` the following query that will be processed by the Spring Data Query Builder mechanism: `Author findByBooksReviews(Review review);`
- Behind the scene Spring Data will produce a `SELECT` with two `LEFT JOIN`
- In this case, the method creates the property traversal `books.reviews`. The algorithm starts by interpreting the entire part (`BooksReviews`) as the property and checks the domain class for a property with that name (uncapitalized). If the algorithm succeeds, it uses that property. If not, the algorithm splits up the source at the camel case parts from the right side into a head and a tail and tries to find the corresponding property — in our example, `Books` and `Reviews`. If the algorithm finds a property with that head, it takes the tail and continues building the tree down from there, splitting the tail up in the way just described. If the first split does not match, the algorithm moves the split point to the left and continues.
- Although this algorithm should work for most cases, it is possible for the algorithm to select the wrong property. Suppose the `Author` class has an `booksReview` property as well. The algorithm would match in the first split round already, choose the wrong property, and fail (as the type of `booksReview` probably has no code property). To resolve this ambiguity you can use _ inside your method name to manually define traversal points. So our method name would be as follows: `Author findByBooks_Reviews(Review review);`
- More examples (including DTOs) are available in the application
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

