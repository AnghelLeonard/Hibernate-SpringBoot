**[How To Batch Deletes In MySQL Via orphanRemoval=true](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchDeleteOrphanRemoval)**
 
<b><a href="https://persistencelayer.wixsite.com/springboot-hibernate/post/how-to-batch-deletes-in-mysql-via-orphanremoval-true">If you prefer to read it as a blog-post containing the relevant snippets of code then check this post</a></b>
 
**Description:** Batch deletes in MySQL via `orphanRemoval=true`.

**Note:** Spring `deleteAllInBatch()` and `deleteInBatch()` don't use delete batching and don't take advantage of cascading removal, `orphanRemoval` and automatic optimstic locking mechanism to prevent *lost updates* (e.g., `@Version` is ignored). They rely on `Query.executeUpdate()` to trigger *bulk* operations. These operations are fast, but Hibernate doesn’t know which entities are removed, therefore, the Persistence Context is not updated accordingly (it's up to you to flush (before delete) and close/clear (after delete) the Persistence Context accordingly to avoid issues created by unflushed (if any) or outdated (if any) entities). The first one (`deleteAllInBatch()`) simply triggers a `delete from entity_name` statement and is very useful for deleting all records. The second one (`deleteInBatch()`) triggers a `delete from entity_name where id=? or id=? or id=? ...` statement, therefore, is prone to cause issues if the generated `DELETE` statement exceedes the maximum accepted size. This issue can be controlled by deleting the data in chunks, relying on `IN` operator, and so on. *Bulk* operations are faster than batching which can be achieved via the `deleteAll()`, `deleteAll(Iterable<? extends T> entities)` or `delete()` method. Behind the scene, the two flavors of `deleteAll()` relies on `delete()`. The `delete()`/`deleteAll()` methods rely on `EntityManager.remove()` therefore the Persistence Context is synchronized accordingly. If automatic optimstic locking mechanism (to prevent *lost updates*) is enabled then it will be used. Moreover, cascading removals and `orphanRemoval` works as well.

**Key points for using `deleteAll()/delete()`:**
- in this example, we have a `Author` entity and each author can have several `Book` (*one-to-many*)
- first, we use `orphanRemoval=true` and `CascadeType.ALL`
- second, we dissociate all `Book` from the corresponding `Author`
- third, we explicitly (manually) flush the Persistent Context; is time for `orphanRemoval=true` to enter into the scene; thanks to this setting, all disassociated books will be deleted; the generated `DELETE` statements are batched (if `orphanRemoval` is set to `false`, a bunch of updates will be executed instead of deletes)
- forth, we delete all `Author` via the `deleteAll()` or `delete()` method (since we have dissaciated all `Book`, the `Author` deletion will take advantage of batching as well)

-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
