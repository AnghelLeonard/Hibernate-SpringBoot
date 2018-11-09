**[How To Avoid LazyInitializationException Via JOIN FETCH](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinFetch)**

**Description:** Typically, when we get a `LazyInitializationException` we tend to modify the relationship fetching type from `LAZY` to `EAGER`. That is bad! This is a [code smell](https://vladmihalcea.com/eager-fetching-is-a-code-smell/). Best way to avoid this exception is to rely on `JOIN FETCH` + DTOs (if needed). This application is a `JOIN FETCH` example with no DTOs. But, based on the DTOs examples from this repo, you can easily adapt it to use DTOs as well.

**Key points:**\
     - define two related entities (e.g., `Category` and `Product` in a one-to-many lazy bidirectional relationship)\
     - write a JPQL `JOIN FETCH` to fetch a category including products\
     - write a JPQL `JOIN FETCH` to fetch all products including categories

**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootJoinFetch/sample%201.png) 
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootJoinFetch/sample2.png)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
