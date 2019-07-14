**[How To Quickly Reproduce N+1 Performance Issue](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSimulateNPlus1)**

**Description:** N+1 is an issue of lazy fetching (but, eager is not exempt). This application reproduce the N+1 behavior.

**Key points:**\
     - define two entities, `Author` and `Book` in a lazy bidirectional `@OneToMany` relationship\
     - fetch all `Book` lazy, so without `Author` (results in 1 query)\
     - loop the fetched `Book` collection and for each entry fetch the corresponding `Author` (results N queries)\
     -or, fetch all `Author` lazy, so without `Book` (results in 1 query)\
     - loop the fetched `Author` collection and for each entry fetch the corresponding `Book` (results N queries)
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootSimulateNPlus1/simulate%20N%2B1.png)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
