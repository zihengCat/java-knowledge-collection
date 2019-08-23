# Java 线程池简介

在 Web 开发中，服务器需要接受并处理请求，所以会为一个请求来分配一个线程来进行处理。如果每次请求都新创建一个线程的话实现起来非常简便，但是存在问题：

如果并发的请求数量非常多，但每个线程执行的时间很短，这样就会频繁的创建和销毁线程，如此一来会大大降低系统的效率。可能出现服务器在为每个请求创建新线程和销毁线程上花费的时间和消耗的系统资源要比处理实际的用户请求的时间和资源更多。

那么有没有一种办法使执行完一个任务，并不被销毁，而是可以继续执行其他的任务呢？

这就是线程池的目的了。线程池为线程生命周期的开销和资源不足问题提供了解决方案。**通过对多个任务重用线程，线程创建的开销被分摊到了多个任务上**。

什么时候使用线程池？

- 单个任务处理时间比较短

- 需要处理的任务数量很大

使用线程池的好处？

- 降低资源消耗。通过重复利用已创建的线程降低线程创建和销毁造成的消耗。

- 提高响应速度。当任务到达时，任务可以不需要的等到线程创建就能立即执行。

- 提高线程的可管理性。线程是稀缺资源，如果无限制的创建，不仅会消耗系统资源，还会降低系统的稳定性，使用线程池可以进行统一的分配，调优和监控。

Java 中的线程池是用`ThreadPoolExecutor`类来实现的，本文结合 JDK 1.8 类源码来分析一下这个类内部对于线程的创建, 管理以及后台任务的调度等方面的执行原理。

![Concurrency-ThreadPool-1][Concurrency-ThreadPool-1]

> 图：Java 线程池类图

# Executor 框架

Executor 框架是根据一组执行策略调用、调度、执行、控制线程的异步任务执行框架，目的是提供一种将「任务提交」与「任务运行」分离的机制。

J.U.C 提供了三个 Executor 接口：

- `Executor`：一个运行新任务的简单接口。

- `ExecutorService`：扩展了`Executor`接口，添加了一些用来管理执行器生命周期和任务生命周期的方法。

- `ScheduledExecutorService`：扩展了`ExecutorService`接口，支持`Future`和定时任务。

## Executor 接口

Executor 接口只有一个`execute()`方法，用来替代通常创建或启动线程的方法。如：使用`Thread`来创建并启动线程。

Executor 接口`execute()`方法的工作模式取决于其具体实现，可能是创建一个新线程并立即启动，也有可能是使用已有的工作线程来运行传入的任务，也可能是根据设置线程池的容量或者阻塞队列的容量来决定是否要将传入的线程放入阻塞队列中或者拒绝接收传入的线程。

```java
public interface Executor {
    void execute(Runnable command);
}
```
> 代码清单：`Executor`接口源码

## ExecutorService 接口

ExecutorService 接口继承自 Executor 接口，提供了线程池管理与终止的方法，以及可为跟踪一个或多个异步任务执行状况而生成`Future`的方法。如果需要支持即时关闭，则任务需要正确处理中断。

```java
public interface ExecutorService extends Executor {
    void shutdown();
    List<Runnable> shutdownNow();
    boolean isShutdown();
    boolean isTerminated();
    boolean awaitTermination(long timeout, TimeUnit unit)
        throws InterruptedException;
    <T> Future<T> submit(Callable<T> task);
    <T> Future<T> submit(Runnable task, T result);
    Future<?> submit(Runnable task);
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
        throws InterruptedException;
    <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks,
                                  long timeout, TimeUnit unit)
        throws InterruptedException;
    <T> T invokeAny(Collection<? extends Callable<T>> tasks)
        throws InterruptedException, ExecutionException;
    <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                    long timeout, TimeUnit unit)
        throws InterruptedException, ExecutionException, TimeoutException;
}
```
> 代码清单：`ExecutorService`接口源码

## ScheduledExecutorService 接口

ScheduledExecutorService 接口扩展了 ExecutorService 接口，增加了 Schedule 调度方法。调用`schedule()`方法可以在指定的延时后执行一个`Runnable`或者`Callable`任务。接口还定义了按照指定时间间隔定期执行任务的`scheduleAtFixedRate()`方法与`scheduleWithFixedDelay()`方法。

```java
public interface ScheduledExecutorService extends ExecutorService {
    public ScheduledFuture<?> schedule(Runnable command,
                                       long delay, TimeUnit unit);
    public <V> ScheduledFuture<V> schedule(Callable<V> callable,
                                           long delay, TimeUnit unit);
    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command,
                                                  long initialDelay,
                                                  long period,
                                                  TimeUnit unit);
    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command,
                                                     long initialDelay,
                                                     long delay,
                                                     TimeUnit unit);
}
```
> 代码清单：`ScheduledExecutorService`接口源码

# ThreadPoolExecutor 源码分析

`ThreadPoolExecutor`是最常用的线程池实现类。`ThreadPoolExecutor`继承自抽象父类`AbstractExecutorService`，实现了`ExecutorService`接口。

## 重要字段

`ctl`是对线程池的运行状态和线程池中有效线程的数量进行控制的一个字段，它包含两部分信息：线程池的运行状态`runState`与线程池内有效线程的数量`workerCount`。可以看到，使用了一枚`Integer`整型来保存，高3位保存`runState`，低29位保存`workerCount`。`COUNT_BITS`为29，`CAPACITY`为`2^29`，表示`workerCount`上限值，大约是5亿+。

```java
public class ThreadPoolExecutor extends AbstractExecutorService {

    private final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;

    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

}
```
> 代码清单：`ThreadPoolExecutor`重要字段

线程池一共有五种状态, 分别是:

- `RUNNING`：能接受新提交的任务，并且也能处理阻塞队列中的任务；

- `SHUTDOWN`：关闭状态，不再接受新提交的任务，但却可以继续处理阻塞队列中已保存的任务。在线程池处于`RUNNING`状态时，调用`shutdown()`方法会使线程池进入到该状态。

- `STOP`：不能接受新任务，也不处理队列中的任务，会中断正在处理任务的线程。在线程池处于`RUNNING`或`SHUTDOWN`状态时，调用`shutdownNow()`方法会使线程池进入到该状态；
- `TIDYING`：如果所有的任务都已终止，`workerCount`（有效线程数）为0，线程池进入该状态后会调用`terminated()`方法进入`TERMINATED`状态。

- `TERMINATED`：在`terminated()`方法执行完后进入该状态，默认`terminated()`方法中不做任何事情。进入`TERMINATED`条件如下：
    - 线程池不处于`RUNNING`状态；
    - 线程池不处于`TIDYING`状态或`TERMINATED`状态；
    - 如果线程池状态处于`SHUTDOWN`并且`workerQueue`为空；
    - 有效线程数`workerCount`为0；
    - 设置`TIDYING`状态成功。

线程池的状态转换过程如图所示：

![Concurrency-ThreadPool-2][Concurrency-ThreadPool-2]

> 图：线程池状态转换

## `ctl`相关方法

几个`ctl`状态计算与获取的方法：

```java
private static int runStateOf(int c)     { return c & ~CAPACITY; }
private static int workerCountOf(int c)  { return c & CAPACITY; }
private static int ctlOf(int rs, int wc) { return rs | wc; }
```
> 代码清单：`ctl`相关方法

- `runStateOf()`：获取运行状态

- `workerCountOf()`：获取活动线程数

- `ctlOf()`：获取运行状态和活动线程数

## 构造函数

看一下线程池`ThreadPoolExecutor`的构造函数。

```java
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler) {
    if (corePoolSize < 0 ||
        maximumPoolSize <= 0 ||
        maximumPoolSize < corePoolSize ||
        keepAliveTime < 0) {
        throw new IllegalArgumentException();
    }
    if (workQueue == null || threadFactory == null || handler == null) {
        throw new NullPointerException();
    }
    this.acc = System.getSecurityManager() == null ?
            null :
            AccessController.getContext();
    this.corePoolSize = corePoolSize;
    this.maximumPoolSize = maximumPoolSize;
    this.workQueue = workQueue;
    this.keepAliveTime = unit.toNanos(keepAliveTime);
    this.threadFactory = threadFactory;
    this.handler = handler;
}
```
> 代码清单：`ThreadPoolExecutor`构造函数

- **`corePoolSize`**：核心线程数量，当有新任务在`execute(`)方法提交时，会执行以下判断：
    - 如果运行的线程少于`corePoolSize`，则创建新线程来处理任务，即使线程池中的其他线程是空闲的；
    - 如果线程池中的线程数量大于等于`corePoolSize`且小于`maximumPoolSize`，则只有当`workQueue`满时才创建新的线程去处理任务；
    - 如果设置的`corePoolSize`和`maximumPoolSize`相同，则创建的线程池的大小是固定的，这时如果有新任务提交，若`workQueue`未满，则将请求放入`workQueue`中，等待有空闲的线程去从`workQueue`中取任务并处理；
    - 如果运行的线程数量大于等于`maximumPoolSize`，这时如果`workQueue`已经满了，则通过`handler`所指定的策略来处理任务；

    所以，任务提交时，判断的顺序为：corePoolSize –> workQueue –> maximumPoolSize。
- **`maximumPoolSize`**：最大线程数量。

- **`workQueue`**：保存等待执行任务的阻塞队列，当提交一个新任务到线程池以后, 线程池会根据当前线程池中正在运行着的线程的数量来决定对该任务的处理方式，当任务提交时，如果线程池中的线程数量大于等于`corePoolSize`，则把该任务封装成一个`Worker`对象放入等待队列，主要有以下几种处理方式：
    - **直接切换**：这种方式常用的队列是`SynchronousQueue`，该队列不保存任务，而是直接将任务交给工作线程。
    - **无界队列**：一般使用基于链表的阻塞队列`LinkedBlockingQueue`。如果使用这种方式，那么线程池中能够创建的最大线程数就是`corePoolSize`，而`maximumPoolSize`就不会起作用了（后面也会说到）。当线程池中所有的核心线程都处于`RUNNING`状态时，一个新提交的任务就会放入等待队列中。
    - **有界队列**：一般使用`ArrayBlockingQueue`。使用该方式可以将线程池的最大线程数量限制为`maximumPoolSize`，这样能够降低资源的消耗，但同时这种方式也使得线程池对线程的调度变得更困难，因为线程池和队列的容量都是有限的值，所以要想使线程池处理任务的吞吐率达到一个相对合理的范围，又想使线程调度相对简单，并且还要尽可能的降低线程池对资源的消耗，就需要合理的设置这两个数量。

    如果要想降低系统资源的消耗（包括CPU的使用率，操作系统资源的消耗，上下文环境切换的开销等）, 可以设置较大的队列容量和较小的线程池容量, 但这样也会降低线程处理任务的吞吐量。如果提交的任务经常发生阻塞，那么可以考虑通过调用 setMaximumPoolSize() 方法来重新设定线程池的容量。如果队列的容量设置的较小，通常需要将线程池的容量设置大一点，这样CPU的使用率会相对的高一些。但如果线程池的容量设置的过大，则在提交的任务数量太多的情况下，并发量会增加，那么线程之间的调度就是一个要考虑的问题，因为这样反而有可能降低处理任务的吞吐量。

- **`keepAliveTime`**：线程池维护线程所允许的空闲时间。当线程池中的线程数量大于`corePoolSize`的时候，如果这时没有新的任务提交，核心线程外的线程会等待直到等待时间超过`keepAliveTime`，再销毁线程。

- **`unit`**：时间单位，`TimeUnit`类型。

- **`threadFactory`**：线程工厂，`ThreadFactory`类型的变量，用来创建新线程。默认使用`Executors.defaultThreadFactory()`。使用默认的`ThreadFactory`创建线程时，会设置：线程名称，`NORM_PRIORITY`运行优先级，非守护线程。

- **`handler`**：线程池饱和策略，`RejectedExecutionHandler`类型。如果阻塞队列满了并且没有空闲的线程，这时如果继续提交任务，就需要采取一种策略处理该任务。线程池提供了4种策略：
    - `AbortPolicy`：直接抛出异常，这是默认策略；
    - `CallerRunsPolicy`：用调用者所在的线程来执行任务；
    - `DiscardOldestPolicy`：丢弃阻塞队列中靠最前的任务，并执行当前任务；
    - `DiscardPolicy`：直接丢弃任务；

## `execute()`方法

线程池的`execute()`方法用来提交任务。在执行`execute()`方法时如果线程池状态为`RUNNING`时，执行过程如下：

1. 如果`workerCount < corePoolSize`，则创建并启动一个线程来执行新提交的任务；

2. 如果`workerCount >= corePoolSize`，且线程池内的阻塞队列未满，则将新任务添加到该阻塞队列中；

3. 如果`workerCount >= corePoolSize && workerCount < maximumPoolSize`，且线程池内的阻塞队列已满，则创建并启动一个线程来执行新提交的任务；

4. 如果`workerCount >= maximumPoolSize`，并且线程池内的阻塞队列已满, 则根据拒绝策略来处理该任务, 默认处理方式是直接抛出异常。

执行流程图如下：

![Concurrency-ThreadPool-3][Concurrency-ThreadPool-3]

> 图：线程池`execute()`方法流程图

```java
public void execute(Runnable command) {
    /* 如果任务为 null，抛出空指针异常 */
    if (command == null) {
        throw new NullPointerException();
    }
    /* clt 记录着 runState 与 workerCount */
    int c = ctl.get();
    /**
     * workerCountOf 方法：取出低29位的值，表示当前活动的线程数；
     * 如果当前活动线程数小于 corePoolSize，
     * 则新建一个线程放入线程池中，
     * 并把任务添加到该线程中。
     */
    if (workerCountOf(c) < corePoolSize) {
        /**
         * 调用 addWorker() 添加任务
         * 第2个参数表示限制添加线程数量的判断方式
         * true：根据 corePoolSize 判断；
         * false：根据 maximumPoolSize 判断
         */
        if (addWorker(command, true)) {
            return;
        }
        /* 任务添加失败：需重新获取 ctl 值 */
        c = ctl.get();
    }
    /**
     * 当前线程池处于运行状态
     * 并且任务添加到队列成功
     */
    if (isRunning(c) && workQueue.offer(command)) {
        /* 重新获取 ctl 值 */
        int recheck = ctl.get();
        /**
         * 再次判断线程池状态，
         * 如果不处于运行状态，
         * 由于之前已经把任务添加到 workQueue 中了，这时需要移除任务，
         * 再使用拒绝策略对该任务进行处理
         */
        if (!isRunning(recheck) && remove(command)) {
            reject(command);
        }
        /**
         * 获取线程池中的有效线程数：如果数量是0，执行 addWorker() 方法
         */
        else if (workerCountOf(recheck) == 0) {
            /**
             * 传入参数：
             * 第1个参数：null，表示在线程池中创建一个线程，但不启动。
             * 第2个参数：false，将线程池线程数量上限设置为 maximumPoolSize，
             *                   添加线程时根据 maximumPoolSize 判断。
             * 如果判断 workerCount 大于0，则直接返回，
             * 在 workQueue 中新增的任务在将来的某个时刻会被执行。
             */
            addWorker(null, false);
        }
    }
    /**
     * 如果执行到这里，有两种情况：
     * 1. 线程池不处于 RUNNING 状态。
     * 2. 线程池处于 RUNNING 状态，
     *    但 workerCount >= corePoolSize 且 workQueue 满。
     * 这时，再次调用 addWorker() 方法，
     * 但第2个参数传入为false，将线程池线程数量上限设为 maximumPoolSize，
     * 失败则调用拒绝策略
     */
    else if (!addWorker(command, false)) {
        reject(command);
    }
}
```
> 代码清单：线程池`execute()`源码

## `addWorker()`方法

线程池`addWorker()`私有方法的主要任务是：在线程池中创建一个新的线程并执行。

- **`firstTask`**：指定新增线程执行的第一个任务。

- **`core`**：`true`表示在新增线程时判断当前活动线程数是否少于`corePoolSize`；`false`表示新增线程前需要判断当前活动线程数是否少于`maximumPoolSize`。

```java
private boolean addWorker(Runnable firstTask, boolean core) {
    /* 跳转标号 */
    retry:
    /* 外层死循环 */
    for (;;) {
        /* 获取运行状态 */
        int c = ctl.get();
        int rs = runStateOf(c);
        /**
         * 判断线程池状态
         * rs >= SHUTDOWN：线程池不再接收新任务
         * 接着判断以下3个条件，只要有1个条件不满足，返回 false
         * 1. rs == SHUTDOWN
         * 2. firstTask 为空
         * 3. workQueue 不为空
         */
        if (rs >= SHUTDOWN &&
            !(rs == SHUTDOWN &&
              firstTask == null &&
              !workQueue.isEmpty())) {
            return false;
        }
        /* 内层死循环 */
        for (;;) {
            /* 获取线程数 */
            int wc = workerCountOf(c);
            /**
             * 线程数大于等于限定容量: 返回 false
             * 限定容量根据参数 core 布尔值取得
             */
            if (wc >= CAPACITY ||
                wc >= (core ? corePoolSize : maximumPoolSize)) {
                return false;
            }
            /**
             * CAS 尝试增加 workerCount
             * 成功则跳出最外层死循环
             */
            if (compareAndIncrementWorkerCount(c)) {
                break retry;
            }
            /* 重读 ctl */
            c = ctl.get();
            /**
             * 侦测到线程池当前运行状态不为rs，说明线程池状态已被改变，
             * 回到外层死循环继续执行
             */
            if (runStateOf(c) != rs) {
                continue retry;
            }
            /* CAS 操作失败，重试内层死循环 */
        }
    }
    /* 临时变量 */
    boolean workerStarted = false;
    boolean workerAdded = false;
    Worker w = null;
    try {
        /* 封装 firstTask 为 Worker 对象 */
        w = new Worker(firstTask);
        /* 每个 Worker 对象都会创建一个线程 */
        final Thread t = w.thread;
        /* Worker 对象线程创建成功 */
        if (t != null) {
            /* 取锁 */
            final ReentrantLock mainLock = this.mainLock;
            /* 加锁 */
            mainLock.lock();
            try {
                /* 重读线程池状态 */
                int rs = runStateOf(ctl.get());
                /* 重读线程池状态 */
                if (rs < SHUTDOWN ||
                    (rs == SHUTDOWN && firstTask == null)) {
                    /* 前置检查线程是否启动：线程已启动则抛出异常 */
                    if (t.isAlive()) {
                        throw new IllegalThreadStateException();
                    }
                    /* 添加任务，workers 类似 HashSet */
                    workers.add(w);
                    int s = workers.size();
                    /**
                     * largestPoolSize 记录
                     * 线程池中出现过的最大线程数量
                     * 如果当前线程池中线程数量更大，则更新该值
                     */
                    if (s > largestPoolSize) {
                        largestPoolSize = s;
                    }
                    /* 任务成功添加 */
                    workerAdded = true;
                }
            } finally {
                /* 解锁 */
                mainLock.unlock();
            }
            /* 任务已添加 */
            if (workerAdded) {
                /* 启动线程 */
                t.start();
                /* 更改任务状态 */
                workerStarted = true;
            }
        }
    } finally {
        /* 任务未启动成功：添加任务失败 */
        if (!workerStarted) {
            addWorkerFailed(w);
        }
    }
    /* 返回任务启动状态 */
    return workerStarted;
}
```
> 代码清单：线程池`addWorker()`源码

注意一下这里的`t.start()`这个语句，启动时会调用`Worker`类中的`run()`方法，`Worker`本身实现了`Runnable`接口，所以一个`Worker`对象也是一个线程。

## Worker 内部类

...



[Concurrency-ThreadPool-1]: ../../images/Concurrency-ThreadPool-1.jpg

[Concurrency-ThreadPool-2]: ../../images/Concurrency-ThreadPool-2.png

[Concurrency-ThreadPool-3]: ../../images/Concurrency-ThreadPool-3.png

<!-- EOF -->
