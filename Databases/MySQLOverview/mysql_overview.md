# MySQL 简介


![Databases-MySQLOverview-1-MySQLArchitecture][Databases-MySQLOverview-1-MySQLArchitecture]

> 图：MySQL 架构图

# 索引模块

## 为什么要使用索引

如果没有索引，查询数据的方式：全表扫描。

## 索引利用什么数据结构

一切可以加快查询效率的数据结构

### 二分搜索树（BST）

单个树节点只存放两个孩子，数据库记录条数多时，树高太高。

### 平衡多路查找树（B树，BTree）

...

### B+树（B+Tree）

B+树是B树的变体，新增了新特性。

- 查询完整从根节点走到叶子节点，非叶子节点仅用来存储索引，不存储实际数据。
- 叶子节点有链表指针连接，非常有利于范围查询。

### 哈希表（HashTable）

- 单条记录查询效率高
- 仅能满足`=`、`IN`等值查询，不能满足范围查询
- 无法被用来做数据记录的排序操作
- 无法利用部分索引键（如：组合索引键中的一个）优化查询
- 哈希冲突严重的情况下性能低下

### 位图（BitMap）

- 适用于仅有几个枚举值的字段
- 锁粒度大（增、删、改操作会锁住与字段值相同的所有记录）

## 密集索引与稀疏索引

## 联合索引最左匹配原则

成因：MySQL 以组合索引键中的最左键排序建立索引树。

- 等值查询（`=`、`IN`）时，SQL 查询优化器会将语句优化为索引可识别的形式。

# 锁模块

## MyISAM 与 InnoDB 锁机制区别

- MyISAM 只有表级锁，没有行级锁

- InnoDB 既支持表级锁，也支持行级锁，默认使用行级锁。








[Databases-MySQLOverview-1-MySQLArchitecture]: ../../images/Databases-MySQLOverview-1-MySQLArchitecture.jpg

<!-- EOF -->