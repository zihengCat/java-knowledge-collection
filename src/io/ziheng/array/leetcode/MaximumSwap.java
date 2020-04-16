package io.ziheng.array.leetcode;
/**
 * LeetCode 670. Maximum Swap
 * https://leetcode.com/problems/maximum-swap/
 */
public class MaximumSwap {
    public int maximumSwap(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        maximumSwap0(arr, 0, arr.length);
        return Integer.valueOf(String.valueOf(arr));
    }
    private void maximumSwap0(char[] arr, int currentIndex, int maximumLength) {
        if (currentIndex >= maximumLength) {
            return;
        }
        // try to find a number greater than the current
        // note, if there are several max numbers,
        // we need to take the last one,
        // e.g. 1993 -> 9913 rather than 9193
        int maximumDigitIndex = currentIndex;
        for (int i = currentIndex + 1; i < arr.length; i++) {
            if (arr[i] > arr[currentIndex]
                && arr[i] >= arr[maximumDigitIndex]) {
                maximumDigitIndex = i;
            }
        }
        if (maximumDigitIndex > currentIndex) {
            swap(arr, maximumDigitIndex, currentIndex);
            return;
        }
        maximumSwap0(arr, currentIndex + 1, maximumLength);
    }
    private void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
/* EOF */