# Java 字符串类

Java 字符串类是 Java 程序设计中使用最为频繁的类之一，深入理解 Java 字符串类是非常有必要的。

# Java 字符串类数据结构

**与 Java 基本类型不同，Java 字符串是一个类（Class）。**

```java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
    /* 底层数据存储 */
    private final char value[];

    /* 字符串哈希值 */
    private int hash;

    /* 序列化号 */
    private static final long serialVersionUID = -6849794470754667710L;
...
}
```
> 代码清单：Java 字符串类源码（部分）

从上述代码中，可以看出几点：

1. `String`类是`final`类，不能被继承，其所有成员方法默认为`final`方法。

2. `String`类通过字符数组`char[]`存储数据。

# Java 字符串类构造函数

观察 Java 字符串类的构造函数，Java 字符串类支持多种构造方式：

- 无参构造函数：构造`""`空字符串。

- 以`String`构造：拷贝另一字符串的字符数组与哈希值。

- 以`StringBuilder`构造：拷贝`StringBuilder`中的字符数组。

- 以`StringBuffer`构造：加锁后拷贝`StringBuffer`中的字符数组（线程安全）。

- 以`char[]`构造：拷贝字符数组（支持局部字符数组）。

- 以`byte[]`构造：按指定字符编码解码后拷贝构造字符串。

```java
/* 默认构造函数 */
public String() {
    this.value = "".value;
}
/* 以 String 构造 */
public String(String original) {
    this.value = original.value;
    this.hash = original.hash;
}
/* 以 StringBuffer 构造 */
public String(StringBuffer buffer) {
    synchronized(buffer) {
        this.value = Arrays.copyOf(buffer.getValue(), buffer.length());
    }
}
/* 以 StringBuilder 构造 */
public String(StringBuilder builder) {
    this.value = Arrays.copyOf(builder.getValue(), builder.length());
}
/* 以字符数组构造 */
public String(char value[]) {
    this.value = Arrays.copyOf(value, value.length);
}
/* 以局部字符数组构造 */
public String(char value[], int offset, int count) {
    if (offset < 0) {
        throw new StringIndexOutOfBoundsException(offset);
    }
    if (count <= 0) {
        if (count < 0) {
            throw new StringIndexOutOfBoundsException(count);
        }
        if (offset <= value.length) {
            this.value = "".value;
            return;
        }
    }
    // Note: offset or count might be near -1>>>1.
    if (offset > value.length - count) {
        throw new StringIndexOutOfBoundsException(offset + count);
    }
    this.value = Arrays.copyOfRange(value, offset, offset + count);
}
/* 以字节数组构造 */
public String(byte bytes[], int offset, int length, String charsetName)
        throws UnsupportedEncodingException {
    if (charsetName == null) {
        throw new NullPointerException("charsetName");
    }
    checkBounds(bytes, offset, length);
    this.value = StringCoding.decode(charsetName, bytes, offset, length);
}
```
> 代码清单：Java 字符串类构造函数

# Java 字符串类静态工厂方法

除了构造函数外，Java 字符串类还提供了一系列静态工厂方法，支持从基本数据类型、对象构造字符串，其内部实现为：调用基本类型包装类和对象类型的`toString()`方法。

```java
/* 通过[布尔型]构造字符串 */
public static String valueOf(boolean b) {
    return b ? "true" : "false";
}
/* 通过[字符型]构造字符串 */
public static String valueOf(char c) {
    char data[] = {c};
    return new String(data, true);
}
/* 通过[整数型]构造字符串 */
public static String valueOf(int i) {
    return Integer.toString(i);
}
/* 通过[长整型]构造字符串 */
public static String valueOf(long l) {
    return Long.toString(l);
}
/* 通过[单精度浮点型]构造字符串 */
public static String valueOf(float f) {
    return Float.toString(f);
}
/* 通过[双精度浮点型]构造字符串 */
public static String valueOf(double d) {
    return Double.toString(d);
}
/* 通过[字符数组]构造字符串 */
public static String valueOf(char data[]) {
    return new String(data);
}
/* 通过[局部字符数组]构造字符串 */
public static String valueOf(char data[], int offset, int count) {
    return new String(data, offset, count);
}
/* 通过[对象]构造字符串 */
public static String valueOf(Object obj) {
    return (obj == null) ? "null" : obj.toString();
}
```
> 代码清单：Java 字符串类静态工厂方法

# Java 字符串类常用方法

观察 Java 字符串类的常用方法后，我们会发现，对字符串的变更操作都不是在原有字符串上进行的，而是重新生成了一个新的字符串对象，执行操作后，原始字符串并没有被改变。

**`String`字符串对象一旦被创建即固定不变，任何对`String`对象的操作都会不影响原对象，任何对字符串的变更操作都会生成新的字符串对象。**

```java
/* 连接字符串 */
public String concat(String str) {
    int otherLen = str.length();
    if (otherLen == 0) {
        return this;
    }
    int len = value.length;
    char buf[] = Arrays.copyOf(value, len + otherLen);
    str.getChars(buf, len);
    return new String(buf, true);
}
/* 替换字符 */
public String replace(char oldChar, char newChar) {
    if (oldChar != newChar) {
        int len = value.length;
        int i = -1;
        char[] val = value; /* avoid getfield opcode */

        while (++i < len) {
            if (val[i] == oldChar) {
                break;
            }
        }
        if (i < len) {
            char buf[] = new char[len];
            for (int j = 0; j < i; j++) {
                buf[j] = val[j];
            }
            while (i < len) {
                char c = val[i];
                buf[i] = (c == oldChar) ? newChar : c;
                i++;
            }
            return new String(buf, true);
        }
    }
    return this;
}
/* 截取子串 */
public String substring(int beginIndex, int endIndex) {
    if (beginIndex < 0) {
        throw new StringIndexOutOfBoundsException(beginIndex);
    }
    if (endIndex > value.length) {
        throw new StringIndexOutOfBoundsException(endIndex);
    }
    int subLen = endIndex - beginIndex;
    if (subLen < 0) {
        throw new StringIndexOutOfBoundsException(subLen);
    }
    return ((beginIndex == 0) && (endIndex == value.length)) ? this
            : new String(value, beginIndex, subLen);
}
/* 字符串转字符数组 */
public char[] toCharArray() {
    // Cannot use Arrays.copyOf because of class initialization order issues
    char result[] = new char[value.length];
    System.arraycopy(value, 0, result, 0, value.length);
    return result;
}
```
> 代码清单：Java 字符串类常用方法

# 字符串常量池

在 Java 中，字符串也是一个类，字符串分配和其他对象分配一样，需要消耗高昂的时间和空间。但字符串的使用非常频繁，JVM 为了提高性能和减少内存开销，在实例化字符串时会进行优化：**使用字符串常量池**。

当我们创建字符串常量时，JVM 会首先检查字符串常量池，如果该字符串已经存在常量池中，直接返回常量池中的字符串引用；如果字符串不存在常量池中，就会实例化该字符串并将其放入常量池中。由于字符串的不可变性，JVM 保证常量池中一定不存在两个相同的字符串（这一点至关重要）。

字符串常量池位于`.class`文件之中，在运行期间被 JVM 装载，并且可以扩充。字符串对象`intern()`方法就是扩充字符串常量池的方法之一：当一个字符串对象实例调用`intern()`方法时，查找常量池中是否存在相同 Unicode 字符串常量，如果存在，返回常量池中的字符串引用，如果不存在，则在字符串常量池中新增一个 Unicode 字符串常量并返回其引用。

```java
/* 返回字符串常量池中的字符串引用 */
public native String intern();
```
> 代码清单：Java 字符串类`intern()`方法

通过下图，可以更好的理解 Java 字符串类与字符串常量池的关系。

![Basic-String-1][Basic-String-1]

> 图：Java 字符串类常量池操作

# 字符串比较

在 Java 中，操作符`==`与`equals()`方法，含义是不同的。

1. `==`操作符
    - 作用于基本数据类型（`byte`、`short`、`char`、`int`、`long`、`float`、`double`、`boolean`），直接比较其存储的值是否相等。
    - 作用于引用类型（`Object`），比较对象所指的地址是否相等。

2. `equals()`比较方法
    - `Object`基类中定义的方法，所有类都继承了该方法（所有类都是`Object`类的子类）。
    - 在`Object`基类中，`equals()`方法实现为比较两个对象的地址是否相等（等同于`==`操作符）。
    - 子类可以重写`equals()`方法，实现出自己的比较方式（如：`String`类）。

Java 字符串类重写了`equals()`比较方法，将其实现为比较字符串内部字符数组的值是否全部相等。进行字符串比较操作时，应使用`equals()`方法而非`==`操作符。

```java
/* 字符串比较 */
public boolean equals(Object anObject) {
    if (this == anObject) {
        return true;
    }
    if (anObject instanceof String) {
        String anotherString = (String)anObject;
        int n = value.length;
        if (n == anotherString.value.length) {
            char v1[] = value;
            char v2[] = anotherString.value;
            int i = 0;
            while (n-- != 0) {
                if (v1[i] != v2[i]) {
                    return false;
                }
                i++;
            }
            return true;
        }
    }
    return false;
}
```
> 代码清单：Java 字符串类`equals()`方法

# 字符串连接

Java 语言并没有操作符重载（Operator Overloading）这一机制，但是允许我们使用操作符`+`连接多条字符串。

```java
String s0 = "a" + "b" + "c";
String s1 = "a";
String s2 = "b";
String s3 = "c";
String s4 = s1  +  s2  +  s3;
```
> 代码清单：Java 字符串连接样例

这实际上是 Java 编译器为程序员提供的语法糖。字符串`+`操作符连接，会被转化为`StringBuilder`类操作。

例如，对于上述语句，编译器会执行优化：

```java
String s0 = "abc";
...
String s4 = new StringBuilder(s1).append(s2).append(s3).toString();
```
> 代码清单：Java 字符串连接样例（编译器优化）

因此，使用`+`操作符连接字符串是比较低效的（尤其是在循环中使用），如果要连接大量字符串，应使用`StringBuffer`类或`StringBulider`类。

# `String`、`StringBuffer`、`StringBuilder`异同

`String`、`StringBuffer`、`StringBuilder`都是 Java 中操作字符串的常用类，它们各有异同。

1. **数据结构**：`String`、`StringBuffer`、`StringBuilder`底层数据结构都是`char[]`字符数组。

2. **可变性**：`String`是不可变字符串对象，`StringBuilder`和`StringBuffer`都是可变字符串对象（其内部的字符数组长度可变）。

3. **线程安全**：`String`字符串对象不可变，可以理解为常量，天然线程安全。`StringBuilde`r`无相关线程安全机制保障，线程不安全。`StringBuffer`与`StringBuilder`中的方法和功能完全等价，但`StringBuffer`中的方法大都使用了`synchronized`同步锁，线程安全。

4. **执行效率**：通常情况下`StringBuilder > StringBuffer > String`，不绝对。

5. **使用场景**：字符串拼接或改动较少的情况下，使用`String`；字符串拼接操作较多的情况下，使用`StringBuilder`；多线程并发情况下，使用`StringBuffer`。

# Java 字符串长度

Java 中的字符串`String`是否有长度限制。这个问题要分两个阶段看，分别是**编译时**和**运行时**，不同的时期限制不同。

编译时：我们使用字符串字面量直接定义`String`的时候，Java 将字符串放入常量池中存储。常量池中的每一种数据项也有自己的类型，Java 中的 UTF-8 编码的 Unicode 字符串在常量池中以`CONSTANT_Utf8`类型表示。定义如下：

```plain
CONSTANT_Utf8_info {
    u1 tag;
    u2 length;
    u1 bytes[length];
}
```
> 代码清单：`CONSTANT_Utf8_info`类型

其中`u2`表示无符号 16 位整型，理论上允许最大长度是`2 ^ 16 = 65536`。而 Java Class 文件是使用一种变体 UTF-8 格式来存放字符，`null`值使用两个字节表示，因此剩下`65536 - 2 ＝ 65534`字节。

编译时：Java 字符串字面量最大长度为`2 ^ 16 - 2 = 65534`。

运行时：在运行时，Java 字符串`String`使用字符数组`char[]`存储，Java 数组长度必须是非负整数，理论最大值为`Integer.MAX_VALUE`。

运行时：Java 字符串最大长度为`2 ^ 31 - 1 = 2147483647`。

[Basic-String-1]: ../../images/Basic-String-1.png

<!-- EOF -->
