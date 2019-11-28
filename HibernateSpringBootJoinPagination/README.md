**[How To Page The Result Set of a `JOIN`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinPagination)**

**Description:** Using `JOIN` is very useful for fetching DTOs (data that is never modified, not in the current or subsequent requests). For example, consider two entities, `Author` and `Book` in a lazy-bidirectional `@OneToMany` association. And, we want to fetch a subset of columns from the parent table (`author`) and a subset of columns from the child table (`book`). This job is a perfect fit for `JOIN` which can pick up columns from different tables and build a *raw* result set. This way we fetch only the needed data. Moreover, we may want to serve the result set in pages (e.g., via `LIMIT`). This application contains several approaches for accomplishing this task with offset pagination.

**Key points:**
- pagination via `Page` (with `SELECT COUNT` and `COUNT(*) OVER()` window function)
- pagination via `Slice` and `List`
- pagination via `DENSE_RANK()` for avoiding the truncation of the result set (an author can be fetched with only a subset of his books)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

