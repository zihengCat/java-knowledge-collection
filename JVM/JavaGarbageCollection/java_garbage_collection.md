# GC Roots

JVM 虚拟机根据 GC Roots 可达性分析判定一个对象是否被回收。

GC Roots 原理
GC Roots 基本思路就是通过一系列的称为 GC Roots 的对象作为起始点，从这些节点开始向下搜索，搜索所走过的路径称为引用链（ Reference Chain），当一个对象到 GC Roots 没有任何引用链相连（用图论的话来 说，就是从 GC Roots 到这个对象不可达）时，则证明此对象是可回收的。

GC Roots 对象
常说的GC(Garbage Collector) Roots，特指的是垃圾收集器（Garbage Collector）的对象，GC会收集那些不是GC Roots且没有被GC Roots引用的对象。

一个对象可以属于多个root，GC Roots有以下几种：

- Class - 由系统类加载器（System ClassLoader）加载的对象，这些类是不能够被回收的，他们可以以静态字段的方式保存持有其它对象。我们需要注意的一点就是，通过用户自定义的类加载器加载的类，除非相应的`Java.lang.Class`实例以其它的某种（或多种）方式成为 roots，否则它们并不是 roots。
- Thread - 活着的线程
- Stack Local - Java方法的local变量或参数
- JNI Local - JNI方法的local变量或参数
- JNI Global - 全局JNI引用
- Monitor Used - 用于同步的监控对象
- Held by JVM - 用于JVM特殊目的由GC保留的对象，但实际上这个与JVM的实现是有关的。可能已知的一些类型是：系统类加载器、一些JVM知道的重要的异常类、一些用于处理异常的预分配对象以及一些自定义的类加载器等。然而，JVM并没有为这些对象提供其它的信息，因此需要去确定哪些是属于"JVM持有"的了。

在 Java 语言中，可以作为 GC Roots 的对象包括以下几种：

- 虚拟机栈（栈帧中的本地变量表）中引用的对象

- 方法区中类静态属性引用的对象

- 方法区中常量引用的对象

- 本地方法栈中 JNI（Native 方法）引用的对象

# Java 引用类型

JDK 1.2 之前，Java 中引用的定义很传统：如果 reference 类型的数据存储的数值代表的是另一块内存的起始地址，就称这块内存代表一个引用。

JDK 1.2 以后，Java 对引用的概念进行了扩充，将引用分为强引用、软引用、弱引用、虚引用四种（引用强度逐渐减弱）。

## 1．强引用（StrongReference）

## 2．软引用（SoftReference）

## 3．弱引用（WeakReference）

## 4．虚引用（PhantomReference）



<!-- EOF -->