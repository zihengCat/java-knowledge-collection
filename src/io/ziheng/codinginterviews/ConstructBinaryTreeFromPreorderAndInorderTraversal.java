package io.ziheng.codinginterviews;
/**
 * 二叉树节点
 */
class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
/**
 * 剑指 Offer 面试题 06：重建二叉树（前序、中序）
 *
 * 题目描述：
 * 输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如：
 * - 前序遍历序列 {1,2,4,7,3,5,6,8}
 * - 中序遍历序列 {4,7,2,1,5,3,8,6}
 * 重建这颗二叉树并返回。
 *
 * 知识点：["树"]
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public static void main(String[] args) {
        // ...
    }
    /**
     * 剑指 Offer 面试题 06：重建二叉树（前序、中序）
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     *
     * @param pre 前序遍历
     * @param in 中序遍历
     * @return TreeNode
     */
    public TreeNode constructBinaryTree(int[] pre, int[] in) {
        return constructBinaryTree0(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }
    private TreeNode constructBinaryTree0(
        int[] pre, int preStart, int preEnd,
        int[] in, int inStart, int inEnd) {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }
        TreeNode root = new TreeNode(pre[preStart]);
        int inRoot = 0;
        for (int i = inStart; i <= inEnd; i++) {
            if (in[i] == pre[preStart]) {
                inRoot = i;
            }
        }
        int numsLeft = inRoot - inStart;
        root.left = constructBinaryTree0(
            pre, preStart + 1, preStart + numsLeft,
            in, inStart, inRoot - 1
        );
        root.right = constructBinaryTree0(
            pre, preStart + numsLeft + 1, preEnd,
            in, inRoot + 1, inEnd
        );
        return root;
    }
}
/* EOF */