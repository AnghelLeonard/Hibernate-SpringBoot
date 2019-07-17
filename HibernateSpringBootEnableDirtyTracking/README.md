**[How To Enable Dirty Tracking In A Spring Boot Application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnableDirtyTracking)**

**Description:** Prior to Hibernate version 5, the *dirty checking* mechanism relies on Java Reflection API. Starting with Hibernate version 5, the *dirty checking* mechanism relies on *bytecode enhancement*. This approach sustain a better performance, especially when you have a relatively large number of entitites.

**Key points:**\
     - add the corresponding `plugin` in `pom.xml` (use Maven bytecode enhancement plugin)
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootEnableDirtyTracking/Enable%20dirty%20tracking.png)

The bytecode enhancement effect can be seen on `Author.class` [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootEnableDirtyTracking/Bytecode%20Enhancement%20Author.class/Author.java)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
