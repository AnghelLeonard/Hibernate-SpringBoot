**[How To Map `@ManyToOne` Relationship To A SQL Query Via The Hibernate `@JoinFormula`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootJoinFormula)**
 
**Description:** This application is an example of mapping the JPA `@ManyToOne` relationship to a SQL query via the Hibernate `@JoinFormula` annotation. We start with two entities, `Author` and `Book`, involved in a unidirectional `@ManyToOne` relationship. Each book has a price. While we fetch a book by id (let's call it book `A`), we want to fetch another book `B` of the same author whose price is the next smaller price in comparison with book `A` price.

**Key points:**
- fetching the book `B` is done via `@JoinFormula`
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
