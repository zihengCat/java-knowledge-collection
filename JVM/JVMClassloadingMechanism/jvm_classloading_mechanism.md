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

实际上，JVM 虚拟机并没有规定在什么情况下需要进行类加载，可以加入启动参数`-XX:+TraceClassLoading`打印类加载日志。但是 JVM 虚拟机规范严格定义了遇到以下几种情况必须要进行类的初始化（Initialization）。

- 遇到`new`、`getstatic`、`putstatic`、`invokestatic`这 4 条字节码指令时，如果类没有进行初始化，则需要先触发其初始化。

- 使用`java.lang.reflect`方法进行反射时，如果类没有进行过初始化，则需要先触发其初始化。

- 初始化一个类的时候，如果发现其父类还没有进行过初始化，则需要先触发其父类的初始化。

- 当虚拟机启动时，用户需要指定一个要执行的主类（包含`main()`方法），JVM 会先初始化这个主类。

- 当使用 JDK 7 动态语言支持特性时，如果一个`java.lang.invoke.MethodHandle`实例解析结果为`REF_getStatic`、`REF_putStatic`、`REF_invokeStatic`方法句柄，并且这个方法句柄所对应的类没有进行过初始化，则需要先触发其初始化。该行为属于对一个类的主动引用，相对的属于被动引用的情况则不会触发初始化（如：子类引用父类的静态字段、通过数组定义来引用类、常量传播优化至调用者常量池）。

# 加载 - Loading

在加载阶段，JVM 完成以下三件事情：

- 通过一个类的**全限定名**获取该类的**二进制字节流**，获取类字节流的途径可以是：Class 文件、Jar 包、网络、文件自动生成。

- 将类字节流的静态存储结构转化为**方法区（Method Area）**运行时数据结构。

- 在内存中生成一个代表这个类的`java.lang.Class`对象，作为方法区类数据的访问入口。

# 连接 - Linking

连接阶段包含**验证、准备、解析**多个子过程。

## 验证 (Verification)

验证阶段非常重要，直接决定 JVM 能否抵御恶意代码攻击，确保`Class`字节流中包含的信息符合当前虚拟机的要求。

大致有如下几个验证步骤：

- 文件格式验证：验证字节流是否符合`Class`格式规范，并且能被当前版本的 JVM 虚拟机处理。只有通过此阶段的验证后，字节流才会进入内存的方法区中进行存储，所以后面的验证都基于方法区的存储结构进行，不会再直接操作字节流。

- 元数据验证：对类的元数据（Class Metadata）信息进行语义校验，确保元数据信息符合 Java 语法规范。

- 字节码验证：对类的方法体进行校验分析，确保类方法运行时不会危害到虚拟机安全。

- 符号引用验证：对类自身以外的符号引用进行校验 (如：常量池中的符号引用)，目的是确保解析动作能正常执行，如果无法通过符号引用验证，那么将会抛出一个`java.lang.IncompatibleClassChangeError`错误异常的子类。

## 准备 (Preparation)

准备阶段正式为类变量（Static）分配内存并设置零值，这些类变量所使用的内存都将在**方法区**中分配。

注意要点：

- 此时只为类变量`static`分配内存，分配在方法区，实例变量会在对象初始化阶段分配在 JVM 堆中。

- 此时变量的初始值是其对应数据类型的默认零值（如：`0`、`null`、`false`等)。

举例说明：

```java
public static int number = 6;
```

类变量`number`在准备阶段后的值为`0`而不是`6`。因为此时还没有开始执行任何 Java 方法。而将变量`number`赋值为`6`是在程序编译后执行`putstatic`指令，存放于类的构造器方法`<clinit>`中，所以`number`赋值为`6`的操作将在**初始化阶段**进行。

```java
public static final int number = 6;
```

但是，如果变量加上`final`关键字修饰，则在编译期间就为`number`生成 ConstantValue 属性将其结果放入常量池中，在准备阶段 JVM 虚拟机根据 ConstantValue 属性直接将`number`赋值为`6`，即：在准备阶段后`number`值为`6`。

类 Class 定义中，字段表（field_info）用于描述类或接口中声明的类变量与实例变量。字段表带有属性表集合（attributes），其中有一枚专门针对字段表描述的属性 ConstantValue 。

目前，HotSpot JVM 虚拟机前端编译器 javac 的编译行为是：如果一个变量同时使用`static`与`final`修饰，且变量数据类型为基本类型或字符串字面量，则生成 ConstantValue 属性在准备阶段对其进行初始化。如果不符合条件，则在后续`<clinit>`方法中进行初始化。

## 解析 (Resolution)

在解析阶段，JVM 虚拟机将 Class 常量池中的符号引用转化为直接引用。

类方法调用的目标方法在 Class 文件中是一枚常量池符号引用。在解析阶段，将其中一部分符号引用转换为直接引用。前提是：方法在程序运行前就有一个可确定的调用版本，且该方法调用版本在程序运行时不可变。

- 符号引用：以一组符号来描述所引用的目标。与虚拟机实现的内存布局无关，即：所引用的目标不一定加载到了内存中。

- 直接引用：一个指向目标的指针。与虚拟机实现的内存布局相关，如果目标有了直接引用，说明已经被加载到了内存中。

解析主要针对类或接口、字段、类方法、接口方法四类符号引用，他们和常量池中的类型对应关系如下表。

| 符号引用 | 常量类型（常量池） |
| :------ | :-------------- |
| 类或接口 | `CONSTANT_Class_info` |
| 字段    | `CONSTANT_Fieldref_info` |
| 类方法  | `CONSTANT_Methodref_info` |
| 接口方法 | `CONSTANT_InterfaceMethodref_info` |

> 表：符号引用与常量类型对应关系表

JVM 虚拟机规范定义了方法调用的字节码指令：

- `invokestatic`：调用静态方法。

- `invokespecial`：调用实例构造器`<init>`方法、私有方法和父类方法。

- `invokevirtual`：调用所有虚方法。

- `invokeinterface`：调用接口方法，在运行时再确定一个实现此接口的对象。

- `invokedynamic`：JDK 8 Lambda 相关调用指令。

被`invokestatic`与`invokespecial`字节码指令调用的方法，都可以在解析阶段确定唯一的调用版本，符合条件的有：静态方法、私有方法、实例构造器、父类方法以及`final`方法（尽管使用`invokevirtual`指令调用，但也是一个非虚方法），这些方法调用在类加载时就会把符号引用解析为直接引用。

# 初始化 - Initialization

在准备阶段，变量已经设置过一次零值，而在初始化阶段会按照用户对变量的实际定义执行类构造器。

初始化阶段开始执行类中的 Java 代码，主要执行`<clinit>`类构造器方法。

类构造器方法`<clinit>`由编译器自动收集类中所有类变量的赋值动作和静态语句块中的语句合并产生。编译器收集的顺序由语句在源文件中出现的顺序决定。特别注意，静态语句块只能访问到定义在其之前的变量，定义在它之后的变量可以赋值，但不能访问。

`<clinit>`构造器方法与类的构造函数不同，它不需要显式地调用父类构造器，JVM 虚拟机保证在子类的`<clinit>`方法执行之前，其父类的`<clinit>`方法已经执行完毕，这也意味着父类中定义的静态语句块执行要优先于子类。在 JVM 虚拟机中第一个被执行`<clinit>`方法的类是`java.lang.Object`。

`<clinit>`方法对于类或接口来说并不是必须的，如果一个类中没有静态语句块，也没有对变量的赋值操作，那么编译期可以不为这个类生成`<clinit>`方法。

接口中不可以使用静态语句块，但仍然有类变量初始化的赋值操作。因此接口与类一样都会生成`<clinit>`方法。但接口与类不同的是：执行接口的`<clinit>`方法不需要先执行父接口的`<clinit>`方法。只有当父接口中定义的变量需要在子接口使用时，父接口才会被初始化。另外接口的实现类在初始化时也一样不会执行接口的`<clinit>`方法。

**JVM 虚拟机保证一个类的`<clinit>`类构造器方法在多线程环境下被正确地加锁和同步**。如果多个线程同时初始化一个类，只会有一个线程执行这个类的`<clinit>`方法，其它线程都会阻塞等待，直到活动线程执行`<clinit>`完毕。如果在一个类的`<clinit>`方法中有比较耗时的操作，就可能造成多个线程阻塞，在实际过程中此种阻塞非常隐蔽。

# 使用 - Using

当 JVM 虚拟机完成初始化阶段后，JVM 开始从入口方法开始执行用户的程序代码。

# 卸载 - Unloading

当用户程序代码执行完毕后，JVM 开始销毁创建的`Class`对象，最后负责运行的 JVM 也退出内存。

# JVM 生命周期结束

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

- **线程上下文类加载器（Thread Context ClassLoader）**：通过`Thread`类`setContextClassLoader()`方法设置。如果创建线程时未设置，则从父线程中继承；如果在应用程序全局范围内都没有设置过，则默认为应用程序类加载器。为了实现 SPI（服务提供者接口）而设置的类加载器。

Java 类加载器形成的层级结构也被称为**双亲委派模型**。类加载器之间的关系不是靠继承而是使用组合的方式实现。

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

- SPI（Service Provider Interface）：JNDI、JDBC、JCE、JAXB 与 JBI 等。

- Tomcat：使用`WebappClassLoader`加载自己目录下的类文件，不传递给父类加载器。隔离不同`webapp`下的 `class`和`lib`，实现相同资源共享，实现热部署。

- OSGI（Open Service Gateway Initiative）：动态改变构造、模块化编程与热插拔。

[JVM-ClassloadingMechanism-1-ClassloadingFlow]: ../../images/JVM-ClassloadingMechanism-1-ClassloadingFlow.png

[JVM-ClassloadingMechanism-2-ClassLoader]: ../../images/JVM-ClassloadingMechanism-2-ClassLoader.png

<!-- EOF -->