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
 * 剑指 Offer 面试题 26：树的子结构
 *
 * 题目描述：
 * 输入两棵二叉树 A，B，判断 B 是不是 A 的子结构。
 * 注：我们约定空树不是任意一个树的子结构。
 *
 * 知识点：["树"]
 */
public class SubstructureOfTree {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        SubstructureOfTree obj = new SubstructureOfTree();
        TreeNode rootA = new TreeNode(0);
        TreeNode rootB = new TreeNode(3);
        int[] arr = new int[]{1, 2, 3, };
        for (int n : arr) {
            rootA = insertIntoBST(rootA, n);
        }
        System.out.println(
            obj.hasSubtree(rootA, rootB)
        );
    }
    private static TreeNode insertIntoBST(TreeNode root, int n) {
        if (root == null) {
            return new TreeNode(n);
        }
        if (n > root.val) {
            root.right = insertIntoBST(root.right, n);
        } else if (n < root.val) {
            root.left = insertIntoBST(root.left, n);
        }
        return root;
    }
    public boolean hasSubtree(TreeNode rootA, TreeNode rootB) {
        if (rootA == null || rootB == null) {
            return false;
        }
        if (isSameTree(rootA, rootB)) {
            return true;
        }
        return hasSubtree(rootA.left, rootB)
            || hasSubtree(rootA.right, rootB);
    }
    private boolean isSameTree(TreeNode rootA, TreeNode rootB) {
        if (rootA == null && rootB == null) {
            return true;
        }
        if (rootA == null || rootB == null) {
            return false;
        }
        if (rootA.val != rootB.val) {
            return false;
        }
        return isSameTree(rootA.left, rootB.left)
            && isSameTree(rootA.right, rootB.right);
    }
}
/* EOF */