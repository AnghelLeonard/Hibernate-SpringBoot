**[How Efficient Is Just `@OManyToOne`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJustManyToOne)**

**Note:** The `@ManyToOne` association maps exactly to the one-to-many table relationship. The underlying foreign key is under child-side control in unidirectional or bidirectional relationship.

**Description:** This application shows that using only `@ManyToOne` is quite efficient. On the other hand, using only `@OneToMany` is far away from being efficient. Always, prefer bidirectional `@OneToMany` or unidirectional `@ManyToOne`. Consider two entities, `Author` and `Book` in a unidirectional `@ManyToOne` relationship.

**Key points:**
- Adding a new book is efficient
- Fetching all books of an author is efficient via a JPQL
- Pagination of books is efficient
- Remove a book is efficient
- Even if the fetched collection is not managed, *dirty checking* mechanism works as expected
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

