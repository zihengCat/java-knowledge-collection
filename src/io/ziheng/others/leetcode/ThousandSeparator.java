package io.ziheng.others.leetcode;
/**
 * LeetCode 1556. Thousand Separator
 * https://leetcode.com/problems/thousand-separator/
 */
public class ThousandSeparator {
    public static void main(String[] args) {
        // ...
    }
    public String thousandSeparator(int n) {
        if (n <= 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        int base = 10;
        int cnt = 0;
        while (n > 0) {
            if (cnt == 3) {
                sb.insert(0, '.');
                cnt = 0;
            }
            int remainder = n % base;
            n /= base;
            sb.insert(0, remainder);
            cnt++;
        }
        return sb.toString();
    }
}
/* EOF */