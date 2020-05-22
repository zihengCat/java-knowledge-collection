package io.ziheng.codinginterviews;

import java.util.List;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 面试题 59：滑动窗口的最大值
 *
 * 题目描述：
 * 给定一个整型数组和滑动窗口大小，找出所有滑动窗口中的最大值。
 *
 * 样例：
 * 输入：数组 {2,3,4,2,6,2,5,1}，滑动窗口大小 3
 * 输出：{4,4,6,6,6,5}
 *
 * 数组中的滑动窗口：
 * 1. {[2,3,4],2,6,2,5,1}
 * 2. {2,[3,4,2],6,2,5,1}
 * 3. {2,3,[4,2,6],2,5,1}
 * 4. {2,3,4,[2,6,2],5,1}
 * 5. {2,3,4,2,[6,2,5],1}
 * 6. {2,3,4,2,6,[2,5,1]}
 *
 * 知识点：["队列"]
 */
public class MaximumValueOfSlidingWindow {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        MaximumValueOfSlidingWindow obj = new MaximumValueOfSlidingWindow();
        int[] numsArray = new int[]{2, 3, 4, 2, 6, 2, 5, 1, };
        int windowSize = 3;
        System.out.println(
            obj.maximumValueOfSlidingWindow(numsArray, windowSize)
        );
    }
    /**
     * 剑指 Offer 面试题 59：滑动窗口的最大值
     *
     * @param numsArray
     * @param windowSize
     * @return {@code List<Integer>}
     */
    public List<Integer> maximumValueOfSlidingWindow(
        int[] numsArray, int windowSize) {
        if (numsArray == null
            || windowSize < 1
            || windowSize > numsArray.length) {
            return new LinkedList<>();
        }
        return maximumValueOfSlidingWindowBruteForce(numsArray, windowSize);
    }
    /**
     * 剑指 Offer 面试题 59：滑动窗口的最大值 -> 双端队列
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(k)
     *
     * @param numsArray
     * @param windowSize
     * @return {@code List<Integer>}
     */
    private List<Integer> maximumValueOfSlidingWindowOptimized(
        int[] numsArray, int windowSize) {
        List<Integer> resultList = new LinkedList<>();
        Deque<Integer> aDeque = new LinkedList<>();
        for (int i = 0; i < windowSize; i++) {
            // ...
        }
        return resultList;
    }
    /**
     * 剑指 Offer 面试题 59：滑动窗口的最大值 -> 暴力法
     *
     * 时间复杂度：O(n * k)
     * 空间复杂度：O(1)
     *
     * @param numsArray
     * @param windowSize
     * @return {@code List<Integer>}
     */
    private List<Integer> maximumValueOfSlidingWindowBruteForce(
        int[] numsArray, int windowSize) {
        List<Integer> resultList = new LinkedList<>();
        for (int i = 0, j = windowSize - 1;
            j < numsArray.length;
            i++, j++) {
            resultList.add(getMaximumValue(numsArray, i, j));
        }
        return resultList;
    }
    private int getMaximumValue(int[] arr, int i, int j) {
        int maximumValue = arr[i];
        for (/* ... */; i <= j; i++) {
            if (arr[i] > maximumValue) {
                maximumValue = arr[i];
            }
        }
        return maximumValue;
    }
}
/* EOF */