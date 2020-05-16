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
 * 剑指 Offer 面试题 55：二叉树的深度
 *
 * 题目描述：
 * 输入一颗二叉树的根节点，求该二叉树的深度。
 *
 * 知识点：["树"]
 */
public class BinaryTreeDepth {
    public static void main(String[] args) {
        BinaryTreeDepth obj = new BinaryTreeDepth();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        TreeNode root = t1;
        System.out.println(
            obj.binaryTreeDepth(root)
        );
    }
    /**
     * 剑指 Offer 面试题 55：二叉树的深度
     *
     * @param root
     * @return int
     */
    public int binaryTreeDepth(TreeNode root) {
        // DFS
        // return binaryTreeDepthDFS(root);
        // BFS
        return binaryTreeDepthBFS(root);
    }
    /**
     * Depth First Search
     *
     * @param root
     * @return int
     */
    public int binaryTreeDepthDFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(
            binaryTreeDepth(root.left),
            binaryTreeDepth(root.right)
        );
    }
    /**
     * Breath First Search
     *
     * @param root
     * @return int
     */
    private int binaryTreeDepthBFS(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int treeDepth = 0;
        Queue<TreeNode> aQueue = new LinkedList<>();
        aQueue.offer(root);
        while (!aQueue.isEmpty()) {
            int size = aQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = aQueue.poll();
                if (node.left != null) {
                    aQueue.offer(node.left);
                }
                if (node.right != null) {
                    aQueue.offer(node.right);
                }
            }
            treeDepth++;
        }
        return treeDepth;
    }
}
/* EOF */