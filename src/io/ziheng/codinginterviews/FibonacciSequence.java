package io.ziheng.codinginterviews;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 面试题 10-1：斐波那契数列
 *
 * 题目描述：
 *
 * 输入一个正整数`n`，求斐波那契数列的第`n`项。
 * 注：n <= 39
 *
 * 知识点：["递归"]
 */
public class FibonacciSequence {
    /**
     * 主函数 -> 测试用例
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        FibonacciSequence obj = new FibonacciSequence();
        System.out.println(
            obj.fibonacci(20)
        );
    }
    /**
     * 剑指 Offer 面试题 10-1：斐波那契数列
     *
     * @param n
     * @return int
     */
    public int fibonacci(int n) {
        if (n < 0) {
            return 0;
        }
        // return fibonacciFormula(n);
        // return fibonacciWithMemo(n, new HashMap<Integer, Integer>());
        return fibonacciWithDP(n);
    }
    /**
     * 剑指 Offer 面试题 10-1：斐波那契数列 -> 动态规划
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return int
     */
    private int fibonacciWithDP(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        int fibA = 0;
        int fibB = 1;
        int fibN = 0;
        for (int i = 2; i <= n; i++) {
            fibN = fibB + fibA;
            fibA = fibB;
            fibB = fibN;
        }
        return fibN;
    }
    /**
     * 剑指 Offer 面试题 10-1：斐波那契数列 -> 记忆化搜索
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param n
     * @param memo
     * @return int
     */
    private int fibonacciWithMemo(int n, Map<Integer, Integer> memo) {
        if (memo.get(n) != null) {
            return memo.get(n);
        }
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        memo.put(n, fibonacciWithMemo(n - 1, memo)
            + fibonacciWithMemo(n - 2, memo)
        );
        return memo.get(n);
    }
    /**
     * 剑指 Offer 面试题 10-1：斐波那契数列 -> 暴力法
     *
     * 时间复杂度：O(2^n)
     * 空间复杂度：O(1)
     *
     * @param n
     * @param memo
     * @return int
     */
    private int fibonacciFormula(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        return fibonacciFormula(n - 1)
            + fibonacciFormula(n - 2);
    }
}
/* EOF */