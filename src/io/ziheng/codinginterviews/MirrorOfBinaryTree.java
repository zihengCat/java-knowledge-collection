package io.ziheng.codinginterviews;

import java.util.LinkedList;
import java.util.Queue;

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
 * 剑指 Offer 面试题 27：二叉树的镜像
 *
 * 题目描述：
 * 输入一棵二叉树，将其变换为二叉树的镜像。
 * 源二叉树：
 *          8
 *         /  \
 *        6   10
 *       / \  / \
 *      5  7 9 11
 * 镜像二叉树：
 *          8
 *         /  \
 *        10   6
 *       / \  / \
 *      11 9 7  5
 *
 * 知识点：["树"]
 */
public class MirrorOfBinaryTree {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        MirrorOfBinaryTree obj = new MirrorOfBinaryTree();
        TreeNode root = buildTree();
        printTree(root);
        obj.mirrorOfBinaryTree(root);
        printTree(root);
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
        TreeNode t3 = new TreeNode(10);
        t1.left = t2;
        t1.right = t3;
        TreeNode t4 = new TreeNode(5);
        TreeNode t5 = new TreeNode(7);
        t2.left = t4;
        t2.right = t5;
        TreeNode t6 = new TreeNode(9);
        TreeNode t7 = new TreeNode(11);
        t3.left = t6;
        t3.right = t7;
        TreeNode root = t1;
        return root;
    }
    /**
     * 剑指 Offer 面试题 27：二叉树的镜像
     *
     * @param root
     * @return void
     */
    public void mirrorOfBinaryTree(TreeNode root) {
        // mirrorOfBinaryTreeRecursively(root);
        mirrorOfBinaryTreeNonRecursive(root);
    }
    /**
     * 剑指 Offer 面试题 27：二叉树的镜像 -> 递归
     * 
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return void
     */
    private void mirrorOfBinaryTreeRecursively(TreeNode root) {
        if (root == null) {
            return;
        }
        mirrorOfBinaryTreeRecursively(root.left);
        mirrorOfBinaryTreeRecursively(root.right);
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
    }
    /**
     * 剑指 Offer 面试题 27：二叉树的镜像 -> 非递归
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return void
     */
    private void mirrorOfBinaryTreeNonRecursive(TreeNode root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode> aQueue = new LinkedList<>();
        aQueue.offer(root);
        while (!aQueue.isEmpty()) {
            // mirror TreeNode
            TreeNode rootNode = aQueue.poll();
            TreeNode temp = rootNode.left;
            rootNode.left = rootNode.right;
            rootNode.right = temp;
            if (rootNode.left != null) {
                aQueue.offer(rootNode.left);
            }
            if (rootNode.right != null) {
                aQueue.offer(rootNode.right);
            }
        }
    }
}
/* EOF */