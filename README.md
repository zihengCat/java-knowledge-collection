# Java Knowledge Collection

Java 核心知识点梳理。

# 目录（Table of Contents）

<!-- TOC -->

  * [Java Core](#Java-Core)
    * [Java 基础](#Java-基础)
    * [Java 进阶](#Java-进阶)
    * [JVM 虚拟机](#JVM-虚拟机)
    * [Java 并发](#Java-并发)
    * [Java 集合](#Java-集合)
  * [Java 框架](#Java-框架)
    * [Kafka](#Kafka)
  * [设计模式](#设计模式)
  * [数据库](#数据库)
  * [操作系统](#操作系统)
  * [计算机网络](#计算机网络)
  * [计算机组成原理](#计算机组成原理)
  * [数据结构与算法](#数据结构与算法)
  * [剑指 Offer](#剑指-Offer)
  * [LeetCode 题解](#LeetCode-题解)
    * [Array](#Array)
    * [String](#String)
    * [Linked List](#Linked-List)
    * [Stack](#Stack)
    * [Queue](#Queue)
    * [Heap](#Heap)
    * [Hash Table](#Hash-Table)
    * [Tree](#Tree)
    * [Divide and Conquer](#Divide-and-Conquer)
    * [Backtracking](#Backtracking)
    * [Dynamic Programming](#Dynamic-Programming)
    * [Bit Manipulation](#Bit-Manipulation)

<!-- TOC -->

## Java Core

### Java 基础

- [Java 字符串](Basic/String/string.md)

- [Java 比较器](Basic/ComparableAndComparatorInterfaces/comparable_and_comparator_interfaces.md)

- [Java `==`操作符、`equals()`方法、`hashCode()`方法](Basic/HashCodeAndEquals/hashcode_and_equals.md)

### Java 进阶

- [Java 动态代理](./Advanced/DynamicProxy/dynamic_proxy.md)

### JVM 虚拟机

- [Java 虚拟机 JVM 概览](JVM/01.Overview/overview.md)

- [JVM 线程](JVM/02.JVM-Threads/jvm_threads.md)

- [JVM 内存区域](JVM/03.JVM-Memory-Areas/jvm_memory_areas.md)

- [JVM 类加载机制](JVM/JVMClassloadingMechanism/jvm_classloading_mechanism.md)

- [JVM 垃圾回收](JVM/JavaGarbageCollection/java_garbage_collection.md)

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

### Kafka

...

## 设计模式

- [设计模式概述](DesignPatterns/DesignPatternsOverview/design_patterns_overview.md)

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

- 操作系统（OS）概述

- 进程管理

- 内存管理

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

## 剑指 Offer

- 剑指 Offer 面试题 01：赋值运算符函数

- 剑指 Offer 面试题 02：实现单例模式

- [剑指 Offer 面试题 03：二维数组中的查找](src/io/ziheng/codinginterviews/LookupInATwoDimensionalArray.java)

- [剑指 Offer 面试题 04：替换空格](src/io/ziheng/codinginterviews/ReplaceSpace.java)

- [剑指 Offer 面试题 05：从尾到头打印链表](src/io/ziheng/codinginterviews/PrintLinkedListFromTailToHead.java)

- [剑指 Offer 面试题 06：重建二叉树（前序、中序）](src/io/ziheng/codinginterviews/ConstructBinaryTreeFromPreorderAndInorderTraversal.java)

- [剑指 Offer 面试题 07：使用栈实现队列](src/io/ziheng/codinginterviews/QueueUsingStacks.java)

- [剑指 Offer 面试题 10-1：斐波那契数列](src/io/ziheng/codinginterviews/FibonacciSequence.java)

- [剑指 Offer 面试题 10-2：青蛙跳台阶](src/io/ziheng/codinginterviews/JumpingStairs.java)

- [剑指 Offer 面试题 15：二进制中 1 的个数](src/io/ziheng/codinginterviews/NumberOfOneInBinary.java)

- [剑指 Offer 面试题 18：删除链表节点](src/io/ziheng/codinginterviews/DeleteNodeInLinkedList.java)

- [剑指 Offer 面试题 22：链表倒数第 k 个节点](src/io/ziheng/codinginterviews/KthNodeFromTheEndOfLinkedList.java)

- [剑指 Offer 面试题 23：链表中环的入口节点](src/io/ziheng/codinginterviews/EntryNodeOfRingInLinkedList.java)

- [剑指 Offer 面试题 24：反转链表](src/io/ziheng/codinginterviews/ReverseLinkedList.java)

- [剑指 Offer 面试题 25：合并两个排序的链表](src/io/ziheng/codinginterviews/MergeTwoSortedLinkedLists.java)

- [剑指 Offer 面试题 26：树的子结构](src/io/ziheng/codinginterviews/SubstructureOfTree.java)

- [剑指 Offer 面试题 27：二叉树的镜像](src/io/ziheng/codinginterviews/MirrorOfBinaryTree.java)

- [剑指 Offer 面试题 28：对称的二叉树](src/io/ziheng/codinginterviews/IsSymmetricalBinaryTree.java)

- [剑指 Offer 面试题 29：顺时针打印矩阵](src/io/ziheng/codinginterviews/ClockwisePrintMatrix.java)

- [剑指 Offer 面试题 30：包含 min() 函数的栈](src/io/ziheng/codinginterviews/StackWithMinFunction.java)

- [剑指 Offer 面试题 31：栈的压入、弹出序列](src/io/ziheng/codinginterviews/StackPushPopSequence.java)

- [剑指 Offer 面试题 32：从上到下打印二叉树](src/io/ziheng/codinginterviews/PrintBinaryTreeFromTopToBottom.java)

- [剑指 Offer 面试题 38：字符串的排列](src/io/ziheng/codinginterviews/StringPermutation.java)

- [剑指 Offer 面试题 39：数组中出现次数超过一半的数字](src/io/ziheng/codinginterviews/MoreThanHalfNumInArray.java)

- [剑指 Offer 面试题 40：最小的 k 个数](src/io/ziheng/codinginterviews/MinimumKNumbers.java)

- [剑指 Offer 面试题 47：礼物的最大价值](src/io/ziheng/codinginterviews/MaximumValueOfGifts.java)

- [剑指 Offer 面试题 52：两个链表的第一个公共节点](src/io/ziheng/codinginterviews/IntersectionNodeOfTwoLinkedLists.java)

- [剑指 Offer 面试题 54：二叉搜索树第 k 大节点](src/io/ziheng/codinginterviews/KthNodeInBinarySearchTree.java)

- [剑指 Offer 面试题 55-1：二叉树的深度](src/io/ziheng/codinginterviews/BinaryTreeDepth.java)

- [剑指 Offer 面试题 55-2：判断平衡二叉树](src/io/ziheng/codinginterviews/IsBalancedBinaryTree.java)

- [剑指 Offer 面试题 56：数组中数字出现次数](src/io/ziheng/codinginterviews/NumberAppearsInArray.java)

- [剑指 Offer 面试题 57-1：和为 s 的两数](src/io/ziheng/codinginterviews/FindTwoNumbersWithSum.java)

- [剑指 Offer 面试题 57-2：和为 s 的连续正整数序列]()

- [剑指 Offer 面试题 58-1：翻转字符串单词顺序](src/io/ziheng/codinginterviews/ReverseWordsInSentence.java)

- [剑指 Offer 面试题 58-2：左旋转字符串](src/io/ziheng/codinginterviews/LeftRotateString.java)

- [剑指 Offer 面试题 59：滑动窗口的最大值](src/io/ziheng/codinginterviews/MaximumValueOfSlidingWindow.java)

- [剑指 Offer 面试题 68：二叉树两节点最小公共祖先](src/io/ziheng/codinginterviews/LowestCommonAncestorOfBinaryTree.java)

## LeetCode 题解

### Array

- [LeetCode 26. Remove Duplicates from Sorted Array](src/io/ziheng/array/leetcode/RemoveDuplicatesFromSortedArray.java)

- [LeetCode 34. Find First and Last Position of Element in Sorted Array](src/io/ziheng/array/leetcode/FindFirstAndLastPositionOfElementInSortedArray.java)

- [LeetCode 75. Sort Colors](src/io/ziheng/array/leetcode/SortColors.java)

- [LeetCode 35. Search Insert Position](src/io/ziheng/array/leetcode/SearchInsertPosition.java)

- [LeetCode 283. Move Zeroes](src/io/ziheng/array/leetcode/MoveZeroes.java)

- [LeetCode 289. Game of Life](src/io/ziheng/array/leetcode/GameOfLife.java)

- [LeetCode 11. Container With Most Water](src/io/ziheng/array/leetcode/ContainerWithMostWater.java)

- [LeetCode 905. Sort Array By Parity](src/io/ziheng/array/leetcode/SortArrayByParity.java)

- [LeetCode 922. Sort Array By Parity II](src/io/ziheng/array/leetcode/SortArrayByParityII.java)

- [LeetCode 88. Merge Sorted Array](src/io/ziheng/array/leetcode/MergeSortedArray.java)

- [LeetCode 56. Merge Intervals](src/io/ziheng/array/leetcode/MergeIntervals.java)

- [LeetCode 581. Shortest Unsorted Continuous Subarray](src/io/ziheng/array/leetcode/ShortestUnsortedContinuousSubarray.java)

- [LeetCode 1380. Lucky Numbers in a Matrix](src/io/ziheng/array/leetcode/LuckyNumbersInAMatrix.java)

- [LeetCode 1351. Count Negative Numbers in a Sorted Matrix](src/io/ziheng/array/leetcode/CountNegativeNumbersInASortedMatrix.java)

### String

- [LeetCode 344. Reverse String](src/io/ziheng/string/leetcode/ReverseString.java)

- [LeetCode 541. Reverse String II](src/io/ziheng/string/leetcode/ReverseStringII.java)

### Linked List

- [LeetCode 206. Reverse Linked List](src/io/ziheng/list/leetcode/ReverseLinkedList.java)

- [LeetCode 92. Reverse Linked List II](src/io/ziheng/list/leetcode/ReverseLinkedListII.java)

- [LeetCode 160. Intersection of Two Linked Lists](src/io/ziheng/list/leetcode/IntersectionOfTwoLinkedLists.java)

- [LeetCode 141. Linked List Cycle](src/io/ziheng/list/leetcode/LinkedListCycle.java)

- [LeetCode 142. Linked List Cycle II](src/io/ziheng/list/leetcode/LinkedListCycleII.java)

- [LeetCode 86. Partition List](src/io/ziheng/list/leetcode/PartitionList.java)

- [LeetCode 138. Copy List with Random Pointer](src/io/ziheng/list/leetcode/CopyListWithRandomPointer.java)

- [LeetCode 21. Merge Two Sorted Lists](src/io/ziheng/list/leetcode/MergeTwoSortedLists.java)

- [LeetCode 23. Merge k Sorted Lists](src/io/ziheng/list/leetcode/MergeKSortedLists.java)

### Stack

- [LeetCode 20. Valid Parentheses](src/io/ziheng/stack/leetcode/ValidParentheses.java)

- [LeetCode 155. Min Stack](src/io/ziheng/stack/leetcode/MinStack.java)

- [LeetCode 1047. Remove All Adjacent Duplicates In String](src/io/ziheng/stack/leetcode/RemoveAllAdjacentDuplicatesInString.java)

- [LeetCode 232. Implement Queue using Stacks](src/io/ziheng/stack/leetcode/ImplementQueueUsingStacks.java)

- [LeetCode 1190. Reverse Substrings Between Each Pair of Parentheses](src/io/ziheng/stack/leetcode/ReverseSubstringsBetweenEachPairOfParentheses.java)

- [LeetCode 1381. Design a Stack With Increment Operation](src/io/ziheng/stack/leetcode/DesignAStackWithIncrementOperation.java)

### Queue

- [LeetCode 225. Implement Stack using Queues](src/io/ziheng/queue/leetcode/ImplementStackUsingQueues.java)

### Heap

- [LeetCode 347. Top K Frequent Elements](src/io/ziheng/heap/leetcode/TopKFrequentElements.java)

- [LeetCode 692. Top K Frequent Words](src/io/ziheng/heap/leetcode/TopKFrequentWords.java)

- [LeetCode 704. Kth Largest Element in a Stream](src/io/ziheng/heap/leetcode/KthLargestElementInAStream.java)

- [LeetCode 215. Kth Largest Element in an Array](src/io/ziheng/heap/leetcode/KthLargestElementInAnArray.java)

### Hash Table

- [LeetCode 202. Happy Number](src/io/ziheng/hashtable/leetcode/HappyNumber.java)

- [LeetCode 242. Valid Anagram](src/io/ziheng/hashtable/leetcode/ValidAnagram.java)

- [LeetCode 49. Group Anagrams](src/io/ziheng/hashtable/leetcode/GroupAnagrams.java)

- [LeetCode 383. Ransom Note](src/io/ziheng/hashtable/leetcode/RansomNote.java)

- [LeetCode 169. Majority Element](src/io/ziheng/hashtable/leetcode/MajorityElement.java)

- [LeetCode 229. Majority Element II](src/io/ziheng/hashtable/leetcode/MajorityElementII.java)

- [LeetCode 771. Jewels and Stones](src/io/ziheng/hashtable/leetcode/JewelsAndStones.java)

- [LeetCode 811. Subdomain Visit Count](src/io/ziheng/hashtable/leetcode/SubdomainVisitCount.java)

- [LeetCode 349. Intersection of Two Arrays](src/io/ziheng/hashtable/leetcode/IntersectionOfTwoArrays.java)

- [LeetCode 350. Intersection of Two Arrays II](src/io/ziheng/hashtable/leetcode/IntersectionOfTwoArraysII.java)

- [LeetCode 599. Minimum Index Sum of Two Lists](src/io/ziheng/hashtable/leetcode/MinimumIndexSumOfTwoLists.java)

- [LeetCode 1160. Find Words That Can Be Formed by Characters](src/io/ziheng/hashtable/leetcode/FindWordsThatCanBeFormedByCharacters.java)

- [LeetCode 1346. Check If N and Its Double Exist](src/io/ziheng/hashtable/leetcode/CheckIfNAndItsDoubleExist.java)

### Tree

- [LeetCode 112. Path Sum](src/io/ziheng/tree/leetcode/PathSum.java)

- [LeetCode 113. Path Sum II](src/io/ziheng/tree/leetcode/PathSumII.java)

- [LeetCode 437. Path Sum III](src/io/ziheng/tree/leetcode/PathSumIII.java)

- [LeetCode 111. Minimum Depth of Binary Tree](src/io/ziheng/tree/leetcode/MinimumDepthOfBinaryTree.java)

- [LeetCode 208. Implement Trie (Prefix Tree)](src/io/ziheng/tree/leetcode/ImplementTriePrefixTree.java)

- [LeetCode 1305. All Elements in Two Binary Search Trees](src/io/ziheng/tree/leetcode/AllElementsInTwoBinarySearchTrees.java)

- [LeetCode 1302. Deepest Leaves Sum](src/io/ziheng/tree/leetcode/DeepestLeavesSum.java)

### Divide and Conquer

- [LeetCode 50. Pow(x, n)](src/io/ziheng/recursion/divideandconquer/leetcode/Pow.java)

### Backtracking

- [LeetCode 51. N-Queens](src/io/ziheng/recursion/backtracking/leetcode/NQueens.java)

- [LeetCode 78. Subsets](src/io/ziheng/recursion/backtracking/leetcode/Subsets.java)

- [LeetCode 79. Word Search](src/io/ziheng/recursion/backtracking/leetcode/WordSearch.java)

- [LeetCode 17. Letter Combinations of a Phone Number](src/io/ziheng/recursion/backtracking/leetcode/LetterCombinationsOfAPhoneNumber.java)

### Dynamic Programming

- [LeetCode 70. Climbing Stairs](src/io/ziheng/recursion/dynamicprogramming/leetcode/ClimbingStairs.java)

- [LeetCode 494. Target Sum](src/io/ziheng/recursion/dynamicprogramming/leetcode/TargetSum.java)

- [LeetCode 1143. Longest Common Subsequence](src/io/ziheng/recursion/dynamicprogramming/leetcode/LongestCommonSubsequence.java)

- [LeetCode 53. Maximum Subarray](src/io/ziheng/recursion/dynamicprogramming/leetcode/MaximumSubarray.java)

- [LeetCode 198. House Robber](src/io/ziheng/recursion/dynamicprogramming/leetcode/HouseRobber.java)

- [LeetCode 62. Unique Paths](src/io/ziheng/recursion/dynamicprogramming/leetcode/UniquePaths.java)

- [LeetCode 63. Unique Paths II](src/io/ziheng/recursion/dynamicprogramming/leetcode/UniquePathsII.java)

- [LeetCode 72. Edit Distance](src/io/ziheng/recursion/dynamicprogramming/leetcode/EditDistance.java)

### Bit Manipulation

- [LeetCode 191. Number of 1 Bits](src/io/ziheng/bitmanipulation/leetcode/NumberOfOneBits.java)

- [LeetCode 338. Counting Bits](src/io/ziheng/bitmanipulation/leetcode/CountingBits.java)

- [LeetCode 1356. Sort Integers by The Number of 1 Bits](src/io/ziheng/bitmanipulation/leetcode/SortIntegersByTheNumberOf1Bits.java)

# 许可协议（License）

[CC BY-NC-SA 4.0](https://creativecommons.org/licenses/by-nc-sa/4.0/)

<!--
- 时刻学习
- 积极主动
- 事事有回复
- 慎重承诺，使命必达
-->
<!-- EOF -->