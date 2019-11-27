**[Quickest Mapping Of Java Enums](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnumStringInt)**
  
**Description:** This application uses `EnumType.ORDINAL` and `EnumType.STRING` for mapping Java `enum` type to database. As a rule of thumb, strive to keep the data types as small as possible (e.g., for `EnumType.ORDINAL` use `TINYINT/SMALLINT`, while for `EnumType.STRING` use `VARCHAR(max_needed_bytes)`). Relying on `EnumType.ORDINAL` should be more efficient but is less expressive than `EnumType.STRING`.

**Key points:**
- strive for smallest data types (e.g., for `EnumType.ORDINAL` set `@Column(columnDefinition = "TINYINT")`)
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    

