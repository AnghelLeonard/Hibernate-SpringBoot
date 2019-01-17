**[How To Batch Deletes In MySQL Via orphanRemoval=true](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchDeleteOrphanRemoval)**

**Description:** Batch deletes in MySQL via `orphanRemoval=true`.

**Note:** Spring `deleteAllInBatch()` and `deleteInBatch()` don't use batching. The first one simply triggers a `delete from entity_name` statement, while the second one triggers a `delete from entity_name where id=? or id=? or id=? ...` statement. Rely on `delete()` method.

**Key points:**\
     - in this example, we have a `Tournament` entity and each tournament can have several `TennisPlayer` (*one-to-many*)\
     - first, we use `orphanRemoval=true` and only `CascadeType.PERSIST` and `CascadeType.MERGE`\
     - second, we dissociate all `TennisPlayer` from the corresponding `Tournament`\
     - third, we explicitly (manually) flush the persistent context (this will delete in batch all `TennisPlayer` thanks to `orphanRemoval=true`; if this is set to `false`, you will obtain a bunch of updates instead of deletes)\
     - forth, we delete all `Tournament` via the `delete()` method (since we have dissaciated all `TennisPlayer`, the `Tournament` deletion will take advantage of batching as well)
        
**Output example:**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchDeleteOrphanRemoval/batch_delete.png)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
