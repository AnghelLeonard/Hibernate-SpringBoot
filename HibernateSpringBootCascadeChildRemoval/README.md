**[The Best Way To Remove Parent And Child Entities](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCascadeChildRemoval)**

**Description:** Commonly, deleting a parent and the associated children via `CascadeType.REMOVE`  and/or `orphanRemoval=true` involved several SQL statements (e.g., each child is deleted in a dedicated `DELETE` statement). This is far from being efficient, therefore another approaches should be employed. 

**Key points:**\
     - when the parent and associated children are loaded in Persistent Context rely on the built-in `deleteInBatch()`\
     - when only the parent is loaded in the Persistent Context rely on custom *bulk* operations (e.g., `IN` clause)\
     - when the parent and associated children are not loaded in the Persistent Context rely on custom *bulk* operations
     
**Note:** The most efficient way to delete all parents and/or all children can be done via the built-in `deleteAllInBatch()`.
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
