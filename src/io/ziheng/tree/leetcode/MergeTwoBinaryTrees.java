package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * LeetCode 617. Merge Two Binary Trees
 * https://leetcode.com/problems/merge-two-binary-trees/
 */
public class MergeTwoBinaryTrees {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        //return mergeTreesRecursively(t1, t2);
        return mergeTreesIteratively(t1, t2);
    }
    private TreeNode mergeTreesIteratively(TreeNode t1, TreeNode t2) {
        if (t1 == null) {
            return t2;
        }
        if (t2 == null) {
            return t1;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(t1);
        queue.offer(t2);
        while (!queue.isEmpty()) {
            TreeNode n1 = queue.poll();
            TreeNode n2 = queue.poll();
            n1.val += n2.val;
            if (n1.left != null && n2.left != null) {
                queue.offer(n1.left);
                queue.offer(n2.left);
            } else if (n1.left == null || n2.left == null) {
                n1.left = n1.left == null ? n2.left : n1.left;
            }
            if (n1.right != null && n2.right != null) {
                queue.offer(n1.right);
                queue.offer(n2.right);
            } else if (n1.right == null || n2.right == null) {
                n1.right = n1.right == null ? n2.right : n1.right;
            }
        }
        return t1;
    }
    private TreeNode mergeTreesRecursively(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return null;
        }
        if (t1 != null && t2 == null) {
            return t1;
        }
        if (t1 == null && t2 != null) {
            return t2;
        }
        if (t1.left == null && t1.right == null &&
            t2.left == null && t2.right == null) {
            t1.val += t2.val;
            return t1;
        }
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }
}
/* EOF */