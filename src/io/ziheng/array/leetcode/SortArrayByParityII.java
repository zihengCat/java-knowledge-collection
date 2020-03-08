package io.ziheng.array.leetcode;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 * LeetCode 922. Sort Array By Parity II
 * https://leetcode.com/problems/sort-array-by-parity-ii/
 */
public class SortArrayByParityII {
    public int[] sortArrayByParityII(int[] A) {
        if (A == null || A.length == 0) {
            return new int[0];
        }
        List<Integer> oddList = new ArrayList<>();
        List<Integer> evenList = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            if (isEven(A[i])) {
                evenList.add(A[i]);
            } else {
                oddList.add(A[i]);
            }
        }
        Collections.sort(oddList);
        Collections.sort(evenList);
        int[] resultArray = new int[A.length];
        for (int i = 0; i < resultArray.length; i++) {
            if (isEven(i)) {
                resultArray[i] = evenList.remove(evenList.size() - 1);
            } else {
                resultArray[i] = oddList.remove(oddList.size() - 1);
            }
        }
        return resultArray;
    }
    private boolean isEven(int num) {
        return num % 2 == 0;
    }
}
/* EOF */