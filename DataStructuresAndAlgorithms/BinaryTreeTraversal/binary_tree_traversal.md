# 二叉树遍历

二叉树的遍历是指：**按照某条搜索路径依次访问二叉树中的每个结点，使得二叉树中的每个结点均被访问一次，且仅被访问一次。**

由于二叉树是递归定义的，按照深度优先遍历（Depth-First Search，DFS）原则，遍历一棵二叉树需要决定对其根节点、左右子树的先后访问顺序，由此诞生几种不同的遍历次序：

- 前序遍历（根左右）

- 中序遍历（左根右）

- 后序遍历（左右根）

> 注：其中「序」指的是根结点的访问次序。

按照广度优先遍历（Breadth-First Search，BFS）原则，二叉树还有层序遍历法：从上至下，从左至右依次遍历二叉树每个节点。

```plain
     1
   /   \
  2     3
 / \   / \
4   5 6   7
```
> 注：二叉树实例

```java
public class TreeNode<E> {
    private E element;
    private TreeNode<E> leftNode;
    private TreeNode<E> rightNode;
    public TreeNode() {
        this(null, null, null);
    }
    public TreeNode(E element) {
        this(element, null, null);
    }
    public TreeNode(E element, TreeNode<E> leftNode, TreeNode<E> rightNode) {
        this.element = element;
        this.leftNode = leftNode;
        this.rightNode = rightNode;
    }
    public E getElement() {
        return element;
    }
    public void setElement(E element) {
        this.element = element;
    }
    public TreeNode<E> getLeftNode() {
        return leftNode;
    }
    public void setLeftNode(TreeNode<E> leftNode) {
        this.leftNode = leftNode;
    }
    public TreeNode<E> getRightNode() {
        return rightNode;
    }
    public void setRightNode(TreeNode<E> rightNode) {
        this.rightNode = rightNode;
    }
    @Override
    public String toString() {
        return String.format(
            "TreeNode{%s}", element == null ? "null" : element.toString()
        );
    }
}
/* EOF */
```
> 代码清单：二叉树结点（二叉链表）实现类

```java
import java.util.Queue;
import java.util.Deque;
import java.util.LinkedList;
import java.util.ArrayDeque;
public class BinaryTreeTraversal {
    public static <E> void visitTreeNode(TreeNode<E> node) {
        System.out.println(node);
    }
    public static <E> TreeNode<E> buildBinaryTree(E[] arr) {
        if (arr == null || arr.length == 0) {
            return null;
        }
        Queue<TreeNode<E>> queue = new ArrayDeque<TreeNode<E>>();
        int len = arr.length;
        TreeNode<E> root = new TreeNode<E>(arr[0], null, null);
        queue.offer(root);
        for (int i = 1; i < len; /* advancing i inside */) {
            TreeNode<E> node = queue.poll();
            TreeNode<E> leftNode =
                new TreeNode<E>(arr[i], null, null);
            node.setLeftNode(leftNode);
            queue.offer(leftNode);
            i++;
            if (i < len) {
                TreeNode<E> rightNode =
                    new TreeNode<E>(arr[i], null, null);
                node.setRightNode(rightNode);
                queue.offer(rightNode);
                i++;
            }
        }
        return root;
    }
}
/* EOF */
```
> 代码清单：二叉树遍历模版类

# 二叉树前序遍历（Preorder Traversal）

二叉树前序遍历：按照根结点、左子树、右子树的顺序进行遍历。

上述二叉树实例前序遍历结果为：`[1, 2, 4, 5, 3, 6, 7]`

![PreOrderTraversal][PreOrderTraversal]

> 图：二叉树前序遍历示意图

```java
public static <E> void recursivePreorderTraversal(TreeNode<E> root) {
    if (root == null) {
        return;
    }
    visitTreeNode(root);
    recursivePreorderTraversal(root.getLeftNode());
    recursivePreorderTraversal(root.getRightNode());
}
```
> 代码清单：二叉树前序遍历（递归实现）

```java
public static <E> void iterativePreorderTraversal(TreeNode<E> root) {
    if (root == null) {
        return;
    }
    Deque<TreeNode<E>> stack = new LinkedList<TreeNode<E>>();
    stack.push(root);
    while (!stack.isEmpty()) {
        TreeNode<E> node = stack.pop();
        visitTreeNode(node);
        /* 栈后进先出：先压右结点，后压左节点 */
        if (node.getRightNode() != null) {
            stack.push(node.getRightNode());
        }
        if (node.getLeftNode() != null) {
            stack.push(node.getLeftNode());
        }
    }
}
```
> 代码清单：二叉树前序遍历（迭代实现）

# 二叉树中序遍历（Inorder Traversal）

二叉树中序遍历：按照左子树、根结点、右子树的顺序进行遍历。

上述二叉树实例中序遍历结果为：`[4, 2, 5, 1, 6, 3, 7]`

![InOrderTraversal][InOrderTraversal]

> 图：二叉树中序遍历示意图

```java
public static <E> void recursiveInorderTraversal(TreeNode<E> root) {
    if (root == null) {
        return;
    }
    recursiveInorderTraversal(root.getLeftNode());
    visitTreeNode(root);
    recursiveInorderTraversal(root.getRightNode());
}
```
> 代码清单：二叉树中序遍历（递归实现）

```java
public static <E> void iterativeInorderTraversal(TreeNode<E> root) {
    if (root == null) {
        return;
    }
    Deque<TreeNode<E>> stack = new LinkedList<TreeNode<E>>();
    TreeNode<E> node = root;
    while (!stack.isEmpty() || node != null) {
        if (node != null) {
            stack.push(node);
            node = node.getLeftNode();
        } else {
            node = stack.pop();
            visitTreeNode(node);
            node = node.getRightNode();
        }
    }
}
```
> 代码清单：二叉树中序遍历（迭代实现）

# 二叉树后序遍历（Postorder Traversal）

![PostOrderTraversal][PostOrderTraversal]

> 图：二叉树后序遍历示意图

# 二叉树层序（遍历（Level Order Traversal）

![LevelOrderTraversal][LevelOrderTraversal]

> 图：二叉树层序遍历示意图

```java

```
> 代码清单：二叉树层序遍历（Level Order Traversal）


[PreOrderTraversal]: ../../images/DataStructuresAndAlgorithms-BinaryTreeTraversal-N-PreOrderTraversal.png

[InOrderTraversal]: ../../images/DataStructuresAndAlgorithms-BinaryTreeTraversal-N-InOrderTraversal.png

[PostOrderTraversal]: ../../images/DataStructuresAndAlgorithms-BinaryTreeTraversal-N-PostOrderTraversal.png

[LevelOrderTraversal]: ../../images/DataStructuresAndAlgorithms-BinaryTreeTraversal-N-LevelOrderTraversal.png

<!-- EOF -->
