package io.ziheng.codinginterviews;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * 剑指 Offer 面试题 56：数组中数字出现次数
 *
 * 知识点：["数组"]
 */
public class NumberAppearsInArray {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        int[] arr = new int[]{2, 4, 3, 6, 3, 2, 5, 5, };
        NumberAppearsInArray obj = new NumberAppearsInArray();
        System.out.println(
            obj.twoNumberAppearsOnlyOnce(arr)
        );
        arr = new int[]{1, 1, 2, 2, 5, 5, 3, 3, 4, 6, 6, };
        System.out.println(
            obj.oneNumberAppearsOnlyOnce(arr, 2)
        );
    }
    /**
     * 剑指 Offer 面试题 56-1：数组中只出现一次的两数
     *
     * 题目描述：
     * 一个整型数组中有两个数字只出现了一次，其余数字都出现了两次，
     * 请找出数组中只出现一次的两数。
     *
     * @param arr
     * @return {@code List<Integer>}
     */
    public List<Integer> twoNumberAppearsOnlyOnce(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new LinkedList<>();
        }
        return twoNumberAppearsOnlyOnceHashing(arr);
    }
    /**
     * 剑指 Offer 面试题 56-1：数组中只出现一次的两数 -> 哈希表
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param arr
     * @return {@code List<Integer>}
     */
    private List<Integer> twoNumberAppearsOnlyOnceHashing(int[] arr) {
        Map<Integer, Integer> aMap = new HashMap<>();
        for (int n : arr) {
            aMap.put(n, 1 + aMap.getOrDefault(n, 0));
        }
        return aMap.entrySet()
            .stream()
            .filter(s -> s.getValue().intValue() == 1)
            .map(s -> s.getKey())
            .collect(Collectors.toList());
    }
    /**
     * 剑指 Offer 面试题 56-2：数组中唯一只出现一次的数字
     *
     * 题目描述：
     * 一个整型数组中有一个数字只出现了一次，其余数字都出现了两次，
     * 请找出数组中只出现一次的数。
     *
     * 升级难度：
     * 一个整型数组中有一个数字只出现了一次，其余数字都出现了三次，
     * 请找出数组中只出现一次的数。
     *
     * @param arr
     * @param int
     * @return int
     */
    public int oneNumberAppearsOnlyOnce(int[] arr, int times) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        if (times == 2) {
            return oneNumberAppearsOnlyOnceXOR(arr);
        } else if (times == 3) {
            // ...
        }
        return 0;
    }
    /**
     * 剑指 Offer 面试题 56-2：数组中唯一只出现一次的数字 -> 位运算 XOR
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param arr
     * @return int
     */
    private int oneNumberAppearsOnlyOnceXOR(int[] arr) {
        int resultNum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            resultNum ^= arr[i];
        }
        return resultNum;
    }
}
/* EOF */