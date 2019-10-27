**[How To Implement Pagination Via `ROW_NUMBER()` Window Function](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPaginationRowNumber)**
 
**Description:** This application is an example of using `ROW_NUMBER()` (and `COUNT(*) OVER()` for counting all elements) window function to implement pagination.

**Key points:**
- use a native query relying on `ROW_NUMBER()`
- we don't return a page as `Page` or `Slice`, we return it as `List`, therefore `Pageable` is not used

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
