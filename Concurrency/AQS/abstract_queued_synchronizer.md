# Java 并发框架 - AQS（抽象队列同步器）

##  什么是 AQS

AQS 全名 AbstractQueuedSynchronizer，类如其名，抽象队列同步器，是 Java 并发编程中实现同步器的一个框架。**AQS 定义了一套多线程访问共享资源的同步器框架**，JUC 中许多同步类实现都依赖于它，如：`ReentrantLock`、`Semaphore`、`CountDownLatch`等。

![Concurrency-AQS-1][Concurrency-AQS-1]

> 图：AQS 结构

AQS 内部维护了一个`volatile int state`（代表共享资源）整数和一个 FIFO 线程等待双向队列（多线程争用资源被阻塞时放入队列）。

这里`volatile`是核心关键词，整数`state`具有 volatile 语义，访问方式有三种：

- `getState()`：获取变量值

- `setState()`：设置变量值

- `compareAndSetState()`：CAS 原子修改变量值

在 AQS 中存在一个 FIFO 双向队列，队列中的节点表示被阻塞的线程，队列节点元素有4种状态，每种状态表示线程被阻塞的原因。

- `CANCELLED`：线程因超时或中断而被放到队列中

- `CONDITION`：线程因某个条件不满足而被放到队列中，需要等待一个条件，直到条件成立后才会出队

- `SIGNAL`：线程需要被唤醒

- `PROPAGATE`：共享模式下，当前节点执行释放操作后需传播通知给后面所有节点

一个共享资源同一时间只能由一条线程持有，也可以被多个线程持有，由此 AQS 定义了两种资源共享方式：

- Exclusive（独占资源）：资源同一时间只能由一个线程持有，如：`ReentrantLock`。

- Share（共享资源）：资源可以由多个线程持有，如：`Semaphore`、`CountDownLatch`。




[Concurrency-AQS-1]: ../../images/Concurrency-AQS-1.png

<!-- EOF -->
