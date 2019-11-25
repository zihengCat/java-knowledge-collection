# 二叉树最大深度

二叉树的最大深度是指：**从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度即为树的深度。**

给出一颗二叉树，求该二叉树的最大深度。

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return leftDepth >= rightDepth ? leftDepth + 1 : rightDepth + 1;
    }
}
/* EOF */
```
> 代码清单：求二叉树最大深度（递归）

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
import java.util.Queue;
import java.util.LinkedList;
public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int treeDepth = 0;
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            treeDepth++;
            for (int i = 0, n = queue.size(); i < n; ++i) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return treeDepth;
    }
}
/* EOF */
```
> 代码清单：求二叉树最大深度（迭代）

<!-- EOF -->
