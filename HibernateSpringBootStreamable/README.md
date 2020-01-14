**[How To NOT Use Spring Data `Streamable`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootStreamable)**
 
**Description:** This application is a sample of fetching `Streamable<entity>` and `Streamable<dto>`. But, more important, this application contains three examples of how to **not** use `Streamable`. It is very tempting and comfortable to fetch a `Streamable` and chop it via `filter()` and `map()` until we obtain the needed result set instead of writing a query (e.g., JPQL) that fetches exactly the needed result set. Mainly, we just throw away some of the fetched data to keep only the needed data. Fetching more data than needed can cause significant performance penalties. Moreover, pay attention to combining two or more `Streamable` via the `and()` method. 

**Key points:**
- don't fetch more columns than needed just to drop a part of them (e.g., via `map()`)
- don't fetch more rows than needed just to throw away a part of it (e.g., via `filter()`)
- pay attention on combining `Streamable` via `and()`; each `Streamable` produces a separate SQL statement and the final result set is a concatenation of the intermediate results sets (prone to duplicate values)
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

