package io.ziheng.hashtable.leetcode;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;

/**
 * LeetCode 15. 3Sum
 * https://leetcode.com/problems/3sum/
 */
public class ThreeSum {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        ThreeSum obj = new ThreeSum();
        int[] nums = {
            -1, 0, 1, 2, -1, -4,
        };
        System.out.println(
            obj.threeSum(nums)
        );
    }
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null || nums.length < 3) {
            return new LinkedList<>();
        }
        // 暴力法
        // return threeSumBruteForce(nums);
        // 哈希表
        return threeSumHashWay(nums);
    }
    /**
     * 哈希表法
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(2n)
     *
     * @param nums
     * @return {@code List<List<Integer>>}
     */
    private List<List<Integer>> threeSumHashWay(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet<>();
        Map<Integer, Integer> aMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            aMap.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int key = -1 * (nums[i] + nums[j]);
                if (aMap.containsKey(key)
                    && i != aMap.get(key)
                    && j != aMap.get(key)) {
                    List<Integer> list = new LinkedList<>();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(key);
                    Collections.sort(list);
                    resultSet.add(list);
                }
            }
        }
        return resultSet.stream().collect(Collectors.toList());
    }
    /**
     * 暴力求解
     * 时间复杂度：O(n^3)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @return {@code List<List<Integer>>}
     */
    private List<List<Integer>> threeSumBruteForce(int[] nums) {
        Set<List<Integer>> resultSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> list = new LinkedList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        Collections.sort(list);
                        resultSet.add(list);
                    }
                }
            }
        }
        return resultSet.stream().collect(Collectors.toList());
    }
}
/* EOF */