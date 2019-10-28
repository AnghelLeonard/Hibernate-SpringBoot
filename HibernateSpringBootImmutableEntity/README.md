**[How To Declare Immutable Entities And Store Them In Second Level Cache (e.g., `EhCache`)](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootImmutableEntity)**

**Description:** This application is a sample of declaring an immutable entity. Moreover, the immutable entity will be stored in Second Level Cache via `EhCache` implementation.

**Key points of declaring an immutable entity:**
- annotate the entity with `@Immutable (org.hibernate.annotations.Immutable)`
- avoid any kind of associations
- set `hibernate.cache.use_reference_entries configuration` to `true`

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
