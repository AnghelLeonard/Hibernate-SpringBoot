**[Redundant save() Call](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootRedundantSave)**

**Description:** This application is an example when calling `save()` for a managed entity is redundant.

**Key points:**\
     - Hibernate triggers `UPDATE` statements for managed entities without the need to explicitly call the `save()` method\
     - behind the scene, this redundancy implies a performance penalty as well
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
