**[The Best Way To Map The `@OneToMany` Bidirectional Association](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOneToManyBidirectional)**

**Description:** This application is a proof of concept of how is correct to implement the bidirectional `@OneToMany` association. 

**Key points:**
- always cascade from parent to child
- use `mappedBy` on the parent
- use `orphanRemoval` on parent in order to remove children without references
- use helper methods on parent to keep both sides of the association in sync
- use lazy fetching on both side of the association
- as entities identifiers, use assigned identifiers (business key, natural key (`@NaturalId`)) and/or database-generated identifiers and override (on child-side) properly the `equals()` and `hashCode()` methods as [here](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/)
- if `toString()` need to be overridden, then pay attention to involve only the basic attributes fetched when the entity is loaded from the database
     
**Note:** Pay attention to remove operations, especially to removing child entities. The `CascadeType.REMOVE` and `orphanRemoval=true` may produce too many queries. In such scenarios, relying on *bulk* operations is most of the time the best way to go for deletions.
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
