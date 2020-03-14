package io.ziheng.others.leetcode;
/**
 * LeetCode 168. Excel Sheet Column Title
 * https://leetcode.com/problems/excel-sheet-column-title/
 */
public class ExcelSheetColumnTitle {
    public String convertToTitle(int n) {
        if (n < 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int remainder = (n - 1) % 26;
            sb.append((char)(65 + remainder));
            n = (n - 1) / 26;
        }
        return sb.reverse().toString();
    }
}
/* EOF */