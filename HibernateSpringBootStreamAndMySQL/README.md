
-----------------------------------------------------------------------------------------------------------------------    

86. **[How To Stream Result Set Via Spring Data In MySQL](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootStreamAndMySQL)**

**Note:** For web-applications, pagination should be the way to go, not streaming. But, if you choose streaming then keep in mind the golden rule: keep th result set as small as posible. Also, keep in mind that the Execution Plan might not be as efficient as when using SQL-level pagination.

**Description:** This application is an example of streaming the result set via Spring Data and MySQL. This example can be adopted for databases that fetches the entire result set in a single roundtrip causing performance penalties.

**Key points:**\
     - rely on forward-only result set (default in Spring Data)\
     - rely on read-only statement (add `@Transactional(readOnly=true)`)\
     - set the fetch-size set (e.g. 30, or row-by-row; `Integer.MIN_VALUE` (recommended in MySQL))
