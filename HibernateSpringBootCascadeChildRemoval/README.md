**[The Best Way To Remove Parent And Child Entities Via Bulk Deletions](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCascadeChildRemoval)**

**Description:** Commonly, deleting a parent and the associated children via `CascadeType.REMOVE`  and/or `orphanRemoval=true` involved several SQL statements (e.g., each child is deleted in a dedicated `DELETE` statement). When the number of entities is significant, this is far from being efficient, therefore other approaches should be employed. 

Consider `Author` and `Book` in a bidirectional-lazy `@OneToMany` association. This application exposes the best way to delete the parent(s) and the associated children in four scenarios listed below. These approaches relies on *bulk* deletions, therefore they are not useful if you want the deletions to take advantage of automatic optimistic locking mechanisms (e.g., via `@Version`): 

**Best way to delete author(s) and the associated books via *bulk* deletions when:**
- One `Author` is in Persistent Context, no `Book`
- More `Author` are in the Persistent Context, no `Book`
- One `Author` and the associated `Book` are in Persistent Context
- No `Author` or `Book` is in Persistent Context
     
**Note:** The most efficient way to delete all entities via a *bulk* deletion can be done via the built-in `deleteAllInBatch()`. 
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

