package io.ziheng.array.leetcode;

import java.util.Arrays;

/**
 * LeetCode 1365. How Many Numbers Are Smaller Than the Current Number
 * https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
 */
public class HowManyNumbersAreSmallerThanTheCurrentNumber {
    public static void main(String[] args) {
        HowManyNumbersAreSmallerThanTheCurrentNumber obj =
        new HowManyNumbersAreSmallerThanTheCurrentNumber();
        int[] nums = {8,1,2,2,3, };
        System.out.println(
            Arrays.toString(
                obj.smallerNumbersThanCurrent(nums)
            )
        );
    }
    public int[] smallerNumbersThanCurrent(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }
        int[] newNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(newNums);
        for (int i = 0, j = newNums.length - 1; i < j; i++, j--) {
            swap(newNums, i, j);
        }
        int[] resultArray = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            resultArray[i] = countSmallerNums(
                nums[i], newNums,
                findIndex(nums[i], newNums), nums.length -1
            );
        }
        return resultArray;
    }
    private int countSmallerNums(int target, int[] orderedArray, int index, int right) {
        int cnt = 0;
        for (int i = index; i <= right; i++) {
            if (orderedArray[i] < target) {
                cnt++;
            }
        }
        return cnt;
    }
    private int findIndex(int t, int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (t == arr[i]) {
                return i;
            }
        }
        return -1;
    }
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
/* EOF */