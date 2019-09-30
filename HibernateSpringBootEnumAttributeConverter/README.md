**[Quickest Mapping Of Enums](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnumStringInt)**
 
**Description:** This application uses `EnumType.ORDINAL` and `EnumType.STRING` for mapping Java `enum` type to database. As a rule of thumb, strive to keep the data types as small as possible (e.g., for `EnumType.ORDINAL` use `SMALLINT`, while for `EnumType.STRING` use `VARCHAR(n)`). Relying on `EnumType.ORDINAL` should be more efficient but is less expressive than `EnumType.STRING`.

**Key points:**\
     - strive for smallest data types (e.g., for `EnumType.ORDINAL` set `@Column(columnDefinition = "SMALLINT")`)
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
