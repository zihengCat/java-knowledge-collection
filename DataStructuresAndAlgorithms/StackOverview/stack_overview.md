# 栈（Stack）数据结构

栈（英语：Stack）是计算机科学中的一种抽象数据类型，只允许在有序线性数据集合的一端（栈顶）进行加入数据（Push）和移除数据（Pop）操作，因而按照后进先出（LIFO, Last In First Out）的原理运作。栈数据结构常用数组或链表来实现，称为：顺序栈与链式栈。

![StackStructure][StackStructure]

> 图：栈（Stack）结构示意图

# 栈操作接口（Interface）

栈数据结构通常具有如下几种操作接口。

| 接口        | 含义                       |
| ----------- | -------------------------- |
| `push()`    | 将元素压入栈中（入栈）。   |
| `pop()`     | 将栈顶元素弹出栈（出栈）。 |
| `top()`     | 查看栈顶元素。             |
| `size()`    | 获取栈中存放元素数量。     |
| `isEmpty()` | 判断栈是否为空。           |

> 表：栈操作接口表

使用 Java 接口描述栈操作。

```java
public interface Stack<E> {
    public void push(E e);
    public E pop();
    public E top();
    public int size();
    public boolean isEmpty();
}
/* EOF */
```
> 代码清单：栈操作接口 - Java 代码

# 栈异常（Exception）

栈数据结构通常指定两项异常。

- 栈满时数据压栈：`StackIsFullException`

- 栈空时数据弹栈：`StackIsEmptyException`

```java
public class StackIsFullException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public StackIsFullException() {
        super();
    }
    public StackIsFullException(String message) {
        super(message);
    }
    public StackIsFullException(String message, Throwable cause) {
        super(message, cause);
    }
}
/* EOF */
```
> 代码清单：`StackIsFullException`异常 - Java 代码

```java
public class StackIsEmptyException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public StackIsEmptyException() {
        super();
    }
    public StackIsEmptyException(String message) {
        super(message);
    }
    public StackIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
/* EOF */
```
> 代码清单：`StackIsEmptyException`异常 - Java 代码

[StackStructure]: ../../images/DataStructuresAndAlgorithms-StackOverview-1-StackStructure.png

<!-- EOF -->
