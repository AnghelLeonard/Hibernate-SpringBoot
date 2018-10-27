**[How To Avoid Lazy Initialization Caused By Open Session In View Anti-Pattern](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSuppressLazyInitInOpenSessionInView)**

**Description:** The Open-Session in View anti-pattern is activated by default in SpringBoot. If you prefer to use it then it is recommended to mitigate its performance penalties as much as possible. One optimization consist in marking the `Connection` as read-only which would allow the database server to avoid writing to the transaction log. Another optimization consist in explicitly setting the lazy properties of the detached entities when you don't want them to be lazy initialized.

**Key points:**\
     - fetch a entity, detach it and set the lazy properties explicitly\
     - you can do this is the service or controller layer, depending where it fits better to your case
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSuppressLazyInitInOpenSessionInView/sample.png)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
