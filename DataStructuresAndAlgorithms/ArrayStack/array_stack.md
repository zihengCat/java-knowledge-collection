# 数组实现栈（Java）

栈（Stack）是一种后进先出（LIFO）的线性数据结构，具体实现上有：顺序栈、链式栈。本文讲解在 Java 中使用数组实现顺序栈。

## 实现思路

数组实现栈结构，底层数据结构选用数组，栈还需要一枚栈顶指针，指示下一个元素存放的索引位置，再引入一个`size`变量指示栈中已存放元素数量。

- 入栈：将元素放入栈顶位置，栈顶指针后移。

- 出栈：栈顶指针前移，返回栈顶元素。

## 数组栈数据字段

理解整体实现思路后，着手编写 Java 代码：引入栈操作接口与栈异常，使用范型类来实现栈接口，这样栈可以存放任何类型的数据；设置底层数组、栈顶指针、栈容量变量。

```java
/* 导入栈接口 */
import Stack;
/* 导入栈异常 */
import StackIsEmptyException;
import StackIsFullException
/* 数组栈 */
public class ArrayStack<E> implements Stack<E> {
    /* 栈默认容量大小 */
    private static final int DEFAULT_CAPACITY = 16;
    /* 存放实际元素的数组 */
    private E[] elementData;
    /* 栈顶指针（指示下一个元素在数组中的存放索引下标） */
    private int top;
    /* 栈已存放元素数量 */
    private int size;
}
/* EOF */
```
> 代码清单：数组栈数据字段 - Java 代码

# 拓展延伸 - 数组动态扩容




<!-- EOF -->
