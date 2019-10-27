**[How To Generate Custom Sequence IDs](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootCustomSequenceGenerator)**

**Description:** This is a Spring Boot example of using the `hi/lo` algorithm and a custom implementation of `SequenceStyleGenerator` for generating custom sequence IDs (e.g, `A-0000000001`, `A-0000000002`, ...).

**Key points:**
- extend `SequenceStyleGenerator` and override the `configure()` and `generate()` methods
- set this generator in entities

<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
