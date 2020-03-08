# Java `==` 运算符

在 Java 程序设计语言中，`==` 是一枚运算符，用于比较其左右两个操作数是否相等。被比较操作数的数据类型必须相同，同为基本数据类型（及其包装类）或引用数据类型。

- 基本数据类型：比较数值是否相等。

- 引用数据类型：比较地址值是否相等。

# Java `equals()` 方法

在 Java 程序设计语言中，没有操作符重载这一特性，判断两个对象是否相等，不能简单地使用`==`操作符。Java 中的每个对象都带有一枚`equals()`方法，该方法的作用是：**判断两个对象是否相等**。

Java 对象`equals()`方法定义在`java.lang.Object`中，所有 Java 类都拥有`equals()`方法，所有类都可以通过该方法比较两个对象是否相等。其默认实现等价于`==`操作符：通过比较两个对象的**地址值**来判断对象是否相等。

```java
public boolean equals(Object obj) {
    return (this == obj);
}
```
> 代码清单：`Object.equals()`方法

如果我们不希望按照地址值进行判断，而是依据其他判断逻辑（如：若两个对象的内容相等，则对象相等），则需要`@Override`覆写`equals()`方法。

覆写`equals()`方法应遵循的几项原则：

- 自反性：对于任何非空引用值`x`，`x.equals(x)`都应返回`true`。

- 对称性：对于任何非空引用值`x`和`y`，当且仅当`y.equals(x)`返回`true`时，`x.equals(y)`才应返回 `true`。

- 传递性：对于任何非空引用值`x`、`y`、`z`，如果`x.equals(y)`返回`true`，并且`y.equals(z)`返回 `true`，那么`x.equals(z)`应返回`true`。

- 一致性：对于任何非空引用值`x`和`y`，多次调用`x.equals(y)`始终返回`true`或始终返回`false`，前提是对象`equals`比较所使用的信息未被修改。

- 非空性：对于任何非空引用值`x`，`x.equals(null)`都应返回`false`。

# Java `hashCode()` 方法

Java 对象的`hashCode()`方法用于获取对象的哈希散列码，返回值是一个`int`带符号整型数。

Java 对象`hashCode()`方法定义在`java.lang.Object`中，所有 Java 类都拥有`hashCode()`方法，该方法主要在 Java 哈希散列表中被使用（如：`HashMap`、`Hashtable`）。

**如果自定义 Java 类覆写了`equals()`方法，那么也应该覆写`hashCode()`方法。**

## HotSpot VM `hashCode()` 源码

最新版 HotSpot VM `hashCode()`源码分布在以下位置。

- `src/hotspot/share/runtime/globals.hpp`

- `src/hotspot/share/runtime/synchronizer.cpp`

- `src/hotspot/share/runtime/thread.cpp`

最新版 HotSpot VM `hashCode()`通过和当前线程有关的一个随机数 + 三个确定值，运用 Marsaglia's xor-shift scheme 随机数算法得到一个新随机数。xor-shift 是由 George Marsaglia 发现的一类伪随机数生成器，其通过移位和与或计算，能够在计算机上以极快的速度生成伪随机数序列。

> Marsaglia's xor-shift scheme 论文：https://www.jstatsoft.org/article/view/v008i14/xorshift.pdf

```cpp
experimental(intx, hashCode, 5,                                           \
             "(Unstable) select hashCode generation algorithm")           \
```
> 代码清单：HotSpot VM `hashCode()`源码（`globals.hpp`）

```cpp
// hashCode() generation :
//
// Possibilities:
// * MD5Digest of {obj,stw_random}
// * CRC32 of {obj,stw_random} or any linear-feedback shift register function.
// * A DES- or AES-style SBox[] mechanism
// * One of the Phi-based schemes, such as:
//   2654435761 = 2^32 * Phi (golden ratio)
//   HashCodeValue = ((uintptr_t(obj) >> 3) * 2654435761) ^ GVars.stw_random ;
// * A variation of Marsaglia's shift-xor RNG scheme.
// * (obj ^ stw_random) is appealing, but can result
//   in undesirable regularity in the hashCode values of adjacent objects
//   (objects allocated back-to-back, in particular).  This could potentially
//   result in hashtable collisions and reduced hashtable efficiency.
//   There are simple ways to "diffuse" the middle address bits over the
//   generated hashCode values:

static inline intptr_t get_next_hash(Thread* self, oop obj) {
  intptr_t value = 0;
  if (hashCode == 0) {
    // This form uses global Park-Miller RNG.
    // On MP system we'll have lots of RW access to a global, so the
    // mechanism induces lots of coherency traffic.
    value = os::random();
  } else if (hashCode == 1) {
    // This variation has the property of being stable (idempotent)
    // between STW operations.  This can be useful in some of the 1-0
    // synchronization schemes.
    intptr_t addr_bits = cast_from_oop<intptr_t>(obj) >> 3;
    value = addr_bits ^ (addr_bits >> 5) ^ GVars.stw_random;
  } else if (hashCode == 2) {
    value = 1;            // for sensitivity testing
  } else if (hashCode == 3) {
    value = ++GVars.hc_sequence;
  } else if (hashCode == 4) {
    value = cast_from_oop<intptr_t>(obj);
  } else {
    // Marsaglia's xor-shift scheme with thread-specific state
    // This is probably the best overall implementation -- we'll
    // likely make this the default in future releases.
    unsigned t = self->_hashStateX;
    t ^= (t << 11);
    self->_hashStateX = self->_hashStateY;
    self->_hashStateY = self->_hashStateZ;
    self->_hashStateZ = self->_hashStateW;
    unsigned v = self->_hashStateW;
    v = (v ^ (v >> 19)) ^ (t ^ (t >> 8));
    self->_hashStateW = v;
    value = v;
  }
```
> 代码清单：HotSpot VM `hashCode()`源码（`synchronizer.cpp`）

```cpp
// thread-specific hashCode stream generator state - Marsaglia shift-xor form
_hashStateX = os::random();
_hashStateY = 842502087;
_hashStateZ = 0x8767;    // (int)(3579807591LL & 0xffff) ;
_hashStateW = 273326509;
```
> 代码清单：HotSpot VM `hashCode()`源码（`thread.cpp`）

对 HotSpot VM 几种`hashCode()`实现方案作简要说明。

| 实现方案 | 简要说明 |
| :----- | :------ |
| `0`    | 返回一个 Park-Miller 伪随机数生成器生成的随机数（OpenJDK 6 & 7 默认实现） |
| `1`    | 取对象的内存地址做移位运算后与一个随机数进行异或得到结果 |
| `2`    | 固定返回常量`1`(测试用) |
| `3`    | 返回一个全局递增序列的当前值 |
| `4`    | 返回当前对象的内存地址 |
| `5`    | 使用线程局部状态来实现 Marsaglia's xor-shift 随机数生成 |

> 表：HotSpot VM `hashCode()`实现方案

<!-- EOF -->