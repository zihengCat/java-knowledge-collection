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
 * 剑指 Offer 面试题 55-2：判断平衡二叉树
 *
 * 题目描述：
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 *
 * 知识点：["树"]
 */
public class IsBalancedBinaryTree {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        IsBalancedBinaryTree obj = new IsBalancedBinaryTree();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left = t2;
        t1.right = t3;
        TreeNode root = t1;
        TreeNode root2 = new TreeNode(22);
        root2.left = t1;
        System.out.println(
            obj.isBalanced(root)
        );
        System.out.println(
            obj.isBalanced(root2)
        );
    }
    /**
     * 剑指 Offer 面试题 55-2：判断平衡二叉树
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n)
     *
     * @param root
     * @return boolean
     */
    public boolean isBalanced(TreeNode root) {
        if (root == null) {
            return true;
        }
        if (Math.abs(treeDepth(root.left) - treeDepth(root.right)) > 1) {
            return false;
        }
        return isBalanced(root.left) && isBalanced(root.right);
    }
    /**
     * 计算树的高度。
     *
     * @param root
     * @return int
     */
    private int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(treeDepth(root.left), treeDepth(root.right));
    }
}
/* EOF */