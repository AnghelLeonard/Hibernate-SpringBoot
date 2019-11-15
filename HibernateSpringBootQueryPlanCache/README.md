**[Avoid Spring Redundant `save()` Call](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRedundantSave)**
 
**Description:** This application is an example when calling `save()` for an entity is redundant (not necessary).

**Key points:**
- at flush time, Hibernate relies on *dirty checking* mechanism to determine the potential modifications in entities 
- for each modification, Hibernate automatically triggers the corresponding `UPDATE` statement without the need to explicitly call the `save()` method
- behind the scene, this redundancy (calling `save()` when is not necessarily) doesn't affect the number of triggered queries, but it implies a performance penalty in the underlying Hibernate processes
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
