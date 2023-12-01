# 线程池
java通过Executors提供了四种线程池。spring提供创建线程池的方式

## newSingleThreadExecutor
Executors.newSingleThreadExecutor(),创建单线程的线程池，仅有一个线程执行任务，
保证所有任务按照顺序执行，任务都保存到LinkedBlockingQueue中，等待唯一的线程来执行

## newFixedThreadPool
创建一个指定大小的线程池，可控制线程的最大并发数，超出的线程会在LinkedBlockingQueue阻塞队列中等待

## newCachedThreadPool
Executors.newCachedThreadPool,可缓存的无界线程池，如果长度超出线程池长度，可灵活回收，
若无可回收，则新建线程，线程空闲时间超过60秒进行自动回收，大小限制Integer.MAX_VALUE.

## newScheduledThreadPool
创建一个定长的线程池，可以指定线程池核心线程数，支持定时及周期性的任务执行

## 线程池执行任务流程？
当任务小于核心线程数会直接创建核心线程数，大于核心线程数时放入队列，
队列放不下会创建非核心线程执行任务，当超过最大线程数，触发拒绝策略。

## 线程池原理


## 线程池的拒绝策略
1：AbortPolicy 抛出异常
2：CallRunnersPolicy 调用的主线程执行任务，不创建子线程
3: DiscardPolicy 丢弃掉任务
3: DiscardOldestPolicy  丢弃掉最早的任务


参考链接：
https://blog.csdn.net/qq_40093255/article/details/116990431


