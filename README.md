# Java Knowledge Collection

Java 核心知识点梳理。

# 目录（TOC）

## Java Core

### 基础

- [Java 字符串](./Basic/String/string.md)

- [Java 比较器](./Basic/ComparableAndComparatorInterfaces/comparable_and_comparator_interfaces.md)

### 进阶

- [Java 动态代理](./Advanced/DynamicProxy/dynamic_proxy.md)

### JVM

- [Java 虚拟机 JVM 概览](./JVM/01.Overview/overview.md)

- [JVM 线程](./JVM/02.JVM-Threads/jvm_threads.md)

- [JVM 内存区域](./JVM/03.JVM-Memory-Areas/jvm_memory_areas.md)

- [JVM 类加载机制](./JVM/JVM-Classloading-Mechanism/jvm_classloading_mechanism.md)

### 并发

- [Java 并发编程概览](./Concurrency/01.Overview/overview.md)

- [Java 并发框架 - AQS（抽象队列同步器）](./Concurrency/AQS/abstract_queued_synchronizer.md)

- [Java 线程池](./Concurrency/ThreadPool/thread_pool.md)

### 集合容器

#### List 系列

- [Java ArrayList 源码分析](./Collections/ArrayList/arraylist.md)

- [Java LinkedList 源码分析](./Collections/LinkedList/linkedlist.md)

#### Deque 系列

- [Java ArrayDeque 源码分析](./Collections/ArrayDeque/arraydeque.md)

#### Map 系列

- [Java HashMap 源码分析](./Collections/HashMap/hashmap.md)

- [Java LinkedHashMap 源码分析](./Collections/LinkedHashMap/linkedhashmap.md)

#### Set 系列

- [Java HashSet 源码分析](./Collections/HashSet/hashset.md)

- [Java LinkedHashSet 源码分析](./Collections/LinkedHashSet/linkedhashset.md)

#### 并发容器系列

...

## Java Web

### Servlet

- [Java Servlet 概览](./Servlet/Overview/overview.md)

### Spring

...

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

- [LeetCode 017. Letter Combinations of a Phone Number]()

### 动态规划

- [斐波那契数列]()

- [背包问题]()

- [LeetCode 070. Climbing Stairs]()

- [LeetCode 343. Integer Break]()

### 数组

- [动态数组（ArrayList）数据结构及其 Java 实现](./DataStructuresAndAlgorithms/ArrayList/array_list.md)

#### 双指针

- [LeetCode 283. Move Zeroes]()

- [LeetCode 027. Remove Element]()

#### 字符串

- [LeetCode 344. Reverse String]()

### 链表

- [线性表（List）数据结构概览](./DataStructuresAndAlgorithms/ListOverview/list_overview.md)

- [LeetCode 203. 移除链表元素]()

### 栈

- [栈（Stack）数据结构概览](./DataStructuresAndAlgorithms/StackOverview/stack_overview.md)

- [数组实现栈（Java）](./DataStructuresAndAlgorithms/ArrayStack/array_stack.md)

- [链表实现栈（Java）](./DataStructuresAndAlgorithms/LinkedStack/linked_stack.md)

- [队列实现栈（Java）](./DataStructuresAndAlgorithms/QueueStack/queue_stack.md)

- [LeetCode 020. Valid Parentheses]()

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

- LeetCode 112. Path Sum

- LeetCode 113. Path Sum II

- LeetCode 437. Path Sum III

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

- LeetCode 785. Is Graph Bipartite

- LeetCode 695. Max Area of Island

- LeetCode 773. Sliding Puzzle

### 并查集

- [并查集（Union Find）数据结构概览]()

### 哈希表

- [哈希表（HashTable）概览](./DataStructuresAndAlgorithms/HashTableOverview/hash_table_overview.md)

- [哈希表数据结构 Java 实现](./DataStructuresAndAlgorithms/HashTable/hash_table.md)

- [LeetCode 001. Two Sum]()

### 位运算

- 状态压缩

## 操作系统

...

## 计算机网络

...

# 许可协议（License）

[CC BY-NC-SA 4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/)

<!-- EOF -->