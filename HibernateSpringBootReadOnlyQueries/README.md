**[Use Read-Only Entity Whenever You Plan To Propagate Entity Changes To The Database In A Future Persistent Context](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootReadOnlyQueries)**
 
**Description:** This application highlights the difference betweeen loading entities in *read-write* vs. *read-only* mode. If you plan to modify the entities in a future Persistent Context then fetch them as *read-only* in the current Persistent Context.

**Key points:**
- in the current Persistent Context, fetch entities in *read-only* mode 
- modifiy the entities in the current Persistent Context or in detached state (the potential modifications done in the current Persistent Context will not be propagated to the database at flush time)
- in a subsequent Persistent Context, merge the detached entity and propagate changes to the database

**Note:** If you never plan to modify the fetched result set then use DTO (e.g., Spring projection), not *read-only* entities.
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

