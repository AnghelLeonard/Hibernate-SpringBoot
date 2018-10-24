
**[@OneToOne and @MapsId](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootOneToOneMapsId)**

**Description:** Instead of a bidirectional `@OneToOne` better rely on an unidirectional `@OneToOne` and `@MapsId`. This application is a proof of concept. 

**Key points:**\
     - use `@MapsId` on child side\
     - basically, for `@OneToOne` associations, this will share the Primary Key with the parent table
     <a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
