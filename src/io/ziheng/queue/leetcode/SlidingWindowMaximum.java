package io.ziheng.queue.leetcode;

import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Deque;
import java.util.PriorityQueue;

/**
 * LeetCode 239. Sliding Window Maximum
 * https://leetcode.com/problems/sliding-window-maximum/
 */
public class SlidingWindowMaximum {
    public static void main(String[] args) {
        SlidingWindowMaximum obj = new SlidingWindowMaximum();
        int[] nums = new int[]{1, 3, -1, -3, 5, 3, 6, 7, };
        int k = 3;
        System.out.println(
            Arrays.toString(
                obj.maxSlidingWindow(nums, k)
            )
        );
    }
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k < 0 || nums.length < k) {
            return new int[0];
        }
        // 暴力法
        // return maxSlidingWindowBruteForce(nums, k);

        // 优先队列（最大堆）
        // return maxSlidingWindowMaximumHeap(nums, k);

        // 滑动窗口（双端队列）
        // ...
        return maxSlidingWindowDeque(nums, k);
    }
    private int[] maxSlidingWindowDeque(int[] nums, int k) {
        int[] resultArray = new int[nums.length - k + 1];
        int resultArrayIndex = 0;
        // store index
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            System.out.println(
                "Round " + resultArrayIndex + ": "
                + deque
            );
            // remove numbers out of range k
            while (!deque.isEmpty()
                && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            // remove smaller numbers in k range as they are useless
            while (!deque.isEmpty()
                && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            // q contains index... r contains content
            deque.offerLast(i);
            if (i >= k - 1) {
                resultArray[resultArrayIndex] = nums[deque.peekFirst()];
                resultArrayIndex++;
            }
        }
        return resultArray;
    }
    private int[] maxSlidingWindowMaximumHeap(int[] nums, int k) {
        int[] resultArray = new int[nums.length - k + 1];
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
            Collections.reverseOrder()
        );
        for (int i = 0; i < k; i++) {
            priorityQueue.offer(nums[i]);
        }
        resultArray[0] = priorityQueue.peek();
        for (int i = k; i < nums.length; i++) {
            priorityQueue.remove(nums[i - k]);
            priorityQueue.offer(nums[i]);
            resultArray[i - k + 1] = priorityQueue.peek();
        }
        return resultArray;
    }
    private int[] maxSlidingWindowBruteForce(int[] nums, int k) {
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i <= nums.length - k; i++) {
            int currentMaximumValue = Integer.MIN_VALUE;
            for (int j = i; j < i + k; j++) {
                if (nums[j] > currentMaximumValue) {
                    currentMaximumValue = nums[j];
                }
            }
            resultList.add(currentMaximumValue);
        }
        int[] resultArray = new int[resultList.size()];
        for (int i = 0; i < resultArray.length; i++) {
            resultArray[i] = resultList.get(i);
        }
        return resultArray;
    }
}
/* EOF */