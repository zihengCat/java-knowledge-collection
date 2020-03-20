package io.ziheng.array.leetcode;

import java.util.Arrays;

/**
 * LeetCode 581. Shortest Unsorted Continuous Subarray
 * https://leetcode.com/problems/shortest-unsorted-continuous-subarray/
 */
public class ShortestUnsortedContinuousSubarray {
    public int findUnsortedSubarray(int[] nums) {
        // 异常情况
        if (nums == null || nums.length == 0) {
            return -1;
        }
        /**
         * 1. 拷贝并排序数组
         * 2. 设置头尾指针
         * 3. 从头至尾/从尾至头遍历双数组，寻找第一个不同元素
         * 4. 索引指针相减
         */
        int[] sortedNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedNums);
        int begin = 0;
        int end = nums.length - 1;
        while (begin <= end && nums[begin] == sortedNums[begin]) {
            begin++;
        }
        while (end >= begin && nums[end] == sortedNums[end]) {
            end--;
        }
        return end - begin + 1;
    }
}
/* EOF */