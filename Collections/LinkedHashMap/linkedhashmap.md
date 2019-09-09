# Java `LinkedHashMap` 源码剖析

LinkedHashMap 是一个**有序哈希表**，用于存放关联数据，允许单一键为`null`，任意数量值为`null`。LinkedHashMap 并不是线程安全的集合容器。

# LinkedHashMap 继承体系

LinkedHashMap 继承了 HashMap，实现`Map`接口，可以保持元素按「插入顺序」或「访问顺序」有序排列。

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

LinkedHashMap 内部节点`Entry<K,V>`继承自`HashMap.Node<K,V>`，在其基础上扩展了前驱后继节点，改造为「双向链表」结构。并添加双向链表头尾节点`head`、`tail`。

```java
public class LinkedHashMap<K,V>
    extends HashMap<K,V>
    implements Map<K,V>
{
    /**
     * LinkedHashMap 节点数据结构，继承 HashMap.Node 。
     */
    static class Entry<K,V> extends HashMap.Node<K,V> {
        Entry<K,V> before, after;
        Entry(int hash, K key, V value, Node<K,V> next) {
            super(hash, key, value, next);
        }
    }
    /**
     * 双向链表头节点（最老）。
     */
    transient LinkedHashMap.Entry<K,V> head;
    /**
     * 双向链表尾节点（最新）。
     */
    transient LinkedHashMap.Entry<K,V> tail;
    /**
     * 元素迭代顺序。
     * false：插入顺序
     * true：访问顺序
     */
    final boolean accessOrder;
}
```
> 代码清单：LinkedHashMap 数据字段

## LinkedHashMap 构造函数

LinkedHashMap 构造函数在 HashMap 基础之上额外添加`accessOrder`标识，用于控制节点顺序，默认为`false`，即按元素「插入顺序」排列。

```java
/**
 * 无参构造函数，使用默认值：容量 16，负载因子 0.75，
 * accessOrder 访问标识默认置为 false 。
 */
public LinkedHashMap() {
	super();
	accessOrder = false;
}
/**
 * 以指定初始容量，负载因子，排序模式构造。
 *
 * @param  initialCapacity 初始容量
 * @param  loadFactor      负载因子
 * @param  accessOrder     排序模式
 * @throws IllegalArgumentException 容量或负载因子为负。
 */
public LinkedHashMap(int initialCapacity,
                     float loadFactor,
                     boolean accessOrder) {
    super(initialCapacity, loadFactor);
    this.accessOrder = accessOrder;
}
/**
 * 以指定初始容量，负载因子，
 * 默认排序模式构造。
 *
 * @param  initialCapacity 初始容量
 * @param  loadFactor      负载因子
 * @throws IllegalArgumentException 容量或负载因子为负。
 */
public LinkedHashMap(int initialCapacity, float loadFactor) {
	super(initialCapacity, loadFactor);
	accessOrder = false;
}
/**
 * 以指定初始容量构造。
 *
 * @param  initialCapacity 初始容量
 * @throws IllegalArgumentException 容量或负载因子为负。
 */
public LinkedHashMap(int initialCapacity) {
    super(initialCapacity);
    accessOrder = false;
}
/**
 * 以另一 Map 构造。
 *
 * @param  m Map 结构
 * @throws NullPointerException 参数为空
 */
public LinkedHashMap(Map<? extends K, ? extends V> m) {
    super();
    accessOrder = false;
    putMapEntries(m, false);
}
```
> 代码清单：LinkedHashMap 构造函数

## LinkedHashMap `put()`方法

...








[Collections-LinkedHashMap-1-Hierachy]: ../../images/Collections-LinkedHashMap-1-Hierachy.png

[Collections-LinkedHashMap-2-DataStructure]: ../../images/Collections-LinkedHashMap-2-DataStructure.png

<!-- EOF -->
