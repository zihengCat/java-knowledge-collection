package io.ziheng.codinginterviews;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 面试题 10-2：青蛙跳台阶
 *
 * 题目描述：
 * 一只青蛙一次可以跳上`1`级台阶，也可以一次跳上`2`级台阶。
 * 求该青蛙跳上一个`n`级的台阶总共有多少种跳法
 * 注：先后次序不同算不同的结果。
 *
 * 知识点：["递归"]
 */
public class JumpingStairs {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        JumpingStairs obj = new JumpingStairs();
        System.out.println(
            obj.jumpingStairs(10)
        );
    }
    /**
     * 剑指 Offer 面试题 10-2：青蛙跳台阶
     *
     * @param n
     * @return int
     */
    public int jumpingStairs(int n) {
        if (n < 0) {
            return 0;
        }
        // return jumpingStairsBruteForce(n);
        // return jumpingStairsWithMemo(n, new HashMap<Integer, Integer>());
        return jumpingStairsWithDP(n);
    }
    /**
     * 剑指 Offer 面试题 10-2：青蛙跳台阶 -> 动态规划
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return int
     */
    private int jumpingStairsWithDP(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int jumpA = 1;
        int jumpB = 1;
        int jumpN = 0;
        for (int i = 2; i <= n; i++) {
            jumpN = jumpB + jumpA;
            jumpA = jumpB;
            jumpB = jumpN;
        }
        return jumpN;
    }
    /**
     * 剑指 Offer 面试题 10-2：青蛙跳台阶 -> 记忆化搜索
     *
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * @param n
     * @param memo
     * @return int
     */
    private int jumpingStairsWithMemo(int n, Map<Integer, Integer> memo) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (memo.get(n) != null) {
            return memo.get(n);
        }
        memo.put(n, jumpingStairsWithMemo(n - 1, memo)
            + jumpingStairsWithMemo(n - 2, memo)
        );
        return memo.get(n);
    }
    /**
     * 剑指 Offer 面试题 10-2：青蛙跳台阶 -> 暴力递归
     *
     * 时间复杂度：O(2^n)
     * 空间复杂度：O(1)
     *
     * @param n
     * @return int
     */
    private int jumpingStairsBruteForce(int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return 1;
        }
        return jumpingStairsBruteForce(n - 1)
            + jumpingStairsBruteForce(n - 2);
    }
}
/* EOF */