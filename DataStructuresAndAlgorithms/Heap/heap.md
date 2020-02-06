# 堆（Heap）数据结构概览

堆（英语：Heap）是计算机科学中一种特殊的树型数据结构。

堆结构满足以下特性：

- 堆中任意节点的值总是不大于或不小于其父节点的值，即：给定堆中任意节点 P 和 C，若 P 为 C 的父节点，那么 P 的值小于等于（或大于等于）C 的值。
- 若父节点的值恒小于等于子节点的值，则称此堆为***最小堆（Min-heap）***；若父节点的值恒大于等于子节点的值，则称此堆为***最大堆（Max-heap）***。
- 堆中最顶端的节点称为根节点（root node），其值为堆中所有元素的最大值（或最小值），根节点本身没有父节点（parent node）。
- 二叉堆是一棵***完全二叉树***。

# 二叉堆（Binary Heap）顺序存储结构

我们可以使用数组（Array）作为二叉堆的底层存储结构。

- 按照二叉树层次遍历（Binary Tree Level Order Traversal）顺序排列数组元素。
- 数组索引`0`位置留空。
- **对于数组索引位置为`i`的任意节点，其父节点的索引位置为`i/2`（整数除法，舍去余数），其左子节点的索引位置为`2*i`，其右子节点的索引位置为`2*i+1`。**
```plain
arr = [-1, 62, 41, 30, 28, 16, 22, 13, 19, 17, 15, ]
i   =   0,  1,  2,  3,  4,  5,  6,  7,  8,  9, 10
parentNode(i) = i / 2
leftNode(i) = 2 * i
rightNode(i) = 2 * i + 1
```
- 若数组索引`0`位置不选择留空而是正常排放元素，则对于数组索引位置为`i`的任意节点，其父节点的索引位置为`(i-1)/2`（整数除法，舍去余数），其左子节点的索引位置为`2*i+1`，其右子节点的索引位置为`2*i+2`。**

# 二叉堆（Binary Heap）链式存储结构

...

# 二叉堆（Binary Heap）数据结构 Java 实现

使用 Java 实现二叉堆（Binary Heap）数据结构。

## 二叉堆测试代码（TDD）

秉持测试驱动开发（TDD）的原则，我们先编写二叉堆测试代码。测试代码执行时，使用`java -ea`开启断言测试。

测试流程：

1. 建立最大堆
2. 向堆中添加随机元素
3. 顺序移除堆中元素
4. 检查元素出堆顺序
5. 测试`heapify()`

```java
/* 导入最大堆实现类 */
import MaxHeap;
/* 导入随机数标准库 */
import java.util.Random;
/* 测试类 */
public class HeapTest {
    public static void main(String[] args) {
        testHeap();
    }
    public static void testHeap() {
        int capacity = 1000000;
        Random random = new Random();
        MaxHeap<Integer> maxHeap = new MaxHeap<Integer>(capacity);
        for (int i = 0; i < capacity; ++i) {
            maxHeap.add(
                random.nextInt(Integer.MAX_VALUE)
            );
        }
        assert maxHeap.size() == capacity;
        int[] arr = new int[capacity];
        for (int i = 0; i < capacity; ++i) {
            arr[i] = maxHeap.extract();
        }
        for (int i = 1; i < capacity; ++i) {
            assert arr[i] <= arr[i - 1];
        }
        assert maxHeap.size() == 0;
        maxHeap = new MaxHeap<Integer>(new Integer[]{1, 1, 2, 3, 4, 5, });
        assert maxHeap.toString().equals("[5, 4, 2, 3, 1, 1, ]");
        System.out.println("Test result: OK");
    }
}
/* EOF */
```
> 代码清单：最大堆测试代码

## 二叉堆操作接口（Interface）

堆的常用操作接口如下表所示：

| 接口         | 说明             |
| :---------- | :--------------- |
| `add()`     | 向堆中添加一个新元素 |
| `peek()`    | 查看堆顶元素 |
| `extract()` | 移除堆顶元素 |
| `size()`    | 获取堆存放元素数量 |
| `isEmpty()` | 判断堆是否为空 |
| `clear()`   | 清空堆中所有元素 |

> 表：堆操作接口表

按照接口表将堆常用操作转译为 Java 接口，代码如下：

```java
public interface HeapOperation<E> {
    void add(E e);
    E peek();
    E extract();
    int size();
    boolean isEmpty();
    void clear();
}
```
> 代码清单：堆操作 Java 接口

## 二叉堆数据字段（Fieds）

为了实现简单，使用 ***定长数组（Array）*** 作为二叉堆的底层存储结构；使用 ***范型（Generic Type）*** 保障堆内存放元素的灵活性；另外注意，堆结构要求堆内存放元素具备 ***可比较性（Comparability）*** ，反映到代码层面，即：范型需要实现 Java `Comparable<T>` 比较接口。

```java
import java.lang.Comparable;
import java.lang.reflect.Array;
import HeapOperation;
public class MaxHeap<E extends Comparable<E>>
implements HeapOperation<E> {
    /* 默认堆容量 */
    private static final int DEFAULT_CAPACITY = 8;
    /* 底层数组 */
    private E[] elementData;
    /* 数组堆容量 */
    private int capacity;
    /* 堆内存放元素数量 */
    private int size;
}
/* EOF */
```
> 代码清单：二叉堆数据字段

## 二叉堆构造函数（Constructor）

二叉堆构造函数中，接收一枚堆容量配额整型参数`initialCapacity`，以该配额初始化定长数组。注意初始化数组时不能直接创建范型对象数组，使用**反射 + 强制类型转换**间接创建。

```java
/**
 * 默认无参构造函数。
 *
 * @param void
 */
public MaxHeap() {
    this(DEFAULT_CAPACITY);
}
/**
 * 默认无参构造函数。
 *
 * @param initialCapacity
 */
@SuppressWarnings("unchecked")
public MaxHeap(int initialCapacity) {
    if (initialCapacity <= 0) {
        throw new IllegalArgumentException(
            "[ERROR]: Initial capacity must be greater than zero."
        );
    }
    this.elementData = (E[])Array.newInstance(
        Comparable.class,
        initialCapacity
    );
    this.capacity = initialCapacity;
    this.size = 0;
}
```
> 代码清单：二叉堆构造函数

## 二叉堆辅助函数（Supported Functions）

我们选择不放空数组索引`0`位置，而是正常排放元素，以此为二叉堆添加几个辅助功能函数。

```java
/**
 * 数组越界访问检查。
 *
 * @param index
 * @return void
 */
private void checkIndex(int index) {
    if (index < 0 || index >= this.capacity) {
        throw new IndexOutOfBoundsException();
    }
}
/**
 * 获取传入节点的父节点索引（根节点无父节点）。
 *
 * @param index
 * @return int
 */
private int getParentNode(int index) {
    checkIndex(index);
    if (index == 0) {
        throw new RuntimeException(
            "[ERROR]: root node does not have parentNode."
        );
    }
    return (index - 1) / 2;
}
/**
 * 获取传入节点的左子节点索引。
 *
 * @param index
 * @return int
 */
private int getLeftNode(int index) {
    checkIndex(index);
    return index * 2 + 1;
}
/**
 * 获取传入节点的右子节点索引。
 *
 * @param index
 * @return int
 */
private int getRightNode(int index) {
    checkIndex(index);
    return index * 2 + 2;
}
/**
 * 交换节点元素。
 *
 * @param i
 * @param j
 * @return void
 */
private void swapNode(int i, int j) {
    checkIndex(i);
    checkIndex(j);
    E element = elementData[i];
    elementData[i] = elementData[j];
    elementData[j] = element;
}
/**
 * 释放节点。
 *
 * @param index
 * @return void
 */
private void freeNode(int index) {
    checkIndex(index);
    elementData[index] = null;
}
```
> 代码清单：二叉堆辅助函数

<!-- EOF -->