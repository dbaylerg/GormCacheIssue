# GormCacheIssue

1. Clone the project and build.
2. The project is configured for a Oracle DB just adjust the credentials or reconfigure for another DB.
3. Run the project. It will create 3 records, one for each domain A, B, C
4. Execute a POST twice to http://localhost:8080/test  (Assuming Grails starts on port 8080)
5. The output below shows that the first POST neither A, B, or C domain is cached as expected.
6. The second POST shows that domain C is cached as expected but neither A or B is a cache hit which is the issue.

Domains A and B extends from the Base domain.  All of three of these domains include in the mapping "cache true"
I would expect on the second POST to the test endpoint that all 3 records would return records from the cache.


Output
----------------------------------------------------------

Hibernate: select a0_.id as id1_0_0_, a0_.version as version2_0_0_ from base a0_ where a0_.id=? and a0_.class='app.A'
Hibernate: select b0_.id as id1_0_0_, b0_.version as version2_0_0_ from base b0_ where b0_.id=? and b0_.class='app.B'
Hibernate: select c0_.id as id1_1_0_, c0_.version as version2_1_0_ from c c0_ where c0_.id=?
2023-05-01 13:07:49.908  INFO --- [nio-8080-exec-3] i.StatisticalLoggingSessionEventListener : Session Metrics {
    12900 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    281400 nanoseconds spent preparing 3 JDBC statements;
    11756100 nanoseconds spent executing 3 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    1259000 nanoseconds spent performing 1 L2C puts;
    0 nanoseconds spent performing 0 L2C hits;
    91100 nanoseconds spent performing 1 L2C misses;
    0 nanoseconds spent executing 0 flushes (flushing a total of 0 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
}
Hibernate: select a0_.id as id1_0_0_, a0_.version as version2_0_0_ from base a0_ where a0_.id=? and a0_.class='app.A'
Hibernate: select b0_.id as id1_0_0_, b0_.version as version2_0_0_ from base b0_ where b0_.id=? and b0_.class='app.B'
2023-05-01 13:07:50.798  INFO --- [nio-8080-exec-4] i.StatisticalLoggingSessionEventListener : Session Metrics {
    11800 nanoseconds spent acquiring 1 JDBC connections;
    0 nanoseconds spent releasing 0 JDBC connections;
    320500 nanoseconds spent preparing 2 JDBC statements;
    2483900 nanoseconds spent executing 2 JDBC statements;
    0 nanoseconds spent executing 0 JDBC batches;
    0 nanoseconds spent performing 0 L2C puts;
    85600 nanoseconds spent performing 1 L2C hits;
    0 nanoseconds spent performing 0 L2C misses;
    0 nanoseconds spent executing 0 flushes (flushing a total of 0 entities and 0 collections);
    0 nanoseconds spent executing 0 partial-flushes (flushing a total of 0 entities and 0 collections)
