# 队列（Queue）数据结构

队列（英语：Queue）是计算机科学中的一种抽象数据类型，允许在有序线性数据集合的一端（队尾）进行添加数据（入队，enqueue）操作，在另一端（队头）进行移除数据（出队，dequeue）操作，按照先进先出（FIFO, First In First Out）的原理运作。以存储结构区分，队列结构常使用「数组」或「链表」实现。

![QueueStructure][QueueStructure]

> 图：队列（Queue）结构示意图

# 队列操作接口（Interface）

队列数据结构通常具有如下几种操作接口。

| 接口        | 含义                             |
| ----------- | -------------------------------- |
| `enqueue()` | 将元素添加到队尾（入队）。       |
| `dequeue()` | 将队头元素从队列中移除（出队）。 |
| `peek()`    | 查看队头元素。                   |
| `size()`    | 获取队列中存放元素数量。         |
| `isEmpty()` | 判断队列是否为空。               |
| `clear()`   | 清空队列。                       |

> 表：队列操作接口表

# 队列异常（Exception）

队列数据结构通常指定两项异常。

- 队列满时数据入队：`QueueIsFullException`

- 队列空时数据出队：`QueueIsEmptyException`

```java
public class QueueIsFullException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public QueueIsFullException() {
        super();
    }
    public QueueIsFullException(String message) {
        super(message);
    }
    public QueueIsFullException(String message, Throwable cause) {
        super(message, cause);
    }
}
/* EOF */
```
> 代码清单：`QueueIsFullException`异常 - Java 代码

```java
public class QueueIsEmptyException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public QueueIsEmptyException() {
        super();
    }
    public QueueIsEmptyException(String message) {
        super(message);
    }
    public QueueIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
/* EOF */
```
> 代码清单：`QueueIsEmptyException`异常 - Java 代码

[QueueStructure]: ../../images/DataStructuresAndAlgorithms-QueueOverview-1-QueueStructure.png

<!-- EOF -->
