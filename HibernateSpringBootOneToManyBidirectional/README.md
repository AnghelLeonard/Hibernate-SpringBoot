**[OneToMany Bidirectional](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOneToManyBidirectional)**

**Description:** This application is a proof of concept of how is correct to implement the bidirectional `@OneToMany` association. 

**Key points:**\
     - always cascade from parent to child\
     - use `mappedBy` on the parent\
     - use `orphanRemoval` on parent in order to remove children without references\
     - use helper methods on parent to keep both sides of the association in sync\
     - use lazy fetch\
     - use a natural/business key or use entity identifier and override `equlas()` and `hashCode()` as [here](https://vladmihalcea.com/the-best-way-to-implement-equals-hashcode-and-tostring-with-jpa-and-hibernate/)         

