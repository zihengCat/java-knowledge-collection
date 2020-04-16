package io.ziheng.array.leetcode;
/**
 * LeetCode 941. Valid Mountain Array
 * https://leetcode.com/problems/valid-mountain-array/
 */
public class ValidMountainArray {
    public boolean validMountainArray(int[] A) {
        if (A == null || A.length < 3) {
            return false;
        }
        return validMountainArrayTwoPointer(A);
    }
    private boolean validMountainArrayTwoPointer(int[] arr) {
        int pLeft = 0;
        int pRight = arr.length - 1;
        while (pLeft < pRight) {
            if (arr[pLeft] < arr[pLeft + 1]) {
                pLeft++;
            } else if (arr[pRight] < arr[pRight - 1]) {
                pRight--;
            } else {
                break;
            }
        }
        return pLeft != 0
            && pRight != arr.length - 1
            && pLeft == pRight;
    }
}
/* EOF */