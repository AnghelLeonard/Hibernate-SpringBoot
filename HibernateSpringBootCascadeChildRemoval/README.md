**[The Best Way To Remove Parent And Child Entities](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCascadeChildRemoval)**

**Description:** Commonly, deleting a parent and the associated children via `CascadeType.REMOVE`  and/or `orphanRemoval=true` involved several SQL statements (e.g., each child is deleted in a dedicated `DELETE` statement). When the number of entities is significant, this is far from being efficient, therefore other approaches should be employed. 

Consider `Author` and `Book` in a bidirectional-lazy `@OneToMany` association. This application exposes the best way to delete the parent(s) and the associated children in four scenarios listed below.

**Best way to delete author(s) and the associated books when:**
- One `Author` is in Persistent Context, no `Book`
- More `Author` are in the Persistent Context, no `Book`
- One `Author` and the associated `Book` are in Persistent Context
- No `Author` or `Book` is in Persistent Context
     
**Note:** The most efficient way to delete all entities can be done via the built-in `deleteAllInBatch()`. 
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
