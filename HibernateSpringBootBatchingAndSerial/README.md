**[Why To Avoid PostgreSQL (`BIG`)`SERIAL` In Batching Inserts Via Hibernate](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchingAndSerial)**

**Description:** In PostgreSQL, using `GenerationType.IDENTITY` will disable insert batching. The `(BIG)SERIAL` is acting "almost" like MySQL, `AUTO_INCREMENT`. In this application, we use the `GenerationType.SEQUENCE` which permits insert batching, and we optimize it via the `hi/lo` optimization algorithm. 

**Key points:**
- use `GenerationType.SEQUENCE` instead of `GenerationType.IDENTITY`
- rely on the `hi/lo` algorithm to fetch a *hi* value in a database roundtrip (the *hi* value is useful for generating identifiers in-memory) 
- you can go even further and use the Hibernate `pooled` and `pooled-lo` identifier generators (these are optimizations of `hi/lo` that allows external services to use the database without causing duplication keys errors)
   
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchingAndSerial/PostgreSQL%20(BIG)SERIAL%20and%20Batching%20Inserts.png)

-----------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
