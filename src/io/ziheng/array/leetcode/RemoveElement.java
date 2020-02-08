package io.ziheng.array.leetcode;

import java.util.Arrays;

/**
 * LeetCode 27. Remove Element
 * https://leetcode.com/problems/remove-element/
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {
        //return removeElementV1(nums, val);
        //return removeElementV2(nums, val);
        return removeElementV3(nums, val);
    }
    public int removeElementV3(int[] nums, int val) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                swap(nums, i, k);
                k++;
            }
        }
        return k;
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public int removeElementV2(int[] nums, int val) {
        int numsLength = nums.length;
        int pointerA = 0;
        int pointerB = 0;
        for (; pointerA < numsLength; pointerA++) {
            if (nums[pointerA] != val) {
                nums[pointerB] = nums[pointerA];
                pointerB++;
            }
        }
        for (int i = pointerB; i < numsLength; i++) {
            nums[i] = val;
        }
        return pointerB;
    }
    public int removeElementV1(int[] nums, int val) {
        int numsLength = nums.length;
        int[] nonValNums = new int[numsLength];
        int nonValIndex = 0;
        for (int i = 0; i < numsLength; i++) {
            if (nums[i] != val) {
                nonValNums[nonValIndex] = nums[i];
                nonValIndex++;
            }
        }
        for (int i = 0; i < nonValIndex; i++) {
            nums[i] = nonValNums[i];
        }
        for (int i = nonValIndex; i < numsLength; i++) {
            nums[i] = val;
        }
        return nonValIndex;
    }
    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 3, 3, 4, 5, 6, 7, 8, };
        int num = new RemoveElement().removeElement(arr, 3);
        assert num == arr.length - 3;
        System.out.println(num);
        System.out.println(Arrays.toString(arr));
    }
}
/* EOF */