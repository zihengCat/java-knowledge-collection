# Java `LinkedHashMap` 源码剖析

LinkedHashMap 是一个**有序哈希表**，用于存放关联数据，允许单一键为`null`，任意数量值为`null`。LinkedHashMap 并不是线程安全的集合容器。

# LinkedHashMap 继承体系

LinkedHashMap 继承了 HashMap，实现`Map`接口，仅重写了几个方法实现，可以保持元素按「插入顺序」或「访问顺序」有序排列。

LinkedHashMap 支持两种排序顺序：

- 插入顺序：先添加的元素在前，后添加的元素在后，修改操作不影响元素排列顺序。

- 访问顺序：对一个键值对执行操作后，该键值对会被移动到尾部。

![Collections-LinkedHashMap-1-Hierachy][Collections-LinkedHashMap-1-Hierachy]

> 图：LinkedHashMap 继承体系图

# LinkedHashMap 数据结构

LinkedHashMap 继承自 HashMap，也具有 HashMap 中所有的数据结构。另外，LinkedHashMap 内部还使用了一个双向链表维护键值对的顺序，每个键值对既位于哈希表中，也位于双向链表中。

在具体实现上，LinkedHashMap 仅重写了几个方法，以满足其有序排列的需求。

![Collections-LinkedHashMap-2-DataStructure][Collections-LinkedHashMap-2-DataStructure]

> 图：LinkedHashMap 数据结构图

# LinkedHashMap 基本用法

以下代码展示了 LinkedHashMap 基本用法。

```java
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Iterator;
public class LinkedHashMapTest {
    public static void main(String[] args) {
        Map<String, String> map = new LinkedHashMap<String, String>();
        map.put("1", "a");
        map.put("2", "b");
        map.put("3", "c");
        map.put("4", "d");
        for (Iterator<Map.Entry<String, String>> iterator =
                map.entrySet().iterator();
            iterator.hasNext(); /* Nope */ ) {
            /* 有序输出键值对 */
            System.out.println(iterator.next());
        }
    }
}
/* EOF */
```
> 代码清单：LinkedHashMap 基本用法

# LinkedHashMap 源码剖析

从 JDK 源码角度深入分析 LinkedHashMap 实现。

## LinkedHashMap 数据字段













[Collections-LinkedHashMap-1-Hierachy]: ../../images/Collections-LinkedHashMap-1-Hierachy.png

[Collections-LinkedHashMap-2-DataStructure]: ../../images/Collections-LinkedHashMap-2-DataStructure.png

<!-- EOF -->
