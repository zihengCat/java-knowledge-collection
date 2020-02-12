package io.ziheng.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;

public class BinarySearchTree<E extends Comparable<E>> {
    private class TreeNode<E> {
        public E element;
        public TreeNode<E> left;
        public TreeNode<E> right;
        public TreeNode(E element, TreeNode<E> left, TreeNode<E> right) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode<E> root;
    private int size;

    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        int[] arr = new int[]{5, 3, 6, 8, 4, 2,};
        /**
         *          5
         *        /   \
         *       3     6
         *      /  \    \
         *     2    4    8
         */
        for (int i : arr) {
            binarySearchTree.add(i);
        }
        /**
         * PreOrder:  [5, 3, 2, 4, 6, 8,]
         * InOrder:   [2, 3, 4, 5, 6, 8,]
         * PostOrder: [2, 4, 3, 8, 6, 5,]
         */
        binarySearchTree.travelsalTree();
    }

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }
    public int size() {
        return size;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public void add(E element) {
        root = addRecursively(root, element);
    }
    private TreeNode<E> addRecursively(TreeNode<E> root, E element) {
        if (root == null) {
            size++;
            return new TreeNode<E>(element, null, null);
        }
        if (element.compareTo(root.element) < 0) {
            root.left = addRecursively(root.left, element);
        } else if (element.compareTo(root.element) > 0) {
            root.right = addRecursively(root.right, element);
        }
        return root;
    }
    public void travelsalTree() {

        System.out.printf("PreOrderIteratively: ");
        travelsalTreePreOrderIteratively(root);
        System.out.println();
        System.out.printf("PreOrder: ");
        travelsalTreePreOrder(root);
        System.out.println();
        System.out.printf("InOrder: ");
        travelsalTreeInOrder(root);
        System.out.println();
        System.out.printf("PostOrder: ");
        travelsalTreePostOrder(root);
        System.out.println();
    }
    private void travelsalTreePostOrder(TreeNode<E> root) {
        // Left, Right, Root
        if (root == null) {
            return;
        }
        travelsalTreePostOrder(root.left);
        travelsalTreePostOrder(root.right);
        visitNode(root);
    }
    private void travelsalTreePreOrderIteratively(TreeNode<E> root) {
        if (root == null) {
            return;
        }
        Deque<TreeNode<E>> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<E> node = stack.pop();
            visitNode(node);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }
    private void travelsalTreePreOrder(TreeNode<E> root) {
        // Root, Left, Right
        if (root == null) {
            return;
        }
        visitNode(root);
        travelsalTreePreOrder(root.left);
        travelsalTreePreOrder(root.right);
    }
    private void travelsalTreeInOrder(TreeNode<E> root) {
        // Left, Root, Right
        if (root == null) {
            return;
        }
        travelsalTreeInOrder(root.left);
        visitNode(root);
        travelsalTreeInOrder(root.right);
    }
    private void visitNode(TreeNode<E> node) {
        System.out.print(node.element + " ");
    }
}
/* EOF */