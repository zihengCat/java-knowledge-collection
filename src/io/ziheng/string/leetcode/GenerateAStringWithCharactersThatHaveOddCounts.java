package io.ziheng.string.leetcode;
/**
 * LeetCode 1374. Generate a String With Characters That Have Odd Counts
 * https://leetcode.com/problems/generate-a-string-with-characters-that-have-odd-counts/
 */
public class GenerateAStringWithCharactersThatHaveOddCounts {
    public String generateTheString(int n) {
        if (n <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (n % 2 == 0) {
            for (int i = 0; i < n - 1; i++) {
                sb.append('x');
            }
            sb.append('y');
        } else {
            for (int i = 0; i < n; i++) {
                sb.append('x');
            }
        }
        return sb.toString();
    }
}
/* EOF */