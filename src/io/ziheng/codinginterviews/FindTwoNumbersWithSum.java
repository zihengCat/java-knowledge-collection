package io.ziheng.codinginterviews;

import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.HashSet;

/**
 * 剑指 Offer 面试题 57-1：和为 s 的两数
 *
 * 题目描述：
 * 输入一个递增排序的数组和一个数字 S，在数组中查找两个数，使得两数和正好是 S，
 * 如果有多对数字的和都等于 S，输出两数乘积最小的一对。
 *
 * 知识点：["数学"]
 */
public class FindTwoNumbersWithSum {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        FindTwoNumbersWithSum obj = new FindTwoNumbersWithSum();
        int[] arr = {1, 2, 3, 4, 5, };
        int sum = 5;
        System.out.println(
            obj.findTwoNumbersWithSum(arr, sum)
        );
    }
    /**
     * 剑指 Offer 面试题 57-1：和为 s 的两数
     *
     * @param arr
     * @param sum
     * @return {@code List<Integer>}
     */
    public List<Integer> findTwoNumbersWithSum(int[] arr, int sum) {
        if (arr == null || arr.length < 2) {
            return new LinkedList<>();
        }
        // 哈希表
        // return findTwoNumbersWithSumHashing(arr, sum);
        // 双指针
        return findTwoNumbersWithSumTwoPointer(arr, sum);
    }
    /**
     * 双指针
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param arr
     * @param sum
     * @return {@code List<Integer>}
     */
    private List<Integer> findTwoNumbersWithSumTwoPointer(int[] arr, int sum) {
        List<Integer> resultList = new LinkedList<>();
        for (int pLeft = 0, pRight = arr.length - 1;
            pLeft < pRight;
            /* ... */) {
            int currentSum = arr[pLeft] + arr[pRight];
            if (currentSum == sum) {
                resultList.add(arr[pLeft]);
                resultList.add(arr[pRight]);
                break;
            } else if (currentSum < sum) {
                pLeft++;
            } else if (currentSum > sum) {
                pRight--;
            }
        }
        return resultList;
    }
    /**
     * 哈希表
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param arr
     * @param sum
     * @return {@code List<Integer>}
     */
    private List<Integer> findTwoNumbersWithSumHashing(int[] arr, int sum) {
        List<Integer> resultList = new LinkedList<>();
        Set<Integer> aSet = new HashSet<>();
        for (int n : arr) {
            aSet.add(n);
        }
        for (int i = 0; i < arr.length; i++) {
            int findNum = sum - arr[i];
            if (aSet.contains(findNum)) {
                resultList.add(arr[i]);
                resultList.add(findNum);
                break;
            }
        }
        return resultList;
    }
}
/* EOF */