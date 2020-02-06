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

# 二叉堆测试代码（TDD）

秉持测试驱动开发（TDD）的原则，我们先编写二叉堆测试代码。

测试流程：

1. 建立最大堆
2. 向堆中添加随机元素
3. 顺序移除堆中元素
4. 检查元素出堆顺序

测试代码执行时，使用`java -ea`开启断言测试。

```java
/* 导入最大堆实现类 */
import MaxHeap;
/* 导入随机数标准库 */
import java.util.Random;
/* 测试类 */
public class TestHeap {
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

<!-- EOF -->