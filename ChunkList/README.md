**[How To Efficiently Chunk A Java List](https://github.com/AnghelLeonard/Hibernate-SpringBoot/tree/master/ChunkList)**
 
**Description:** Is a common scenario to have a big `List` and to need to chunk it in multiple smaller `List` of given size. For example, if we want to employee a concurrent batch implementation we need to give to each thread a sublist of items. Chunking a list can be done via Google Guava, `Lists.partition(List list, int size)` [method](https://guava.dev/releases/22.0/api/docs/com/google/common/collect/Lists.html#partition-java.util.List-int-) or Apache Commons Collections, `ListUtils.partition(List list, int size)` [method](https://commons.apache.org/proper/commons-collections/apidocs/org/apache/commons/collections4/ListUtils.html#partition(java.util.List,%20int)). But, it can be implemented in plain Java as well. This application exposes 6 ways to do it. The trade-off is between the speed of implementation and speed of execution. For example, while the implementation relying on grouping collector is not performing very well, it is quite simple and fast to write it.

**Key points:**
- the fastest execution is provided by `Chunk.java` class which relies on the built-in `List.subList()` method
     
**Time-performance trend graphic for chunking 500, 1_000_000, 10_000_000 and 20_000_000 items:**\
![](https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/ChunkList/head-to-head.png)
     
<a href="https://leanpub.com/java-persistence-performance-illustrated-guide"><p align="center"><img src="https://github.com/AnghelLeonard/Hibernate-SpringBoot/blob/master/Java%20Persistence%20Performance%20Illustrated%20Guide.jpg" height="410" width="350"/></p></a>
