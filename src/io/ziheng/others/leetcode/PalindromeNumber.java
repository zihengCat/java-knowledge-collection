package io.ziheng.others.leetcode;
/**
 * LeetCode 9. Palindrome Number
 * https://leetcode.com/problems/palindrome-number/
 */
public class PalindromeNumber {
    public static void main(String[] args) {
        PalindromeNumber obj = new PalindromeNumber();
        System.out.println(
            obj.isPalindrome(121)
        );
        System.out.println(
            obj.isPalindrome(122)
        );
    }
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        return x == reverseNum(x);
    }
    private int reverseNum(int n) {
        int x = 0;
        /**
         * n = 1234
         * 123...4 -> 4
         * 12...3  -> 4 * 10 + 3 = 43
         * 1...2   -> 43 * 10 + 2 = 432
         * 0...1   -> 432 * 10 + 1 = 4321
         */
        while (n > 0) {
            int remainder = n % 10;
            n /= 10;
            x = x * 10 + remainder;
        }
        return x;
    }
}
/* EOF */