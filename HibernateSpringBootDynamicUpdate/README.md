**[How To Include In The `UPDATE` Statement Only The Modified Columns Via Hibernate `@DynamicUpdate`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootDynamicUpdate)**

**Description:** This application is an example of using the Hibernate-specific, `@DynamicUpdate`. By default, even if we modify only a subset of columns, the triggered `UPDATE` statements will include all columns. By simply annotating the corresponding entity at class-level with `@DynamicUpdate` the generated `UPDATE` statement will include only the modified columns. 

**Key points:**
- pro: avoid updating unmodified indexes (useful for heavy indexing)
- con: cannot reuse the same `UPDATE` for different subsets of columns via JDBC statements caching (each `UPDATE` string will be cached and reused accordingly)
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

