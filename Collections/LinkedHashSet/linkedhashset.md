# Java `LinkedHashSet` 简介

LinkedHashSet 利用「哈希表」与「双向链表」实现`Set`接口，可以存储唯一不重复数据。与 HashSet 的不同之处在于：**LinkedHashSet 可以按顺序迭代元素**。

# Java `LinkedHashSet` 实现

LinkedHashSet 继承自 HashSet，大部分操作也是直接复用 HashSet 方法实现。唯一的技巧就在于：**调用「隐藏构造函数」构造父类 HashSet，使 LinkedHashSet 后端变为 LinkedHashMap 实现，由 LinkedHashMap 维护内部元素的顺序**。

```java
/* 继承自 HashSet，实现 Set 接口 */
public class LinkedHashSet<E>
    extends HashSet<E>
    implements Set<E>, Cloneable, java.io.Serializable {

    private static final long serialVersionUID = -2851667679971038690L;

    public LinkedHashSet(int initialCapacity, float loadFactor) {
        super(initialCapacity, loadFactor, true);
    }
    public LinkedHashSet(int initialCapacity) {
        super(initialCapacity, .75f, true);
    }
    public LinkedHashSet() {
        super(16, .75f, true);
    }
    public LinkedHashSet(Collection<? extends E> c) {
        super(Math.max(2*c.size(), 11), .75f, true);
        addAll(c);
    }
}
```
> 代码清单：LinkedHashSet 源码实现

```java
HashSet(int initialCapacity, float loadFactor, boolean dummy) {
    /* 后端实现变更为 LinkedHashMap */
    map = new LinkedHashMap<>(initialCapacity, loadFactor);
}
```
> 代码清单：HashSet 隐藏构造函数

<!-- EOF -->
