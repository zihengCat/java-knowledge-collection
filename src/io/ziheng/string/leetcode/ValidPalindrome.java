package io.ziheng.string.leetcode;
/**
 * LeetCode 125. Valid Palindrome
 * https://leetcode.com/problems/valid-palindrome/
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        ValidPalindrome obj = new ValidPalindrome();
        String s = "A man, a plan, a canal: Panama";
        System.out.println(s + " -> " + obj.isPalindrome(s));
        s = "0P";
        System.out.println(s + " -> " + obj.isPalindrome(s));
    }
    /**
     * Is Valid Palindrome String
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     *
     * @param s
     * @return boolean
     */
    public boolean isPalindrome(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0, j = arr.length - 1; i < j; /* ... */) {
            if (!Character.isAlphabetic(arr[i]) && !Character.isDigit(arr[i])) {
                i++;
                continue;
            }
            if (!Character.isAlphabetic(arr[j]) && !Character.isDigit(arr[j])) {
                j--;
                continue;
            }
            if (Character.toLowerCase(arr[i])
                != Character.toLowerCase(arr[j])) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
/* EOF */