**[How Efficient Is Just `@OManyToOne`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJustManyToOne)**

**Note:** The `@ManyToOne` association maps exactly to the one-to-many table relationship. The underlying foreign key is under child-side control in unidirectional or bidirectional relationship.

**Description:** This application shows that using only `@ManyToOne` is quite efficient. On the other hand, using only `@OneToMany` is far away from being efficient. Always, prefer bidirectional `@OneToMany` or unidirectional `@ManyToOne`. Consider two entities, `Author` and `Book` in a unidirectional `@ManyToOne` relationship.

**Key points:**
- Adding a new book is efficient
- Fetching all books of an author is efficient via a JPQL
- Pagination of books is efficient
- Remove a book is efficient
- Even if the fetched collection is not managed, *dirty checking* mechanism works as expected
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
