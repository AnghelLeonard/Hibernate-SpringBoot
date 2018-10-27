**[How @ElementCollection Without @OrderColumn Works](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootElementCollectionNoOrderColumn)**

**Description:** This application reveals the performance penalties of using `@ElementCollection`. In this case, without `OrderColumn`.

**Key points:**\
     - an `@ElementCollection` doesn't have a Primary Key\
     - an `@ElementCollection` is mapped in a separate table\
     - avoid `@ElementCollection` when you have a lot of inserts and deletes in/from it since the database has to delete all existing rows in order to add a new one or delete one\
     - the more items we have in this table the greater the performance penalty
     
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootElementCollectionNoOrderColumn/sample.png)
