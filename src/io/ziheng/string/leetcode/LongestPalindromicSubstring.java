package io.ziheng.string.leetcode;

/**
 * LeetCode 5. Longest Palindromic Substring
 * https://leetcode.com/problems/longest-palindromic-substring/
 */
public class LongestPalindromicSubstring {
    /**
     * Longest Palindromic Substring
     *
     * @param s
     * @return String
     */
    public String longestPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return s;
        }
        // return longestPalindromeBruteForce(s);
        return longestPalindromeExtendsWay(s);
    }
    /**
     * Longest Palindromic Substring -> Middle Extends
     *
     * Time Complexity: O(n^2)
     * Space Complexity: O(1)
     *
     * @param s
     * @return String
     */
    private String longestPalindromeExtendsWay(String s) {
        int len = s.length();
        String resultStr = "";
        for (int i = 0; i < len - 1; i++) {
            String oddStr = extendsPalindrome(s, i, i);
            String evenStr = extendsPalindrome(s, i, i + 1);
            if (oddStr.length() > resultStr.length()) {
                resultStr = oddStr;
            }
            if (evenStr.length() > resultStr.length()) {
                resultStr = evenStr;
            }
        }
        return resultStr;
    }
    private String extendsPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length()
            && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }
    /**
     * Longest Palindromic Substring -> Brute Force
     *
     * Time Complexity: O(n^3)
     * Space Complexity: O(1)
     *
     * @param s
     * @return String
     */
    private String longestPalindromeBruteForce(String s) {
        char[] arr = s.toCharArray();
        int longestPalindromeLength = 1;
        String longestPalindromeString = null;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (isPalindrome(arr, i, j)) {
                    if (j - i + 1 > longestPalindromeLength) {
                        longestPalindromeLength = j - i + 1;
                        longestPalindromeString = String.valueOf(
                            arr, i, longestPalindromeLength
                        );
                    }
                }
            }
        }
        return longestPalindromeString;
    }
    private boolean isPalindrome(char[] arr, int left, int right) {
        for (; left < right; left++, right--) {
            if (arr[left] != arr[right]) {
                return false;
            }
        }
        return true;
    }
    /**
     * Main Tests
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        LongestPalindromicSubstring obj = new LongestPalindromicSubstring();
        String s = "babad";
        System.out.println(
            obj.longestPalindrome(s)
        );
        s = "cbbd";
        System.out.println(
            obj.longestPalindrome(s)
        );
    }
}
/* EOF */