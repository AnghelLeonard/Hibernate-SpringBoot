**[How To Batch Deletes In MySQL Via orphanRemoval=true](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchDeleteOrphanRemoval)**
 
**Description:** Batch deletes in MySQL via `orphanRemoval=true`.

**Note:** Spring `deleteAllInBatch()` and `deleteInBatch()` don't use batching, don't take advantage of optimistic locking to prevent *lost updates* and don't take advantage of `orphanRemoval=true`. They trigger *bulk* operations and the Persistent Context is not synchronized accordingly (it is advisable to flush (before delete) and close/clear (after delete) the Persistent Context accordingly to avoid issues created by unflushed (if any) or outdated (if any) entities). The first one simply triggers a `delete from entity_name` statement, while the second one triggers a `delete from entity_name where id=? or id=? or id=? ...` statement. Using these methods for deleting parent-entities and the associated entites (child-entities) requires explicit calls for both sides. For batching rely on `deleteAll()`, `deleteAll(Iterable<? extends T> entities)` or even better, on `delete()` method. Behind the scene, `deleteAll()` methods uses `delete()`. The `delete()` and `deleteAll()` methods rely on `EntityManager.remove()`, therefore, the Persistent Context is synchronized. Moreover, cascading removals and `orphanRemoval` are taken into account.

**Key points for using `deleteAll()/delete()`:**
- in this example, we have a `Author` entity and each author can have several `Book` (*one-to-many*)
- first, we use `orphanRemoval=true` and `CascadeType.ALL`
- second, we dissociate all `Book` from the corresponding `Author`
- third, we explicitly (manually) flush the Persistent Context; is time for `orphanRemoval=true` to enter into the scene; thanks to this setting, all disassociated books will be deleted; the generated `DELETE` statements are batched (if `orphanRemoval` is set to `false`, a bunch of updates will be executed instead of deletes)
- forth, we delete all `Author` via the `deleteAll()` or `delete()` method (since we have dissaciated all `Book`, the `Author` deletion will take advantage of batching as well)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
