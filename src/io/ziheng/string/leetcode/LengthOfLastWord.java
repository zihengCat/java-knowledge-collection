package io.ziheng.string.leetcode;

import java.util.List;
import java.util.ArrayList;

/**
 * LeetCode 58. Length of Last Word
 * https://leetcode.com/problems/length-of-last-word/
 */
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        String[] strs = s.split(s, ' ');
        if (strs.length == 0) {
            return 0;
        }
        return strs[strs.length - 1].length();
    }
    private List<String> split(String s, char c) {
        List<String> resultList = new ArrayList<>();
        char[] charArray = s.toCharArray();
        for (int i = 0, offset = 0; i < charArray.length; i++) {
            if (charArray[i] == c) {
                resultList.add(buildWith(charArray, offset, i));
                offset = i + 1;
            }
        }
        return resultList;
    }
    private String buildWith(char[] arr, int left, int right) {
        char[] charArray = new char[right - left];
        for (int i = 0; i < charArray.length; i++) {
            charArray[i] = arr[left + i];
        }
        return String.valueOf(charArray);
    }
}
/* EOF */