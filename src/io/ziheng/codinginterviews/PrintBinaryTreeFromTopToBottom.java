package io.ziheng.codinginterviews;

import java.util.List;
import java.util.Queue;
import java.util.Collections;
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
 * 剑指 Offer 面试题 32：从上到下打印二叉树
 *
 * 题目描述：...
 *
 * 知识点：["树"]
 */
public class PrintBinaryTreeFromTopToBottom {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        PrintBinaryTreeFromTopToBottom obj = new PrintBinaryTreeFromTopToBottom();
        TreeNode rootNode = buildBinaryTree();
        System.out.println(
            obj.printBinaryTreeFromTopToBottom(rootNode)
        );
        System.out.println(
            obj.printBinaryTreeFromTopToBottomByLayer(rootNode)
        );
        System.out.println(
            obj.printBinaryTreeFromTopToBottomByLayerAndZOrder(rootNode)
        );
    }
    /**
     * 构造二叉树
     *
     * @param void
     * @return {@code TreeNode}
     */
    public static TreeNode buildBinaryTree() {
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t3.left = t4;
        t3.right = t5;
        t1.left = t2;
        t1.right = t3;
        return t1;
    }
    /**
     * 剑指 Offer 面试题 32：从上到下打印二叉树 -> v3
     *
     * @param root
     * @return {@code List<List<Integer>>}
     */
    public List<List<Integer>> printBinaryTreeFromTopToBottomByLayerAndZOrder(TreeNode root) {
        List<List<Integer>> resultList = new LinkedList<>();
        Queue<TreeNode> aQueue = new LinkedList<>();
        boolean reverseOrder = false;
        aQueue.offer(root);
        while (!aQueue.isEmpty()) {
            List<Integer> aList = new LinkedList<>();
            int size = aQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = aQueue.poll();
                aList.add(currentNode.val);
                if (currentNode.left != null) {
                    aQueue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    aQueue.offer(currentNode.right);
                }
            }
            if (reverseOrder) {
                Collections.reverse(aList);
            }
            resultList.add(aList);
            reverseOrder = reverseOrder == true ? false : true;
        }
        return resultList;
    }
    /**
     * 剑指 Offer 面试题 32：从上到下打印二叉树 -> v2
     *
     * @param root
     * @return {@code List<List<Integer>>}
     */
    public List<List<Integer>> printBinaryTreeFromTopToBottomByLayer(TreeNode root) {
        List<List<Integer>> resultList = new LinkedList<>();
        Queue<TreeNode> aQueue = new LinkedList<>();
        aQueue.offer(root);
        while (!aQueue.isEmpty()) {
            int size = aQueue.size();
            List<Integer> aList = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode currentNode = aQueue.poll();
                aList.add(currentNode.val);
                if (currentNode.left != null) {
                    aQueue.offer(currentNode.left);
                }
                if (currentNode.right != null) {
                    aQueue.offer(currentNode.right);
                }
            }
            resultList.add(aList);
        }
        return resultList;
    }
    /**
     * 剑指 Offer 面试题 32：从上到下打印二叉树 -> v1
     *
     * @param root
     * @return {@code List<Integer>}
     */
    public List<Integer> printBinaryTreeFromTopToBottom(TreeNode root) {
        List<Integer> resultList = new LinkedList<>();
        Queue<TreeNode> aQueue = new LinkedList<>();
        aQueue.offer(root);
        while (!aQueue.isEmpty()) {
            TreeNode currentNode = aQueue.poll();
            resultList.add(currentNode.val);
            if (currentNode.left != null) {
                aQueue.offer(currentNode.left);
            }
            if (currentNode.right != null) {
                aQueue.offer(currentNode.right);
            }
        }
        return resultList;
    }
}
/* EOF */