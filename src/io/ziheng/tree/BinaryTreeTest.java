package io.ziheng.tree;

import io.ziheng.tree.TreeNode;

import java.util.Queue;
import java.util.LinkedList;

public class BinaryTreeTest {
    public static void main(String[] args) {
        /*
        TreeNode<Integer> root = new TreeNode<Integer>(null, null, null);
        TreeNode<Integer> node1 = new TreeNode<Integer>(1, null, null);
        TreeNode<Integer> node2 = new TreeNode<Integer>(2, null, null);
        root.setLeftNode(node1);
        root.setRightNode(node2);
        levelOrderTraverseBinaryTree(root);
        */

        TreeNode<Integer> root = new TreeNode<Integer>();
        buildBinaryTree(root, new Integer[]{0, 1, 2, 3, 4}, 1);
        levelOrderTraverseBinaryTree(root);
    }
    public static <E> TreeNode<E> buildTreeFromArrayTemplate(E[] arr) {
        TreeNode<E> head = new TreeNode<E>();
        int length = arr.length;
        for (int i = 0; i < length; ++i) {
            // ...
        }
        return null;
    }
    // 用数组建立普通二叉树
    private static <E> TreeNode<E> buildBinaryTree(TreeNode<E> root, E[] arr, int index) {
        if (index > arr.length / 2) {
            return root;
        }
        if (index == 1) {
            root.setElement(arr[0]);
        }
        root.setLeftNode(new TreeNode<E>(arr[index * 2 - 1]));
        root.setRightNode(new TreeNode<E>(arr[index * 2 - 0]));
        buildBinaryTree(root.getLeftNode(), arr, index + 1);
        buildBinaryTree(root.getRightNode(), arr, index + 2);
        return root;
    }
    /* 层序遍历二叉树 */
    public static <E> void levelOrderTraverseBinaryTree(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        Queue<TreeNode<E>> queue = new LinkedList<TreeNode<E>>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode<E> node = queue.poll();
            visit(node);
            if (node.getLeftNode() != null) {
                queue.offer(node.getLeftNode());
            }
            if (node.getRightNode() != null) {
                queue.offer(node.getRightNode());
            }
        }
    }
    public static <E> void visit(TreeNode<E> node) {
        System.out.println(node);
    }
}
/* EOF */
