**[How To Map Java `enum` To Database Via `AttributeConverter`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnumAttributeConverter)**
 
**Description:** This application maps a Java `enum` via `AttributeConverter`. In other words, it maps the `enum` values `HORROR`, `ANTHOLOGY` and `HISTORY` to the integers `10`, `20` and `30` and viceversa. This allows us to set the column type as `TINYINT/SMALLINT` which is less space-consuming than `VARCHAR(9)` needed in this case.

**Key points:**
- define a custom `AttributeConverter`
- annotate with `@Converter` the corresponding entity field
     
-----------------------------------------------------------------------------------------------------------------------    
<table>
     <tr><td><b>If you need a deep dive into the performance recipes exposed in this repository then I am sure that you will love my book "Spring Boot Persistence Best Practices"</b></td><td><b>If you need a hand of tips and illustrations of 100+ Java persistence performance issues then "Java Persistence Performance Illustrated Guide" is for you.</b></td></tr>
     <tr><td>
<a href="https://www.apress.com/us/book/9781484256251"><p align="left"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Spring%20Boot%20Persistence%20Best%20Practices.jpg" height="500" width="450"/></p></a>
</td><td>
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="right"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="500" width="450"/></p></a>
</td></tr></table>

-----------------------------------------------------------------------------------------------------------------------    
