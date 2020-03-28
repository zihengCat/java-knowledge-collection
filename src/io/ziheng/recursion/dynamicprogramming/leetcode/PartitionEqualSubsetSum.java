package io.ziheng.recursion.dynamicprogramming.leetcode;

import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 416. Partition Equal Subset Sum
 * https://leetcode.com/problems/partition-equal-subset-sum/
 */
public class PartitionEqualSubsetSum {
    public static void main(String[] args) {
        PartitionEqualSubsetSum obj = new PartitionEqualSubsetSum();
        int[] num = {1, 2, 3, 4};
        System.out.println(obj.canPartition(num));
        num = new int[]{1, 1, 3, 4, 7};
        System.out.println(obj.canPartition(num));
        num = new int[]{2, 3, 4, 6};
        System.out.println(obj.canPartition(num));

        num = new int[]{2, 3, 2, };
        System.out.println(obj.canPartition(num));
    }
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int sumValue = 0;
        for (int n: nums) {
            sumValue += n;
        }
        if (sumValue % 2 != 0) {
            return false;
        }
        int halfValue = sumValue / 2;
        // return canPartitionRecursive(nums, 0, halfValue);
        return canPartitionTopToBottom(nums, 0, halfValue);
    }
    private Map<String, Boolean> memo = new HashMap<>();
    /**
     * 自顶向下 -> 记忆化搜索
     *
     * @param nums
     * @param currentIndex
     * @param sum
     * @return boolean
     */
    private boolean canPartitionTopToBottom(int[] nums, int currentIndex, int sum) {
        if (sum == 0) {
            return true;
        }
        if (currentIndex >= nums.length) {
            return false;
        }
        String key = currentIndex + "->" + sum;
        if (!memo.containsKey(key)) {
            boolean taken = canPartitionTopToBottom(nums, currentIndex + 1, sum - nums[currentIndex]);
            boolean notTaken = canPartitionTopToBottom(nums, currentIndex + 1, sum);
            memo.put(key, taken || notTaken);
        }
        return memo.get(key);
    }
    private boolean canPartitionRecursive(int[] nums, int currentIndex, int sum) {
        if (sum == 0) {
            return true;
        }
        if (currentIndex >= nums.length) {
            return false;
        }
        return canPartitionRecursive(nums, currentIndex + 1, sum)
            || canPartitionRecursive(nums, currentIndex + 1, sum - nums[currentIndex]);
    }
}
/* EOF */