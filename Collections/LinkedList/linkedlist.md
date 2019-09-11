# Java `LinkedList` 简介

LinkedList 是 JDK 实现的「双向链表」集合容器，用于存储线性数据，是常用的 Java 集合实现类之一。LinkedList 并不是线程安全的集合容器。

# LinkedList 继承体系

如图所示，LinkedList 继承自抽象父类`AbstractSequentialList`，可顺序访问集合元素，实现了`List`，`Deque`接口。因此，LinkedList 还可以被用作「栈」与「队列」。

![Collections-LinkedList-1-Hierarchy][Collections-LinkedList-1-Hierarchy]

> 图：LinkedList 继承体系图

# LinkedList 数据结构

LinkedList 的底层数据结构为「双向链表」，封装元素为链表节点，通过前趋后继节点指针串联数据，使用链式存储，内存不连续。

LinkedList 使用链式存储结构，可以自由扩缩容量，插入删除数据效率高，查找数据效率较低。

![Collections-LinkedList-2-DataStructure][Collections-LinkedList-2-DataStructure]

> 图：LinkedList 数据结构图

# LinkedList 基本用法

以下代码展示了 LinkedList 基本使用方式。

```java
import java.util.List;
import java.util.LinkedList;
public class LinkedListTest {
    public static void main(String[] args) {
        List<Integer> linkedList = new LinkedList<Integer>();
        for (int i = 0; i < 10; ++i) {
            linkedList.add(i);
        }
        System.out.println(linkedList.get(1));
    }
}
/* EOF */
```
> 代码清单：LinkedList 基本用法

# LinkedList 源码剖析

...


# LinkedList 总结

...


[Collections-LinkedList-1-Hierarchy]: ../../images/Collections-LinkedList-1-Hierarchy.png

[Collections-LinkedList-2-DataStructure]: ../../images/Collections-LinkedList-2-DataStructure.png

<!-- EOF -->
