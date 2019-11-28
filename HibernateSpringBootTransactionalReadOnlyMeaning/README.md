**[What `@Transactional(readOnly=true)` Actually Do](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootTransactionalReadOnlyMeaning)**
  
 **Description:** This application is meant to reveal what is the difference between `@Transactional(readOnly = false)` and `@Transactional(readOnly = true)`. In a nuthsell, `readOnly = false` (default) fetches entites in *read-write* mode (managed). Before Spring 5.1, `readOnly = true` just set `FlushType.MANUAL/NEVER`, therefore the automatic *dirty checking mechanism* will not take action since there is no flush. In other words, Hibernate keep in the Persistent Context the fetched entities and the hydrated (loaded) state. By comparing the entity state with the hydrated state, the *dirty checking mechanism* can decide to trigger `UPDATE` statements in our behalf. But, the *dirty checking mechanism* take place at flush time, therefore, without a flush, the hydrated state is kept in Persistent Context for nothing, representing a performance penalty. Starting with Spring 5.1, the *read-only* mode is propagated to Hibernate, therefore the hydrated state is discarded immediately after loading the entities. Even if the *read-only* mode discards the hydrated state the entities are still loaded in the Persistent Context, therefore, for *read-only* data, relying on DTO (Spring projection) is better.

**Key points:**
- `readOnly = false` load data in *read-write* mode (managed)
- `readOnly = true` discard the hydrated state (starting with Spring 5.1)
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
