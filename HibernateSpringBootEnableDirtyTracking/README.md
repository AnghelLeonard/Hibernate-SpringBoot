**[How To Enable Dirty Tracking In A Spring Boot Application](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnableDirtyTracking)**

**Note:** The Hibernate *Dirty Checking* mechanism is responsible to identify the entitites modifications at flush-time and to trigger the corresponding `UPDATE` statements in our behalf.

**Description:** Prior to Hibernate version 5, the *Dirty Checking* mechanism relies on Java Reflection API for checking every property of every managed entity. Starting with Hibernate version 5, the *Dirty Checking* mechanism can rely on the *Dirty Tracking* mechanism (which is the capability of an entity to track its own attributes changes) which requires Hibernate *Bytecode Enhancement* to be present in the application. The *Dirty Tracking* mechanism sustain a better performance, especially when you have a relatively large number of entitites. 

For *Dirty Tracking*, during *Bytecode Enhancement* process, the entity classes bytecode is instrumented by Hibernate by adding a *tracker*, `$$_hibernate_tracker`. At flush time, Hibernate will use this *tracker* to discover the entities changes (each entity *tracker* will report the changes). This is better than checking every property of every managed entity.

Commonly (by default), the instrumentation takes place at build-time, but it can be configured to take place at runtime or deploy-time as well. It is preferable to take place at build-time for avoiding an overhead in the runtime.

Adding *Bytecode Enhancement* and enabling *Dirty Tracking* can be done via a plugin added via Maven or Gradle (Ant can be used as well). We use Maven, therefore we add it in `pom.xml`.

**Key points:**
- Hibernate come with *Bytecode Enhancement* plugins for Maven, Gradle (Ant can be used as well)
- for Maven, add the *Bytecode Enhancement* plugin in the `pom.xml` file
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootEnableDirtyTracking/Enable%20dirty%20tracking.png)
 
The *Bytecode Enhancement* effect can be seen on `Author.class` [here](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootEnableDirtyTracking/Bytecode%20Enhancement%20Author.class/Author.java). Notice how the bytecode was instrumented with `$$_hibernate_tracker`.

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

