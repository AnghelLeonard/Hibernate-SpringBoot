**[Inspect Persistent Context](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootInspectPersistentContext)**

**Description:** This application is a ...

**Key points:**\
     - ...
     
**Note:** Pay attention to remove operations, especially to removing child entities. The `CascadeType.REMOVE` and `orphanRemoval=true` may produce too many queries. Relying on *bulk* operations is most of the time the best way to go for deletions.     
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
