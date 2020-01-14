**[How To NOT Use Spring Data `Streamable`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootStreamable)**
 
**Description:** This application is an example of fetching `Streamable<entity>` and `Streamable<dto>`. But, more important, is an example of how to **not** use `Streamable`. It is very tempting to fetch a `Streamable` just to apply to the result set some filter or a map operations, or even to join two or more `Streamable` via `and()` method. Is nothing wrong in using these methods as long as you respect the key points from below.

**Key points:**
- don't fetch all columns just to drop a part of them (e.g., via `map()`)
- don't fetch all rows just to throw away a part of it (e.g., via `filter()`)
- don't join `Streamable` via `and()` if you can write a single SQL statement (each `Streamable` produces a separate SQL statement)
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

