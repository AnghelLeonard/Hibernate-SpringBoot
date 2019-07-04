71. **[How To Remove The Extra SELECT COUNT Query in Spring Boot Paging Via Window Functions](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootWindowFunctionPaging)**
 
**Description:** When we rely on an *offset* paging we have the performance penalty induced by throwing away *n* records before reached the desired *offset*. Larger *n* leads to a significant performance penalty. But, this is not the only performance penalty. Most of the time we want to count the total number of rows to calculate the total number of possible pages, so this is an extra `SELECT COUNT`. So, if we don't want to go with *keyset* pagination and avoid counting that total number of records, which can be very costly, we have to tackle this performance penalty somehow. For databases vendors that support *Window Functions* there is a solution relying on `COUNT(*) OVER()` as in this application that uses this window function in a native query against MySQL 8.

**Key points:**\
     - create a DTO projection to cover the extra-column added by the `COUNT(*) OVER()` window function\
     - write a native query relying on this window function

**Example:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootWindowFunctionPaging/offset%20pagination%20via%20window%20function.png)

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
