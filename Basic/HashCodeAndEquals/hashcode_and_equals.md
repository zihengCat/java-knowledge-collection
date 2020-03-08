# Java `equals()` 方法

在 Java 程序设计语言中，没有操作符重载这一特性，判断两个对象是否相等，不能简单地使用`==`操作符。Java 中的每个对象都带有一枚`equals()`方法，该方法的作用是：判断两个对象是否相等。

`equals()`方法定义在`java.lang.Object`中，所有 Java 类都实现了`equals()`方法，所有类都可以通过该方法比较两个对象是否相等。其默认实现等价于`==`操作符：通过比较两个对象的**地址值**来判断对象是否相等。

```java
public boolean equals(Object obj) {
    return (this == obj);
}
```
> 代码清单：`Object.equals()`方法

如果我们不希望按照地址值进行判断，而是依据其他判断逻辑（如：若两个对象的内容相等，则对象相等），则需要`@Override`覆写`equals()`方法。

<!-- EOF -->