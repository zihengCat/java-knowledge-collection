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
| `extract()` | 移除堆顶元素 |
| `peek()`    | 查看堆顶元素 |
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

二叉堆构造函数中，接收一枚堆容量配额`initialCapacity`整型参数，以该配额初始化定长数组。注意初始化数组时不能直接创建范型对象数组，使用**反射 + 强制类型转换**间接创建。

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

## 二叉堆添加新元素（Sift Up）

向二叉堆中添加新元素`add()`具体流程如下：

1. 向二叉堆树末端（对应数组尾索引）添加一个新节点；
2. 执行`siftUp()`操作使新节点「上浮」：比较「新节点」与「其父节点」的值，依据堆类型（最大堆/最小堆）进行节点交换，迭代上溯直至满足堆的定义或抵达根节点。

```java
/**
 * 向堆中添加一个新元素。
 *
 * @param element
 * @return void
 */
@Override
public void add(E element) {
    elementData[size] = element;
    size++;
    siftUpNode(size - 1);
}
/**
 * 堆节点上浮（sift up）。
 *
 * @param index
 * @return void
 */
private void siftUpNode(int index) {
    checkIndex(index);
    while (index > 0 && elementData[getParentNode(index)]
        .compareTo(elementData[index]) < 0) {
        swapNode(index, getParentNode[index]);
        index = getParentNode(index);
    }
}
```
> 代码清单：二叉堆添加新元素（Sift Up）

## 二叉堆移除堆顶元素（Sift Down）

移除二叉堆堆顶元素`extract()`具体流程如下：

1. 暂存堆顶根节点元素。
2. 交换二叉堆树堆顶根节点与树末端（对应数组尾索引）节点
3. 移除二叉堆树末端节点
4. 执行`siftDown()`操作使根节点「下沉」：比较「根节点」与「其左右子节点」的值，依据堆类型（最大堆/最小堆）与左右子节点之一进行交换，迭代下降直至满足堆的定义或抵达叶子节点。

```java
/**
 * 查看堆顶元素。
 *
 * @param void
 * @return E
 */
@Override
public E peek() {
    if (size == 0) {
        return null;
    }
    return elementData[0];
}
/**
 * 移除堆顶元素。
 *
 * @param void
 * @return E
 */
@Override
public E extract() {
    E element = peek();
    swapNode(0, size - 1);
    freeNode(size - 1);
    size--;
    siftDownNode(0);
    return element;
}
/**
 * 堆节点下沉（sift down）。
 *
 * @param void
 * @return E
 */
private void siftDownNode(int index) {
    checkIndex(index);
    while (getLeftNode(index) < size) {
        int leftNodeIndex = getLeftNode(index);
        int rightNodeIndex = getRightNode(index)
        int maxNodeIndex = leftNodeIndex;
        if (rightNodeIndex < size &&
            elementData[rightNodeIndex].compareTo(
            elementData[leftNodeIndex]) > 0) {
            maxNodeIndex = rightNodeIndex;
        }
        if (elementData[index].compareTo(
            elementData[maxNodeIndex]) >= 0) {
            break;
        } else {
            swapNode(index, maxNodeIndex);
            index = maxNodeIndex;
        }
    }
}
```
> 代码清单：二叉堆移除堆顶元素（Sift Down）

## 二叉堆堆化函数（Heapify）

二叉堆堆化`heapify()`操作用于将一枚数组`arr`组织成为一个二叉堆，运用`siftUp()`与`siftDown()`操作即可快速完成，具体实现如下：

```java
/**
 * 以数组构造堆。
 *
 * @param arr
 * @return maxHeap
 */
public static <E extends Comparable<E>> MaxHeap<E> heapify(E[] arr) {
    if (arr == null || arr.length == 0) {
        return null;
    }
    int initialCapacity = arr.length;
    MaxHeap<E> maxHeap = new MaxHeap<E>(initialCapacity);
    for (int i = 0; i < arr.length; ++i) {
        maxHeap.add(arr[i]);
    }
    return maxHeap;
}
/**
 * 数组构造函数。
 *
 * @param arr
 */
@SuppressWarnings("unchecked")
public MaxHeap(E[] arr) {
    if (arr == null || arr.length == 0) {
        throw new IllegalArgumentException();
    }
    int initialCapacity = arr.length;
    this.elementData = (E[])Array.newInstance(
        Comparable.class,
        initialCapacity
    );
    this.capacity = initialCapacity;
    this.size = initialCapacity;
    for (int i = 0; i < initialCapacity; ++i) {
        elementData[i] = arr[i];
    }
    heapifyInternal();
}
/**
 * 堆化（Heapify）操作。
 *
 * @param void
 * @return void
 */
private void heapifyInternal() {
    for (int i = getParentNode(size - 1); i >= 0; i--) {
        siftDownNode(i);
    }
}
```
> 代码清单：二叉堆`heapify()`操作

## 二叉堆其他函数（Others）

二叉堆的其他函数实现起来都比较简单明了，不多做赘述，实现如下：

```java
/**
 * 获取二叉堆存放元素数量。
 *
 * @param void
 * @return int
 */
@Override
public int size() {
    return size;
}
/**
 * 判断二叉堆是否为空。
 *
 * @param void
 * @return boolean
 */
@Override
public boolean isEmpty() {
    return size == 0;
}
/**
 * 清空二叉堆。
 *
 * @param void
 * @return void
 */
@Override
public void clear() {
    for (int i = 0; i < capacity; ++i) {
        freeNode(i);
    }
    size = 0;
}
/**
 * 二叉堆字符串化。
 *
 * @param void
 * @return String
 */
@Override
public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append('[');
    for (int i = 0; i < capacity; ++i) {
        stringBuilder.append(
            elementData[i] == null ? "null" : elementData[i].toString()
        );
        stringBuilder.append(", ");
    }
    stringBuilder.append(']');
    return stringBuilder.toString();
}
```
> 代码清单：二叉堆其他函数（Others）

<!-- EOF -->