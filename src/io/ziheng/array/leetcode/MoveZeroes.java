package io.ziheng.array.leetcode;

import java.util.Arrays;

/**
 * LeetCode 283. Move Zeroes
 * https://leetcode.com/problems/move-zeroes/
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        //moveZeroesV1(nums);
        //moveZeroesV2(nums);
        moveZeroesV3(nums);
    }
    /**
     * 双指针（优化版）
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return void
     */
    public void moveZeroesV3(int[] nums) {
        for (int i = 0, k = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, k);
                k++;
            }
        }
    }
    /**
     * 双指针 i, k ：
     * nums[0...k) -> != 0
     * nums[k,  n] -> == 0
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param nums
     * @return void
     */
    public void moveZeroesV2(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[k] = nums[i];
                k++;
            }
        }
        for (int i = k; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
    /**
     * 新数组覆盖
     * 时间复杂度：O(2n)
     * 空间复杂度：O(n)
     *
     * @param nums
     * @return void
     */
    public void moveZeroesV1(int[] nums) {
        int numsLength = nums.length;
        int[] nonZeroNums = new int[numsLength];
        int nonZeroIndex = 0;
        for (int i = 0; i < numsLength; i++) {
            if (nums[i] != 0) {
                nonZeroNums[nonZeroIndex] = nums[i];
                nonZeroIndex++;
            }
        }
        for (int i = 0; i < nonZeroIndex; i++) {
            nums[i] = nonZeroNums[i];
        }
        for (int i = nonZeroIndex; i < numsLength; i++) {
            nums[i] = 0;
        }
    }
    /**
     * Swap util function.
     *
     * @param arr
     * @param i
     * @param j
     * @return void
     */
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 0, 4, 5, 0, };
        new MoveZeroes().moveZeroes(arr);
        assert Arrays.toString(arr).equals(
            "[1, 2, 3, 4, 5, 0, 0]"
        );
        System.out.println("Test result: OK");
    }
}
/* EOF */