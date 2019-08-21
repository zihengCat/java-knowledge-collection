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

- Exclusive（独占资源）：资源同一时间只能由一个线程持有，可以实现公平锁，如：`ReentrantLock`。
    - 公平锁：按照线程在队列中的排队顺序，先到者先拿到锁。
    - 非公平锁：当线程要获取锁时，无视队列顺序直接去抢锁，谁抢到就是谁的。
- Share（共享资源）：资源可以由多个线程持有，如：`Semaphore`、`CountDownLatch`。

## AQS 工作原理

AQS 只是一个同步框架，AQS 只定义了一个接口，具体资源的获取交由自定义同步器去实现（通过`state`变量）。之所以没有定义成`abstract`，是因为独占模式下只需要实现`tryAcquire()`与`tryRelease()`，而共享模式下只用实现`tryAcquireShared()与`tryReleaseShared()`，独占与共享模式不需要额外去实现另一模式下的接口。

不同的自定义同步器争用共享资源的方式也不同。自定义同步器在实现时只需要实现共享资源`state`的获取与释放方式即可，至于具体线程等待队列的维护（如：获取资源失败入队、唤醒出队等），AQS 已经在顶层实现好了。

自定义同步器主要实现以下几种方法：

- `isHeldExclusively()`：该线程是否正在独占资源（只有用到`condition`才需要去实现它）。

- `tryAcquire(int)`：以独占方式尝试获取资源，获取成功返回`true`，获取失败返回`false`。

- `tryRelease(int)`：以独占方式尝试释放资源，释放成功返回`true`，获取失败返回`false`。

- `tryAcquireShared(int)`：以共享方式尝试获取资源，返回负数表示失败，返回`0`表示成功，但已没有剩余可用资源，返回正数表示成功，且有剩余资源。

- `tryReleaseShared(int)`：以共享方式尝试释放资源，如果释放后允许唤醒后续等待结点则返回`true`，否则返回`false`。

![Concurrency-AQS-2][Concurrency-AQS-2]

> 图：AQS 加锁流程图


[Concurrency-AQS-1]: ../../images/Concurrency-AQS-1.png

[Concurrency-AQS-2]: ../../images/Concurrency-AQS-2.png

<!-- EOF -->
