package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 230. Kth Smallest Element in a BST
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 */
public class KthSmallestElementInABST {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> list = new LinkedList<>();
        inorderTraversal(root, list);
        return list.get(k - 1);
    }
    private void inorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }
}
/* EOF */