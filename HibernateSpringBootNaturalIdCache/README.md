**[How To Use Hibernate `@NaturalIdCache` For Skipping The Entity Identifier Retrieval](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootNaturalIdCache)**

**Description:** This is a SpringBoot - MySQL application that maps a natural business key using Hibernate `@NaturalId`. This implementation allows us to use `@NaturalId` as it was provided by Spring. Moreover, this application uses Second Level Cache (`EhCache`) and `@NaturalIdCache` for skipping the entity identifier retrieval.

**Key points:**
- enable Second Level Cache (`EhCache`)
- annotate entity with `@NaturalIdCache` for caching natural ids
- optionally, annotate entity with `@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "Book")` for caching entites as well

**Output sample (for MySQL with `IDENTITY` generator, `@NaturalIdCache` and `@Cache`):**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootNaturalIdCache/Hibernate%20NaturalIdCache%20first%20query.png)

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootNaturalIdCache/Hibernate%20NaturalIdCache%20second%20query.png)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
