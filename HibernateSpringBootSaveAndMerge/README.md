**[How To Optimize The Merge Operation Using Update](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootSaveAndMerge)** 
 
**Description:** Behind the built-in Spring Data `save()` there is a call of `EntityManager#persist()` or `EntityManager#merge()`. It is important to know this aspect in several cases. Among this cases, we have the entity update case (simple update or update batching). 

Consider `Author` and `Book` involved in a bidirectional-lazy `@OneToMany` association. And, we load an `Author`, detach it, update it in the *detached* state, and save it to the database via `save()` method. Calling `save()` will come with the following two issues resulting from calling `merge()` behind the scene:

- there will be two SQL statements, one `SELECT` (merge) and one `UPDATE`
- the `SELECT` will contain a `LEFT OUTER JOIN` to fetch the associated `Book` as well (we don't need the books!)

How about triggering only the `UPDATE` instead of this? The solution relies on calling `Session#update()`. Calling `Session.update()` requires to unwrap the `Session` via `entityManager.unwrap(Session.class)`.

**Key points:**
- calling `Session.update()` will trigger only the `UPDATE` (there is no `SELECT`)
- `Session.update()` works with *versioned* optimistic locking mechanism as well (so, *lost updates* are prevented)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

