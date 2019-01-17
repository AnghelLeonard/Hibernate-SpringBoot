**[How To Batch Deletes In MySQL Via orphanRemoval=true](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchDeleteOrphanRemoval)**

**Description:** Batch deletes in MySQL via `orphanRemoval=true`.

**Note:** Spring `deleteAllInBatch()` and `deleteInBatch()` don't use batching. The first one simply triggers a `delete from entity_name` statement, while the second one triggers a `delete from entity_name where id=? or id=? or id=? ...` statement. Rely on `delete()` method.

**Key points:**\
     - in this example, we have a `Tournament` entity and each tournament can have several `TennisPlayer` (*one-to-many*)\
     - first, we dissociate all `TennisPlayer` from the corresponding `Tournament`\
     - second, we explicitly (manually) flush the persistent context (this will delete in batch all `TennisPlayer` thanks to `orphanRemoval=true`; if this is set to `false`, you will obtain a bunch of updates instead of deletes)\
     - third, we delete all `Tournament` via the `delete()` method (since we have dissaciated all `TennisPlayer`, the `Tournament` deletion will take advantage of batching as well)
        
**Output example:**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchDeleteOrphanRemoval/batch_delete.png)
