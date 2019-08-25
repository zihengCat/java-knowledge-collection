# Java 字符串类

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
> 代码清单：Java `String`类源码

从上述代码中，可以看出几点：

1. `String`类是`final`类，不能被继承，其所有成员方法默认为`final`方法。

2. `String`类通过字符数组`char[]`存储数据。

看看字符串类的构造函数。

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
> 代码清单：Java `String`类构造函数

Java 字符串类支持多种构造方式：

- 无参构造函数：构造`""`空字符串。

- 以`String`构造：拷贝另一字符串的字符数组与哈希值。

- 以`StringBuilder`构造：拷贝`StringBuilder`中的字符数组。

- 以`StringBuffer`构造：加锁拷贝字符数组（线程安全）。

- 以`char[]`构造：拷贝字符数组，支持局部字符数组。

- 以`byte[]`构造：按指定字符编码解码后拷贝。






<!-- EOF -->
