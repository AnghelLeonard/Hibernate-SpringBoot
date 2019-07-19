**[How To Use Hibernate `@NaturalIdCache` For Skipping The Entity Identifier Retrieval](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNaturalIdCache)**

**Description:** This is a SpringBoot application that maps a natural business key using Hibernate `@NaturalId`. This implementation allows us to use `@NaturalId` as it was provided by Spring. Moreover, this application uses Second Level Cache (`EhCache`) and `CacheConcurrencyStrategy.READ_WRITE` for skipping the entity identifier retrieval.

**Key points:**
- enable Second Level Cache (`EhCache`)
- annotate entity with `@NaturalIdCache`
- annotate entity with `@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Book")`

**Output sample:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootNaturalIdCache/Hibernate%20NaturalIdCache%20first%20query.png)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
