package io.ziheng.recursion.divideandconquer.leetcode;

import java.util.LinkedList;
import java.util.List;

public class PartitionEqualSubsetSum {
    /**
     * 主函数 -> 测试用例
     */
    public static void main(String[] args) {
        PartitionEqualSubsetSum obj = new PartitionEqualSubsetSum();
        int[] nums = new int[]{1, 5, 11, 5, };
        System.out.println(
            obj.canPartition(nums)
        );
    }
    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        List<Integer> partA = new LinkedList<>();
        List<Integer> partB = new LinkedList<>();
        for (int num : nums) {
            partB.add(num);
        }
        canPartition0(nums, partA, partB);
    }
    private boolean canPartition0(int[] nums, List<Integer> partA, List<Integer> partB) {
        if ()
    }
}
/* EOF */