package io.ziheng.hashtable.leetcode;

import java.util.Set;
import java.util.HashSet;

/**
 * LeetCode 217. Contains Duplicate
 * https://leetcode.com/problems/contains-duplicate/
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        return containsDuplicateHashWay(nums);
    }
    private boolean containsDuplicateHashWay(int[] nums) {
        Set<Integer> aSet = new HashSet<>();
        for (int n : nums) {
            aSet.add(n);
        }
        return nums.length != aSet.size();
    }
}
/* EOF */