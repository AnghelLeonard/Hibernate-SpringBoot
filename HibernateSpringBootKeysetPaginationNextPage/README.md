**[How To Implement Keyset Pagination in Spring Boot](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/HibernateSpringBootKeysetPaginationNextPage)**

**Note**: For a list of pros and cons of *offset vs keyset* please check my book: [Java Persistence Performance Illustrated Guide](https://leanpub.com/java-persistence-performance-illustrated-guide).

**Description:** When we rely on an *offset* paging we have the performance penalty induced by throwing away *n* records before reached the desired *offset*. Larger *n* leads to a significant performance penalty. When we have a large *n* is better to rely on *keyset* pagination which maintain a "constant" time for large datasets. In order to understand how bad *offset* can perform please check this [article](http://allyouneedisbackend.com/blog/2017/09/24/the-sql-i-love-part-1-scanning-large-table/):

Screenshot from that article (*offset* pagination):
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/HibernateSpringBootKeysetPagination/offset.png)

**Need to know if there are more records?**\
By its nature, *keyset* doesn't use a `SELECT COUNT` to fetch the number of total records. But, we a little tweak we can easily say if there are more records, therefore to show a button of type `Next Page`:

`public Map<List<Player>, Boolean> fetchNextSlice(long id, int limit) {`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`List<Player> players = playerRepository.fetchAllPlayers(id, limit + 1);`
        
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`if(players.size() == (limit + 1)) {`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`players.remove(players.size() -1);`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`return Collections.singletonMap(players, true);`\
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`}`
        
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`return Collections.singletonMap(players, false);`\
`}`

A `Previous Page` button can be implemented easily based on the first record.

**Key points:**\
     - choose a column to act as the latest visited record (e.g., `id`)\
     - use this column in the `WHERE` clause of your SQL

-------------------------------

**You may like to try as well:**
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>

