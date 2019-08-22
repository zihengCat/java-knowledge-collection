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


[Concurrency-ThreadPool-1]: ../../images/Concurrency-ThreadPool-1.jpg

<!-- EOF -->
