package io.ziheng.recursion.backtracking.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * LeetCode 1395. Count Number of Teams
 * https://leetcode.com/problems/count-number-of-teams/
 */
public class CountNumberOfTeams {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        int[] rating = new int[]{2,5,3,4,1,};
        CountNumberOfTeams obj = new CountNumberOfTeams();
        System.out.println(
            obj.numTeams(rating)
        );
        System.out.println(
            obj.numTeams(
                new int[]{2, 1, 3, }
            )
        );
    }
    private class Pair<L, R> {
        public L left;
        public R right;
        public Pair(L left, R right) {
            this.left = left;
            this.right = right;
        }
    }
    private int cnt = 0;
    public int numTeams(int[] rating) {
        if (rating == null || rating.length < 3) {
            return 0;
        }
        // 暴力法
        return numTeamsBruteForce(rating);
        // 回溯法
        // cnt = 0;
        // numTeams0(rating, 0, rating.length - 1, new LinkedList<Pair<Integer, Integer>>());
        // return cnt;
    }
    /**
     * 暴力法
     * 时间复杂度：O(n^3)
     * 空间复杂度：O(1)
     *
     * @param rating
     * @return int
     */
    private int numTeamsBruteForce(int[] rating) {
        int result = 0;
        for (int i = 0; i < rating.length; i++) {
            for (int j = 0; j < rating.length; j++) {
                for (int k = 0; k < rating.length; k++) {
                    if ((i < j && j < k)
                        && ((rating[i] < rating[j] && rating[j] < rating[k])
                        ||  (rating[i] > rating[j] && rating[j] > rating[k]))) {
                        result++;
                    }
                }
            }
        }
        return result;
    }
    /**
     * 回溯法。
     *
     * @param rating
     * @param start
     * @param end
     * @param list
     * @return void
     */
    private void numTeams0(int[] rating, int start, int end, List<Pair<Integer, Integer>> list) {
        if (start > end) {
            return;
        }
        if (list.size() == 3) {
            int i = list.get(0).right;
            int j = list.get(1).right;
            int k = list.get(2).right;

            int ii = list.get(0).left;
            int ji = list.get(1).left;
            int ki = list.get(2).left;

            if ((ii < ji && ji < ki)
                && ((i < j && j < k)
                || (i > j && j > k))) {
                cnt++;
                return;
            }
        }
        if (list.size() < 3) {
            for (int i = start; i <= end; i++) {
                list.add(new Pair<Integer,Integer>(i, rating[i]));
                numTeams0(rating, start + 1, end, list);
                list.remove(list.size() - 1);
            }
        }
    }
}
/* EOF */