**[How To Implement Pagination Via `ROW_NUMBER()` Window Function](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootPaginationRowNumber)**
 
**Description:** This application is an example of using `ROW_NUMBER()` (and `COUNT(*) OVER()` for counting all elements) window function to implement pagination.

**Key points:**
- use a native query relying on `ROW_NUMBER()`
- we don't return a page as `Page` or `Slice`, we return it as `List`, therefore `Pageable` is not used

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

