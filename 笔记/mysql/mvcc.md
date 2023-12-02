# MVCC是什么？
mysql的多版本并发控制

## mvcc如何保证acid的？
A：原子性：通过undolog实现，要么全部成功，要么全部失败

C: 事务追求的最终目标，需要数据库层面保证，也需要代码应用层保证。

I：隔离性：不同事务相互隔离，不会相互影响，undo log类 以及 next-key lock机制

D: 保证事务提交后，不会因为mysql宕机，而丢失数据，基于redolog实现。