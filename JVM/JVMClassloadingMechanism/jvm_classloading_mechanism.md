# JVM 类加载机制（JVM Classloading Mechanism）

Java 类`class`从被加载到 JVM 虚拟机至被卸载，整个生命周期包括几个阶段：

1. 加载 (Loading)
2. 验证 (Verification)
3. 准备 (Preparation)
4. 解析 (Resolution)
5. 初始化 (Initialization)
6. 使用 (Using)
7. 卸载 (Unloading)

![JVM-ClassloadingMechanism-1-ClassloadingFlow][JVM-ClassloadingMechanism-1-ClassloadingFlow]

> 图：Java 类加载流程

# 加载阶段

在加载阶段，JVM 完成以下三件事情：

- 通过一个类的**全限定名**获取类定义的**二进制字节流**，获取类字节流的途径可以是：Class 文件、Jar 包、网络、文件自动生成。

- 将类字节流的**静态存储结构**转化为方法区**运行时数据结构**。

- 在 JVM 堆中生成一个代表这个类的`java.lang.Class`对象，作为方法区类数据的访问入口。

[JVM-ClassloadingMechanism-1-ClassloadingFlow]: ../../images/JVM-ClassloadingMechanism-1-ClassloadingFlow.png

# 验证阶段

验证是为了确保加载的`Class`字节流格式正确，符合 JVM 虚拟机的要求，且不会损害虚拟机安全。

大致有几个验证步骤：

- 文件格式验证：保证类字节流可以被正确地解析，并被存储到方法区中。

- 元数据验证：确保元数据信息符合 Java 语法规范。

- 字节码验证：对类的方法体进行校验，确保运行时不会危害到虚拟机。

- 符号引用验证：对类自身外的引用符号匹配进行校验 (如：常量池中的符号引用)。

# 准备阶段

准备阶段主要为类变量分配内存并设置变量初始值，分配在方法区中。

注意要点：

- 此时的内存分配只包括类变量`static`，实例变量会在对象初始化时分配在 JVM 堆中。

- 此时变量的初始值是其对应数据类型的默认零值（如：`0`、`null`、`false`等)。

举例说明：

```java
public static int number = 6;
```

类变量`number`在准备阶段后的值为`0`而不是`6`。因为此时还没有开始执行任何 Java 方法。而将变量`number`赋值为`6`是在程序编译后执行`putstatic`指令，存放于类的构造器`<clinit>()`方法中，所以`number`赋值为`6`的操作将在**初始化阶段**进行。

```java
public static final int number = 6;
```

但是，如果变量加上`final`关键字修饰，则在编译期间就为`number`生成 ConstantValue 属性将其结果放入常量池中，在准备阶段 JVM 虚拟机根据 ConstantValue 属性直接将`number`赋值为`6`，即：在准备阶段后`number`值为`6`。

# 解析阶段

在解析阶段，JVM 虚拟机完成将常量池中符号引用转化为直接引用的过程。

- 符号引用：以一组符号来描述所引用的目标。与虚拟机实现的内存布局无关，即：所引用的目标不一定加载到了内存中。

- 直接引用：一个指向目标的指针。与虚拟机实现的内存布局相关，如果目标有了直接引用，说明已经被加载到了内存中。

解析主要针对类或接口、字段、类方法、接口方法四类符号引用，他们和常量池中的类型对应关系如下表。

| 符号引用 | 常量类型（常量池） |
| ------- | --------------- |
| 类或接口 | `CONSTANT_Class_info` |
| 字段    | `CONSTANT_Fieldref_info` |
| 类方法  | `CONSTANT_Methodref_info` |
| 接口方法 | `CONSTANT_InterfaceMethodref_info` |

> 表：符号引用与常量类型对应关系表

# 初始化阶段

初始化阶段开始执行类中的 Java 代码，主要执行`<clinit>()`类构造器方法。

类构造器方法`<clinit>()`由编译器自动收集类中所有类变量的赋值动作和静态语句块中的语句合并产生。编译器收集的顺序由语句在源文件中出现的顺序决定。特别注意的是，静态语句块只能访问到定义在它之前的类变量，定义在它之后的类变量只能赋值，不能访问。

父类的`<clinit>()`构造器方法先于子类执行，也就意味着父类中定义的静态语句块执行要优先于子类。

接口中不可以使用静态语句块，但仍然有类变量初始化的赋值操作。因此接口与类一样都会生成`<clinit>()`方法。但 接口与类不同的是，执行接口的`<clinit>()`方法不需要先执行父接口的`<clinit>()`方法。只有当父接口中定义的变量需要在子接口使用时，父接口才会初始化。另外，接口的实现类在初始化时也一样不会执行接口的`<clinit>()`方法。

**JVM 虚拟机保证一个类的`<clinit>()`方法在多线程环境下被正确地加锁和同步**。如果多个线程同时初始化一个类，只会有一个线程执行这个类的`<clinit>()`构造器方法，其它线程都会阻塞等待，直到活动线程执行`<clinit>()`构造器方法完毕。如果在一个类的`<clinit>()`方法中有比较耗时的操作，就可能造成多个线程阻塞，在实际过程中此种阻塞比较隐蔽。

# 使用阶段

当 JVM 虚拟机完成初始化阶段后，JVM 便开始从入口方法开始执行用户的程序代码。

# 卸载阶段

当用户程序代码执行完毕后，JVM 便开始销毁创建的`Class`对象，最后负责运行的 JVM 也退出内存。

# JVM 虚拟机结束生命周期

在以下几种情况下，JVM 虚拟机结束生命周期。

1. 执行`System.exit()`方法。
2. 程序执行完成正常结束。
3. 程序在执行过程过遇到了异常或错误而非正常终止。
4. 操作系统 OS 出现错误（如：内存不足）导致 JVM 进程非正常终止。

# Java 类加载器（ClassLoader）

在 Java 类的加载阶段，我们可以实现自己的 ClassLoader 自定义类加载器，从而可以动态创建符合特定需求的类，或者从特殊数据源（网络、文件系统、数据库等）中获取类`class`二进制字节流。

大致的类加载器层级结构如下：

![JVM-ClassloadingMechanism-2-ClassLoader][JVM-ClassloadingMechanism-2-ClassLoader]

> 图：Java 类加载器层级结构

- **启动类加载器（Bootstrap ClassLoader）**：负责加载`JDK/jre/lib/`路径或被`-Xbootclasspath`启动参数指定的路径下所有能被 JVM 虚拟机识别的类库（如：`rt.jar`），Java 标准类库`java.*`均由启动类加载器负责加载。启动类加载器使用 C++ 语言实现，是 JVM 虚拟机自身的一部分，无法被 Java 程序直接引用。

- **扩展类加载器（Extension ClassLoader）**：负责加载`JDK/jre/lib/ext`路径或由`java.ext.dirs`系统变量指定的路径下的类库（如：`javax.*`）。开发者可以直接引用扩展类加载器。

- **应用程序类加载器（Application ClassLoader）**：负责加载用户指定路径`classpath`下的类。开发者可以直接使用该类加载器，如果没有自定义类加载器，则其为 Java 程序中默认的类加载器。

- **线程上下文类加载器（Thread Context ClassLoader）**：线程上下文类加载器在默认情况下就是应用程序类加载器。

Java 类加载器形成的层级结构也被称为 Java 类加载器的**双亲委派模型**。类加载器之间的关系不是靠继承而是使用组合的方式实现。

如果一个类加载器收到加载类的请求，首先将请求委托给其父加载器，依次向上直至顶端的启动类加载器，此时当父类加载器在其搜索范围内找不到请求类时（即：当前类加载器无法完成类加载），子类加载器才会尝试自己加载该类。

例如要请求加载`java.lang.Object`类，最终所有的加载器都会委托启动加载器去加载，从而保证了无论是哪一个加载器想要加载`Object`类最终都指向同一个类。

双亲委派流程:

1. 当`AppClassLoader`加载一个`class`时，它首先不会自己去尝试加载这个类，而是把类加载请求委派给父类加载器`ExtClassLoader`去完成。

2. 当`ExtClassLoader`加载一个`class`时，它首先也不会自己去尝试加载这个类，而是把类加载请求委派给`BootStrapClassLoader`去完成。

3. 如果`BootStrapClassLoader`加载失败（在`$JAVA_HOME/jre/lib`中未找到该类），则使用`ExtClassLoader`尝试加载。

5. 如果`ExtClassLoader`也加载失败，则使用`AppClassLoader`来加载。

6. 如果`AppClassLoader`也加载失败，抛出`ClassNotFoundException`异常。

Java 设计者提出的双亲委派约束模型意义总结：

- 具备一种优先级的层次关系，防止系统中出现多份相同字节码。

- 保证 Java 程序运行的稳定与安全。

破坏双亲委派模型的情况：

- JDBC

- Tomcat

- OSGI（Open Service Gateway Initiative）

[JVM-ClassloadingMechanism-1-ClassloadingFlow]: ../../images/JVM-ClassloadingMechanism-1-ClassloadingFlow.png

[JVM-ClassloadingMechanism-2-ClassLoader]: ../../images/JVM-ClassloadingMechanism-2-ClassLoader.png

<!-- EOF -->