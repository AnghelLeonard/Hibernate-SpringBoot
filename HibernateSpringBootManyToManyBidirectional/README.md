
**[How To Correctly Write a Bidirectional @ManyToMany Association](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootManyToManyBidirectional)**

**Description:** This application is a proof of concept of how is correct to implement the bidirectional `@ManyToMany` association. 

**Key points:**\
     - choose and set the owner of the relationship via `mappedBy`\    
     - use `Set` not `List`\
     - use helper methods on the owner of the relationship to keep both sides of the association in sync\
     - on the owner of the relationship use `CascadeType.PERSIST` and `CascadeType.MERGE, but avoid `CascadeType.REMOVE`\
     - on the owner of the relationship set up join table columns\
     - `@ManyToMany`is lazy by default; keep it this way!\
     - use a natural/business key or use generated entity identifier and override `equals()` and `hashCode()` as [here](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/)\
     - if `toString()` need to be overridden, then pay attention to involve only for the basic attributes fetched when the entity is loaded from the database
          
**Output example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootManyToManyBidirectional/sample.png)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
