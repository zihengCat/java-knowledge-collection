package io.ziheng.array.leetcode;
/**
 * LeetCode 665. Non-decreasing Array
 * https://leetcode.com/problems/non-decreasing-array/
 */
public class NonDecreasingArray {
    public boolean checkPossibility(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        boolean modified = false;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                if (modified) {
                    return false;
                } else {
                    if (i - 2 >= 0 && nums[i] < nums[i - 2]) {
                        nums[i] = nums[i - 1];
                    }
                    modified = true;
                }
            }
        }
        return true;
    }
}
/* EOF */