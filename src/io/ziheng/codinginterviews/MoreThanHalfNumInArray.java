package io.ziheng.codinginterviews;

import java.util.Map;
import java.util.TreeMap;

/**
 * 剑指 Offer 面试题 39：数组中出现次数超过一半的数字
 *
 * 题目描述：
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如，输入一个长度为 9 的数组: {1, 2, 3, 2, 2, 2, 5, 4, 2}
 * 由于数字 2 在数组中出现了 5 次，超过数组长度的一半，因此输出 2。
 * 如果不存在则输出 0。
 *
 * 知识点：["数组"]
 */
public class MoreThanHalfNumInArray {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        MoreThanHalfNumInArray obj = new MoreThanHalfNumInArray();
        int[] arr = new int[]{1, 2, 3, 2, 2, 2, 5, 4, 2};
        System.out.println(obj.moreThanHalfNumInArray(arr));
    }
    /**
     * 剑指 Offer 面试题 39：数组中出现次数超过一半的数字
     *
     * @param arr
     * @return int
     */
    public int moreThanHalfNumInArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return moreThanHalfNumInArrayHashing(arr);
    }
    /**
     * 剑指 Offer 面试题 39：数组中出现次数超过一半的数字 -> 哈希计数
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param arr
     * @return int
     */
    private int moreThanHalfNumInArrayHashing(int[] arr) {
        Map<Integer, Integer> aMap = new TreeMap<>();
        for (int n : arr) {
            aMap.put(n, 1 + aMap.getOrDefault(n, 0));
        }
        for (Map.Entry<Integer, Integer> e : aMap.entrySet()) {
            if (e.getValue() > arr.length / 2) {
                return e.getKey();
            }
        }
        return 0;
    }
}
/* EOF */