# HashSet 简介

HashSet 是 Java 集合框架的一员，用于存放不重复的集合元素。关于 HashSet，有如下几个要点。

- HashSet 存放唯一不重复元素，允许存放一个`null`值。

- HashSet 内部使用 HashMap 实现。

- HashSet 不保证元素插入顺序，如需保证元素插入顺序，可以使用`LinkedHashSet`。

- HashSet 不保证元素排列顺序，如需保证元素排列顺序，可以使用`TreeSet`。

- HashSet 不是线程安全的容器，如需保证线程安全，可以使用`Collections.synchronizedSet`或`CopyOnWriteArraySet`。

# HashSet 继承体系

如图所示，HashSet 继承自`AbstractSet`抽象父类，实现了`Set`接口。

![Collections-HashSet-1-Hierachy][Collections-HashSet-1-Hierachy]

> 图：Java HashSet 继承体系









[Collections-HashSet-1-Hierachy]: ../../images/Collections-HashSet-1-Hierachy.png

<!-- EOF -->
