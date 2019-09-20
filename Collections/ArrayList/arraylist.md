# Java `ArrayList` 简介

ArrayList 是 Java 实现的动态数组，用于存放顺序对象，允许存放`null`值，是最常用的 Java 集合实现类之一。ArrayList 并不是线程安全的集合容器。

# ArrayList 继承体系

从 ArrayList 继承关系图中可以看出，`ArrayList`继承自抽象父类`AbstractList`，实现了`List`接口。另外，ArrayList 还支持快速随机访问`RandomAccess`。

![Collections-ArrayList-1-Hierarchy][Collections-ArrayList-1-Hierarchy]

> 图：ArrayList 继承体系图

# ArrayList 数据结构

ArrayList 底层数据结构比较简单：对象数组。数组具备的特性（固定类型、固定长度、空间连续、随机访问等）ArrayList 也都具备。另外 ArrayList 也扩展了数组，可以动态伸缩。

在 Java 中，数组一但在堆内存中创建，长度即固定不可变，ArrayList 实现数组扩容动态伸缩的原因：创建新数组，拷贝原数组元素。

扩容操作也是 ArrayList 性能消耗较大的地方。如果能提前预知数据规模，应指定容量大小，减少扩容次数，提高运行效率。

![Collections-ArrayList-2-DataStructure][Collections-ArrayList-2-DataStructure]

> 图：ArrayList 数据结构

# ArrayList 基本用法

以下代码展示了 ArrayList 基本使用方式。

```java
import java.util.ArrayList;
class ArrayListTest {
    public static void main(String args[]){
        /* 创建容量为 2 的 ArrayList */
        ArrayList<Integer> arrayList = new ArrayList<Integer>(2);
        /* 向 ArrayList 加入元素 */
        arrayList.add(1);
        arrayList.add(2);
        /* 访问 ArrayList 中的元素 */
        System.out.println(arrayList.get(1));
    }
}
```
> 代码清单：ArrayList 基本用法

# ArrayList 源码剖析

从 JDK 源码角度深入分析 ArrayList 实现。

## ArrayList 数据字段

观察 ArrayList 类中的数据字段，可以得知：

- 底层数据结构为**对象数组**。

- 默认数组容量为`10`。

- 通过整型变量`size`纪录数组容器实际存储元素数量。

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    /* 序列化号 */
    private static final long serialVersionUID = 8683452581122892189L;

    /* 默认初始化容量 */
    private static final int DEFAULT_CAPACITY = 10;

    /* 空对象数组实例 */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /* 默认空对象数组实例 */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /* 实际存储数据的对象数组 */
    transient Object[] elementData; // non-private to simplify nested class access

    /* 数组实际存储元素的数量 */
    private int size;

    /* 数组最大容量 */
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    /* 修改次数 */
    protected transient int modCount = 0;
}
```
> 代码清单：ArrayList 数据字段

## ArrayList 构造函数

观察 ArrayList 类的构造函数，可以看到，ArrayList 支持三种构造方式。

- **无参数默认构造**：构造以默认容量`10`构造空对象数组。

- **指定容量构造**：构造指定容量的空对象数组。

- **以`Collection`序列对象构造**：构造顺序为迭代器返回元素顺序的对象数组。

```java
/**
 * 以指定容量构造。
 *
 * @param  initialCapacity  初始化容量
 * @throws IllegalArgumentException 如果初始化容量为负
 */
public ArrayList(int initialCapacity) {
    /* 初始化容量大于 0 : 构造指定容量的空数组
       初始化容量等于 0 : 构造容量为 0 的空数组
       初始化容量小于 0 : 抛出异常 */
    if (initialCapacity > 0) {
        this.elementData = new Object[initialCapacity];
    } else if (initialCapacity == 0) {
        this.elementData = EMPTY_ELEMENTDATA;
    } else {
        throw new IllegalArgumentException("Illegal Capacity: "+
                                           initialCapacity);
    }
}
/**
 * 以默认容量 10 构造。
 */
public ArrayList() {
    this.elementData = DEFAULTCAPACITY_EMPTY_ELEMENTDATA;
}
/**
 * 以 Collection 序列对象构造，顺序为迭代器返回元素顺序。
 *
 * @param c 序列对象
 * @throws NullPointerException 如果序列对象为空
 */
public ArrayList(Collection<? extends E> c) {
    /* 序列对象转换数组 */
    elementData = c.toArray();
    /*  设置容量 */
    if ((size = elementData.length) != 0) {
        /* Collection.toArray() 可能返回不正常数据 */
        if (elementData.getClass() != Object[].class) {
            /* 拷贝数组元素 */
            elementData = Arrays.copyOf(elementData, size, Object[].class);
        }
    } else {
        /* 序列对象长度为零则切换至空数组 */
        this.elementData = EMPTY_ELEMENTDATA;
    }
}
```
> 代码清单：ArrayList 构造函数

## ArrayList `add()`方法

ArrayList `add()` 方法用于向容器内添加元素。默认将元素添加至数组尾部，也可以指定插入位置。

1. **插入数组尾部**：数组容量检查 -> 插入元素至数组尾部 -> 容量 + 1

2. **插入指定位置**：索引边界检查 -> 数组容量检查 -> 数组元素后移1位 -> 插入元素 -> 容量 + 1

```java
/**
 * 添加指定元素至数组尾部。
 *
 * @param e 待添加元素
 * @return true 操作成功
 */
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}
/**
 * 添加元素至数组指定位置，数组元素后移。
 *
 * @param index 指定位置索引下标
 * @param element 待添加元素
 * @throws IndexOutOfBoundsException
 */
public void add(int index, E element) {
    /* 下标索引边界检查 */
    rangeCheckForAdd(index);
    /* 数组容量检查 */
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    /* 数组元素整体后移一位 */
    System.arraycopy(elementData, index, elementData, index + 1,
                     size - index);
    /* 向指定位置添加元素 */
    elementData[index] = element;
    /* 存储元素数量加1 */
    size++;
}
```
> 代码清单：ArrayList `add()`方法

## ArrayList `remove()`方法

ArrayList `remove()` 方法用于移除容器内元素。支持两种方式：移除指定下标索引的元素，移除指定元素。

```java
/**
 * 移除指定索引下标的元素，数组元素左移1位。
 *
 * @param index 待删除元素索引下标
 * @return 删除元素
 * @throws IndexOutOfBoundsException
 */
public E remove(int index) {
    /* 索引下标边界检查 */
    rangeCheck(index);

    /* 操作 + 1 */
    modCount++;

    /* 取得待移除元素 */
    E oldValue = elementData(index);

    /* 计算移动元素长度 */
    int numMoved = size - index - 1;
    if (numMoved > 0) {
        /* 数组元素左移1位 */
        System.arraycopy(elementData, index + 1, elementData, index,
                         numMoved);
    }
    elementData[--size] = null; // clear to let GC do its work


    /* 返回移除元素 */
    return oldValue;
}

/**
 * 移除数组指定元素。
 *
 * @param o 待删除元素
 * @return boolean 操作成功返回 true，不存在目标元素返回 false
 */
public boolean remove(Object o) {
    if (o == null) {
        for (int index = 0; index < size; index++)
            if (elementData[index] == null) {
                fastRemove(index);
                return true;
            }
    } else {
        for (int index = 0; index < size; index++)
            if (o.equals(elementData[index])) {
                fastRemove(index);
                return true;
            }
    }
    return false;
}
/*
 * 快速删除方法，去除了边界检查与返回值暂存以提高效率。
 */
private void fastRemove(int index) {
    modCount++;
    int numMoved = size - index - 1;
    if (numMoved > 0) {
        System.arraycopy(elementData, index + 1, elementData, index,
                         numMoved);
    }
    elementData[--size] = null; // clear to let GC do its work
}
```
> 代码清单：ArrayList `remove()`方法


## ArrayList `grow()`扩容方法

ArrayList 扩容机制：`ensureCapacity()`检查数组容量，容量不足则触发扩容，扩容为原数组的 1.5 倍。

```java
/**
 * 数组扩容，保证容器可以存储期望最小可用容量的元素。
 *
 * @param minCapacity 期望最小可用容量
 */
private void grow(int minCapacity) {
    /* 当前数组长度 */
    int oldCapacity = elementData.length;
    /* 扩容数组长度，扩容 1.5 倍（1 + 1/2） */
    int newCapacity = oldCapacity + (oldCapacity >> 1);
    /* 扩容 1.5 倍后仍不满足需求，取「期望最小可用容量」 */
    if (newCapacity - minCapacity < 0) {
        newCapacity = minCapacity;
    }
    /* 扩容后超过最大限定容量，取「最大限定容量」 */
    if (newCapacity - MAX_ARRAY_SIZE > 0) {
        newCapacity = hugeCapacity(minCapacity);
    }
    /* 调用 Arrays.copyOf() 拷贝元素至新数组 */
    elementData = Arrays.copyOf(elementData, newCapacity);
}
private static int hugeCapacity(int minCapacity) {
    if (minCapacity < 0) { // overflow
        throw new OutOfMemoryError();
    }
    return (minCapacity > MAX_ARRAY_SIZE) ?
        Integer.MAX_VALUE :
        MAX_ARRAY_SIZE;
}
```
> 代码清单：ArrayList `grow()`扩容方法


## ArrayList 其他方法

下面看一看 ArrayList 其他的一些常用方法。

```java
/**
 * 返回指定索引下标的元素。
 *
 * @param  index 索引下标
 * @return 目标索引元素
 * @throws IndexOutOfBoundsException
 */
public E get(int index) {
    /* 边界检查 */
    rangeCheck(index);
    /* 返回对应数组元素 */
    return elementData(index);
}
/**
 * 将制定索引位置的元素替换为新元素。
 *
 * @param index 替换元素的索引下标
 * @param element 替换元素
 * @return 旧元素
 * @throws IndexOutOfBoundsException
 */
public E set(int index, E element) {
    /* 边界检查 */
    rangeCheck(index);
    /*  暂存旧元素 */
    E oldValue = elementData(index);
    /*  替换元素 */
    elementData[index] = element;
    /*  返回旧元素 */
    return oldValue;
}
/**
 * 清空数组。
 */
public void clear() {
    /* 操作次数 + 1 */
    modCount++;
    /* 遍历数组，置空元素 */
    for (int i = 0; i < size; i++) {
        elementData[i] = null;
    }
    /* 存储数量置零 */
    size = 0;
}
/* 「索引访问」辅助函数 */
@SuppressWarnings("unchecked")
E elementData(int index) {
    return (E) elementData[index];
}
/* 「边界检查」辅助函数 */
private void rangeCheck(int index) {
    if (index >= size) {
        throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
}
/**
 * 返回指定元素在数组中第一次出现的索引下标。返回 -1 表示不存在该元素。
 *
 * @param o 目标元素
 * @return 目标元素下标
 */
public int indexOf(Object o) {
    if (o == null) {
        for (int i = 0; i < size; i++) {
            if (elementData[i] == null) {
                return i;
            }
        }
    } else {
        for (int i = 0; i < size; i++) {
            if (o.equals(elementData[i])) {
                return i;
            }
        }
    }
    return -1;
}
/**
 * 容器中是否包含目标元素
 * @param o 目标元素
 * @return boolean
 */
public boolean contains(Object o) {
    /* 调用 indexOf() 实现 */
    return indexOf(o) >= 0;
}
/**
 * 容器转数组。
 */
public Object[] toArray() {
    /* 调用 Arrays.copyOf() 复制出一份新数组返回 */
    return Arrays.copyOf(elementData, size);
}
```
> 代码清单：ArrayList 其他方法




[Collections-ArrayList-1-Hierarchy]: ../../images/Collections-ArrayList-1-Hierarchy.png

[Collections-ArrayList-2-DataStructure]: ../../images/Collections-ArrayList-2-DataStructure.png

<!-- EOF -->
