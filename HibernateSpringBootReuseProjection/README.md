**[Reusing Spring projection](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootReuseProjection)**
 
**Description:** This application is a sample of reusing an interface-based Spring projection.

**Key points:**
- define an interface-based Spring projection containing getters for the wider case 
- rely on class-level `@JsonInclude(JsonInclude.Include.NON_DEFAULT)` annotation to avoid serialization of default fields (e.g., fields that are not available in the current projection and are `null` - these fields haven't been fetched in the current query)
- this is useful to Jackson that will not serialize in the resulted JSON the missing fields (e.g., `null` fields)

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
