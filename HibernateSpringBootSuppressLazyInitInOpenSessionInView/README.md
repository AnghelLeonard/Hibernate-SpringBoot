**[How To Avoid Lazy Initialization Issues Caused By Disabling Open Session In View Via Explicit (Default) Values](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSuppressLazyInitInOpenSessionInView)**

**Note: Before reading this item try to see if [Hibernate5Module](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJacksonHibernate5Module) is not what you are looking for.**
 
**Description:** The Open-Session in View anti-pattern is activated by default in SpringBoot. Now, imagine a lazy association (e.g., `@OneToMany`) between two entities, `Author` and `Book` (an author has associated more books). Next, a REST controller endpoint fetches an `Author` without the associated `Book`. But, the View (more precisely, Jackson), forces the lazy loading of the associated `Book` as well. Since OSIV will supply the already opened `Session`, the proxies initializations take place successfully. The solution to avoid this performance penalty starts by disabling the OSIV. Further, explicitly initialize the un-fetched lazy associations. This way, the View will not force lazy loading.

**Key points:**
- disable OSIV by adding in `application.properties` this setting: `spring.jpa.open-in-view=false`
- fetch an `Author` entity and initialize its associated `Book` explicitly with (default) values (e.g., `null`)
- set `@JsonInclude(Include.NON_EMPTY)` on this entity-level to avoid rendering `null` or what is considered empty in the resulted JSON
     
 **NOTE:** If OSIV is enabled, the developer can still initialize the un-fetched lazy associations manually as long as he does this outside of a transaction to avoid flushing. But, why is this working? Since the `Session` is open, why the manually initialization of the associations of a managed entity doesn't trigger the flush? The answer can be found in the documentation of `OpenSessionInViewFilter` which specifies that: *This filter will by default not flush the Hibernate `Session`, with the flush mode set to `FlushMode.NEVER`. It assumes to be used in combination with service layer transactions that care for the flushing: The active transaction manager will temporarily change the flush mode to `FlushMode.AUTO` during a read-write transaction, with the flush mode reset to `FlushMode.NEVER` at the end of each transaction. If you intend to use this filter without transactions, consider changing the default flush mode (through the "flushMode" property).*     

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
