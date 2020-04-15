package io.ziheng.stack.leetcode;

/**
 * LeetCode 84. Largest Rectangle in Histogram
 * https://leetcode.com/problems/largest-rectangle-in-histogram/
 */
public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }
        return largestRectangleAreaBruteForce(heights);
    }
    public int largestRectangleAreaBruteForce(int[] heights) {
        int maximunArea = Integer.MIN_VALUE;
        for (int i = 0; i < heights.length; i++) {
            int height = heights[i];
            int leftIndex = findLeftSmallerIndex(i, heights);
            int rightIndex = findRightSmallerIndex(i, heights);
            int currentArea = height * (rightIndex - leftIndex - 1);
            maximunArea = Math.max(maximunArea, currentArea);
        }
        return maximunArea;
    }
    private int findLeftSmallerIndex(int index, int[] heights) {
        int height = heights[index];
        while (index >= 0 && heights[index] >= height) {
            index--;
        }
        return index;
    }
    private int findRightSmallerIndex(int index, int[] heights) {
        int height = heights[index];
        while (index < heights.length && heights[index] >= height) {
            index++;
        }
        return index;
    }
}
/* EOF */