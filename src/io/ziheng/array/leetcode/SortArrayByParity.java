package io.ziheng.array.leetcode;
/**
 * LeetCode 905. Sort Array By Parity
 * https://leetcode.com/problems/sort-array-by-parity/
 */
public class SortArrayByParity {
    public int[] sortArrayByParity(int[] A) {
        if (A == null || A.length == 0) {
            return new int[0];
        }
        int pLeft = 0;
        int pRight = A.length - 1;
        while (pLeft < pRight) {
            while (!isEven(A[pRight]) && pLeft < pRight) {
                pRight--;
            }
            while (isEven(A[pLeft]) && pLeft < pRight) {
                pLeft++;
            }
            if (pLeft < pRight) {
                swap(A, pLeft, pRight);
            }
        }
        return A;
    }
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
    private boolean isEven(int num) {
        return num % 2 == 0;
    }
}
/* EOF */