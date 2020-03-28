package io.ziheng.string.leetcode;
/**
 * LeetCode 14. Longest Common Prefix
 * https://leetcode.com/problems/longest-common-prefix/
 */
public class LongestCommonPrefix {
    /**
     * 主函数 -> 测试用例
     *
     * @param args
     * @return void
     */
    public static void main(String[] args) {
        LongestCommonPrefix obj = new LongestCommonPrefix();
        String[] strs = new String[] {
            "flower", "flow", "flight",
        };
        System.out.println(
            obj.longestCommonPrefix(strs)
        );
    }
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        // return longestCommonPrefixScanningWay(strs);
        return longestCommonPrefixDivideAndConquer(strs, 0, strs.length - 1);
    }
    /**
     * 分治法
     *
     * @param strs
     * @return String
     */
    private String longestCommonPrefixDivideAndConquer(String[] strs, int left, int right) {
        if (left >= right) {
            return strs[left];
        }
        int mid = (left + right) / 2;
        String lcpLeft = longestCommonPrefixDivideAndConquer(strs, left, mid);
        String lcpRight = longestCommonPrefixDivideAndConquer(strs, mid + 1, right);
        return findCommonPrefix(lcpLeft, lcpRight);
    }
    /**
     * 扫描法
     *
     * @param strs
     * @return String
     */
    private String longestCommonPrefixScanningWay(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String lcp = strs[0];
        for (String s : strs) {
            lcp = findCommonPrefix(lcp, s);
        }
        return lcp;
    }
    /**
     * 寻找字符串公共前缀。
     *
     * @param s1
     * @param s2
     * @return String
     */
    private String findCommonPrefix(String s1, String s2) {
        int len = Math.min(s1.length(), s2.length());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) == s2.charAt(i)) {
                sb.append(s1.charAt(i));
            } else {
                break;
            }
        }
        return sb.toString();
    }
}
/* EOF */