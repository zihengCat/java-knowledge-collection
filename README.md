# Java Knowledge Collection

Java 核心知识点梳理。

# 目录（TOC）

<!-- TOC -->
  * [Java Core](#Java-Core)
    * [Java 基础](#Java-基础)
    * [Java 进阶](#Java-进阶)
    * [JVM 虚拟机](#JVM-虚拟机)
    * [Java 并发](#Java-并发)
    * [Java 集合](#Java-集合)
  * [Java 框架](#Java-框架)
  * [设计模式](#设计模式)
  * [数据库](#数据库)
  * [操作系统](#操作系统)
  * [计算机网络](#计算机网络)
  * [计算机组成原理](#计算机组成原理)
  * [数据结构与算法](#数据结构与算法)
  * [LeetCode 题解](#LeetCode-题解)
<!-- TOC -->

## Java Core

### Java 基础

- [Java 字符串](Basic/String/string.md)

- [Java 比较器](Basic/ComparableAndComparatorInterfaces/comparable_and_comparator_interfaces.md)

- [Java `==`操作符、`equals()`方法、`hashCode()`方法](Basic/HashCodeAndEquals/hashcode_and_equals.md)

### Java 进阶

- [Java 动态代理](./Advanced/DynamicProxy/dynamic_proxy.md)

### JVM 虚拟机

- [Java 虚拟机 JVM 概览](./JVM/01.Overview/overview.md)

- [JVM 线程](./JVM/02.JVM-Threads/jvm_threads.md)

- [JVM 内存区域](./JVM/03.JVM-Memory-Areas/jvm_memory_areas.md)

- [JVM 类加载机制](./JVM/JVMClassloadingMechanism/jvm_classloading_mechanism.md)

### Java 并发

- [Java 内存模型（JMM）](./JVM/JavaMemoryModel/java_memory_model.md)

- [Java 并发编程概览](./Concurrency/01.Overview/overview.md)

- [Java 并发框架 - AQS（抽象队列同步器）](./Concurrency/AQS/abstract_queued_synchronizer.md)

- [Java 线程池](./Concurrency/ThreadPool/thread_pool.md)

### Java 集合

#### List 系列

- [ArrayList 源码分析](Collections/ArrayList/arraylist.md)

- [LinkedList 源码分析](Collections/LinkedList/linkedlist.md)

#### Queue 系列

- [ArrayDeque 源码分析](Collections/ArrayDeque/arraydeque.md)

- [PriorityQueue 源码分析](Collections/PriorityQueue/priority_queue.md)

#### Map 系列

- [HashMap 源码分析](Collections/HashMap/hashmap.md)

- [TreeMap 源码分析](Collections/TreeMap/treemap.md)

- [LinkedHashMap 源码分析](Collections/LinkedHashMap/linkedhashmap.md)

#### Set 系列

- [HashSet 源码分析](Collections/HashSet/hashset.md)

- [TreeSet 源码分析](Collections/TreeSet/treeset.md)

- [LinkedHashSet 源码分析](Collections/LinkedHashSet/linkedhashset.md)

## Java 框架

### Servlet

- [Java Servlet 概览](./Servlet/Overview/overview.md)

### Spring

- Spring Bean 生命周期

## 设计模式

- 设计模式概述

### 创建型

- 单例模式（Singleton）

- 简单工厂模式（Simple Factory）

- 工厂方法模式（Factory Method）

- 抽象工厂模式（Abstract Factory）

- 建造者模式（Builder）

- 原型模式（Prototype）

### 结构型

- 适配器模式（Adapter）

- 桥接模式（Bridge）

- 组合模式（Composite）

- 装饰模式（Decorator）

- 外观模式（Facade）

- 享元模式（Flyweight）

- 代理模式（Proxy）

### 行为型

- 职责链模式（Chain of Responsibility）

- 命令模式（Command）

- 解释器模式（Interpreter）

- 迭代器模式（Iterator）

- 中介者模式（Mediator）

- 备忘录模式（Memento）

- 观察者模式（Observer）

- 状态模式（State）

- 策略模式（Strategy）

- 模板方法模式（Template Method）

- 访问者模式（Visitor）

## 数据库

### MySQL

- [MySQL 概览](./Databases/MySQLOverview/mysql_overview.md)

### Redis

- [Redis 概览](./Databases/RedisOverview/redis_overview.md)

## 计算机网络

- [计算机网络 - 传输层（Transport Layer）](./Network/TransportLayer/TransportLayer.md)

## 操作系统

...

## 计算机组成原理

...

## 数据结构与算法

### 排序

- [冒泡排序 - Java 实现](./DataStructuresAndAlgorithms/BubbleSort/bubble_sort.md)

- [选择排序 - Java 实现](./DataStructuresAndAlgorithms/SelectionSort/selection_sort.md)

- [插入排序 - Java 实现](./DataStructuresAndAlgorithms/InsertionSort/insertion_sort.md)

- [快速排序 - Java 实现](./DataStructuresAndAlgorithms/QuickSort/quick_sort.md)

- [归并排序 - Java 实现](./DataStructuresAndAlgorithms/MergeSort/merge_sort.md)

- [堆排序 - Java 实现](./DataStructuresAndAlgorithms/HeapSort/heap_sort.md)

- [计数排序 - Java 实现](./DataStructuresAndAlgorithms/CountSort/count_sort.md)

### 回溯剪枝

- [LeetCode 017. Letter Combinations of a Phone Number](./src/io/ziheng/recursion/backtracking/leetcode/LetterCombinationsOfAPhoneNumber.java)

- [LeetCode 079. Word Search](./src/io/ziheng/recursion/backtracking/leetcode/WordSearch.java)

### 动态规划

- [斐波那契数列](./DataStructuresAndAlgorithms/FibonacciSequence/fibonacci_sequence.md)

- [背包问题]()

### 数组

- [动态数组（ArrayList）数据结构及其 Java 实现](./DataStructuresAndAlgorithms/ArrayList/array_list.md)

### 字符串

...

### 链表

- [线性表（List）数据结构概览](./DataStructuresAndAlgorithms/ListOverview/list_overview.md)

### 栈

- [栈（Stack）数据结构概览](./DataStructuresAndAlgorithms/StackOverview/stack_overview.md)

- [数组实现栈（Java）](./DataStructuresAndAlgorithms/ArrayStack/array_stack.md)

- [链表实现栈（Java）](./DataStructuresAndAlgorithms/LinkedStack/linked_stack.md)

- [队列实现栈（Java）](./DataStructuresAndAlgorithms/QueueStack/queue_stack.md)

### 队列

- [队列（Queue）数据结构概览](./DataStructuresAndAlgorithms/QueueOverview/queue_overview.md)

- [数组实现队列（Java）](./DataStructuresAndAlgorithms/ArrayQueue/array_queue.md)

- [链表实现队列（Java）](./DataStructuresAndAlgorithms/LinkedQueue/linked_queue.md)

- [栈实现队列（Java）](./DataStructuresAndAlgorithms/StackQueue/stack_queue.md)

- [优先队列（PriorityQueue）数据结构 Java 实现](./DataStructuresAndAlgorithms/PriorityQueue/priority_queue.md)

### 树

- [树（Tree）数据结构概览](./DataStructuresAndAlgorithms/TreeOverview/tree_overview.md)

- [二叉树（Binary Tree）数据结构概览](./DataStructuresAndAlgorithms/BinaryTreeOverview/binary_tree_overview.md)

- [二叉树遍历（Java）](./DataStructuresAndAlgorithms/BinaryTreeTraversal/binary_tree_traversal.md)

- [二叉树最大深度（Java）](./DataStructuresAndAlgorithms/MaximumDepthOfBinaryTree/maximum_depth_of_binary_tree.md)

- [二分搜索树（Binary Search Tree）数据结构概览及其实现](./DataStructuresAndAlgorithms/BinarySearchTree/binary_search_tree.md)

- [AVL 树概览及其实现](./DataStructuresAndAlgorithms/AVLTree/avl_tree.md)

- [红黑树（Red Black Tree）概览及其实现](./DataStructuresAndAlgorithms/RedBlackTree/red_black_tree.md)

### 堆

- [堆（Heap）数据结构与二叉堆 Java 实现](./DataStructuresAndAlgorithms/Heap/heap.md)

- [索引堆（Index Heap）结构及其 Java 实现]()

### 图

- [图（Graph）的基本概念](./DataStructuresAndAlgorithms/GraphOverview/graph_overview.md)

- [图（Graph）的基本表示](./DataStructuresAndAlgorithms/GraphRepresentations/graph_representations.md)

- [图（Graph）的遍历 - 深度优先遍历（Depth-First-Search）](./DataStructuresAndAlgorithms/GraphDepthFirstSearch/graph_depth_first_search.md)

- [图（Graph）的遍历 - 广度优先遍历（Breadth-First-Search）](./DataStructuresAndAlgorithms/GraphBreadthFirstSearch/graph_breadth_first_search.md)

- 桥（Bridge）与割点（Cut-Vertex）

- [哈密尔顿图（Hamiltonian Graph）与哈密尔顿回路（Hamiltonian Circuit）](./DataStructuresAndAlgorithms/HamiltonianCircuitAndGraph/hamiltonian_circuit_and_graph.md)

- [欧拉路径（Euler Path）与欧拉回路（Euler Circuit）](./DataStructuresAndAlgorithms/EulerPathAndCircuit/euler_path_and_circuit.md)

- [带权图（Weighted Graph）及其实现](./DataStructuresAndAlgorithms/EulerPathAndCircuit/euler_path_and_circuit.md)

- [带权图（Weighted Graph）的最小生成树（Minimum Spanning Tree）](./DataStructuresAndAlgorithms/MinimumSpanningTreeOfWeightedGraph/minimum_spanning_tree_of_weighted_graph.md)

- [带权图（Weighted Graph）的最短路径（Shortest Path）](./DataStructuresAndAlgorithms/WeightedGraphShortestPath/weighted_graph_shortest_path.md)

### 并查集

- [并查集（Union Find）数据结构概览]()

### 哈希表

- [哈希表（HashTable）概览](./DataStructuresAndAlgorithms/HashTableOverview/hash_table_overview.md)

- [哈希表数据结构 Java 实现](./DataStructuresAndAlgorithms/HashTable/hash_table.md)

### 位运算

- 状态压缩

## LeetCode 题解

### Array

- [LeetCode 283. Move Zeroes](src/io/ziheng/array/leetcode/MoveZeroes.java)

- [LeetCode 11. Container With Most Water](src/io/ziheng/array/leetcode/ContainerWithMostWater.java)

- [LeetCode 905. Sort Array By Parity](src/io/ziheng/array/leetcode/SortArrayByParity.java)

- [LeetCode 922. Sort Array By Parity II](src/io/ziheng/array/leetcode/SortArrayByParityII.java)

### Stack

- [LeetCode 20. Valid Parentheses](./src/io/ziheng/stack/leetcode/ValidParentheses.java)

- [LeetCode 1047. Remove All Adjacent Duplicates In String](./src/io/ziheng/stack/leetcode/RemoveAllAdjacentDuplicatesInString.java)

### Tree

- [LeetCode 112. Path Sum](./src/io/ziheng/tree/leetcode/PathSum.java)

- [LeetCode 113. Path Sum II](./src/io/ziheng/tree/leetcode/PathSumII.java)

- [LeetCode 437. Path Sum III](./src/io/ziheng/tree/leetcode/PathSumIII.java)

# 许可协议（License）

[CC BY-NC-SA 4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/)

<!--
- 时刻学习
- 积极主动
- 事事有回复
- 慎重承诺，使命必达
-->
<!-- EOF -->