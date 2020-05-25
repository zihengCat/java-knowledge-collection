package io.ziheng.codinginterviews;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 剑指 Offer 面试题 40：最小的 k 个数
 *
 * 题目描述：
 * 输入一个具有 n 个整数的数组，找出其中最小的 k 个数。
 *
 * 举例说明：
 * 输入：[4, 5, 1, 6, 2, 7, 3, 8, ]
 * 输出：[1, 2, 3, 4, ]
 *
 * 知识点：["排序", "堆"]
 */
public class MinimumKNumbers {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        MinimumKNumbers obj = new MinimumKNumbers();
        int[] nums = new int[]{
            4, 5, 1, 6, 2, 7, 3, 8,
        };
        System.out.println(
            obj.minimumKNumbers(nums, 4)
        );
    }
    /**
     * 剑指 Offer 面试题 40：最小的 k 个数
     *
     * @param nums
     * @param k
     * @return {@code List<Integer>}
     */
    public List<Integer> minimumKNumbers(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new LinkedList<>();
        }
        // 排序
        // return minimumKNumbersSorting(nums, k);
        // 优先队列（堆）
        return minimumKNumbersPriorityQueue(nums, k);
    }
    /**
     * 剑指 Offer 面试题 40：最小的 k 个数 -> 优先队列（堆）
     *
     * 时间复杂度：O(n * log(k))
     * 空间复杂度：O(k)
     *
     * @param nums
     * @param k
     * @return {@code List<Integer>}
     */
    private List<Integer> minimumKNumbersPriorityQueue(int[] nums, int k) {
        List<Integer> resultList = new LinkedList<>();
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(
            Collections.reverseOrder()
        );
        for (int num : nums) {
            if (priorityQueue.size() < k) {
                priorityQueue.offer(num);
            } else {
                if (num < priorityQueue.peek()) {
                    priorityQueue.poll();
                    priorityQueue.offer(num);
                }
            }
        }
        while (!priorityQueue.isEmpty()) {
            resultList.add(priorityQueue.poll());
        }
        Collections.reverse(resultList);
        return resultList;
    }
    /**
     * 剑指 Offer 面试题 40：最小的 k 个数 -> 排序
     *
     * 时间复杂度：O(n * log(n))
     * 空间复杂度：O(1)
     *
     * @param nums
     * @param k
     * @return {@code List<Integer>}
     */
    private List<Integer> minimumKNumbersSorting(int[] nums, int k) {
        List<Integer> resultList = new LinkedList<>();
        quickSort(nums, 0, nums.length - 1);
        for (int i = 0; i < k; i++) {
            resultList.add(nums[i]);
        }
        return resultList;
    }
    private void quickSort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int index = partition(arr, left, right);
        quickSort(arr, left, index - 1);
        quickSort(arr, index + 1, right);
    }
    private int partition(int[] arr, int left, int right) {
        int pivot = arr[left];
        int pLeft = 0;
        int pRight = 0;
        for (pLeft = left, pRight = right; pLeft < pRight; /* ... */) {
            while (arr[pRight] > pivot && pLeft < pRight) {
                pRight--;
            }
            while (arr[pLeft] <= pivot && pLeft < pRight) {
                pLeft++;
            }
            if (pLeft < pRight) {
                swap(arr, pLeft, pRight);
            }
        }
        swap(arr, left, pLeft);
        return pLeft;
    }
    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
/* EOF */