# 数组实现栈（Java）

栈（Stack）是一种后进先出（LIFO）的线性数据结构，具体实现上有：顺序栈、链式栈。

本文讲解在 Java 中使用数组实现顺序栈。

# 实现思路

数组实现栈结构，底层数据结构选用数组，栈还需要一枚栈顶指针，指示下一个元素存放的索引位置，再引入一个`size`变量指示栈中已存放元素数量。另外，将栈实现为范型类，这样栈就可以存放任何类型的数据了。

- 入栈：将元素放入栈顶位置，栈顶指针后移。

- 出栈：栈顶指针前移，返回栈顶元素。

# 数组栈测试代码（TDD）

秉持着测试驱动开发（TDD）原则，先编写数组栈测试代码。

```java
/* 导入栈接口 */
import Stack;
/* 导入栈异常 */
import StackIsEmptyException;
import StackIsFullException
/* 导入范型数组栈实现类 */
import ArrayStack;
/* 数组栈测试类 */
public class ArrayStackTest {
    public static void main(String[] args) {
        int capacity = 128;
        Stack<Integer> arrayStack = new ArrayStack<Integer>(capacity);
        System.out.println("Before push elements, ArrayStack.size(): " +
            arrayStack.size()
        );
        for (int i = 0; i < capacity; ++i) {
            arrayStack.push(i);
        }
        System.out.println("After push elements, ArrayStack.size(): " +
            arrayStack.size()
        );
        System.out.println("ArrayStack.top(): " + arrayStack.top());
        for (int i = 0; i < capacity; ++i) {
            System.out.println(arrayStack.pop());
        }
        System.out.println("After pop elements, ArrayStack.size(): " +
            arrayStack.size()
        );
    }
}
/* EOF */
```
> 代码清单：数组栈测试代码

# 数组栈数据字段

理解整体实现思路后，着手编写 Java 代码：引入栈操作接口与栈异常，使用范型类来实现栈接口，这样栈可以存放任何类型的数据；设置底层数组、栈顶指针、栈容量变量。

```java
/* 导入栈接口 */
import Stack;
/* 导入栈异常 */
import StackIsEmptyException;
import StackIsFullException
/* 数组栈 */
public class ArrayStack<E> implements Stack<E> {
    /* 栈默认容量大小 */
    private static final int DEFAULT_CAPACITY = 1 << 4; /* 16 */
    /* 存放实际元素的数组 */
    private E[] elementData;
    /* 栈顶指针（指示下一个元素在数组中的存放索引下标） */
    private int top;
    /* 栈已存放元素数量 */
    private int size;
}
/* EOF */
```
> 代码清单：数组栈数据字段 - Java 代码

# 数组栈构造函数

为数组栈提供构造函数，初始化过程为数组分配指定容量的内存，栈容量置零，栈顶指针指向下一个空闲的数组索引下标。

```java
/**
 * 默认无参构造。
 */
public ArrayStack() {
    this(DEFAULT_CAPACITY);
}
/**
 * 指定容量构造。
 * @param capacity
 */
@SuppressWarnings("unchecked")
public ArrayStack(int capacity) {
    if (capacity <= 0) {
        throw new IllegalArgumentException(
            "[ERROR]: capacity should be greater than zero"
        );
    }
    this.elementData = (E[])new Object[capacity];
    this.top = 0;
    this.size = 0;
}
```
> 代码清单：数组栈构造函数 - Java 代码

# 数组栈压栈操作

压栈操作`push()`将指定元素压入栈中，其具体实现为：先检查栈空间容量，容量不足则抛出异常，在栈顶指针位置放入元素，栈顶指针后移一位，栈存放元素数量增加1。

```java
/**
 * 将目标元素压入栈中。
 * @param e
 * @return void
 */
public void push(E e) {
    ensureCapacity();
    elementData[top++] = e;
    size++;
}
/**
 * 检查栈空间容量是否足够存放下一个元素。
 */
private void ensureCapacity() {
    if (size + 1 > elementData.length) {
        throw new StackIsFullException("[ERROR]: Stack is full");
    }
}
```
> 代码清单：数组栈压栈操作 - Java 代码

# 数组栈弹栈操作

弹栈操作`pop()`将栈顶元素弹出栈，其具体实现为：先检查栈是否为空，栈为空则抛出异常，栈存放元素数量减少1，栈顶指针前移一位，返回当前栈顶所指元素。

```java
/**
 * 将栈顶元素弹出栈。
 * @return E
 */
public E pop() {
    if (isEmpty()) {
        throw new StackIsEmptyException("[ERROR]: Stack is empty");
    }
    size--;
    return elementData[--top];
}
/**
 * 检查当前栈是否为空。
 * @return boolean
 */
public boolean isEmpty() {
    return size == 0;
}
```
> 代码清单：数组栈弹栈操作 - Java 代码

# 数组栈其他操作

接下来，实现数组栈其他一些操作，这些操作的实现都比较简单。

```java
/**
 * 查看栈顶元素。
 * @param void
 * @return E
 */
public E top() {
    if (isEmpty()) {
        throw new StackIsEmptyException("[ERROR]: Stack is empty");
    }
    return elementData[top - 1];
}
/**
 * 获取栈存放元素数量。
 * @param void
 * @return size
 */
public int size() {
    return size;
}
/**
 * 清空栈。
 * @param void
 * @return void
 */
public void clear() {
    for (int i = 0; i < size; ++i) {
        elementData[i] = null;
    }
    top = 0;
    size = 0;
}
```
> 代码清单：数组栈其他操作 - Java 代码

# 栈动态扩容（拓展内容）

在上述数组栈实现中，栈是无法动态扩容的，在栈满后再压入数据，会抛出`StackIsFullException`异常。但我们可以改写代码，实现数组栈动态扩容。具体实现：

1. 发现栈容量已满，执行扩容操作；

2. 创建一个新数组，容量扩大为原容量两倍；

3. 将旧数组中的元素按顺序依次拷贝至新数组中；

4. 将栈数组替换为新数组。

```java
/**
 * 检查栈容量是否足够存放下一个元素，不足则扩容两倍。
 */
private void ensureCapacity() {
    if (size + 1 > elementData.length) {
        // throw new StackIsFullException("[ERROR]: Stack is full");
        grow();
    }
}
/**
 * 数组扩容。
 * @param void
 * @return void
 */
@SuppressWarnings("unchecked")
private void grow() {
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity << 1;
    E[] oldElementData = this.elementData;
    E[] newElementData = (E[])new Object[newCapacity];
    for (int i = 0; i < size; ++i) {
        newElementData[i] = oldElementData[i];
        oldElementData[i] = null;
    }
    this.elementData = newElementData;
}
```
> 代码清单：栈动态扩容 - Java 代码

# 完整代码

```java
import Stack;
import StackIsEmptyException;
import StackIsFullException;
public class ArrayStack<E> implements Stack<E> {
    private static final int DEFAULT_CAPACITY = 16;
    private E[] elementData;
    private int top;
    private int size;
    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }
    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException(
                "[ERROR]: capacity should be greater than zero"
            );
        }
        this.elementData = (E[])new Object[capacity];
        this.top = 0;
        this.size = 0;
    }
    public void push(E e) {
        ensureCapacity();
        elementData[top++] = e;
        size++;
    }
    public E pop() {
        if (isEmpty()) {
            throw new StackIsEmptyException("[ERROR]: Stack is empty");
        }
        size--;
        return elementData[--top];
    }
    public E top() {
        if (isEmpty()) {
            throw new StackIsEmptyException("[ERROR]: Stack is empty");
        }
        return elementData[top - 1];
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void clear() {
        for (int i = 0; i < size; ++i) {
            elementData[i] = null;
        }
        top = 0;
        size = 0;
    }
    private void ensureCapacity() {
        if (size + 1 > elementData.length) {
            // throw new StackIsFullException("[ERROR]: Stack is full");
            grow();
        }
    }
    @SuppressWarnings("unchecked")
    private void grow() {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity << 1;
        E[] oldElementData = this.elementData;
        E[] newElementData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; ++i) {
            newElementData[i] = oldElementData[i];
            oldElementData[i] = null;
        }
        this.elementData = newElementData;
    }
}
/* EOF */
```
> 代码清单：数组实现栈 - Java 代码

<!-- EOF -->
