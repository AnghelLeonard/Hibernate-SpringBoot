**[How To Batch Deletes In MySQL Via SQL `ON DELETE CASCADE`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchDeleteCascadeDelete)**

**Description:** Batch deletes in MySQL via `ON DELETE CASCADE`. Auto-generated database schema will contain the `ON DELETE CASCADE` directive.

**Note:** Spring `deleteAllInBatch()` and `deleteInBatch()` don't use delete batching and don't take advantage of optimistic locking mechanism to prevent *lost updates*. They trigger *bulk* operations via `Query.executeUpdate()`, therefore, the Persistent Context is not synchronized accordingly (it is advisable to flush (before delete) and close/clear (after delete) the Persistent Context accordingly to avoid issues created by unflushed (if any) or outdated (if any) entities). The first one simply triggers a `delete from entity_name` statement, while the second one triggers a `delete from entity_name where id=? or id=? or id=? ...` statement. Both of them take advantage on `ON DELETE CASCADE` and are very efficient. For delete in batches rely on `deleteAll()`, `deleteAll(Iterable<? extends T> entities)` or `delete()` method. Behind the scene, the two flavors of `deleteAll()` relies on `delete()`. Mixing batching with database automatic actions (`ON DELETE CASCADE`) will result in a partially synchronized Persistent Context.

**Key points:**
- in this application, we have a `Author` entity and each author can have several `Book` (*one-to-many*)
- first, we remove `orphanRemoval` or set it to `false`
- second, we use only `CascadeType.PERSIST` and `CascadeType.MERGE`
- third, we set `@OnDelete(action = OnDeleteAction.CASCADE)` next to `@OneToMany`
- fourth, we set `spring.jpa.properties.hibernate.dialect` to `org.hibernate.dialect.MySQL5InnoDBDialect` (or, `MySQL8Dialect`)
- fifth, we run through each `deleteFoo()` method
        
**Output example:**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchDeleteCascadeDelete/batch%20delete%20via%20SQL%20cascade%20delete.png)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

