package io.ziheng.array.leetcode;

import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 532. K-diff Pairs in an Array
 * https://leetcode.com/problems/k-diff-pairs-in-an-array/
 */
public class KDiffPairsInAnArray {
    public static void main(String[] args) {
        KDiffPairsInAnArray obj = new KDiffPairsInAnArray();
        int[] nums = new int[]{3, 1, 4, 1, 5, };
        int k = 2;
        obj.findPairs(nums, k);
        obj.findPairsHashTable(nums, k);
        k = 0;
        obj.findPairsHashTable(nums, k);
    }
    /**
     * LeetCode 532. K-diff Pairs in an Array
     *
     * @param nums
     * @param k
     * @return int
     */
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length <= 0 || k < 0) {
            return 0;
        }
        // return findPairsBruteForce(nums, k);
        return findPairsHashTable(nums, k);
    }
    /**
     * Brute Force
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     *
     * @param nums
     * @param k
     * @return int
     */
    public int findPairsBruteForce(int[] nums, int k) {
        Set<Set<Integer>> aSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (i != j
                    && nums[i] <= nums[j]
                    && nums[j] - nums[i] == k) {
                    Set<Integer> s = new HashSet<>();
                    s.add(nums[i]);
                    s.add(nums[j]);
                    aSet.add(s);
                }
            }
        }
        System.out.println(aSet);
        return aSet.size();
    }
    /**
     * Hash Table
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param nums
     * @param k
     * @return int
     */
    public int findPairsHashTable(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int n : nums) {
            cnt.put(n, cnt.getOrDefault(n, 0) + 1);
        }
        int r = 0;
        for (int x : cnt.keySet()) {
            if ((k > 0 && cnt.containsKey(x + k))
                || (k == 0 && cnt.get(x) > 1)) {
                r++;
            }
        }
        return r;
    }
}

/* EOF */