package io.ziheng.codinginterviews;

import java.util.List;
import java.util.ArrayList;

/**
 * 剑指 Offer 面试题 58-2：左旋转字符串
 *
 * 题目描述：
 * 字符串的左选转操作是把字符串头部若干字符转移到字符串尾部。例如：
 * 输入：字符串 "abcdefg"，数字 2
 * 输出："cdefgab"
 *
 * 知识点：["字符串"]
 */
public class LeftRotateString {
    public static void main(String[] args) {
        LeftRotateString obj = new LeftRotateString();
        String s = "abcdefg";
        System.out.println(
            obj.leftRotateString(s, 2)
        );
    }
    public String leftRotateString(String s, int n) {
        if (s == null || s.length() == 0 || n < 0) {
            return s;
        }
        char[] charArr = s.toCharArray();
        List<Character> aList = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            aList.add(charArr[i]);
        }
        shiftCharArray(charArr, n);
        for (int i = charArr.length - n, j = 0; i < charArr.length; i++, j++) {
            charArr[i] = aList.get(j);
        }
        return String.valueOf(charArr);
    }
    private void shiftCharArray(char[] arr, int n) {
        for (int i = 0, j = n; j < arr.length; i++, j++) {
            arr[i] = arr[j];
        }
    }
}
/* EOF */