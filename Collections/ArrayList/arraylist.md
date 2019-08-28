# ArrayList 简介

ArrayList 是 Java 实现的动态数组，用于存放顺序对象，是最常用的 Java 集合类之一。ArrayList 并不是线程安全的集合容器。

# ArrayList 继承关系

从 ArrayList 继承关系图中可以看出，`ArrayList`继承自抽象父类`AbstractList`，实现了`List`接口。另外，ArrayList 还支持快速随机访问`RandomAccess`。

![Collections-ArrayList-1-Hierarchy][Collections-ArrayList-1-Hierarchy]

> 图：ArrayList 继承关系图

# ArrayList 数据结构

ArrayList 底层数据结构比较简单：对象数组。数组具有的特性（固定类型、固定长度、随机访问等），ArrayList 也都具备，另外 ArrayList 也扩展了数组，可以动态伸缩。

![Collections-ArrayList-2-DataStructure][Collections-ArrayList-2-DataStructure]

> 图：ArrayList 数据结构

# ArrayList 源码剖析







[Collections-ArrayList-1-Hierarchy]: ../../images/Collections-ArrayList-1-Hierarchy.png

<!-- EOF -->
