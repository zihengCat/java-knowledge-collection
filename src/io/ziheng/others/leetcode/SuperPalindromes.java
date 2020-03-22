package io.ziheng.others.leetcode;
/**
 * LeetCode 906. Super Palindromes
 * https://leetcode.com/problems/super-palindromes/
 */
public class SuperPalindromes {
    /**
     * 主函数 -> 测试用例
     */
    public static void main(String[] args) {
        SuperPalindromes obj = new SuperPalindromes();
        obj.superpalindromesInRange("4", "1000");
    }
    public int superpalindromesInRange(String L, String R) {
        long leftNum = Integer.valueOf(L);
        long rightNum = Integer.valueOf(R);
        int cnt = 0;
        for (long i = leftNum; i <= rightNum; i++) {
            if (isPalindrome(i)
                && isPerfectSquare(i)
                && isPalindrome((long)Math.sqrt(i))) {
                // System.out.println(
                //     "i = " + i 
                //     + " sqrt(i) = "
                //     + Double.valueOf(Math.sqrt(i)).intValue()
                // );
                cnt++;
            }
        }
        return cnt;
    }
    public boolean isPalindrome(long x) {
        return x == reverse(x);
    }
    public long reverse(long x) {
        long ans = 0;
        while (x > 0) {
            ans = 10 * ans + x % 10;
            x /= 10;
        }
        return ans;
    }
    private boolean isPalindromeNum(int n) {
        char[] arr = String.valueOf(n).toCharArray();
        for (int i = 0, j = arr.length - 1; i <= j; i++, j--) {
            if (arr[i] != arr[j]) {
                return false;
            }
        }
        return true;
    }
    private boolean isPerfectSquare(long n) {
        if (n < 0) {
            return false;
        }
        long tst = (long)(Math.sqrt(n) + 0.5);
        return tst * tst == n;
    }
}
/* EOF */