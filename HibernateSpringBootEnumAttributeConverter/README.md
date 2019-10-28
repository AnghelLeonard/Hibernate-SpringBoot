**[How To Map Java `enum` To Database Via `AttributeConverter`](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootEnumAttributeConverter)**
 
**Description:** This application maps a Java `enum` via `AttributeConverter`. In other words, it maps the `enum` values `HORROR`, `ANTHOLOGY` and `HISTORY` to the integers `10`, `20` and `30` and viceversa. This allows us to set the column type as `TINYINT/SMALLINT` which is less space-consuming than `VARCHAR(9)` needed in this case.

**Key points:**
- define a custom `AttributeConverter`
- annotate with `@Converter` the corresponding entity field
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
