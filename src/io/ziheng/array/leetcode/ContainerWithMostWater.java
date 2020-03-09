package io.ziheng.array.leetcode;
/**
 * LeetCode 11. Container With Most Water
 * https://leetcode.com/problems/container-with-most-water/
 */
public class ContainerWithMostWater {
    public static void main(String[] args) {
        int[] height = new int[]{1,8,6,2,5,4,8,3,7,};
        int max = new ContainerWithMostWater().maxArea(height);
        assert max == 49;
        System.out.println("Test result: OK");
    }
    /**
     * maxArea()
     *
     * @param height
     * @return int
     */
    public int maxArea(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }
        // return maxAreaLoop(height);
        return maxAreaTwoPointer(height);
    }
    /**
     * 双指针
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param height
     * @return int
     */
    private int maxAreaTwoPointer(int[] height) {
        int max = 0;
        for (int pLeft = 0, pRight = height.length - 1;
            pLeft < pRight; /* ... */ ) {
            int minHeight = 0;
            if (height[pLeft] < height[pRight]) {
                minHeight = height[pLeft];
                pLeft++;
            } else {
                minHeight = height[pRight];
                pRight--;
            }
            int area = (pRight - pLeft + 1) * minHeight;
            max = Math.max(max, area);
        }
        return max;
    }
    /**
     * 暴力迭代
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * @param height
     * @return int
     */
    private int maxAreaLoop(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                int area = (j - i) * Math.min(height[i], height[j]);
                max = Math.max(max, area);
            }
        }
        return max;
    }
}
/* EOF */