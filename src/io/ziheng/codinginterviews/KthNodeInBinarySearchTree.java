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
        this.left = null;;
        this.right = null;;
    }
}

/**
 * 剑指 Offer 面试题 54：二叉搜索树第 k 大节点
 *
 * 题目描述：
 * 输入一棵二叉搜索树，找出其中第 k 大的节点。
 *
 * 知识点：["树"]
 */
public class KthNodeInBinarySearchTree {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        KthNodeInBinarySearchTree obj = new KthNodeInBinarySearchTree();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t2.left = t1;
        t2.right = t3;
        TreeNode root = t2;
        System.out.println(
            obj.kthNodeInBinarySearchTree(root, 5)
        );
    }
    private int kth;
    private TreeNode resultNode;
    /**
     * 剑指 Offer 面试题 54：二叉搜索树第 k 大节点
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param root
     * @param k
     * @return TreeNode
     */
    public TreeNode kthNodeInBinarySearchTree(TreeNode root, int k) {
        this.kth = k;
        this.resultNode = null;
        inOrderTraversal(root);
        return resultNode;
    }
    /**
     * 二叉搜索树中序遍历 -> 排序
     *
     * @param root
     * @return void
     */
    private void inOrderTraversal(TreeNode root) {
        if (root == null) {
            return;
        }
        // Left, Root, Rigth
        inOrderTraversal(root.left);
        kth--;
        if (kth == 0) {
            resultNode = root;
        }
        inOrderTraversal(root.right);
    }
}
/* EOF */