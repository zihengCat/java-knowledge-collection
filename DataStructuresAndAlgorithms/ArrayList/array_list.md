# 动态数组（ArrayList）数据结构概览

在计算机科学中，数组（英语：Array）是由相同类型的元素（Element）集合所组成的数据结构，分配一块连续的内存来存储。利用元素的索引（Index）可以快速计算出该元素对应的存储地址。

数组设计之初依赖系统内存分配，必须在使用前预先申请空间。这使得数组有以下特性：

- 快速随机访问。
- 申请空间后大小固定，不能再改变。
- 内存空间连续。
- 程序一般不对数组操作做越界判断，有潜在的数组越界的风险。

简单数组贴近计算机硬件，对于现代程序设计而言不够灵活，动态数组的出现有效解决了上述问题。

**动态数组（Dynamic Array）是可变长度、动态扩缩容、硬件无关的更高级数据结构，兼具数组随机访问（Random Access）的优势特性**。现代编程语言一般都有动态数组具体实现（例如：C++ 的 Vector，Java 的 ArrayList），以语言标准库的形式提供给开发者使用。

| 数组操作            | 简单数组 | 动态数组 |
| :----------------- | :------ | :----- |
| **索引**            | `O(1)` | `O(1)` |
| **头部插入/删除元素** | `\` | `O(n)` |
| **尾部插入/删除元素** | `\` | `O(1)` |
| **中部插入/删除元素** | `\` | `O(n)` |
| **平均空间占用**     | `O(n)` | `O(2n)` |

> 表：数组结构操作性能比较表

# 动态数组（ArrayList）数据结构 Java 实现

```java
/* 动态数组实现类 */
import ArrayList;
/* 随机数标准库 */
import java.util.Random;
/**
 * 测试类
 */
public class ArrayListTest {
    public static void main(String[] args) {
        testArrayList();
    }
    private static void testArrayList() {
        Random random = new Random();
        ArrayList<Integer> aList = new ArrayList<>();

        assert aList.size() == 0;
        assert aList.getCapacity() == 8;
        assert aList.toString().equals("[]");

        int capacity = 128;
        for (int i = 0; i < capacity; i++) {
            aList.addLast(random.nextInt(100));
        }
        aList.addLast(random.nextInt(100));
        assert aList.size() == capacity + 1;
        assert aList.getCapacity() == capacity * 2;
        while (!aList.isEmpty()) {
            aList.removeFirst();
        }
        assert aList.size() == 0;
        assert aList.getCapacity() == 0;

        aList.clear();

        aList.addFirst(0);
        aList.addLast(100);
        aList.add(1, 1);
        assert aList.get(1) == 1;
        aList.remove(1);
        aList.remove(0);
        assert aList.size() == 1 && aList.get(0) == 100;
        aList.set(0, 1024);
        assert aList.get(0) == 1024;

        System.out.println("Test result: OK");
    }
}
/* EOF */
```

<!-- EOF -->