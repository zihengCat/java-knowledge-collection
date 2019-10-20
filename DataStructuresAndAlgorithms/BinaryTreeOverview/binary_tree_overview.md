# 二叉树（Binary Tree）基本定义

二叉树（Binary Tree）是 *n (n >= 0)* 个结点构成的有限数据集合。*n = 0* 时称为空二叉树。在任意一棵非二叉空树中：由一个根结点和两棵互不相交的、分别为根结点左子树、右子树的二叉树组成。

![1-BinaryTreeStructure][1-BinaryTreeStructure]

> 图：二叉树（Binary Tree）结构

显然，二叉树是树型数据结构的一个特例，二叉树的度最大为 2 ，且具有以下特点：

- 二叉树任何结点至多有两个子结点（树的度最大为 2 ）。

- 二叉树是有序树，左、右子树有序，不能随意颠倒。

# 二叉树（Binary Tree）基本性质

- 非空二叉树的叶子结点数目等于其度为 2 的结点数目加 1 。

- 非空二叉树的第 *k (k >= 1)* 层至多有 *2^(k-1)* 个结点。

- 高度为 *h (h >= 1)* 的二叉树至多有 *2^h - 1* 个结点。

# 几种特殊二叉树

数据组织结构的差异会导致二叉树有各式各样的形态，下面简单介绍几种特殊的二叉树。

## 斜二叉树（Skewed Binary Tree）

只有左子节点或只有右子节点的二叉树被称为斜二叉树（Skewed Binary Tree）。

![2-SkewedBinaryTreeStructure][2-SkewedBinaryTreeStructure]

> 图：斜二叉树（Skewed Binary Tree）结构

斜二叉树特点：

- 树的度为 1 。

- 树结点只存在左子节点或右子节点。

## 满二叉树（Full Binary Tree）

一棵高度为 *h* 且具有 *2^h - 1* 个结点的二叉树被称为满二叉树（完美二叉树）。也就是说，在满二叉树中，每一层都含有最多的结点，所有分支节点都具有两个子结点（分支结点的度都为 2 ），所有叶子结点都位于同一层。

![3-FullBinaryTreeStructure][3-FullBinaryTreeStructure]

> 图：满二叉树（Full Binary Tree）结构

满二叉树特点：

- 二叉树结点数目达到当前树高的最大值。

- 分支结点的度都为 2 。

- 叶子结点都位于同一层。

## 完全二叉树（Complete Binary Tree）

一棵高度为 *h* 具有 *n* 个结点的二叉树，当且仅当其每个结点都与高度为 *h* 的满二叉树中的结点一一对应时，被称为完全二叉树。也就是说，在完全二叉树中，从根结点至倒数第二层都是满二叉树，最后一层可以不完全填充，但其叶子结点都靠左对齐。

![4-CompleteBinaryTreeStructure][4-CompleteBinaryTreeStructure]

> 图：完全二叉树（Complete Binary Tree）结构

完全二叉树特点：

- 叶子结点只能出现在最下两层，且最下层的叶子结点一定集中在左侧且连续。

- 若存在度为 1 的结点，则只可能有一个，且该节点只有左子结点没有右子结点。

> 注：满二叉树一定是完全二叉树，完全二叉树不一定是满二叉树。

# 二叉树（Binary Tree）存储结构

二叉树（Binary Tree）的物理存储结构可以分为：顺序存储、链式存储。

## 顺序存储结构

二叉树的顺序存储结构是指使用一组地址连续的存储空间自上而下、从左到右存储二叉树结点，再通过一些方法确定结点的逻辑关系（父子、兄弟等）。

顺序存储结构比较适用于完全二叉树，因为不会浪费额外空间。

如果使用顺序存储结构存储二叉树，二叉树中某结点的索引下标为 *i* ，其左子结点的索引下标为 *2i + 1* ，其右子结点的索引下标为 *2i + 2* ，其双亲结点的索引下标为 *(i - 1) / 2* 。

![5-CompleteBinaryTreeArray][5-CompleteBinaryTreeArray]

> 图：完全二叉树顺序（数组）存储结构

## 链式存储结构

...

[1-BinaryTreeStructure]: ../../images/DataStructuresAndAlgorithms-BinaryTreeOverview-1-BinaryTreeStructure.png

[2-SkewedBinaryTreeStructure]: ../../images/DataStructuresAndAlgorithms-BinaryTreeOverview-2-SkewedBinaryTreeStructure.png

[3-FullBinaryTreeStructure]: ../../images/DataStructuresAndAlgorithms-BinaryTreeOverview-3-FullBinaryTreeStructure.png

[4-CompleteBinaryTreeStructure]: ../../images/DataStructuresAndAlgorithms-BinaryTreeOverview-4-CompleteBinaryTreeStructure.png

[5-CompleteBinaryTreeArray]: ../../images/DataStructuresAndAlgorithms-BinaryTreeOverview-5-CompleteBinaryTreeArray.png

<!-- EOF -->
