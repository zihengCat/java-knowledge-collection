# Java `ArrayDeque` 简介

ArrayDeque 是以动态数组实现的「双端队列」线性数据结构容器，不允许存放`null`值，在 JDK 1.6 版本被加入到 Java 集合框架中，是一个比较新的 Java 集合框架实现类。ArrayDeque 并不是线程安全的集合容器。

# ArrayDeque 继承体系

从 ArrayDeque 继承关系图中可以看出，ArrayDeque 继承自抽象父类`AbstractCollection`，实现了`Deque`接口。

![Collections-ArrayDeque-1-Hierarchy][Collections-ArrayDeque-1-Hierarchy]

> 图：ArrayDeque 继承体系图

# ArrayDeque 数据结构

ArrayDeque 底层数据结构采用「对象数组」，数组可动态扩容。

![Collections-ArrayDeque-2-DataStructure][Collections-ArrayDeque-2-DataStructure]

> 图：ArrayDeque 数据结构

[Collections-ArrayDeque-1-Hierarchy]: ../../images/Collections-ArrayDeque-1-Hierarchy.png

[Collections-ArrayDeque-2-DataStructure]: ../../images/Collections-ArrayDeque-2-DataStructure.png

<!-- EOF -->
