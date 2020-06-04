package io.ziheng.codinginterviews;

import java.util.Queue;
import java.util.LinkedList;

/**
 * 二叉树节点
 */
class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int val) {
        this.val = val;
        this.left = null;;
        this.right = null;;
    }
}

/**
 * 剑指 Offer 面试题 28：对称的二叉树
 *
 * 题目描述：
 * 输入一棵二叉树，判断其是否是对称的二叉树。
 * 注：如果一颗二叉树与此二叉树的镜像相同，则定义其对称。
 * 
 * 对称二叉树：
 *          8
 *         /  \
 *        6    6
 *       / \  / \
 *      5  7 7   5
 * 非对称二叉树：
 *          8
 *         /  \
 *        6    9
 *       / \  / \
 *      5   7 7  5
 *
 * 知识点：["树"]
 */
public class IsSymmetricalBinaryTree {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        IsSymmetricalBinaryTree obj = new IsSymmetricalBinaryTree();
        TreeNode root = buildTree();
        printTree(root);
        System.out.println(
            obj.isSymmetricalBinaryTree(root)
        );
    }
    /**
     * 剑指 Offer 面试题 28：对称的二叉树
     *
     * @param root
     * @return boolean
     */
    public boolean isSymmetricalBinaryTree(TreeNode root) {
        return isSymmetrical(root, root);
    }
    /**
     * 剑指 Offer 面试题 28：对称的二叉树 -> 递归
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param aTree
     * @param bTree
     * @return boolean
     */
    private boolean isSymmetrical(TreeNode aTree, TreeNode bTree) {
        if (aTree == null && bTree == null) {
            return true;
        }
        if (aTree == null || bTree == null) {
            return false;
        }
        if (aTree.val != bTree.val) {
            return false;
        }
        return isSymmetrical(aTree.left, bTree.right)
            && isSymmetrical(aTree.right, bTree.left);
    }
    public static void printTree(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> aQueue = new LinkedList<>();
        aQueue.offer(root);
        while (!aQueue.isEmpty()) {
            int size = aQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = aQueue.poll();
                System.out.printf("%d, ", currentNode.val);
                if (currentNode.left != null) {
                    aQueue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    aQueue.offer(currentNode.right);
                }
            }
            System.out.println();
        }
    }
    public static TreeNode buildTree() {
        TreeNode t1 = new TreeNode(8);
        TreeNode t2 = new TreeNode(6);
        TreeNode t3 = new TreeNode(6);
        t1.left = t2;
        t1.right = t3;
        TreeNode t4 = new TreeNode(5);
        TreeNode t5 = new TreeNode(7);
        t2.left = t4;
        t2.right = t5;
        TreeNode t6 = new TreeNode(7);
        TreeNode t7 = new TreeNode(5);
        t3.left = t6;
        t3.right = t7;
        TreeNode root = t1;
        return root;
    }
}
/* EOF */