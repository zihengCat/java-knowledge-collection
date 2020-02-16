# 图（Graph）的基本表示

...

# 图的基本表示：邻接矩阵（Adjacency Matrix）

...

![1-AdjacencyMatrix][1-AdjacencyMatrix]

> 图：图（Graph）邻接矩阵表示法

# 图的基本表示：邻接表（Adjacency List）

...

![2-AdjacencyList][2-AdjacencyList]

> 图：图（Graph）邻接表表示法

| 查找表 | 查找顶点 | 遍历顺序 |
| :---- | :------ | :------ |
| 动态数组 | `O(V)` | 插入顺序 |
| 链表 | `O(V)` | 插入顺序 |
| 平衡树 | `O(logV)` | 自然顺序 |
| 哈希表 | `O(1)` | 无序 |

> 图：图（Graph）邻接表查找表比较

[1-AdjacencyMatrix]: ../../images/DataStructuresAndAlgorithms-GraphRepresentations-1-AdjacencyMatrix.png

[2-AdjacencyList]: ../../images/DataStructuresAndAlgorithms-GraphRepresentations-2-AdjacencyList.png

<!-- EOF -->