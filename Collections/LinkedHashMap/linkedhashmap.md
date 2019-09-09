# Java `LinkedHashMap` 简介

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

![Collections-LinkedHashMap-3-AddNode][Collections-LinkedHashMap-3-AddNode]

> 图：LinkedHashMap 添加节点

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
 * @param  m Map 结构参数
 * @throws NullPointerException 参数为空
 */
public LinkedHashMap(Map<? extends K, ? extends V> m) {
    super();
    accessOrder = false;
    putMapEntries(m, false);
}
```
> 代码清单：LinkedHashMap 构造函数

## LinkedHashMap 新增节点方法

LinkedHashMap 插入元素直接复用 HashMap `put()`方法，没有重写 HashMap 的`put()`方法，而是重写了构建新节点的`newNode()`方法。

```java
/* 创建新单链表节点 */
Node<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
    LinkedHashMap.Entry<K,V> p =
        new LinkedHashMap.Entry<K,V>(hash, key, value, e);
    linkNodeLast(p);
    return p;
}
/* 创建新红黑树节点 */
TreeNode<K,V> newTreeNode(int hash, K key, V value, Node<K,V> next) {
    TreeNode<K,V> p = new TreeNode<K,V>(hash, key, value, next);
    linkNodeLast(p);
    return p;
}
/* 将节点链接到双向链表尾部 */
private void linkNodeLast(LinkedHashMap.Entry<K,V> p) {
    LinkedHashMap.Entry<K,V> last = tail;
    tail = p;
    if (last == null) {
        head = p;
    } else {
        p.before = last;
        last.after = p;
    }
}
```
> 代码清单：LinkedHashMap 新增节点方法

## LinkedHashMap 移除节点方法

...

# LinkedHashMap 总结

LinkedHashMap 相对于 HashMap，源码简单许多，因为其继承了 HashMap，大部分操作都复用 HashMap 方法，仅重写了几个方法，用于维护迭代顺序。这也是其与 HashMap 相比最大的不同：有序迭代。在每次插入、访问、修改数据时，都会新增节点、调整内部双向链表节点顺序。

- `accessOrder`，默认为`false`，则迭代时输出的顺序是插入节点的顺序。若设为`true`，则输出顺序按照节点访问顺序。设为`true`时，可以构建一个 LRU 缓存。

- LinkedHashMap 并没有重写任何`put()`方法，而是重写了构建新节点的`newNode()`方法，在每次构建新节点时，将新节点链接到内部双向链表尾部。

- 访问顺序`accessOrder = true`模式下，在`afterNodeAccess()`函数中，会将当前被访问到的节点移动至内部双向链表尾部。`afterNodeAccess()`会修改`modCount`，d多线程操作会导致*fail-fast*，因为内部迭代顺序已经改变。

- LinkedHashMap 中`nextNode()`方法就是迭代器`Iterator`中的`next()`方法实现。从内部双链表的头节点开始循环输出，保证迭代顺序。双链表节点顺序则在增、删、改、查时都会更新。

- LinkedHashMap 重写了`containsValue()`方法，直接遍历内部双向链表比对值是否相等，效率更高。

- LinkedHashMap 不重写`containsKey()`方法，因为`key`使用`hash()`算法快速定位。

[Collections-LinkedHashMap-1-Hierachy]: ../../images/Collections-LinkedHashMap-1-Hierachy.png

[Collections-LinkedHashMap-2-DataStructure]: ../../images/Collections-LinkedHashMap-2-DataStructure.png

[Collections-LinkedHashMap-3-AddNode]: ../../images/Collections-LinkedHashMap-3-AddNode.png

<!-- EOF -->
