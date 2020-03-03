package io.ziheng.others.leetcode;

/**
 * LeetCode 860. Lemonade Change
 * https://leetcode.com/problems/lemonade-change/
 */
public class LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        // 安全检查
        if (bills == null || bills.length == 0) {
            return false;
        }
        // 初始化状态
        int five = 0;
        int ten = 0;
        int twenty = 0;
        for (int bill: bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                ten++;
                five--;
            } else if (bill == 20) {
                twenty++;
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else {
                    five -= 3;
                }
            }
            if (five < 0 || ten < 0 || twenty < 0) {
                return false;
            }
        }
        return true;
    }
}
/* EOF */