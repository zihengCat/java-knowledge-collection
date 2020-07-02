package io.ziheng.others.leetcode;

/**
 * LeetCode 441. Arranging Coins
 * https://leetcode.com/problems/arranging-coins/
 */
public class ArrangingCoins {
    public static void main(String[] args) {
        ArrangingCoins obj = new ArrangingCoins();
        System.out.println(obj.arrangeCoins(10));
    }
    public int arrangeCoins(int n) {
        int formedRows = 0;
        for (int i = 1; n - i >= 0; i++) {
            n -= i;
            formedRows++;
        }
        return formedRows;
    }
}
/* EOF */