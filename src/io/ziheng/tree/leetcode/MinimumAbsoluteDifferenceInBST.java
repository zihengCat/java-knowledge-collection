package io.ziheng.tree.leetcode;

import io.ziheng.tree.leetcode.TreeNode;

import java.util.List;
import java.util.ArrayList;

/**
 * LeetCode 530. Minimum Absolute Difference in BST
 * https://leetcode.com/problems/minimum-absolute-difference-in-bst/
 */
public class MinimumAbsoluteDifferenceInBST {
    public int getMinimumDifference(TreeNode root) {
        List<Integer> aList = new ArrayList<>();
        inOrderTraversal(root, aList);
        return findMinimumDifference(aList);
    }
    private void inOrderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left, list);
        list.add(Integer.valueOf(root.val));
        inOrderTraversal(root.right, list);
    }
    private int findMinimumDifference(List<Integer> aList) {
        int minimumDiff = Integer.MAX_VALUE;
        for (int i = 0; i < aList.size() - 1; i++) {
            minimumDiff = Math.min(minimumDiff,
                Math.abs(aList.get(i) - aList.get(i + 1))
            );
        }
        return minimumDiff;
    }
}
/* EOF */