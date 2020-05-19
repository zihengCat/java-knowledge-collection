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
 * 剑指 Offer 面试题 68：二叉树两节点最小公共祖先
 *
 * 题目描述：
 * 输入一棵二叉搜索树根节点与二叉树树中两个子节点，两节点的最小公共祖先。
 *
 * 知识点：["树"]
 */
public class LowestCommonAncestorOfBinaryTree {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        LowestCommonAncestorOfBinaryTree obj = new LowestCommonAncestorOfBinaryTree();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t3.left = t4;
        t3.right = t5;
        t1.left = t2;
        t1.right = t3;
        TreeNode root = t1;
        System.out.println(
            obj.lowestCommonAncestor(root, t4, t2).val
        );
    }
    /**
     * 剑指 Offer 面试题 68：二叉树两节点最小公共祖先
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param rootNode
     * @param pNode
     * @param qNode
     * @return TreeNode
     */
    public TreeNode lowestCommonAncestor(
        TreeNode rootNode, TreeNode pNode, TreeNode qNode) {
        if (rootNode == null
            || rootNode == pNode
            || rootNode == qNode) {
            return rootNode;
        }
        TreeNode leftNode = lowestCommonAncestor(rootNode.left, pNode, qNode);
        TreeNode rightNode = lowestCommonAncestor(rootNode.right, pNode, qNode);
        return leftNode == null ? rightNode
            : rightNode == null ? leftNode : rootNode;
    }
}
/* EOF */