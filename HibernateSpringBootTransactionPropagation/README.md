**[Avoid Spring Redundant `save()` Call](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRedundantSave)**
 
**Description:** This application is an example when calling `save()` for an entity is redundant (not necessary).

**Key points:**
- at flush time, Hibernate relies on *dirty checking* mechanism to determine the potential modifications in entities 
- for each modification, Hibernate automatically triggers the corresponding `UPDATE` statement without the need to explicitly call the `save()` method
- behind the scene, this redundancy (calling `save()` when is not necessarily) doesn't affect the number of triggered queries, but it implies a performance penalty in the underlying Hibernate processes
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

