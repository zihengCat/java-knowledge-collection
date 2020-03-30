package io.ziheng.array.leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * LeetCode 384. Shuffle an Array
 * https://leetcode.com/problems/shuffle-an-array/
 */
public class ShuffleAnArray {
    private int[] originalArray;
    public ShuffleAnArray(int[] nums) {
        this.originalArray = nums;
    }
    /**
     * Resets the array to its original configuration and return it.
     *
     * @param void
     * @return int[]
     */
    public int[] reset() {
        return originalArray;
    }
    /**
     * Returns a random shuffling of the array.
     *
     * @param void
     * @return int[]
     */
    public int[] shuffle() {
        int[] shuffledArray = Arrays.copyOf(originalArray, originalArray.length);
        int length = shuffledArray.length;
        Random random = new Random();
        while (length > 0) {
            int index = random.nextInt(length);
            swap(shuffledArray, length - 1, index);
            length--;
        }
        return shuffledArray;
    }
    /**
     * Swap array elements function.
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
}
/* EOF */