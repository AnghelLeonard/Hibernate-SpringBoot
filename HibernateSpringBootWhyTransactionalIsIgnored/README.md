**[Why the `@Transactional` annotation is being ignored](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootWhyTransactionalIsIgnored)**
 
**Description:** This application is an example of fixing the case when `@Transactional` annotation is ignored. Most of the time, this annotation is ignored in the following scenarios:

1. `@Transactional` was added to a `private`, `protected` or `package-protected` method
2. `@Transactional` was added to a method defined in the same class where it is invoked

**Key points:**\
     - write a helper service and move the `@Transactional` methods there\
     - ensure that these methods are declared as `public`\
     - call `@Transactional` methods from other services
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
