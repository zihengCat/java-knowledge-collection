package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

/**
 * LeetCode 572. Subtree of Another Tree
 * https://leetcode.com/problems/subtree-of-another-tree/
 */
public class SubtreeOfAnotherTree {
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        if (isSameTree(s, t)) {
            return true;
        }
        return isSubtree(s.left, t) ||
               isSubtree(s.right, t);
    }
    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        } else if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
}
/* EOF */