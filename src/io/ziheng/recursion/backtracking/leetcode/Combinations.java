package io.ziheng.recursion.backtrcking.leetcode;

import java.util.List;
import java.util.LinkedList;

/**
 * LeetCode 77. Combinations
 * https://leetcode.com/problems/combinations/submissions/
 */
public class Combinations {
    public static void main(String[] args) {
        List<List<Integer>> list = new Combinations().combine(5, 4);
        System.out.println(list);
    }
    private List<List<Integer>> resultList = new LinkedList<>();
    /**
     * C(n, k) 问题
     * [1, 2, 3, ..., n] 中寻找 k 个数的全部组合。
     *
     * @param n
     * @param k
     * @return {@code List<List<Integer>>}
     */
    public List<List<Integer>> combine(int n, int k) {
        if (n <= 0 || k <= 0 || k > n) {
            return resultList;
        }
        LinkedList<Integer> list = new LinkedList<>();
        combine0(n, k, 1, list);
        return resultList;
    }
    private void combine0(int n, int k, int startNum, LinkedList<Integer> result) {
        if (result.size() == k) {
            resultList.add(new LinkedList<>(result));
            return;
        }
        /**
         * 剪枝优化：
         * 
         * i <= n
         * i <= n - (k - result.size()) + 1
         */
        for (int i = startNum; i <= n - (k - result.size()) + 1; i++) {
            result.push(i);
            combine0(n, k, i + 1, result);
            result.pop();
        }
    }
}
/* EOF */