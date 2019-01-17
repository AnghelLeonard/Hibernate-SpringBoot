**[How To Batch Deletes In MySQL Via SQL "on delete cascade"](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootBatchDeleteCascadeDelete)**

**Description:** Batch deletes in MySQL via `on delete cascade`. Auto-generated database schema will contain `ON DELETE CASCADE` directive.

**Note:** Spring `deleteAllInBatch()` and `deleteInBatch()` don't use batching. The first one simply triggers a `delete from entity_name` statement, while the second one triggers a `delete from entity_name where id=? or id=? or id=? ...` statement. Rely on `delete()` method.

**Key points:**\
     - in this example, we have a `Tournament` entity and each tournament can have several `TennisPlayer` (*one-to-many*)\
     - first, we remove `orphanRemoval` or set it to `false`\
     - second, we use only `CascadeType.PERSIST` and `CascadeType.MERGE`\
     - third, we set `@OnDelete(action = OnDeleteAction.CASCADE)` next to `@OneToMany`\
     - fourth, we set `spring.jpa.properties.hibernate.dialect` to `org.hibernate.dialect.MySQL5InnoDBDialect`\
     - fifth, we use the Spring `delete()` method to delete all `Tournament`
        
**Output example:**

![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootBatchDeleteCascadeDelete/batch_delete.png)
