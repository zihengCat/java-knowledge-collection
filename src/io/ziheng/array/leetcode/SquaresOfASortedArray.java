package io.ziheng.array.leetcode;

import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * LeetCode 977. Squares of a Sorted Array
 * https://leetcode.com/problems/squares-of-a-sorted-array/
 */
public class SquaresOfASortedArray {
    public int[] sortedSquares(int[] A) {
        if (A == null || A.length == 0) {
            return new int[0];
        }
        // return sortedSquaresSortListWay(A);
        return sortedSquaresSortArrayWay(A);
    }
    private int[] sortedSquaresSortArrayWay(int[] arr) {
        int[] resultArray = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            resultArray[i] = arr[i] * arr[i];
        }
        Arrays.sort(resultArray);
        return resultArray;
    }
    private int[] sortedSquaresSortListWay(int[] A) {
       List<Integer> resultList = new LinkedList<>();
        for (int n : A) {
            resultList.add(n * n);
        }
        Collections.sort(resultList);
        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = resultList.get(i);
        }
        return resultArray;
    }
}
/* EOF */