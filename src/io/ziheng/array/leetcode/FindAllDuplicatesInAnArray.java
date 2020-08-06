package io.ziheng.array.leetcode;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

/**
 * LeetCode 442. Find All Duplicates in an Array
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/
 */
public class FindAllDuplicatesInAnArray {
    public static void main(String[] args) {
        // ...
    }
    public List<Integer> findDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new LinkedList<>();
        }
        // return findDuplicatesHashWay(nums);
        return findDuplicatesSortWay(nums);
    }
    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param nums
     * @return List<Integer>
     */
    private List<Integer> findDuplicatesHashWay(int[] nums) {
        Map<Integer, Integer> aMap = new HashMap<>();
        List<Integer> rList = new LinkedList<>();
        for (int n : nums) {
            aMap.put(n, 1 + aMap.getOrDefault(n, 0));
        }
        for (Map.Entry<Integer, Integer> e : aMap.entrySet()) {
            if (e.getValue() > 1) {
                rList.add(e.getKey());
            }
        }
        return rList;
    }
    /**
     * Time Complexity: O(n*log(n))
     * Space Complexity: O(1)
     *
     * @param nums
     * @return List<Integer>
     */
    private List<Integer> findDuplicatesSortWay(int[] nums) {
        List<Integer> rList = new LinkedList<>();
        quickSort(nums, 0, nums.length - 1);
        for (int i = 0, j = 1; j < nums.length; i++, j++) {
            if (nums[i] == nums[j]) {
                rList.add(nums[i]);
            }
        }
        return rList;
    }
    private static void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int i = partition(arr, left, right);
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int pLeft = 0, pRight = 0;
        for (pLeft = left, pRight = right; pLeft < pRight; /* ... */) {
            while (arr[pRight] > pivot && pLeft < pRight) {
                pRight--;
            }
            while (arr[pLeft] <= pivot && pLeft < pRight) {
                pLeft++;
            }
            if (pLeft < pRight) {
                swap(arr, pLeft, pRight);
            }
        }
        swap(arr, left, pLeft);
        return pLeft;
    }
    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
}
/* EOF */