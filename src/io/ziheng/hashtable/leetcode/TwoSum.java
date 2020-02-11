package io.ziheng.hashtable.leetcode;

import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 1. Two Sum
 * https://leetcode.com/problems/two-sum/
 */
public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        //return twoSumV1(nums, target);
        //return twoSumV2(nums, target);
        return twoSumV3(nums, target);
    }
    /**
     * 查找表法（1-Pass）
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @param target
     * @return int[]
     */
    public int[] twoSumV3(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complementNum = target - nums[i];
            if (map.containsKey(complementNum)) {
                result[0] = map.get(complementNum);
                result[1] = i;
                return result;
            }
            map.put(nums[i], i);
        }
        // Not Found!
        return null;
    }
    /**
     * 查找表法（2-Pass）
     * 时间复杂度：O(2n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @param target
     * @return int[]
     */
    public int[] twoSumV2(int[] nums, int target) {
        int[] result = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        /**
         * NOTE: 如果数组中有重复元素，会发生索引覆盖。
         */
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complementNum = target - nums[i];
            if (map.containsKey(complementNum)) {
                result[0] = map.get(complementNum);
                result[1] = i;
                return result;
            }
        }
        // Not Found!
        return null;
    }
    /**
     * 暴力搜索
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @param target
     * @return int[]
     */
    public int[] twoSumV1(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        // Not Found!
        return null;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15, 17, };
        int target = 9;
        int[] result = new TwoSum().twoSum(nums, target);
        assert result != null &&
            result.length == 2 &&
            (result[0] == 0 && result[0] == 1) ||
            (result[0] == 1 && result[0] == 0);
        System.out.println(
            Arrays.toString(result)
        );
    }
}
/* EOF */