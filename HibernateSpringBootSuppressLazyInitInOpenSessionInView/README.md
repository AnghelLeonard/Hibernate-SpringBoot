**[How To Avoid Lazy Initialization Caused By Open Session In View Anti-Pattern With Session Per HTTP Request-Response](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSuppressLazyInitInOpenSessionInView)**

**Description:** The Open-Session in View anti-pattern is activated by default in SpringBoot. If you prefer to use it then it is recommended to mitigate its performance penalties as much as possible. One optimization consist in marking the `Connection` as read-only which would allow the database server to avoid writing to the transaction log. Another optimization consist in explicitly setting the lazy properties of the fetched entities when you don't want them to be lazy initialized.

**Key points:**\
     - fetch a entity and set the lazy properties explicitly\
     - you can do this is the service or controller layer, depending where it fits better to your case
     - why is this working? why we can set the property of a managed entity and not trigger the flush? well, the answer can be found in the documentation of `OpenSessionInViewFilter` which specifies that:
     
 **NOTE:** This filter will by default not flush the Hibernate `Session`, with the flush mode set to `FlushMode.NEVER`. It assumes to be used in combination with service layer transactions that care for the flushing: The active transaction manager will temporarily change the flush mode to `FlushMode.AUTO` during a read-write transaction, with the flush mode reset to `FlushMode.NEVER` at the end of each transaction. If you intend to use this filter without transactions, consider changing the default flush mode (through the "flushMode" property).
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSuppressLazyInitInOpenSessionInView/sample.png)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
