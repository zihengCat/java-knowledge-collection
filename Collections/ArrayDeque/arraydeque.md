# Java `ArrayDeque` 简介

ArrayDeque 是以动态数组实现的「双端队列」线性数据结构容器，不允许存放`null`值，在 JDK 1.6 版本被加入到 Java 集合框架中，是一个比较新的 Java 集合框架实现类。ArrayDeque 并不是线程安全的集合容器。

# ArrayDeque 继承体系

从 ArrayDeque 继承关系图中可以看出，ArrayDeque 继承自抽象父类`AbstractCollection`，实现了`Deque`接口。

![Collections-ArrayDeque-1-Hierarchy][Collections-ArrayDeque-1-Hierarchy]

> 图：ArrayDeque 继承体系图

# ArrayDeque 数据结构

ArrayDeque 底层数据结构采用「对象数组」，数组可动态扩容；另有队头指针、队尾指针，指示队头队尾在数组中的索引下标。另外，ArrayDeque 还将对象数组用作一个循环数组，移除元素时不需要移动其他元素，只需调整指针位置即可，大大提高了效率。

![Collections-ArrayDeque-2-DataStructure][Collections-ArrayDeque-2-DataStructure]

> 图：ArrayDeque 数据结构

# ArrayDeque 基本用法

以下代码展示了 ArrayDeque 基本使用方式。

```java
import java.util.Deque;
import java.util.ArrayDeque;
public class ArrayDequeTest {
    public static void main(String[] args) {
        /* 创建 ArrayDeque */
        Deque<Integer> arrayDeque = new ArrayDeque<Integer>();
        /* 向 ArrayDeque 队头加入元素 */
        arrayDeque.offerFirst(1);
        /* 向 ArrayDeque 队尾加入元素 */
        arrayDeque.offerLast(2);
        /* 检查 ArrayDeque 元素数量 */
        System.out.println("ArrayDeque.size(): " + arrayDeque.size());
        /* 取出 ArrayDeque 队头元素 */
        System.out.println(arrayDeque.pollFirst());
        /* 取出 ArrayDeque 队尾元素 */
        System.out.println(arrayDeque.pollLast());
        /* 再次检查 ArrayDeque 元素数量 */
        System.out.println("ArrayDeque.size(): " + arrayDeque.size());
    }
}
/* EOF */
```
> 代码清单：ArrayDeque 基本用法



[Collections-ArrayDeque-1-Hierarchy]: ../../images/Collections-ArrayDeque-1-Hierarchy.png

[Collections-ArrayDeque-2-DataStructure]: ../../images/Collections-ArrayDeque-2-DataStructure.png

<!-- EOF -->
