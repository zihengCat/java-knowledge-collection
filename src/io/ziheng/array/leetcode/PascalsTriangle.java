package io.ziheng.array.leetcode;

import java.util.List;
import java.util.ArrayList;

/**
 * LeetCode 118. Pascal's Triangle
 * https://leetcode.com/problems/pascals-triangle/
 */
public class PascalsTriangle {
    public List<List<Integer>> generate(int numRows) {
        if (numRows <= 0) {
            return new ArrayList<>();
        }
        List<List<Integer>> resultList = new ArrayList<>();
        List<Integer> aList = new ArrayList<>();
        aList.add(1);
        resultList.add(aList);
        for (int i = 1; i < numRows; i++) {
            List<Integer> prevRow = resultList.get(i - 1);
            List<Integer> currentRow = new ArrayList<>();
            currentRow.add(1);
            for (int j = 1; j < i; j++) {
                currentRow.add(
                    prevRow.get(j - 1) + prevRow.get(j)
                );
            }
            currentRow.add(1);
            resultList.add(currentRow);
        }
        return resultList;
    }
}
/* EOF */