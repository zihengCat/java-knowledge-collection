package io.ziheng.search.leetcode;

import java.util.List;

/**
 * This is the BinaryMatrix's API interface.
 * You should not implement it, or speculate about its implementation.
 */
interface BinaryMatrix {
    public int get(int x, int y);
    public List<Integer> dimensions();
};

/**
 * LeetCode 1374. Leftmost Column with at Least a One
 * ...
 */
public class LeftmostColumnWithAtLeastAOne {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        if (binaryMatrix == null) {
            return -1;
        }
        // return leftMostColumnWithOneBruteForce(binaryMatrix);
        // return leftMostColumnWithOneOptimization(binaryMatrix);
        return leftMostColumnWithOneBinarySearch(binaryMatrix);
    }
    private int leftMostColumnWithOneBinarySearch(BinaryMatrix binaryMatrix) {
        List<Integer> matrixDimensions = binaryMatrix.dimensions();
        int rowNum = matrixDimensions.get(0);
        int colNum = matrixDimensions.get(1);
        int pRow = 0;
        int pCol = colNum;
        while (pRow < rowNum) {
            int left = 0;
            int right = colNum;
            while (left < right) {
                int mid = left + (right - left) / 2;
                int midBit = binaryMatrix.get(pRow, mid);
                if (midBit == 1) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            pCol = Math.min(pCol, left);
            pRow++;
        }
        return pCol == colNum ? -1 : pCol;
    }
    private int leftMostColumnWithOneOptimization(BinaryMatrix binaryMatrix) {
        List<Integer> matrixDimensions = binaryMatrix.dimensions();
        int rowNum = matrixDimensions.get(0);
        int colNum = matrixDimensions.get(1);
        int leftMostColumnWithOne = -1;
        for (int pRow = 0, pCol = colNum - 1;
            pRow < rowNum && pCol >= 0;
            /* ... */) {
            int pBit = binaryMatrix.get(pRow, pCol);
            if (pBit == 1) {
                leftMostColumnWithOne = pCol;
                pCol--;
            } else {
                pRow++;
            }
        }
        return leftMostColumnWithOne;
    }
    private int leftMostColumnWithOneBruteForce(BinaryMatrix binaryMatrix) {
        List<Integer> matrixDimensions = binaryMatrix.dimensions();
        int rowNum = matrixDimensions.get(0);
        int colNum = matrixDimensions.get(1);
        for (int i = 0; i < colNum; i++) {
            for (int j = 0; j < rowNum; j++) {
                int bit = binaryMatrix.get(j, i);
                if (bit == 1) {
                    return i;
                }
            }
        }
        return -1;
    }
}
/* EOF */