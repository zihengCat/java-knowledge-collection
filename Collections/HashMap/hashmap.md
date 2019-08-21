# HashMap 简介

`HashMap`是 Java 实现的哈希表，存放键值对，最常用的 Java 集合之一，但是`HashMap`并不是线程安全的集合容器。

![Collections-HashMap-1][Collections-HashMap-1]

> 图：HashMap 继承关系图

# HashMap 数据结构

在 Java 8 之前，HashMap 底层数据结构为：「数组」+「链表」。

![Collections-HashMap-2][Collections-HashMap-2]

> 图：Java 7 HashMap 数据结构

而 Java 8 之后，HashMap 底层数据结构变成了：「数组」+「链表」+「红黑树」。

![Collections-HashMap-3][Collections-HashMap-3]

> 图：Java 8 HashMap 数据结构

# HashMap 源码分析

Java HashMap 允许 4 种形式的构造函数：

- 无参默认构造

- 指定容量大小构造

- 指定容量大小与负载因子构造

- 以另一个`Map`构造

```java
...
    /* 默认构造函数 */
    public HashMap() {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
    }
    /* 包含另一个 Map 的构造函数 */
    public HashMap(Map<? extends K, ? extends V> m) {
        this.loadFactor = DEFAULT_LOAD_FACTOR;
        putMapEntries(m, false);
    }
    /* 指定[容量大小]的构造函数 */
    public HashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }
    /* 指定[容量大小]和[负载因子]的构造函数 */
    public HashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
        if (initialCapacity > MAXIMUM_CAPACITY) {
            initialCapacity = MAXIMUM_CAPACITY;
        }
        if (loadFactor <= 0 || Float.isNaN(loadFactor)) {
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        }
        this.loadFactor = loadFactor;
        this.threshold = tableSizeFor(initialCapacity);
    }
...
```
> 代码清单：HashMap 构造函数

再来看一看 HashMap 中的类字段。

```java
public class HashMap<K,V> extends AbstractMap<K,V>
    implements Map<K,V>, Cloneable, Serializable {
...
    /* 序列化号 */
    private static final long serialVersionUID = 362498820763181265L;
    /* 默认初始容量: 16 */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
    /* 最大容量: 2^30 */
    static final int MAXIMUM_CAPACITY = 1 << 30;
    /* 默认负载因子: 0.75 */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;
    /* 当哈希桶（Bucket）上的结点数大于这个值时[链表]转[红黑树] */
    static final int TREEIFY_THRESHOLD = 8;
    /* 当哈希桶（Bucket）上的结点数小于这个值时[红黑树]转[链表] */
    static final int UNTREEIFY_THRESHOLD = 6;
    /* 哈希桶中节点结构转化为[红黑树]对应表最小容量 */
    static final int MIN_TREEIFY_CAPACITY = 64;
    /* 存储元素的数组，总为2的幂次 */
    transient Node<K,V>[] table;
    /* 存放具体元素的集合 */
    transient Set<Map.Entry<K,V>> entrySet;
    /* 已存放元素的个数（不等于数组长度） */
    transient int size;
    /* 扩容或更改结构时的计数器 */
    transient int modCount;
    /* 临界值: 当实际大小（容量*负载因子）超过临界值时，触发扩容 */
    int threshold;
    /* 负载因子 */
    final float loadFactor;
...
}
```
> 代码清单：HashMap 类字段

```java
...
    /**
     * 基础 Node 数据结构，继承自 Map.Entry<K,V>
     */
    static class Node<K,V> implements Map.Entry<K,V> {
        /* 哈希值 */
        final int hash;
        /* 键 */
        final K key;
        /* 值 */
        V value;
        /* 链表后继节点 */
        Node<K,V> next;
        /* 构造函数 */
        Node(int hash, K key, V value, Node<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
        /* 取键 */
        public final K getKey()        { return key; }
        /* 取值 */
        public final V getValue()      { return value; }
        /* 字符串化 */
        public final String toString() { return key + "=" + value; }
        /* 计算节点对象哈希值 */
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }
        /* 设置新值，返回旧值 */
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }
        /* 比较节点是否相等 */
        public final boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (o instanceof Map.Entry) {
                Map.Entry<?,?> e = (Map.Entry<?,?>)o;
                if (Objects.equals(key, e.getKey()) &&
                    Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }
...
```
> 代码清单：`Node`节点类源码

```java
...
    /**
     * 红黑树节点数据结构，继承自 LinkedHashMap.Entry<K,V>
     */
    static final class TreeNode<K,V> extends LinkedHashMap.Entry<K,V> {
        TreeNode<K,V> parent;  // red-black tree links
        /* 左节点 */
        TreeNode<K,V> left;
        /* 右节点 */
        TreeNode<K,V> right;
        TreeNode<K,V> prev;    // needed to unlink next upon deletion
        /* 颜色 */
        boolean red;
        /* 构造函数 */
        TreeNode(int hash, K key, V val, Node<K,V> next) {
            super(hash, key, val, next);
        }
        /**
         * 返回红黑树根节点
         */
        final TreeNode<K,V> root() {
            for (TreeNode<K,V> r = this, p;;) {
                if ((p = r.parent) == null)
                    return r;
                r = p;
            }
        }
        /* 更多红黑树方法实现... */
...
    }
```
> 代码清单：`TreeNode`节点类源码

```java
...
    /**
     * 哈希扰动函数
     * key.hashCode()：返回对象哈希值（hashCode）
     * ^             ：按位异或
     * >>>           ：无符号右移，忽略符号位，空位以0补齐
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
...
```
> 代码清单：`hash()`哈希扰动函数

```java
...
    /**
     * 根据键获取值
     */
    public V get(Object key) {
        Node<K,V> e;
        return (e = getNode(hash(key), key)) == null ? null : e.value;
    }
    /**
     * Map.get 方法实现
     *
     * @param hash 键哈希值
     * @param key 键
     * @return 目标节点，无匹配则返回空
     */
    final Node<K,V> getNode(int hash, Object key) {
        /**
         * 准备元素
         * tab  : 哈希桶数组
         * n    : 数组长度
         * first: 头节点
         * e    : 临时节点
         * k    : 临时节点键
         */
        Node<K,V>[] tab; Node<K,V> first, e; int n; K k;
        /**
         * 哈希数组不为空
         * 数组长度大于0
         * 数组目标位置存在元素
         *
         * 位置计算方法：(n - 1) & hash
         */
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (first = tab[(n - 1) & hash]) != null) {
            /* 总是先检查头节点，键匹配成功则直接返回头节点 */
            if (first.hash == hash &&
                ((k = first.key) == key || (key != null && key.equals(k)))) {
                return first;
            }
            /* 哈希冲突（头节点后继节点有值）*/
            if ((e = first.next) != null) {
                /* 节点链为[红黑树]：使用红黑树方法查找目标节点 */
                if (first instanceof TreeNode) {
                    return ((TreeNode<K,V>)first).getTreeNode(hash, key);
                }
                /* 节点链为[链表]：遍历链表查找目标节点 */
                do {
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k)))) {
                        return e;
                    }
                } while ((e = e.next) != null);
            }
        }
        /* 没找到：该键值对不存在，返回 null */
        return null;
    }
...
```
> 代码清单：`get()`方法实现



```java
...
    /**
     * 将键值对插入哈希表中
     */
    public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
    }
    /**
     * Map.put 方法实现
     *
     * @param hash 键哈希值
     * @param key 目标键
     * @param value 目标值
     * @param onlyIfAbsent 如果设为true，则不改变已存在的值
     * @param evict 如果设为false，哈希表为creation mode
     * @return 先前的值，如果无先前键值对则返回 null
     */
    final V putVal(int hash, K key, V value, boolean onlyIfAbsent,
                   boolean evict) {
        /**
         * 准备好元素
         * tab: 哈希桶数组
         * n  : 数组长度
         * i  : 数组索引
         * p  : 临时节点
         */
        Node<K,V>[] tab; Node<K,V> p; int n, i;
        /* 如果哈希桶数组为空或数组长度为0：扩容 */
        if ((tab = table) == null || (n = tab.length) == 0) {
            n = (tab = resize()).length;
        }
        /* 哈希不冲突 */
        if ((p = tab[i = (n - 1) & hash]) == null) {
            /* 新建节点直接插入 */
            tab[i] = newNode(hash, key, value, null);
        }
        /* 哈希冲突 */
        else {
            Node<K,V> e; K k;
            /* 头节点键与目标键冲突：取得头节点 */
            if (p.hash == hash &&
                ((k = p.key) == key || (key != null && key.equals(k)))) {
                e = p;
            }
            /* 节点链为[红黑树] */
            else if (p instanceof TreeNode) {
                /* 使用红黑树方法插入新节点 */
                e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
            }
            /* 节点链为[链表] */
            else {
                /* 遍历链表，计算链表长度 */
                for (int binCount = 0; ; ++binCount) {
                    /* 使用尾插法将新节点插入到链表尾部 */
                    if ((e = p.next) == null) {
                        p.next = newNode(hash, key, value, null);
                        /* 加入新节点后链表长度大于等于树化阈值：树化 */
                        if (binCount >= TREEIFY_THRESHOLD - 1) { // -1 for 1st
                            treeifyBin(tab, hash);
                        }
                        break;
                    }
                    /* 发现键值对已存在：直接跳出 */
                    if (e.hash == hash &&
                        ((k = e.key) == key || (key != null && key.equals(k)))) {
                        break;
                    }
                    /* 与 if((e = p.next) == null) 呼应 */
                    p = e;
                }
            }
            /* 侦测到哈希表中已存在目标键 */
            if (e != null) { // existing mapping for key
                /* 取得已存在的值 */
                V oldValue = e.value;
                /**
                 * 更新键值对
                 *
                 * onlyIfAbsent 参数在这里发挥了作用
                 *   true:  不更新键值对
                 *   false: 更新键值对
                 */
                if (!onlyIfAbsent || oldValue == null) {
                    e.value = value;
                }
                /* 节点访问后操作 */
                afterNodeAccess(e);
                /* 返回旧值 */
                return oldValue;
            }
        }
        /* 操作计数器 + 1 */
        ++modCount;
        /* 哈希表存放元素个数 + 1
           节点插入后哈希表元素个数大于临界值：扩容 */
        if (++size > threshold) {
            resize();
        }
        /* 节点插入后操作 */
        afterNodeInsertion(evict);
        return null;
    }
...
```
> 代码清单：`put()`方法实现




[Collections-HashMap-1]: ../../images/Collections-HashMap-1.png

[Collections-HashMap-2]: ../../images/Collections-HashMap-2.png

[Collections-HashMap-3]: ../../images/Collections-HashMap-3.png

[Collections-HashMap-4]: ../../images/Collections-HashMap-4.png

<!-- EOF -->
