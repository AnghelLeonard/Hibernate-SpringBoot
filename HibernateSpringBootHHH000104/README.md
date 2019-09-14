**[How To Avoid HHH000104 And Use Pagination Of Parent-Child](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootHHH000104)**

**Description:** HHH000104 is a Hibernate warning that tell us that pagination of a result set is tacking place in memory. For example, consider the `Author` and `Book` entities in a bidirectional lazy `@OneToMany` relationship and the following query: 

  `@Transactional`\
  `@Query(value = "SELECT a FROM Author a LEFT JOIN FETCH a.books WHERE a.genre = ?1",`\
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`countQuery = "SELECT COUNT(a) FROM Author a WHERE a.genre = ?1")`\
  `Page<Author> fetchWithBooksByGenre(String genre, Pageable pageable);`

Calling `fetchWithBooksByGenre()` works fine only that the following warning is signaled: `HHH000104: firstResult/maxResults specified with collection fetch; applying in memory!` Obviously, having pagination in memory cannot be good from performance perspective. This application reveals several approaches for moving pagination at database-level.

**Key points:**\
     - use two JPQL queries (use `Page` or `Slice`) to fetch read-write result set\
     - use DTOs to fetch read-only result set\
     - use `DENSE_RANK()` to avoid truncate the result set (truncate the books of the an author)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
