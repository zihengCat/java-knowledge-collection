# Java 内存模型（JMM）

# 基本概念

- 程序：可执行二进制代码序列（静态概念）

- 进程：程序由磁盘载入到内存被 CPU 执行的完整过程，系统资源分配的最小单位（动态概念）

- 线程：CPU 调度（时间片分配）的最小单位

# Java 内存模型（JMM）概览

Java 内存模型（JMM）是一种内存规范，对计算机硬件内存架构的抽象模型。

- 主内存

- 工作内存

- 交互方式

![JVM-JavaMemoryModel-1-JavaMemoryModelOverview][JVM-JavaMemoryModel-1-JavaMemoryModelOverview]

> 图：Java 内存模型（JMM）概览

# Java 内存模型（JMM）对并发编程的意义

并发编程的特性：

- 原子性：操作不可分割。

- 可见性：线程只能操作自己工作内存中的数据。

- 有序性：不影响执行结果的情况下进行重排序（编译重排序、指令重排序）提高程序运行效率。

# Happens-before 原则

- 程序次序原则

- 加锁原则：后一次加锁必须等待前一次解锁

- volatile 原则：JSR-133

- 传递性原则：{A -> B, B -> C} => {A -> C}




[JVM-JavaMemoryModel-1-JavaMemoryModelOverview]: ../../images/JVM-JavaMemoryModel-1-JavaMemoryModelOverview.png

<!-- EOF -->