**[How To Use `@MapsId` For Sharing Identifier In `@OneToOne` Relationships](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOneToOneMapsId)**

**Description:** Instead of *regular* unidirectional/bidirectional `@OneToOne` better rely on an unidirectional `@OneToOne` and `@MapsId`. This application is a proof of concept. 

**Key points:**\
- use `@MapsId` on child side
- use `@JoinColumn` to customize the name of the primary key column
- mainly, for `@OneToOne` associations, `@MapsId` will share the primary key with the parent table (`id` property acts as both primary key and foreign key)    
     
**Note:**
- `@MapsId` can be used for `@ManyToOne` as well

---------------------------------

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
