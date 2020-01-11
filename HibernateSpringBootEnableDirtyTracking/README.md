**[How To Enable Dirty Tracking In A Spring Boot Application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnableDirtyTracking)**

**Description:** Prior to Hibernate version 5, the *Dirty Checking* mechanism relies on Java Reflection API. Starting with Hibernate version 5, the *Dirty Checking* mechanism can rely on the *Dirty Tracking* mechanism (which is the capability of an entity to track its own property changes) which requires Hibernate *Bytecode Enhancement* to be enabled (disabled by default). The *Dirty Tracking* mechanism sustain a better performance, especially when you have a relatively large number of entitites. Enabling *Bytecode Enhancement* is about adding a plugin into the application (via Maven or Gradle).

**Key points:**
- Hibernate come with plugins for Maven and Gradle
- for Maven, add the plugin in the `pom.xml` file
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootEnableDirtyTracking/Enable%20dirty%20tracking.png)

The *bytecode enhancement* effect can be seen on `Author.class` [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootEnableDirtyTracking/Bytecode%20Enhancement%20Author.class/Author.java)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

