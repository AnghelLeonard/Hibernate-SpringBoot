**[How To Increment The Version Of The Locked Entity Even If This Entity Was Not Modified `OPTIMISTIC_FORCE_INCREMENT`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOptimisticForceIncrement)**

**Description:** This application is a sample of how `OPTIMISTIC_FORCE_INCREMENT` works in MySQL. This is useful when you want to increment the version of the locked entity even if this entity was not modified. Via `OPTIMISTIC_FORCE_INCREMENT` the version is updated (incremented) at the end of the currently running transaction.

**Key points:**
- use a root entity, `Chapter` (which uses `@Version`)
- several editors load a chapter and perform modifications mapped via the `Modification` entity
- between `Modification` (child-side) and `Chapter` (parent-side) there is a lazy unidirectional `@ManyToOne` association
- for each modification, Hibernate will trigger an `INSERT` statement against the `modification` table, therefore the `chapter` table will not be modified by editors
- but, `Chapter` entity version is needed to ensure that modifications are applied sequentially (the author and editor are notified if a modificaton was added since the chapter copy was loaded)
- the `version` is forcibly increased at each modification (this is materialized in an `UPDATE` triggered against the `chapter` table at the end of the currently running transaction)
- set `OPTIMISTIC_FORCE_INCREMENT` in the corresponding repository
- rely on two concurrent transactions to shape the scenario that will lead to an exception of type `ObjectOptimisticLockingFailureException`    
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

