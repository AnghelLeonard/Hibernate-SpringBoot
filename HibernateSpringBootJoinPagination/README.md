**[How To Page The Result Set of a `JOIN`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinPagination)**

**Description:** Using `JOIN` is very useful for fetching DTOs (data that is never modified, not in the current or subsequent requests). For example, consider two entities, `Author` and `Book` in a lazy-bidirectional `@OneToMany` association. And, we want to fetch a subset of columns from the parent table (`author`) and a subset of columns from the child table (`book`). This job is a perfect fit for `JOIN` which can pick up columns from different tables and build a *raw* result set. This way we fetch only the needed data. Moreover, we may want to serve the result set in pages (e.g., via `LIMIT`). This application contains several approaches for accomplishing this task with offset pagination.

**Key points:**
- pagination via `Page` (with `SELECT COUNT` and `COUNT(*) OVER()` window function)
- pagination via `Slice` and `List`
- pagination via `DENSE_RANK()` for avoiding the truncation of the result set (an author can be fetched with only a subset of his books)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
